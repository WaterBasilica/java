package bean;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Torihikisakibean extends DbConnection {
	private String torihikisaki = "";
	private String torihikisaki_kbn = "";
	private int page = 0;

	public String getTorihikisaki() {
		return torihikisaki;
	}

	public void setTorihikisaki(String torihikisaki) {
		this.torihikisaki = torihikisaki;
	}

	public String getTorihikisaki_kbn() {
		return torihikisaki_kbn;
	}

	public void setTorihikisaki_kbn(String torihikisaki_kbn) {
		this.torihikisaki_kbn = torihikisaki_kbn;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	//店舗選択部品用データ取得
	public List<Map<String,String>> getTenpoData() {
		List<Map<String, String>> list = new ArrayList<Map<String,String>>();

		//SQL作成
		StringBuilder sql = new StringBuilder();
		sql.append("select ");
		sql.append("torihikisaki_code, ");
		sql.append("torihikisaki_name ");
		sql.append("from mst_torihikisaki ");
		sql.append("where torihikisaki_kbn  = '2' and delete_flg = '0'");

		open();
		try {
			ps = con.prepareStatement(sql.toString());
			rs = ps.executeQuery();

			while(rs.next()) {
				Map<String,String> map = new HashMap<String,String>();
				map.put("tenpo_code",  rs.getString("torihikisaki_code"));
				map.put("tenpo_name", rs.getString("torihikisaki_name"));

				list.add(map);
			}
			close();
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		return list;

	}
	//必要ページを返す
	public int getPages() {
		int page = 0;

		StringBuilder sql = new StringBuilder();
		StringBuilder s_index = new StringBuilder();

		sql.append("SELECT COUNT(*) AS cnt ");
		sql.append("FROM mst_torihikisaki ");
		sql.append("WHERE delete_flg = '0' ");

		if(torihikisaki.length() > 0) {
			sql.append("AND (torihikisaki_code = ? OR torihikisaki_name LIKE ?) ");
			s_index.append("11");
		}

		if(torihikisaki_kbn.length() > 0) {
			sql.append("AND torihikisaki_kbn = ? ");
			s_index.append("2");
		}

		open();
		try {
			ps = con.prepareStatement(sql.toString());
			int i_index;
			i_index = s_index.indexOf("1");

			if(i_index  >= 0) {
				ps.setString(i_index + 1, torihikisaki);
				ps.setString(i_index + 2, "%" + torihikisaki + "%");
			}

			i_index = s_index.indexOf("2");
			if(i_index >= 0) {
				ps.setString(i_index + 1, torihikisaki_kbn);
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
	public List<Map<String,String>> getList() {
		List<Map<String,String>> list = new ArrayList<Map<String,String>>();

		StringBuilder sql = new StringBuilder();
		StringBuilder s_index = new StringBuilder();

		sql.append("SELECT torihikisaki_code, torihikisaki_name ");
		sql.append("FROM mst_torihikisaki ");
		sql.append("WHERE delete_flg = '0' ");

		if(torihikisaki.length() > 0) {
			sql.append("AND (torihikisaki_code = ? OR torihikisaki_name LIKE ?) ");
			s_index.append("11");
		}

		if(torihikisaki_kbn.length() > 0) {
			sql.append("AND torihikisaki_kbn = ? ");
			s_index.append("2");
		}

		//limit
		sql.append("LIMIT ?,25 ");
		s_index.append("3");

		open();
		try {
			ps = con.prepareStatement(sql.toString());
			int i_index;
			i_index = s_index.indexOf("1");

			if(i_index  >= 0) {
				ps.setString(i_index + 1, torihikisaki);
				ps.setString(i_index + 2, "%" + torihikisaki + "%");
			}

			i_index = s_index.indexOf("2");
			if(i_index >= 0) {
				ps.setString(i_index + 1, torihikisaki_kbn);
			}

			i_index = s_index.indexOf("3");
			ps.setInt(i_index +1, (page -1) * 25);

			rs = ps.executeQuery();

			while(rs.next()) {
				Map<String,String> map = new HashMap<String,String>();
				map.put("torihikisaki_code", rs.getString("torihikisaki_code"));
				map.put("torihikisaki_name", rs.getString("torihikisaki_name"));
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
