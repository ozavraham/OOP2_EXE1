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
	private Lexer lexer;
	public int arr[];
	static private int index;

	public Parser (){
		//this.lexer = lexer;
		this.arr = new int[26];
		this.index = 0;
	}

	// Whole line
	// Need to change to return int!
	// FNDSKLFNKLDSNFLKDSNFLKDS
	protected void Line(LinkedList <Token> list) {
		int a;
		for (int i=0; i<list.size() ; i++) {
			Token t = list.get(i);
			if (t.get_type()==TokenType.INTEGER) {
				System.out.println("Result: " + Expression(list,i)); // Getting here twice becuase of the previous line condition.
				i = i+2;
			}
			else if (t.get_type()==TokenType.IDENTIFIER) {
				char tav = t.get_val();
				i++;
				t = list.get(i); // Next token
				if (t.get_val()=='=') {
					i++;
					t = list.get(i); // Next token	
					if (t.get_type()==TokenType.INTEGER && list.get(i+1).type!=TokenType.OPERNAD) {
						a = t.get_val()-'0';
						this.arr[tav-'a'] = t.get_val()-'0';
						System.out.println(tav + "=" + a + "; Has been excuted.");
					}
					else {
						int temp = Expression(list, i);
						System.out.println(temp);
						a = t.get_val()-'0';
						this.arr[tav-'a'] = temp;
						System.out.println(tav + "=" + temp + "; Has been excuted.");
					}
				}
				else Expression(list, i);
			}
			if (t.get_val()==';') {
				i = list.size();
			}
			
			// If its an expression inside Brackets
			if (t.type==TokenType.BRACKET) {
				System.out.println(Expression(list,i+1));
			}
		}
	}

	// If needed calculation
	protected int Expression(List <Token> list, int i){
		int a = list.get(i).get_val()-'0';
		if (list.get(i+1).get_val()=='+' || list.get(i+1).get_val()=='-') {
			return Term(list, i);
		}
		else {
			return Factor(list,i);
		}
	}

	// +\- Calculation
	protected int Term(List<Token> list, int i) {
		int a = list.get(i).get_val()-'0'; 
		int b = list.get(i+2).get_val()-'0';
		if (list.get(i+1).get_val()=='+') return a+b;
		else return a-b;
	}

	// */ Calculation
	protected int Factor(List<Token> list, int i) {
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
