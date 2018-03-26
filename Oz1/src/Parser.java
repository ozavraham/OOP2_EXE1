import java.util.LinkedList;
import java.util.List;

/*	Parser checking line intactness, calculate the score and print it, or assign into varaible
 * 	 
 */
public class Parser {
	public int arr[];
	private int result; // getter
	boolean isResult; // if excuted calculation
	private Token token;
	private Lexer lexer;


	public Parser (){
		this.arr = new int[26];
		this.isResult = true;
	}

	/**
	 * 
	 * @return
	 */
	protected void Line(Lexer lexer) {
		this.result = 0;
		this.lexer = lexer;
		token = lexer.get_Token();
		while (this.token!=null) {
			if (this.token.getType()==TokenType.INTEGER) {
				this.result = Expression();
				System.out.println(result);
			}
			else if (this.token.getType()==TokenType.IDENTIFIER) {
				this.isResult = false;
				String tav = this.token.getValue();
				this.token = lexer.get_Token(); 
				if (this.token.getValue().equals("=")) {
					this.token = lexer.get_Token(); 	
					if (token.getType()==TokenType.INTEGER && lexer.tokensList.get(Lexer.index).getType()!=TokenType.OPERNAD) {
						int a = Integer.parseInt(token.getValue());
						this.arr[tav.charAt(0)-'a'] = a;
						break;
					}
					else {
						int temp = Expression();
						this.arr[tav.charAt(0)-'a'] = temp;
						break;
					}
				}
			}

			// Need to continue!
			else if (token.getValue().equals("(")) {
				this.token = lexer.get_Token();
				if (this.token.getType()==TokenType.IDENTIFIER || this.token.getType()==TokenType.INTEGER) {
					if (this.token.getType()==TokenType.IDENTIFIER) {
						String tav = this.token.getValue();
						System.out.println(tav);
						this.result = this.arr[tav.charAt(0)-'a'];
					}
					else {
						this.result = Integer.parseInt(token.getValue());
					}
					this.token = lexer.get_Token();
					char tav = this.token.getValue().charAt(0);
					this.token = lexer.get_Token();
					switch (tav) {
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
			}
			else if (token.getType()==TokenType.OPERNAD) {					
				char tav = this.token.getValue().charAt(0);
				this.token = lexer.get_Token();
				switch (tav) {
				case '+': 
					this.result += Expression();
					break;
				case '-': 
					this.result -= Expression();
					break;
				case '*': 
					int value = Expression();
					this.result *= value;
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
			else if (token.getValue().equals(")")) {
				this.token = lexer.get_Token();
			}

			if (token.getValue().equals(";")) {
				this.token = null;
				break;
			}

		}
	}

	/**
	 * 
	 * @return
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

	/**
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
				val = this.arr[token.getValue().charAt(0)];
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
			if (token.getValue().equals("(")) {
				token = lexer.get_Token();
				val = Expression();
				if (token.getValue().equals(")")) {
					token = lexer.get_Token();
				}
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
