So when i learn about Deep link


i implement some part, but when i use deep link, 
the disambiguation dialog does not appears for deep link android kotlin.

I need go to setting and setup link in app preference. so that disambiguation dialog will appear
is that right ?

or i forgot to implement something

but if I test with this command bellow, its works fine : 

adb shell am start \
-W -a android.intent.action.VIEW \
-d "https://www.mricoism.id/hello/FellasHowHoHow" \
com.mricoism.deeplinkapp

Once it does, try the command again without the explicit package name 
‘com.mricoism.deeplinkapp’. This tests that Android can open the application with the link correctly.

adb shell am start \
-W -a android.intent.action.VIEW \
-d "https://www.mricoism.id/hello/FellasHowHoHow" 