@echo off
SETLOCAL ENABLEDELAYEDEXPANSION

:: Cambiar al directorio del proyecto
cd /d "%~dp0"

:: Carpeta de la app Python
set STREAMLIT_DIR=streamlit-app

:: Ruta al ejecutable del entorno virtual
set VENV_PATH=%STREAMLIT_DIR%\venv\Scripts\python.exe

:: Si no existe el entorno virtual, crearlo
IF NOT EXIST "%VENV_PATH%" (
    echo 🐍 Entorno virtual no encontrado. Creando entorno virtual...
    python -m venv %STREAMLIT_DIR%\venv
)

:: Activar entorno virtual
echo ✅ Activando entorno virtual...
call %STREAMLIT_DIR%\venv\Scripts\activate.bat

:: Instalar dependencias si es necesario
IF EXIST "%STREAMLIT_DIR%\requirements.txt" (
    echo 📦 Instalando dependencias...
    pip install -r %STREAMLIT_DIR%\requirements.txt
)


pause
