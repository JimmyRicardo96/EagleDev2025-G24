# 🦅 EagleDev2025-G24 - Clasificación de Radiografías con IA
## 🎥 Video de presentación del proyecto
🔗 [Haz clic aquí para ver el video](https://drive.google.com/file/d/1WRb0ZqL3WHZprMVSXo-Awkn10Tapxeeh/view?usp=sharing)

Este proyecto combina el poder del backend en **Spring Boot**, la ejecución de modelos de **Machine Learning** con **FastAPI**, y una interfaz de usuario moderna con **Streamlit**, para clasificar imágenes médicas (radiografías) en cuatro categorías:

- COVID
- Lung Opacity
- Normal
- Viral Pneumonia

## 🧠 Modelo de Machine Learning

Se utiliza un modelo entrenado y exportado en formato **TensorFlow Lite (`.tflite`)**, ideal para entornos ligeros.

## 🧪 Tecnologías utilizadas

| Tecnología     | Propósito                                 |
|----------------|--------------------------------------------|
| `Spring Boot`  | Orquestación principal y backend Java      |
| `FastAPI`      | Servicio de inferencia con ML en Python    |
| `Streamlit`    | Interfaz gráfica para carga de imágenes    |
| `TensorFlow Lite` | Ejecución del modelo entrenado         |
| `Python 3.10`  | Versión recomendada para compatibilidad     |

---

## 📂 Estructura del proyecto

```
EagleDev2025-G24/
├── streamlit-app/
│   ├── api.py                  # Servicio FastAPI
│   ├── clasificador_app.py     # App visual Streamlit
│   ├── model.tflite            # Modelo de clasificación
│   ├── requirements.txt        # Dependencias de Python
│   └── venv/                   # ⚠️ Ignorada en .gitignore
├── src/main/java/com/uisrael/.../StreamlitLauncher.java # Lanza servicios
├── .gitignore
└── README.md
```

---

## ⚙️ Instalación y ejecución

### 🔧 Requisitos

- Java 17+
- Python 3.10
- Git
- Pip

### 🧱 Clonar el repositorio

```bash
git clone https://github.com/JimmyRicardo96/EagleDev2025-G24.git
cd EagleDev2025-G24
```

### 🐍 Crear entorno virtual

```bash
cd streamlit-app
python -m venv venv
venv\Scripts\activate  # En Windows
```

### 📦 Instalar dependencias

```bash
pip install --upgrade pip
pip install -r requirements.txt
```

> ✅ Asegúrate de tener `tensorflow`, `uvicorn`, `fastapi`, `streamlit`, `pillow`, `python-multipart`, etc.

---

## 🚀 Ejecutar servicios

Desde el módulo Java con Spring Boot:

```bash
mvn spring-boot:run
```

Esto automáticamente lanza:

- ✅ `FastAPI` en: http://localhost:8000
- ✅ `Streamlit` en: http://localhost:8501

---

## 🖼️ Clasificación de imágenes

Sube una radiografía desde la interfaz en Streamlit o mediante un `POST` a:

```http
POST /predict
Host: http://localhost:8000
Body: FormData -> file = imagen.png
```

---

## 📝 Autoría

Proyecto realizado por:

- Jimmy Ricardo Delgado
- Byron Andrés Guallasamín
- Mauricio Chicaiza

---

## ⚠️ Notas importantes

- **No subas la carpeta `venv/` al repositorio.** Ya está ignorada en `.gitignore`.
- **Evita archivos pesados como `.dll` o `.pyd` del entorno virtual.**
- Si necesitas añadir grandes modelos, considera usar [Git LFS](https://git-lfs.github.com/).

---

## 📄 Licencia

Este proyecto es de uso educativo y se encuentra bajo la Licencia MIT.
