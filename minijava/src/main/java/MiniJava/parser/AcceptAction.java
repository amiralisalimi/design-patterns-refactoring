package MiniJava.parser;

import MiniJava.scanner.token.Token;

public class AcceptAction implements ActionStrategy {
	@Override
	public void execute(Parser parser, Token lookAhead, Action action) {
		parser.setFinish(true);
	}
}
