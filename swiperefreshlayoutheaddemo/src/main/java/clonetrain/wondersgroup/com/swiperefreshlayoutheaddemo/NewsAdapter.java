package clonetrain.wondersgroup.com.swiperefreshlayoutheaddemo;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;

/**
 * Created by zhangwentao on 16/10/31.
 * Description : 消息适配
 * Version :1.0
 */
public class NewsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<String> datas;

    public NewsAdapter(List<String> dataList) {
        datas = dataList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new NewsViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_my_news, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof NewsViewHolder) {
            NewsViewHolder viewHolder = (NewsViewHolder) holder;
            viewHolder.newsTime.setText(datas.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return datas == null ? 0 : datas.size();
    }

    class NewsViewHolder extends RecyclerView.ViewHolder {
        TextView newsTime;

        public NewsViewHolder(View itemView) {
            super(itemView);
            newsTime = (TextView) itemView.findViewById(R.id.text_view_news_content);
        }

    }
}
