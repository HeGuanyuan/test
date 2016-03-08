import java.sql.ResultSet;
import java.sql.SQLException;

import tools.DBHelper;

public class Main {
	static final int width = 15;
	static DBHelper db = null;
	static ResultSet ret = null;
	static StringBuilder sqlBuilder = new StringBuilder();

	public static void main(String[] args) {

		sqlBuilder.append("SELECT ");
//		sqlBuilder.append("Vendors.vend_name,prod_name ");
//		sqlBuilder.append("FROM Vendors,Products ");
//		sqlBuilder.append("WHERE Vendors.vend_id = Products.vend_id");
		
		sqlBuilder.append("* FROM qdm114750388_db.a ");
		

		sqlBuilder.append(";");

		db = new DBHelper(sqlBuilder.toString());

		try {
			/** 执行语句，得到结果集 */
			ret = db.pst.executeQuery();

			/** 显示表头 */
			int count = ret.getMetaData().getColumnCount();
			String f = "%" + width + "s | ";
			for (int i = 1; i <= count; i++) {
				System.out.format(f, ret.getMetaData().getColumnName(i));
			}
			System.out.println("");
			for (int j = 0; j < (width + 3) * count; j++) {
				System.out.print("-");
			}
			System.out.println("");

			/** 显示数据 */
			while (ret.next()) {
				for (int i = 1; i <= count; i++) {
					System.out.format("%" + width + "s | ", ret.getString(i));
				}
				System.out.println("");
			}
			ret.close();
			db.close();// 关闭连接

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}