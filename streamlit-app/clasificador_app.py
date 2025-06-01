import streamlit as st
from PIL import Image
import numpy as np
import tensorflow.lite as tflite
import base64
import os

st.set_page_config(page_title="API de Predicción")

# Cargar modelo
interpreter = tflite.Interpreter(model_path="model.tflite")
interpreter.allocate_tensors()
input_index = interpreter.get_input_details()[0]['index']
output_index = interpreter.get_output_details()[0]['index']

def preprocess_image(image: Image.Image):
    image = image.resize((150, 150))
    img_array = np.array(image) / 255.0
    return np.expand_dims(img_array, axis=0).astype(np.float32)

def predict(image: Image.Image):
    input_data = preprocess_image(image)
    interpreter.set_tensor(input_index, input_data)
    interpreter.invoke()
    prediction = interpreter.get_tensor(output_index)
    return prediction

st.title("Clasificador de Rayos X")

uploaded_file = st.file_uploader("Sube una imagen", type=["jpg", "png", "jpeg"])
if uploaded_file is not None:
    image = Image.open(uploaded_file)
    st.image(image, caption="Imagen subida", use_column_width=True)
    result = predict(image)
    labels = ['COVID', 'Lung Opacity', 'Normal', 'Viral Pneumonia']
    st.success(f"Resultado: {labels[np.argmax(result)]} con {np.max(result)*100:.2f}% de confianza")
