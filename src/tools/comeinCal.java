package tools;

public class comeinCal {
	public static float thisYearAdd = 1;
	public static float rate = 1.2f;
	public static float thisYearTotal = 0;
	public static float shouYi = 1.1f;
	public static float lastYearAll = 0;

	public static void main(String[] args) {
		for (int i = 0; i < 10; i++) {
			thisYearAdd *= rate;
			thisYearTotal += thisYearAdd + lastYearAll * shouYi;
			lastYearAll = thisYearTotal;
			System.out.format("%4.2f\n", lastYearAll);
		}
	}
}