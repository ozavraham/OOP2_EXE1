import java.util.*;

public class Lexer 
{
	
	List <Token> tokensList;
	public static int index;
	private int length;
	
	public Lexer() {
		Lexer.index = 0;
	}
	
	public List <Token> tokenize(String source) throws UnknownTokenException
{
		this.length = source.length();
		StringBuffer currentToken = new StringBuffer();
		tokensList = new ArrayList<Token>();
		Token token = null;
		
		for(int i = 0; i < source.length(); i++)
		{
			String sub = source.substring(i, i+1);
			if(sub.matches("[0-9.a-zA-Z_]")) 
			{
				currentToken.append(sub);
				continue; 
			}
			else if (currentToken.length() > 0) 
			{
				String lexeme = currentToken.toString();
				if (lexeme.matches("[0-9]+|[0-9]+\\.[0-9]+|[0-9]+[eE][0-9]+"))
				{
					tokensList.add(new Token(lexeme, TokenType.INTEGER));
				} 
				else if (lexeme.matches("[A-Za-z_]+[0-9]*")) 
				{
					tokensList.add(new Token(lexeme, TokenType.IDENTIFIER));
				}
				else 
				{
					throw new UnknownTokenException(lexeme + " is not a valid variable name, nor a number.");
				}
				currentToken = new StringBuffer();
			}
			char chr = source.charAt(i);
			switch (chr) 
			{
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
				if (token != null && token.getType() == TokenType.IDENTIFIER) 
				{
					tokensList.remove(tokensList.size() - 1);
					token = new Token(token.getValue(), TokenType.FUNCTION);
				} 
				else 
				{
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

	public void printList() 
	{
		System.out.print("[");
		for(int i = 0; i < tokensList.size(); i++)
		{
			if (i==tokensList.size()-1) System.out.print(tokensList.get(i));
			else System.out.print(tokensList.get(i) + " | ");
		}
		System.out.println("]");
	}

	Token get_Token() 
	{
		//Thread.dumpStack();
		Token token = this.tokensList.get(index);
		index++;
		return token;

	}
	
	public boolean hasNextToken() {
		int temp = Lexer.index + 1;
		if (temp>this.length) return false;
		else return true;
	}
}