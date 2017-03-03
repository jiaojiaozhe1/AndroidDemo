package app.demo.wondersgroup.com.materialdesigndemo.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.LinearLayout;

import app.demo.wondersgroup.com.materialdesigndemo.R;

/**
 * Created by zhangwentao on 16/10/31.
 * Description :
 * Version :
 */
public class EditTextView extends LinearLayout {
    public EditTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(context).inflate(R.layout.view_edit_text,this,true);
    }

    public EditTextView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public EditTextView(Context context) {
        this(context,null);
//
    }

}
