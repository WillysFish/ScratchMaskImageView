# ScratchMaskImageView
ScratchMaskImageView can scratch off itself by touch screen.  
It extensions by ImageView, so it easily uses just like ImageView.  
We can custom drawable, size, positions which you like.  
<br/>
Let we see the sample below.  

| Percentage of scratch arrived xx% | Custom size (Bigger mask)  |
| :-----: | :-----: |
| <img src="https://github.com/WillysFish/ScratchMaskImageView/blob/main/auto_revealed.gif" height="40%" width="40%" > | <img src="https://github.com/WillysFish/ScratchMaskImageView/blob/main/custom_size.gif" height="40%" width="40%" > |  

| Revert mask |Manually clear |
| :-----: | :-----: |
| <img src="https://github.com/WillysFish/ScratchMaskImageView/blob/main/mask.gif" height="40%" width="40%" > | <img src="https://github.com/WillysFish/ScratchMaskImageView/blob/main/clear.gif" height="40%" width="40%" > |  

# Download
### Gradle:  
Add it in your root build.gradle at the end of repositories:
```gradle
allprojects {
  repositories {
    ...
    maven { url 'https://jitpack.io' }
  }
}
```
Add the dependency
```gradle
dependencies {
	 implementation 'com.github.WillysFish:ScratchMaskImageView:1.0.0'
}
```
### Maven:
Add the JitPack repository to your build file
```xml
<repositories>
  <repository>
      <id>jitpack.io</id>
      <url>https://jitpack.io</url>
  </repository>
</repositories>
```
Add the dependency
```xml
<dependency>
  <groupId>com.github.WillysFish</groupId>
  <artifactId>ScratchMaskImageView</artifactId>
  <version>1.0.0</version>
</dependency>
```

# How to use?  
It's easy, we just put ScratchMaskImageView on the area of target that make it been covered by ScratchMaskImageView.  

Example:
```xml
<androidx.constraintlayout.widget.ConstraintLayout>

    <androidx.appcompat.widget.AppCompatTextView
        android:id="@+id/target" />

    <studio.zewei.willy.scratch.ScratchMaskImageView
        android:id="@+id/maskImage"
        android:layout_width="0dp" // or specifica a size
        android:layout_height="0dp"
        android:src="@drawable/tennis"
        app:layout_constraintBottom_toBottomOf="@id/target"
        app:layout_constraintEnd_toEndOf="@id/target"
        app:layout_constraintStart_toStartOf="@id/target"
        app:layout_constraintTop_toTopOf="@id/target" />

</androidx.constraintlayout.widget.ConstraintLayout>
```
  
# Method Introduction
- Set the stroke width for paint of eraser
```kotlin
fun setEraseStrokeWidth(width: Float) {}
```
<br/>

- Set a observer to watch the percentage of revealed
```kotlin
fun setRevealedObserver(observer: (percentage: Float) -> Unit) {}
```
<br/>

- Clear all area of masking with the Fade Animation.
```kotlin
fun clear(fadeAnimMs: Long = 500) {}
```  
<br/>

- Revert scratches and mask view again.
```kotlin
fun mask() {}
```


# License
```
Copyright 2021 zewei yan

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


