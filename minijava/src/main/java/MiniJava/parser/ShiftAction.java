package MiniJava.parser;

import MiniJava.scanner.token.Token;

public class ShiftAction implements ActionStrategy {
	@Override
	public void execute(Parser parser, Token lookAhead, Action action) {
		parser.getParsStack().push(action.number);
		parser.setLookAhead(parser.getLexicalAnalyzer().getNextToken());
	}
}
