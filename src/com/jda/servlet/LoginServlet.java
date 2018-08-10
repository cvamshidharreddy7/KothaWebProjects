package com.jda.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jda.daoRepo.UserRepositoryImpl;
import com.jda.model.User;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    UserRepositoryImpl userRepositoryImpl = new UserRepositoryImpl();
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	
		
		String username=request.getParameter("username");
		
		String password=request.getParameter("password");
		
		try {
		int value = 	userRepositoryImpl.check(username, password);
		if(value ==1){	System.out.println("Email or  is wrong");
			response.sendRedirect("home.html");
		}
		else{
			System.out.println("Email or password is wrong");
		}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.sendRedirect("login.html");
		}
	}


