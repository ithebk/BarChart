package me.ithebk.barchart;

/**
 * Created by bharath on 12/10/17.
 */

public class BarChartModel {
    private int barValue;
    private int barColor;
    private Object barTag;

    BarChartModel(int barValue, int barColor, Object barTag) {
        this.barValue = barValue;
        this.barColor = barColor;
        this.barTag = barTag;
    }

    public BarChartModel() {

    }

    public int getBarValue() {
        return barValue;
    }

    public void setBarValue(int barValue) {
        this.barValue = barValue;
    }

    public int getBarColor() {
        return barColor;
    }

    public void setBarColor(int barColor) {
        this.barColor = barColor;
    }

    public Object getBarTag() {
        return barTag;
    }

    public void setBarTag(Object barTag) {
        this.barTag = barTag;
    }
}
