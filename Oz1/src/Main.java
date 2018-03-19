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
			if (str=="END") System.exit(0);;
			Lexer lexer = new Lexer (str);
			lexer.printLine();
			// presenting tokens...
			System.out.println("---------------------");
			// Start parser using:
			//Parser pareser = new Parser (list);
			
			System.out.println("To continue, Insert new command, 'END' to exit");
		} while (str!="END");
		
		
		System.out.println("");
		
		

	}

}
