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

import bean.ShainBean;

/**
 * Servlet implementation class Shain
 */
@WebServlet("/shain")
public class Shain extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Shain() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		//System.out.println("call shinki success");
		ShainBean sb = new ShainBean();
		HttpSession session = request.getSession();
		session.setAttribute("sb", sb);
		getServletContext().getRequestDispatcher("/shain.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		HttpSession session = request.getSession();
		String job = request.getParameter("job");
		System.out.println("sjob = " + job);
		ShainBean sb = new ShainBean();
		sb.setShain(request.getParameter("shain"));
		sb.setKengen_code(request.getParameter("kengen_code"));
		sb.setPage(Integer.parseInt(request.getParameter("page")));

		//社員データ一覧で検索ボタンが押された
		if(job.equals("1")) {
			int page  = sb.getPages();
			response.getWriter().print(page);
		}

		if(job.equals("2")) {
			List<Map<String,String>> list = sb.getList();
			request.setAttribute("list", list);
			getServletContext().getRequestDispatcher("/shainList.jsp").forward(request, response);

		}


	}

}
