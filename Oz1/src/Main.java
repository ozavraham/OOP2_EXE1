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
		do {
			str =  read.nextLine();
			System.out.println(str); // Priniting the user's input
			if (str=="END") System.exit(0);;
			Lexer lexer = new Lexer (str);
			lexer.printList();
			// presenting tokens...
			System.out.println("---------------------");
			// Start parser using:
			Parser parser = new Parser();
			parser.Line(lexer.list);
			//Parser pareser = new Parser (list);
			
			System.out.println("To continue, Insert new command, 'END' to exit");
		} while (!str.equals("END"));
		
		
		System.out.println("");
		
		

	}

}
