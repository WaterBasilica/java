package bean;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

public class NyukaTorokuBean extends DbConnection {
	//フィールド
	private String title = "";
	private String nyuka_seq = "";
	private String tenpo_code = "";
	private List<Map<String,String>> list;
	private String kengen_code = "";
	private String torihikisaki_code = "";
	private String torihikisaki_name = "";
	private String yakuhin_kbn = "";
	private String yakuhin_kbn_name = "";
	private String hanbai_name = "";
	private String jan_code = "";
	private String yj_code = "";
	private double nyuka_su = 0.0;
	private String nyuka_date = "";
	private String biko = "";
	private String shain_code = "";
	private String button = "";
	private String disabled = "";
	private String msg ="";

	//コンストラクタ
	public NyukaTorokuBean() {
		Torihikisakibean tb = new Torihikisakibean();
		list = tb.getTenpoData();
	}


	//getter,setter
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getNyuka_seq() {
		return nyuka_seq;
	}
	public void setNyuka_seq(String nyuka_seq) {
		this.nyuka_seq = nyuka_seq;
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
	public String getKengen_code() {
		return kengen_code;
	}
	public void setKengen_code(String kengen_code) {
		this.kengen_code = kengen_code;
	}
	public String getTorihikisaki_code() {
		return torihikisaki_code;
	}
	public void setTorihikisaki_code(String torihikisaki_code) {
		this.torihikisaki_code = torihikisaki_code;
	}
	public String getTorihikisaki_name() {
		return torihikisaki_name;
	}
	public void setTorihikisaki_name(String torihikisaki_name) {
		this.torihikisaki_name = torihikisaki_name;
	}
	public String getYakuhin_kbn() {
		return yakuhin_kbn;
	}
	public void setYakuhin_kbn(String yakuhin_kbn) {
		this.yakuhin_kbn = yakuhin_kbn;
	}
	public String getYakuhin_kbn_name() {
		return yakuhin_kbn_name;
	}
	public void setYakuhin_kbn_name(String yakuhin_kbn_name) {
		this.yakuhin_kbn_name = yakuhin_kbn_name;
	}
	public String getHanbai_name() {
		return hanbai_name;
	}
	public void setHanbai_name(String hanbai_name) {
		this.hanbai_name = hanbai_name;
	}
	public String getJan_code() {
		return jan_code;
	}
	public void setJan_code(String jan_code) {
		this.jan_code = jan_code;
	}
	public String getYj_code() {
		return yj_code;
	}
	public void setYj_code(String yj_code) {
		this.yj_code = yj_code;
	}
	public double getNyuka_su() {
		return nyuka_su;
	}
	public void setNyuka_su(double nyuka_su) {
		this.nyuka_su = nyuka_su;
	}
	public String getNyuka_date() {
		return nyuka_date;
	}
	public void setNyuka_date(String nyuka_date) {
		this.nyuka_date = nyuka_date;
	}
	public String getBiko() {
		return biko;
	}
	public void setBiko(String biko) {
		this.biko = biko;
	}
	public String getShain_code() {
		return shain_code;
	}
	public void setShain_code(String shain_code) {
		this.shain_code = shain_code;
	}
	public String getButton() {
		return button;
	}
	public void setButton(String button) {
		this.button = button;
	}
	public String getDisabled() {
		return disabled;
	}
	public void setDisabled(String disabled) {
		this.disabled = disabled;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}

	//チェック用メソッド
	public boolean registShinki() {
		//必須チェック
		if(tenpo_code.length() == 0 || nyuka_date.length() == 0  || hanbai_name.length() == 0 || torihikisaki_code.length() == 0) {
			msg = "店舗、取引先、薬品の選択は必須です。";
			return false;
		}
		String[] arrDate = nyuka_date.split("-");

		//年と月をint型に変換
		int intYear = Integer.parseInt(arrDate[0]);
		int intMonth = Integer.parseInt(arrDate[1]);

		if(intMonth < 11) intYear--;

		//入荷連番を作成
		/***最新入荷連番を取得する***/

		StringBuilder sql = new StringBuilder();

		sql.append("SELECT MAX(nyuka_seq) nyuka_seq ");
		sql.append("FROM nyuka ");
		sql.append("WHERE tenpo_code = ? AND nendo = ? ");

		open();

		try {
			ps = con.prepareStatement(sql.toString());
			ps.setString(1, tenpo_code);
			ps.setString(2, (Integer.toString(intYear)));

			rs = ps.executeQuery();

			String strSeq = "0000001";
			rs.next();
			String tmpSeq = rs.getString("nyuka_seq");
			if(tmpSeq != null) {
				strSeq = tmpSeq.substring(9, 16);
				int intSeq = Integer.parseInt(strSeq);
				intSeq++;
				strSeq = String.format("%07d", intSeq);
			}
			//System.out.print("strSeq = " + strSeq);

			//新しい入荷連番
			String newNyukaSeq = "n" + intYear + tenpo_code + strSeq;

			//現在日時の取得
			Calendar cal = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String mydate = sdf.format(cal.getTime());

			//INSERT
			sql = new StringBuilder();

			sql.append("INSERT INTO ");
			sql.append("nyuka ");
			sql.append("VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

			ps = con.prepareStatement(sql.toString());
			ps.setString(1, newNyukaSeq);
			ps.setString(2, Integer.toString(intYear));
			ps.setString(3, tenpo_code);
			ps.setString(4, torihikisaki_code);
			ps.setString(5, yakuhin_kbn);
			ps.setString(6, jan_code);
			ps.setString(7, yj_code);
			ps.setDouble(8, nyuka_su);
			ps.setString(9, nyuka_date);
			ps.setString(10, "0");
			ps.setString(11, biko);
			ps.setString(12, mydate);
			ps.setString(13, shain_code);
			ps.setString(14, mydate);
			ps.setString(15, shain_code);

			ps.execute();

			close();

		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		return true;
	}

	//nyuka_seqにひも付くデータの取得
	public void getData() {
		StringBuilder sql = new StringBuilder();

		sql.append("SELECT n.tenpo_code, n.torihikisaki_code, ");
		sql.append("mt.torihikisaki_name, n.yakuhin_kbn, ");
		sql.append("CASE n.yakuhin_kbn ");
		sql.append("WHEN '1' THEN '薬品' ");
		sql.append("WHEN '2' THEN 'ＯＴＣ' ");
		sql.append("WHEN '4' THEN '特材' ");
		sql.append("ELSE '不明' END yakuhin_kbn_name, ");
		sql.append("ms.hanbai_name, n.jan_code, n.yj_code, n.nyuka_su, ");
		sql.append("n.nyuka_date, n.biko ");
		sql.append("FROM nyuka n ");
		sql.append("JOIN mst_torihikisaki mt ");
		sql.append("ON n.torihikisaki_code = mt.torihikisaki_code ");
		//sql.append("JOIN mst_torihikisaki mt2 ");
		//sql.append("ON n.tenpo_code = mt2.tenpo_code ");
		sql.append("JOIN mst_shohin ms ");
		sql.append("ON n.jan_code = ms.jan_code ");
		sql.append("WHERE nyuka_seq = '" + nyuka_seq + "'");

		open();

		try {
			ps = con.prepareStatement(sql.toString());
			rs = ps.executeQuery();
			rs.next();

			tenpo_code = rs.getString("tenpo_code");
			torihikisaki_code = rs.getString("torihikisaki_code");
			torihikisaki_name = rs.getString("torihikisaki_name");
			yakuhin_kbn = rs.getString("yakuhin_kbn");
			yakuhin_kbn_name = rs.getString("yakuhin_kbn_name");
			hanbai_name = rs.getString("hanbai_name");
			jan_code = rs.getString("jan_code");
			yj_code = rs.getString("yj_code");
			nyuka_su = rs.getDouble("nyuka_su");
			nyuka_date = rs.getString("nyuka_date").substring(0,10);
			biko = rs.getString("biko");

		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		close();
	}
	public boolean registHenshu() {
		//必須チェック
		if(tenpo_code.length() == 0 || nyuka_date.length() == 0  || hanbai_name.length() == 0 || torihikisaki_code.length() == 0) {
			msg = "店舗、取引先、薬品の選択は必須です。";
			return false;
		}
		String[] arrDate = nyuka_date.split("-");

		//年と月をint型に変換
		int intYear = Integer.parseInt(arrDate[0]);
		int intMonth = Integer.parseInt(arrDate[1]);

		if(intMonth < 11) intYear++;

		//現在日時の取得
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String mydate = sdf.format(cal.getTime());

		StringBuilder sql = new StringBuilder();

		sql.append("UPDATE nyuka SET ");
		sql.append("nendo = ?, tenpo_code = ?, ");
		sql.append("torihikisaki_code = ?, yakuhin_kbn = ?, ");
		sql.append("jan_code = ?, yj_code = ?, ");
		sql.append("nyuka_su = ?, nyuka_date = ?, ");
		sql.append("biko = ?, updated_on = ?, ");
		sql.append("updated_by = ? ");
		sql.append("WHERE nyuka_seq = ? ");

		open();

		try {
			ps = con.prepareStatement(sql.toString());
			ps.setString(1, Integer.toString(intYear));
			ps.setString(2, tenpo_code);
			ps.setString(3, torihikisaki_code);
			ps.setString(4, yakuhin_kbn);
			ps.setString(5, jan_code);
			ps.setString(6, yj_code);
			ps.setDouble(7, nyuka_su);
			ps.setString(8, nyuka_date);
			ps.setString(9, biko);
			ps.setString(10, mydate);
			ps.setString(11, shain_code);
			ps.setString(12, nyuka_seq);

			ps.execute();

		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		close();
		return true;
	}
	public void delNyuka() {

		//現在日時の取得
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String mydate = sdf.format(cal.getTime());

		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE nyuka SET ");
		sql.append("delete_flg = '1', updated_on = ?, ");
		sql.append("updated_by = ? ");
		sql.append("WHERE nyuka_seq = ? ");

		open();

		try {
			ps = con.prepareStatement(sql.toString());
			ps.setString(1, mydate);
			ps.setString(2, shain_code);
			ps.setString(3, nyuka_seq);

			ps.execute();
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		close();
	}

}












