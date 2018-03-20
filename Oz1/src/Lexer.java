import java.util.*;
import java.util.LinkedList;

// Lexer divides input into tokens
// function get_token gives the next token as string 

public class Lexer {
	 LinkedList <Token> list;

	public Lexer (){
		this.list = new LinkedList();
		/*
		 * Ah sheli!
		 * it will be easier if before you read the string you will remove all of the spaces!
		 * Example:
		 * If the input is a = 3;
		 * then use trim() to turn it to a=3; (which is easeir to divide)
		 * String str = "a = 3;"
		 * String newStr = str.trim();
		 * newStr = "a=3;"
		 * will make it easier to us to ignore all the spaces
		 * :):):):):)
		 */
	}

	LinkedList <Token> tokenDivide(String str) {
		this.list = new LinkedList();
		char tav;
		for(int i = 0; i < str.length(); i++) {

			tav = str.charAt(i);

			if (tav >= 'a' && tav <= 'z') {
				this.list.add(new Token(tav, TokenType.IDENTIFIER));
			}
			if (tav >= '0' && tav <= '9') {
				this.list.add(new Token(tav, TokenType.INTEGER));
			}
			if (tav == '+' || tav == '-' || tav == '*' || tav == '/' || tav == '='){
				this.list.add(new Token(tav, TokenType.OPERNAD));
			}
			if ( tav == '(' || tav == ')') {
				this.list.add(new Token(tav, TokenType.BRACKET));
			}
			if (tav == ';'){
				this.list.add(new Token(tav, TokenType.END_OF_LINE));
			}
		}
		return list;

	}

	void printList() {
		for( int i = 0; i < this.list.size() ; i++) {
			System.out.println(list.get(i));
			System.out.println();
		}
	}
}