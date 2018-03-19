import java.util.*;
import java.util.LinkedList;

// Lexer divides input into tokens
// function get_token gives the next token as string 

public class Lexer {
	LinkedList <Token> list = new LinkedList(); 
	public Lexer (String str){
		char tav;
		for(int i = 0; i < str.length(); i++) {
			tav = str.charAt(i);
			if (tav >= 'a' && tav <= 'z') {
				this.list.add(new Token(tav, TokenType.IDENTIFIER));
			}
			if (tav >= 0 && tav < 10) {
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
	}
}