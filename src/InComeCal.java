public class InComeCal {
	public static float thisInCome = 0;
	public static float thisSalary = 0;
	public static float lastSalary = 4;
	public static float salaryIncreaseRate = 0.15f;
	public static float investProfitRate = 0.24f;
	public static float investProfit = 0;
	public static float total = 4;
	public static int years = 5;

	public static void main(String[] args) {
		for (int i = 0; i < years; i++) {
			thisSalary = lastSalary * (salaryIncreaseRate + 1);
			investProfit = total * investProfitRate;
			thisInCome = thisSalary + investProfit;
			total = thisInCome + total;
			lastSalary = thisSalary;
			String f = "第%3d年 : 储蓄额：%2.1f 利息收入：%3.1f 资产：%3.1f\n";
			System.out.format(f, i + 2, thisSalary, investProfit, total);
		}
	}
}
