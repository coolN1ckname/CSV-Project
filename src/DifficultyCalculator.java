class DifficultyCalculator {
    private String name;
    private List<Integer> grades;

    public DifficultyCalculator(Theme theme) {
        this.name = theme.getName();
        this.grades = theme.getGrades();
    }

    public String getName() {
        return name;
    }

    public List<Integer> getGrades() {
        return grades;
    }
}
