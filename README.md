Сервис - UrlShortCut
=========================================



**О проекте:**
==
Проект представляет собой сервис быстрого доступа к URL-адресам.
Пользователь регистрирует свой сайт, конвертирует свои собственные ссылки с помощью данного сервиса и вставляет их в любом месте.
Перенаправление происходит через данный сервис, который подсчитывает клики по ссылкам.

**Используемые технологии:**
==
- Java 12
- Spring Boot 2
- Spring Security & JWT authorization
- Spring Data JPA
- PostgreSQL

**Как запустить приложение через docker-compose:**
==
- Собрать образ приложения:
```
docker build -t shortcut .
```

- Запустить кластер базы данных в отдельном контейнере:
```
docker run -d --name postgres \
-e POSTGRES_PASSWORD=password \
-e PGDATA=/var/lib/postgresql/data/pgdata \
-p 5433:5432 \
-v postgres_db:/var/lib/postgresql/data \
postgres
```

- Создать базу данных для приложения:
  + Подключиться к кластеру изнутри контейнера:
    ```
    docker exec -it postgres bash
    ```
  + Открыть psql:
    ```
    psql -U postgres
    ```
  + Создать базу данных:
    ```
    CREATE DATABASE shortcut;
    ```
- Запустить скрипт docker-compose:
```
docker-compose up
```

- Запустить приложение:
Для проверки работы приложения можно воспользоваться Postman, url по умолчанию - http://127.0.0.1:80.
Примеры запросов см. ниже.

**REST API запросы:**
==
Регистрация нового сайта, получение логина и пароля
![ScreenShot](images/2021-11-04_233326.png)

Вход в систему под логином и паролем, получение токена
![ScreenShot](images/2021-11-04_233617.png)

Список зарегистрированных сайтов
![ScreenShot](images/2021-11-04_233815.png)

Генерация ссылки
![ScreenShot](images/2021-11-04_234332.png)

Переадресация с использованием новой ссылки
![ScreenShot](images/2021-11-04_234704.png)

Статистика переходов по сгенерированным ссылкам
![ScreenShot](images/2021-11-04_234812.png)

**Контакты:**
==
[![telegram](https://img.shields.io/badge/Telegram-gray?style=for-the-badge&logo=Telegram&logoColor=white)](https://t.me/pavlovilyaru)
[![email](https://img.shields.io/badge/Mail.Ru-blue?style=for-the-badge&logo=Mail.Ru&logoColor=white)](mailto:ilya.pavlov@list.ru)
