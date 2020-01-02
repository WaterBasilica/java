package bean;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class YakuhinBean extends DbConnection {
	//フィールド
	private String yakuhin ="";
	private String yakuhin_kbn ="";
	private int page = 0;

	public String getYakuhin() {
		return yakuhin;
	}
	public void setYakuhin(String yakuhin) {
		this.yakuhin = yakuhin;
	}
	public String getYakuhin_kbn() {
		return yakuhin_kbn;
	}
	public void setYakuhin_kbn(String yakuhin_kbn) {
		this.yakuhin_kbn = yakuhin_kbn;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}

	//ページネーション用ページ
	public int getPages() {
		int page = 0;

		StringBuilder sql = new StringBuilder();
		StringBuilder s_index = new StringBuilder();

		//SQL
		sql.append("SELECT COUNT(*) cnt ");
		sql.append("FROM mst_shohin ");
		sql.append("WHERE delete_flg = '0' ");

		//yakuhin
		if(yakuhin.length() > 0) {
			sql.append("AND (jan_code = ? OR hanbai_name LIKE ?) ");
			s_index.append("11");
		}

		//yakuhin_kbn
		if(yakuhin_kbn.length() > 0) {
			sql.append("AND yakuhin_kbn = ? ");
			s_index.append("2");
		}

		open();
		try {
			ps = con.prepareStatement(sql.toString());

			int i_index;
			//yakuhin
			i_index = s_index.indexOf("1");
			if(i_index >= 0) {
				ps.setString(i_index +1, yakuhin);
				ps.setString(i_index +2, "%" + yakuhin + "%");
			}

			//yakuhin_kbn
			i_index = s_index.indexOf("2");
			if(i_index >= 0) {
				ps.setString(i_index +1, yakuhin_kbn);
			}

			rs = ps.executeQuery();
			rs.next();

			int rec = rs.getInt("cnt");
			page = rec / 25;
			if(rec % 25 > 0) page++;

			close();
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		return page;
	}

	//ページネーション用ページ
		public List<Map<String,String>> getList() {
			List<Map<String,String>> list = new ArrayList<Map<String,String>>();

			StringBuilder sql = new StringBuilder();
			StringBuilder s_index = new StringBuilder();

			//SQL
			sql.append("SELECT ");
			sql.append("jan_code, ");
			sql.append("yj_code, ");
			sql.append("hanbai_name, ");
			sql.append("yakuhin_kbn, ");
			sql.append("CASE yakuhin_kbn ");
			sql.append("WHEN '1' THEN '薬品' ");
			sql.append("WHEN '2' THEN 'ＯＴＣ' ");
			sql.append("WHEN '4' THEN '特材' ");
			sql.append("ELSE '不明' END yakuhin_kbn_name ");
			sql.append("FROM mst_shohin ");
			sql.append("WHERE delete_flg = '0' ");

			//yakuhin
			if(yakuhin.length() > 0) {
				sql.append("AND (jan_code = ? OR hanbai_name LIKE ?) ");
				s_index.append("11");
			}

			//yakuhin_kbn
			if(yakuhin_kbn.length() > 0) {
				sql.append("AND yakuhin_kbn = ? ");
				s_index.append("2");
			}

			//limit
			sql.append("LIMIT ?,25 ");
			s_index.append("3");

			open();
			try {
				ps = con.prepareStatement(sql.toString());

				int i_index;
				//yakuhin
				i_index = s_index.indexOf("1");
				if(i_index >= 0) {
					ps.setString(i_index +1, yakuhin);
					ps.setString(i_index +2, "%" + yakuhin + "%");
				}

				//yakuhin_kbn
				i_index = s_index.indexOf("2");
				if(i_index >= 0) {
					ps.setString(i_index +1, yakuhin_kbn);
				}

				//limit
				i_index = s_index.indexOf("3");
				ps.setInt(i_index +1, (page -1) * 25);

				rs = ps.executeQuery();
				while(rs.next()) {
					Map<String,String> map = new HashMap<String,String>();
					map.put("jan_code", rs.getString("jan_code"));
					map.put("yj_code", rs.getString("yj_code"));
					map.put("hanbai_name", rs.getString("hanbai_name"));
					map.put("yakuhin_kbn", rs.getString("yakuhin_kbn"));
					map.put("yakuhin_kbn_name", rs.getString("yakuhin_kbn_name"));
					list.add(map);
				}

				close();
			} catch (SQLException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}

			return list;
		}


}
