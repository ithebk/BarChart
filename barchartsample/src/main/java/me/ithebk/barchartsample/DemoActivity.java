package me.ithebk.barchartsample;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import java.util.Random;

import me.ithebk.barchart.BarChart;
import me.ithebk.barchart.BarChartModel;

/**
 * Created by bharath on 16/10/17.
 */

public class DemoActivity extends AppCompatActivity {
    private BarChart barChart;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.language_progress);
        barChart = (BarChart) findViewById(R.id.language_progress_bar);

        String data[] = new String[]{"Java",
                "Python",
                "JavaScript",
                "C"};
        int dataColor[] = new int[]{
                Color.parseColor("#00BCD4"),
                Color.parseColor("#3F51B5"),
                Color.parseColor("#2196F3"),
                Color.parseColor("#FF9800")};
        barChart.setBarMaxValue(100);
        for (int i = 0; i < 4; i++) {
            BarChartModel barChartModel = new BarChartModel();
            barChartModel.setBarValue(new Random().nextInt(100));
            barChartModel.setBarColor(dataColor[i]);
            barChartModel.setBarTag(null);
            barChartModel.setBarText(data[i]);
            barChart.addBar(barChartModel);
        }

    }
}
