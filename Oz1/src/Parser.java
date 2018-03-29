import java.util.LinkedList;
import java.util.List;

/*	Parser:
 *  arr : Holds the values from the Assignmen command
 * 	result : Total result for calculation
 *  isResult : if excuted calculation
 */
public class Parser {
	public int arr[]; // 
	private int result; // 
	boolean isResult; // 
	private Token token;
	private Lexer lexer;

	/* Constructor
	 * Allcotaing array from a -> z
	 * Assuming this is a calculation command
	 */
	public Parser (){
		this.arr = new int[26];
		this.isResult = true;
	}

	/* Line 
	 * Getting as an input the lexer, diciding if its and calculation or an assign command
	 * 
	 * @throws IllegalArgumentException if the command dose not ends after ';'
	 * 
	 */
	protected void Line(Lexer lexer) {
		this.result = 0;
		this.lexer = lexer;
		token = lexer.get_Token();
		while (lexer.hasNextToken()) {
			if (this.token.getType()==TokenType.INTEGER) {
				this.result = Expression();
				System.out.println(result);
			}
			else if (this.token.getType()==TokenType.IDENTIFIER) {
				this.isResult = false;
				String tav = this.token.getValue();
				this.token = lexer.get_Token(); 
				// IF its and assign command
				if (this.token.getValue().equals("=")) {
					this.token = lexer.get_Token(); 	
					if (token.getType()==TokenType.INTEGER && lexer.tokensList.get(Lexer.index).getType()!=TokenType.OPERNAD) {
						int a = Integer.parseInt(token.getValue());
						this.arr[tav.charAt(0)-'a'] = a;

					}
					else {
						int temp = Expression();
						this.arr[tav.charAt(0)-'a'] = temp;
					}
				}
			}
			else if (this.token.getType()==TokenType.OPERNAD) {
				char ch = this.token.getValue().charAt(0);
				this.token = lexer.get_Token();
				switch (ch) {
				case '+': 
					this.result += Expression();
					break;
				case '-': 
					this.result -= Expression();
					break;
				case '*': 
					this.result *= Expression();
					break;
				case '/': 
					int val = Expression();
					if (val==0) {
						throw new ArithmeticException("Cannot devide by zero!");
					}
					break;
				default:
					throw new IllegalArgumentException("No operand after identifier!");
				}
			}
			// Need to continue! try catch block
			else if (token.getType()==TokenType.OPEN_BREAKETS) {
				this.token = lexer.get_Token();
			}

			else if (token.getType()==TokenType.CLOSE_BREAKETS) {
				this.token = lexer.get_Token();
			}
			if (token.getValue().equals(";")) {
				if (lexer.hasNextToken()) throw new IllegalArgumentException("Value is prohibeded after ';' ! ");
				else break;	
			}

		}
	}

	/**
	 * 
	 * @return val 
	 */
	protected int Expression(){
		int val = Term();
		//		token = lexer.get_Token();
		System.out.println(token.getValue());
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
		return val;
	}

	/**
	 * 
	 * @return
	 */
	protected int Term() {
		int val = Factor();
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

	/* Buttom line recursion
	 * if Integer - returns the integer
	 * 
	 * @return
	 */
	protected int Factor() {
		int val = 0;
		if (token.getType()==TokenType.INTEGER || token.getType()==TokenType.IDENTIFIER) {
			if (token.getType() == TokenType.INTEGER) {
				val = Integer.parseInt(token.getValue());
			}
			else {
				val = this.arr[token.getValue().charAt(0)-'a'];
			}
			token = lexer.get_Token();
		}
		else if (token.getValue().equals("-")) {
			token = lexer.get_Token();
			if (token.getType() == TokenType.END_OF_LINE) { 
				throw new IllegalArgumentException("Token ';' canno't come after -!");
			}
			else if (token.getType() == TokenType.INTEGER) {
				val -= Integer.parseInt(token.getValue());
				token = lexer.get_Token();
			}
		}
		else if (token.getType()== TokenType.OPEN_BREAKETS || token.getType()== TokenType.CLOSE_BREAKETS) {
			if (token.getType()==TokenType.OPEN_BREAKETS) {
				token = lexer.get_Token();
				val = Expression();
			}
			token = lexer.get_Token();
		}
		return val;
	}

	public void showSavedValues() {
		for (int i=0; i<this.arr.length ; i++) {
			char c = (char)(i +'a');
			System.out.print(c + ": " + this.arr[i] + " | ");
		}
		System.out.println();
	}

	public int getResult() {
		return this.result;
	}

	public void setisResult() {
		this.isResult = true;
	}
}
