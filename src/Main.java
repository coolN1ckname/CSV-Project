import java.util.*;

class Main {
    public static void main(String[] args) {
        List<String> themeNames = Arrays.asList(
                "1. Введение в Java",
                "2. Базовый синтаксис. Типы",
                "3. Массивы и управляющие конструкции",
                "4. ООП. Основы",
                "5. ООП. Наследование. Абстракции",
                "6. Обработка ошибок, исключения, отладка",
                "7. Ввод-вывод, доступ к файловой системе",
                "8. Ввод-вывод, продвинутые возможности",
                "9. Обобщения. Коллекции",
                "10. Функциональные интерфейсы. Stream API",
                "11. Многопоточность",
                "12. Тестирование",
                "13. Практика"
        );

        // Индексы, которые нужно извлечь для каждой темы
        int[][] indicesToExtract = {
                {0}, // Для темы 1
                {7, 11, 13}, // Для темы 2
                {19, 21, 24}, // Для темы 3
                {29, 32, 35}, // Для темы 4
                {43, 42, 48}, // Для темы 5
                {53, 55, 57}, // Для темы 6
                {63, 66, 69}, // Для темы 7
                {80, 82, 84}, // Для темы 8
                {88, 90}, // Для темы 9
                {97, 99}, // Для темы 10
                {105}, // Для темы 11
                {108}, // Для темы 12
                {110}  // Для темы 13
        };

        List<Theme> themes = Theme.parseCSV("C:\\Users\\Moji\\Desktop\\java-rtf.csv", themeNames, indicesToExtract);

        // Сортировка тем по общей оценке в порядке возрастания
        themes.sort(Comparator.comparingInt(Theme::getTotalScore));

        System.out.println("Темы, отсортированные по общей оценке:");
        for (Theme theme : themes) {
            System.out.println(theme.getName() + " - " + theme.getTotalScore() + " баллов");
        }

        // Вставки в БД

        DatabaseHelper.insertThemes(themes);

        // Подсчет общего числа баллов
        int totalScore = themes.stream().mapToInt(Theme::getTotalScore).sum();
        System.out.println("Общее количество баллов: " + totalScore);
    }
}