import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		
		Scanner read = new Scanner (System.in);
		String str;
		// Instructions
		System.out.println("Welcome!");
		System.out.println("Insert command to execute:");
		System.out.println("Insert 'END' to exit.");
		// Initialize new Parser
		Parser parser = new Parser();
		str =  read.nextLine();
		// As long as we ain't getting END as an input
		try {
		while (!str.equals("END")) {
			Lexer lexer = new Lexer();
			// If needed
			List <Token> list = new LinkedList(); 
			// Priniting the user's input
			System.out.println("Your input: " + str); 
			// Sending the string to tokenize, and assign to list
			list = lexer.tokenize(str); 
			//Sending the token list to  chech if valid
			lexer.isValid(list);
			// Presenting the Token Dividion
			System.out.print("Token divide:");
			lexer.printList(); 
			// Sending the lexer to Line @ Parser class
			parser.Line(lexer); 
			if (parser.isResult) { 
				System.out.println("Result: " + parser.getResult());
			}
			else {
				System.out.println(str + " Has been excuted!");
				// If need to show values at the array
//				parser.showSavedValues();
			}
			// Getting new input from the user
			System.out.println("To continue, Insert new command, 'END' to exit");
			parser.setisResult();
			str =  read.nextLine();
		}
	}
		catch(IllegalTokenException e) {
			System.out.println(e);
		}
		catch(UnknownTokenException e) {
			System.out.println(e);
		}
		// Closing the scanner
		read.close(); 
		System.out.println("Exiting... Goodbye!");

	}

}
