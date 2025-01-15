### SO, when I learn this topic. I facing several issue.  
One of them is, Gradle not generate BuildConfig. even though I already set up buildConfigField.
The solutions :
1. Make sure you already set up buildConfigField
2. Ensure the buildConfig feature is explicitly enabled in your build.gradle file.
3. Build > Rebuild Project (possible issue in this case)
    Force Clean and Rebuild
    Sometimes, cached files prevent BuildConfig from being generated. 
    Perform a clean build to resolve this:
    Go to Build > Clean Project in Android Studio.
    Then select Build > Rebuild Project.
4. run [ ./gradlew assemble ]
    karena, By default, Android Studio only generates resources and code (e.g., BuildConfig)
    for the currently selected Build Variant. To generate all Build Variants, you can trigger
    a full build from the command line [ ./gradlew assemble ]
5. OR If you only want to build certain Build Variants, use:
    [./gradlew assembleDebug] and [./gradlew assembleRelease]
   This ensures the BuildConfig files for both debug and release are generated.

### So far, what i did was right. But as mention at point 4
"Android Studio only generates resources for the currently selected Build Variant.".
So i need to know how to reverse it to default.
I can do these approaches:
Cleaning the build artifacts, which can be achieved using [ ./gradlew clean ]. What does [ ./gradlew clean ] do?
It removes all the generated build files from the build/ directory of your project and its modules.
Essentially, it resets the project to a pre-build state, deleting compiled classes, generated resources, and other intermediate files.
BUT running [ ./gradlew clean ] will not de-assemble or reverse the assemble process; it only removes the build outputs and intermediate files from the build/ directory.
Gradle doesn't have a built-in "de-assemble" process since its purpose is to build artifacts, not reverse them.
So you can use Use [ ./gradlew clean assemble ] to clean the build directory and regenerate all build artifacts.

### BuildType profile is missing or become an error.
Unresolved reference: profile
Why buildTypes profile in kotlin DSL is missing ?
The buildTypes.profile is missing in Kotlin DSL because Gradle does not define profile as a default build type. 
Only debug and release are preconfigured by default in Gradle. If you want to use a profile build type, you must explicitly define it in your build.gradle.kts.
Not Predefined by Gradle: Gradle only provides debug and release as built-in build types. 
Any other build types, like profile, must be manually added.

