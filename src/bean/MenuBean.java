package bean;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MenuBean extends DbConnection {
	//フィールド
	private List<Map<String,String>> list;

	//getter,setter
	public List<Map<String, String>> getList() {
		return list;
	}

	public void setList(List<Map<String, String>> list) {
		this.list = list;
	}

	//コンストラクタ
	public MenuBean(String kengen_code) {
		List<Map<String,String>> list = new ArrayList<Map<String,String>>();

		StringBuilder sql = new StringBuilder();
		sql.append("select ");
		sql.append("mm.menu_uri,");
		sql.append("mm.menu_name ");
		sql.append("from ");
		sql.append("mst_menu_kengen as mmk join mst_menu as mm ");
		sql.append("on mmk.menu_code = mm.menu_code ");
		sql.append("where ");
		sql.append("mmk.kengen_code = ? ");
		sql.append("and mmk.delete_flg = '0' ");
		sql.append("order by mmk.hyoji_jun");

		open();
		try {
			ps = con.prepareStatement(sql.toString());
			ps.setString(1, kengen_code);
			//System.out.println(ps.toString());
			rs = ps.executeQuery();

			while(rs.next()) {
				Map<String,String> map = new HashMap<String,String>();
				map.put("menu_uri", rs.getString("menu_uri"));
				map.put("menu_name", rs.getString("menu_name"));

				list.add(map);
			}
			close();

		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		this.list = list;


	}
	public MenuBean() {}

}
