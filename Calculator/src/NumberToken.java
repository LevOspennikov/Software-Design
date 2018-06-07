
class NumberToken implements Token {
    private int number;

    NumberToken(int number) {
        this.number = number;
    }

    int getNumber() {
        return number;
    }

    @Override
    public void accept(TokenVisitor visitor) {
        visitor.visit(this);
    }
}
