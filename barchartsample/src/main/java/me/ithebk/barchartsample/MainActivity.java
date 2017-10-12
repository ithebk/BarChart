package me.ithebk.barchartsample;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.Random;

import me.ithebk.barchart.BarChart;
import me.ithebk.barchart.BarChartModel;

public class MainActivity extends AppCompatActivity {
    private BarChart barChartVertical;
    private BarChart barChartHorizontal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        barChartVertical = (BarChart) findViewById(R.id.bar_chart_vertical);
        barChartHorizontal = (BarChart) findViewById(R.id.bar_chart_horizontal);
        for (int i = 0; i < 3; i++) {
            BarChartModel barChartModel = new BarChartModel();
            barChartModel.setBarValue(new Random().nextInt(100));
            Random rnd = new Random();
            barChartModel.setBarColor(Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256)));
            barChartModel.setBarTag(null);
            barChartVertical.addBar(barChartModel);
        }

        for (int i = 0; i < 3; i++) {
            BarChartModel barChartModel = new BarChartModel();
            barChartModel.setBarValue(new Random().nextInt(100));
            Random rnd = new Random();
            barChartModel.setBarColor(Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256)));
            barChartModel.setBarTag(null);
            barChartHorizontal.addBar(barChartModel);
        }


        findViewById(R.id.tv_hello).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("Clicked::");
                BarChartModel barChartModel = new BarChartModel();
                barChartModel.setBarValue(new Random().nextInt(100));
                Random rnd = new Random();
                barChartModel.setBarColor(Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256)));
                barChartModel.setBarTag(null);
                barChartVertical.addBar(barChartModel);
                barChartHorizontal.addBar(barChartModel);
            }
        });

        barChartVertical.setOnBarClickListener(new BarChart.OnBarClickListener() {
            @Override
            public void onBarClick(BarChartModel barChartModel) {
                barChartVertical.removeBar(barChartModel);
            }
        });
        barChartHorizontal.setOnBarClickListener(new BarChart.OnBarClickListener() {
            @Override
            public void onBarClick(BarChartModel barChartModel) {
                barChartHorizontal.removeBar(barChartModel);
            }
        });
    }
}
