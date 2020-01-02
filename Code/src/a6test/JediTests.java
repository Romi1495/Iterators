package a6test;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.Test;

import a6.*;

public class JediTests {
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
	List<Driver> driver_list_two = new ArrayList<Driver>();
	List<Driver> driver_list_three = new ArrayList<Driver>();
	
	
	@Test
	public void testJedi() {
		Position p0 = new PositionImpl(10,10);
		Position p1 = new PositionImpl(0,0);
		Position p2 = new PositionImpl(0,1);
		Position p3 = new PositionImpl(0,2);
		Position p4 = new PositionImpl(0,3);
		Position p5 = new PositionImpl(0,40);
		Position p6 = new PositionImpl(0,5);
		Position p7 = new PositionImpl(0,1);
		Position p8 = new PositionImpl(0,7);
		
		Vehicle v0 = new VehicleImpl("B", "O", "B", p0);
		Vehicle v1 = new VehicleImpl("B", "O", "B", p1);
		Vehicle v2 = new VehicleImpl("B", "O", "B", p2);
		Vehicle v3 = new VehicleImpl("B", "O", "B", p3);
		Vehicle v4 = new VehicleImpl("B", "O", "B", p4);
		Vehicle v5 = new VehicleImpl("B", "O", "B", p5);
		Vehicle v6 = new VehicleImpl("B", "O", "B", p6);
		Vehicle v7 = new VehicleImpl("B", "O", "B", p7);
		Vehicle v8 = new VehicleImpl("B", "O", "B", p8);
		
		Driver d0 = new DriverImpl("B", "Y", 0, v0);
		Driver d1 = new DriverImpl("B", "Y", 0, v1);
		Driver d2 = new DriverImpl("B", "Y", 0, v2);
		Driver d3 = new DriverImpl("B", "Y", 0, v3);
		Driver d4 = new DriverImpl("B", "Y", 0, v4);
		Driver d5 = new DriverImpl("B", "Y", 0, v5);
		Driver d6 = new DriverImpl("B", "Y", 0, v6);
		Driver d7 = new DriverImpl("B", "Y", 0, v7);
		Driver d8 = new DriverImpl("B", "Y", 0, v8);
		
		Driver[] array0 = new Driver[] {d0, d2, d1, d3};
		ArrayList<Driver> drivers0 = new ArrayList<>(Arrays.asList(array0));
		
		Driver[] array1 = new Driver[] {d6, d7, d8};
		ArrayList<Driver> drivers1 = new ArrayList<>(Arrays.asList(array1));

		Driver[] array3 = new Driver[] {d4};
		ArrayList<Driver> drivers3 = new ArrayList<>(Arrays.asList(array3));
	
		Driver[] array4 = new Driver[] {d5, d0};
		ArrayList<Driver> drivers4 = new ArrayList<>(Arrays.asList(array4));
	
		Driver[] array5 = new Driver[] {d0, d1, d8};
		ArrayList<Driver> drivers5 = new ArrayList<>(Arrays.asList(array5));
	
		List<Iterable<Driver>> iters = new ArrayList<Iterable<Driver>>();
		iters.add(drivers0); iters.add(drivers1); iters.add(drivers3); iters.add(drivers4); iters.add(drivers5);
		
		Iterator<Driver> snake = new SnakeOrderAcrossPoolsIterator(iters);
		assertTrue(snake.hasNext());
		assertEquals(snake.next(), d0);
		assertTrue(snake.hasNext());
		assertEquals(snake.next(), d6);
		assertTrue(snake.hasNext());
		assertEquals(snake.next(), d4);
		assertTrue(snake.hasNext());
		assertEquals(snake.next(), d5);
		assertTrue(snake.hasNext());
		assertEquals(snake.next(), d0);
		assertTrue(snake.hasNext());
		assertEquals(snake.next(), d1);
		assertTrue(snake.hasNext());
		assertEquals(snake.next(), d0);
		assertTrue(snake.hasNext());
		assertEquals(snake.next(), d7);
		assertTrue(snake.hasNext());
		assertEquals(snake.next(), d2);
		assertTrue(snake.hasNext());
		assertEquals(snake.next(), d1);
		assertTrue(snake.hasNext());
		assertEquals(snake.next(), d8);
		assertTrue(snake.hasNext());
		assertEquals(snake.next(), d8);
		assertTrue(snake.hasNext());
		assertEquals(snake.next(), d3);
		assertFalse(snake.hasNext());
		assertThrows(NoSuchElementException.class, () -> snake.next());
	}
}
