package MiniJava;

import MiniJava.compiler.Compiler;

public class Main {
	public static void main(String[] args) {
		Compiler compiler = new Compiler();
		compiler.compile("src/main/resources/code");
	}
}
