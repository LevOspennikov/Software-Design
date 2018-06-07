import java.util.ArrayList;
import java.util.List;
import java.util.Stack;


class ParseVisitor implements TokenVisitor {
    private List<Token> polishNotation;
    private Stack<Token> stack;

    ParseVisitor() {
        polishNotation = new ArrayList<>();
        stack = new Stack<>();
    }

    @Override
    public void visit(NumberToken token) {
        polishNotation.add(token);
    }

    @Override
    public void visit(Brace token) {
        if (token.getBrace() == '(') {
            stack.push(token);
        } else {
            while (true) {
                if (stack.isEmpty()) {
                    throw new RuntimeException("Wrong expression\n");
                }
                Token peek = stack.peek();
                stack.pop();
                if (!(peek instanceof Brace)) {
                    polishNotation.add(peek);
                } else {
                   break;
                }
            }
        }
    }

    private int priority(Operation token) {
        if (token.getOperation() == '+' || token.getOperation() == '-') {
            return 0;
        }
        if (token.getOperation() == '*' || token.getOperation() == '/') {
            return 1;
        }
        return -1;
    }

    @Override
    public void visit(Operation token) {
        while (!stack.isEmpty()) {
            Token op = stack.peek();
            if (!(op instanceof Operation)) {
                break;
            }
            if (priority((Operation) op) >= priority(token)) {
                polishNotation.add(op);
                stack.pop();
            } else {
                break;
            }
        }
        stack.push(token);
    }

    List<Token> toPolish(List<Token> tokens) {
        for (Token token : tokens) {
            token.accept(this);
        }
        while (!stack.isEmpty()) {
            Token token = stack.peek();
            stack.pop();
            if (token instanceof Brace) {
                throw new RuntimeException("There is a problem with parenthesis in expression");
            }
            polishNotation.add(token);
        }
        return polishNotation;
    }
}
