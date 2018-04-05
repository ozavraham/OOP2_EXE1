public class Parser {
	public int arr[]; // Allcoating to save into identifires
	private int result; // To calculate the total result
	boolean isResult; // True - if it is a calculation command 
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
	 * Getting as an input the lexer, diciding if its an calculation or an assign command
	 * @throws IllegalArgumentException if the command dose not ends after ';'
	 * 
	 */
	protected void Line(Lexer lexer) {
		this.result = 0;
		this.lexer = lexer;
		token = lexer.get_Token();
		while (lexer.hasNextToken()) {
			// If its an Integer at the beginning, then its calculation
			if (this.token.getType()==TokenType.INTEGER) {
				this.result = Expression();
			}
			// Else its possibly an Assign command 
			else if (this.token.getType()==TokenType.IDENTIFIER) {
				this.isResult = false;
				String tav = this.token.getValue();
				this.token = lexer.get_Token(); 
				// If its an assign command then 
				if (this.token.getValue().equals("=")) {
					this.token = lexer.get_Token(); 	
					if (token.getType()==TokenType.INTEGER && lexer.tokensList.get(Lexer.index).getType()!=TokenType.OPERNAD) {
						int a = Integer.parseInt(token.getValue());
						this.arr[tav.charAt(0)-'a'] = a;
					}
					// Handle a=) situation 
					else if (token.getType()==TokenType.CLOSE_BREAKETS) {
						throw new IllegalArgumentException ("Close Bracket cannot be after '=' !");
					}
					else {
						int temp = Expression();
						this.arr[tav.charAt(0)-'a'] = temp;
					}
				}
				// Else it is a calculation command
				else {
					this.result = this.arr[tav.charAt(0)-'a'];
					this.isResult = true;
				}
			}
			// If its an opernad, then we need some calculation
			else if (this.token.getType()==TokenType.OPERNAD) {
				char ch = this.token.getValue().charAt(0);
				this.token = lexer.get_Token();
				// Excuting the calculation according to the matching operand
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
					this.result /= val;
					break;
					
				default:
					throw new IllegalArgumentException("No operand after identifier!");
				}
			}
			else if (token.getType()==TokenType.OPEN_BREAKETS) {
				this.token = lexer.get_Token();
			}

			else if (token.getType()==TokenType.CLOSE_BREAKETS) {
				this.token = lexer.get_Token();
			}
			if (token.getValue().equals(";")) {
				if (lexer.hasNextToken()) throw new IllegalArgumentException("Value is prohibited after ';' ! ");
				else break;	
			}

		}
	
	}

	/* Expression starts a recursive calculation
	 * Addition and Subtraction actions begin excute here
	 * @return val (int) 
	 */
	protected int Expression() {
		int val = Term();
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

	/* Term continue the recursive calculation
	 * Multiplication and Division actions excute here if needed
	 * @return val (int)
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
	 * if INTEGER - returns the INTEGER
	 * if IDENTIFIER - returns the value inside the IDENTIFIER
	 * @return val(int)
	 * @throws IllegalArgumentException when finding ';' after '-'
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
	
	/* showSavedValues Used for testing
	 * Print all of the values that located at the "memory"
	 * Used for test only * 
	 */
	public void showSavedValues() {
		for (int i=0; i<this.arr.length ; i++) {
			char c = (char)(i +'a');
			System.out.print(c + ": " + this.arr[i] + " | ");
		}
		System.out.println();
	}
	
	/* getResult()
	 * @return The result of this parser
	 */
	public int getResult() {
		return this.result;
	}
	
	/* setisResult()
	 * If it is calculation, setting the value to true
	 */
	public void setisResult() {
		this.isResult = true;
	}
}
