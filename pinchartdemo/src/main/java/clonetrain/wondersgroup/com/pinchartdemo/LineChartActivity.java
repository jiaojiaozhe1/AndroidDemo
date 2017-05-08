package clonetrain.wondersgroup.com.pinchartdemo;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

public class LineChartActivity extends AppCompatActivity {
    LineChart mChart;
    String[] labs = {"", "1月", "2月", "3月", "4月", "5月"};
    List<String> xValues = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_line_chart);


        mChart = (LineChart) findViewById(R.id.line_chart);


        xValues.add("1");
        xValues.add("2");
        xValues.add("3");
        xValues.add("4");
        xValues.add("5");


//        initView();
        drawTheChartByMPAndroid();
//
    }

    private void drawTheChartByMPAndroid() {
        LineData lineData = getLineData(5, 40);
        showChart(mChart, lineData, Color.rgb(137, 230, 81));
    }

    private LineData getLineData(int count, float range) {
//        ArrayList xValues = new ArrayList();

//        for (int i = 0; i < count; i++) {
//            // x轴显示的数据，这里默认使用数字下标显示
//            xValues.add();
//        }

        // y轴的数据
        ArrayList yValues = new ArrayList();
        for (int i = 0; i < count; i++) {
            float value = (int) (Math.random() * range);
            yValues.add(new Entry(i, value));
//            yValues.add(new Entry(Float.valueOf(xValues.get(i)), value));
        }
        // create a dataset and give it a type
        // y轴的数据集合
        LineDataSet lineDataSet = new LineDataSet(yValues, "访问量统计");

        lineDataSet.setColor(Color.RED);
        lineDataSet.setCircleColor(Color.WHITE);
        lineDataSet.setLineWidth(2f);
        lineDataSet.setCircleRadius(3f);
        lineDataSet.setFillAlpha(65);
        lineDataSet.setFillColor(ColorTemplate.colorWithAlpha(Color.YELLOW, 200));
        lineDataSet.setDrawCircleHole(false);
        lineDataSet.setHighLightColor(Color.rgb(244, 117, 117));
        lineDataSet.setDrawFilled(true);//折线下面颜色
        lineDataSet.setFillColor(Color.GRAY);


        ArrayList lineDataSets = new ArrayList();
        lineDataSets.add(lineDataSet); // 添加数据集合

        //创建lineData
//        LineData lineData = new LineData(xValues, lineDataSets);
        LineData lineData = new LineData(lineDataSets);
        return lineData;
    }


    private void showChart(LineChart lineChart, LineData lineData, int color) {
        lineChart.setDrawBorders(false); //在折线图上添加边框
        lineChart.setDescription(null); //数据描述
//        lineChart.setNoDataTextDescription("You need to provide data for the chart.");

        lineChart.setDrawGridBackground(false); //表格颜色
        lineChart.setGridBackgroundColor(Color.WHITE & 0x70FFFFFF); //表格的颜色，设置一个透明度

        lineChart.setTouchEnabled(false); //可点击

        lineChart.setDragEnabled(false);  //可拖拽
        lineChart.setScaleEnabled(false);  //可缩放

        lineChart.setPinchZoom(false);

//        lineChart.setBackgroundColor(color); //设置背景颜色

        lineChart.setData(lineData);  //填充数据

        lineChart.getLegend().setEnabled(false); //设置标示，就是那个一组y的value的

//        mLegend.setForm(Legend.LegendForm.CIRCLE); //样式
//        mLegend.setFormSize(6f); //字体
//        mLegend.setTextColor(Color.WHITE); //颜色

        mChart.getAxisRight().setEnabled(false);
        mChart.getAxisLeft().setEnabled(false);

        lineChart.setVisibleXRange(1, 7);   //x轴可显示的坐标范围
        XAxis xAxis = lineChart.getXAxis();  //x轴的标示
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM); //x轴位置
        xAxis.setTextColor(Color.RED);    //字体的颜色
        xAxis.setTextSize(10f); //字体大小
        xAxis.setGridColor(Color.WHITE);//网格线颜色
        xAxis.setDrawGridLines(false); //不显示网格线
        xAxis.setDrawAxisLine(false);
//        xAxis.setTypeface(mTf);

        YAxis axisLeft = lineChart.getAxisLeft(); //y轴左边标示
        YAxis axisRight = lineChart.getAxisRight(); //y轴右边标示
        axisLeft.setTextColor(Color.WHITE); //字体颜色
        axisLeft.setTextSize(10f); //字体大小
        axisLeft.setAxisMaximum(120f);
        axisLeft.setAxisMinimum(0f);
        axisLeft.setLabelCount(6, true); //显示格数
        axisLeft.setGridColor(Color.WHITE); //网格线颜色
//        axisLeft.setTypeface(mTf);

        axisRight.setDrawAxisLine(false);
        axisRight.setDrawGridLines(false);
        axisRight.setDrawLabels(false);

        lineChart.animateX(2500);  //立即执行动画
    }


    private void initView() {
        mChart.setTouchEnabled(false);

        mChart.setDragDecelerationFrictionCoef(0.9f);
        mChart.setDescription(null);
        mChart.setNoDataText("You need to provide data for the chart.");

        mChart.setBorderColor(Color.WHITE & 0xffFFFFFF);
        mChart.setPinchZoom(true);

        // enable scaling and dragging
        mChart.setDragEnabled(false);
        mChart.setScaleEnabled(false);
        mChart.setDrawGridBackground(false);
        mChart.setHighlightPerDragEnabled(true);
        mChart.setGridBackgroundColor(Color.WHITE & 0xffFFFFFF); // 表格的的颜色，在这里是是给颜色设置一个透明度

        mChart.getAxisRight().setEnabled(false);
        mChart.getAxisLeft().setEnabled(false);
        // if disabled, scaling can be done on x- and y-axis separately
        mChart.setPinchZoom(true);
//        mChart.setExtraLeftOffset(-30);

        // set an alternative background color
        mChart.setBackgroundColor(Color.WHITE);

        // add data
        setData(20, 30);

        mChart.animateX(2000);

        // get the legend (only possible after setting data)
        mChart.getLegend().setEnabled(false);
//

//        // modify the legend ...
//        l.setForm(Legend.LegendForm.LINE);
////        l.setTypeface(mTfLight);
//        l.setTextSize(11f);
//        l.setTextColor(Color.WHITE);
//        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
//        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
//        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
//        l.setDrawInside(false);
//        l.setYOffset(11f);

        XAxis xAxis = mChart.getXAxis();
        xAxis.setEnabled(true);
//        xAxis.setTypeface(mTfLight);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTextSize(18f);
        xAxis.setTextColor(Color.RED);
        xAxis.setDrawLabels(true);
        // 设置x轴数据偏移量
//        xAxis.setXOffset(-50f);
        xAxis.setDrawGridLines(false);
        xAxis.setDrawAxisLine(false);
//        xAxis.setSpaceMin(0.7f);
        xAxis.setSpaceMin(1f);

        xAxis.setAvoidFirstLastClipping(true);
//        xAxis.setLabelCount(5);
//        xAxis.setValueFormatter(new IAxisValueFormatter() {
//            @Override
//            public String getFormattedValue(float value, AxisBase axis) {
////                for (String lab :labs){
////                    return lab;
////                }
//                return labs[(int)value];
//            }
//
//        });

        YAxis leftAxis = mChart.getAxisLeft();
//        leftAxis.setTypeface(mTfLight);
        leftAxis.setTextColor(ColorTemplate.getHoloBlue());
        leftAxis.setAxisMaximum(120f);
        leftAxis.setAxisMinimum(0f);
        leftAxis.setDrawGridLines(false);
        leftAxis.setGranularityEnabled(true);
        leftAxis.setDrawZeroLine(false);
        leftAxis.setLabelCount(10);

        YAxis rightAxis = mChart.getAxisRight();
//        rightAxis.setTypeface(mTfLight);
//        rightAxis.setTextColor(Color.RED);
        rightAxis.setAxisMaximum(80);
        rightAxis.setAxisMinimum(0f);
        rightAxis.setDrawGridLines(false);
        rightAxis.setDrawZeroLine(false);
        rightAxis.setGranularityEnabled(false);
    }

    private void setData(int count, float range) {

        ArrayList<Entry> yVals3 = new ArrayList<Entry>();

        for (int i = 0; i < 5; i++) {
            float mult = range;
            float val = (float) (Math.random() * mult);
            yVals3.add(new Entry(i, val));
        }


        LineDataSet set3;
//        set3.setValues(yVals3);

        if (mChart.getData() != null && mChart.getData().getDataSetCount() > 0) {
            set3 = (LineDataSet) mChart.getData().getDataSetByIndex(0);
            set3.setValues(yVals3);
            mChart.getData().notifyDataChanged();
            mChart.notifyDataSetChanged();
        } else {
            set3 = new LineDataSet(yVals3, "");
            set3.setAxisDependency(YAxis.AxisDependency.LEFT);
            set3.setColor(Color.YELLOW);
            set3.setCircleColor(Color.RED);
            set3.setLineWidth(2f);
            set3.setCircleRadius(3f);
            set3.setFillAlpha(65);
            set3.setFillColor(ColorTemplate.colorWithAlpha(Color.YELLOW, 200));
            set3.setDrawCircleHole(false);
            set3.setHighLightColor(Color.rgb(244, 117, 117));
            set3.setDrawFilled(true);//折线下面颜色
            set3.setFillColor(Color.GRAY);

            // create a data object with the datasets
            LineData data = new LineData(set3);
            data.setValueTextColor(Color.RED);
            data.setValueTextSize(9f);

            // set data
            mChart.setData(data);
        }
    }


}
