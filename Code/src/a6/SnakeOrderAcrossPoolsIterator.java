package a6;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class SnakeOrderAcrossPoolsIterator implements Iterator<Driver> {

	// Instance fields and constants
	List<Iterator<Driver>> driver_iters = new ArrayList<Iterator<Driver>>();
	int index = 0;
	// Booleans for use with direction
	boolean forward;
	int count_next = 0;
	Driver next_driver = null;

	public SnakeOrderAcrossPoolsIterator(List<Iterable<Driver>> driver_pools) {
		// Check for null array
		if (driver_pools == null) {
			throw new IllegalArgumentException("null list");
		}
		// Check for null pools and adds pool iterators
		// to instance field of pool iterators
		// as well as counting number of drivers
		// while checking if drivers are null
		for (Iterable<Driver> pool : driver_pools) {
			int pool_size = 0;
			if (pool == null) {
				throw new IllegalArgumentException("null driver pool(s)");
			}
			for (Driver driver : pool) {
				if (driver == null) {
					throw new IllegalArgumentException("null driver(s)");
				}
				pool_size++;
			}
			if (pool_size == 0) {
				throw new IllegalArgumentException("empty pool(s)");
			}
			driver_iters.add(pool.iterator());

		}
	}

	@Override
	public boolean hasNext() {
		// If count of times next has been
		// called exceeds the number of
		// drivers, then all drivers in
		// all pools have been used
		if (next_driver != null) {
			return true;
		}
		Iterator<Driver> next_iter = driver_iters.get(logic());
		while (!next_iter.hasNext()) {
			// Find the next iterator to use and then
			// determine which direction to go
			// this also accounts for using front/end
			// iterators twice when reaching them
			count_next++;
			if (count_next > driver_iters.size() + 2) {
				return false;
			}
			next_iter = driver_iters.get(logic());
		}

		next_driver = next_iter.next();
		return true;
	}

	@Override
	public Driver next() {

		if (!hasNext()) {

			throw new NoSuchElementException("No more drivers remaining");
		}

		Driver temp = next_driver;
		next_driver = null;
		return temp;
	}

	public int logic() {
		if (forward) {
			index ++;
			if (index == driver_iters.size()) {
				forward = false;
				index = driver_iters.size() - 1;
			}
		} else {
			index --;
			if (index < 0) {
				forward = true;
				index = 0;
			}
		}
		return index;
		
	}
	
}
