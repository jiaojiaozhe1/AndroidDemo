package clonetrain.wondersgroup.com.elementtransition;

import android.app.Activity;
import android.app.ActivityOptions;
import android.app.SharedElementCallback;
import android.content.Intent;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.Explode;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Map;

import static clonetrain.wondersgroup.com.elementtransition.Constants.ALBUM_IMAGE_URLS;
import static clonetrain.wondersgroup.com.elementtransition.Constants.ALBUM_NAMES;

public class MainActivity extends Activity {
    static final String EXTRA_STARTING_ALBUM_POSITION = "extra_starting_item_position";
    static final String EXTRA_CURRENT_ALBUM_POSITION = "extra_current_item_position";

    private Bundle mTmpReenterState;
    ImageView imageView;
    ElementShareAdapter adapter;
    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // set an enter transition
//        getWindow().setEnterTransition(new Explode());
        setContentView(R.layout.activity_main);

//        imageView = (ImageView) findViewById(R.id.image_element);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this,2));

        adapter = new ElementShareAdapter(this);
        mRecyclerView.setAdapter(adapter);


//        imageView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
//// Pass data object in the bundle and populate details activity.
////                intent.putExtra(DetailsActivity.EXTRA_CONTACT, contact);
//                intent.putExtra("shared_element_transition_name",imageView.getTransitionName());
//                ActivityOptionsCompat options = ActivityOptionsCompat.
//                        makeSceneTransitionAnimation(MainActivity.this, imageView, imageView.getTransitionName());
//                startActivity(intent, options.toBundle());
//            }
//        });
    }


}
