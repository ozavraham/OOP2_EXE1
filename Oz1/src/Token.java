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
	public Token (TokenType type) {
		this.value = null;
		this.type = type;
	}
	
	public Token (String value, TokenType type) {   
		this.value = value;
		this.type = type;
	}
	
	public void setValue(String val) {
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
