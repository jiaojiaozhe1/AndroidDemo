package app.demo.wondersgroup.com.materialdesigndemo;

import android.content.Intent;
import android.os.PersistableBundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class MenuActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        //如果销毁活动保存数据 savedInstanceState不为空,如果没有保存数据 savedInstanceState 为null
        if (savedInstanceState != null){
            String data = savedInstanceState.getString("data");
            Log.e("onCreate","data" + data);
        }
        initView();
    }

    private void initView() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.menu_tool_bar);
        toolbar.setTitle("menu");
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

    }

    /**
     * 当前活动跳转到其他活动,当前活动处于onStop状态,当系统资源不足会将当前活动回收,如果再次回到当前活动,会重新执行onCreate,onStart,onResume...
     * 看起来没变化,但是当前活动保存的数据会清空,所以使用下面方法进行数据保存,当重新回到onCreate savedInstanceState 保存了数据,不为空,可以获取保存
     * 数据
     * @param outState
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        String data = "当活动被销毁可以在销毁之前将活动状态进行保存~~ 然后销毁活动后再次进入活动会重新执行onCreate 开始一个新的生命在周期";
        outState.putString("data",data);

            //bundle intent 都可以用来保存数据
//        Intent intent = new Intent(this,MainActivity.class);
//        Bundle bundle = new Bundle();
//        bundle.putString("data","bund");
//        intent.putExtras(bundle);
//        startActivity(intent);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_lay,menu);
        return true;//返回值为ture menu 是可见的并且meun 的其他功能起作用,返回值false menu 不可见并且不起作用
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
            break;
        }
        return super.onOptionsItemSelected(item);
    }
}
