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
		/* Variáveis */
		String url = null, urlLinkText = null;
		
		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();
		
		if(user != null){/* Usuário logado */
			/* Buscando as tarefas */
			TaskDAO taskDAO = new TaskDAO();
			List<Task> taskByUser = new LinkedList<Task>();
			
			taskByUser = taskDAO.findAllByID(user.getUserId());
			request.setAttribute("tasks", taskByUser);
			request.setAttribute("userNickname", user.getNickname());
			request.setAttribute("logged", true);
			
			urlLinkText = "Logout";
			url = userService.createLogoutURL(request.getRequestURI());
		}else{/* Usuário não logado */
			urlLinkText = "Login";
			url = userService.createLoginURL(request.getRequestURI());
		}
		
		request.setAttribute("url", url);
		request.setAttribute("urlLinkText", urlLinkText);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
        try {
			dispatcher.forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		}
	}
}