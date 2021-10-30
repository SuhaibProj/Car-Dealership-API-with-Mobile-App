import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;
import org.eclipse.jetty.webapp.Configuration.ClassList;

public class ControllerServer {
	/**
	 * Clas which starts up the jetty web server using database and all 
	 * constituent servlets onto specified port number
	 * @author Muhammad Suhaib 17026213
	 * @version 4.6
     */
	public static void main(String[] args) throws Exception {
		// declaring the jetty server and port. (using HTTP requests and responses)
		Server sL = new Server(8005); //listen on port 8005
		WebAppContext context = new WebAppContext(); //using web apps in jetty
		
		//setting path for folder and starting URL
		context.setResourceBase("webapp");
		context.setContextPath("/vehiclesdb");
		
		// config for server 
		context.setAttribute("org.eclipse.jetty.server.webapp.ContainerIncludeJarPattern", ".*/[^/]*jstl.*\\.jar$");
		ClassList classlist = ClassList.setServerDefault(sL);
		classlist.addBefore("org.eclipse.jetty.webapp.JettyWebXmlConfiguration", 
						"org.eclipse.jetty.annotations.AnnotationConfiguration");
				
		// Mappings for which files or classes to associate with and adding path to URL.
		context.addServlet("servlets.ServletHome", "/home"); 	
		context.addServlet("servlets.ServletAdmin", "/admin");	
		context.addServlet("servlets.ServletInsertVehicle", "/create");
		context.addServlet("servlets.ServletDeleteVehicle", "/delete");
		context.addServlet("servlets.ServletUpdateVehicle", "/update");
		context.addServlet("servlets.ServletSales", "/sales");
		
		// API Route Mappings 
		context.addServlet("servlets.ServletAPI", "/api");
		
		// Setting the handler and starting the Server
		sL.setHandler(context);
		sL.start(); //start the server
		sL.join(); //synchronise server
	}	
}
