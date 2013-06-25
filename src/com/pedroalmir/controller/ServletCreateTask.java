/**
 * 
 */
package com.pedroalmir.controller;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.pedroalmir.repository.TaskDAO;

/**
 * @author Pedro Almir
 *
 */
@SuppressWarnings("serial")
public class ServletCreateTask extends HttpServlet {
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		System.out.println("Creating new todo...");
		
		User user = (User) request.getAttribute("user");
		if (user == null) {
			UserService userService = UserServiceFactory.getUserService();
			user = userService.getCurrentUser();
		}

		String summary = checkNull(request.getParameter("summary"));
		String longDescription = checkNull(request.getParameter("description"));
		String url = checkNull(request.getParameter("url"));
		String dueDate = checkNull(request.getParameter("date"));
		
		TaskDAO taskDAO = new TaskDAO();
		taskDAO.add(user.getUserId(), summary, longDescription, url, dueDate);
		
		response.sendRedirect("/");
	}

	/**
	 * @param s
	 * @return empty string if s is null
	 */
	private String checkNull(String s) {
		if (s == null) {
			return "";
		}
		return s;
	}
}