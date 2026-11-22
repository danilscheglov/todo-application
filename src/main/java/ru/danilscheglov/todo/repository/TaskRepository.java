package ru.danilscheglov.todo.repository;

import ru.danilscheglov.todo.exception.DataAccessException;
import ru.danilscheglov.todo.model.Task;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TaskRepository {

    private static final String COLUMN_ID = "id";
    private static final String COLUMN_TITLE = "title";
    private static final String COLUMN_IS_COMPLETED = "is_completed";
    private static final String COLUMN_CREATED_AT = "created_at";

    private static final String FIND_ALL_SQL = """
            SELECT id, title, is_completed, created_at
            FROM tasks
            ORDER BY created_at DESC
            """;

    private static final String SAVE_SQL = """
            INSERT INTO tasks (title, is_completed)
            VALUES (?, ?)
            RETURNING id, title, is_completed, created_at
            """;

    private static final String UPDATE_COMPLETED_SQL = """
            UPDATE tasks
            SET is_completed = ?
            WHERE id = ?
            """;

    private static final String DELETE_BY_ID_SQL = """
            DELETE FROM tasks
            WHERE id = ?
            """;

    private final DataSource dataSource;

    public TaskRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    public List<Task> findAll() {
        List<Task> tasks = new ArrayList<>();

        try (Connection connection = dataSource.getConnection()){
            PreparedStatement statement = connection.prepareStatement(FIND_ALL_SQL);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                tasks.add(mapRow(resultSet));
            }

            return tasks;
        } catch (SQLException e) {
            throw new DataAccessException("Не удалось загрузить список задач из базы данных", e);
        }
    }

    public Task save(Task task) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(SAVE_SQL)) {

            statement.setString(1, task.title());
            statement.setBoolean(2, task.isCompleted());

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return mapRow(resultSet);
                }
                throw new DataAccessException("Не удалось сохранить задачу: база данных не вернула созданную запись");
            }
        } catch (SQLException e) {
            throw new DataAccessException("Не удалось сохранить задачу: «" + task.title() + "»", e);
        }
    }

    public void updateCompleted(Long id, boolean isCompleted) {
        try (Connection connection = dataSource.getConnection()){
            PreparedStatement statement = connection.prepareStatement(UPDATE_COMPLETED_SQL);

            statement.setBoolean(1, isCompleted);
            statement.setLong(2, id);
            statement.executeQuery();

        } catch (SQLException e) {
            throw new DataAccessException("Не удалось обновить статус задачи с id=" + id, e);
        }
    }

    public void deleteById(Long id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_BY_ID_SQL)) {

            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException("Не удалось удалить задачу с id=" + id, e);
        }
    }

    private Task mapRow(ResultSet rs) throws SQLException {
        Timestamp createdAt = rs.getTimestamp(COLUMN_CREATED_AT);
        return new Task(
                rs.getLong(COLUMN_ID),
                rs.getString(COLUMN_TITLE),
                rs.getBoolean(COLUMN_IS_COMPLETED),
                createdAt != null ? createdAt.toLocalDateTime() : null
        );
    }
}
