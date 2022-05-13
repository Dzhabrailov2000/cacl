package com.kata.calc;

public class Main {
    public static String calc(String input) {
        String[] operands = input.split("[+-/*]");
        int result = 0;
        try {
            int op1 = Integer.parseInt(operands[0]);
            int op2 = Integer.parseInt(operands[1]);
            if (op1 < 1 || op1 > 10 || op2 < 1 || op2 > 10) {
                throw new ArrayIndexOutOfBoundsException();
            }
            if (operands.length > 2) {
                throw new IndexOutOfBoundsException();
            }

            String[] op = input.split("[0-10]");
            String c = op[op.length - 1];

            switch (c) {
                case "+" -> result = op1 + op2;
                case "-" -> result = op1 - op2;
                case "/" -> result = op1 / op2;
                case "*" -> result = op1 * op2;
            }
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
            System.out.println("throws Exception //т.к. строка не является математической операцией");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("throws Exception //т.к. формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
        }
        return "Ваш результат: " + result;
    }


    public static void main(String[] args) {
        System.out.println(calc("1+11"));
    }
}
