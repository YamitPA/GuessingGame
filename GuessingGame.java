/**
 * This class is responsible for running the guessing game.
 * The game follows these rules:
 * - The player is asked to guess a 4-digit number where all digits are unique.
 * - After each guess, the player receives feedback on their guess in the format of "bulls" and "hits":
 *   - "Bulls" indicate the number of digits that are correct and in the correct position.
 *   - "Hits" indicate the number of digits that are correct but in the wrong position.
 * - The player can choose to continue playing as many times as they wish until they decide to stop.
 */



public class GuessingGame {
	
	/*
     * The main method creates an instance of the GameEngine and starts the game. 
     */
	public static void main (String[] args) {
		GameEngine game = new GameEngine();
        game.start();
	}

}
