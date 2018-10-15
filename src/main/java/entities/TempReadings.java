package entities;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

@Entity
public class TempReadings {
	
	public @Id Long id;
	public Key<WaterTank> tankKey;
	public int sensor1;
	public int sensor2;
	public int sensor3;
	public Long timeTaken;
	
	public TempReadings() {}

	public TempReadings(Key<WaterTank> tankKey, int sensor1, int sensor2, int sensor3, Long timeTaken) {
		super();
		this.tankKey = tankKey;
		this.sensor1 = sensor1;
		this.sensor2 = sensor2;
		this.sensor3 = sensor3;
		this.timeTaken = timeTaken;
		ObjectifyService.ofy().save().entity(this).now();
	}
	
	


}
