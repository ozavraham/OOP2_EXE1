import java.util.LinkedList;

//Parser checking line intactness, calculate the score and print it, or assign into varaible

public class Parser {
	protected boolean isValid;
	protected String str;
	protected int leftBrace=0, rightBrace=0; // Counting braces!
	static char[] symbols = {'0','1','2','3','4','5','6','7','8','9','+','-','*'};

	public Parser (LinkedList <Token> list){
		System.out.println(Parser.symbols[0]);
		int a = 0, b=0, c=0;
		this.str = str;
		this.isValid = false;
		list.add(new Token (str.charAt(i)));
		

		if (list.get(0).type==TokenType.IDENTIFIER) {
			assignLine(list);
		}

		else if (list.get(0).type==TokenType.DIGIT) {
			calculationLine(list);
		}	
	}

	// If its an assign command, return TRUE if succeed
	protected boolean assignLine(LinkedList <Token> list) {
		return isValid;

	}

	// If its an calculation command, return TRUE if succeed
	protected boolean calculationLine(LinkedList <Token> list) {

		return isValid;
	}
	
	protected int exp(LinkedList <Token> list) {
		return null;
	}
}
