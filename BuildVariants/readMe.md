JADI SAYA NEMU BEBERAPA PROBLEM

Salah satunya, Gradle tidak generate BuildConfig. padahal saya sudah set up buildConfigField
Solusinya :
1. pastikan sudah set up buildConfigField
2. Build > Rebuild Project (Kemungkinan ada issue pada hal ini)
    Force Clean and Rebuild
    Sometimes, cached files prevent BuildConfig from being generated. 
    Perform a clean build to resolve this:
    Go to Build > Clean Project in Android Studio.
    Then select Build > Rebuild Project.
3. run [ ./gradlew assemble ]
    karena, By default, Android Studio only generates resources and code (e.g., BuildConfig)
    for the currently selected Build Variant. To generate all Build Variants, you can trigger
    a full build from the command line [ ./gradlew assemble ]