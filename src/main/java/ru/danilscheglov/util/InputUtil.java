package ru.danilscheglov.util;

import java.util.Scanner;

/**
 * Утилитный класс для безопасного считывания данных из консоли
 *
 * @author Danil Scheglov
 */
public final class InputUtil {

    public static int readInt(Scanner scanner, String promptMessage) {
        while (true) {
            System.out.print(promptMessage);
            String input = scanner.nextLine();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("[Ошибка] Неверный формат! Пожалуйста, вводите только цифры.");
                System.out.println("--------------------------------------------------");
            }
        }
    }
}
