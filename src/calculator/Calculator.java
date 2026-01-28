package calculator;

public class Calculator {
	
	private Double currentValue;

	public Double getCurrentValue() {
		return currentValue;
	}

	public void setCurrentValue(Double currentValue) {
		this.currentValue = currentValue;
	}
	
	public Calculator() {
		this.currentValue = 0.0;
	}
	
	public void calculate(Double value, char operator) throws DivisionByZeroException, NotSupportedOperationException {
		switch (operator) {
			case '+':
				this.currentValue += value;
				break;
			case '-':
				this.currentValue -= value;
				break;
			case '*':
				this.currentValue *= value;
				break;
			case '/':
				if (value == 0) {
					throw new DivisionByZeroException();
				}
				else
					this.currentValue /= value;
				break;
			default:
				throw new NotSupportedOperationException("Operator '" + operator + "' is not supported");
		}
				
	}

}
