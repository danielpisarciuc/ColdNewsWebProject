package eu.unicredit.fiipractic.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import eu.unicredit.fiipractic.integration.ConnectionFactory;
import eu.unicredit.fiipractic.integration.NewsDAO;
import eu.unicredit.fiipractic.integration.NewsDAOImpl;
import eu.unicredit.fiipractic.model.News;

public class NewsController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = (String) request.getParameter("action");

		if (action.equals("getAll")) {
			getAllInformation(request, response);
		}
		if (action.equals("getNewsById")) {
			getSingleNews(request, response);
		}
	}

	private void getSingleNews(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	
		String id = (String) request.getParameter("id");
		News news = null;
		try {
			NewsDAO newsDao = new NewsDAOImpl(ConnectionFactory.getConnection());
			news = newsDao.load(Long.valueOf(id));
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("singleNews", news);
		RequestDispatcher view = request.getRequestDispatcher("singleNews.jsp");
		view.forward(request, response);
	}

	private void getAllInformation(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		List<News> result = new ArrayList<News>();
		try {
			NewsDAO newsDao = new NewsDAOImpl(ConnectionFactory.getConnection());
			result = newsDao.loadAll();
		} catch (Exception e) {
			e.printStackTrace();
		}

		request.setAttribute("listOfNews", result);
		RequestDispatcher view = request.getRequestDispatcher("newsPage.jsp");
		view.forward(request, response);
	}
}
