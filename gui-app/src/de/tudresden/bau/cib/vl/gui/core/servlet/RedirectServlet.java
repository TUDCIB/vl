//package de.tudresden.bau.cib.vl.gui.core.servlet;
//
//import java.io.IOException;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//public class RedirectServlet extends HttpServlet {
//
//	/**
//	 * 
//	 */
//	private static final long serialVersionUID = 4945896199644753382L;
//	
//	
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		redirect( request, response );
//	}
//
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		redirect( request, response );
//	}
//
//	static void redirect(HttpServletRequest request, HttpServletResponse response) throws IOException {
//		if(request.getRequestURI().equals("/")){
//			response.sendRedirect( response.encodeRedirectURL("default") );
//		} else {
//			response.sendError( HttpServletResponse.SC_NOT_FOUND );
//		}
////		if( request.getPathInfo().equals( "/" ) ) {
////			response.sendRedirect( response.encodeRedirectURL( "start" ) );
////		} else {
////			response.sendError( HttpServletResponse.SC_NOT_FOUND );
////		}
//	}
//}
