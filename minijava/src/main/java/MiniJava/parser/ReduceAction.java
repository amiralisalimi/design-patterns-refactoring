package MiniJava.parser;

import MiniJava.errorHandler.ErrorHandler;
import MiniJava.scanner.token.Token;

public class ReduceAction implements ActionStrategy {
	@Override
	public void execute(Parser parser, Token lookAhead, Action action) {
		Rule rule = parser.getRules().get(action.number);
		for (int i = 0; i < rule.RHS.size(); i++) {
			parser.getParsStack().pop();
		}
		parser.getParsStack().push(parser.getParseTable().getGotoTable(parser.getParsStack().peek(), rule.LHS));
		try {
			parser.getCodeGenerator().semanticFunction(rule.semanticAction, lookAhead);
		} catch (Exception e) {
			ErrorHandler.printError("Code Generator Error");
		}
	}
}
