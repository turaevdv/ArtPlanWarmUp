package main;

import java.util.Scanner;
import java.util.function.Function;

public class Main {
    public static String reverse(String str) {
        char[] charArray = str.toCharArray();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = str.length() - 1; i >= 0; --i) {
            stringBuilder.append(charArray[i]);
        }
        return stringBuilder.toString();
    }

    public static <T, R> double countingFunctionTimeInMicroseconds(Function<T, R> method, T parameter, int numberOfRepetitions) {
        long totalTime = 0;
        for (int i = 0; i < numberOfRepetitions; i++) {
            long start = System.nanoTime();
            method.apply(parameter);
            long finish = System.nanoTime();
            totalTime += finish - start;
        }
        return ((double) totalTime / numberOfRepetitions) / 1000;
    }

    public static void main(String[] args) {
        String str;
        System.out.println("Введите строку");

        try(Scanner in = new Scanner(System.in)) {
            str = in.nextLine();
        } catch (Exception e) {
            System.out.println("Ошибка при вводе строки");
            return;
        }

        System.out.println("Исходная строка: " + str);
        System.out.println("Перевернутая строка: " + reverse(str) + "\n");

        for (int numberOfRepetitions = 1000; numberOfRepetitions <= 100_000; numberOfRepetitions *= 10) {
            System.out.println("Время работы метода на " + numberOfRepetitions + " повторений: "
                    + countingFunctionTimeInMicroseconds(Main::reverse, str, numberOfRepetitions) + " мкс");
        }
    }
}