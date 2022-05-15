package com.kata.calc;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Main {
    public static String calc(String inputString) {
        String input = inputString.toUpperCase();
        String[] operands = input.split("[+-/*]");//вытаскиваем операторы
        String[] romanNums = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX,", "X"};
        String result = "0";
        int left = 0;
        int right = 0;
        try {
            loop:
            if (operands.length == 2) {
                for (int i = 0; i < romanNums.length; i++) {
                    if (romanNums[i].contains(operands[0]) || romanNums[i].contains(operands[1])) {
                        String op = getOpString(input);
                        left = romanToInteger(operands[0]);
                        right = romanToInteger(operands[1]);
                        int res = calculate(left, right, op);
                        if (res < 1) {
                            throw new OutOfMemoryError();
                        } else {
                            result = integerToRoman(res);
                        }
                        break loop;
                    } else if (!(romanNums[i].contains(operands[0]) || romanNums[i].contains(operands[1]))) {
                        throw new RuntimeException();
                    }
                }
                if (!(Integer.parseInt(operands[0]) < 1 || Integer.parseInt(operands[0]) > 10 || Integer.parseInt(operands[1]) < 1 || Integer.parseInt(operands[1]) > 10)) {
                    String oper = getOpInteger(input);
                    int res = calculate(Integer.parseInt(operands[0]), Integer.parseInt(operands[1]), oper);
                    result = res + "";
                } else {
                    throw new RuntimeException();
                }
            } else if (operands.length > 2){
                throw new Exception();
            } else {
                int x = Integer.parseInt(operands[0]);
                throw new Exception();
            }
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            System.out.println("throws Exception //т.к. строка не является математической операцией");
        } catch (RuntimeException e) {
            System.out.println("throws Exception //т.к. формат математической операции не удовлетворяет заданию - один или оба операнда не в диапазоне 1 - 10");
        } catch (OutOfMemoryError e) {
            System.out.println("throws Exception //т.к. в римской системе счисления нет отрицательных чисел");
        } catch (Exception e) {
            System.out.println("throws Exception //т.к. формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
        }
        return "Congratulations! Your result: " + result;
    }

    static String integerToRoman(int num) {
        StringBuilder sb = new StringBuilder();
        int times = 0;
        String[] romans = new String[]{"I", "IV", "V", "IX", "X", "XL", "L",
                "XC", "C", "CD", "D", "CM", "M"};
        int[] ints = new int[]{1, 4, 5, 9, 10, 40, 50, 90, 100, 400, 500,
                900, 1000};
        for (int i = ints.length - 1; i >= 0; i--) {
            times = num / ints[i];
            num %= ints[i];
            while (times > 0) {
                sb.append(romans[i]);
                times--;
            }
        }
        return sb.toString();
    }

    static int romanToInteger(String roman) {
        Map<Character, Integer> numbersMap = new HashMap<>();
        numbersMap.put('I', 1);
        numbersMap.put('V', 5);
        numbersMap.put('X', 10);
        numbersMap.put('L', 50);
        numbersMap.put('C', 100);
        numbersMap.put('D', 500);
        numbersMap.put('M', 1000);

        int result = 0;

        for (int i = 0; i < roman.length(); i++) {
            char ch = roman.charAt(i);
            if (i > 0 && numbersMap.get(ch) > numbersMap.get(roman.charAt(i - 1))) {
                result += numbersMap.get(ch) - 2 * numbersMap.get(roman.charAt(i - 1));
            } else
                result += numbersMap.get(ch);
        }
        return result;
    }

    static int calculate(int x, int y, String op) {
        int result = 0;
        switch (op) {
            case "+" -> result = x + y;
            case "-" -> result = x - y;
            case "/" -> result = x / y;
            case "*" -> result = x * y;
        }
        return result;
    }

    static String getOpString(String string) {
        String regex = "(\\d+\\.\\d+)|(\\d+)|([+-/*/^])|([/()])";
        Matcher m = Pattern.compile(regex).matcher(string);
        LinkedList list = new LinkedList();
        while (m.find()) {
            list.add(m.group());

        }
        return (String) list.get(0);
    }

    static String getOpInteger(String string) {
        String[] op = string.split("\\d");
        return op[op.length - 1];
    }

    public static void main(String[] args) {
        System.out.println("Enter expression (2 operands and operator):");
        Scanner scanner = new Scanner(System.in);
        String inputString = scanner.nextLine().replace(" ", "");
        System.out.println(calc(inputString));
    }

}
//допилить исключение с римскими числами меньше 1 и больше 10
