package clonetrain.wondersgroup.com.elementtransition;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import static clonetrain.wondersgroup.com.elementtransition.Constants.ALBUM_IMAGE_URLS;

/**
 * Created by zhangwentao on 16/10/31.
 * Description :
 * Version :
 */
public class ElementShareAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context mContext;

    ElementShareAdapter(Context context) {
        mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ElementViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cardview, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ElementViewHolder){
            ElementViewHolder viewHolder = (ElementViewHolder) holder;
            viewHolder.bind(position);
        }
    }

    @Override
    public int getItemCount() {
        return ALBUM_IMAGE_URLS.length;
    }

    class ElementViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imageView;

        public ElementViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            imageView = (ImageView) itemView.findViewById(R.id.image_element);
        }

        public void bind(int position) {
            Glide.with(mContext).load(ALBUM_IMAGE_URLS[position]).placeholder(R.mipmap.ic_launcher).into(imageView);
        }


        @Override
        public void onClick(View v) {
            //添加启动动画
            Intent intent = new Intent(mContext, SecondActivity.class);
// Pass data object in the bundle and populate details activity.
            intent.putExtra("shared_element_transition_name", imageView.getTransitionName());
            ActivityOptionsCompat options = ActivityOptionsCompat.
                    makeSceneTransitionAnimation((Activity) mContext, imageView, imageView.getTransitionName());
            mContext.startActivity(intent, options.toBundle());
        }
    }
//
//        public void bind(int position) {
//            imageView.setTransitionName(ALBUM_NAMES[position]);
//            imageView.setTag(ALBUM_NAMES[position]);
//        }
//    }
}
