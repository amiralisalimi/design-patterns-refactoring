package MiniJava.parser;

import MiniJava.scanner.token.Token;

public interface ActionStrategy {
    void execute(Parser parser, Token lookAhead, Action action);
}
