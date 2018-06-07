import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class Main {
    // (23 + 10) * 5 - 3 * (32 + 5) * (10 - 4 * 5) + 8 / 2
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String expression = reader.readLine();

            List<Token> tokens = new Tokenizer().tokenize(expression);
            List<Token> polish = new ParseVisitor().toPolish(tokens);
            new PrintVisitor().print(polish);

            System.out.println(new CalcVisitor().calculate(polish));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
