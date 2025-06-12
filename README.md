# Used Furniture App

## 🔹 Описание

Used Furniture App — веб-сервис для покупки и продажи подержанной мебели. Каждый пользователь может:

* регистрироваться и входить в аккаунт;
* добавлять, редактировать и удалять объявления;
* смотреть каталог и отвечать на сообщения;
* использовать личный кабинет;
* админы могут управлять пользователями и объявлениями.

## 🛠️ Технологии

* **Языки**: Kotlin (Backend), Java (Admin), HTML + CSS (Frontend)
* **Spring Boot**: Web, Security, Data JPA
* **Thymeleaf**: шаблонизация HTML
* **PostgreSQL**: база данных
* **Maven**: сборка и управление зависимостями
* **JWT**: аутентификация по токену

## Описание подразделов

### Роуты:

#### Общидоступные

* `/home` — главная
* `/catalog` — каталог товаров
* `/login`, `/register` — вход и регистрация

#### Аутентифицированные

* `/sales` — мои продажи
* `/sales/new`, `/sales/edit/{id}`, `/sales/update/{id}`
* `/account`, `/account/update` — кабинет
* `/messages` — сообщения

#### Админские

* `/admin` — дашборд админа
* `/admin/users`, `/admin/items`

### 💻 Структура `backend/src/main/`

```
kotlin/rksp/furniture/
├── controller/        # Контроллеры (Kotlin + Java)
├── model/             # Модели JPA
├── repository/        # Spring Data JPA репозитории
├── security/          # Jwt логика
├── service/           # Сервисы (UserDetails, AdminService на Java)
resources/
├── templates/         # HTML шаблоны (Thymeleaf)
├── static/css/        # Стили CSS
```

## 🔧 Сборка и запуск

### 1. Сборка проекта

```bash
./mvnw clean install
```

### 2. Запуск Spring Boot

```bash
./mvnw spring-boot:run
```

## 📆 Админ-панель

* `/admin` — общая статистика
* `/admin/users` — список юзеров, кнопки для изменения ролей, удаления
* `/admin/items` — объявления, удаление, просмотр состояния

## 🔢 Fuzz тестирование

* `src/main/kotlin/rksp/furniture/FuzzTester.java`
* подключение к `localhost:8080`

```bash
javac -d . FuzzTester.java
java rksp.furniture.FuzzTester
```

## 📓 TODO / Планы:

* [ ] Загрузка изображений к объявлениям
* [ ] Формат JSON API для мобильной версии
* [ ] Рейтинг продавцов
* [ ] CI/CD + Docker Compose

## 👤 Автор

Степанов Антон