package clonetrain.wondersgroup.com.transitiondemo;

import android.graphics.Rect;
import android.support.v4.view.animation.FastOutLinearInInterpolator;
import android.support.v4.view.animation.LinearOutSlowInInterpolator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.transitionseverywhere.ArcMotion;
import com.transitionseverywhere.ChangeBounds;
import com.transitionseverywhere.ChangeImageTransform;
import com.transitionseverywhere.ChangeText;
import com.transitionseverywhere.Explode;
import com.transitionseverywhere.Fade;
import com.transitionseverywhere.Rotate;
import com.transitionseverywhere.Slide;
import com.transitionseverywhere.Transition;
import com.transitionseverywhere.TransitionManager;
import com.transitionseverywhere.TransitionSet;
import com.transitionseverywhere.extra.Scale;

public class MainActivity extends AppCompatActivity {
    Button button;
    Button button1;
    Button button2;
    Button button3;
    Button button4;
    ImageView imageView;
    ImageView imageView1;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ViewGroup transitionsContainer = (ViewGroup) findViewById(R.id.transitions_container);
        final TextView text = (TextView) findViewById(R.id.text);
        button = (Button) findViewById(R.id.button);
        imageView = (ImageView) findViewById(R.id.imageview);
        imageView1 = (ImageView) findViewById(R.id.imageview1);
        textView = (TextView) findViewById(R.id.text1);

        textView.setOnClickListener(new View.OnClickListener() {
            boolean isSecondText;
            @Override
            public void onClick(View view) {
                isSecondText = ! isSecondText;
                TransitionManager.beginDelayedTransition(transitionsContainer,
                        new ChangeText().setChangeBehavior(ChangeText.CHANGE_BEHAVIOR_OUT_IN));
                textView.setText(isSecondText ? "第一次显示" : "第二次显示拉拉拉拉");
            }
        });

        imageView1.setOnClickListener(new View.OnClickListener() {
            boolean isRotated;
            @Override
            public void onClick(View view) {
                isRotated = !isRotated;
                TransitionManager.beginDelayedTransition(transitionsContainer, new Rotate());
                imageView1.setRotation(isRotated ? 135 : 0);
            }
        });

        button1 = (Button) transitionsContainer.findViewById(R.id.button1);

        button1.setOnClickListener(new View.OnClickListener() {
            boolean isReturnAnimation;
            @Override
            public void onClick(View view) {
                isReturnAnimation = !isReturnAnimation;
                TransitionManager.beginDelayedTransition(transitionsContainer,
                        new ChangeBounds().setPathMotion(new ArcMotion()).setDuration(500));

                LinearLayout.LayoutParams params = (LinearLayout.LayoutParams)  button1.getLayoutParams();
                params.gravity = isReturnAnimation ? (Gravity.LEFT | Gravity.TOP) :
                        (Gravity.BOTTOM | Gravity.RIGHT);
                button1.setLayoutParams(params);
            }
        });
        button2 = (Button) transitionsContainer.findViewById(R.id.button2);

        button2.setOnClickListener(new View.OnClickListener() {
            boolean visible;
            @Override
            public void onClick(View view) {
                visible = !visible;
                TransitionSet set = new TransitionSet()
                        .addTransition(new Scale(0.7f))
                        .addTransition(new Fade())
                        .setInterpolator(visible ? new LinearOutSlowInInterpolator() :
                                new FastOutLinearInInterpolator());

                TransitionManager.beginDelayedTransition(transitionsContainer, set);
                text.setVisibility(visible ? View.VISIBLE : View.INVISIBLE);
            }
        });
//        button3 = (Button) transitionsContainer.findViewById(R.id.button3);
//        button4 = (Button) transitionsContainer.findViewById(R.id.button4);
//
//        button1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                // save rect of view in screen coordinates
//                final Rect viewRect = new Rect();
//                view.getGlobalVisibleRect(viewRect);
//
//                // create Explode transition with epicenter
//                Transition explode = new Explode()
//                        .setEpicenterCallback(new Transition.EpicenterCallback() {
//                            @Override
//                            public Rect onGetEpicenter(Transition transition) {
//                                return viewRect;
//                            }
//                        });
//                explode.setDuration(1000);
//                TransitionManager.beginDelayedTransition(transitionsContainer, explode);
//            }
//        });

        button.setOnClickListener(new View.OnClickListener() {
            boolean visible;

            @Override
            public void onClick(View v) {

                TransitionManager.beginDelayedTransition(transitionsContainer, new Slide(Gravity.RIGHT));
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//                    TransitionManager.beginDelayedTransition(transitionsContainer);
//                }
                visible = !visible;
                text.setVisibility(visible ? View.VISIBLE : View.GONE);
            }

        });


        imageView.setOnClickListener(new View.OnClickListener() {
            boolean expanded;
            @Override
            public void onClick(View view) {
                expanded = !expanded;
                TransitionManager.beginDelayedTransition(transitionsContainer, new TransitionSet()
                        .addTransition(new ChangeBounds())
                        .addTransition(new ChangeImageTransform()));

                ViewGroup.LayoutParams params = imageView.getLayoutParams();
                params.height = expanded ? ViewGroup.LayoutParams.MATCH_PARENT :
                        ViewGroup.LayoutParams.WRAP_CONTENT;
                imageView.setLayoutParams(params);

                imageView.setScaleType(expanded ? ImageView.ScaleType.CENTER_CROP :
                        ImageView.ScaleType.FIT_CENTER);
            }
        });

    }
}
