package bean;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KengenBean extends DbConnection {

	//権限リスト作成
	public List<Map<String,String>> getKengen() {
		List<Map<String,String>> list = new ArrayList<Map<String,String>>();

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT kengen_code, kengen_name ");
		sql.append("FROM mst_kengen ");
		sql.append("WHERE delete_flg = '0' ");

		open();
		try {
			ps = con.prepareStatement(sql.toString());
			rs = ps.executeQuery();

			while(rs.next()) {
				Map<String,String> map = new HashMap<String,String>();
				map.put("kengen_code", rs.getString("kengen_code"));
				map.put("kengen_name", rs.getString("kengen_name"));
				list.add(map);
			}
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		close();

		return list;
	}

}
