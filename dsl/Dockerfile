FROM python:3.8-slim-buster

COPY ./requirements.txt /app/requirements.txt

WORKDIR /app

RUN pip install --upgrade pip
RUN pip install -r requirements.txt

COPY runtime-EclipseXtext/test/src-gen/rules /app

ENTRYPOINT [ "python" ]

CMD [ "app.py" ]