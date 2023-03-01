package telran.connection;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;



class ConnectionsPoolImplTest {

	ConnectionsPoolImpl pool;

	@BeforeEach
	void setUp() {
		pool = new ConnectionsPoolImpl(3);
		pool.addConnection(new Connection(1, "192.168.0.1", 25));
		pool.addConnection(new Connection(2, "8.8.8.8", 8080));
		pool.addConnection(new Connection(3, "192.168.27.11", 21));
	}

	@Test
	void addConnection() {
		assertFalse(pool.addConnection(new Connection(3, "192.168.27.11", 21)));		
		assertEquals(pool.listOfConnections.head.connection, pool.getConnection(3));
		assertTrue(pool.addConnection(new Connection(4, "255.255.255.0", 53)));
		assertEquals(pool.listOfConnections.head.connection, pool.getConnection(4));
		assertEquals(pool.listOfConnections.tail.connection, pool.getConnection(2));
		assertTrue(pool.addConnection(new Connection(1, "192.168.0.1", 25)));
	}

	@Test
	void getConnection() {
		assertNull(pool.getConnection(0));
		
		assertEquals("192.168.0.1", pool.getConnection(1).ipAddress);
		assertEquals(25, pool.getConnection(1).port);		

		assertEquals(2, pool.getConnection(2).id);
		assertEquals(pool.listOfConnections.head.connection, pool.getConnection(2));
		
		assertEquals("192.168.27.11", pool.getConnection(3).ipAddress);
		assertNotEquals("8.8.8.8", pool.getConnection(3).ipAddress);
		
		assertEquals(8080, pool.getConnection(2).port);
		assertNotEquals(21, pool.getConnection(2).port);
	
		assertNull(pool.getConnection(4));	
	}

}