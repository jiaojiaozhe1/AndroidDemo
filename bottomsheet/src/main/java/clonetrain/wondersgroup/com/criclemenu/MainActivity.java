package clonetrain.wondersgroup.com.criclemenu;

import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView dialogFragment;
    TextView textView;
    TextView bottomSheets;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.text_view);
        bottomSheets = (TextView) findViewById(R.id.text_view_bottom_sheets);
        dialogFragment = (TextView) findViewById(R.id.text_view_dialog_fragment);

        textView.setOnClickListener(this);
        dialogFragment.setOnClickListener(this);
        bottomSheets.setOnClickListener(this);



    }

    @Override
    public void onClick(View view) {
        if (view == dialogFragment) {
            new FullSheetDialogFragment().show(getSupportFragmentManager(), "dialog");
        } else if (view == textView) {
            BottomSheetDialog dialog = new BottomSheetDialog(MainActivity.this);
            View view1 = getLayoutInflater().inflate(R.layout.dialog_bottom_sheet, null);
            dialog.setContentView(view1);
            dialog.show();
        } else if (view == bottomSheets){

//            View bottomSheet = view2.findViewById(R.id.bottom_sheet);
//            BottomSheetBehavior behavior = BottomSheetBehavior.from(bottomSheet);
//            behavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
//                @Override
//                public void onStateChanged(@NonNull View bottomSheet, int newState) {
//                    //这里是bottomSheet状态的改变
//                }
//
//                @Override
//                public void onSlide(@NonNull View bottomSheet, float slideOffset) {
//                    //这里是拖拽中的回调，根据slideOffset可以做一些动画
//                }
//            });

        }
    }
}
