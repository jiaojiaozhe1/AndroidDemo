package clonetrain.wondersgroup.com.swiperefreshlayoutheaddemo;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.reginald.swiperefresh.CustomSwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<String> datas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final CustomSwipeRefreshLayout swipeRefreshLayout = (CustomSwipeRefreshLayout) findViewById(R.id.swipelayout);
        swipeRefreshLayout.setCustomHeadview(new MyCustomHeadView(this));
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        for (int i = 0; i < 30;i++){

            datas.add("测试数据");
        }
        NewsAdapter adapter = new NewsAdapter(datas);

        recyclerView.setAdapter(adapter);

        //set onRefresh listener
        swipeRefreshLayout.setOnRefreshListener(new CustomSwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // do something here when it starts to refresh
                // e.g. to request data from server

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeRefreshLayout.refreshComplete();
                    }
                },4000);
            }
        });
    }
}
