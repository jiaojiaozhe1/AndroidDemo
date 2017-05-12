package clonetrain.wondersgroup.com.calendardemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.jeek.calendar.widget.calendar.CalendarUtils;
import com.jeek.calendar.widget.calendar.OnCalendarClickListener;
import com.jeek.calendar.widget.calendar.month.MonthCalendarView;
import com.jeek.calendar.widget.calendar.month.MonthView;
import com.jeek.calendar.widget.calendar.schedule.ScheduleLayout;

import java.util.Calendar;

import static clonetrain.wondersgroup.com.calendardemo.R.id.slSchedule;

public class MainActivity extends AppCompatActivity {
    int weekRow = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ScheduleLayout scheduleLayout = (ScheduleLayout) findViewById(R.id.slSchedule);
        final TextView dateView = (TextView) findViewById(R.id.text_view_date_detail);


        int year = scheduleLayout.getCurrentSelectYear();
        int month = scheduleLayout.getCurrentSelectMonth() + 1;
        int day = scheduleLayout.getCurrentSelectDay();

        calculateWeek(day);

        dateView.setText(year + " 年 " + (month ) + " 月 " + (weekRow) + " 周");

        scheduleLayout.setOnCalendarClickListener(new OnCalendarClickListener() {
            @Override
            public void onClickDate(int year, int month, int day) {
                Log.e("onClickDate", "year==" + year + "month=" + (month + 1) + "day=" + day);
                //监听获得点击的年月日
                calculateWeek(day);

                dateView.setText(year + " 年 " + (month + 1) + " 月 " + (weekRow) + " 周");
            }

            @Override
            public void onPageChange(int year, int month, int day) {
            }
        });
    }

    private void calculateWeek(int day) {
        if (day <= 7) {
            weekRow = 1;
        } else if (day > 7 && day <= 14) {
            weekRow = 2;
        } else if (day > 14 && day <= 21) {
            weekRow = 3;
        } else if (day > 21 & day <= 28) {
            weekRow = 4;
        } else {
            weekRow = 5;
        }
    }
}
