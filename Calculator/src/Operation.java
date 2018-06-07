
class Operation implements Token {
    private char operation;

    Operation(char operation) {
        this.operation = operation;
    }

    char getOperation() {
        return operation;
    }

    @Override
    public void accept(TokenVisitor visitor) {
        visitor.visit(this);
    }
}
