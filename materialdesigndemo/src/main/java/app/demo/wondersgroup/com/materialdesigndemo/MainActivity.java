package app.demo.wondersgroup.com.materialdesigndemo;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.johnson.commonlibs.common_utils.BaseActivity;
import com.johnson.commonlibs.common_utils.utils.ActivityController;
import com.johnson.commonlibs.common_utils.utils.LogUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import app.demo.wondersgroup.com.materialdesigndemo.adapter.FruitAdapter;
import app.demo.wondersgroup.com.materialdesigndemo.model.Fruit;

/**
 * 结合materialDesign 实现简单的效果
 */
public class MainActivity extends BaseActivity implements View.OnClickListener {
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private FloatingActionButton floatingActionButton;
    private RecyclerView recyclerView;
    private ImageView settingImg;
    private SwipeRefreshLayout swipeRefreshLayout;

    static final String EXTRA_STARTING_ALBUM_POSITION = "extra_starting_item_position";
    static final String EXTRA_CURRENT_ALBUM_POSITION = "extra_current_item_position";

    private Fruit[] fruits = {new Fruit("陕西冰糖心红富士", R.mipmap.muli_fruit),
            new Fruit("陕西红富士", R.mipmap.sum),
            new Fruit("海南大香蕉", R.mipmap.autom),
            new Fruit("泰国臭榴莲", R.mipmap.sala),
            new Fruit("美国甜凤梨", R.mipmap.frush)};

    private int[] colors = {R.color.colorPrimary};

    private List<Fruit> fruitList = new ArrayList<>();
    private FruitAdapter adapter;

    public static void startAction(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();
        initView();
        initListener();


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
    }

    /**
     * 返回键关闭抽屉
     */
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    private void initListener() {
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

        settingImg.setOnClickListener(this);
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

        //获取navigationView 中头布局的组件
        settingImg = (ImageView) navigationView.getHeaderView(0).findViewById(R.id.home_setting);

        setSupportActionBar(toolbar);

        //这里会看到snackBar 弹出来会盖住 floatingActionBar 那么怎么办呢 需要加入CoordinatorLayout 它的作用和
        //FrameLayout 一样,但是它可以监听子控件的事件 从而做出合理相应,如果把floatingActionButton 放入到 CoordinatorLayout
        //中,当出现遮盖的时候 会自动将floatingActionButton向上移动
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar snackbar = Snackbar.make(view, "要删除吗", Snackbar.LENGTH_SHORT).setAction("不同意", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Toast.makeText(MainActivity.this, "保存了数据", Toast.LENGTH_LONG).show();
                    }
                }).setActionTextColor(Color.RED);
                snackbar.show();

//                setActionTextColor() 用来修改action 文字内容

//                //改变Snackbar文本的颜色
//                View barView = snackbar.getView();
//                TextView textView = (TextView) barView.findViewById(android.support.design.R.id.snackbar_text);
//                textView.setTextColor(Color.YELLOW);
//                snackbar.show();
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

    /**
     * 当返回的时候 拿到标记的recyclerview position 自动滚到到 上一次点击的位置
     *
     * @param requestCode
     * @param data
     */
    @Override
    public void onActivityReenter(int requestCode, Intent data) {
        super.onActivityReenter(requestCode, data);
        Bundle mTmpReenterState = new Bundle(data.getExtras());
        int currentPosition = mTmpReenterState.getInt(EXTRA_CURRENT_ALBUM_POSITION);
        recyclerView.scrollToPosition(currentPosition);

        LogUtil.e("onActivityReenter", "onActivityReenter");
//        postponeEnterTransition();
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
                //显示启动活动
                startActivity(new Intent(this, FruitViewActivity.class));
                break;
            case R.id.delete:
                //隐式启动活动
                Intent intent = new Intent();
                intent.setAction("android.intent.action.menu.activity");
                startActivity(intent);
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

    @Override
    public void onClick(View view) {
        if (view == settingImg) {
//            ActivityController.finishAllActivities();
//            LoginActivity.startLoginAction(this);
            VideoPlayerView.startAction(this);
        }
    }
}
