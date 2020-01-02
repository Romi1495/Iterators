package a6;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ExpandingProximityIterator implements Iterator<Driver> {
	// Instance fields and constants
	Iterable<Driver> driver_pool;
	Iterator<Driver> iter;
	Position client_position;
	Driver next_driver = null;
	int expansion_step;
	int count_next = 0;
	int pool_size = 0;
	private int num_expansions = 0;

	public ExpandingProximityIterator(Iterable<Driver> driver_pool, Position client_position, int expansion_step) {
		// Checks for null pool and position
		// while checking for null drivers
		// and counting number of drivers
		if (driver_pool == null) {
			throw new IllegalArgumentException("Null driver pool");
		}
		if (client_position == null) {
			throw new IllegalArgumentException("Null client position");
		}
		for (Driver driver : driver_pool) {
			if (driver == null) {
				throw new IllegalArgumentException("Null item in driver pool");
			}
			pool_size++;
		}
		// Set instance fields to
		// passed parameter values
		this.driver_pool = driver_pool;
		this.client_position = client_position;
		this.expansion_step = expansion_step;
		this.iter = driver_pool.iterator();
	}

	@Override
	public boolean hasNext() {

		// If the count of time next() has
		// been called exceeds the pool
		// size, all drivers have been
		// checked and pool is exhausted
		if (next_driver != null) {
			return true;
		} else {
			while (count_next < pool_size) {
				
				while (iter.hasNext()) {
					next_driver = iter.next();
					int driver_dist = next_driver.getVehicle().getPosition().getManhattanDistanceTo(client_position);
					if (driver_dist <= 1 + expansion_step * num_expansions
							&& driver_dist > 1 + expansion_step * (num_expansions - 1)) {

						return true;
					}
				}
				num_expansions++;
				iter = driver_pool.iterator();
			}
		} 
		// reset next_driver to null in
		// order to not always return true
		next_driver = null;
		return false;
	}

	@Override
	public Driver next() {
		if (!hasNext()) {

			throw new NoSuchElementException("No more drivers remaining");
		}
		count_next++;
		Driver temp = next_driver;
		next_driver = null;
		return temp;
	}
}
