package clonetrain.wondersgroup.com.swiperefreshlayoutheaddemo;

import android.content.Context;
import android.util.SparseArray;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.reginald.swiperefresh.CustomSwipeRefreshLayout;

import org.w3c.dom.Text;

/**
 * Created by zhangwentao on 16/10/31.
 * Description :
 * Version :
 */
public class MyCustomHeadView extends LinearLayout implements CustomSwipeRefreshLayout.CustomSwipeRefreshHeadLayout {
    private ViewGroup mContainer;
    private TextView textView;

    public MyCustomHeadView(Context context) {
        super(context);
        setupLayout();
    }

    private void setupLayout() {
        ViewGroup.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mContainer = (LinearLayout) LayoutInflater.from(getContext()).inflate(R.layout.my_swiperefresh_head_layout, null);
        textView = (TextView) mContainer.findViewById(R.id.textView);
        addView(mContainer, lp);
        setGravity(Gravity.TOP);


    }

    @Override
    public void onStateChange(CustomSwipeRefreshLayout.State state, CustomSwipeRefreshLayout.State lastState) {
        int stateCode = state.getRefreshState();
        int lastStateCode = lastState.getRefreshState();
        float percent = state.getPercent();

        switch (stateCode) {
            case CustomSwipeRefreshLayout.State.STATE_NORMAL:

                if (stateCode != lastStateCode) {
                    textView.setVisibility(View.VISIBLE);

                }
                break;
            case CustomSwipeRefreshLayout.State.STATE_READY:
                if (stateCode != lastStateCode) {
                    textView.setVisibility(View.VISIBLE);
                }
                break;
            case CustomSwipeRefreshLayout.State.STATE_REFRESHING:
                if (stateCode != lastStateCode) {
                    textView.setVisibility(View.VISIBLE);
                }
                break;

            case CustomSwipeRefreshLayout.State.STATE_COMPLETE:
                if (stateCode != lastStateCode) {
                    textView.setVisibility(View.VISIBLE);
                }
                break;
            default:
        }
    }

}