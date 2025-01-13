# Streamify

## 📌 Описание проекта

**Streamify** – это серверная часть новостного сервиса, вдохновленного функционалом "ВКонтакте" и др. популярных сервисов. Проект реализует возможности подписок, репостов, лайков, комментариев и отображения ленты новостей.

## 🚀 Функциональность

- **Регистрация и аутентификация пользователей** (JWT)
- **Подписка и отписка** на пользователей
- **Создание постов** с медиафайлами
- **Репосты** публикаций
- **Лайки и комментарии** к постам
- **Уведомления** о действиях пользователей
- **Новостная лента** с сортировкой по дате и популярности

---

## 🛠️ Стек технологий

**Backend**: Java 21, Spring Boot, Docker
- **Database**: PostgreSQL
- **Security**: JWT, Spring Security
- **ORM**: Spring Data JPA, Spring Data JDBC
- **API Documentation**: Swagger/OpenAPI
- **Dependency Management**: Lombok
- **REST Framework**: Spring MVC, OpenFeign
- **XML Support**: JAXB API
- **SSH Support**: JSch

---


---

## 📌 Основные эндпоинты API

### 🔑 Аутентификация
- `POST /api/auth/register` – Регистрация пользователя
- `POST /api/auth/login` – Аутентификация и получение JWT

### 👤 Пользователи
- `GET /api/users/{id}` – Просмотр профиля
- `POST /api/users/{id}/follow` – Подписка
- `POST /api/users/{id}/unfollow` – Отписка

### 📝 Посты
- `POST /api/posts` – Создать пост
- `POST /api/posts/{id}/repost` – Репост
- `GET /api/posts/{id}` – Получить пост
- `DELETE /api/posts/{id}` – Удалить пост

### ❤️ Лайки и 💬 Комментарии
- `POST /api/posts/{id}/like` – Поставить лайк
- `DELETE /api/posts/{id}/like` – Удалить лайк
- `POST /api/posts/{id}/comments` – Добавить комментарий
- `DELETE /api/comments/{id}` – Удалить комментарий

### 🔔 Уведомления
- `GET /api/notifications` – Получить список уведомлений

### 📰 Новостная лента
- `GET /api/feed` – Получить ленту новостей

---
Вот обновленная версия с добавлением информации о запуске проекта через Docker Compose:

---

## 🚀 Запуск проекта

### 🔹 1. Клонирование репозитория
```sh
git clone https://github.com/Maxim2710/Streamify.git
cd Streamify
```

### 🔹 2. Запуск сервера

#### Через Maven
```sh
mvn spring-boot:run
```
После успешного запуска API будет доступно по адресу `http://localhost:8080`.

#### Через Docker Compose
1. Убедитесь, что у вас установлен Docker и Docker Compose.
2. Запустите команду:
   ```sh
   docker-compose up -d
   ```
3. После успешного запуска API будет доступно по адресу `http://localhost:8080`.

---

## ✅ Тестирование API
Проект включает Swagger-документацию, доступную по адресу:

```
http://localhost:8080/swagger-ui.html
```

Для тестирования API можно использовать **Postman** или **cURL**.

---

## 📌 Контакты
📧 Если у вас есть вопросы или предложения, свяжитесь со мной:
- GitHub: [Maxim2710](https://github.com/Maxim2710)
- Email: pm2710@mail.ru

