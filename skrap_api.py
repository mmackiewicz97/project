import time
import datetime
import folium
import requests
import math
from sqlalchemy import Column, Integer, String, ForeignKey, Float, DateTime
from sqlalchemy import select, func
from sqlalchemy.future import create_engine
from sqlalchemy.orm import relationship, backref, sessionmaker, declarative_base
from sqlalchemy import desc
from skrap_fb import SkrapFB

engine = create_engine(f"sqlite:////database.db")
Session = sessionmaker(expire_on_commit=False)
Session.configure(bind=engine)
session = Session()

Base = declarative_base()
class Div(Base):
    def __init__(self):
        self.title = ""
        self.url = ""
        self.desc = ""
        self.photo = ""
        self.price = 0
        self.year = 0
        self.power = 0
        self.enginesize = 0
        self.petrol = ""
        self.car_body = ""
        self.milage = 0
        self.condition = ""
        self.transmission = ""
        self.price_per_m = 0
        self.floor_select = ""
        self.furniture = ""
        self.market = ""
        self.area = 0
        self.rooms = ""
        self.builttype = ""
        self.old_price = ""

    def __repr__(self):
        return f'DIV: {self.title} {str(self.price)} zł {self.url}'

    def __eq__(self, other):
        return (self.title == other.title) & \
            (self.price == other.price) & \
            (self.desc == other.desc)
    
    __tablename__ = "div"
    div_id = Column(Integer, primary_key=True)
    title = Column(String, default="")
    url = Column(String, default="")
    desc = Column(String, default="")
    photo = Column(String, default="")
    price = Column(Integer, default=0)
    year = Column(Integer, default=0)
    power = Column(Integer, default=0)
    enginesize = Column(Integer, default=0)
    petrol = Column(String, default="")
    car_body = Column(String, default="")
    milage = Column(Integer, default=0)
    condition = Column(String, default="")
    transmission = Column(String, default="")
    price_per_m = Column(Float, default=0)
    floor_select = Column(String, default="")
    furniture = Column(String, default="") # umeblowane
    market = Column(String, default="") # rynek wtórny/nowe
    area = Column(Float, default=0)
    rooms = Column(String, default="")
    builttype = Column(String, default="") # rodzaj zabudowy: blok/
    old_price = Column(String, default="")
    created = Column(DateTime)
    saved = Column(DateTime)
    edited = Column(DateTime)
    address_id = Column(Integer, ForeignKey("address.address_id"))

class Address(Base):
    def __repr__(self):
        return f'ID: {self.address_id} ADRES: {self.city}'

    __tablename__ = "address"
    address_id = Column(Integer, primary_key=True)
    city = Column(String)
    lat = Column(Float)
    lon = Column(Float)
    distance = Column(Float)
    region = Column(String)
    divs = relationship("Div", backref=backref("div"))

Base.metadata.create_all(engine)

def calculate_dis(lat, lon):
    R = 6373.0

    lat1 = math.radians(lat)
    lon1 = math.radians(lon)
    lat2 = math.radians(51.7768349)
    lon2 = math.radians(22.5547575)

    dlon = lon2 - lon1
    dlat = lat2 - lat1

    a = math.sin(dlat / 2) ** 2 + math.cos(lat1) * math.cos(lat2) * math.sin(dlon / 2) ** 2
    c = 2 * math.atan2(math.sqrt(a), math.sqrt(1 - a))

    distance = R * c
    return distance

class Skrap_API:
    def __init__(self):
        self.start_time = datetime.datetime.now().replace(microsecond=0)
        self.all_added = 0
        self.all = 0
        self.price_changed = 0
    def get_page(self, js):
        page_added = 0
        for ad in js["data"]:
            address = Address()
            address.lat = ad['map']['lat']
            address.lon = ad['map']['lon']
            address.distance = calculate_dis(address.lat, address.lon)
            address.city = ad['location']['city']['name']
            address.region = ad['location']['region']['name']
            old_address = (
                session.query(Address)
                .filter(Address.lat == address.lat, Address.lon == address.lon)
                .first()
            )
            if old_address is None:
                session.add(address)
                session.commit()
                session.refresh(address)
            else:
                address.address_id = old_address.address_id
            div = Div()
            div.title = ad['title'].replace("\\", "")
            div.url = ad['url']
            div.desc = ad['description'].replace("<p>","").replace("</p>"," ").replace("<br />", " ").strip()
            try:
                div.photo = ad['photos'][0]['link'].split(';')[0] + ';s=240x240'
            except:
                div.photo = ""
            for i in ad['params']:
                match i['key']:
                    case 'price':
                        div.price = i['value']['value']
                    case 'year':
                        div.year = int(i['value']['key'])
                    case 'enginepower':
                        div.power = int(i['value']['key'])
                    case 'petrol':
                        div.petrol = i['value']['label']
                    case 'milage':
                        div.milage = int(i['value']['key'])
                    case 'condition':
                        div.condition = i['value']['label']
                    case 'transmission':
                        div.transmission = i['value']['key']
                    case 'enginesize':
                        div.enginesize = i['value']['key']
                    case 'car_body':
                        div.car_body = i['value']['key']
                    case 'price_per_m':
                        div.price_per_m = i['value']['key']
                    case 'floor_select':
                        div.floor_select = i['value']['label']
                    case 'furniture':
                        div.furniture = i['value']['label']
                    case 'market':
                        div.market = i['value']['label']
                    case 'm':
                        div.area = i['value']['key']
                    case 'rooms':
                        div.rooms = i['value']['label']
                    case 'builttype':
                        div.builttype = i['value']['label']
            if not div.price_per_m:
                try:
                    div.price_per_m = div.price/div.area
                except:
                    div.price_per_m = div.price
            div.saved = self.start_time
            div.edited = self.start_time
            div.created = datetime.datetime.strptime(ad['created_time'][:19], '%Y-%m-%dT%H:%M:%S')
            div.address_id = address.address_id

            old = (
                session.query(Div)
                .filter(Div == div)
                .first()
            )
            if old is None:
                price = (
                    session.query(Div)
                    .filter(Div.title == div.title, Div.address_id == div.address_id)
                    .first()
                )
                if price is None:
                    page_added += 1
                    self.all_added += 1
                    session.add(div)
                    session.commit()
                else:
                    price.old_price += f'{price.price} zł {price.edited.strftime("%d.%m %H:%M")} {price.url} <br>'
                    price.price = div.price
                    price.edited = self.start_time
                    self.price_changed += 1
                    session.commit()
            else:
                pass
                #old.edited = self.start_time
                #session.commit()

        self.all += len(js["data"])
        print(f'dodano: {page_added} z {len(js["data"])}')
        print(f'wszystkich dodanych {self.all_added} z {self.all}; zmieniona cena w {self.price_changed}')
        return page_added
    def get_data(self, link):
        offset = 0
        headers = {'User-Agent': 'Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:109.0) Gecko/20100101 Firefox/109.0'}
        response = requests.get(link, headers=headers)
        total_elements = response.json()["metadata"]['total_elements']
        print(total_elements, f' - {link}')
        i = 0
        added = 1
        while added != 0 and offset < total_elements:
            data = response.json()
            added = self.get_page(data)
            print(f'i: {i} offset: {offset}')
            time.sleep(1)
            i += 1
            offset = offset + 40
            response = requests.get(link.replace("offset=0", f'offset={offset}'), headers=headers)
    def get_fb(self, link):            
        f = SkrapFB()
        divs = f.wyciaganie_danych(link)
        self.create_from_fb(divs)
    def create_from_fb(self, divs):
        page_added = 0
        for d in divs:
            address = Address()
            address.city = d['city']
            address.distance = 0
            old_address = (
                    session.query(Address)
                    .filter(Address.city == address.city)
                    .first()
                )
            if old_address is None:
                session.add(address)
                session.commit()
                session.refresh(address)
                address_id = address.address_id
            else:
                address_id = old_address.address_id

            div = Div()
            div.address_id = address_id
            div.title = d['desc']
            div.url = d['url']
            div.price = d['price']
            div.photo = d['photo']
            div.saved = self.start_time
            div.created = self.start_time
            old = (
                session.query(Div)
                .filter(Div == div)
                .first()
            )
            if old is None:
                page_added += 1
                self.all_added += 1
                session.add(div)
                session.commit()
        print(f'dodano: {page_added} z {len(divs)}')
    def remove_old(self):
        print(self.start_time)
        divs = session.query(Div).filter(Div.edited != self.start_time)
        for i in divs:
            print(i)
        if input("delete y/n?") == "y":
            divs.delete()
            session.commit()
    def create_website(self, filter):
        fil = "/skrap.html"
        if filter == "all":
            divs = session.query(Div, Address).join(Address, Div.address_id == Address.address_id) \
            .filter(Address.distance <= 35) \
            .order_by(Div.price).all.join(Address, Div.address_id == Address.address_id)()
        if filter == "new":
            divs = session.query(Div, Address).join(Address, Div.address_id == Address.address_id) \
                .filter(Div.edited == self.start_time) \
                .order_by(Div.price).all()
        if filter == "fb":
            divs = session.query(Div, Address).join(Address, Div.address_id == Address.address_id) \
                .filter(Div.saved == self.start_time) \
                .order_by(Div.price).all()
        with open(fil, "w") as f:
            f.write('''
    <style>
        .ad{
            padding: 10px 20px 0px 20px;
            width: 90vw;
            clear: left;
        }
        img {
            float: left;
            padding-right: 25px;
        }
        .a {
            padding-bottom: 10px;
        }
        .price{
            font-weight: bold;
            font-size: larger;
            float: right;
        }
        .oldprice{
            color: #ff0000;
            font-weight: bold;
            float: right;
        }
        .city{
            clear: left;
            font-weight: bold;
        }
        .bar{
            padding-bottom: 5px;
        }
        ul {
            display: flex;
            flex-wrap: wrap;
            padding: 0px;
            margin: 0px;
        }
        li {
            list-style: none;
            margin: 0px 8px 0px 0px;
            border: 1px solid rgb(64, 99, 103);
            border-radius: 4px;
            padding: 0px 5px;
        }    
    </style>
            ''')
            x = 0
            for div, address in divs:
                f.write(
                    f'<div class="ad"><div class="bar">Wystawiono {(self.start_time - div.created).days}'
                    f' dni temu. {div.created} ID {div.div_id}</div><img src={div.photo}><div class="a">'
                    f'<a href={div.url}>{div.title}</a></div>'
                    f'<div class="desc">{div.desc}</div><div class="price">{div.price:_} zł</div>'                    
                    f'<ul><li><p>Piętro: {div.floor_select}</p></li><li><p>{div.price_per_m} zł/m2</p></li><li><p>{div.area} m2</p></li><li><p> Umeb: {div.furniture}</p></li>'
                    f'<li><p>Rynek {div.market}</p></li><li><p>Zab: {div.builttype}</p></li><li><p>Pokoje: {div.rooms}</p></li>'
                    f'<li><p>{address.city}</p></li>'
                    f'<li><p>{round(address.distance)} km</p></li></ul>'
                    f'<div class="oldprice">{div.old_price}</div><div class="city">{x} {div.edited}</div></div>')
                    # f'<ul><li><p>Rok: {div.year}</p></li><li><p>{div.milage} km</p></li><li><p>{div.power} KM</p></li>'
                    # f'<li><p>{div.enginesize} cm³</p></li><li><p>{div.petrol}</p></li><li><p>{div.car_body}</p></li>'
                    # f'<li><p>{div.condition}</p></li><li><p>{div.transmission}</p></li><li><p>{address.city}</p></li>'
                x+=1
        #os.system(f'xdg-open {fil}')

def create_map():
    mapa = folium.Map(zoom_start=6)
    cities = session.query(Address).all()
    for city in cities:
        divs = session.query(Div).filter(Div.address_id == city.address_id).all()
        ahref = ""
        for div in divs:
            div.title = div.title.replace("\\", "")

            ahref+=f'<a href="{div.url}">{div.title} {div.price} zł<br></a>'
        if ahref != "":
            folium.Marker([city.lat, city.lon], popup=ahref).add_to(mapa)
    mapa.save('/maps.html')

if __name__ == "__main__":
    link=""
    s = Skrap_API()
    #s.get_fb(link)
    #s.create_website('fb')
    # s.get_data(link)
    s.create_website("new")