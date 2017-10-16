package me.ithebk.barchart;

import android.animation.LayoutTransition;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


/**
 * Created by bharath on 9/10/17.
 */

public class BarChart extends FrameLayout {
    private int barSpaces;
    private int barTextColor;
    private Context context;
    private int barType;
    private int barDimension;
    private int barTextSize;
    private int barColor;
    private boolean showAutoColorBar;
    private int barMaxValue;
    private LinearLayout verticalLinearParent, horizontalLinearParent;
    private boolean isBarAdded = false;
    private boolean isShowBarValue = true;
    private boolean isShowAnimation = true;
    private OnBarClickListener onBarClickListener;

    private List<BarChartModel> barChartModels = new ArrayList<>();

    public BarChart(Context context) {
        super(context);
        init();
    }

    public BarChart(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.BarChart, 0, 0);
        barType = a.getInt(R.styleable.BarChart_bar_type, BarChartUtils.BAR_CHART_VERTICAL);
        barDimension = a.getDimensionPixelSize(R.styleable.BarChart_bar_width,
                (int) BarChartUtils.convertDpToPixel(20, context));
        barColor = a.getColor(R.styleable.BarChart_bar_color, BarChartUtils.BAR_CHART_COLOR_DEFAULT);
        barTextSize = a.getDimensionPixelSize(R.styleable.BarChart_bar_text_size,
                (int) BarChartUtils.convertDpToPixel(13, context));
        barTextSize = (int) BarChartUtils.convertPixelsToDp(barTextSize, context);
        barTextColor = a.getColor(R.styleable.BarChart_bar_text_color, BarChartUtils.BAR_CHART_TEXT_COLOR_DEFAULT);

        showAutoColorBar = a.getBoolean(R.styleable.BarChart_bar_show_auto_color, false);

        barMaxValue = a.getInt(R.styleable.BarChart_bar_max_value, 0);

        barSpaces = a.getDimensionPixelSize(R.styleable.BarChart_bar_spaces,
                (int) BarChartUtils.convertDpToPixel(BarChartUtils.BAR_CHART_SPACE, context));

        isShowBarValue = a.getBoolean(R.styleable.BarChart_bar_show_value, true);
        isShowAnimation = a.getBoolean(R.styleable.BarChart_bar_show_animation, true);


        a.recycle();
        init();
    }

    public List<BarChartModel> getBar() {
        return barChartModels;
    }

    public BarChartModel getBar(int index){
        if(index<barChartModels.size()){
            return barChartModels.get(index);
        }
        return null;
    }

    /**
     *
     *
     */
    private void init() {
        if (barType == BarChartUtils.BAR_CHART_HORIZONTAL) {
            initHorizontalChart();
        } else {
            initVerticalChart();

        }
    }

    private void initHorizontalChart() {
        horizontalLinearParent = new LinearLayout(context);
        horizontalLinearParent.setOrientation(LinearLayout.VERTICAL);
        horizontalLinearParent.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        horizontalLinearParent.setGravity(Gravity.LEFT | Gravity.START);
        if (isShowAnimation) {
            horizontalLinearParent.setLayoutTransition(new LayoutTransition());
        }
        this.addView(horizontalLinearParent);
    }

    private void initVerticalChart() {
        verticalLinearParent = new LinearLayout(context);
        verticalLinearParent.setOrientation(LinearLayout.HORIZONTAL);
        verticalLinearParent.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        verticalLinearParent.setGravity(Gravity.BOTTOM);
        if (isShowAnimation) {
            verticalLinearParent.setLayoutTransition(new LayoutTransition());
        }
        this.addView(verticalLinearParent);
    }

    /**
     * @param position
     * @param dimension
     * @param barChartModel
     */
    private void createHorizontalChart(int position, int dimension, final BarChartModel barChartModel) {
        if (dimension == 0 || barMaxValue == 0) {
            return;
        }
        View view = LayoutInflater.from(context).inflate(R.layout.bar_horizontal, horizontalLinearParent, false);
        updateUi(position, dimension, null, barChartModel, view);

    }

    /**
     * @param position
     * @param dimension
     * @param barChartModel
     */
    private void createVerticalChart(int position, int dimension, final BarChartModel barChartModel) {
        if (dimension == 0 || barMaxValue == 0) {
            return;
        }
        View view = LayoutInflater.from(context).inflate(R.layout.bar_vertical, verticalLinearParent, false);
        updateUi(position, dimension, null, barChartModel, view);
    }

    /**
     * @param position
     * @param dimension
     * @param barChartModelInit
     * @param barChartModel
     * @param view
     */
    private void updateUi(int position, int dimension, BarChartModel barChartModelInit,
                          final BarChartModel barChartModel,
                          View view) {
        if (barChartModel.getBarColor() != 0) {
            view.findViewById(R.id.linear_bar).setBackgroundColor(barChartModel.getBarColor());
        } else if (showAutoColorBar) {
            view.findViewById(R.id.linear_bar).setBackgroundColor(BarChartUtils.getRandomColor());
        } else {
            view.findViewById(R.id.linear_bar).setBackgroundColor(barColor);
        }
        int dimensionBar = dimension * barChartModel.getBarValue() / barMaxValue;

        MarginLayoutParams layoutParamsBar = (MarginLayoutParams) view.getLayoutParams();


        if (isShowBarValue) {
            TextView textView = view.findViewById(R.id.tv_bar);
            if(barChartModel.getBarText()!=null){
                textView.setText(String.format(Locale.getDefault(), "%s", barChartModel.getBarText()));

            }
            else {
                textView.setText(String.format(Locale.getDefault(), "%d", barChartModel.getBarValue()));
            }
            textView.setTextSize(barTextSize);
            textView.setTextColor(barTextColor);
        } else {
            view.findViewById(R.id.tv_bar).setVisibility(GONE);
        }


        final LinearLayout linearLayoutBar = view.findViewById(R.id.linear_bar);
        ValueAnimator anim = ValueAnimator.ofInt(barChartModelInit == null ?
                0 : dimension * barChartModelInit.getBarValue() / barMaxValue, dimensionBar);
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int val = (Integer) valueAnimator.getAnimatedValue();
                ViewGroup.LayoutParams layoutParams = linearLayoutBar.getLayoutParams();
                if (barType == BarChartUtils.BAR_CHART_VERTICAL) {
                    layoutParams.height = val;
                } else {
                    layoutParams.width = val;
                }
                linearLayoutBar.setLayoutParams(layoutParams);
            }

        });
        if (isShowAnimation) {
            anim.setDuration(500);
        } else {
            anim.setDuration(0);
        }
        anim.start();
        view.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onBarClickListener != null) {
                    onBarClickListener.onBarClick(barChartModel);
                }
            }
        });
        view.setTag(barChartModel);
        if (barType == BarChartUtils.BAR_CHART_VERTICAL) {
            view.getLayoutParams().width = barDimension;
            if (barChartModelInit == null) {
                if (isBarAdded) {
                    layoutParamsBar.leftMargin = barSpaces;
                }
                if (position == -1) {
                    verticalLinearParent.addView(view);
                } else if (position <= verticalLinearParent.getChildCount()) {
                    verticalLinearParent.addView(view, position);
                }
            }
        } else {
            view.getLayoutParams().height = barDimension;
            if (barChartModelInit == null) {
                if (isBarAdded) {
                    layoutParamsBar.topMargin = barSpaces;
                }
                if (position == -1) {
                    horizontalLinearParent.addView(view);
                } else if (position <= horizontalLinearParent.getChildCount()) {
                    horizontalLinearParent.addView(view, position);
                }
            }


        }
        isBarAdded = true;
    }



    /**
     * @param dimension
     * @param barChartModelInit
     * @param barChartModel
     */
    private void updateHorizontalChart(int dimension, BarChartModel barChartModelInit,
                                       final BarChartModel barChartModel) {
        View view = horizontalLinearParent.findViewWithTag(barChartModelInit);
        updateUi(-1, dimension, barChartModelInit, barChartModel, view);
    }

    /**
     * @param dimension
     * @param barChartModelInit
     * @param barChartModel
     */
    private void updateVerticalChart(int dimension, BarChartModel barChartModelInit, final BarChartModel barChartModel) {
        View view = verticalLinearParent.findViewWithTag(barChartModelInit);
        updateUi(-1, dimension, barChartModelInit, barChartModel, view);
    }


    /**
     * @param barChartModel
     */
    private void removeBarInternal(BarChartModel barChartModel) {
        if (barType == BarChartUtils.BAR_CHART_HORIZONTAL) {
            horizontalLinearParent.removeView(horizontalLinearParent.findViewWithTag(barChartModel));
        } else {
            verticalLinearParent.removeView(verticalLinearParent.findViewWithTag(barChartModel));

        }
    }

    /**
     * @param isHeightRequested
     * @param view
     * @param listener
     */
    private void getDimension(final boolean isHeightRequested, final View view, final DimensionReceivedCallback listener) {
        view.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                System.out.println("Got the dimesion request onGlobalLayout");
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    view.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                } else {
                    view.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                }

                if (isHeightRequested) {
                    listener.onDimensionReceived(view.getHeight());
                } else {
                    listener.onDimensionReceived(view.getWidth());
                }
            }
        });
    }


    /**
     * @param onBarClickListener
     */
    public void setOnBarClickListener(OnBarClickListener onBarClickListener) {
        this.onBarClickListener = onBarClickListener;
    }


    /**
     * @param barChartModelList
     */
    public void addBar(List<BarChartModel> barChartModelList) {
        for (BarChartModel barChartModel : barChartModelList) {
            if (barChartModel != null) {
                addBar(barChartModels.size(), barChartModel);
            }
        }
    }

    /**
     * @param barChartModel
     */
    public void addBar(final BarChartModel barChartModel) {
        addBar(barChartModels.size(), barChartModel);
    }

    /**
     * @param position
     * @param barChartModel
     */
    public void addBar(final int position, final BarChartModel barChartModel) {
        if (position > barChartModels.size()) {
            return;
        }
        barChartModels.add(position, barChartModel);
        if (barChartModel != null) {
            if (barType == BarChartUtils.BAR_CHART_HORIZONTAL) {
                //Horizontal bar
                if (horizontalLinearParent.getHeight() == 0) {
                    getDimension(false, horizontalLinearParent, new DimensionReceivedCallback() {
                        @Override
                        public void onDimensionReceived(int dimension) {
                            createHorizontalChart(position, dimension, barChartModel);
                        }
                    });
                } else {
                    createHorizontalChart(position, horizontalLinearParent.getWidth(), barChartModel);
                }
            } else {

                if (verticalLinearParent.getHeight() == 0) {
                    getDimension(true, verticalLinearParent, new DimensionReceivedCallback() {
                        @Override
                        public void onDimensionReceived(int dimension) {
                            createVerticalChart(position, dimension, barChartModel);
                        }
                    });
                } else {
                    createVerticalChart(position, verticalLinearParent.getHeight(), barChartModel);
                }
            }

        }
    }

    /**
     * @param index
     * @param barChartModel
     */
    public void updateBar(final int index, final BarChartModel barChartModel) {
        if (index >= barChartModels.size() || barChartModel == null) {
            return;
        }
        final BarChartModel barChartModelInit = barChartModels.get(index);
        barChartModels.set(index, barChartModel);

        if (barType == BarChartUtils.BAR_CHART_HORIZONTAL) {
            //Horizontal bar
            if (horizontalLinearParent.getHeight() == 0) {
                getDimension(false, horizontalLinearParent, new DimensionReceivedCallback() {
                    @Override
                    public void onDimensionReceived(int dimension) {
                        updateHorizontalChart(dimension, barChartModelInit, barChartModel);
                    }
                });
            } else {
                updateHorizontalChart(horizontalLinearParent.getWidth(), barChartModelInit, barChartModel);
            }
        } else {

            if (verticalLinearParent.getHeight() == 0) {
                getDimension(true, verticalLinearParent, new DimensionReceivedCallback() {
                    @Override
                    public void onDimensionReceived(int dimension) {
                        updateVerticalChart(dimension, barChartModelInit, barChartModel);
                    }
                });
            } else {
                updateVerticalChart(verticalLinearParent.getHeight(), barChartModelInit, barChartModel);
            }
        }

    }

    /**
     * @param barChartModel
     */
    public void removeBar(BarChartModel barChartModel) {
        barChartModels.remove(barChartModel);
        removeBarInternal(barChartModel);
    }

    /**
     * @param index
     */
    public void removeBar(int index) {
        if (index < barChartModels.size()) {
            BarChartModel barChartModel = barChartModels.remove(index);
            removeBarInternal(barChartModel);
            System.out.println("barChart Val:" + barChartModel.getBarValue());
            System.out.println("barChart Val Index:" + index);
        }

    }

    public void clearAll() {
        barChartModels.clear();
        if (verticalLinearParent != null) {
            verticalLinearParent.removeAllViews();
        }
        if (horizontalLinearParent != null) {
            horizontalLinearParent.removeAllViews();
        }
    }


    private interface DimensionReceivedCallback {
        void onDimensionReceived(int dimension);
    }

    public interface OnBarClickListener {
        void onBarClick(BarChartModel barChartModel);
    }
}
