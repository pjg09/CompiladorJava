// Lexer.java
import java.util.*;

public class Lexer {
    private String input;
    private int pos = 0;

    public Lexer(String input) {
        this.input = input;
    }

    public Token nextToken() {
        while (pos < input.length()) {
            char current = input.charAt(pos);

            if (Character.isWhitespace(current)) {
                pos++;
                continue;
            }

            if (Character.isLetter(current)) {
                StringBuilder id = new StringBuilder();
                while (pos < input.length() && Character.isLetterOrDigit(input.charAt(pos))) {
                    id.append(input.charAt(pos++));
                }

                String value = id.toString();
                switch (value) {
                    case "int": return new Token(Token.Type.INT, value);
                    case "float": return new Token(Token.Type.FLOAT, value);
                    case "print": return new Token(Token.Type.PRINT, value);
                    default: return new Token(Token.Type.IDENTIFIER, value);
                }
            }

            if (Character.isDigit(current)) {
                StringBuilder num = new StringBuilder();
                boolean hasDot = false;

                while (pos < input.length() && (Character.isDigit(input.charAt(pos)) || input.charAt(pos) == '.')) {
                    if (input.charAt(pos) == '.') {
                        if (hasDot) break;
                        hasDot = true;
                    }
                    num.append(input.charAt(pos++));
                }

                return new Token(Token.Type.NUMBER, num.toString());
            }

            switch (current) {
                case '=': pos++; return new Token(Token.Type.EQUAL, "=");
                case '+': pos++; return new Token(Token.Type.PLUS, "+");
                case ';': pos++; return new Token(Token.Type.SEMICOLON, ";");
                case '(': pos++; return new Token(Token.Type.LPAREN, "(");
                case ')': pos++; return new Token(Token.Type.RPAREN, ")");
                default:
                    throw new RuntimeException("Carácter no reconocido: " + current);
            }
        }

        return new Token(Token.Type.EOF, "");
    }
}