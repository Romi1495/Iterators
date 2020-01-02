package a6test;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.*;

import org.junit.Test;

import a6.*;

public class AdeptTests {
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
	public void testAdept() {
		Position p0 = new PositionImpl(10,10);
		Position p1 = new PositionImpl(0,0);
		Position p2 = new PositionImpl(0,1);
		Position p3 = new PositionImpl(0,2);
		Position p4 = new PositionImpl(0,3);
		Position p5 = new PositionImpl(0,40);
		Position p6 = new PositionImpl(0,5);
		Position p7 = new PositionImpl(0,1);
		Position p8 = new PositionImpl(0,7);
		Position p9 = new PositionImpl(0,4);
		Position p10 = new PositionImpl(0,1);
		
		Vehicle v0 = new VehicleImpl("B", "O", "B", p0);
		Vehicle v1 = new VehicleImpl("B", "O", "B", p1);
		Vehicle v2 = new VehicleImpl("B", "O", "B", p2);  
		Vehicle v3 = new VehicleImpl("B", "O", "B", p3);
		Vehicle v4 = new VehicleImpl("B", "O", "B", p4);
		Vehicle v5 = new VehicleImpl("B", "O", "B", p5);
		Vehicle v6 = new VehicleImpl("B", "O", "B", p6);
		Vehicle v7 = new VehicleImpl("B", "O", "B", p7);
		Vehicle v8 = new VehicleImpl("B", "O", "B", p8);
		Vehicle v9 = new VehicleImpl("B", "O", "B", p9);
		Vehicle v10 = new VehicleImpl("B", "O", "B", p10);
		
		Driver d0 = new DriverImpl("B", "Y", 0, v0);
		Driver d1 = new DriverImpl("B", "Y", 0, v1);
		Driver d2 = new DriverImpl("B", "Y", 0, v2);
		Driver d3 = new DriverImpl("B", "Y", 0, v3);
		Driver d4 = new DriverImpl("B", "Y", 0, v4);
		Driver d5 = new DriverImpl("B", "Y", 0, v5);
		Driver d6 = new DriverImpl("B", "Y", 0, v6);
		Driver d7 = new DriverImpl("B", "Y", 0, v7);
		Driver d8 = new DriverImpl("B", "Y", 0, v8);
		Driver d9 = new DriverImpl("B", "Y", 0, v9);
		Driver d10 = new DriverImpl("B", "Y", 0, v10);
		
		Driver[] array = new Driver[] {d0, d1, d2, d3, d4, d5, d6, d7, d8, d9, d10};
		ArrayList<Driver> drivers = new ArrayList<>(Arrays.asList(array));
		
		Iterator<Driver> iter = new ExpandingProximityIterator(drivers, new PositionImpl(0, 0), 3);
		assertTrue(iter.hasNext());
		assertEquals(iter.next(), d1);
		assertTrue(iter.hasNext());
		assertEquals(iter.next(), d2);
		assertTrue(iter.hasNext());
		assertEquals(iter.next(), d7);
		assertTrue(iter.hasNext());
		assertEquals(iter.next(), d10);
		//RING SIZE INCREASED
		assertTrue(iter.hasNext());
		assertEquals(iter.next(), d3);
		assertTrue(iter.hasNext());
		assertEquals(iter.next(), d4);
		assertTrue(iter.hasNext());
		assertEquals(iter.next(), d9);
		//RING SIZE INCREASED
		assertTrue(iter.hasNext());
		assertEquals(iter.next(), d6);
		assertTrue(iter.hasNext());
		assertEquals(iter.next(), d8);
		//RING SIZE INCREASED MULTIPLE TIMES
		assertTrue(iter.hasNext());
		assertEquals(iter.next(), d0);
		//RING SIZE INCREASED MULTIPLE TIMES
		assertTrue(iter.hasNext());
		assertEquals(iter.next(), d5);
		//ITERATOR IS EMPTY
		assertFalse(iter.hasNext());
		assertThrows(NoSuchElementException.class, () -> iter.next());
	}
}
