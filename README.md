Ripple Everywhere
=================

A ripple animation layout library.You can use it above android 4.0.

# Preview

![ripple_everywhere](./images/ripple_everywhere.gif)

![demo](https://github.com/onlynight/ReadmeDemo/blob/master/Readmes/ReciprocatingAnimation/images/ripple_demo.gif)

![principle](https://github.com/onlynight/ReadmeDemo/blob/master/Readmes/ReciprocatingAnimation/images/ripple_principle.gif)

(If you can't see the preview gif, you can download it, it's a large gif)

# build.gradle
In your project root path ```build.gradle``` file, add this:

```groovy
allprojects {
	repositories {
		...
		maven { url "https://jitpack.io" }
	}
}
```

And in your module path ```build.gradle``` file, add this:

```groovy
dependencies {
    compile 'com.github.onlynight:RippleEverywhere:0.1.0'
}
```

# proguard

```java
-dontwarn com.github.onlynight.rippleeverywhere.library.**
-keep com.github.onlynight.rippleeverywhere.library.** {*;}
```

# Sample Code

layout file:

```xml
<com.github.onlynight.rippleeverywhere.library.RippleLayout
    android:id="@+id/rippleLayout"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    app:ripple_anim_time="500"
    app:ripple_center_align="bottom_left"
    app:ripple_center_x="20dp"
    app:ripple_center_y="20dp"
    app:ripple_end_value="1"
    app:ripple_start_value="0">

    <ImageView
        android:layout_width="match_parent"
        android:layout_height="0dp"
        android:layout_weight="1"
        android:scaleType="centerCrop"
        android:src="@drawable/my_girlfriend"/>

    <ImageView
        android:layout_width="match_parent"
        android:layout_height="0dp"
        android:layout_weight="1"
        android:scaleType="centerCrop"
        android:src="@drawable/my_girlfriend2"/>

</com.github.onlynight.rippleeverywhere.library.RippleLayout>
```

java controller code:

```java
private RippleLayout rippleLayout;

rippleLayout = (RippleLayout) findViewById(R.id.rippleLayout);
rippleLayout.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        rippleLayout.getRadiusAnimator().start();
    }
});
```

# XML property explain

```xml
<declare-styleable name="RippleLayout">

    <!-- ripple animation center x,
    the default value is the center of the view X-->
    <attr name="ripple_center_x" format="dimension"/>

    <!-- ripple animation center y,
    the default value is the center of the view Y-->
    <attr name="ripple_center_y" format="dimension"/>

    <!-- ripple anim duration,
    the default value is 300ms-->
    <attr name="ripple_anim_time" format="integer"/>

    <!-- ripple start value,
    from time=0 set the value to this value,
    the default value is 0-->
    <attr name="ripple_start_value" format="float"/>

    <!-- ripple end value,
    to time=ripple_anim_time set the value to this value,
    the default value is 1-->
    <attr name="ripple_end_value" format="float"/>

    <!-- the ripple animation center point alignment,
     the default value is top_left.-->
    <attr name="ripple_center_align" format="enum">
        <enum name="top_left" value="0"/>
        <enum name="top_right" value="1"/>
        <enum name="bottom_left" value="2"/>
        <enum name="bottom_right" value="3"/>
    </attr>
</declare-styleable>
```

# API explain

```java
/**
 * get the radius change animator,
 * so that you can listen the animator status.
 * @return animator
 */
RippleLayout#getRadiusAnimator()
```

# THANKS

[CircularReveal]

# License

```
Copyright 2016 onlynight

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```

[CircularReveal]: https://github.com/ozodrukh/CircularReveal
