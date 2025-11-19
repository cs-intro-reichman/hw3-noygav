// Computes the periodical payment necessary to pay a given loan.
public class LoanCalc {
	
	static double epsilon = 0.001;  // Approximation accuracy
	static int iterationCounter;    // Number of iterations 
	
	// Gets the lorenan data and computes the periodical payment.
	
    // Expects to get three command-line arguments: loan amount (double),
    // interest rate (double, as a percentage), and number of payments (int).  
	public static void main(String[] args)
	 {		
		// Gets the loan data
		double loan = Double.parseDouble(args[0]);
		double rate = Double.parseDouble(args[1]);
		int n = Integer.parseInt(args[2]);
		System.out.println("Loan = " + loan + ", interest rate = " + rate + "%, periods = " + n);

		// Computes the periodical payment using brute force search
		System.out.print("\nPeriodical payment, using brute force: ");
		System.out.println((int) bruteForceSolver(loan, rate, n, epsilon));
		System.out.println("number of iterations: " + iterationCounter);

		System.out.print("\nPeriodical payment, using bi-section search: ");
		System.out.println((int) bisectionSolver(loan, rate, n, epsilon));
		System.out.println("number of iterations: " + iterationCounter);
	}

	// Computes the ending balance of a loan, given the loan amount, the periodical
	// interest rate (as a 	), the number of periods (n), and the periodical payment.
	private static double endBalance(double loan, double rate, int n, double payment) {	
		double r = rate / 100.0;
		double balance = loan;
		for (int i = 0; i < n; i++) {
			balance = (balance - payment) * (1.0 + r);
		}
		return balance;
	}
	
	// Uses sequential search to compute an approximation of the periodical payment
	// that will bring the ending balance of a loan close to 0.
	// Given: the sum of the loan, the periodical interest rate (as a percentage),
	// the number of periods (n), and epsilon, the approximation's accuracy
	// Side effect: modifies the class variable iterationCounter.
    public static double bruteForceSolver(double loan, double rate, int n, double epsilon)
	 {
		iterationCounter = 0;
		double payment = loan / (double) n; // initial guess (ignores interest)
		// compute ending balance for current payment
		double bal = endBalance(loan, rate, n, payment);
		// increase payment by epsilon until ending balance <= 0 (we paid enough)
		while (bal > 0) {
			payment += epsilon;
			iterationCounter++;
			bal = endBalance(loan, rate, n, payment);
		}
		return payment;
    }
    
    // Uses bisection search to compute an approximation of the periodical payment 
	// that will bring the ending balance of a loan close to 0.
	// Given: the sum of the loan, the periodical interest rate (as a percentage),
	// the number of periods (n), and epsilon, the approximation's accuracy
	// Side effect: modifies the class variable iterationCounter.
    public static double bisectionSolver(double loan, double rate, int n, double epsilon) {  
		iterationCounter = 0;
		// lo is a payment that is too small (f(lo) > 0)
		double lo = 0.0;
		// hi is a payment that is large enough so that f(hi) <= 0
		double hi = loan; // initial guess
		while (endBalance(loan, rate, n, hi) > 0) {
			hi *= 2.0; // expand until we overpay
			iterationCounter++;
		}
		// bisection loop
		double mid;
		while ((hi - lo) > epsilon) {
			mid = (lo + hi) / 2.0;
			double fmid = endBalance(loan, rate, n, mid);
			double flo = endBalance(loan, rate, n, lo);
			// if fmid and flo have same sign, root is in (mid, hi)
			if (fmid * flo > 0) {
				lo = mid;
			} else {
				hi = mid;
			}
			iterationCounter++;
		}
		return (lo + hi) / 2.0;
    }
}