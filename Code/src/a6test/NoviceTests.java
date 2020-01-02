package a6test;

import java.util.*;

import org.junit.Test;

import a6.*;

public class NoviceTests {
	Position cPos = new PositionImpl(0, 0);
	Position p1 = new PositionImpl(3, 3);
	Vehicle v1 = new VehicleImpl("Honda", "Accord", "xd", p1);
	Driver d1 = new DriverImpl("Romi", "Asad", 1, v1);
	Position p2 = new PositionImpl(2, 2);
	Vehicle v2 = new VehicleImpl("Honda", "Civic", "xd2", p2);
	Driver d2 = new DriverImpl("Sami", "Asad", 2, v2);
	Position p3 = new PositionImpl(1, 1);
	Vehicle v3 = new VehicleImpl("Honda", "Odyssey", "kms", p3);
	Driver d3 = new DriverImpl("Mazen", "Asad", 3, v3);
	List<Driver> driver_list = new ArrayList<Driver>();

	
	@Test
	public void testNextHasNext() {
		driver_list.add(d1);
		driver_list.add(d2);
		driver_list.add(d3);
		Iterator<Driver> iter = new ProximityIterator(driver_list, cPos, 4);
		System.out.println(d1.getVehicle().getPosition().getManhattanDistanceTo(cPos));
		System.out.println(d2.getVehicle().getPosition().getManhattanDistanceTo(cPos));
		System.out.println(iter.hasNext());
		System.out.println(iter.hasNext());
		System.out.println(iter.next().getFullName());
		System.out.println(iter.next().getFullName());
	}
	
}
