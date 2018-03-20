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
		Parser.index = 0;
	}

	// Whole line
	// Need to change to return int!
	protected void Line(LinkedList <Token> list) {
		int a;
		for (Parser.index=0; index<list.size() ; index++) {
			Token t = list.get(index);
			if (t.get_type()==TokenType.INTEGER) {
				System.out.println("Result: " + Expression(list,index)); // Getting here twice becuase of the previous line condition.
				index = index+2;
			}
			else if (t.get_type()==TokenType.IDENTIFIER) {
				char tav = t.get_val();
				index++;
				t = list.get(index); // Next token
				if (t.get_val()=='=') {
					index++;
					t = list.get(index); // Next token	
					if (t.get_type()==TokenType.INTEGER && list.get(index+1).type!=TokenType.OPERNAD) {
						a = t.get_val()-'0';
						this.arr[tav-'a'] = t.get_val()-'0';
						System.out.println(tav + "=" + a + "; Has been excuted.");
					}
					else {
						int temp = Expression(list, index);
						System.out.println(temp);
						a = t.get_val()-'0';
						this.arr[tav-'a'] = temp;
						System.out.println(tav + "=" + temp + "; Has been excuted.");
					}
				}
				else Expression(list, index);
			}
			if (t.get_val()==';') {
				index = list.size()-1;
			}
		}
	}

	// If needed calculation
	protected int Expression(List <Token> list, int index){
		if (list.get(index).get_type()==TokenType.BRACKET) return 0;
		if (list.get(index+1).get_val()=='+' || list.get(index+1).get_val()=='-') {
			return Term(list, index);
		}
		else {
			return Factor(list,index);
		}
	}

	// +\- Calculation
	protected int Term(List<Token> list, int index) {
		int a = list.get(index).get_val()-'0'; 
		int b = list.get(index+2).get_val()-'0';
		Parser.index = index+2;
		if (list.get(index+1).get_val()=='+') return a+b;
		else return a-b;
	}

	// */ Calculation
	protected int Factor(List<Token> list, int index) {
		int a = list.get(index).get_val()-'0'; 
		int b = list.get(index+2).get_val()-'0';
		Parser.index = index+2;
		if (list.get(index+1).get_val()=='*') return a*b;
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
