public class Calculator {
    private final char[] acts = {'+', '-', '*', '/'};

    public String execute(String data) {
        char act = findAct(data);
        int pos = data.indexOf(act);

        String leftStringArgument = data.substring(0, pos).trim();
        String rightStringArgument = data.substring(pos + 1).trim();

        String leftArgumentName = "Первый операнд";
        String rightArgumentName = "Второй операнд";

        boolean leftArgumentIsRoman = checkStringArgument(leftStringArgument, leftArgumentName);
        boolean rightArgumentIsRoman = checkStringArgument(rightStringArgument, rightArgumentName);

        if (leftArgumentIsRoman != rightArgumentIsRoman) {
            throw new ArithmeticException("Оба операнда должны быть либо арабскими, либо римскими числами!");
        }

        RomanNumber leftArgument = leftArgumentIsRoman ? new RomanNumber(leftStringArgument) :
                new RomanNumber(Integer.parseInt(leftStringArgument));
        if (leftArgument.getArabic() > 10) {
            throw new ArithmeticException(leftArgumentName + ": значение больше 10!");
        }

        RomanNumber rightArgument = rightArgumentIsRoman ? new RomanNumber(rightStringArgument) :
                new RomanNumber(Integer.parseInt(rightStringArgument));
        if (rightArgument.getArabic() > 10) {
            throw new ArithmeticException(rightArgumentName + ": значение больше 10!");
        }

        int result = performAnAction(leftArgument.getArabic(), rightArgument.getArabic(), act);

        if (leftArgumentIsRoman) {
            if (result <= 0) {
                throw new ArithmeticException("" + result + " - невозможно представить римским числом!");
            }
            return "" + new RomanNumber(result);
        }

        return "" + result;
    }

    private int performAnAction(int leftArabic, int rightArabic, char act) {
        switch (act) {
            case '+': return leftArabic + rightArabic;
            case '-': return leftArabic - rightArabic;
            case '*': return leftArabic * rightArabic;
            case '/': return leftArabic / rightArabic;
        }
        return 0;
    }

    private boolean checkStringArgument(String argument, String name) {
        if (argument.length() == 0) {
            throw new ArithmeticException(name + ": пустой!");
        }
        if (isAllLegalLettersInStringArgument(argument, RomanNumber.ARABIC_LETTERS)) {
            if (Integer.parseInt(argument) == 0) {
                throw new ArithmeticException(name + ": равен 0!");
            }
            return false;
        } else if (isAllLegalLettersInStringArgument(argument, RomanNumber.ROMAN_LETTERS)) {
            return true;
        }
        throw new ArithmeticException(name + ": неправильный формат!");
    }

    private boolean isAllLegalLettersInStringArgument(String argument, String letters) {
        for (int i = 0; i < argument.length(); i++) {
            char c = argument.charAt(i);
            if (letters.indexOf(c) == -1) {
                return false;
            }
        }
        return true;
    }

    private char findAct(String data) {
        for (char act: acts) {
            if (data.indexOf(act) != -1) {
                return act;
            }
        }
        throw new ArithmeticException("Нет операции в выражении!");
    }
}
