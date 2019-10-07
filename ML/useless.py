import cv2
import face_recognition
# from flask import Flask, request, jsonify
# from base64 import decodebytes

# app = Flask(__name__)


# @app.route('/', methods=['POST'])
# def index():
#     img_data = request.json['image']
#     img_data = bytes(img_data, 'utf-8')
#     with open("img.png", "wb") as img:
#         img.write(decodebytes(img_data))

#     img = cv2.imread('img.png')
#     img = img[:, :, ::-1]
#     face_locations = face_recognition.face_locations(img)
#     return jsonify(prediction=len(face_locations))

import sys
import numpy as np
cap = cv2.VideoCapture(0)

while True:
    demo, frame = cap.read()
    rgb_frame = frame[:, :, ::-1]
    face_locations = face_recognition.face_locations(rgb_frame)

    for(y, w, h, x) in face_locations:
        cv2.rectangle(frame, (x, y), (w, h), (0, 255, 255), 2)
    cv2.imshow('Video', frame)

    if cv2.waitKey(1) & 0xFF == ord('q'):
        sys.exit()

cap.release()
cv2.destroyAllWindows()

# img = cv2.imread('11.jpg')
# rgb_img = img[:, :, ::-1]
# face_locations = face_recognition.face_locations(rgb_img)
# # face_landmarks = face_recognition.face_landmarks(rgb_img)

# for top, right, bottom, left in face_locations:
#     cv2.rectangle(img, (left, top), (right, bottom), (0, 0, 255), 2)

# cv2.imshow('Video', img)
# cv2.waitKey(50000)
# print(face_landmarks[0]['left_eye'])
