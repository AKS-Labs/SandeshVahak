@echo off
echo Setting up Android build environment...

set JAVA_HOME=C:\Program Files\Android\Android Studio\jbr
set ANDROID_HOME=C:\Users\ashin\AppData\Local\Android\Sdk
set PATH=%JAVA_HOME%\bin;%ANDROID_HOME%\tools;%ANDROID_HOME%\platform-tools;%PATH%

echo JAVA_HOME: %JAVA_HOME%
echo ANDROID_HOME: %ANDROID_HOME%

echo.
echo Checking Java version...
java -version

echo.
echo Checking Gradle wrapper...
if exist gradlew.bat (
    echo Gradle wrapper found
) else (
    echo ERROR: Gradle wrapper not found!
    pause
    exit /b 1
)

echo.
echo Starting clean build...
gradlew.bat clean assembleDebug

echo.
echo Build complete! Check the output above for any errors.
echo APK should be in: app\build\outputs\apk\debug\
pause
