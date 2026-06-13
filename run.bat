@echo off
echo ============================================
echo   AI Career Match ^& Placement Portal
echo ============================================
echo.

:: Kill anything already on port 8081
echo Checking for processes on port 8081...
for /f "tokens=5" %%a in ('netstat -ano 2^>nul ^| findstr ":8081" ^| findstr "LISTENING"') do (
    echo Killing old process %%a on port 8081...
    taskkill /PID %%a /F >nul 2>&1
)

echo Starting server...
echo Open your browser at: http://localhost:8081
echo.
.\gradlew bootRun
pause
