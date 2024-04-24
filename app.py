from flask import Flask, request, jsonify
from flask_sqlalchemy import SQLAlchemy
from sqlalchemy import LargeBinary
import base64
from PIL import Image as PILImage
from io import BytesIO

app = Flask(__name__)
app.config['SQLALCHEMY_DATABASE_URI'] = 'sqlite:///images.db'
db = SQLAlchemy(app)

class review_data(db.Model):
    id = db.Column(db.Integer, primary_key=True)
    image = db.Column(LargeBinary)
    location = db.Column(db.String)

with app.app_context():
    db.create_all()

@app.route('/')
def home():
    return "Hi there!!"

@app.route('/upload', methods=['POST'])
def upload_image():
    # Assuming the image is sent as a base64 string in the request
    image_base64 = request.form.get('image')
    location = request.form.get('location')

    if image_base64:
        # Decode the base64 string
        image_data = base64.b64decode(image_base64)

        # Save the image to the database
        add_data = review_data(image=image_data, location=location)
        db.session.add(add_data)
        db.session.commit()

        return jsonify({'message': 'Image uploaded successfully!'})

    else:
        return jsonify({'error': 'No image data provided!'})


if __name__ == '__main__':
    app.run(host='192.168.1.7', port=5000,debug=True)