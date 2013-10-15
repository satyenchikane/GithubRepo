package servlet;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Dictionary;
import model.Word;

import java.util.logging.*;

/**
 * Servlet implementation class WordGameServlet
 */
public class WordGameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final Logger logger = Logger.getLogger(getClass().getName());   
	private  final String INDEX_JSP = "/jsp/index.jsp";
	static Dictionary dictionary;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WordGameServlet() {
        super();
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        logger.info("Init");
        // Initialize the list of words
        dictionary = Dictionary.getInstance();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.info("doGet3.. forwarding to jsp");
		
		String jspurl = INDEX_JSP;
		ServletContext sc = getServletContext();
		RequestDispatcher rd = sc.getRequestDispatcher(jspurl);
		rd.forward(request, response);
		logger.info("doGet3.. forwarded to jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String wordSofar = (String)request.getParameter("wordinput");

		if(wordSofar != null) {
			// Get the part of the word with next letter played by ghost 
			String stringWithNextLetter = new Word().getStringWithNextLetter(wordSofar.toLowerCase());
			response.setContentType("text/html");
			response.getWriter().write(stringWithNextLetter);
		}
	}

    @Override
    public void destroy() {
        super.destroy();
        logger.info("Destroy");
        dictionary = null;
    }

}
