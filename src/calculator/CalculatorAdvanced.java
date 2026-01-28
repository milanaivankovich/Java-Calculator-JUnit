package calculator;

public class CalculatorAdvanced extends Calculator{
	
	public int factorial(int num) {
		if (num == 0 || num == 1)
	        return 1;
		
	    int result = 1;
	    for(int i = 1; i <= num; i++)
	    	result *=i;
	    
	    return result;
	}
	
	public int power(int base, int exponent) {
		if (exponent == 0)
			return 1;
		
		int result = 1;
        for (int i = 0; i < exponent; i++) {
            result *= base;
        }
        return result;

	}

	public void  calculateAdvanced(char action) throws NumberNotInAreaException, NotSupportedOperationException{
		int integerCurrentValue = this.getCurrentValue().intValue();
		
		if (action == '!') {
			if (integerCurrentValue < 0 || integerCurrentValue > 10)
				throw new NumberNotInAreaException("Current value is not from [0,10]");
			this.setCurrentValue((double)factorial(integerCurrentValue));
			return;
		}
		else if (action >= '0' && action <= '9') {
			int exponent = Character.getNumericValue(action);
			this.setCurrentValue((double)power(integerCurrentValue, exponent));
			return;
		}
		else
			throw new NotSupportedOperationException("Operator '" + action + "' is not supported");
	}
	
	public Boolean isArmstrong(int num) {
		int originalNumber = num;
        int sum = 0;
        int digits = String.valueOf(num).length();

        while (num > 0) {
            int digit = num % 10;
            sum += power(digit, digits);
            num /= 10;
        }
        return sum == originalNumber;
	}
	
	public Boolean isPerfect(int num) {
		int sum = 0;
        for (int i = 1; i <= num / 2; i++) { 
            if (num % i == 0) {
                sum += i;
            }
        }
        return sum == num;
	}
	
	public Boolean hacCharacteristic(char value) throws NumberNotInAreaException, NotSupportedOperationException{
		int integerCurrentValue = this.getCurrentValue().intValue();
		
		if(integerCurrentValue < 1) {
			throw new NumberNotInAreaException("The number must be greater than or equal to 1");
		}
		if (value == 'A') 
			return isArmstrong(integerCurrentValue);
		else if (value == 'P') 
			return isPerfect(integerCurrentValue);
		else 
			throw new NotSupportedOperationException("Operator '" + value + "' is not supported");
		}
}
