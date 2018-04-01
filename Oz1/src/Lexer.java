import java.util.*;

public class Lexer {
	List <Token> tokensList;
	public static int index;
	private int length;

	public Lexer() {
		Lexer.index = 0;
	}

	public List <Token> tokenize(String source) throws UnknownTokenException {
		StringBuffer currentToken = new StringBuffer();
		tokensList = new ArrayList<Token>();
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
				token = new Token(TokenType.OPERNAD);
				token.setValue(Character.toString(chr));
				break;
			case '-':
				token = new Token(TokenType.OPERNAD);
				token.setValue(Character.toString(chr));
				break;
			case '*':
				token = new Token(TokenType.OPERNAD);
				token.setValue(Character.toString(chr));
				break;
			case '/':
				token = new Token(TokenType.OPERNAD);
				token.setValue(Character.toString(chr));
				break;
			case '=':
				token = new Token(TokenType.OPERNAD);
				token.setValue(Character.toString(chr));
				break;
			case '(':
				if (token != null && token.getType() == TokenType.IDENTIFIER) {
					tokensList.remove(tokensList.size() - 1);
					token = new Token(token.getValue(), TokenType.FUNCTION);
				} 
				else {
					token = new Token(TokenType.OPEN_BREAKETS);
					token.setValue(Character.toString(chr));
				}
				break;
			case ')':
				token = new Token(TokenType.CLOSE_BREAKETS);
				token.setValue(Character.toString(chr));
				break;
			case ';':
				token = new Token(TokenType.END_OF_LINE);
				token.setValue(Character.toString(chr));
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

	public boolean hasNextToken() {
		int temp = Lexer.index + 1;
		if (temp>this.tokensList.size()) return false;
		else return true;
	}
	
	public static void isValid(List<Token> tokens) throws IllegalTokenException
	{
		int countSemicolon = 0;
		int countEqual = 0;
		int length = 0;
		try {
			while(length < tokens.size()){
				if(tokens.get(index).getType().equals(TokenType.END_OF_LINE)) {
					countSemicolon++;
					if(countSemicolon > 1 || (length == tokens.size()-1  && 1 == countSemicolon))
						throw new IllegalTokenException("Each line need to contain a single Semicolon at the end ");
				}
				if(tokens.get(index).getType().equals('=')) {
					countEqual++;
					if(countEqual > 1)
						throw new IllegalTokenException("Each line can contain a single Equal sign ");
				}
				length++;
			}
			if(tokens.get(0).getType().equals(TokenType.OPERNAD))
				throw new IllegalTokenException("A line can't start with: " + tokens.get(0).getValue());
		}
		catch(IllegalTokenException e) {
			System.out.println(e);
		}

	}

}