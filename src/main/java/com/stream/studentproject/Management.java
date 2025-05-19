package com.stream.studentproject;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import static java.util.stream.Collectors.averagingDouble;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.partitioningBy;
import static java.util.stream.Collectors.toList;

public class Management {

    public static void main(String[] args) {
        List<Student> students = List.of(
                new Student("Femi", 12, "JSS1", 47),
                new Student("Emma", 14, "JSS2", 79),
                new Student("Dave", 11, "JSS3", 29),
                new Student("Kemi", 10, "SS1", 91),
                new Student("Yinka", 16, "SS3", 75)
        );

        // 1. Filter student who passed (>= 50)
        List<Student> studentWhoPassed50 = students.stream()
                .filter(s -> s.getScore() >= 50)
                .collect(toList());
        studentWhoPassed50.forEach(System.out::println);
        System.out.println();

        // 2 List students sorted by score (descending)
        List<Student> studentScore = students.stream()
                .sorted((a, b) -> Double.compare(b.getScore(), a.getScore()))
                .collect(toList());
        studentScore.forEach(System.out::println);
        System.out.println();

        // 3 student with the highest score
        Optional<Student> studentHighestScore = students.stream()
                .max(Comparator.comparingDouble(Student::getScore));
        studentHighestScore.ifPresent(highScore -> System.out.println("Highest score: " + highScore));
        System.out.println();

        // 4 Group students by grade level
        Map<String, List<Student>> studentGradeLevel = students.stream()
                .collect(groupingBy(Student::getGradeLevwl));
        studentGradeLevel.forEach((s, l) -> {
            System.out.println(s + ": ");
            l.forEach(System.out::println);
        });
        System.out.println();

        // 5 Get average score per grade level
        Map<String, Double> averageScoreByGrade = students.stream()
                .collect(groupingBy(Student::getGradeLevwl, averagingDouble(Student::getScore)));
        averageScoreByGrade.forEach((s, d) -> System.out.println(s + ": " + d));
        System.out.println();

        // 6 List names of students in uppercase
        List<String> studentInUpperCase = students.stream()
                .map(u -> u.getName().toUpperCase())
                .collect(toList());
        studentInUpperCase.forEach(System.out::println);
        System.out.println();

        // 7 Count students older than 15
        long studentOlderThan15 = students.stream()
                .filter(age -> age.getAge() > 15)
                .count();
        System.out.println("Student older than 15: " + studentOlderThan15);

        // 8 Partition students into passed and failed
        Map<Boolean, List<Student>> passedAndFailed = students.stream()
                .collect(partitioningBy(score -> score.getScore() >= 60));
        System.out.println("Pass");
        passedAndFailed.get(true).forEach(System.out::println);
        System.out.println("Failed");
        passedAndFailed.get(false).forEach(System.out::println);
        System.out.println();

        // 9 Join all student names into a single string with commas
        String joinedName = students.stream()
                .map(Student::getName)
                .collect(Collectors.joining(", "));
        System.out.println(joinedName);

    }
}
