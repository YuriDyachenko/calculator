import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Calculator calculator = new Calculator();

        while (true) {
            System.out.print("Введите данные или exit для выхода: ");
            String inputString = scanner.nextLine();

            if (inputString.equalsIgnoreCase("exit")) {
                break;
            }

            System.out.println("  результат: " + calculator.execute(inputString));
        }

        System.out.println("Пока...");
        scanner.close();
    }
}
