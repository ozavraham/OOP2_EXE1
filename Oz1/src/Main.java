import java.util.*;

public class Main {

	public static void main(String[] args) {
		List<Token> list = new LinkedList(); // If needed
		Scanner read = new Scanner (System.in);
		String str;
		System.out.println("Welcome!");
		System.out.println("Insert command to execute:");
		System.out.println("Insert 'END' to exit.");
		str =  read.nextLine();
		while (!str.equals("END")) {
			Lexer lexer = new Lexer();
			Parser parser = new Parser();
			System.out.println("Your input: " + str); // Priniting the user's input
			list = lexer.tokenize(str);
			// presenting tokens...
			System.out.print("Token divide:");
			lexer.printList();
			// Start parser using:
			parser.Line(lexer);
			parser.showSavedValues();
			System.out.println("To continue, Insert new command, 'END' to exit");
			str =  read.nextLine();
		}
		System.out.println("Goodbye...");

	}

}
