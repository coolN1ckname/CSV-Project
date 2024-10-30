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

    }
}

