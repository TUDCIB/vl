//package de.tudresden.bau.cib.vl.gui.core.servlet;
//
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.io.InputStream;
//import java.util.Map;
//
//import javax.servlet.ServletException;
//import javax.servlet.ServletOutputStream;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import de.tudresden.bau.cib.vl.gui.core.ServiceRegistry;
//
//public class FileAccessServlet extends HttpServlet {
//	
//	private static final short FILEBUFFERSIZE = 1024;
//
//	/**
//	 * 
//	 */
//	private static final long serialVersionUID = 4945896199644753382L;
//	
//	
//	private static final Logger LOG = LoggerFactory.getLogger(FileAccessServlet.class);
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
//	/**
//	 * Redirects the request to the appropriate functions.
//	 * @param request for example http://141.30.143.53:8080/ivel/file/temp?file=filter20140822.zip
//	 * @param response
//	 * @throws IOException
//	 * @throws ServletException
//	 */
//	static void redirect(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {	
//		String pathInfo = request.getPathInfo();
//		
//		if (pathInfo != null ) {
//			Map<?,?> parameterMap = request.getParameterMap();
//			String[] files = (String[]) parameterMap.get("file");
//			
//			String file = files[0];
//			
//			LOG.debug("Request from URL: {} for file: {}", new Object[]{pathInfo, file});
//			
//			ServletOutputStream out = response.getOutputStream();
//			response.setContentType("application/octet-stream");
//			response.setHeader("Content-Disposition", "attachment; filename="+file);
//			InputStream in;
//			try {
//				in = ServiceRegistry.getInstance().getUserService().getUserFileStreamFromUrl(pathInfo, file);
////			    FileNameMap fileNameMap = URLConnection.getFileNameMap();
////			    String mimeType = fileNameMap.getContentTypeFor(request.getRequestURI());
//				byte[] bytes = new byte[FILEBUFFERSIZE];
//				int bytesRead;
//				
////				response.setContentType(mimeType);
//				
//				while ((bytesRead = in.read(bytes)) != -1) {
//				    out.write(bytes, 0, bytesRead);
//				}
//				
//				in.close();
//				out.close();
//			} catch (FileNotFoundException e) {
//				throw new ServletException(e);
//			}			
//		} else {
//			response.sendError( HttpServletResponse.SC_NOT_FOUND );
//		}
//	}
//}
//
//
