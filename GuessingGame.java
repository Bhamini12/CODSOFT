import java.util.Scanner;
import java.util.Random;

public class GuessingGame {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        int maxAttempts = 10, score = 0;
        String playAgain;

        do {
            int num = random.nextInt(100) + 1; // Generate random number (1-100)
            boolean correct = false;

            for (int i = 1; i <= maxAttempts; i++) {
                System.out.print("Guess #" + i + ": ");
                int guess = scanner.nextInt();

                if (guess == num) {
                    correct = true;
                    score += maxAttempts - i + 1;
                    System.out.println("Congrats! Guessed in " + i + " attempts.");
                    break;
                } else if (guess < num) {
                    System.out.println("Too low. Try again.");
                } else {
                    System.out.println("Too high. Try again.");
                }
            }

            if (!correct) {
                System.out.println("Out of attempts. Number was: " + num);
            }

            do { // Validate play again input (y/n)
                System.out.print("Play again? (y/n): ");
                playAgain = scanner.next().toLowerCase(); // Convert to lowercase for case-insensitive check
            } while (!playAgain.equals("y") && !playAgain.equals("n"));

        } while (playAgain.equals("y")); // Continue if user enters "y"

        System.out.println("Thanks for playing! Your score is: " + score);
        scanner.close();
    }
}
