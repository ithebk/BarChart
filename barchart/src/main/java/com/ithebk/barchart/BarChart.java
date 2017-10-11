package com.ithebk.barchart;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Build;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.Random;

/**
 * Created by bharath on 9/10/17.
 */

public class BarChart extends FrameLayout {
    private Context context;
    private int orientation;

    public BarChart(Context context) {
        super(context);
        init(context);
    }

    public BarChart(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.BarChart, 0, 0);
        System.out.println(a.getIndexCount());
        orientation = a.getInt(R.styleable.BarChart_orientation,BarChartUtils.BAR_CHART_VERTICAL);
        a.recycle();
        init(context);
    }

    /**
     *
     * @param context
     */
    private void init(Context context){
        if(orientation == BarChartUtils.BAR_CHART_HORIZONTAL){
            createHorizontalChart();
        }
        else {
            createVerticalChart();
        }
    }

    private void createHorizontalChart() {
            final ScrollView verticalScrollView = new ScrollView(context);
            verticalScrollView.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT));
            final LinearLayout linearLayoutParent = new LinearLayout(context);
            linearLayoutParent.setOrientation(LinearLayout.VERTICAL);
            linearLayoutParent.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT));
            linearLayoutParent.setBackgroundColor(Color.parseColor("#EEEEEE"));
            linearLayoutParent.setGravity(Gravity.LEFT|Gravity.START);
            getDimension(false, verticalScrollView, new DimensionReceivedCallback() {
                @Override
                public void onDimensionReceived(int dimension) {
                    System.out.println("Max Height:"+dimension);
                    if(dimension>0) {
                        for (int i = 0; i < 10; i++) {
                            int width = new Random().nextInt(dimension);
                            System.out.println("width::" + width);
                            LinearLayout linearLayout = new LinearLayout(context);
                            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(width,
                                    100);
                            if (i > 0) {
                                layoutParams.topMargin = 40;
                            }

                            linearLayout.setBackgroundColor(Color.BLACK);
                            linearLayout.setLayoutParams(layoutParams);
                            linearLayoutParent.addView(linearLayout);
                        }
                    }
                }
            });



            verticalScrollView.addView(linearLayoutParent);
            this.addView(verticalScrollView);


    }

    private void createVerticalChart(){
        final LinearLayout linearLayoutParent = new LinearLayout(context);
        linearLayoutParent.setOrientation(LinearLayout.HORIZONTAL);
        linearLayoutParent.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
     /*   linearLayoutParent.setBackgroundColor(Color.parseColor("#EEEEEE"));*/
        linearLayoutParent.setGravity(Gravity.BOTTOM);
        getDimension(true, linearLayoutParent, new DimensionReceivedCallback() {
            @Override
            public void onDimensionReceived(int dimension) {
                if(dimension==0){
                    dimension=200;
                }
                System.out.println("Max Height:"+dimension);
                for(int i=0;i<11;i++){
                    int height = new Random().nextInt(dimension);
                    System.out.println("height::"+height);

                    Random rnd = new Random();
                    int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
                    View view = LayoutInflater.from(context).inflate(R.layout.bar,linearLayoutParent,false);
                    view.findViewById(R.id.linear_bar).setBackgroundColor(color);
                    TextView textView = view.findViewById(R.id.tv_bar);
                    textView.setText(height+"");
                    MarginLayoutParams layoutParamsBar = (MarginLayoutParams) view.getLayoutParams();
                    if(i>0) {
                        layoutParamsBar.leftMargin = 40;
                    }
                   final LinearLayout linearLayoutBar = view.findViewById(R.id.linear_bar);


                    ValueAnimator anim = ValueAnimator.ofInt(0,height);

                    anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                        @Override
                        public void onAnimationUpdate(ValueAnimator valueAnimator) {
                            int val = (Integer) valueAnimator.getAnimatedValue();
                            ViewGroup.LayoutParams layoutParams = linearLayoutBar.getLayoutParams();
                            layoutParams.height = val;
                            linearLayoutBar.setLayoutParams(layoutParams);
                        }

                    });
                    anim.setDuration(500);
                    anim.start();

                    linearLayoutParent.addView(view);
                }
            }
        });


        this.addView(linearLayoutParent);

    }

    private void getDimension(final boolean isHeightRequested, final View view, final DimensionReceivedCallback listener){
        view.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    view.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                } else {
                    view.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                }

                if(isHeightRequested){
                    listener.onDimensionReceived(view.getHeight());
                }
                else {
                    listener.onDimensionReceived(view.getWidth());
                }
            }
        });
    }

    private interface DimensionReceivedCallback{
        void onDimensionReceived(int dimension);
    }
}
