package a6;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ProximityIterator implements Iterator<Driver> {

	// Constants and instance fields
	Iterator<Driver> driver_iter;
	Position client_position;
	int proximity_limit;
	Driver next_driver;

	public ProximityIterator(Iterable<Driver> driver_pool, Position client_position, int proximity_limit) {
		// Checks for null driver pool
		// as well as null drivers
		// and null position
		if (driver_pool == null ) {
			throw new IllegalArgumentException("null driver pool");
		}
		if (client_position == null) {
			throw new IllegalArgumentException("null position");
		}
		for (Driver driver : driver_pool) {
			if (driver == null) {
				throw new IllegalArgumentException("null driver(s) in pool");
			}
		}
		
		this.driver_iter = driver_pool.iterator();
		this.client_position = client_position;
		this.proximity_limit = proximity_limit;
	}

	@Override
	public boolean hasNext() { 
		
		if (next_driver != null) {
			return true;
		} else {
			while (driver_iter.hasNext()) {
				Driver next = driver_iter.next();
				if (next.getVehicle().getPosition().getManhattanDistanceTo(client_position) <= proximity_limit) {
					
					next_driver = next;
					return true;
				}
			}
			
		} 
		return false;
	} 

	@Override
	public Driver next() {
		
		if (!hasNext()) {
			throw new NoSuchElementException();
		}
		Driver temp_driver = next_driver;
		next_driver = null;
		return temp_driver;
	}
}
