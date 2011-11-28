/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.milanvit.iforum.controllers;

import java.io.IOException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.milanvit.iforum.models.Thread;
import net.milanvit.iforum.models.User;

/**
 *
 * @author Milan
 */
@WebServlet (name = "CreateThread", urlPatterns = {"/secure/createThread"})
public class CreateThread extends HttpServlet {
	private String title;
	private String post;
	private String author;
	private Date created;
	private boolean locked;
	
	/** 
	 * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
	 * @param request servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an I/O error occurs
	 */
	protected void processRequest (HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Thread thread = null;
		ThreadController threadController = new ThreadController (null, null);
		User user = null;
		UserController userController = new UserController (null, null);
		
		parseValues (request);
		
		user = userController.findUser (author);
		
		thread = new Thread ();
		thread.setAuthor (user);
		thread.setCreated (created);
		thread.setLocked (locked);
		thread.setPost (post);
		thread.setTitle (title);
		
		System.out.println ("id = " + thread.getId ());
		System.out.println ("created = " + created);
		System.out.println ("locked = " + locked);
		System.out.println ("post = " + post);
		System.out.println ("title = " + title);
		System.out.println ("author = " + author);
		System.out.println ("user = " + user);
		
		try {
			threadController.create (thread);
			
			response.sendRedirect ("index.jsp");
		} catch (Exception e) {
			Logger.getLogger (CreateThread.class.getName()).log (Level.SEVERE, null, e);
		}
	}
	
	private void parseValues (HttpServletRequest request) {
		title = request.getParameter ("title");
		post = request.getParameter ("post");
		author = (String) request.getSession ().getAttribute ("username");
		created = new Date ();
		locked = false;
	}

	// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
	/** 
	 * Handles the HTTP <code>GET</code> method.
	 * @param request servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an I/O error occurs
	 */
	@Override
	protected void doGet (HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest (request, response);
	}

	/** 
	 * Handles the HTTP <code>POST</code> method.
	 * @param request servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an I/O error occurs
	 */
	@Override
	protected void doPost (HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest (request, response);
	}

	/** 
	 * Returns a short description of the servlet.
	 * @return a String containing servlet description
	 */
	@Override
	public String getServletInfo () {
		return "Short description";
	}// </editor-fold>
}