package MiniJava.parser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Map;
import java.util.Stack;

import MiniJava.Log.Log;
import MiniJava.codeGenerator.CodeGenerator;
import MiniJava.errorHandler.ErrorHandler;
import MiniJava.scanner.lexicalAnalyzer;
import MiniJava.scanner.token.Token;

public class Parser {
	private ArrayList<Rule> rules;
	private Stack<Integer> parsStack;
	private ParseTable parseTable;
	private lexicalAnalyzer lexicalAnalyzer;
	private CodeGenerator cg;
	private Token lookAhead;
	private boolean finish;

	private Map<act, ActionStrategy> strategies = Map.of(act.shift, new ShiftAction(), act.reduce, new ReduceAction(),
			act.accept, new AcceptAction());

	public Parser() {
		parsStack = new Stack<Integer>();
		parsStack.push(0);
		try {
			parseTable = new ParseTable(Files.readAllLines(Paths.get("src/main/resources/parseTable")).get(0));
		} catch (Exception e) {
			e.printStackTrace();
		}
		rules = new ArrayList<Rule>();
		try {
			for (String stringRule : Files.readAllLines(Paths.get("src/main/resources/Rules"))) {
				rules.add(new Rule(stringRule));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		cg = new CodeGenerator();
	}

	public void startParse(java.util.Scanner sc) {
		lexicalAnalyzer = new lexicalAnalyzer(sc);
		lookAhead = lexicalAnalyzer.getNextToken();
		finish = false;
		Action currentAction;
		while (!finish) {
			try {
				Log.print(lookAhead.toString() + "\t" + parsStack.peek());
				currentAction = parseTable.getActionTable(parsStack.peek(), lookAhead);
				Log.print(currentAction.toString());
				strategies.get(currentAction.action).execute(this, lookAhead, currentAction);

				Log.print("");
			} catch (Exception ignored) {
				ignored.printStackTrace();
			}
		}
		if (!ErrorHandler.hasError)
			cg.printMemory();
	}

	protected Stack<Integer> getParsStack() {
		return parsStack;
	}

	protected lexicalAnalyzer getLexicalAnalyzer() {
		return lexicalAnalyzer;
	}

	protected ArrayList<Rule> getRules() {
		return rules;
	}

	protected ParseTable getParseTable() {
		return parseTable;
	}

	protected CodeGenerator getCodeGenerator() {
		return cg;
	}

	protected Token getLookAhead() {
		return lookAhead;
	}

	protected void setFinish(boolean finish) {
		this.finish = finish;
	}

	protected void setLookAhead(Token lookAhead) {
		this.lookAhead = lookAhead;
	}
}
