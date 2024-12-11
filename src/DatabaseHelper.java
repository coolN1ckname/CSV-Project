import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class DatabaseHelper {
    private static final String URL = "jdbc:postgresql://localhost:5432/Java_Project";
    private static final String USER = "postgres";
    private static final String PASSWORD = "29v07j13n";

    public static void insertThemes(List<Theme> themes) {
        String insertSQL = "INSERT INTO themes (name, total_score) VALUES (?, ?)";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(insertSQL)) {

            for (Theme theme : themes) {
                preparedStatement.setString(1, theme.getName());
                preparedStatement.setInt(2, theme.getTotalScore());
                preparedStatement.addBatch();
            }

            preparedStatement.executeBatch(); // Выполнение вставок
            System.out.println("Данные успешно вставлены в БД.");
        } catch (SQLException e) {
            System.err.println("Ошибка при вставке: " + e.getMessage());
        }
    }
}
