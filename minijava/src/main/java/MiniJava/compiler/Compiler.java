package MiniJava.compiler;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import MiniJava.errorHandler.ErrorHandler;
import MiniJava.parser.Parser;

public class Compiler implements CompilerFacade {
    private final Parser parser;

    public Compiler() {
        parser = new Parser();
    }

    public void compile(String codeUrl) {
        try {
            Scanner sc = new Scanner(new File(codeUrl));
            parser.startParse(sc);
        } catch (FileNotFoundException e) {
            ErrorHandler.printError(e.getMessage());
        }
    }
}
