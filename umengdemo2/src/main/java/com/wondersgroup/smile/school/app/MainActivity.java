package com.wondersgroup.smile.school.app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.johnson.commonlibs.common_utils.BaseActivity;
import com.johnson.commonlibs.common_utils.utils.LogUtil;
import com.johnson.commonlibs.common_utils.utils.ToastUtils;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;

import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements View.OnClickListener {
    @Bind(R.id.qqLogin)
    Button qqLogin;
    @Bind(R.id.weChatLogin)
    Button weChatLogin;

    @Bind(R.id.weChatShare)
    Button weChatShare;
    @Bind(R.id.weChatCircleShare)
    Button weChatSCirclehare;
    @Bind(R.id.qqShare)
    Button qqShare;
    @Bind(R.id.qqZoneShare)
    Button qqZoneShare;

    UMImage image;
    UMShareAPI mShareAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        mShareAPI = UMShareAPI.get(MainActivity.this);
        image = new UMImage(this, R.mipmap.ic_launcher);
        image.setThumb(image);

        initListener();
    }

    private void initListener() {
        qqLogin.setOnClickListener(this);
        weChatLogin.setOnClickListener(this);
        qqShare.setOnClickListener(this);
        qqZoneShare.setOnClickListener(this);
        weChatShare.setOnClickListener(this);
        weChatSCirclehare.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == qqLogin) {
            //qq 登录不用授权直接获取用户信息就可以,但是必须实现onActivityResult 否则没有回掉信息
            mShareAPI.getPlatformInfo(this, SHARE_MEDIA.QQ, umAuthListener);
        } else if (view == weChatLogin) {
            //微信登录 先授权 再获取用户信息
            mShareAPI.doOauthVerify(MainActivity.this, SHARE_MEDIA.WEIXIN, umAuthListener);

            mShareAPI.getPlatformInfo(this, SHARE_MEDIA.WEIXIN, umAuthListener);
        } else if (view == qqShare) {
            sharePlatForm(SHARE_MEDIA.QQ, "qq 朋友分享");
        } else if (view == qqZoneShare) {
            sharePlatForm(SHARE_MEDIA.QZONE, "qq 空间分享");

        } else if (view == weChatShare) {
            sharePlatForm(SHARE_MEDIA.WEIXIN, "微信朋友分享");

        } else if (view == weChatSCirclehare) {
            sharePlatForm(SHARE_MEDIA.WEIXIN_CIRCLE, "微信朋友圈分享");
        }

    }

    /**
     * 分享有分享 文本,图片,连接,视频,音乐,下面的例子是讲解分享了一个百度页面的连接
     * @param share_media
     * @param content
     */
    private void sharePlatForm(SHARE_MEDIA share_media, String content) {
        new ShareAction(MainActivity.this).setPlatform(share_media)
                .withText(content)//分享内容
                .withTitle("标题")//分享标题
                .withTargetUrl("http://www.baidu.com")//分享连接
                .withMedia(image)//分享图片
                .setCallback(umShareListener)//分享后的回掉
                .share();
    }

    /**
     * 第三方分享回调
     */
    private UMShareListener umShareListener = new UMShareListener() {
        @Override
        public void onResult(SHARE_MEDIA platform) {
            LogUtil.e("plat", "platform" + platform);

            ToastUtils.showToast(MainActivity.this, platform + " 分享成功啦");

        }

        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            ToastUtils.showToast(MainActivity.this, platform + " 分享失败啦");
            if (t != null) {
                LogUtil.e("plat", "Throwable===" + t);
            }
        }

        @Override
        public void onCancel(SHARE_MEDIA platform) {
            ToastUtils.showToast(MainActivity.this, platform + " 分享取消了");
        }
    };


    /**
     * 第三方登录回调
     */

    private UMAuthListener umAuthListener = new UMAuthListener() {
        @Override
        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {
            if (platform == SHARE_MEDIA.QQ) {
                ToastUtils.showToast(getApplicationContext(), "qq ===Authorize succeed");

            } else if (platform == SHARE_MEDIA.WEIXIN) {
                ToastUtils.showToast(getApplicationContext(), "weXin ===Authorize succeed");
            }
        }

        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {
            if (platform == SHARE_MEDIA.QQ) {
                ToastUtils.showToast(getApplicationContext(), "qq ===Authorize fail");

            } else if (platform == SHARE_MEDIA.WEIXIN) {
                ToastUtils.showToast(getApplicationContext(), "weiXin ===Authorize fail");
            }
        }

        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {
            if (platform == SHARE_MEDIA.QQ) {

                ToastUtils.showToast(getApplicationContext(), "qq ===Authorize cancel");

            } else if (platform == SHARE_MEDIA.WEIXIN) {
                ToastUtils.showToast(getApplicationContext(), "qq ===Authorize cancel");
            }
        }
    };

    /**
     * qq 或者新浪分享或者登录需要实现
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);

    }

}
