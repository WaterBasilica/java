package bean;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class LoginBean extends DbConnection {
	//フィールド
	private String shain_code = "";
	private String shain_name = "";
	private String password = "";
	private String tenpo_code = "";
	private String tenpo_name = "";
	private List<Map<String,String>> tenpo_data;
	private String kengen_code = "";
	private String kengen_name = "";
	private String msg = "";

	//コンストラクタ
	public LoginBean() {
		Torihikisakibean  tb = new Torihikisakibean();
		tenpo_data = tb.getTenpoData();
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getTenpo_code() {
		return tenpo_code;
	}
	public void setTenpo_code(String tenpo_code) {
		this.tenpo_code = tenpo_code;
	}
	public String getTenpo_name() {
		return tenpo_name;
	}
	public void setTenpo_name(String tenpo_name) {
		this.tenpo_name = tenpo_name;
	}
	public List<Map<String, String>> getTenpo_data() {
		return tenpo_data;
	}
	public void setTenpo_data(List<Map<String, String>> tenpo_data) {
		this.tenpo_data = tenpo_data;
	}
	public String getKengen_code() {
		return kengen_code;
	}
	public void setKengen_code(String kengen_code) {
		this.kengen_code = kengen_code;
	}
	public String getKengen_name() {
		return kengen_name;
	}
	public void setKengen_name(String kengen_name) {
		this.kengen_name = kengen_name;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}

	//チェック
	public boolean check() {
		//必須チェック
		if(shain_code.length() == 0 || password.length()==0) {
			msg = "社員コード、パスワードの入力は必須です。";
			return false;

		}
		//存在チェック
		StringBuilder sql = new StringBuilder();
		sql.append("select ");
		sql.append("ms.shain_name, ");
		sql.append("mk.kengen_code,");
		sql.append("mk.kengen_name ");
		sql.append("from ");
		sql.append("mst_shain as ms join mst_kengen as mk ");
		sql.append("on ms.kengen_code = mk.kengen_code ");
		sql.append("where ");
		sql.append("ms.shain_code = ? ");
		sql.append("and ms.password = ?");
		sql.append("and ms.delete_flg = '0'");

		open();
		try {
			ps = con.prepareStatement(sql.toString());
			ps.setString(1, shain_code);
			ps.setString(2, password);
			rs = ps.executeQuery();

			//存在した
			if(rs.next()) {
				shain_name = rs.getString("shain_name");
				kengen_code = rs.getString("kengen_code");
				kengen_name = rs.getString("kengen_name");
			}else {
				//存在しなかった
				msg ="社員コード、またはパスワードに間違いがあります。";
				return false;
			}
			close();

			//関連チェック
			if(kengen_code.equals("002") && tenpo_code.length() == 0) {
				msg = "店舗権限者は店舗の選択が必須です。";
				return false;
			}

			//店舗情報を整理する。
			if(kengen_code.equals("001")) {
				tenpo_code = "";
				tenpo_name = "事務局";
				return true;
			}

			//店舗名を取得する
			sql = new StringBuilder();
			sql.append("SELECT torihikisaki_name ");
			sql.append("FROM mst_torihikisaki ");
			sql.append("WHERE torihikisaki_code = ? ");

			open();
			ps = con.prepareStatement(sql.toString());
			ps.setString(1, tenpo_code);
			rs = ps.executeQuery();

			if(rs.next()) {
				tenpo_name = rs.getString("torihikisaki_name");
			} else {
				msg = "店舗名取得時に予期せぬエラーが発生しました。";
				return false;
			}

			close();
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}


		return true ;
	}

}
