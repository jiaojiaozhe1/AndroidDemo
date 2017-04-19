package clonetrain.wondersgroup.com.elementtransition;

import android.app.SharedElementCallback;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.TransitionManager;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.List;
import java.util.Map;

import static clonetrain.wondersgroup.com.elementtransition.MainActivity.EXTRA_CURRENT_ALBUM_POSITION;
import static clonetrain.wondersgroup.com.elementtransition.MainActivity.EXTRA_STARTING_ALBUM_POSITION;

public class SecondActivity extends AppCompatActivity {
    private static final String TAG = SecondActivity.class.getSimpleName();

    private static final boolean DEBUG = false;
    ImageView ambImg;

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);


//        toolbar = (Toolbar) findViewById(R.id.toolbar_second);
        ambImg = (ImageView) findViewById(R.id.image_element_detail);
       ImageView glidImg = (ImageView) findViewById(R.id.image_glid);

        String path = "http://www.topetrain.com.cn/img/data/c12.png";
        Glide.with(this).load(path).into(glidImg);

//        setSupportActionBar(toolbar);
//        if (getSupportActionBar() != null) {
//            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        }

        //上个页面 imageview 和当前页面展示的imageview 具有相同transitionname
        String transitionName = getIntent().getStringExtra("shared_element_transition_name");
        if (!TextUtils.isEmpty(transitionName)) {
            ViewCompat.setTransitionName(ambImg, transitionName);
        }
    }


    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                supportFinishAfterTransition();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
