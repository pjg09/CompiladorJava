// Main.java
import java.nio.file.*;

public class Main {
    public static void main(String[] args) throws Exception {
        String input = Files.readString(Path.of("test/ejemplo.txt"));
        Lexer lexer = new Lexer(input);
        Parser parser = new Parser(lexer);

        parser.parse();
        System.out.println("Compilación completada sin errores.");
    }
}