import java.util.Scanner;
import java.util.*;
import java.util.LinkedList;

public class Main {

	public static void main(String[] args) {
		LinkedList list = new LinkedList(); // If needed
		Scanner read = new Scanner (System.in);
		String str;
		System.out.println("Welcome!");
		System.out.println("Insert command to execute:");
		System.out.println("Insert 'END' to exit.");
		str =  read.nextLine();
		Lexer lexer = new Lexer();
		Parser parser = new Parser();
		while (!str.equals("END")) {
			System.out.println("Your input: " + str); // Priniting the user's input
			list = lexer.tokenDivide(str);
			// presenting tokens...
			System.out.print("Token divide:");
			lexer.printList();
			// Start parser using:
			parser.Line(list);
			//parser.showSavedValues();
			System.out.println("To continue, Insert new command, 'END' to exit");
			str =  read.nextLine();
		}
		System.out.println("Goodbye...");

	}

}
