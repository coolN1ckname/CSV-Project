import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Theme {
    private String name;
    private List<Task> tasks;
    private List<Integer> grades;

    public Theme(String name, List<Task> tasks, List<Integer> grades) {
        this.name = name;
        this.tasks = tasks;
        this.grades = grades;
    }

    public String getName() {
        return name;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public List<Integer> getGrades() {
        return grades;
    }

    public double getAverageGrade() {
        if (grades.isEmpty()) return 0;
        double sum = 0;
        for (int grade : grades) {
            sum += grade;
        }
        return sum / grades.size();
    }

    public static List<Theme> parseCSV(String csvFile) {
        List<Theme> themes = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String line;
            boolean isFirstLine = true;
            List<Task> tasks = new ArrayList<>();
            List<Integer> grades = new ArrayList<>();
            String themeName = "";

            while ((line = br.readLine()) != null) {
                String[] values = line.split(";");

                // Обработка первой строки (название темы и задачи)
                if (isFirstLine) {
                    themeName = values[0].replace(";", "").trim(); // Удаляем лишние символы
                    for (int i = 1; i < values.length; i++) {
                        if (!values[i].isEmpty()) {
                            tasks.add(new Task(values[i].trim())); // Создание задач
                        }
                    }
                    isFirstLine = false;
                } else {
                    // Обработка остальных строк (оценки)
                    if (values.length > 0) {
                        for (int i = 3; i < values.length; i++) { // Предполагается, что оценки начинаются с 4-го элемента
                            if (!values[i].isEmpty()) {
                                grades.add(Integer.parseInt(values[i].trim())); // Добавляем оценки
                            }
                        }
                    }
                }
            }

            // Создание объекта Theme
            themes.add(new Theme(themeName, tasks, grades));

        } catch (IOException e) {
            e.printStackTrace();
        }

        return themes;
    }
}
class Task {
    private String name;

    public Task(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}


