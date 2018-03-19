import java.util.LinkedList;
import java.util.List;

//Parser checking line intactness, calculate the score and print it, or assign into varaible

public class Parser {
	
	public Parser (){
		
	}

	protected void Line(LinkedList <Token> list) {
		for (int i=0; i<list.size() ; i++) {
			Token t = list.get(i);
			if (t.get_type()==TokenType.INTEGER) {
				System.out.println(Expression(list)); // Getting here twice becuase of the previous line condition.
				i = i+2;
			}
			else if (t.get_type()==TokenType.IDENTIFIER) {
				t = list.get(i+1); // Next token
				if (t.get_val()=='=') {
					t = list.get(i+1); // Next token	
					Expression((LinkedList<Token>)list.subList(i+1, list.size()-1));
				}
				else Expression((LinkedList<Token>)list.subList(i+1, list.size()-1));
			}
			if (t.get_val()==';') {
				t= list.get(i+1); // Next token
			}
		}
	}

	protected int Expression(LinkedList <Token> list){
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

	protected int Term(List<Token> list) {
		int i=0;
		int a = list.get(i).get_val()-'0'; 
		int b = list.get(i+2).get_val()-'0';
		if (list.get(i+1).get_val()=='+') return a+b;
		else return a-b;
	}
	
	protected int Factor(List<Token> list) {
		int i=0;
		int a = list.get(i).get_val()-'0'; 
		int b = list.get(i+2).get_val()-'0';
		if (list.get(i+1).get_val()=='*') return a*b;
		else return (a/b);
	}
	
}
