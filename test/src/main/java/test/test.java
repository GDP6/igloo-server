package test;

import java.util.ArrayList;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class test {


	public static void main(String[] args)
	{
		//String target = "http://localhost:8080";
		String target = "https://igloo-boiler.appspot.com";
		
		
		Client client = ClientBuilder.newClient();
		Response response;
		System.out.println("Creating 2 tanks");

		response = client.target(target + "/tank")
				.request(MediaType.APPLICATION_JSON_TYPE)
				.post(Entity.json("sample"));
		
		String tankId = response.readEntity(String.class);
		System.out.println(tankId);
		
		response = client.target(target + "/tank")
				.request(MediaType.APPLICATION_JSON_TYPE)
				.post(Entity.json("sample"));
		
		tankId = response.readEntity(String.class);
		System.out.println(tankId);

		Gson g = new Gson();
		
		response = client.target(target + "/tank")
				.request(MediaType.APPLICATION_JSON_TYPE)
				.get();
		String body = response.readEntity(String.class);
		
		ArrayList<WaterTank> listOfTanks = g.fromJson(body, new TypeToken<ArrayList<WaterTank>>(){}.getType());

		System.out.println("printing all ids");
		for(WaterTank w: listOfTanks)
		{
			System.out.println(w.id);
		}
		
		
		ReadingUpload ru = new ReadingUpload(Long.parseLong(tankId),3,4,5,1000L);
		System.out.println("Uploading a reading");
		response = client.target(target + "/readings")
				.request(MediaType.APPLICATION_JSON_TYPE)
				.post(Entity.json(g.toJson(ru)));
		
		
		String ruId = response.readEntity(String.class);
		System.out.println(ruId);

	
		
		
	}


}
