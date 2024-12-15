package org.example;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Theme {
    private String name;
    private int totalScore; // Общая оценка
    private List<Integer> studentScores; // Список оценок студентов

    public Theme(String name) {
        this.name = name;
        this.totalScore = 0;
        this.studentScores = new ArrayList<>(); // Инициализация списка оценок студентов
    }

    public String getName() {
        return name;
    }

    public void addScore(int score) {
        totalScore += score;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public List<Integer> getStudentScores() {
        return studentScores; // Возвращаем список оценок студентов
    }

    public void addStudentScore(int score) {
        studentScores.add(score); // Добавляем оценку студента в список
    }

    public static List<Theme> parseCSV(String csvFile, List<String> themeNames, int[][] indicesToExtract) {
        List<Theme> themes = new ArrayList<>();
        for (String themeName : themeNames) {
            themes.add(new Theme(themeName));
        }

        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(csvFile), StandardCharsets.UTF_8))) {
            String line;
            boolean isFirstLine = true;

            while ((line = br.readLine()) != null) {
                System.out.println("Обрабатываем строку: " + line); // Вывод отладочной строки
                String[] values = line.split(";");

                if (!isFirstLine) {
                    // Удаление первых 6 элементов
                    if (values.length > 6) {
                        String[] studentData = Arrays.copyOfRange(values, 6, values.length);

                        for (int i = 0; i < themes.size(); i++) {
                            int score = 0;

                            // Суммирование баллов по необходимым индексам
                            for (int index : indicesToExtract[i]) {
                                if (index < studentData.length) {
                                    score += parseScore(studentData[index]);
                                }
                            }

                            themes.get(i).addScore(score);
                            themes.get(i).addStudentScore(score); // Добавляем оценку студента
                            System.out.println("Текущий счёт для темы " + themes.get(i).getName() + ": " + score);
                        }
                    }
                }
                isFirstLine = false; // Пропуск первой строки
            }
        } catch (IOException e) {
            System.err.println("Ошибка при чтении файла: " + e.getMessage());
        }

        return themes;
    }

    private static int parseScore(String scoreStr) {
        try {
            return scoreStr.isEmpty() ? 0 : Integer.parseInt(scoreStr.trim());
        } catch (NumberFormatException e) {
            System.err.println("Недопустимое значение: " + scoreStr);
            return 0; // Возвращение 0 для недопустимых значений
        }
    }
}
