package app.demo.wondersgroup.com.materialdesigndemo;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import app.demo.wondersgroup.com.materialdesigndemo.adapter.FruitAdapter;
import app.demo.wondersgroup.com.materialdesigndemo.model.Fruit;

/**
 * 结合materialDesign 实现简单的效果
 */
public class MainActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private FloatingActionButton floatingActionButton;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private Fruit[] fruits = {new Fruit("陕西冰糖心红富士", R.mipmap.muli_fruit),
            new Fruit("陕西红富士", R.mipmap.sum),
            new Fruit("海南大香蕉", R.mipmap.autom),
            new Fruit("泰国臭榴莲", R.mipmap.sala),
            new Fruit("美国甜凤梨", R.mipmap.frush)};

    private int[] colors = {R.color.colorPrimary};

    private List<Fruit> fruitList = new ArrayList<>();
    private FruitAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();
        initView();


//        ActionBar supportActionBar = getSupportActionBar();
//        if (supportActionBar != null) {
//            supportActionBar.setDisplayHomeAsUpEnabled(true);// 显示默认的控件HomeAsUp
//            supportActionBar.setHomeAsUpIndicator(R.mipmap.date);//修改HomeAsUp控件的图标
//        }


        // 添加了可以打开滑动抽屉的icon 并且自己实现了点击事件
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        //navigationView 操作
        navigationView.setCheckedItem(R.id.call);//默认选中menu中第一个item
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                navigationView.setCheckedItem(item.getItemId());//点击navigationView 选中menu 中item
                drawerLayout.closeDrawers();
                NewsActivity.startAction(MainActivity.this);
                return false;
            }
        });

    }

    private void initData() {
        for (int i = 0; i < 20; i++) {
            Random random = new Random();
            int index = random.nextInt(fruits.length);
            fruitList.add(fruits[index]);
        }
    }

    private void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        floatingActionButton = (FloatingActionButton) findViewById(R.id.floating_action_btn);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);
        setSupportActionBar(toolbar);

        //这里会看到snackBar 弹出来会盖住 floatingActionBar 那么怎么办呢 需要加入CoordinatorLayout 它的作用和
        //FrameLayout 一样,但是它可以监听子控件的事件 从而做出合理相应,如果把floatingActionButton 放入到 CoordinatorLayout
        //中,当出现遮盖的时候 会自动将floatingActionButton向上移动
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "要删除吗", Snackbar.LENGTH_SHORT).setAction("不同意", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Toast.makeText(MainActivity.this, "保存了数据", Toast.LENGTH_LONG).show();
                    }
                }).show();
            }
        });


        //reclclerView 使用
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        adapter = new FruitAdapter(fruitList);
        recyclerView.setAdapter(adapter);


        swipeRefreshLayout.setColorSchemeColors(colors);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshRecyclerView();
            }
        });


    }

    private void refreshRecyclerView() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            swipeRefreshLayout.setRefreshing(false);
                        }
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.tool_bar_lay, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.backup:
                startActivity(new Intent(this, FruitViewActivity.class));
                break;
            case R.id.delete:
                startActivity(new Intent(this, MenuActivity.class));
                break;
            case R.id.setting:
                Toast.makeText(this, "setting....", Toast.LENGTH_LONG).show();
                break;
//            case android.R.id.home:
//                drawerLayout.openDrawer(GravityCompat.START);
//                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
