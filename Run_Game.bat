@echo off
cd /d "%~dp0"
javac *.java
if errorlevel 1 (
    echo Compilation failed. Make sure Java JDK is installed.
    pause
    exit /b 1
)
java Main
