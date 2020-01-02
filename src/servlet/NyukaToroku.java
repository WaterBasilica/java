package servlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.NyukaTorokuBean;
import bean.Torihikisakibean;
import bean.YakuhinBean;

/**
 * Servlet implementation class NyukaToroku
 */
@WebServlet("/nyukatoroku")
public class NyukaToroku extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public NyukaToroku() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//System.out.println("call nyukatoroku succsess");
		request.setCharacterEncoding("utf-8");
		String job = request.getParameter("job");
		System.out.println("job2=" + job);

		//セッションを使えるようにしておく
		HttpSession session = request.getSession();
		NyukaTorokuBean ntb = new NyukaTorokuBean();
		ntb.setShain_code(session.getAttribute("login_shain_code").toString());

		//新規作成ボタンが押下された
		if(job.contentEquals("1")) {
			ntb.setTitle("入荷データ新規登録");
			ntb.setButton("<button type='button' onClick='registShinki()'>登録</button>");
			ntb.setKengen_code(session.getAttribute("login_kengen_code").toString());
			ntb.setTenpo_code(session.getAttribute("login_tenpo_code").toString());
			session.setAttribute("ntb", ntb);
			getServletContext().getRequestDispatcher("/nyukaToroku.jsp").forward(request, response);
		}

		//キャンセルボタンが押された
		if(job.equals("2")) {
			getServletContext().getRequestDispatcher("/nyuka.jsp").forward(request, response);
		}

		//取引先参照ボタンが押された
		if(job.equals("3")) {
			ntb = (NyukaTorokuBean)session.getAttribute("ntb");
			ntb.setNyuka_seq(request.getParameter("nyuka_seq"));
			ntb.setTenpo_code(request.getParameter("tenpo_code"));
			ntb.setTorihikisaki_code(request.getParameter("torihikisaki_code"));
			ntb.setTorihikisaki_name(request.getParameter("torihikisaki_name"));
			ntb.setHanbai_name(request.getParameter("hanbai_name"));
			ntb.setYakuhin_kbn(request.getParameter("yakuhin_kbn"));
			ntb.setYakuhin_kbn_name(request.getParameter("yakuhin_kbn_name"));
			ntb.setJan_code(request.getParameter("jan_code"));
			ntb.setYj_code(request.getParameter("yj_code"));
			ntb.setNyuka_su(Double.parseDouble(request.getParameter("nyuka_su")));
			ntb.setNyuka_date(request.getParameter("nyuka_date"));
			ntb.setBiko(request.getParameter("biko"));
			session.setAttribute("ntb", ntb);
			//System.out.println("OK");
			getServletContext().getRequestDispatcher("/tori_sansho.jsp").forward(request, response);

		}

		//取引先参照キャンセルボタンが押されたら
		if(job.equals("4")) {
			getServletContext().getRequestDispatcher("/nyukaToroku.jsp").forward(request, response);
		}

		if(job.equals("5")) {
			Torihikisakibean tb = new Torihikisakibean();
			tb.setTorihikisaki(request.getParameter("torihikisaki"));
			tb.setTorihikisaki_kbn(request.getParameter("torihikisaki_kbn"));
			int page = tb.getPages();
			//System.out.print(page);
			response.getWriter().print(page);

		}

		if(job.equals("6")) {
			//System.out.println("call success");
			Torihikisakibean tb = new Torihikisakibean();
			tb.setTorihikisaki(request.getParameter("torihikisaki"));
			tb.setTorihikisaki_kbn(request.getParameter("torihikisaki_kbn"));
			tb.setPage(Integer.parseInt(request.getParameter("page")));
			//System.out.println("torihikisaki = " + tb.getTorihikisaki());
			//System.out.println("page = " + tb.getPage());
			List<Map<String,String>> list = tb.getList();
			request.setAttribute("list", list);
			getServletContext().getRequestDispatcher("/toriSansholist.jsp").forward(request, response);


		}
		if(job.equals("7")) {
			ntb = (NyukaTorokuBean)session.getAttribute("ntb");
			ntb.setTorihikisaki_code(request.getParameter("torihikisaki_code"));
			ntb.setTorihikisaki_name(request.getParameter("torihikisaki_name"));
			getServletContext().getRequestDispatcher("/nyukaToroku.jsp").forward(request, response);

		}

		//取引先参照ボタンが押された
		if(job.equals("8")) {
			ntb = (NyukaTorokuBean)session.getAttribute("ntb");
			ntb.setNyuka_seq(request.getParameter("nyuka_seq"));
			ntb.setTenpo_code(request.getParameter("tenpo_code"));
			ntb.setTorihikisaki_code(request.getParameter("torihikisaki_code"));
			ntb.setTorihikisaki_name(request.getParameter("torihikisaki_name"));
			ntb.setHanbai_name(request.getParameter("hanbai_name"));
			ntb.setYakuhin_kbn(request.getParameter("yakuhin_kbn"));
			ntb.setYakuhin_kbn_name(request.getParameter("yakuhin_kbn_name"));
			ntb.setJan_code(request.getParameter("jan_code"));
			ntb.setYj_code(request.getParameter("yj_code"));
			ntb.setNyuka_su(Double.parseDouble(request.getParameter("nyuka_su")));
			ntb.setNyuka_date(request.getParameter("nyuka_date"));
			ntb.setBiko(request.getParameter("biko"));
			session.setAttribute("ntb", ntb);
			//System.out.println("OK");
			getServletContext().getRequestDispatcher("/yaku_sansho.jsp").forward(request, response);

		}

		if(job.equals("9")) {
			//System.out.println("call job9");
			YakuhinBean yb = new YakuhinBean();
			yb.setYakuhin(request.getParameter("yakuhin"));
			yb.setYakuhin_kbn(request.getParameter("yakuhin_kbn"));
			int page = yb.getPages();
			response.getWriter().print(page);
		}

		if(job.equals("10")) {
			YakuhinBean yb = new YakuhinBean();
			yb.setYakuhin(request.getParameter("yakuhin"));
			yb.setYakuhin_kbn(request.getParameter("yakuhin_kbn"));
			yb.setPage(Integer.parseInt(request.getParameter("page")));
			List<Map<String, String>> list = yb.getList();
			request.setAttribute("list", list);
			//System.out.println(list.toString());
			getServletContext().getRequestDispatcher("/yakuSansholist.jsp").forward(request, response);

		}
		if(job.equals("11")) {
			ntb = (NyukaTorokuBean)session.getAttribute("ntb");
			ntb.setJan_code(request.getParameter("jan_code"));
			ntb.setYj_code(request.getParameter("yj_code"));
			ntb.setHanbai_name(request.getParameter("hanbai_name"));
			ntb.setYakuhin_kbn(request.getParameter("yakuhin_kbn"));
			ntb.setYakuhin_kbn_name(request.getParameter("yakuhin_kbn_name"));
			session.setAttribute("ntb", ntb);
			getServletContext().getRequestDispatcher("/nyukaToroku.jsp").forward(request, response);
		}

		if(job.equals("12") || job.equals("14")) {
			ntb = (NyukaTorokuBean)session.getAttribute("ntb");
			boolean check_num = true;
			double nyuka_su =0.0;
			try {
				nyuka_su = Double.parseDouble(request.getParameter("nyuka_su"));
				if(nyuka_su == 0.0) {
					check_num = false;
					ntb.setMsg("入荷数は0以外の数字で入力して下さい。");
				}
			}	catch(Exception e) {
				check_num = false;
				ntb.setMsg("入荷数は数字で入力して下さい。");
			}

			ntb.setTenpo_code(request.getParameter("tenpo_code"));
			ntb.setNyuka_date(request.getParameter("nyuka_date"));
			ntb.setBiko(request.getParameter("biko"));
			if(!check_num) {
				session.setAttribute("ntb", ntb);
				getServletContext().getRequestDispatcher("/nyukaToroku.jsp").forward(request, response);
				return;
			} else {
				ntb.setNyuka_su(nyuka_su);
			}
			//System.out.println(ntb.getShain_code());
			//新規登録
			if(job.equals("12")) {
				if(!ntb.registShinki()) {
					session.setAttribute("ntb", ntb);
					getServletContext().getRequestDispatcher("/nyukaToroku.jsp").forward(request, response);
					return;
				} else {
					response.sendRedirect("./nyuka.jsp");
				}
			}
			//編集登録
			if(job.equals("14")) {
				if(!ntb.registHenshu()) {
					session.setAttribute("ntb", ntb);
					getServletContext().getRequestDispatcher("/nyukaToroku.jsp").forward(request, response);
					return;
				} else {
					response.sendRedirect("./nyuka.jsp");
				}
			}

		}
		//入荷データ一覧で、編集ボタン押下
		if(job.equals("13")) {
			ntb = new NyukaTorokuBean();
			ntb.setNyuka_seq(request.getParameter("nyuka_seq"));
			ntb.setTitle("入荷編集画面");
			ntb.setButton("<button type='button' onClick='henshu()'>編集</button>");
			ntb.setShain_code(session.getAttribute("login_shain_code").toString());
			ntb.setKengen_code(session.getAttribute("login_kengen_name").toString());
			ntb.getData();
			session.setAttribute("ntb", ntb);
			getServletContext().getRequestDispatcher("/nyukaToroku.jsp").forward(request, response);
		}

		if(job.equals("15")) {
			ntb = new NyukaTorokuBean();
			ntb.setNyuka_seq(request.getParameter("nyuka_seq"));
			ntb.setTitle("入荷削除画面");
			ntb.setButton("<button type='button' onClick='delNyuka()'>削除</button>");
			ntb.setShain_code(session.getAttribute("login_shain_code").toString());
			ntb.setKengen_code("002");
			ntb.getData();
			ntb.setDisabled("disabled");
			session.setAttribute("ntb", ntb);
			getServletContext().getRequestDispatcher("/nyukaToroku.jsp").forward(request, response);
		}
		//入荷データ削除確認で削除ボタンを押下
		if(job.equals("16")) {
			ntb = new NyukaTorokuBean();
			ntb.setNyuka_seq(request.getParameter("nyuka_seq"));
			ntb.setShain_code(session.getAttribute("login_shain_code").toString());
			ntb.delNyuka();
			response.sendRedirect("./nyuka.jsp");
		}

	}

}
