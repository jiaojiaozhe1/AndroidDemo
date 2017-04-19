package clonetrain.wondersgroup.com.circleprogress;

import android.animation.ObjectAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.timqi.sectorprogressview.ColorfulRingProgressView;


/**
 * 圆形进度
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       DonutProgress dontPro = (DonutProgress) findViewById(R.id.donut_progress_course);
        ColorfulRingProgressView crpv = (ColorfulRingProgressView) findViewById(R.id.crpv);
        dontPro.setText(75 + "%");

        crpv.setPercent(75);

        //进度动画
        ObjectAnimator anim = ObjectAnimator.ofFloat(crpv, "percent",
                0, (crpv).getPercent());
        anim.setInterpolator(new LinearInterpolator());
        anim.setDuration(1000);
        anim.start();



    }
}
