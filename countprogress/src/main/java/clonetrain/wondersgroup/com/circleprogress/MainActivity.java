package clonetrain.wondersgroup.com.circleprogress;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


/**
 * 圆形进度
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       DonutProgress dontPro = (DonutProgress) findViewById(R.id.donut_progress_course);
        dontPro.setText(75 + "%");
    }
}
