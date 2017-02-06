package app.demo.wondersgroup.com.materialdesigndemo;

import android.provider.ContactsContract;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.johnson.commonlibs.common_utils.BaseActivity;

import static android.support.design.R.styleable.CollapsingToolbarLayout;
import static app.demo.wondersgroup.com.materialdesigndemo.R.styleable.Toolbar;

public class FruitActivity extends BaseActivity {
    private String fruitName;
    private int imgId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fruit);
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

    /**
     * 默认返回键事件
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
