public class RomanNumber {
    public static final String ARABIC_LETTERS = "0123456789";
    public static final String ROMAN_LETTERS = "IVXLC";
    private final int[] numbers = {100, 90, 50, 40, 10, 9, 5, 4, 1};
    private final String[] letters = {"C",  "XC", "L",  "XL",  "X",  "IX", "V",  "IV", "I"};
    private final int arabic;
    private final String nameForErrors = "Римское число: ";

    public RomanNumber(int arabic) {
        if (arabic < 1) {
            throw new NumberFormatException(nameForErrors + "арабское число должно быть ненулевым положительным!");
        }
        if (arabic > 100) {
            throw new NumberFormatException(nameForErrors + "арабское число не должно быть больше 100!");
        }

        this.arabic = arabic;
    }

    public RomanNumber(String roman) {
        if (roman.length() == 0) {
            throw new NumberFormatException(nameForErrors + "строка не должна быть пустой!");
        }

        String upRoman = roman.toUpperCase();

        int i = 0;
        int arabic = 0;
        while (i < upRoman.length()) {
            char letter = upRoman.charAt(i);
            int number = letterToNumber(letter);

            if (number == -1) {
                throw new NumberFormatException(nameForErrors + "строка содержит недопустимый символ '" +
                        letter + "'!");
            }

            i++;
            if (i == upRoman.length()) {
                arabic += number;
            } else {
                int nextNumber = letterToNumber(upRoman.charAt(i));
                if (nextNumber > number) {
                    arabic += (nextNumber - number);
                    i++;
                } else {
                    arabic += number;
                }
            }
        }

        if (arabic > 100) {
            throw new NumberFormatException(nameForErrors + "арабское число не должно быть больше 100!");
        }

        this.arabic = arabic;
    }

    private int letterToNumber(char letter) {
        switch (letter) {
            case 'I': return 1;
            case 'V': return 5;
            case 'X': return 10;
            case 'L': return 50;
            case 'C': return 100;
            default: return -1;
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        int n = arabic;
        for (int i = 0; i < numbers.length; i++) {
            while (n >= numbers[i]) {
                stringBuilder.append(letters[i]);
                n -= numbers[i];
            }
        }
        return stringBuilder.toString();
    }

    public int getArabic() {
        return arabic;
    }
}
