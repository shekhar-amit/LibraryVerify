# Change Log

## Version 1.0
A few of the features were changed or redesigned while porting the library from Android to HarmonyOS. These are listed below.

### Displaying Errors
Android's EditText supports `setError()` method that displays errors as shown:

![alt text](https://i.stack.imgur.com/D1Uth.png "Android Error")

Instead of extending the TextField to create a new component, this library utilizes the following logic: If `showErrors: true`, if a TextField is invalid, overwrite the hint text as the error message and change the color of the hint text to red. This is shown here: 
![alt text](https://i.imgur.com/VTDiEyS.png "HMOS Before Error")
 
![alt text](https://i.imgur.com/DpYTc34.png "HMOS After Error")

### onLayout Logic

Android implements inflating the validators using XML and recognizing the EditTexts embedded via `onLayout` method as shown.

```java
@Override  
protected void onLayout(boolean changed, int l, int t, int r, int b) {  
  super.onLayout(changed, l, t, r, b);  
  // get edit text  
  int childCount = getChildCount();  
  // only one edit text per input validator  
  if(childCount == 0 || childCount > 1)  
  try {  
  throw new Exception("InputValidator must contain only one EditText");  
  } catch (Exception e) {  
  e.printStackTrace();  
  }  
  mEditText = (EditText) getChildAt(0);  
  
  // build validator  
  buildValidator();  
}
```

In this implementation, this inflation is done inside `buildValidator` method:
```java
if(mEditText==null) {  
    // get edit text  
  int childCount = getChildCount();  
  // only one edit text per input validator  
  if(childCount == 0 || childCount > 1)  
        try {  
            throw new Exception("InputValidator must contain only one EditText");  
  } catch (Exception e) {  
            e.printStackTrace();  
  }  
    mEditText = (TextField) getComponentAt(0);  
}
```
If the InputValidator is built using the Java Builder methods, mEditText would not be null, hence this section wouldn't be used. The `buildValidator` method is called when `isValid` is called for the first time.

