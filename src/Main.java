import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String data = null;
        while (!Objects.equals(data, "q")) {
            System.out.print("введите стороку (для выхода q): ");
            data = scanner.nextLine();
            if (data != "q") {
                System.out.println(calculate(data));
            }
        }
    }

    public static String calculate(String input) {
        int operationResult = 0;
        MathOperationType operationType = null;
        int value1 = 0;
        int value2 = 0;

        RomanDecimalConverter converter = new RomanDecimalConverter();

        // определение типа операции
        for (MathOperationType operation : MathOperationType.values()) {
            if (input.contains(operation.type)) {
                operationType = operation;
            }
        }
        // если операция не соответствует указнным выбрасываем исключение
        if (operationType == null) throw new RuntimeException("Не верный тип операции");

        String values[] = input.split(operationType.expr);
        
        // если операция не соответствует формату
        if (values.length > 2) throw new RuntimeException("Не верный формат операции");

        // проверка чисел на нахождение в одной системе исчислений
        if( converter.isRomanNumber(values[0].trim()) != converter.isRomanNumber(values[0].trim())) throw new RuntimeException("все числа должны быть или римскими или арабскими");

        Boolean isRoman = converter.isRomanNumber(values[0].trim());

        if (isRoman) {
            value1 = converter.toDecimal(values[0].trim());
            value2 = converter.toDecimal(values[1].trim());
        } else {
            try {
                value1 = Integer.parseInt(values[0].trim());
                value2 = Integer.parseInt(values[1].trim());
            } catch (Exception e) {
                System.out.println("не удалось преобразовать числа в десятичные!");
            }
        }

        if (value1 > 10 || value1 < 1 || value2 > 10 || value2 < 1) throw new RuntimeException("все числа должны быть в диапазоне 1 - 10");

        switch (operationType) {
            case PLUS:
                operationResult = value1 + value2;
                break;
            case MINUS:
                operationResult = value1 - value2;
                break;
            case DIVISION:
                operationResult = value1 / value2;
                break;
            case MULTIPLICATION:
                operationResult = value1 * value2;
                break;
        }

        if ( isRoman) {
            if (operationResult <= 0 ) throw new RuntimeException("результат операции с римскими числами не может быть отрицательным или равным нулю");
            return converter.toRoman(operationResult);

        } else {
            return Integer.toString(operationResult);
        }

    }

    enum MathOperationType {
        PLUS("+", "\\+"), MINUS("-", "-"), DIVISION("/", "/"), MULTIPLICATION("*", "\\*");

        final String type; // для поиска операции в строке
        final String expr;  // для деления строки с помощью выражения

        MathOperationType(String type, String expr) {
            this.type = type;
            this.expr = expr;
        }
    }
}