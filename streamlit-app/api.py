from fastapi import FastAPI, UploadFile, File
from fastapi.middleware.cors import CORSMiddleware
from PIL import Image
import numpy as np
import tensorflow.lite as tflite
import uvicorn
import io

app = FastAPI()

app.add_middleware(
    CORSMiddleware,
    allow_origins=["*"],  # para pruebas locales
    allow_credentials=True,
    allow_methods=["*"],
    allow_headers=["*"],
)

# Modelo
interpreter = tflite.Interpreter(model_path="model.tflite")
interpreter.allocate_tensors()
input_index = interpreter.get_input_details()[0]['index']
output_index = interpreter.get_output_details()[0]['index']

def preprocess_image(image: Image.Image):
    image = image.resize((150, 150))
    img_array = np.array(image) / 255.0
    return np.expand_dims(img_array, axis=0).astype(np.float32)

@app.post("/predict")
async def predict_api(file: UploadFile = File(...)):
    contents = await file.read()
    image = Image.open(io.BytesIO(contents))
    input_data = preprocess_image(image)

    interpreter.set_tensor(input_index, input_data)
    interpreter.invoke()
    output = interpreter.get_tensor(output_index)

    labels = ['COVID', 'Lung Opacity', 'Normal', 'Viral Pneumonia']
    prediction_index = int(np.argmax(output))
    confidence = float(np.max(output))

    return {
        "label": labels[prediction_index],
        "confidence": round(confidence * 100, 2)
    }

if __name__ == "__main__":
    uvicorn.run(app, host="localhost", port=8000)
