package MiniJava.semantic.symbol;

import MiniJava.codeGenerator.Address;
import MiniJava.codeGenerator.Memory;
import MiniJava.codeGenerator.TypeAddress;
import MiniJava.codeGenerator.varType;
import MiniJava.errorHandler.ErrorHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SymbolTable {
    private Map<String, Klass> klasses;
    private Map<String, Address> keyWords;
    private Memory mem;
    private SymbolType lastType;

    public SymbolTable(Memory memory) {
        mem = memory;
        klasses = new HashMap<>();
        keyWords = new HashMap<>();
        keyWords.put("true", new Address(1, varType.Bool, TypeAddress.Imidiate));
        keyWords.put("false", new Address(0, varType.Bool, TypeAddress.Imidiate));
    }

    public boolean fieldExists(String fieldName, String className) {
        return klasses.get(className).getFields().containsKey(fieldName);
    }

    public void setLastType(SymbolType type) {
        lastType = type;
    }

    public void addClass(String className) {
        if (klasses.containsKey(className)) {
            ErrorHandler.printError("This class already defined");
        }
        klasses.put(className, new Klass());
    }

    public void addField(String fieldName, String className) {
        klasses.get(className).getFields().put(fieldName, new Symbol(lastType, mem.getDateAddress()));
    }

    public void addMethod(String className, String methodName, int address) {
        if (klasses.get(className).getMethods().containsKey(methodName)) {
            ErrorHandler.printError("This method already defined");
        }
        klasses.get(className).getMethods().put(methodName, new Method(address, lastType));
    }

    public void addMethodParameter(String className, String methodName, String parameterName) {
        klasses.get(className).getMethods().get(methodName).addParameter(parameterName);
    }

    public void addMethodLocalVariable(String className, String methodName, String localVariableName) {
        Method method = klasses.get(className).getMethods().get(methodName);
        if (method.hasLocalVariable(localVariableName)) {
            ErrorHandler.printError("This variable already defined");
        } else {
            method.defineLocalVariable(localVariableName, lastType);
        }
    }
    

    public void setSuperClass(String superClass, String className) {
        klasses.get(className).superClass = klasses.get(superClass);
    }

    public Address get(String keywordName) {
        return keyWords.get(keywordName);
    }

    public Symbol get(String fieldName, String className) {
//        try {
        return klasses.get(className).getField(fieldName);
//        }catch (NullPointerException n)
//        {
//            n.printStackTrace();
//            return null;
//        }
    }

    public Symbol get(String className, String methodName, String variable) {
        Symbol res = klasses.get(className).getMethods().get(methodName).getVariable(variable);
        if (res == null) res = get(variable, className);
        return res;
    }

    public Symbol getNextParam(String className, String methodName) {
        return klasses.get(className).getMethods().get(methodName).getNextParameter();
    }

    public void startCall(String className, String methodName) {
//        try {
        klasses.get(className).getMethods().get(methodName).reset();
//        }catch (NullPointerException n)
//        {
//            n.printStackTrace();
//        }
    }

    public int getMethodCallerAddress(String className, String methodName) {
        return klasses.get(className).getMethods().get(methodName).callerAddress;
    }

    public int getMethodReturnAddress(String className, String methodName) {
        return klasses.get(className).getMethods().get(methodName).returnAddress;
    }

    public SymbolType getMethodReturnType(String className, String methodName) {
//        try {
        return klasses.get(className).getMethods().get(methodName).returnType;
//        }catch (NullPointerException ed){
//            ed.printStackTrace();
//            return null;
//        }

    }

    public int getMethodAddress(String className, String methodName) {
        return klasses.get(className).getMethods().get(methodName).codeAddress;
    }


    class Klass {
        private Map<String, Symbol> fields;
        private Map<String, Method> methods;
        private Klass superClass;
    
        public Klass() {
            fields = new HashMap<>();
            methods = new HashMap<>();
        }
    
        public Klass getSuperClass() {
            return superClass;
        }
    
        public void setSuperClass(Klass superClass) {
            this.superClass = superClass;
        }
    
        public Symbol getField(String fieldName) {
            if (fields.containsKey(fieldName)) {
                return fields.get(fieldName);
            }
            Klass parent = getSuperClass();
            if (parent != null) {
                return parent.getField(fieldName);
            }
            return null;
        }

        public Map<String, Method> getMethods() {
            return methods;
        }

        public Map<String, Symbol> getFields() {
            return fields;
        }
    }    

    class Method {
        private int codeAddress;
        private Map<String, Symbol> parameters;
        private Map<String, Symbol> localVariables;
        private List<String> orderedParameters;
        private int callerAddress;
        private int returnAddress;
        private SymbolType returnType;
        private int index;

        public Method(int codeAddress, SymbolType returnType) {
            this.codeAddress = codeAddress;
            this.returnType = returnType;
            this.orderedParameters = new ArrayList<>();
            this.returnAddress = mem.getDateAddress();
            this.callerAddress = mem.getDateAddress();
            this.parameters = new HashMap<>();
            this.localVariables = new HashMap<>();
        }

        private Symbol getNextParameter() {
            return parameters.get(getOrderedParameters().get(index++));
        }

        private void reset() {
            index = 0;
        }

        private List<String> getOrderedParameters() {
            return orderedParameters;
        }
        
        private Map<String, Symbol> getParameters() {
            return parameters;
        }

        private Symbol getVariable(String variableName) {
            Symbol symbol = getFromParameters(variableName);
            if (symbol != null) return symbol;

            symbol = getFromLocalVariables(variableName);
            return symbol;
        }

        private Symbol getFromParameters(String variableName) {
            return getParameters().get(variableName);
        }

        private Symbol getFromLocalVariables(String variableName) {
            return getLocalVariables().get(variableName);
        }

        private void addParameter(String parameterName) {
            getParameters().put(parameterName, new Symbol(lastType, mem.getDateAddress()));
            getOrderedParameters().add(parameterName);
        }

        private Map<String, Symbol> getLocalVariables() {
            return localVariables;
        }

        private boolean hasLocalVariable(String name) {
            return getLocalVariables().containsKey(name);
        }
    
        private void defineLocalVariable(String name, SymbolType type) {
            getLocalVariables().put(name, new Symbol(type, mem.getDateAddress()));
        }
    }


}

//class Symbol{
//    public SymbolType type;
//    public int address;
//    public Symbol(SymbolType type , int address)
//    {
//        this.type = type;
//        this.address = address;
//    }
//}
//enum SymbolType{
//    Int,
//    Bool
//}