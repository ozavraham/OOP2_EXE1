import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {

		boolean isDone = false;
		Parser parser = new Parser();
		System.out.println("Welcome!");
		System.out.println("Insert command to execute:");
		System.out.println("Insert 'END' to exit.");
		Scanner read = new Scanner (System.in);
		do {
			// Instructions
			// Initialize new Parser
			try{
				String str =  read.nextLine();
				if (str.equals("END")) isDone = true;
				else {
					// As long as we ain't getting END as an input
					Lexer lexer = new Lexer();
					// If needed
					List <Token> list = new LinkedList<Token>(); 
					// Priniting the user's input
					System.out.println("Your input: " + str); 
					// Sending the string to tokenize, and assign to list
					list = lexer.tokenize(str); 
					//Sending the token list to  check if valid, throws Execeptions 
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
						parser.showSavedValues();
					}
					// Getting new input from the user
					System.out.println("To continue, Insert new command, 'END' to exit");
					parser.setisResult();
				}
			}
			// Catch exceptions from Lexer 
			catch (Exception e) {
				System.out.println(e.getMessage());
				System.out.println("Insert new command, 'END' to exit");
			}
		}while (!isDone);
		// Closing the scanner
		read.close(); 
		System.out.println("Exiting... Goodbye!");
	}

}



