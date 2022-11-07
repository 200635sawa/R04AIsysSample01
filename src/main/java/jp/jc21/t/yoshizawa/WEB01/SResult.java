package jp.jc21.t.yoshizawa.WEB01;

import java.io.IOException;
import java.net.URISyntaxException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/SResult")
public class SResult extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SResult() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String string = "鈴木";
		try {
			Sentiment1 result = Sentiment.getSentiment1(string);
			String negative = result.documents[0].confidenceScores.negative;
			String neutral = result.documents[0].confidenceScores.neutral;
			String positive = result.documents[0].confidenceScores.positive;
			request.setAttribute("negative", negative);
			request.setAttribute("neutral", neutral);
			request.setAttribute("positive", positive);
			request.getRequestDispatcher("/WEB-INF/SResult.jsp")
				.forward(request, response);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request
		.setCharacterEncoding("UTF-8");
	
	try {
		String string = 
				request.getParameter("sent");
		Sentiment1 result = Sentiment.getSentiment1(string);
		String negative = result.documents[0].confidenceScores.negative;
		String neutral = result.documents[0].confidenceScores.neutral;
		String positive = result.documents[0].confidenceScores.positive;
		request.setAttribute("negative", negative);
		request.setAttribute("neutral", neutral);
		request.setAttribute("positive", positive);
		request.getRequestDispatcher("/WEB-INF/SResult.jsp")
			.forward(request, response);
	} catch (IOException e) {
		e.printStackTrace();
	} catch (URISyntaxException e) {
		e.printStackTrace();
	} catch (InterruptedException e) {
		e.printStackTrace();
	}
		
}
}
