package telran.connection;


public interface ConnectionsPool {

	boolean addConnection(Connection connection);	
	
	Connection getConnection (int id);

}