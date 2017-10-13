package me.ithebk.barchartsample;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.Random;

import me.ithebk.barchart.BarChart;
import me.ithebk.barchart.BarChartModel;

public class MainActivity extends AppCompatActivity {
    private BarChart barChartVertical;
    private BarChart barChartHorizontal;
    private EditText etAddViewAt;
    private EditText etRemoveViewAt;
    private EditText etUpdateViewAt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        barChartVertical = (BarChart) findViewById(R.id.bar_chart_vertical);
        barChartHorizontal = (BarChart) findViewById(R.id.bar_chart_horizontal);
        etAddViewAt = (EditText) findViewById(R.id.et_add_view_at);
        etRemoveViewAt = (EditText) findViewById(R.id.et_remove_bar_at);
        etUpdateViewAt = (EditText) findViewById(R.id.et_update_bar_at);
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

        findViewById(R.id.tv_hello_at).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("Clicked::");
                BarChartModel barChartModel = new BarChartModel();
                barChartModel.setBarValue(new Random().nextInt(100));
                Random rnd = new Random();
                barChartModel.setBarColor(Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256)));
                barChartModel.setBarTag(null);
                barChartVertical.addBar(Integer.parseInt(etAddViewAt.getText().toString()),barChartModel);
                barChartHorizontal.addBar(Integer.parseInt(etAddViewAt.getText().toString()),barChartModel);
            }
        });
        findViewById(R.id.tv_clear).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        barChartVertical.clearAll();
                        barChartHorizontal.clearAll();
                    }
                });

        findViewById(R.id.tv_remove_bar_at).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                barChartVertical.removeBar(Integer.parseInt(etRemoveViewAt.getText().toString()));
                barChartHorizontal.removeBar(Integer.parseInt(etRemoveViewAt.getText().toString()));
            }});

        findViewById(R.id.tv_update_bar_at).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BarChartModel barChartModel = new BarChartModel();
                barChartModel.setBarValue(new Random().nextInt(100));
                Random rnd = new Random();
                barChartModel.setBarColor(Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256)));
                barChartModel.setBarTag(null);
                barChartVertical.updateBar(Integer.parseInt(etUpdateViewAt.getText().toString()),barChartModel);
                barChartHorizontal.updateBar(Integer.parseInt(etUpdateViewAt.getText().toString()),barChartModel);
            }});

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
//        int a[] =new int[]{-20,-5,2,4,5,1,-1};
//        //System.out.println("Minimum of "+ Arrays.toString(a)+":Is:"+solution(a));

    }


    private int solution(int a[]){
        int max=1;
        for (int anA1 : a) {
            if (anA1 > max) {
                max = anA1;
            }
        }
        boolean valArray[] = new boolean[max+1];
        for (int anA : a) {
            if(anA>0) {
                valArray[anA] = true;
            }
        }
        for(int i=1;i<valArray.length;i++){
            if(!valArray[i]){
                return i;
            }
        }
        return max;
    }
}
