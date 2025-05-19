// Token.java
public class Token {
    public enum Type {
        INT, FLOAT, PRINT, IDENTIFIER, NUMBER, EQUAL, PLUS, SEMICOLON, LPAREN, RPAREN, EOF
    }

    public final Type type;
    public final String value;

    public Token(Type type, String value) {
        this.type = type;
        this.value = value;
    }

    @Override
    public String toString() {
        return type + "('" + value + "')";
    }
}