Readme of programming assignment for CSCI3136 CSCI 3136: Principles of Programming Languages

Summary:
According to the introduction in class and textbook, in my understanding, this assignment will help us to have a more deep understanding about regular languages, finite automata and lexical analysis. The basic idea about the implementation is as followings:
1.get the source code of TOY language as an input for this scanner;
2.Split the source code to words delimited by spaces;
3.For the words, using different regular expression to evaluate if they are valid tokens;
a)Convert regular expression to NFA
b)NFA -> DFA
c)Minimize the DFA
d)For each word, if it can reach the end state of one DFA, then means it’s a valid token
e)Store all of the valid tokens
4.provide a hasNext() and next() methods to let the parser to get all of valid tokens.

Explanations:
According to the teacher’s email, using embed-ed regular expression in Java is allowed. But I found if using it, the most of works lose challenge. So, we try to implement a traditional method to do this assignment rather than using Java library to evaluate each word.
This implementation is finished with Java SE 1.8 in eclipse oxygen. The all of source code have been attached with this readme.txt file.
In the same folder, two test files are provided as well:
test.toy which is a well-formatted TOY source code which comes from assignment PDF file.the content is as followings:

fun fibonacci // n -- F_n
// TOY supports local functions
fun fib '$-9li // n-i F_{i-1} F_i -- F_n
rotl dup 0 =
[ drop swap drop ]
[ 1 - rotr dup rotl + fib ] ??
.
0 1 fib
.
After scanning the above source code using Scanner.java, the output is as following:
Please enter the name of a TOY program file (by default is test.toy): 

fun(fun) id(fibonacci) fun(fun) id(fib) id(rotl) id(dup) int(0) id(=) lambdabegin([) id(drop) id(swap) id(drop) lambdaend(]) lambdabegin([) int(1) id(-) id(rotr) id(dup) id(rotl) id(+) id(fib) lambdaend(]) id(??) end(.) int(0) int(1) id(fib) end(.) 

Another test file named: test1.toy which includes an illegal word: '$-9li.
This word is illegal because it begins with ‘ but not end with another ‘. So, it is not a char, and it is not a id as well. According to teacher’s email with a new constrains with id:
- Identifiers are not allowed to start with ' or " to avoid clashes with character and string literals.

Structure of source code:
Scanner.java main class
TokenTypeFinder.java  after reading file and split into words, to check if one word is a token
NFAMatcher.java   Init NFA for each lexical element in TOY: keywords, id, symbol and etc.
NFADigram.java    Store the NFA
NFAFactory.java   manipulate the NFA
MFANodes.java     Store the nodes in one NFA
REgexProvider.java convert regular expression
Token.java        TOY token definition
TokenDefinition.java  meta data definition

RegexUtils.java   tools
NFAElementSet.java   data dictionary
NFAUtils.java     tools
REgexFlag.java    data dictionary
TOCOnstants.java  global definition
WordDefinition.java  word defintion in TOY source code
