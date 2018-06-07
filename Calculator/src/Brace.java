
class Brace implements Token {
    private char brace;

    Brace(char brace) {
        this.brace = brace;
    }

    char getBrace() {
        return brace;
    }

    @Override
    public void accept(TokenVisitor visitor) {
        visitor.visit(this);
    }
}
