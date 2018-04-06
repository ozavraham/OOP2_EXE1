Assignment 1 – Lexer and Parser

// Student’s Information // 
Aviram Boniel, ID 305509770, Email: aviramb123@gmail.com
Oz Avraham, ID 203070867, Email: oz.avraham@gmail.com
Github project page: https://github.com/ozavraham/OOP2_EXE1
Second Year Computer Science Department- Ashqelon Academic College

// Assignment //
Writing program which execute commands from the user input, can read basic commands and present the the suitable output
all this by using the Lexer and Parser method’s, for example:
* Instant operation: ‘3*2;’ will print out ‘6’.
* Assign operation: ‘a=5+2;’ will store ‘7’ in variable ‘a’.
* Display operation: ‘a;’ will print out the value inside variable ‘a’.

// Goal //
Use our new knowledge in Java programming and implement a basic compiler    
with a lexer and parser that are designed by the language rules and uses the method      
of recursive descent.

// Classes //
Main, Lexer, Parser, Token, TokenType
Other files attached: Readme.txt

// Definitions // 
* TokenType: TokenType is variable of type enum, in this way we can define for each token what his type, such as:
	INTEGER: Any number between -2147483648 and 2147483647.
	OPERAND: Any operator of the following: ‘=‘ , ‘+’ , ‘-‘ , ‘ *’, ‘/‘ .
	OPEN/CLOSE BRACKET: Represent ‘(‘ or ‘)’ .
	IDENTIFIER: any character between ‘a’ to ‘z’.
	END_OF_LINE: Represent ‘;’ .			
* Token: Certain charecter from given String, contains value and type, as type is one of the TokenTypes, and value is the char himself.
* Lexer: Converting the given string into list of tokens, define the correct Type for each one and validates the command structure.
* Parser: With the given lever list, using Recursive descent parsing to figure out the command and execute as the user desire.
* UnknownTokenException: If the token is unrecognize, throw this exception.

// Running the program //
1. Navigate to Start -> run
2. Enter cmd in the commend lime
3. Set the path to your JDK bin directory, for example: set path= "C:\Program Files\Java\jdk1.8.0_161\bin".
4. Extract the zip file to your desktop.
5. At the run window enter the project source folder likewise: "cd JavaEclipseWorkspace/Oz1
6. Type "javac Main.java" (Wait for the compiler to finish compiling...)
7. Type "java Main" and follow the instuctions and enjoy your calculations.

// Algorithms // 
Recursive Descent Parsing, Wikipedia https://en.wikipedia.org/wiki/Recursive_descent_parser
Lexical Analysis, Wikipedia https://en.wikipedia.org/wiki/Lexical_analysis#Token
Tokenization, Wikipedia https://en.wikipedia.org/wiki/Tokenization_(data_security)
