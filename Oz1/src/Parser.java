import java.util.LinkedList;
import java.util.List;

//Parser checking line intactness, calculate the score and print it, or assign into varaible

public class Parser {
	private Lexer lexer;
	public int arr[];
	
	public Parser (){
		//this.lexer = lexer;
		this.arr = new int[26];
	}
	
	// Whole line
	protected void Line(LinkedList <Token> list) {
		int a;
		for (int i=0; i<list.size() ; i++) {
			Token t = list.get(i);
			if (t.get_type()==TokenType.INTEGER) {
				System.out.println(Expression(list)); // Getting here twice becuase of the previous line condition.
				i = i+2;
			}
			else if (t.get_type()==TokenType.IDENTIFIER) {
				char tav = t.get_val();
				i++;
				t = list.get(i); // Next token
				if (t.get_val()=='=') {
					i++;
					t = list.get(i); // Next token	
					if (t.get_type()==TokenType.INTEGER) {
						a = t.get_val()-'0';
						this.arr[tav-'a'] = t.get_val()-'0';
						System.out.println(tav + "=" + a + "; Has been excuted.");
					}
					else {
						Expression(list.subList(i+1, list.size()-1));
					}
				}
				else Expression(list.subList(i+1, list.size()-1));
			}
			if (t.get_val()==';') {
				i = list.size();
			}
			
		}
	}
	
	// If needed calculation
	protected int Expression(List <Token> list){
		int i=0;
		int b = 0;
		int a = list.get(i).get_val()-'0';
		i++;
		if (list.get(i).get_val()=='+' || list.get(i).get_val()=='-') {
			b = Term(list.subList(i-1, i+2));
		}
		else if (list.get(i).get_val()=='*' || list.get(i).get_val()=='/'){
			b = Factor(list.subList(i-1, i+2));
		}
		return b;
	}
	
	// +\- Calculation
	protected int Term(List<Token> list) {
		int i=0;
		int a = list.get(i).get_val()-'0'; 
		int b = list.get(i+2).get_val()-'0';
		if (list.get(i+1).get_val()=='+') return a+b;
		else return a-b;
	}
	
	// */ Calculation
	protected int Factor(List<Token> list) {
		int i=0;
		int a = list.get(i).get_val()-'0'; 
		int b = list.get(i+2).get_val()-'0';
		if (list.get(i+1).get_val()=='*') return a*b;
		else return (a/b);
	}
	
	public void showSavedValues() {
		for (int i=0; i<this.arr.length ; i++) {
			char c = (char) (i+'a');
			System.out.print(c + ": " + this.arr[i] + " | ");
		}
		System.out.println();
	}
	
}
