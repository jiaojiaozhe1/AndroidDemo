package app.demo.wondersgroup.com.materialdesigndemo.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import app.demo.wondersgroup.com.materialdesigndemo.R;


/**
 * Created by zhangwentao on 2016/12/29.
 * 新闻内容fragment
 */

public class NewsContentFragment extends Fragment {
    private TextView contentView;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.news_content_layout, null, false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
       contentView =(TextView) view.findViewById(R.id.news_content_view);

    }

    public void refreshContent(String content){
        contentView.setText(content);
    }

}
