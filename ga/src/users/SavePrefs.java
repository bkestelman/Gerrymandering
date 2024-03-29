package users;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.HttpConstraint;
import javax.servlet.annotation.ServletSecurity;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.StateDAO;
import entity.User;

/**
 * Servlet implementation class SavePrefs
 */
@WebServlet({ "/SavePrefs", "/savePrefs" })
@ServletSecurity(
		value = @HttpConstraint(rolesAllowed = {"user", "admin"})
)
public class SavePrefs extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SavePrefs() {
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
		String efficiencyGapPref = request.getParameter("efficiencyGapPref");
		String consistentAdvantagePref = request.getParameter("consistentAdvantagePref");
		HashMap<String, String> prefs = new HashMap();
		prefs.put("efficiencyGap", efficiencyGapPref);
		prefs.put("consistentAdvantage", consistentAdvantagePref);
		
		User user = StateDAO.getUser(request.getRemoteUser());
		StateDAO.savePrefs(prefs, user);
	}

}
