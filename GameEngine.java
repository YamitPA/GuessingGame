import java.util.Random;
import java.util.HashSet;
import java.util.Set;
import java.util.ArrayList; 
import java.util.List; 


/**
 * The GameEngine class provides the core logic for a number-guessing game
 * in which the user tries to guess a randomly generated 4-digit number with unique digits.
 * 
 * The game works as follows:
 * 1. The system generates a random 4-digit target number with unique digits.
 * 2. The user is prompted to guess the number, entering a 4-digit number with unique digits.
 * 3. For each guess, feedback is provided based on the number of matching digits
 *    (called "Bulls" for correct digits in the correct positions and "Hits" for correct digits in incorrect positions).
 * 4. The game keeps track of the number of guesses and displays the results of each attempt.
 * 5. After a correct guess or if the player decides to stop, they are given the option to play again.
 * 
 * Main features:
 * - Generates and validates a unique 4-digit target number.
 * - Verifies user guesses and provides feedback in terms of "Bulls" and "Hits".
 * - Records all past guesses and displays them after each guess.
 * - Allows the user to restart the game or exit after each round.
 */




public class GameEngine {
	private static final int DIGIT_COUNT = 4; //constant for the number of digits.
    private int targetNum; //The number the user has to guess.
    private UserInterface ui;
    private int guessCount; //variable to count the guesses
    private List<String> previousGuesses; //A list to save the previous guesses
    
    /*
     * Constructs a new instance of the GameEngine class.
     * This constructor initializes the user interface and generates a new target number for the game.
     * 
     * The user interface (UserInterface) is created to handle user interactions, 
     * and a new target number is generated to start the game.
     */
    public GameEngine() {
        ui = new UserInterface();
        generateNewTarget();
        guessCount = 0; //Initializing the guess count.
        previousGuesses = new ArrayList<>(); //Initialize the list of previous guesses.
    }

    
    /*
     * Starts the main game loop.
     * 
     * This method manages the game's primary loop and user interactions:
     * 1. It displays a welcome message at the beginning of each game.
     * 2. It repeatedly prompts the user for guesses until the correct number is guessed.
     *    - Each guess is validated to ensure it is a 4-digit number with unique digits.
     *    - If valid, it checks the guess against the target number and provides feedback:
     *      * "Bulls" indicate correctly positioned digits.
     *      * "Hits" indicate correct digits in the wrong position.
     * 3. The user receives a summary of all past guesses and results for reference.
     * 4. After a successful guess, the user is asked if they would like to play again.
     *    - If yes, the game resets and generates a new target number.
     *    - If no, a farewell message is displayed, and the loop ends.
     */
    public void start() {
        boolean newGame = true;

        while (newGame) {
            ui.displayMessage("Welcome to the game!");
            boolean userGuess = false;

            while (!userGuess) {
                //request the new guess.
                StringBuilder messageBuilder = new StringBuilder("Please enter your guess (4-digit number with unique digits):\n");
                
                //Add all previous guesses to the display string.
                for (String previousGuess : previousGuesses) {
                    messageBuilder.append(previousGuess).append("\n");
                }

                String guess = ui.getUserInput(messageBuilder.toString());

                if (isValidGuess(guess)) {
                    guessCount++;
                    int[] results = checkGuess(Integer.parseInt(guess));
                    int bulls = results[0];
                    int hits = results[1];
                    
                    previousGuesses.add(guess + " -> Bulls: " + bulls + ", Hits: " + hits);

                    //Check if the user guessed correctly.
                    userGuess = (bulls == DIGIT_COUNT);
                    
                    //Update the final result if the guess was successful.
                    if (userGuess) {
                        ui.displayMessage("Congratulations! You guessed the right number in " + guessCount + " guesses!");
                    }
                } else {
                    ui.displayMessage("Invalid input! Make sure it's a 4-digit number with unique digits.");
                }
            }

            //Ask if the user wants to play again.
            newGame = ui.getUserInput("Would you like to play again? (If yes, enter 1, otherwise enter 0.)").equals("1");

            if (!newGame) {
                ui.displayMessage("Thank you for playing! Goodbye!");
            } else {
                generateNewTarget();
                guessCount = 0;
                previousGuesses.clear();
            }
        }
    }

    
    
    /*
     * Generates a new target number for the guessing game.
     * 
     * This method creates a random 4-digit number where all digits are unique.
     * It uses the Random class to generate a number between 1000 and 9999 
     * and checks if the number has unique digits using the hasUniqueDigits method.
     * Once a valid number is found, it sets it as the target number for the game.
     */
    private void generateNewTarget() {
        Random random = new Random();

        while (true) {
            int number = 1000 + random.nextInt(9000);

            if (hasUniqueDigits(number)) {
                targetNum = number;                
                break;
            }
        }
    }

    
    
    /*
     * Checks if the given number has all unique digits.
     *
     * This method takes a number as input and determines whether all its digits are unique.
     * It uses a HashSet to track the digits encountered. As it processes each digit from
     * right to left, it attempts to add each digit to the set. If a digit is already present
     * in the set, the method returns false, indicating that the number does not have unique digits.
     * If all digits are processed without duplicates, the method returns true.
     */
    private boolean hasUniqueDigits(int number) {
        Set<Integer> digits = new HashSet<>();
        while (number > 0) {
            int digit = number % 10;
            if (!digits.add(digit)) {
                return false;
            }
            number /= 10;
        }
        return true;
    }
    
    

    /*
     * Checks the accuracy of the user's guess by comparing it to the target number.
     *
     * This method evaluates the number of "bulls" and "hits" in the user's guess.
     * 
     * The guess is converted to a 4-digit string, ensuring it's comparable to the target number.
     * Each digit of the guess is compared to the corresponding digit in the target number.
     * For each match in both value and position, the "bulls" counter is incremented.
     * If a digit exists in the target number but in a different position, the "hits" counter is incremented.
     * 
     * If the number of bulls equals the target number length, the method outputs a success message.
     * Otherwise, it outputs the count of bulls and hits.
     */
    private int[] checkGuess(int guess) {
        int bulls = 0;
        int hits = 0;

     //Convert target number and guess to strings for easier comparison of individual digits.
        String targetStr = String.valueOf(targetNum);
        String guessStr = String.format("%04d", guess);//Ensures guess is a 4-digit string (pads with zeros if needed).

        for (int i = 0; i < DIGIT_COUNT; i++) {
            char guessedDigit = guessStr.charAt(i); //Current digit in the guess.

            //Check if the guessed digit is in the correct position and value.
            if (guessedDigit == targetStr.charAt(i)) {
                bulls++;
            }
            //Check if the guessed digit exists elsewhere in the target number.
            else if (targetStr.contains(String.valueOf(guessedDigit))) {
                hits++;
            }
        }

        //Return an array with the bulls and hits count for further processing.
        return new int[]{bulls, hits};
    }

    
    
    /**
     * Validates whether the provided guess is a valid 4-digit number 
     * with unique digits. The guess must meet the following criteria:
     * 
     * 1. It must be exactly 4 digits long.
     * 2. It must contain only numeric characters (digits).
     * 3. Each digit must be unique; no digit may appear more than once.
     */
    private boolean isValidGuess(String guess) {
        if (guess.length() != DIGIT_COUNT || !guess.matches("\\d+")) {
            return false;
        }

        for (int i = 0; i < guess.length(); i++) {
            if (guess.indexOf(guess.charAt(i)) != guess.lastIndexOf(guess.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}
