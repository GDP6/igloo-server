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

import entities.TempReadings;
import entities.WaterTank;
import requests.ReadingUpload;

@WebServlet(
		name = "ReadingsServlet",
		urlPatterns = {"/readings"}
		)
public class ReadingsServlet  extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ObjectifyService.register(TempReadings.class);

		try {
			Gson g = new Gson();

			ReadingUpload ru = g.fromJson(req.getReader().readLine(), ReadingUpload.class);
			TempReadings tr = new TempReadings(ru.tankId,ru.sensor1,ru.sensor2,ru.sensor3,ru.timeTaken);
			ObjectifyService.ofy().save().entity(tr).now();
			resp.getWriter().print(tr.id);
		}
		catch (Exception e) {
			resp.getWriter().print(e.getMessage());

		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ObjectifyService.register(TempReadings.class);
		Gson g = new Gson();
		Iterable<TempReadings> itr = ObjectifyService.ofy().load().type(TempReadings.class);
		ArrayList<TempReadings> result = new ArrayList<TempReadings>();
		for(TempReadings tr: itr)
		{
			result.add(tr);
		}

		resp.getWriter().println(g.toJson(result));

	}

}
