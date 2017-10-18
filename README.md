# BarChart
[ ![Download](https://api.bintray.com/packages/ithebk/maven/Bar-Chart/images/download.svg) ](https://bintray.com/ithebk/maven/Bar-Chart/_latestVersion)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)
[![Android Arsenal]( https://img.shields.io/badge/Android%20Arsenal-Bar%20Chart-green.svg?style=flat )]( https://android-arsenal.com/details/1/6350 )


The BarChart is an customized chart library for android. It provides vertical and horizontal bar chart.

<img src="/screenshot/barchartsample.gif" width="300" height="466"> <img src="/screenshot/bar_chart_vert.gif" width="300" height="466">

<br/>Supported on API Level 10 and above.

## Installation
Add jcenter repository to `build.gradle` in Project Level
	
	repositories {
		jcenter()
	}

Add below dependency to `build.gradle` in app level

	dependencies {
	   compile 'me.ithebk:barchart:0.9'
	}

## Usage
First step is top add BarChart view to xml with required width and height.
And you can customise the view by parameters.

#### XML:

 	<me.ithebk.barchart.BarChart
	  android:id="@+id/bar_chart_vertical"
	  barchart:bar_type="vertical"
	  barchart:bar_width="15dp"
	  barchart:bar_color="@color/colorPrimary"
	  barchart:bar_text_color="#808080"
	  barchart:bar_text_size="10sp"
	  barchart:bar_show_auto_color="false"
	  barchart:bar_max_value="100"
	  barchart:bar_spaces = "0dp"
	  barchart:bar_show_value="true"
	  barchart:bar_show_animation="true"
	  android:layout_width="wrap_content"
	  android:layout_height="150dp"/>
	
#### Java : 

	BarChart barChart = (BarChart) findViewById(R.id.bar_chart_vertical);
	barChart.setBarMaxValue(100);
	
#### ADD BAR:

	//Add single bar
	BarChartModel barChartModel = new BarChartModel();
	barChartModel.setBarValue(50);
	barChartModel.setBarColor(Color.parseColor("#9C27B0"));
    barChartModel.setBarTag(null); //You can set your own tag to bar model
    barChartModel.setBarText("50");
	
    barChart.addBar(barChartModel);
	
	//Add mutliple bar at once as list;
	List<BarChartModel> barChartModelList = new ArrayList<>();
	
	//populate bar array list and add to barchart as a list. 
	barChart.addBar(barChartModelList);
	
#### GET BAR:
	
	//Return value is a list of BarChartModel
	barchart.getBar();
	
	//Return value is BarChartModel at that index
	barchart.getBar(index);

	
#### UPDATE BAR : 
	
	BarChartModel barChartModelNew = new BarChartModel();
    barChartModelNew.setBarValue(70);
    barChartModelNew.setBarColor(Color.parseColor("#CDDC39"));
    barChartModelNew.setBarTag(null);
	int index = 0;
   	barChart.updateBar(index,barChartModelNew);
	
#### REMOVE BAR :

	//To remove BarChartModel object from the list
	barChart.removeBar(barChartModel);
	
	//To remove BarChartModel object at the index from the list
	barChart.removeBar(index);
	
#### ONCLICK LISTENER FOR BAR:

	barChart.setOnBarClickListener(new BarChart.OnBarClickListener() {
            @Override
            public void onBarClick(BarChartModel barChartModel) {
                //Returns the bar you have clicked.
            }
        });
	
	

## License
	Copyright 2017 Bharath Kumar

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

