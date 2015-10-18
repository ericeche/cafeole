# Programmer's code review

##Instructions

- Install .apk
- Run app icon CaffeOle
- By default clicking on search button with no data, will look for Cafe places within a 5 mile radius
- Or Search for something else
- Single search has been deactivated. It is used only in the free version.


## Programmer's technical

- Passing objects between activities using Parcelable instead of Serialazable

- GoogleSearchType.java

        1. Array manipulation from resources
        2. Uses Parcelable objects
        3. ArrayAdapters

- GoogleType.java

        1. Implement this class from "Parcelable"
          So that you can pass this class Object to another using Intents
          Otherwise you can't pass to another actitivy
          write custom code for marshaling and unmarshaling so it creates
          less garbage objects in comparison to Serialization.

          Serialization is a marker interface,
          which implies the user cannot marshal the data according to their requirements.
          In Serialization, a marshaling operation is performed on a Java Virtual Machine (JVM)
          using the Java reflection API.
          This helps identify the Java objects member and behavior,
          but also ends up creating a lot of garbage objects.
          Due to this, the Serialization process is slow in comparison to Parcelable.

- MainActivity.java

        1. Receives parcelable object and displays object in Log.i
        2. Instantiates http connections
        3. Uses Asynch task to load google data
        4. Passes parcelable object to Asynch task
        
## Sliding Menu Using ECLIPSE

![Sliding Window](https://youtu.be/MyyHXFqUffU "sliding fragment window")

### 1. Maven library install

Modify your bash_profile for maven and ANDROID_HOME environment variables:

~~~

	export ANDROID_SDK_HOME=/Users/ericeche/android-sdks/platform-tools
	export M2_HOME=/usr/local/Cellar/maven31
	export PATH=$PATH:$M2_HOME/bin


~~~
 


Android seems to be working only with maven 3.1.1 Download and install maven 3.1.1 here :

 [maven link](http://archive.apache.org/dist/maven/maven-3/)

~~~

	export ANDROID_SDK_HOME=/Users/ericeche/android-sdks/platform-tools
	export M2_HOME=/usr/local/Cellar/maven31
	export PATH=$PATH:$M2_HOME/bin

~~~ 


~~~

cerveza:slidingmenu ericeche$ mvn -v
Apache Maven 3.1.1 (0728685237757ffbf44136acec0402957f723d9a; 2013-09-17 11:22:22-0400)
Maven home: /usr/local/Cellar/maven31
Java version: 1.7.0_71, vendor: Oracle Corporation
Java home: /Library/Java/JavaVirtualMachines/jdk1.7.0_71.jdk/Contents/Home/jre
Default locale: en_US, platform encoding: UTF-8
OS name: "mac os x", version: "10.10.4", arch: "x86_64", family: "mac"

~~~

Clone maven-android-sdk-deployer project. Read the instructions here :

 [maven-android-sdk-deployer link](https://github.com/simpligility/maven-android-sdk-deployer/blob/master/README.markdown)

~~~
git clone  https://github.com/mosabua/maven-android-sdk-deployer
cd maven-android-sdk-deployer
mvn -P4.2 clean install


[INFO] ------------------------------------------------------------------------
[INFO] Reactor Summary:
[INFO] 
[INFO] Maven Android SDK Deployer ........................ SUCCESS [14.653s]
[INFO] Android Platforms ................................. SUCCESS [0.007s]
[INFO] Android Platform 4.2 API 17 ....................... SUCCESS [11.572s]
[INFO] Android Add-Ons ................................... SUCCESS [0.008s]
[INFO] Android Add-On Google Platform 4.2 API 17 (Maps and USB)  SUCCESS [0.016s]


~~~

Checkout the SlidingMenu source code and install it.

~~~

git clone https://github.com/jfeinstein10/SlidingMenu

~~~

Modify the pom.xml according to the instructions in the README.MD from maven-android-sdk-deployer. For example:

~~~

                        <dependency>
                                <groupId>com.google.android</groupId>
                                <artifactId>android</artifactId>
                                <version>4.2.2_r3</version>
                        </dependency>
                        <dependency>
                                <groupId>com.google.android.maps</groupId>
                                <artifactId>maps</artifactId>
                                <version>17_r3</version>
                        </dependency>
                        <dependency>
                                <groupId>com.google.android</groupId>
                                <artifactId>support-v4</artifactId>
                                <version>r7</version>
                        </dependency>


~~~


Update the android maven plugin: 


~~~
<plugin>
 <groupId>com.jayway.maven.plugins.android.generation2</groupId>
 <artifactId>android-maven-plugin</artifactId>
 <version>3.8.0</version>
 <configuration>
  <sdk>
     <platform>16</platform>
  </sdk>
  <undeployBeforeDeploy>true</undeployBeforeDeploy>
 </configuration>
</plugin>

~~~

On the SlidingMenu directory

~~~
  mvn clean install

[INFO] ------------------------------------------------------------------------
[INFO] Reactor Summary:
[INFO] 
[INFO] SlidingMenu (Parent) .............................. SUCCESS [0.074s]
[INFO] SlidingMenu ....................................... SUCCESS [0.110s]
[INFO] SlidingMenu Maps Support .......................... SUCCESS [0.002s]
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS

~~~


SlidingMenu jar library should be under :


~~~

/Users/ericeche/.m2/repository/com/jeremyfeinstein/slidingmenu/slidingmenu/

~~~

## 2. Working With the SlidingMenu library in Eclipse

Drop this jar file in your Eclipse project lib directory.


![Sliding jar file](https://github.com/ericeche/cafeole/blob/master/jarlib.png "sliding jar file")

## 3. Resource Files

Add the following resource file under the res directory

~~~

Layout of the sliding menu
       1. res/layout/slidingmenu.xml
2. res/values/dimens.xml
3. res/drawable/slidingmenu_shadow.xml
4. Add the following colors to the styles.xml
        <color name="purple_light">#a276eb</color> 
        <color name="purple_middle">#8658ce</color>
        <color name="purple_dark">#6a3ab2</color> 

Layout of the fragment of the sliding menu
        5. res/layout/slidingmenu_fragment.xml
Layouts for the ExpandableListView
        6. res/layout/slidingmenu_sectionview.xml 
Layout section item :
        7. res/layout/slidingmenu_sectionitem.xml
Drawable to define the color when section item is pressed                        
		8. res/drawable/slidingmenu_list_selector_background.xml
        

~~~

Add the following classes to your project:

~~~

Classes to describe the menu:
  1. class Section 
  2. class SectionItem 
	
Adapter Class for the ExpandableListView	
  3. class SectionListAdapter

SlidingMenuFragment Class
  4. class SlidingMenuFragment	
	onCreateView method :
		Inflate the fragment layout
		Find the ExpandableListView component
		Disable the group indicator on the ExpandableListView
		Initialize the adapter
		Disable the click on sections of the ExpandableListView
		Expand all groups of the ExpandableListView.
	createMenu method :
		this is where you define your menu (sections and section items).
	onClick method :
		to handle clicks on section items

~~~


Download some menu icons from :

[icons link](http://www.flaticon.com/categories/networking)

![drwawable icons](https://github.com/ericeche/cafeole/blob/master/icons.png "sliding jar file")

![String icon](https://github.com/ericeche/cafeole/blob/master/icons1.png "sliding jar file")


Here are the screen shoots:



![Sliding Window](https://github.com/ericeche/cafeole/blob/master/sliding.png "sliding fragment window")

![Sliding window fragment](https://github.com/ericeche/cafeole/blob/master/sliding1.png "sliding fragment window")













        



