package org.example;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ChartHelper {

    public static void createOverallScoreChart(List<Theme> themes) {
        // Создание набора данных для общего графика
        CategoryDataset dataset = createOverallDataset(themes);

        // Создание графика
        JFreeChart chart = ChartFactory.createBarChart(
                "Общие оценки тем", // Заголовок
                "Оценка",          // Ось X
                "Темы",            // Ось Y
                dataset
        );

        // Настройка отображения названий тем
        CategoryPlot plot = chart.getCategoryPlot();
        CategoryAxis categoryAxis = plot.getDomainAxis();
        categoryAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_90);

        // Создание панели для отображения графика
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(800, 600));

        // Создание окна для отображения графика
        JFrame frame = new JFrame("График общих оценок тем");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(chartPanel);
        frame.pack();
        frame.setVisible(true);
    }

    public static void createDetailedScoreChart(List<Theme> themes) {
        // Создание набора данных для детального графика
        CategoryDataset dataset = createDetailedDataset(themes);

        // Создание графика
        JFreeChart chart = ChartFactory.createBarChart(
                "Детальные оценки по темам", // Заголовок
                "Оценка",                    // Ось X
                "Темы",                     // Ось Y
                dataset
        );

        // Настройка отображения названий тем
        CategoryPlot plot = chart.getCategoryPlot();
        CategoryAxis categoryAxis = plot.getDomainAxis();
        categoryAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_90);

        // Создание панели для отображения графика
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(800, 600));

        // Создание окна для отображения графика
        JFrame frame = new JFrame("График детальных оценок тем");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(chartPanel);
        frame.pack();
        frame.setVisible(true);
    }

    private static CategoryDataset createOverallDataset(List<Theme> themes) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        for (Theme theme : themes) {
            dataset.addValue(theme.getTotalScore(), "Общая оценка", theme.getName());
        }

        return dataset;
    }

    private static CategoryDataset createDetailedDataset(List<Theme> themes) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        for (Theme theme : themes) {
            List<Integer> studentScores = theme.getStudentScores(); // Получение оценок студентов

            // Добавление оценок каждого студента
            for (int i = 0; i < studentScores.size(); i++) {
                dataset.addValue(studentScores.get(i), "Студент " + (i + 1), theme.getName());
            }
        }

        return dataset;
    }
}