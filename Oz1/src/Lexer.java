import java.util.*;
import java.util.LinkedList;

// Lexer divides input into tokens
// function get_token gives the next token as string 

public class Lexer {
	
	LinkedList <Token> list = new LinkedList<Token>(); 
	
	public Lexer (String str){
		
		char token;
		
		for(int i = 0; i < str.length(); i++) {
			
			token = str.charAt(i);
			
			if (token >= 'a' && token <= 'z') {
				this.list.add(new Token(token, TokenType.IDENTIFIER));
			}
			if (token >= '0' && token <= '9') {
				this.list.add(new Token(token, TokenType.INTEGER));
			}
			if (token == '+' || token == '-' || token == '*' || token == '/' ||token == '='){
				this.list.add(new Token(token, TokenType.OPERNAD));
			}
			if (token == '(' || token == ')') {
				this.list.add(new Token(token, TokenType.BRACKET));
			}
			if (token == ';'){
				this.list.add(new Token(token, TokenType.END_OF_LINE));
			}
		}
		
	}
	
	boolean checkSyntax(LinkedList<Token> list) {
		
		Object[] TokensArray = list.toArray(new Object[list.size()]);
		
		int countBreakets = 0;
		
		for(int i = 0; i < list.size(); i++){
			
			if(TokensArray[i].equals('(') || TokensArray[i].equals(')')){
				countBreakets++;
				if(countBreakets%2 != 0)
					return false;
			}
			if(TokensArray[i].equals(TokenType.INTEGER) && TokensArray[i+1].equals(TokenType.IDENTIFIER))//checks if 2a 
				return false;
			if(TokensArray[i].equals(TokenType.IDENTIFIER) && TokensArray[i+1].equals(TokenType.INTEGER))//checks if a2
				return false;
			if(!(TokensArray[TokensArray.length-1].equals(TokenType.END_OF_LINE)))//checks if sentence end with -> ; 
				return false;
			if(TokensArray[i].equals(TokenType.OPERNAD) && TokensArray[i+1].equals(TokenType.OPERNAD))//checks if there are double operations
				return false;
			}
		return true;
	}
	
	void printList() {
		for( int i = 0; i < this.list.size() ; i++) {
			System.out.println(list.get(i));
			System.out.println();
		}
	}
}
