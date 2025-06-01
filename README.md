# 馃 EagleDev2025-G24 - Clasificaci贸n de Radiograf铆as con IA

Este proyecto combina el poder del backend en **Spring Boot**, la ejecuci贸n de modelos de **Machine Learning** con **FastAPI**, y una interfaz de usuario moderna con **Streamlit**, para clasificar im谩genes m茅dicas (radiograf铆as) en cuatro categor铆as:

- COVID
- Lung Opacity
- Normal
- Viral Pneumonia

## 馃 Modelo de Machine Learning

Se utiliza un modelo entrenado y exportado en formato **TensorFlow Lite (`.tflite`)**, ideal para entornos ligeros.

## 馃И Tecnolog铆as utilizadas

| Tecnolog铆a     | Prop贸sito                                 |
|----------------|--------------------------------------------|
| `Spring Boot`  | Orquestaci贸n principal y backend Java      |
| `FastAPI`      | Servicio de inferencia con ML en Python    |
| `Streamlit`    | Interfaz gr谩fica para carga de im谩genes    |
| `TensorFlow Lite` | Ejecuci贸n del modelo entrenado         |
| `Python 3.10`  | Versi贸n recomendada para compatibilidad     |

---

## 馃搨 Estructura del proyecto

```
EagleDev2025-G24/
鈹溾攢鈹€ streamlit-app/
鈹?  鈹溾攢鈹€ api.py                  # Servicio FastAPI
鈹?  鈹溾攢鈹€ clasificador_app.py     # App visual Streamlit
鈹?  鈹溾攢鈹€ model.tflite            # Modelo de clasificaci贸n
鈹?  鈹溾攢鈹€ requirements.txt        # Dependencias de Python
鈹?  鈹斺攢鈹€ venv/                   # 鈿狅笍 Ignorada en .gitignore
鈹溾攢鈹€ src/main/java/com/uisrael/.../StreamlitLauncher.java # Lanza servicios
鈹溾攢鈹€ .gitignore
鈹斺攢鈹€ README.md
```

---

## 鈿欙笍 Instalaci贸n y ejecuci贸n

### 馃敡 Requisitos

- Java 17+
- Python 3.10
- Git
- Pip

### 馃П Clonar el repositorio

```bash
git clone https://github.com/JimmyRicardo96/EagleDev2025-G24.git
cd EagleDev2025-G24
```

### 馃悕 Crear entorno virtual

```bash
cd streamlit-app
python -m venv venv
venv\Scripts\activate  # En Windows
```

### 馃摝 Instalar dependencias

```bash
pip install --upgrade pip
pip install -r requirements.txt
```

> 鉁?Aseg煤rate de tener `tensorflow`, `uvicorn`, `fastapi`, `streamlit`, `pillow`, `python-multipart`, etc.

---

## 馃殌 Ejecutar servicios

Desde el m贸dulo Java con Spring Boot:

```bash
mvn spring-boot:run
```

Esto autom谩ticamente lanza:

- 鉁?`FastAPI` en: http://localhost:8000
- 鉁?`Streamlit` en: http://localhost:8501

---

## 馃柤锔?Clasificaci贸n de im谩genes

Sube una radiograf铆a desde la interfaz en Streamlit o mediante un `POST` a:

```http
POST /predict
Host: http://localhost:8000
Body: FormData -> file = imagen.png
```

---

## 馃摑 Autor铆a

Proyecto realizado por:

- Jimmy Ricardo Delgado
- Byron Andr茅s Guallasam铆n
- [+ otros integrantes...]

---

## 鈿狅笍 Notas importantes

- **No subas la carpeta `venv/` al repositorio.** Ya est谩 ignorada en `.gitignore`.
- **Evita archivos pesados como `.dll` o `.pyd` del entorno virtual.**
- Si necesitas a帽adir grandes modelos, considera usar [Git LFS](https://git-lfs.github.com/).

---

## 馃搫 Licencia

Este proyecto es de uso educativo y se encuentra bajo la Licencia MIT.
