# GuessingGame (בול פגיעה)

## Overview
This project is a Java implementation of the "Bulls and Hits" (בול פגיעה) number-guessing game, developed as a programming exercise. The program generates a random 4-digit number with unique digits, and the user attempts to guess it. After each guess, feedback is provided in terms of "Bulls" (correct digits in the correct position) and "Hits" (correct digits in the wrong position). The game continues until the user guesses the number correctly, at which point the total number of guesses is displayed. The user can then choose to play again or exit. All interactions are handled through dialog boxes using `JOptionPane`.

## Assignment Description
The task was to create a game following these requirements:
- The program selects a random 4-digit number with no repeating digits.
- The user guesses a 4-digit number, and the program responds with:
  - **Bulls (בול)**: Number of digits correct in both value and position.
  - **Hits (פגיעה)**: Number of digits correct but in the wrong position.
- Example:
  - Target number: `1307`
  - Guess: `3201`
  - Result: `1 Bull, 2 Hits`
    - Bull: `0` is in the correct position.
    - Hits: `3` and `1` are in the target but in different positions.
- The game continues until the correct number is guessed, then displays the total number of guesses.
- All previous guesses and their results are shown after each attempt.
- After winning, the user is asked via a dialog box if they want to play again.
- Invalid inputs (non-numeric, wrong length, or repeating digits) trigger an error message.
- The implementation uses the `String` class for parsing input.

## Features
- Generates a random 4-digit number with unique digits using `Random` and validation logic.
- Validates user guesses to ensure they are 4-digit numbers with unique digits.
- Provides detailed feedback after each guess (Bulls and Hits).
- Tracks and displays all previous guesses with their results.
- Interactive UI using `JOptionPane` for input and output.
- Option to play again after winning, with a reset of the game state.

## Project Structure
- **`GameEngine.java`**: Core game logic, including target number generation, guess validation, and feedback calculation.
- **`UserInterface.java`**: Handles user interaction via dialog boxes, ensuring non-empty input.
- **`GuessingGame.java`**: Main class that initializes and runs the game.

## How to Play
1. Run the `GuessingGame` class.
2. A dialog box prompts you to enter a 4-digit number with unique digits.
3. After each guess, a message shows:
   - The number of Bulls and Hits.
   - A history of all previous guesses.
4. If the guess is invalid (e.g., wrong length, repeating digits), an error message appears.
5. Continue guessing until you match the target number.
6. Upon winning, the total number of guesses is displayed, and you're asked if you want to play again (enter `1` for yes, `0` for no).

## Installation and Running
1. Clone this repository:(Replace `username` with your GitHub username.)
2. Open the project in an IDE like Eclipse.
3. Ensure you have JDK 8 or higher installed.
4. Run the `GuessingGame` class to start the game.

## Code Highlights
- **Target Number Generation**: Uses `Random` to create a 4-digit number and `HashSet` to ensure unique digits.
- **Guess Validation**: Checks length, numeric characters, and uniqueness using `String` methods.
- **Feedback Logic**: Compares each digit of the guess to the target, counting Bulls and Hits efficiently.
- **History Tracking**: Stores previous guesses in an `ArrayList` for display.

## Example Gameplay
Target: 1307 (hidden from user)
Guess 1: 1234 -> Bulls: 1, Hits: 1
Guess 2: 5678 -> Bulls: 0, Hits: 0
Guess 3: 3201 -> Bulls: 1, Hits: 2
Guess 4: 1307 -> Bulls: 4, Hits: 0
Result: "Congratulations! You guessed the right number in 4 guesses!"

## Development Environment
- Written in Java using Eclipse IDE.
- Uses standard Java libraries (`java.util.Random`, `java.util.HashSet`, `javax.swing.JOptionPane`, etc.).
