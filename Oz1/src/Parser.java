import java.util.LinkedList;
import java.util.List;

/*	Parser checking line intactness, calculate the score and print it, or assign into varaible
 * 	Assign values is done.
 * TODO:
 * 
 * 
 * 
 */
public class Parser {
	public int arr[];
	private Token token;
	private Lexer lexer;

	public Parser (){
		this.arr = new int[26];
	}

	// Whole line
	protected void Line(Lexer lexer) {
		this.lexer = lexer;
		token = lexer.get_Token();
		while (token!=null) {
			if (token.getType()==TokenType.INTEGER) {
				System.out.println("Result: " + Expression()); 
				break;
			}
			else if (token.getType()==TokenType.IDENTIFIER) {
				String tav = token.getValue();
				token = lexer.get_Token(); 
				if (token.getValue().equals("=")) {
					token = lexer.get_Token(); 	
					if (token.getType()==TokenType.INTEGER && lexer.tokensList.get(lexer.index+1).getType()!=TokenType.OPERNAD) {
						int a = Integer.parseInt(token.getValue());
						this.arr[tav.charAt(0)] = a;
						System.out.println(tav + "=" + a + "; Has been excuted.");
						break;
					}
					else {
						int temp = Expression();
						System.out.println(temp);
						int a = Integer.parseInt(token.getValue());
						this.arr[tav.charAt(0)] = temp;
						System.out.println(tav + "=" + temp + "; Has been excuted.");
						break;
					}
				}
				else Expression();
			}
			if (token.getValue().equals(";")) {
				break;
			}
		}
	}

	protected int Expression(){
		int val = Integer.parseInt(token.getValue());
		token = lexer.get_Token();
		if (token.getValue().equals("+") || token.getValue().equals("-")) {
			if (token.getValue().equals("+")) {
				token = lexer.get_Token();
				val += Term();
			}
			else {
				token = lexer.get_Token();
				val -= Term();
			}
		}
		else {
			val = Term();
		}
		return val;
	}


	protected int Term() {
		int val = Factor();
		//t= lexer.get_Token();
		if (token.getValue().equals("*") || token.getValue().equals("/")){
			if (token.getValue().equals("*")) {
				token = lexer.get_Token();
				val *= Factor();
			}
			else {
				token = lexer.get_Token();
				val /= Factor();
			}
		}
		return val;
	}

	protected int Factor() {
		int val = 0;

		if (token.getType()==TokenType.INTEGER || token.getType()==TokenType.IDENTIFIER) {
			if (token.getType() == TokenType.INTEGER) {
				val = Integer.parseInt(token.getValue());
			}
			else {
				val = this.arr[token.getValue().charAt(0)];
			}
			token = lexer.get_Token();
		}

		if (token.getValue().equals("-")) {
			token = lexer.get_Token();
			if (token.getType() == TokenType.END_OF_LINE) 
				throw new IllegalArgumentException("Token ';' canno't come after -!");
		}
		if (token.getType() == TokenType.OPEN_BREAKETS) {
			token = lexer.get_Token();
			val -= Expression();
			val += Expression();
		}
		else if (token.getType() == TokenType.INTEGER) {
			val = Integer.parseInt(token.getValue());
			token = lexer.get_Token();
		}
		else if (token.getType() == TokenType.IDENTIFIER) {
			val = this.arr[Integer.parseInt(token.getValue())];
			token = lexer.get_Token();
		}
		else if (token.getValue().equals("(")) {
			token = lexer.get_Token();
			val = Expression();
		}
		if (token.getValue().equals(")")) {
			token = lexer.get_Token();
		}
		return val;
	}

	public void showSavedValues() {
		for (int i=0; i<this.arr.length ; i++) {
			char c = (char) (i+'a');
			System.out.print(c + ": " + this.arr[i] + " | ");
		}
		System.out.println();
	}
}
