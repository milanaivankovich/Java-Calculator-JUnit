package calculator;

@SuppressWarnings("serial")
public class NumberNotInAreaException extends Exception{

	public NumberNotInAreaException(String msg) {
		super(msg);
	}
}
