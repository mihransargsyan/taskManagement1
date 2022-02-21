package manager;

import db.DBConnectionProvider;
import model.Task;
import model.TaskStatus;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class TaskManager {

    private Connection connection = DBConnectionProvider.getInstance().getConnection();
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    private UserManager userManager = new UserManager();

    public void add(Task task) {
        String sql = "INSERT INTO task(name,description,deadline,status,user_id) VALUES(?,?,?,?,?)";
        try {
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, task.getName());
            statement.setString(2, task.getDescription());
            statement.setString(3, sdf.format(task.getDeadline()));
            statement.setString(4, task.getTaskStatus().name());
            statement.setInt(5, task.getUserId());
            statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                task.setId(rs.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void upDateTaskStatus(int taskId, String newStatus) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE task set status = ? where  id = ?");
            preparedStatement.setString(1, newStatus);
            preparedStatement.setInt(2, taskId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public List<Task> getAllTasks() {
        List<Task> tasks = new ArrayList<>();
        String sql = "SELECT * FROM task";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Task task = new Task();
                task.setId(resultSet.getInt("id"));
                task.setName(resultSet.getString("name"));
                task.setDescription(resultSet.getString("description"));
                try {
                    task.setDeadline(sdf.parse(resultSet.getString("deadline")));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                task.setTaskStatus(TaskStatus.valueOf(resultSet.getString("status")));
                task.setUserId(resultSet.getInt("user_id"));
                task.setUser(userManager.getUserById(task.getUserId()));
                tasks.add(task);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tasks;
    }

    public List<Task> getAllTasksByUserId(int id) {
        List<Task> tasks = new ArrayList<>();
        String sql = "SELECT * FROM task WHERE user_id =" + id;
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Task task = new Task();
                task.setId(resultSet.getInt("id"));
                task.setName(resultSet.getString("name"));
                task.setDescription(resultSet.getString("description"));
                try {
                    task.setDeadline(sdf.parse(resultSet.getString("deadline")));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                task.setTaskStatus(TaskStatus.valueOf(resultSet.getString("status")));
                task.setUserId(resultSet.getInt("user_id"));
                task.setUser(userManager.getUserById(task.getUserId()));
                tasks.add(task);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tasks;
    }

    public void deleteUserById(int id) {
        String sql = "DELETE  FROM task WHERE id = ? ";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}


