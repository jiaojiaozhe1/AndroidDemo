package app.demo.wondersgroup.com.materialdesigndemo.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import app.demo.wondersgroup.com.materialdesigndemo.FruitActivity;
import app.demo.wondersgroup.com.materialdesigndemo.R;
import app.demo.wondersgroup.com.materialdesigndemo.model.Fruit;

/**
 * Created by zhangwentao on 2016/12/21.
 */

public class FruitAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Fruit> fruits;
    public FruitAdapter(List<Fruit> fruitList){
        fruits = fruitList;
    }
    private Context mContext;
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mContext == null){
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.card_view_lay, parent, false);
        return new FrutiViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
       FrutiViewHolder  viewHolder = (FrutiViewHolder) holder;
        viewHolder.fruitName.setText(fruits.get(position).getName());
        Glide.with(mContext).load(fruits.get(position).getImgId()).into(viewHolder.imageView);

        viewHolder.fruitName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view,"水果名字被点击",Snackbar.LENGTH_SHORT)
                        .setAction("是否取消", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                }).show();
            }
        });

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, FruitActivity.class);
                intent.putExtra("name",fruits.get(position).getName());
                intent.putExtra("imgId",fruits.get(position).getImgId());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return fruits.size();
    }

    class FrutiViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView fruitName;

        public FrutiViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.fruit_img);
            fruitName = (TextView) itemView.findViewById(R.id.fruit_name);
        }
    }

}
