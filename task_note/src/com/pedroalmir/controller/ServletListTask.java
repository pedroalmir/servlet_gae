/**
 * 
 */
package com.pedroalmir.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.pedroalmir.model.Task;
import com.pedroalmir.repository.TaskDAO;

/**
 * @author Pedro Almir
 *
 */
@SuppressWarnings("serial")
public class ServletListTask extends HttpServlet {
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		System.out.println("Listing tasks...");
		
		User user = (User) request.getAttribute("user");
		UserService userService = UserServiceFactory.getUserService();
		
		String url = userService.createLoginURL(request.getRequestURI());
		String urlLinktext = "Login";
		
		if (user == null) {
			user = userService.getCurrentUser();
		}
		TaskDAO taskDAO = new TaskDAO();
		List<Task> taskByUser = new LinkedList<Task>();
		if(user != null){
			taskByUser = taskDAO.findAllByID(user.getUserId());
			url = userService.createLogoutURL(request.getRequestURI());
		    urlLinktext = "Logout";
		}
		
		request.setAttribute("url", url);
		request.setAttribute("tasks", taskByUser);
		request.setAttribute("urlLinktext", urlLinktext);
		request.setAttribute("userNickname", user == null ? "" : user.getNickname());
		request.setAttribute("logged", user == null ? false : true);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
        try {
			dispatcher.forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		}
	}

}