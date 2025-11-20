// Implements algebraic operations and the square root function without using 
// the Java operations a + b, a - b, a * b, a / b, a % b, and without calling 
// Math.sqrt. All the functions in this class operate on int values and
// return int values.

public class Algebra {
	public static void main(String args[]) {
	    // Tests some of the operations
	    System.out.println(plus(2,3));   // 2 + 3
	    System.out.println(minus(7,2));  // 7 - 2
   		System.out.println(minus(2,7));  // 2 - 7
 		System.out.println(times(3,4));  // 3 * 4
   		System.out.println(plus(2,times(4,2)));  // 2 + 4 * 2
   		System.out.println(pow(5,3));      // 5^3
   		System.out.println(pow(3,5));      // 3^5
   		System.out.println(div(12,3));   // 12 / 3    
   		System.out.println(div(5,5));    // 5 / 5  
   		System.out.println(div(25,7));   // 25 / 7
   		System.out.println(mod(25,7));   // 25 % 7
   		System.out.println(mod(120,6));  // 120 % 6    
   		System.out.println(sqrt(36));
		System.out.println(sqrt(263169));
   		System.out.println(sqrt(76123));
	}  

	// Returns x1 + x2
	public static int plus(int x1, int x2) 
	{
		if (x2 >= 0) {
			for (int i = 0; i < x2; i++) {
				x1++;
			}
			return x1;
		} else {
			// x2 is negative: add |x2| by decrementing x1
			int cnt = 0;
			int tmp = x2;
			while (tmp < 0) {
				cnt++;
				tmp++;
			}
			for (int i = 0; i < cnt; i++) x1--;
			return x1;
		}
	}

	// Returns x1 - x2
	public static int minus(int x1, int x2)
	{
		if (x2 >= 0) {
			for (int i = 0; i < x2; i++) {
				x1--;
			}
			return x1;
		} else {
			// subtracting a negative -> add
			int cnt = 0;
			int tmp = x2;
			while (tmp < 0) {
				cnt++;
				tmp++;
			}
			for (int i = 0; i < cnt; i++) x1++;
			return x1;
		}
	}

	// Returns x1 * x2
	public static int times(int x1, int x2)
	 {
		// Handle signs
		boolean negative = false;
		if (x1 < 0) {
			x1 = negate(x1);
			negative = !negative;
		}
		if (x2 < 0) {
			x2 = negate(x2);
			negative = !negative;
		}
		int result = 0;
		for (int i = 0; i < x2; i++) {
			result = plus(result, x1);
		}
		if (negative) result = negate(result);
		return result;
	}
	// Returns x^n (for n >= 0)
	public static int pow(int x, int n) 
	{
		if (n == 0) return 1;
		int result = 1;
		for (int i = 0; i < n; i++) {
			result = times(result, x);
		}
		return result;
	}

	// Returns the integer part of x1 / x2 
	public static int div(int x1, int x2)
	 {
		// Handle sign and use absolute values
		if (x2 == 0) throw new ArithmeticException("Division by zero");
		boolean negative = false;
		if (x1 < 0) { x1 = negate(x1); negative = !negative; }
		if (x2 < 0) { x2 = negate(x2); negative = !negative; }
		int result = 0;
		while (x1 >= x2) {
			x1 = minus(x1, x2);
			result = plus(result, 1);
		}
		if (negative) result = negate(result);
		return result;
	}

	// Returns x1 % x2
	public static int mod(int x1, int x2) 
	{
		if (x2 == 0) throw new ArithmeticException("Division by zero");
		// Use absolute values to compute remainder, then apply sign of dividend
		boolean negativeDividend = false;
		if (x1 < 0) { x1 = negate(x1); negativeDividend = true; }
		if (x2 < 0) x2 = negate(x2);
		while (x1 >= x2) {
			x1 = minus(x1, x2);
		}
		// x1 now holds the remainder (non-negative). Apply sign of original dividend
		if (negativeDividend) return negate(x1);
		return x1;
	}	

	// Returns the integer part of sqrt(x) 
	public static int sqrt(int x)
	 {
		if (x < 0) return -1;
		int s = 0;
		// Increment s while (s+1)^2 <= x
		while (true) {
			int sp1 = plus(s, 1);
			int sq = times(sp1, sp1);
			if (sq <= x) {
				s = sp1;
			} else break;
		}
		return s;
	}	  	  

	// Helper: negate x (i.e., return -x) using only ++/-- and comparisons
	public static int negate(int x) {
		int res = 0;
		if (x > 0) {
			for (int i = 0; i < x; i++) res--;
		} else {
			while (x < 0) { res++; x++; }
		}
		return res;
	}
}