import java.util.*;

public class Main {

	public static void main(String[] args) {
		Scanner read = new Scanner (System.in);
		String str;
		System.out.println("Welcome!");
		System.out.println("Insert command to execute:");
		System.out.println("Insert 'END' to exit.");
		Parser parser = new Parser();
		str =  read.nextLine();
		while (!str.equals("END")) {
			Lexer lexer = new Lexer();
			List<Token> list = new LinkedList(); // If needed
			System.out.println("Your input: " + str); // Priniting the user's input
			list = lexer.tokenize(str);
			System.out.print("Token divide:");
			lexer.printList();
			parser.Line(lexer);
			if (parser.isResult) { 
				System.out.println("Result: " + parser.getResult());
			}
			else {
				System.out.println(str + " Has been excuted!");
				parser.showSavedValues();
			}
			System.out.println("To continue, Insert new command, 'END' to exit");
			parser.setisResult();
			str =  read.nextLine();
		}
		System.out.println("Goodbye...");

	}

}
