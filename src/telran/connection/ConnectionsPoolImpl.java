package telran.connection;

import java.util.*;

public class ConnectionsPoolImpl implements ConnectionsPool {
	HashMap<Integer, Node> mapOfConnections = new HashMap<>();
	ListConnections listOfConnections = new ListConnections();
	int limit;

	public ConnectionsPoolImpl(int limit) {
		this.limit = limit;
	}

	static class Node {

		Connection connection;
		Node next;
		Node prev;

		Node(Connection connection) {
			this.connection = connection;
		}
	}

	static class ListConnections {
		Node head;
		Node tail;

		void addHeadNode(Node connection) {
			connection.next = head;
			if (head == null) {
				tail = connection;
			} else {
				head.prev = connection;
			}
			head = connection;
		}

		void removeTailNode() {
			tail = tail.prev;
			if (tail != null) {
				tail.next = null;
			}

		}

		public void removeNode(Node connection) {
			if (connection == tail) {
				removeTailNode();
			} else {
				Node atFirst = connection.next;
				Node atTheEnd = connection.prev;
				atTheEnd.next = atFirst;
				atFirst.prev = atTheEnd;
			}
		}
	}

	@Override
	public boolean addConnection(Connection connection) {
		Node conNode = new Node(connection);
		if (mapOfConnections.containsKey(connection.getId())) {
			return false;
		}
		mapOfConnections.put(connection.getId(), conNode);
		listOfConnections.addHeadNode(conNode);

		if (limit < mapOfConnections.size()) {
			mapOfConnections.remove(listOfConnections.tail.connection.getId());
			listOfConnections.removeTailNode();
		}
		return true;
	}

	@Override
	public Connection getConnection(int id) {
		Node conNode = mapOfConnections.get(id);
		if (conNode == null) {
			return null;
		}		
		if (conNode != listOfConnections.head) {
			listOfConnections.removeNode(conNode);
			listOfConnections.addHeadNode(conNode);
		}
		return conNode.connection;
	}

}