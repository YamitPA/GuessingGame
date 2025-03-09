import javax.swing.JOptionPane;



/**
 * The UserInterface class provides methods to interact with the user 
 * through dialog boxes. It includes methods to display messages to the 
 * user and receive input, ensuring that input is not empty.
 * 
 * This class manages all user interactions, isolating the input and 
 * output handling for a game or application.
 */
public class UserInterface {
	
    /*
     * Prompts the user with a specified message and waits for 
     * input. This method will continue to prompt the user until 
     * a non-empty input is provided.
     */
    public String getUserInput(String message) {
        String input = "";

        while (input.isEmpty()) {
        	input = JOptionPane.showInputDialog(message);

        	//If the user closes the dialog, input will be null, we can handle that.
            if (input == null || input.length() == 0) {
                JOptionPane.showMessageDialog(null, "The input cannot be empty. Please try again.", 
                                              "Input Error", JOptionPane.ERROR_MESSAGE);
                continue;
            }
        }
        
        return input; 
    }

    
    /*
     * Displays a specified message to the user in a dialog box.
     */
    public void displayMessage(String message) {
    	JOptionPane.showMessageDialog(null, message);
    }
}
