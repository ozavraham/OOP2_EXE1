import java.util.*;

public class Lexer {
	List <Token> tokensList;
	public static int index;

	public Lexer() {
		Lexer.index = 0;
	}
	/*
	 * tokenize receive  a String 
	 * disjoint String to Token List
	 * each Token by his type
	 * Return Token LinkedList
	 */
	public List <Token> tokenize(String source) throws UnknownTokenException {
		StringBuffer currentToken = new StringBuffer();
		tokensList = new LinkedList<Token>();
		Token token = null;
		for(int i = 0; i < source.length(); i++) {
			String sub = source.substring(i, i+1);
			if(sub.matches("[0-9.a-zA-Z_]")) {
				currentToken.append(sub);
				continue; 
			}
			else if (currentToken.length() > 0) {
				String lexeme = currentToken.toString();
				if (lexeme.matches("[0-9]+|[0-9]+\\.[0-9]+|[0-9]+[eE][0-9]+")) {
					tokensList.add(new Token(lexeme, TokenType.INTEGER));
				} 
				else if (lexeme.matches("[A-Za-z_]+[0-9]*")) {
					tokensList.add(new Token(lexeme, TokenType.IDENTIFIER));
				}
				else {
					throw new UnknownTokenException(lexeme + " is not a valid variable name, nor a number.");
				}
				currentToken = new StringBuffer();
			}
			char chr = source.charAt(i);
			switch (chr) {
			case '+':
				token = new Token("+",TokenType.OPERNAD);
				break;
			case '-':
				token = new Token("-",TokenType.OPERNAD);
				break;
			case '*':
				token = new Token("*",TokenType.OPERNAD);
				break;
			case '/':
				token = new Token("/",TokenType.OPERNAD);
				break;
			case '=':
				token = new Token("=",TokenType.OPERNAD);
				break;
			case '(':
				if (token != null && token.getType() == TokenType.IDENTIFIER) {
					tokensList.remove(tokensList.size() - 1);
					token = new Token(token.getValue(), TokenType.FUNCTION);
				} 
				else {
					token = new Token("(",TokenType.OPEN_BREAKETS);
				}
				break;
			case ')':
				token = new Token(")",TokenType.CLOSE_BREAKETS);
				break;
			case ';':
				token = new Token(";",TokenType.END_OF_LINE);
				break;
			case ' ':
				continue;
			default:
				throw new UnknownTokenException(chr + " is an unknown character.");
			}
			tokensList.add(token);
		}
		return tokensList;
	}
	/*Prints Token list 
	 * [token value token type | token value token type]
	 * for -> b; -> prints:
	 * [b IDENTIFIER | ; END_OF_LINE]
	 */
	public void printList() {
		System.out.print("[");
		for(int i = 0; i < tokensList.size(); i++) {
			if (i==tokensList.size()-1) System.out.print(tokensList.get(i));
			else System.out.print(tokensList.get(i) + " | ");
		}
		System.out.println("]");
	}
	Token get_Token() {
		Token token = this.tokensList.get(index);
		index++;
		return token;
	}
	/*prevent index out of bound exception */
	public boolean hasNextToken() {
		int temp = Lexer.index + 1;
		if (temp>this.tokensList.size()) return false;
		else return true;  
	}
	/*isValid checks that the Tokens list generated is valid
	 *OPERAND and Semicolon in start of line are not allowed
	 *each line need to end with semicolon and only one in line
	 *double equal sign is not allowed
	 */
	public void isValid(List <Token> list) throws IllegalArgumentException{
		int length = list.size();
		int countSemicolon = 0;
		int countEqual = 0;
		if (list.get(0).getValue().equals(";") || list.get(0).getType()==TokenType.OPERNAD) {
			throw new IllegalArgumentException("Invalid beginning of command!");
		}

		if (!list.get(length-1).getValue().equals(";")) {
			throw new IllegalArgumentException("; Must be at the end of command!");
		}
		for(int i = 0; i <list.size(); i++) {
			if(list.get(i).getType().equals(TokenType.END_OF_LINE)){
				countSemicolon++;
				if(countSemicolon > 1 ) {
					throw new IllegalArgumentException("Each line need to contain a single Semicolon at the end!");
				}
			}
			else if(list.get(i).getValue().equals("=")) {
				countEqual++;
				if(countEqual > 1) {
					throw new IllegalArgumentException("Each line can contain a single Equal sign!");
				}
			}
		}

	}
}
