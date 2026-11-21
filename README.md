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

2. Подготовьте базу данных:

Создайте базу данных `todo_db` в PostgreSQL и примените схему таблиц из `src/main/resources/db/schema.sql`

```bash
psql -U postgres -d todo_db -f src/main/resources/db/schema.sql
```

3. Настройте подключение к БД:

Скопируйте шаблон конфигурации в локальный файл и укажите свои параметры подключения:

```bash
cp src/main/resources/application.properties src/main/resources/application-dev.properties
```

4. Запустите приложение

```bash
./mvnw clean javafx:run
```