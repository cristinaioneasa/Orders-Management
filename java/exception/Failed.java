package exception;

public class Failed extends RuntimeException {
    public Failed(String s) {
        super(s);
    }
}
