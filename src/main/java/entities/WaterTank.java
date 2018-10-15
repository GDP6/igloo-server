package entities;

import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

@Entity
public class WaterTank {
	
	public @Id Long id;
	public WaterTank() {
		ObjectifyService.ofy().save().entity(this).now();
	}


}
