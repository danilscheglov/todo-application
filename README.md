# Список дел (Todo Application)

Десктопное приложение для управления задачами.

## Стек технологий

- **Язык:** Java 21
- **GUI:** JavaFX 21
- **База данных:** PostgreSQL
- **Доступ к данным:** JDBC, HikariCP
- **Сборка:** Apache Maven

## Установка и запуск

1. Клонируйте репозиторий:

```bash
git clone https://github.com/danilscheglov/todo-application
cd todo-application
```

2. Настройте переменные окружения и конфигурацию приложения:

Скопируйте шаблоны конфигурации в локальные файлы и при необходимости измените параметры подключения:

```bash
cp .env.example .env
cp src/main/resources/application.properties src/main/resources/application-dev.properties
```

3. Запустите базу данных PostgreSQL в Docker:

При первом запуске схема таблиц из `src/main/resources/db/schema.sql` применится автоматически:

```bash
docker compose up -d
```

4. Запустите приложение

```bash
./mvnw clean javafx:run
```