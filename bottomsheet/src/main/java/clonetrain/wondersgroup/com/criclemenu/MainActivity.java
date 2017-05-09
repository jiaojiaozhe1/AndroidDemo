package clonetrain.wondersgroup.com.criclemenu;

import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView dialogFragment;
    TextView textView;
    TextView bottomSheets;
    Button button;
    BottomSheetBehavior mBottomSheetBehavior;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.text_view);
        bottomSheets = (TextView) findViewById(R.id.text_view_bottom_sheets);
        dialogFragment = (TextView) findViewById(R.id.text_view_dialog_fragment);
        button = (Button) findViewById(R.id.button_bottom_dialog);
        NestedScrollView scrollView = (NestedScrollView) findViewById(R.id.nested_scroll_view);
        //拿到这个tab_layout对应的BottomSheetBehavior
        mBottomSheetBehavior = BottomSheetBehavior.from(findViewById(R.id.tab_layout));


        textView.setOnClickListener(this);
        dialogFragment.setOnClickListener(this);
        bottomSheets.setOnClickListener(this);
        button.setOnClickListener(this);

//        添加滑动对底部tablayout 影藏和显示
        scrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {

                Log.e("onScrollChange","onScrollChange====");
// 控制展开还是关闭
                if (mBottomSheetBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {
                    mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);

                    mBottomSheetBehavior.setPeekHeight(150);
                } else if (mBottomSheetBehavior.getState() == BottomSheetBehavior.STATE_COLLAPSED) {
                    mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                    mBottomSheetBehavior.setPeekHeight(0);
                }
            }
        });


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
        } else if (view == bottomSheets) {

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

        } else if (view == button) {
//           /**
//            * The bottom sheet is dragging.
//                    */
//            public static final int STATE_DRAGGING = 1;
//
///**
// * The bottom sheet is settling.
// */
//            public static final int STATE_SETTLING = 2;
//
///**
// * The bottom sheet is expanded.
// */
//            public static final int STATE_EXPANDED = 3;
//
///**
// * The bottom sheet is collapsed.
// */
//            public static final int STATE_COLLAPSED = 4;
//
///**
// * The bottom sheet is hidden.
// */
//            public static final int STATE_HIDDEN = 5;

        }
    }
}
