public class DifficultyCalculator {
    private String name;
    private Integer grades;

    public DifficultyCalculator(Theme theme) {
        this.name = theme.getName();
        this.grades = theme.getGrades();
    }

    public String getName() {
        return name;
    }

    public Integer getGrades() {
        return grades;
    }
}
