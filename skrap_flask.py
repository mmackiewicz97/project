import os
import datetime
import pytz
import csv
from flask import Flask, render_template, request, url_for, redirect, session
from flask_sqlalchemy import SQLAlchemy
import skrap_api
from sqlalchemy.sql import func

app = Flask(__name__, template_folder=".")
app.config['SQLALCHEMY_DATABASE_URI'] = 'sqlite:////home/database.db'
app.config['SQLALCHEMY_TRACK_MODIFICATIONS'] = False
app.config['SECRET_KEY'] = ''
app.app_context().push()

db = SQLAlchemy(app)
db.Model.metadata.reflect(db.engine)


class Div(db.Model):
    __table__ = db.Model.metadata.tables['div']
    def __repr__(self):
        return f'FK DIV: {self.title} {str(self.price)} zł {self.url}'

class Address(db.Model):
    __table__ = db.Model.metadata.tables['address']

    def __repr__(self):
        return f'FK ADRES: {self.city} ID: {self.address_id}'


@app.route('/')
def index():
    s = skrap_api.Skrap_API()
    session['start_time'] = s.start_time
    session['url'] = url_for('now')
    s.get_data("link")
    divs = Div.query.join(Address).filter(Div.address_id == Address.address_id) \
        .add_columns(Address.city, Address.distance) \
        .filter(Div.saved == s.start_time)\
        .order_by(Div.price).all()
    return render_template('skrap_divs.html', divs=divs, today=datetime.datetime.now())
@app.route('/new')
def new():
    session['url'] = url_for('new')
    divs = Div.query.join(Address).filter(Div.address_id == Address.address_id) \
        .add_columns(Address.city, Address.distance) \
        .filter(Div.created >= datetime.datetime.strptime('01.04.2023 00:00', '%d.%m.%Y %H:%M')) \
        .order_by(Div.price).all()
    #y = datetime.datetime(2023,5,6,11,38,42)
    #div.edited = div.edited.replace(tzinfo=pytz.utc)
    return render_template('skrap_divs.html', divs=divs, today=datetime.datetime.now())
@app.route('/unused')
def unused():
    session['url'] = url_for('unused')
    divs = Div.query.join(Address).filter(Div.address_id == Address.address_id) \
        .add_columns(Address.city, Address.distance) \
        .filter(Div.edited != session.get('start_time')) \
        .order_by(Div.price).all()
    return render_template('divs.html', divs=divs, today=datetime.datetime.now())
@app.route('/select', methods=['POST'])
def select():
    divs = Div.query.filter(Div.div_id.in_(request.form.getlist('select'))).all()
    with open('/selected.csv', 'a', encoding='UTF8') as f:
        writer = csv.writer(f)
        for div in divs:
            writer.writerow([div.div_id, div.title, div.price, div.url])
    return redirect(session['url'])

@app.route('/select', methods=['GET'])
def get_select():
    session['url'] = url_for('get_select')
    ids = []
    with open('/selected.csv') as f:
        reader = csv.reader(f, delimiter=',')
        for row in reader:
            ids.append(row[0])
    divs = Div.query.join(Address).filter(Div.address_id == Address.address_id)\
        .add_columns(Address.city, Address.distance)\
        .order_by(Div.price)\
        .filter(Div.div_id.in_(ids)).all()
    return render_template('skrap_divs.html', divs=divs, today=datetime.datetime.now())

@app.route('/del/<id>')
def del_div(id):
    div = Div.query.filter(Div.div_id == int(id)).first_or_404()
    try:
        db.session.delete(div)
        db.session.commit()
        print("deleted ", div)
    except:
        pass
    return redirect(session['url'])


if __name__ == "__main__":
    app.run(debug=True, threaded=False)
