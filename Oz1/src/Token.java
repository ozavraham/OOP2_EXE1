public class Token {
	char tav;
	TokenType type;
	
	public Token (){
	}
	
	public Token (char tav, TokenType t) {
		this.tav = tav;
		this.type = t;
	}
	
	Token get_token(){
		return this;
	}
	
	void setType(TokenType t) {
		this.type = t;
	}
	
	TokenType get_type() {
		return this.type;
	}
	
	char get_val() {
		return this.tav;
	}
}
