package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.googlecode.objectify.ObjectifyService;

import entities.WaterTank;

@WebServlet(
		name = "TankServlet",
		urlPatterns = {"/tank"}
		)
public class TankServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		WaterTank w = new WaterTank();
		resp.getWriter().print(w.id);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ObjectifyService.register(WaterTank.class);
		WaterTank w = new WaterTank();
		resp.getWriter().print(w.id);
	}
	
	

	
}
