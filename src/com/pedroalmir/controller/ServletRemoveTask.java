/**
 * 
 */
package com.pedroalmir.controller;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pedroalmir.repository.TaskDAO;

/**
 * @author Pedro Almir
 *
 */
public class ServletRemoveTask extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String id = request.getParameter("id");
		
		TaskDAO taskDAO = new TaskDAO();
		taskDAO.remove(Long.parseLong(id));
		
		response.sendRedirect("/");
	}
}
