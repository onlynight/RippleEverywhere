Ripple Everywhere
=================

A ripple animation layout library.You can use it above android 4.0.

#Preview

![demo](https://github.com/onlynight/ReadmeDemo/blob/master/Readmes/ReciprocatingAnimation/images/ripple_demo.gif)

![principle](https://github.com/onlynight/ReadmeDemo/blob/master/Readmes/ReciprocatingAnimation/images/ripple_principle.gif)

(If you can't see the preview gif, you can download it, it's a large gif)

#build.gradle
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
    compile 'com.github.onlynight:RippleEverywhere:0.0.2'
}
```

#Sample Code

layout file:
