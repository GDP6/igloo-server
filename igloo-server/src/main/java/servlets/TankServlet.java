package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.googlecode.objectify.ObjectifyService;

import entities.WaterTank;

@WebServlet(
		name = "TankServlet",
		urlPatterns = {"/tank"}
		)
public class TankServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ObjectifyService.register(WaterTank.class);
		WaterTank w = new WaterTank();
		ObjectifyService.ofy().save().entity(w).now();
		System.out.println(w);
		resp.getWriter().print(w.id);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ObjectifyService.register(WaterTank.class);
		Gson g = new Gson();
		Iterable<WaterTank> iw = ObjectifyService.ofy().load().type(WaterTank.class);
		ArrayList<WaterTank> result = new ArrayList<WaterTank>();
		for(WaterTank w: iw)
		{
			result.add(w);
		}
		resp.getWriter().println(g.toJson(result));

	}
	
	

	
}
