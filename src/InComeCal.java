import java.util.Formatter;

public class InComeCal {
	public static float thisInCome = 0;
	public static float thisSalary = 4;
	public static float lastSalary = 0;
	public static float salaryIncreaseRate = 0.20f;
	public static float investProfitRate = 0.24f;
	public static float investProfit = 0;
	public static float total = 0;
	public static int years = 5;

	public static void main(String[] args) {
		String s = "%5d: 储蓄:%5.1f  利息:%5.1f  资产:%6.1f\n";
		Formatter f = new Formatter(System.out);
		for (int i = 0; i < years; i++) {

			if (i == 0) {
				total = 0;
				lastSalary = 0;
			} else {
				thisSalary = lastSalary * (salaryIncreaseRate + 1);
			}

			investProfit = total * investProfitRate;
			thisInCome = thisSalary + investProfit;
			total = thisInCome + total;
			lastSalary = thisSalary;
			f.format(s, i + 1, thisSalary, investProfit, total);

		}
	}
}
