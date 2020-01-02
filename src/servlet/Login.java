package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.LoginBean;

/**
 * Servlet implementation class Login
 */
@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("aaa");
		LoginBean lb = new LoginBean();
		request.setAttribute("lb",  lb);
		getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		LoginBean lb = new LoginBean();
		lb.setShain_code(request.getParameter("shain_code"));
		lb.setPassword(request.getParameter("password"));
		lb.setTenpo_code(request.getParameter("tenpo_code"));

		//チェック
		if(lb.check() == false) {
			//checkメソッドの返り値がfalseだったらログイン画面に戻す
			request.setAttribute("lb", lb);
			getServletContext().getRequestDispatcher("/login.jsp").forward(request,response);
			return;
		}
		//ログイン者情報をsessionに持たせる
		HttpSession session = request.getSession();
		session.setMaxInactiveInterval(0);
		session.setAttribute("login_shain_code", lb.getShain_code());
		session.setAttribute("login_shain_name", lb.getShain_name());
		session.setAttribute("login_tenpo_code", lb.getTenpo_code());
		session.setAttribute("login_tenpo_name", lb.getTenpo_name());
		session.setAttribute("login_kengen_code", lb.getKengen_code());
		session.setAttribute("login_kengen_name", lb.getKengen_name());

		//getServletContext().getRequestDispatcher("/menu").forward(request, response);
		response.sendRedirect("./menu");

	}


}
