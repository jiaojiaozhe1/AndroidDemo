package clonetrain.wondersgroup.com.popupwindow;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View popupView4 = getLayoutInflater().inflate(R.layout.popupwindow1_top, null);

        final PopupWindow mPopupWindow4 = new PopupWindow(popupView4, ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT, true);
        mPopupWindow4.setTouchable(true);
        mPopupWindow4.setOutsideTouchable(true);
        mPopupWindow4.setBackgroundDrawable(new BitmapDrawable(getResources(), (Bitmap) null));
        mPopupWindow4.setAnimationStyle(R.style.PopupAnimationTop);
        TextView viewById = (TextView) findViewById(R.id.text_view_pop);
//                mButton4=(Button)findViewById(R.id.btn_test_popupwindow4);
        viewById.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPopupWindow4.showAsDropDown(v);

            }
        });



        View popupView1 = getLayoutInflater().inflate(R.layout.popupwindow1_top, null);

        final PopupWindow  mPopupWindow1 = new PopupWindow(popupView1, ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT, true);
        mPopupWindow1.setTouchable(true);
        mPopupWindow1.setOutsideTouchable(true);
        mPopupWindow1.setBackgroundDrawable(new BitmapDrawable(getResources(), (Bitmap) null));
        mPopupWindow1.setAnimationStyle(R.style.PopupAnimationTop);

        mPopupWindow1.getContentView().setFocusableInTouchMode(true);
        mPopupWindow1.getContentView().setFocusable(true);
        mPopupWindow1.getContentView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_MENU && event.getRepeatCount() == 0
                        && event.getAction() == KeyEvent.ACTION_DOWN) {
                    if (mPopupWindow1 != null && mPopupWindow1.isShowing()) {
                        mPopupWindow1.dismiss();
                    }
                    return true;
                }
                return false;
            }
        });
        Button  mButton1 = (Button) findViewById(R.id.btn_test_popupwindow1);
        mButton1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                mPopupWindow1.showAtLocation(findViewById(R.id.btn_test_popupwindow1), Gravity.BOTTOM, 0, 0);
            }
        });
    }


}
