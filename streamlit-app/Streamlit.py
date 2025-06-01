import streamlit as st
from PIL import Image
import numpy as np
import tensorflow.lite as tflite

st.title("Clasificación de Rayos X: COVID-19 / Neumonía / Normal")

image = st.file_uploader("Sube una imagen de rayos X", type=["jpg", "png", "jpeg"])
if image:
    img = Image.open(image).resize((150, 150))
    st.image(img)

    interpreter = tflite.Interpreter(model_path="model.tflite")
    interpreter.allocate_tensors()

    input_index = interpreter.get_input_details()[0]['index']
    output_index = interpreter.get_output_details()[0]['index']

    img_array = np.expand_dims(np.array(img) / 255.0, axis=0).astype('float32')
    interpreter.set_tensor(input_index, img_array)
    interpreter.invoke()
    prediction = interpreter.get_tensor(output_index)

    labels = ['COVID', 'Normal', 'Viral Pneumonia', 'Lung_Opacity']
    st.success(f"Predicción: {labels[np.argmax(prediction)]}")
