package app.demo.wondersgroup.com.materialdesigndemo;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.johnson.commonlibs.common_utils.BaseActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import app.demo.wondersgroup.com.materialdesigndemo.adapter.FruitAdapter;
import app.demo.wondersgroup.com.materialdesigndemo.model.Fruit;

/**
 * recyclerView 瀑布式布局使用
 */
public class FruitViewActivity extends BaseActivity {
    private SwipeRefreshLayout refreshLayout;
    private RecyclerView recyclerView;
    private FruitAdapter adapter;

    private Fruit[] fruits = {
            new Fruit("陕西冰糖心红富士", R.mipmap.muli_fruit),
            new Fruit("陕西红富士", R.mipmap.sum),
            new Fruit("海南大香蕉", R.mipmap.autom),
            new Fruit("陕西红富士", R.mipmap.sum),
            new Fruit("泰国臭榴莲", R.mipmap.sala),
            new Fruit("陕西红富士", R.mipmap.sum),
            new Fruit("泰国臭榴莲", R.mipmap.sala),
            new Fruit("陕西红富士", R.mipmap.sum),
            new Fruit("陕西红富士", R.mipmap.sum),
            new Fruit("海南大香蕉", R.mipmap.autom),
            new Fruit("陕西红富士", R.mipmap.sum),
            new Fruit("泰国臭榴莲", R.mipmap.sala),
            new Fruit("美国甜凤梨", R.mipmap.frush)};

    private int[] colors = {R.color.colorPrimary};

    private List<Fruit> furitList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fruit_view);

        initDatas();
        initView();
    }

    private void initDatas() {
        Random random = new Random();
        int len = random.nextInt(6) + 1;
        StringBuilder stringBuilder = new StringBuilder();
        for (Fruit fruit : fruits) {
            for (int i = 0; i < len; i++) {
                stringBuilder.append(fruit.getName());
            }
            fruit.setName(stringBuilder.toString());
            furitList.add(fruit);

        }
    }

    private void initView() {
        refreshLayout = (SwipeRefreshLayout) findViewById(R.id.fruit_view_swipe_layout);
        recyclerView = (RecyclerView) findViewById(R.id.fruit_view_recycler_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.fruit_view_toolbar);

        toolbar.setTitle("水果列表");
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);//默认返回键显示
        }


        //瀑布流方式布局  第一个参数是列数, 第二个参数是 排列方式 纵向  那么item 布局需要控制高度,因为 垂直方向是无限制的
        // 水平方向会因为是2列宽度会根据屏幕适配

        StaggeredGridLayoutManager staggeredGrid = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(staggeredGrid);
        adapter = new FruitAdapter(furitList);
        recyclerView.setAdapter(adapter);

        //刷新动作
        refreshLayout.setColorSchemeColors(colors);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshDatas();
            }
        });
    }

    private void refreshDatas() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            refreshLayout.setRefreshing(false);
                            furitList.add(new Fruit("插入的水果", R.mipmap.sum));
                            adapter.notifyItemInserted(furitList.size() -1);//插入最后
//                            adapter.notifyItemChanged(furitList.size() -1);
                            recyclerView.scrollToPosition(furitList.size() -1);// 滚动到最后
                        }
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home){// 默认返回键事件
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
