public class Train {

	// Kann fuer die Ausgabe verwendet werden
	private static final String LOCOMOTIVE = "<___|o|";

	private Waggon head;

	public int getSize() {
		int size = 0;
		Waggon current = head;
		while (current != null) {
			size++;
			current = current.getNext();
		}
		return size;
	}

	public int getPassengerCount() {
		int count = 0;
		Waggon current = head;
		while (current != null) {
			count += current.getPassengers();
			current = current.getNext();
		}
		return count;
	}

	public int getCapacity() {
		int count = 0;
		Waggon current = head;
		while (current != null) {
			count += current.getCapacity();
			current = current.getNext();
		}
		return count;
	}

	public void appendWaggon(Waggon waggon) {
		if (head == null) {
			head = waggon;
		} else {
			Waggon current = head;
			while (current.getNext() != null) {
				current = current.getNext();
			}
			current.setNext(waggon);
		}
	}

	public void boardPassengers(int numberOfPassengers) {
		Waggon current = head;
		while (numberOfPassengers > 0 && current != null) {
			int freePlace = current.getCapacity() - current.getPassengers();
			if (numberOfPassengers > freePlace) {
				current.setPassengers(current.getCapacity());
				numberOfPassengers -= freePlace;
				current = current.getNext();
			} else {
				current.setPassengers(current.getPassengers() + numberOfPassengers);
				numberOfPassengers = 0;
			}
		}
	}

	public Train uncoupleWaggons(int index) {
		if (index <= 0 || index >= this.getSize()) {
			return null;
		} else {
			Waggon previous = null;
			Waggon current = head;
			int currentIndex = 0;
			while (current != null && currentIndex < index) {
				previous = current;
				current = current.getNext();
				currentIndex++;
			}
			Train newTrain = new Train();
			newTrain.head = current;
			previous.setNext(null);
			return newTrain;
		}
	}

	public void insertWaggon(Waggon waggon, int index) {
		if (index == 0) {
			waggon.setNext(head);
			head = waggon;
		} else {
			Waggon previous = null;
			Waggon current = head;
			int currentIndex = 0;
			while (current != null && currentIndex < index) {
				previous = current;
				current = current.getNext();
				currentIndex++;
			}
			if (current == null) {
				previous.setNext(waggon);
			} else {
				waggon.setNext(current);
				previous.setNext(waggon);
			}
		}
	}

	public void turnOver() {
		Waggon previous = null;
		Waggon current = head;
		Waggon next;
		while (current != null) {
			next = current.getNext();
			current.setNext(previous);
			previous = current;
			current = next;
		}
		head = previous;
	}

	public void removeWaggon(Waggon waggon) {
		if (head == null) {
			return;
		}
		Waggon previous = null;
		Waggon current = head;
		while (current != null && !waggon.getName().equals(current.getName())) {
			previous = current;
			current = current.getNext();
		}
		if (current == head) {
			head = head.getNext();
		} else if (current != null) {
			if (current.getNext() == null) {
				previous.setNext(null);
			} else {
				previous.setNext(current.getNext());
			}
		}
	}

	public Waggon getWaggonAt(int index) {
		if (index < 0 || index >= this.getSize()) {
			return null;
		}
		int currentIndex = 0;
		Waggon current = head;
		while (current != null && currentIndex < index) {
			current = current.getNext();
			currentIndex++;
		}
		return current;
	}

	@Override
	public String toString() {
		if (getSize() == 0) {
			return LOCOMOTIVE;
		}

		StringBuilder str = new StringBuilder(LOCOMOTIVE);

		Waggon pointer = head;
		while (pointer != null) {
			str.append(" <---> ");
			str.append(pointer.getName());
			pointer = pointer.getNext();
		}

		return str.toString();
	}
	
	public Waggon getHead() {
		return head;
	}

}
