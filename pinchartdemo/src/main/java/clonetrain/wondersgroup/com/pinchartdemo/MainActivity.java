package clonetrain.wondersgroup.com.pinchartdemo;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.view.View;
import android.widget.Button;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.MPPointF;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private PieChart mChart;
//    protected Typeface mTfRegular;
//    protected Typeface mTfLight;
    private String[] texts= {"餐费","课外活动费","校服费","小学生就读费"};

    private List<String> percents = new ArrayList<>();
    private Object pieData;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mChart = (PieChart) findViewById(R.id.chart1);
        Button chartBtn = (Button) findViewById(R.id.line_chart_btn);

        chartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,LineChartActivity.class));
            }
        });

//        mTfLight = Typeface.createFromAsset(getAssets(), "OpenSans-Light.ttf");
//        mTfRegular = Typeface.createFromAsset(getAssets(), "OpenSans-Regular.ttf");

        mChart.setUsePercentValues(true);//百分比显示
        mChart.getDescription().setEnabled(false);//不显示描述信息
        mChart.setExtraOffsets(5, 10, 5, 5);

        mChart.setDragDecelerationFrictionCoef(0.95f);

//        mChart.setCenterTextTypeface(mTfLight);
//        mChart.setCenterText(generateCenterSpannableText());
        mChart.setCenterText("总消费\n" + generateCenterSpannableText());

        mChart.setDrawHoleEnabled(true);//中间园显示
        mChart.setHoleColor(Color.WHITE);//园颜色

        mChart.setTransparentCircleColor(Color.WHITE);//透明度颜色
        mChart.setTransparentCircleAlpha(110);//圆环透明度

        mChart.setHoleRadius(58f);//内部园半径
        mChart.setTransparentCircleRadius(61f);//内部园外面一点延伸的圆半径
        mChart.setCenterTextSize(18);//圆中间文字大小

        mChart.setDrawCenterText(true);//中间可以显示文本

        mChart.setRotationAngle(0);//开始旋转角度
        // enable rotation of the chart by touch
        mChart.setRotationEnabled(true);
        mChart.setHighlightPerTapEnabled(true);

        // mChart.setUnit(" €");
        // mChart.setDrawUnitsInChart(true);

        // add a selection listener
//        mChart.setOnChartValueSelectedListener(this);

        setData(4, 100);

        mChart.animateY(1400, Easing.EasingOption.EaseInOutQuad);
        // mChart.spin(2000, 0, 360);


        //默认显示在pinChart 下面 的不同内容块
        Legend l = mChart.getLegend();
        l.setEnabled(false);
//        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
//        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
//        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
//        l.setDrawInside(true);
//
//        l.setTextSize(13);
//        l.setXEntrySpace(7f);
//        l.setYEntrySpace(0f);
//        l.setYOffset(0f);
//
        // entry label styling
        mChart.setEntryLabelColor(Color.WHITE);
//        mChart.setEntryLabelTypeface(mTfRegular);
        mChart.setEntryLabelTextSize(12f);


    }




    private void setData(int count, float range) {

        ArrayList<PieEntry> entries = new ArrayList<PieEntry>();

        // NOTE: The order of the entries when being added to the entries array determines their position around the center of
        // the chart.
//        for (int i = 0; i < count; i++) {
////            entries.add(new PieEntry((float) ((Math.random() * mult) + mult / 5),
////                    mParties[i % mParties.length],
////                    getResources().getDrawable(R.mipmap.star)));
//            //如果第二个参数不为空 百分比会和text一起显示
////            entries.add(new PieEntry((float) ((Math.random() * mult) + mult / 5), "", getResources().getDrawable(R.mipmap.star)));
//            PieEntry pieEntry = new PieEntry(Float.valueOf(percents.get(i)), "");
//            pieEntry.setLabel(texts[i]);
//            entries.add(pieEntry);
//        }

        //设置扇形区域显示值
        entries.add(new PieEntry(30f,""));
        entries.add(new PieEntry(20f,""));
        entries.add(new PieEntry(10f,""));
        entries.add(new PieEntry(40f,""));


        PieDataSet dataSet = new PieDataSet(entries, "");

        dataSet.setDrawIcons(false);

        dataSet.setSliceSpace(3f);//每个扇形间隙
        dataSet.setIconsOffset(new MPPointF(0, 40));
        dataSet.setSelectionShift(5f);

        // add a lot of colors

        ArrayList<Integer> colors = new ArrayList<Integer>();

//        for (int c : ColorTemplate.MATERIAL_COLORS)
//            colors.add(c);
//
//        for (int c : ColorTemplate.JOYFUL_COLORS)
//            colors.add(c);
//
//        for (int c : ColorTemplate.COLORFUL_COLORS)
//            colors.add(c);
//
//        for (int c : ColorTemplate.LIBERTY_COLORS)
//            colors.add(c);
//
//        for (int c : ColorTemplate.PASTEL_COLORS)
//            colors.add(c);


        // 饼图颜色
        colors.add(ColorTemplate.MATERIAL_COLORS[0]);
        colors.add(ColorTemplate.MATERIAL_COLORS[1]);
        colors.add(ColorTemplate.MATERIAL_COLORS[2]);
        colors.add(ColorTemplate.MATERIAL_COLORS[3]);

//        colors.add(ColorTemplate.getHoloBlue());

        dataSet.setColors(colors);
        //dataSet.setSelectionShift(0f);

        PieData data = new PieData(dataSet);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(11f);//扇形文字
        data.setValueTextColor(Color.RED);//扇形文字颜色
//        data.setValueTypeface(mTfLight);
        mChart.setData(data);

        // undo all highlights
        mChart.highlightValues(null);

        mChart.invalidate();
    }


    private SpannableString generateCenterSpannableText() {

//        SpannableString s = new SpannableString("总消费\n3100");
//        s.setSpan(new RelativeSizeSpan(1.1f), 0, 6, 0);//字体大小
//        s.setSpan(new StyleSpan(Typeface.NORMAL), 0, s.length(), 0);
//        s.setSpan(new ForegroundColorSpan(Color.GRAY), 0, s.length(), 0);
//        s.setSpan(new RelativeSizeSpan(1.7f), 6, s.length() - 6, 0);
//        s.setSpan(new StyleSpan(Typeface.NORMAL), s.length() - 4, s.length(), 0);
//        s.setSpan(new ForegroundColorSpan(ColorTemplate.getHoloBlue()), s.length() - 4, s.length(), 0);

        SpannableString s = new SpannableString("3100");
        s.setSpan(new RelativeSizeSpan(1.1f), 0, s.length(), 0);//字体大小
        s.setSpan(new StyleSpan(Typeface.NORMAL), 0, s.length(), 0);//字体样式
//        s.setSpan(new ForegroundColorSpan(Color.RED), 0, s.length(), 0);
        s.setSpan(new ForegroundColorSpan(Color.MAGENTA), 0, s.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);  //设置前景色为洋红色

        return s ;
    }

    public Object getPieData() {
        return pieData;
    }
}
