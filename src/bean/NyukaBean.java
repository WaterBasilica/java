package bean;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NyukaBean extends DbConnection {
	private String yakuhin_kbn = "";
	private String[] y_kbn_code = {"1","2","4"};
	private String[] y_kbn_name = {"薬品","OTC","特材"};
	private String tenpo_code ="";
	private List<Map<String,String>> list;
	private String date_from = "";
	private String date_to = "";
	private String yakuhin = "";
	private String torihikisaki = "";
	private int page = 0;


	public String getYakuhin_kbn() {
		return yakuhin_kbn;
	}
	public void setYakuhin_kbn(String yakuhin_kbn) {
		this.yakuhin_kbn = yakuhin_kbn;
	}
	public String[] getY_kbn_code() {
		return y_kbn_code;
	}
	public void setY_kbn_code(String[] y_kbn_code) {
		this.y_kbn_code = y_kbn_code;
	}
	public String[] getY_kbn_name() {
		return y_kbn_name;
	}
	public void setY_kbn_name(String[] y_kbn_name) {
		this.y_kbn_name = y_kbn_name;
	}
	public String getTenpo_code() {
		return tenpo_code;
	}
	public void setTenpo_code(String tenpo_code) {
		this.tenpo_code = tenpo_code;
	}
	public List<Map<String, String>> getList() {
		return list;
	}
	public void setList(List<Map<String, String>> list) {
		this.list = list;
	}
	public String getDate_from() {
		return date_from;
	}
	public void setDate_from(String date_from) {
		this.date_from = date_from;
	}
	public String getDate_to() {
		return date_to;
	}
	public void setDate_to(String date_to) {
		this.date_to = date_to;
	}
	public String getYakuhin() {
		return yakuhin;
	}
	public void setYakuhin(String yakuhin) {
		this.yakuhin = yakuhin;
	}
	public String getTorihikisaki() {
		return torihikisaki;
	}
	public void setTorihikisaki(String torihikisaki) {
		this.torihikisaki = torihikisaki;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}

	//コンストラクタ
	public NyukaBean() {
		Torihikisakibean tb = new Torihikisakibean();
		list = tb.getTenpoData();
	}

	//一覧表示のページ数を返す
	public int getPages() {
		int page = 0;

		//SQL作成
		StringBuilder s_index = new StringBuilder();
		StringBuilder sql = new StringBuilder();

		sql.append("select count(*) as cnt ");
		sql.append("from nyuka as n join mst_shohin as ms ");
		sql.append("on n.jan_code = ms.jan_code ");
		sql.append("join mst_torihikisaki as mt ");
		sql.append("on n.torihikisaki_code = mt.torihikisaki_code ");
		sql.append("where n.delete_flg = '0' ");
		//yakuhin_kbn
		if(yakuhin_kbn.length() > 0) {
			sql.append("and n.yakuhin_kbn = ? ");
			s_index.append("1");
		}
		//tenpo_code
		if(tenpo_code.length() > 0) {
			sql.append("and n.tenpo_code = ?");
			s_index.append("2");
		}
		//date_from
		if(date_from.length() > 0) {
			sql.append("and n.nyuka_date >= ? ");
				s_index.append("3");

		}
		//date_to
		if(date_to.length() >0 ) {
			sql.append("and n.nyuka_date <= ? ");
			s_index.append("4");
		}
		//yakuin
		if(yakuhin.length() > 0) {
			sql.append("and (");
			sql.append("n.jan_code = ? ");
			sql.append("or ms.hanbai_name like ? ");
			sql.append(")");
			s_index.append("55");
		}
		//torihikisaki
		if(torihikisaki.length() > 0) {
			sql.append("and (");
			sql.append("n.torihikisaki_code = ? ");
			sql.append("or mt.torihikisaki_name like ? ");
			sql.append(")");
			s_index.append("66");
		}

		open();
		try {
			ps = con.prepareStatement(sql.toString());
			//yakuhin_kbn
			int i_index = s_index.indexOf("1");
			if(i_index >= 0) {
				ps.setString(i_index + 1, yakuhin_kbn);
			}
			//tenpo_code
			i_index = s_index.indexOf("2");
			if(i_index >= 0) {
				ps.setString(i_index + 1,  tenpo_code);//正しい位置に表示するためのi_index
			}
			//date_from
			i_index = s_index.indexOf("3");
			if(i_index >= 0) {//indexOfがfalseだと-1を返すため0以上
				ps.setString(i_index + 1,  date_from);
			}
			//date_to
			i_index = s_index.indexOf("4");
			if(i_index >= 0 ) {
				ps.setString(i_index + 1,  date_to);
			}
			//yakuhin
			i_index = s_index.indexOf("5");
			if(i_index >= 0) {
				ps.setString(i_index + 1,  yakuhin);
				ps.setString(i_index + 2, "%"+ yakuhin + "%");
			}
			//torihikisaki
			i_index = s_index.indexOf("6");
			if(i_index >= 0) {
				ps.setString(i_index + 1, torihikisaki);
				ps.setString(i_index + 2,  "%" + torihikisaki + "%");
			}
			//System.out.println(ps.toString());
			rs = ps.executeQuery();//何も入っていなくても0が返るので1行は必ず帰ってくる
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

	public List<Map<String,String>> getNyukaList() {
		List<Map<String,String>> list = new ArrayList<Map<String,String>>();

		//SQL作成
		StringBuilder s_index = new StringBuilder();
		StringBuilder sql = new StringBuilder();

		sql.append("select ");
		sql.append("n.nyuka_seq,");
		sql.append("n.nendo,");
		sql.append("mt2.torihikisaki_name as tenpo_name,");
		sql.append("mt.torihikisaki_name,");
		sql.append("case n.yakuhin_kbn ");
		sql.append("when '1' then '薬品' ");
		sql.append("when '2' then 'OTC' ");
		sql.append("when '4' then  '特材' ");
		sql.append("else '不明' end as yakuhin_kbn_name, ");
		sql.append("n.jan_code,");//商品コード
		sql.append("n.yj_code,");//薬の中身のコード
		sql.append("ms.hanbai_name,");
		sql.append("n.nyuka_su,");
		sql.append("n.nyuka_date ");
		sql.append("from nyuka as n join mst_shohin as ms ");
		sql.append("on n.jan_code = ms.jan_code ");
		sql.append("join mst_torihikisaki as mt ");
		sql.append("on n.torihikisaki_code = mt.torihikisaki_code ");
		sql.append("join mst_torihikisaki as mt2 ");
		sql.append("on n.tenpo_code = mt2.torihikisaki_code ");
		sql.append("where n.delete_flg = '0' ");


		//yakuhin_kbn
		if(yakuhin_kbn.length() > 0) {
			sql.append("and n.yakuhin_kbn = ? ");
			s_index.append("1");
		}
		//tenpo_code
		if(tenpo_code.length() > 0) {
			sql.append("and n.tenpo_code = ? ");
			s_index.append("2");
		}
		//date_from
		if(date_from.length() > 0) {
			sql.append("and n.nyuka_date >= ? ");
				s_index.append("3");

		}
		//date_to
		if(date_to.length() >0 ) {
			sql.append("and n.nyuka_date <= ? ");
			s_index.append("4");
		}
		//yakuin
		if(yakuhin.length() > 0) {
			sql.append("and (");
			sql.append("n.jan_code = ? ");
			sql.append("or ms.hanbai_name like ? ");
			sql.append(")");
			s_index.append("55");
		}
		//torihikisaki
		if(torihikisaki.length() > 0) {
			sql.append("and (");
			sql.append("n.torihikisaki_code = ? ");
			sql.append("or mt.torihikisaki_name like ? ");
			sql.append(")");
			s_index.append("66");
		}
		//order by
		sql.append("order by n.nyuka_seq desc ");
		//limit
		sql.append("limit ?,25");
		s_index.append("7");

		open();
		try {
			ps = con.prepareStatement(sql.toString());
			//yakuhin_kbn
			int i_index = s_index.indexOf("1");
			if(i_index >= 0) {
				ps.setString(i_index + 1, yakuhin_kbn);
			}
			//tenpo_code
			i_index = s_index.indexOf("2");
			if(i_index >= 0) {
				ps.setString(i_index + 1,  tenpo_code);//正しい位置に表示するためのi_index
			}
			//date_from
			i_index = s_index.indexOf("3");
			if(i_index >= 0) {//indexOfがfalseだと-1を返すため0以上
				ps.setString(i_index + 1,  date_from);
			}
			//date_to
			i_index = s_index.indexOf("4");
			if(i_index >= 0 ) {
				ps.setString(i_index + 1,  date_to);
			}
			//yakuhin
			i_index = s_index.indexOf("5");
			if(i_index >= 0) {
				ps.setString(i_index + 1,  yakuhin);
				ps.setString(i_index + 2, "%"+ yakuhin + "%");
			}
			//torihikisaki
			i_index = s_index.indexOf("6");
			if(i_index >= 0) {
				ps.setString(i_index + 1, torihikisaki);
				ps.setString(i_index + 2,  "%" + torihikisaki + "%");
			}
			//limit
			i_index = s_index.indexOf("7");
			ps.setInt(i_index + 1, (page -1) * 25);//
			//System.out.println(ps.toString());
			rs = ps.executeQuery();//何も入っていなくても0が返るので1行は必ず帰ってくる
			//System.out.println("sqlは " + sql);
			while(rs.next()) {
				Map<String,String> map = new HashMap<String,String>();
				map.put("nyuka_seq", rs.getString("nyuka_seq"));
				map.put("nendo", rs.getString("nendo"));
				map.put("tenpo_name", rs.getString("tenpo_name"));
				map.put("torihikisaki_name", rs.getString("torihikisaki_name"));
				map.put("yakuhin_kbn_name", rs.getString("yakuhin_kbn_name"));
				map.put("jan_code", rs.getString("jan_code"));
				map.put("yj_code", rs.getString("yj_code"));
				map.put("hanbai_name", rs.getString("hanbai_name"));
				map.put("nyuka_su", rs.getString("nyuka_su"));
				map.put("nyuka_date", rs.getString("nyuka_date").substring(0, 10));//頭から10文字取る
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
