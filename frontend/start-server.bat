@echo off
echo Starting Frontend Server on http://localhost:3000
echo.
echo Make sure the backend is running on http://localhost:8080
echo.
echo -- OR --
echo.
echo If Node.js is not installed, just open:
echo file:///C:/Users/VANSH/.gemini/antigravity/scratch/modern-banking-system/frontend/index.html
echo And REFRESH THE PAGE after creating an account
echo.
pause

cd /d "%~dp0"
npx -y http-server -p 3000 -c-1
