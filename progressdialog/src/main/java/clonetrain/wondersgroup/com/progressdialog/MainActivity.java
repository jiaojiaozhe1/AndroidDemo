package clonetrain.wondersgroup.com.progressdialog;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView viewById = (TextView) findViewById(R.id.text_view_dialog);
        viewById.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
// Set imageList
                List<Integer> imageList = new ArrayList<Integer>();
                imageList.add(R.mipmap.ic_launcher);
                imageList.add(R.mipmap.ic_launcher);


                FlipProgressDialog fpd = new FlipProgressDialog();

                fpd.setImageList(imageList);                              // *Set a imageList* [Have to. Transparent background png recommended]
                fpd.setCanceledOnTouchOutside(false);                      // If true, the dialog will be dismissed when user touch outside of the dialog. If false, the dialog won't be dismissed.
                fpd.setDimAmount(0.0f);                                   // Set a dim (How much dark outside of dialog)

// About dialog shape, color
                fpd.setBackgroundColor(Color.parseColor("#FF4081"));      // Set a background color of dialog
                fpd.setBackgroundAlpha(0.2f);                             // Set a alpha color of dialog
                fpd.setBorderStroke(0);                                   // Set a width of border stroke of dialog
                fpd.setBorderColor(-1);                                   // Set a border stroke color of dialog
                fpd.setCornerRadius(16);                                  // Set a corner radius

// About image
                fpd.setImageSize(100);                                    // Set an image size
                fpd.setImageMargin(10);                                   // Set a margin of image

// About rotation
                fpd.setOrientation("rotationY");                          // Set a flipping rotation
                fpd.setDuration(600);                                     // Set a duration time of flipping ratation
                fpd.setStartAngle(0.0f);                                  // Set an angle when flipping ratation start
                fpd.setEndAngle(180.0f);                                  // Set an angle when flipping ratation end
                fpd.setMinAlpha(0.0f);                                    // Set an alpha when flipping ratation start and end
                fpd.setMaxAlpha(1.0f);                                    // Set an alpha while image is flipping


                fpd.show(getSupportFragmentManager(), "");                        // Show flip-progress-dialg
//                fpd.dismiss();

            }
        });
    }
}
