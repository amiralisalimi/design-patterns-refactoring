package MiniJava.compiler;

import java.io.FileNotFoundException;

public interface CompilerFacade {
	void compile(String input) throws FileNotFoundException;
}