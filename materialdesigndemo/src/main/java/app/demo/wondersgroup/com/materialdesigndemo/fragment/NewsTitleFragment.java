package app.demo.wondersgroup.com.materialdesigndemo.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import app.demo.wondersgroup.com.materialdesigndemo.NewsContentActivity;
import app.demo.wondersgroup.com.materialdesigndemo.R;
import app.demo.wondersgroup.com.materialdesigndemo.model.News;

/**
 * Created by zhangwentao on 2016/12/23.
 */

public class NewsTitleFragment extends Fragment {
    private static final String TAG = "NewsTitleFragment";
    private List<News> newses = new ArrayList<>();
    private NewsTitleAdapter adapter;
    private boolean mIsPhone = true;
    private RecyclerView recyclerView;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.e(TAG, "onAttach");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.e(TAG, "onCreateView");
        return inflater.inflate(R.layout.fragment_fruit, null, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = (RecyclerView) view.findViewById(R.id.news_title_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getActivity().findViewById(R.id.news_content_lay) != null) {
            mIsPhone = false;
        } else {
            mIsPhone = true;
        }

        initDatas();
    }

    private void initDatas() {
        StringBuilder stringBuilder = new StringBuilder();
        Random random = new Random();
        int len = random.nextInt(5) + 1;
        for (int i = 0; i < len; i++) {
            stringBuilder.append("今日头条" + len);
            News news = new News();
            news.setNewsTitle(stringBuilder.toString());
            news.setNewsContent(stringBuilder.toString() + "头条内容啦拉拉啦拉拉");
            newses.add(news);
        }

        adapter = new NewsTitleAdapter(newses, mIsPhone);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(TAG, "onCreate");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.e(TAG, "onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.e(TAG, "onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.e(TAG, "onDestroyView");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.e(TAG, "onDetach");
    }


    public class NewsTitleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        private List<News> newses;
        private boolean isPhone;

        public NewsTitleAdapter(List<News> newsList, boolean phone) {
            newses = newsList;
            isPhone = phone;
        }


        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new NewsTitleAdapter.NewsViewHolder(LayoutInflater.from(getActivity()).inflate(R.layout.news_title_item, parent, false));
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
            NewsTitleAdapter.NewsViewHolder viewHolder = (NewsTitleAdapter.NewsViewHolder) holder;
            viewHolder.textView.setText(newses.get(position).getNewsTitle());

            viewHolder.textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (isPhone) {
                        NewsContentActivity.startAction(getActivity(), newses.get(position).getNewsContent());
                    } else {
                        NewsContentFragment contentFragment = (NewsContentFragment) getFragmentManager().findFragmentById(R.id.news_content_fragment);
                        contentFragment.refreshContent(newses.get(position).getNewsContent());
                    }
                }
            });
        }

        @Override
        public int getItemCount() {
            return newses.size();
        }

        class NewsViewHolder extends RecyclerView.ViewHolder {
            TextView textView;

            public NewsViewHolder(View itemView) {
                super(itemView);
                textView = (TextView) itemView.findViewById(R.id.news_title_content_view);
            }
        }
    }

}
