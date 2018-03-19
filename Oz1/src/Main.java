import java.util.Scanner;
import java.util.*;
import java.util.LinkedList;

public class Main {

	public static void main(String[] args) {
		LinkedList list = new LinkedList(); // If needed
		Scanner read = new Scanner (System.in);
		String str;
		System.out.println("Welcome!");
		do {
			System.out.println("Insert new command to execute:");
			System.out.println("Insert 'END' to exit.");
			str =  read.nextLine();
			if (str=="END") System.exit(0);;
			Lexer lexer = new Lexer (str);
			// presenting tokens...
			TokenType.
			list = lexer.getList();
			Parser pareser = new Parser (list);
		} while (str!="END");
		
		
		System.out.println("");
		System.out.println("");
		
		

	}

}
