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

import bean.NyukaBean;

/**
 * Servlet implementation class Nyuka
 */
@WebServlet("/nyuka")
public class Nyuka extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Nyuka() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		NyukaBean nb = new NyukaBean(); //別のパッケージにあるためインポートが必要
		HttpSession session = request.getSession();
		nb.setTenpo_code(session.getAttribute("login_tenpo_code").toString());
		session.setAttribute("nb", nb);
		getServletContext().getRequestDispatcher("/nyuka.jsp").forward(request, response);


	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		String job = request.getParameter("job");
		NyukaBean nb = new NyukaBean();
		//System.out.println("job=" + job);
		//System.out.println("nyuka_job = " + job);


		//NyukaBeanに画面の入力値を渡す
		nb.setYakuhin_kbn(request.getParameter("yakuhin_kbn"));
		nb.setTenpo_code(request.getParameter("tenpo_code"));
		nb.setDate_to(request.getParameter("date_to"));
		nb.setDate_from(request.getParameter("date_from"));
		nb.setYakuhin(request.getParameter("yakuhin"));
		nb.setTorihikisaki(request.getParameter("torihikisaki"));
		nb.setPage(Integer.parseInt(request.getParameter("page")));
//		System.out.println("選択された薬品区分: " + nb.getYakuhin_kbn());
//		System.out.println("選択された店舗コード: " + nb.getTenpo_code());
//		System.out.println("選択されたdate_from: " + nb.getDate_from());
//		System.out.println("選択されたdate_to: " + nb.getDate_to());
//		System.out.println("選択された薬品: " + nb.getYakuhin());
//		System.out.println("選択された取引先: " + nb.getTorihikisaki());

		//入荷データ一覧で検索ボタンが押された
		if(job.equals("1") ) {
			int page = nb.getPages();
			//System.out.println("page = " + page);
			//javascriptに返る
			response.getWriter().print(page);
		}
		//System.out.println("jobは" + job);
		//入荷データ一覧画面に一覧を返す
		if(job.equals("2")) {
			//System.out.println("call getList success.");
			List<Map<String, String>> list = nb.getNyukaList();
			//System.out.println(list);
			request.setAttribute("list", list);
			getServletContext().getRequestDispatcher("/nyukalist.jsp").forward(request, response);
		}

		//入荷データ一覧で、新規作戦ボタン押下
		if(job.equals("3")) {
			//System.out.println("job3page=" + nb.getPage());
			//sessionに覚えさせたいだけ
			session.setAttribute("nb", nb);
			response.getWriter().print("OK");
		}


		//入荷データ一覧で、削除ボタン押下
		if(job.equals("5")) {
			session.setAttribute("nb", nb);
		}

	}
}

