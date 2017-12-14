package exception;

public class NoItemException extends Exception {

	private static final long serialVersionUID = 8125218660472803288L;
	public NoItemException() {
		System.out.println("No item");
	}
}
