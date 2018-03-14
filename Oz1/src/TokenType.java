

public enum TokenType {
	DIGIT, IDENTIFIER, EXPRESION, TERM, FACTOR;
}

/*
 * line = exp ";" | identifier ";" exp ";".
identifier = letter.
letter = a|b|c|d|e|f|g|h|i|j|k|l|m|n|o|p|q|r|s|t|u|v|w|x|y|z.
integer = digit {digit}.
digit = &quot;0&quot; | &quot;1&quot; | &quot;2&quot; | &quot;3&quot; | &quot;4&quot; | &quot;5&quot; | &quot;6&quot; | &quot;7&quot; | &quot;8&quot; | &quot;9&quot;.
exp = term { (&quot;+&quot; | &quot;-&quot; ) term }.
term = factor { (&quot;*&quot; | &quot;/&quot; ) factor }.
factor = identifier | integer | - factor | &quot;(&quot; exp &quot;)&quot;.
 */

	
