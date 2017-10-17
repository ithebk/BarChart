# BarChart
[ ![Download](https://api.bintray.com/packages/ithebk/maven/Bar-Chart/images/download.svg) ](https://bintray.com/ithebk/maven/Bar-Chart/_latestVersion)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)
<br/>The BarChart is an customized chart library for android. It provides vertical and horizontal bar chart.<br/>
<img src="/screenshot/barchartsample.gif" width="300" height="466">
<br/>Supported on API Level 10 and above.
## Installation
Add jcenter repository to build.gradle in Project Level
	
	repositories {
		jcenter()
	}

Add below dependency to build.gradle in app level

	dependencies {
	   compile 'me.ithebk:barchart:0.9'
	}

## Usage
Developers can customize the following attributes in xml:
          
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
        
        
             


## License

Licensed under the Apache License, Version 2.0 (the "License"); you may not use this work except in compliance with the License.
You may obtain a copy of the License in the LICENSE file, or at:

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.

