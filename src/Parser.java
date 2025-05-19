import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Parser {
    private Lexer lexer;
    private Token currentToken;
    private Map<String, Variable> symbolTable = new HashMap<>();

    public Parser(Lexer lexer) {
        this.lexer = lexer;
        this.currentToken = lexer.nextToken();
    }

    private void eat(Token.Type type) {
        if (currentToken.type == type) {
            currentToken = lexer.nextToken();
        } else {
            error("Token inesperado: " + currentToken.type + ", se esperaba: " + type);
        }
    }

    private void error(String message) {
        throw new RuntimeException("Error de sintaxis o semántica: " + message);
    }

    public void parse() {
        while (currentToken.type != Token.Type.EOF) {
            statement();
        }
    }

    private void statement() {
        if (currentToken.type == Token.Type.INT || currentToken.type == Token.Type.FLOAT) {
            declaration();
        } else if (currentToken.type == Token.Type.PRINT) {
            printStatement();
        } else {
            error("Instrucción no válida.");
        }
    }

    private void declaration() {
        String type = currentToken.value; // "int" o "float"
        eat(currentToken.type);

        if (currentToken.type != Token.Type.IDENTIFIER) {
            error("Se esperaba un nombre de variable.");
        }

        String varName = currentToken.value;
        eat(Token.Type.IDENTIFIER);

        eat(Token.Type.EQUAL);

        String value = currentToken.value;
        eat(Token.Type.NUMBER);

        eat(Token.Type.SEMICOLON);

        if (symbolTable.containsKey(varName)) {
            error("Variable ya declarada: " + varName);
        }

        if (type.equals("int") && value.contains(".")) {
            error("Tipo int no puede contener punto decimal.");
        }

        symbolTable.put(varName, new Variable(type, value));
        System.out.println("Declaración válida: " + type + " " + varName + " = " + value);
    }

    private void printStatement() {
        eat(Token.Type.PRINT);
        eat(Token.Type.LPAREN);

        String left = currentToken.value;
        eat(Token.Type.IDENTIFIER);

        eat(Token.Type.PLUS);

        String right = currentToken.value;
        eat(Token.Type.IDENTIFIER);

        eat(Token.Type.RPAREN);
        eat(Token.Type.SEMICOLON);

        if (!symbolTable.containsKey(left)) {
            error("Variable no declarada: " + left);
        }
        if (!symbolTable.containsKey(right)) {
            error("Variable no declarada: " + right);
        }

        Variable varLeft = symbolTable.get(left);
        Variable varRight = symbolTable.get(right);

        double valLeft = Double.parseDouble(varLeft.value);
        double valRight = Double.parseDouble(varRight.value);
        double result = valLeft + valRight;

        String resultStr = "Resultado: " + result;
        System.out.println(resultStr);

        try {
            Files.writeString(Path.of("test/output.txt"), resultStr);
        } catch (IOException e) {
            throw new RuntimeException("No se pudo escribir en el archivo de salida.");
        }
    }
}