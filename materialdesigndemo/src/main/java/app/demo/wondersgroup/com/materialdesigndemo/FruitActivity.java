package app.demo.wondersgroup.com.materialdesigndemo;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.johnson.commonlibs.common_utils.BaseActivity;
import com.johnson.commonlibs.common_utils.utils.LogUtil;

import static android.support.design.R.styleable.CollapsingToolbarLayout;
import static app.demo.wondersgroup.com.materialdesigndemo.MainActivity.EXTRA_CURRENT_ALBUM_POSITION;
import static app.demo.wondersgroup.com.materialdesigndemo.MainActivity.EXTRA_STARTING_ALBUM_POSITION;
import static app.demo.wondersgroup.com.materialdesigndemo.R.styleable.Toolbar;

public class FruitActivity extends BaseActivity {
    private String fruitName;
    private int imgId;

    private static final String STATE_CURRENT_PAGE_POSITION = "state_current_page_position";
    private int mStartingPosition;
    private int mCurrentPosition;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fruit);

        mStartingPosition = getIntent().getIntExtra(EXTRA_STARTING_ALBUM_POSITION, 0);
        if (savedInstanceState == null) {
            mCurrentPosition = mStartingPosition;
        } else {
            mCurrentPosition = savedInstanceState.getInt(STATE_CURRENT_PAGE_POSITION);
        }

        initData();
        initView();
    }

    private void initData() {
        fruitName = getIntent().getStringExtra("name");
        imgId = getIntent().getIntExtra("imgId",0);
    }

    private void initView() {
        CollapsingToolbarLayout collapsingToolBar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        ImageView fruitImg = (ImageView) findViewById(R.id.fruit_img);
        Toolbar toolbar = (Toolbar) findViewById(R.id.fruit_tool_bar);
        TextView fruitContent = (TextView) findViewById(R.id.fruit_content_view);

        setSupportActionBar(toolbar);
        collapsingToolBar.setTitle(fruitName);
        Glide.with(this).load(imgId).into(fruitImg);

        // 共享元素变化需要指定 imgage 的 transitionName
        String transitionName = getIntent().getStringExtra("profile");
        if (!TextUtils.isEmpty(transitionName)) {
            ViewCompat.setTransitionName(fruitImg, transitionName);
        }

        StringBuilder builder = new StringBuilder();
        for (int i =0;i< 100;i++){
            builder.append(fruitName);
        }
        fruitContent.setText(builder.toString());

        //actionBar 显示默认返回键
        ActionBar actionBar = getSupportActionBar();
        if (actionBar!= null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(STATE_CURRENT_PAGE_POSITION, mCurrentPosition);
    }

    /**
     * 添加了 共享元素变换效果后 返回的时候 需要将上次传递的position 进行传递
     */
    @Override
    public void finishAfterTransition() {
        Intent data = new Intent();
        data.putExtra(EXTRA_STARTING_ALBUM_POSITION, mStartingPosition);
        data.putExtra(EXTRA_CURRENT_ALBUM_POSITION, mCurrentPosition);
        setResult(RESULT_OK, data);
        super.finishAfterTransition();
        LogUtil.e("finishAfterTransition","finishAfterTransition");
    }

    /**
     * 默认返回键事件 添加共享元素时候 返回键方法 不再是finish
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home){
//            finish();
            supportFinishAfterTransition();
        }
        return super.onOptionsItemSelected(item);
    }
}
