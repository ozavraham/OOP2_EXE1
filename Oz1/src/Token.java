/*
 * This class holds String and TokenType to represent a Token
 */

public class Token 
	{
	private int tokenIndex;
	private String value;
	private TokenType type;
	
	/*
	 * Initialize a token
	 * value - The Value of the token
	 * type - The Type of the token (enum TokenType)  
	 */
	public Token (String value, TokenType type) {   
		setValue(value);
		setType(type);
	}
	private void setType(TokenType type) {
		this.type = type;
	}
	
	private void setValue(String val) {
		this.value = val;
	}
	
	public int getIndex() {
		return this.tokenIndex;
	}
	public String getValue() {
		return this.value;
	}
	
	public TokenType getType() {
		return this.type;
	}
	
	public String toString() {
		return this.value + " " + this.type;
	}
	
}
