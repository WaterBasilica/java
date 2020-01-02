package bean;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShainBean extends DbConnection {
	//フィールド
	private String shain_code = "";
	private String shain_name = "";
	private String kengen_name = "";
	private String kengen_code = "";
	private String[] k_name = {"店舗", "事務局"};
	private String[] k_code = {"001", "0002"};
	private List<Map<String,String>> kengenList;
	private String shain = "";
	private int page = 0;

	//コンストラクタ
	public ShainBean() {
		KengenBean kb = new KengenBean();
		kengenList = kb.getKengen();
	}

	public String getShain_code() {
		return shain_code;
	}
	public void setShain_code(String shain_code) {
		this.shain_code = shain_code;
	}
	public String getShain_name() {
		return shain_name;
	}
	public void setShain_name(String shain_name) {
		this.shain_name = shain_name;
	}
	public String getKengen_name() {
		return kengen_name;
	}
	public void setKengen_name(String kengen_name) {
		this.kengen_name = kengen_name;
	}
	public String getKengen_code() {
		return kengen_code;
	}
	public void setKengen_code(String kengen_code) {
		this.kengen_code = kengen_code;
	}
	public String[] getK_name() {
		return k_name;
	}
	public void setK_name(String[] k_name) {
		this.k_name = k_name;
	}
	public String[] getK_code() {
		return k_code;
	}
	public void setK_code(String[] k_code) {
		this.k_code = k_code;
	}
	public List<Map<String, String>> getKengenList() {
		return kengenList;
	}
	public void setKengenList(List<Map<String, String>> kengenList) {
		this.kengenList = kengenList;
	}
	public String getShain() {
		return shain;
	}
	public void setShain(String shain) {
		this.shain = shain;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}

	//ページ数を返すメソッド
	public int getPages() {
		int page = 0;

		StringBuilder sql = new StringBuilder();
		StringBuilder s_index = new StringBuilder();

		sql.append("SELECT COUNT(*) cnt ");
		sql.append("FROM mst_shain ");
		sql.append("WHERE delete_flg = '0' ");

		//shain
		if(shain.length() > 0) {
			sql.append("AND (shain_code = ? OR shain_name LIKE ?) ");
			s_index.append("11");

		}
		//kengen_code
		if(kengen_code.length() > 0) {
			sql.append("AND kengen_code = ? ");
			s_index.append("2");
		}

		open();
		try {
			ps = con.prepareStatement(sql.toString());

			int i_index;
			//shain
			i_index = s_index.indexOf("1");
			if(i_index >= 0) {
				ps.setString(i_index +1, shain);
				ps.setString(i_index +2, "%" + shain + "%");
			}

			//kengen_code
			i_index = s_index.indexOf("2");
			if(i_index >= 0) {
				ps.setString(i_index +1, kengen_code);
			}

			rs = ps.executeQuery();
			rs.next();

			int rec = rs.getInt("cnt");

			page = rec / 25;

			if(rec % 25 > 0) page++;


		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		close();
		return page;
	}

	//リストを返すメソッド
	public List<Map<String,String>> getList() {
		List<Map<String,String>> list = new ArrayList<Map<String,String>>();

		StringBuilder sql = new StringBuilder();
		StringBuilder s_index = new StringBuilder();

		sql.append("SELECT  ms.shain_code, ms.shain_name, ");
		sql.append("ms.shain_name_kana, mk.kengen_name ");
		sql.append("FROM mst_shain ms JOIN mst_kengen mk ");
		sql.append("ON ms.kengen_code = mk.kengen_code ");
		sql.append("WHERE ms.delete_flg = '0' ");

		//shain
		if(shain.length() > 0) {
			sql.append("AND (ms.shain_code = ? OR ms.shain_name LIKE ?) ");
			s_index.append("11");

		}
		//kengen_code
		if(kengen_code.length() > 0) {
			sql.append("AND ms.kengen_code = ? ");
			s_index.append("2");
		}
		//LIMIT
		sql.append("LIMIT ?, 25 ");
		s_index.append("3");

		open();
		try {
			ps = con.prepareStatement(sql.toString());

			int i_index;
			//shain
			i_index = s_index.indexOf("1");
			if(i_index >= 0) {
				ps.setString(i_index +1, shain);
				ps.setString(i_index +2, "%" + shain + "%");
			}
			//kengen_code
			i_index = s_index.indexOf("2");
			if(i_index >= 0) {
				ps.setString(i_index +1, kengen_code);
			}
			//LIMIT
			i_index = s_index.indexOf("3");
			ps.setInt(i_index +1, (page -1) *25);
			rs = ps.executeQuery();

			while(rs.next()) {
				Map<String,String> map = new HashMap<String,String>();
				map.put("shain_code", rs.getString("shain_code"));
				map.put("shain_name", rs.getString("shain_name"));
				map.put("shain_name_kana", rs.getString("shain_name_kana"));
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
