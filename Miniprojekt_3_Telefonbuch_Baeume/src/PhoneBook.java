import java.util.ArrayList;
import java.util.Arrays;

public class PhoneBook {
	
	private Person root;
	
	// NICHT aendern
	public PhoneBook(){
		//Dieser Konstruktor darf von Ihnen nicht veraendert werden.
		//Falls Sie einen weiteren Konstruktor benoetigen, so koennen Sie diesen jedoch gerne hinzufuegen.
	}
	
	// Aufgabe 1
	// Die Methodensignatur darf NICHT geaendert werden
	public void insertPerson(String lastName, String firstName, int number, boolean married){
		Person newPerson = new Person(lastName, firstName, number, married);
		if (root == null){
			root = newPerson;
		} else {
			insertPerson(newPerson, root);
		}
	}
	
	// Rueckgabetyp und Parametrisierung dieser Methode duerfen geaendert werden!
	private void insertPerson(Person newPerson, Person currentNode){
		if (this.compareTwoPersons(newPerson, currentNode) < 0) {
			if (currentNode.getLeftSuccessor() == null) {
				currentNode.setLeftSuccessor(newPerson);
			} else {
				insertPerson(newPerson, currentNode.getLeftSuccessor());
			}
		} else if (this.compareTwoPersons(newPerson, currentNode) > 0) {
			if (currentNode.getRightSuccessor() == null) {
				currentNode.setRightSuccessor(newPerson);
			} else {
				insertPerson(newPerson, currentNode.getRightSuccessor());
			}
		}
	}
	
	// Aufgabe 2
	// Die Methodensignatur darf NICHT geaendert werden
	public boolean findPerson(String lastName, String firstName, int number){
		if (root == null) {
			return false;
		} else {
			return findPerson(lastName, firstName, number, root);
		}
	}
	
	// Rueckgabetyp und Parametrisierung dieser Methode duerfen geaendert werden! 
	private boolean findPerson(String lastName, String firstName, int number, Person currentNode){
		if (currentNode == null) {
			return false;
		}
		if (currentNode.getLastName().equals(lastName) && currentNode.getFirstName().equals(firstName) &&
			currentNode.getNumber() == number) {
			return true;
		} else {
			boolean left = findPerson(lastName, firstName, number, currentNode.getLeftSuccessor());
			boolean right = findPerson(lastName, firstName, number, currentNode.getRightSuccessor());
			return left || right;
		}
	}
	
	// Aufgabe 3
	// Die Methodensignatur darf NICHT geaendert werden
	public int countAllPersons() {
		if (root == null) {
			return 0;
		} else {
			return countAllPersons(root);
		}
	}
	
	// Rueckgabetyp und Parametrisierung dieser Methode duerfen geaendert werden!
	public int countAllPersons(Person currentNode) {
		if (currentNode == null) {
			return 0;
		}
		return 1 + countAllPersons(currentNode.getLeftSuccessor()) + countAllPersons(currentNode.getRightSuccessor());
	}
	
	// Aufgabe 4
	// Die Methodensignatur darf NICHT geaendert werden
	public Person[] findAllPersonsByFirstName(String firstName){
		if (root == null) {
			return new Person[0];
		} else {
			return findAllPersonsByFirstName(firstName, root);
		}
	}
	
	// Rueckgabetyp und Parametrisierung dieser Methode duerfen geaendert werden!
	private Person[] findAllPersonsByFirstName(String firstName, Person currentNode){
		Person[] left = null;
		Person[] right = null;
		if (currentNode.getLeftSuccessor() == null) {
			left = new Person[0];
		} else {
			left = findAllPersonsByFirstName(firstName, currentNode.getLeftSuccessor());
		}

		if (currentNode.getRightSuccessor() == null) {
			right = new Person[0];
		} else {
			right = findAllPersonsByFirstName(firstName, currentNode.getRightSuccessor());
		}

		int lengthOfResult = left.length + right.length;
		if (currentNode.getFirstName().equals(firstName)) {
			lengthOfResult++;
		}
		Person[] result = new Person[lengthOfResult];
		int index = 0;
		for (Person person : left) {
			result[index++] = person;
		}
		if (currentNode.getFirstName().equals(firstName)) {
			result[index++] = currentNode;
		}
		for (Person person : right) {
			result[index++] = person;
		}
		return result;
	}
	
	// Aufgabe 5
	// Die Methodensignatur darf NICHT geaendert werden
	public void marryTheHochzeits() {
		if (root != null) {
			marryTheHochzeits(root);
		}
	}
	
	// Rueckgabetyp und Parametrisierung dieser Methode duerfen geaendert werden!
	private void marryTheHochzeits(Person currentNode) {
		if (currentNode != null) {
			if (currentNode.getLastName().equals("Hochzeit")) {
				currentNode.setMarried(true);
			}
			marryTheHochzeits(currentNode.getLeftSuccessor());
			marryTheHochzeits(currentNode.getRightSuccessor());
		}
	}
	
	// Aufgabe 6
	// Die Methodensignatur darf NICHT geaendert werden
	public void removePersonFromPhoneBook(String lastName, String firstName, int number){
		if (root != null) {
			if (compareTwoPersons(lastName, firstName, number, root) == 0) {
				if (root.getLeftSuccessor() == null && root.getRightSuccessor() == null) {
					root = null;
				} else {
					removePersonFromPhoneBook(lastName, firstName, number, root);
				}
			} else {
				removePersonFromPhoneBook(lastName, firstName, number, root);
			}
		}
	}
	
	// Rueckgabetyp und Parametrisierung dieser Methode duerfen geaendert werden!
	private Person removePersonFromPhoneBook(String lastName, String firstName, int number, Person currentNode){
		if (currentNode == null) {
			return null;
		}
		if (this.compareTwoPersons(lastName, firstName, number, currentNode) < 0) {
			currentNode.setLeftSuccessor(removePersonFromPhoneBook(lastName, firstName, number,
					currentNode.getLeftSuccessor()));
		} else if (this.compareTwoPersons(lastName, firstName, number, currentNode) > 0) {
			currentNode.setRightSuccessor(removePersonFromPhoneBook(lastName, firstName, number,
					currentNode.getRightSuccessor()));
		} else {
			if (currentNode.getLeftSuccessor() == null && currentNode.getRightSuccessor() == null) {
				currentNode = null;
			} else if (currentNode.getRightSuccessor() != null) {
				Person successor = this.successor(currentNode);
				currentNode.setFirstName(successor.getFirstName());
				currentNode.setLastName(successor.getLastName());
				currentNode.setNumber(successor.getNumber());
				currentNode.setMarried(successor.isMarried());
				currentNode.setRightSuccessor(removePersonFromPhoneBook(currentNode.getLastName(),
						currentNode.getFirstName(), currentNode.getNumber(), currentNode.getRightSuccessor()));
			} else {
				Person predecessor = this.predecessor(currentNode);
				currentNode.setFirstName(predecessor.getFirstName());
				currentNode.setLastName(predecessor.getLastName());
				currentNode.setNumber(predecessor.getNumber());
				currentNode.setMarried(predecessor.isMarried());
				currentNode.setLeftSuccessor(removePersonFromPhoneBook(currentNode.getLastName(),
						currentNode.getFirstName(), currentNode.getNumber(), currentNode.getLeftSuccessor()));
			}
		}
		return currentNode;
	}

	private Person successor(Person root) {
		root = root.getRightSuccessor();
		while (root.getLeftSuccessor() != null) {
			root = root.getLeftSuccessor();
		}
		return root;
	}

	private Person predecessor(Person root) {
		root = root.getLeftSuccessor();
		while (root.getRightSuccessor() != null) {
			root = root.getRightSuccessor();
		}
		return root;
	}
	
	// Aufgabe 7
	// Die Methodensignatur darf NICHT geaendert werden
	public void changePerson(String lastName, String firstName, int number, String newLastname){
		if (root != null) {
			changePerson(lastName, firstName, number, newLastname, root);
		}
	}
	
	// Rueckgabetyp und Parametrisierung dieser Methode duerfen geaendert werden!
	private void changePerson(String lastName, String firstName, int number, String newLastname, Person currentNode){
		if (findPerson(lastName, firstName, number)) {
			removePersonFromPhoneBook(lastName, firstName, number);
			insertPerson(newLastname, firstName, number, true);
		}
	}
	
	//Diese Methode vergleicht zwei Objekte vom Typ Person im Sinne der Sortierkriterien des Telefonbuchs. 
	//Keiner der Parameter darf null sein!
	//Folgende Rueckgabewerte sind moeglich:
	// * 0, wenn person1 und person2 gleich sind
	// * Negative Zahl, wenn person1 im Sinne des Sortierkriteriums kleiner ist als person2
	// * Positive Zahl, wenn person1 im Sinne des Sortierkriteriums groesser ist als person2
	private int compareTwoPersons(Person person1, Person person2) {
		if (person1.getLastName().compareToIgnoreCase(person2.getLastName())!=0) {
			return person1.getLastName().compareToIgnoreCase(person2.getLastName());
		}
		if (person1.getFirstName().compareToIgnoreCase(person2.getFirstName())!=0) {
			return person1.getFirstName().compareToIgnoreCase(person2.getFirstName());
		}
		return person1.getNumber()-person2.getNumber();
	}
	
	//Diese Methode funktioniert wie die obige, nur dass Sie den Nachnamen, den Vornamen und die 
	//Telefonnummer der ersten Person als Parameter uebergeben koennen.
	private int compareTwoPersons(String lastName, String firstName, int number, Person person2) {
		if (lastName.compareToIgnoreCase(person2.getLastName())!=0) {
			return lastName.compareToIgnoreCase(person2.getLastName());
		}
		if (firstName.compareToIgnoreCase(person2.getFirstName())!=0) {
			return firstName.compareToIgnoreCase(person2.getFirstName());
		}
		return number-person2.getNumber();
	}
	
	// Hilfsmethode um den Baum auf der Konsole ausgeben zu koennen
	private void printTree() {
		printNode(0, root, "root");
	}
	
	// Hilfsmethode um den Baum auf der Konsole ausgeben zu koennen
	// verwendet Rekursion
	private void printNode(int depth, Person currentNode, String position) {
		if (currentNode != null) {
			printNode(depth + 1, currentNode.getRightSuccessor(), "right");
			String indentation = "";
			for (int i = 0; i < depth; i++) {
				indentation += "         ";
			}
			System.out.println(indentation + "- " + position + ":" + depth + ": "+ currentNode);
			printNode(depth + 1, currentNode.getLeftSuccessor(), "left");
		}
	}

	public static void main(String[] args) {
		//Die folgenden Methoden testen einzeln die Zu bearbeitenden Aufgaben. Sie koennen diese einzeln
		//aus- und einkommentieren, falls Sie erst Teile der Loesung programmiert haben.
//		testInsertPerson();
//		testFindPerson();
//		testCountPersons();
//		testFindPersons();
//		testMarryTheHochzeits();
		testRemovePersonFromPhoneBook();
//		testChangePerson();
	}
	
	private static void testInsertPerson() {
		//Erzeuge Personen
		Person person1 = new Person("Meier", "Rainer", 123456, false);
		Person person2 = new Person("Maier", "Reiner", 123356, false);
		Person person3 = new Person("Meier", "Reiner", 123556, false);
		Person person4 = new Person("Neuberg", "Tina", 123466, false);
		Person person5 = new Person("Lutz", "Michaela", 123856, false);
		Person person6 = new Person("Fischer", "Bernd", 113456, false);
		Person person7 = new Person("Otto", "Josephine", 123999, false);
		Person person8 = new Person("Bertram", "Heinz", 123456, false);
		Person person9 = new Person("Martens", "Nicole", 838396, false);
		Person person10 = new Person("Nahles", "Andrea", 793256, false);
		
		Person testPerson = new Person("Gauck", "Beate", 654456, false);
		
		person1.setLeftSuccessor(person2);
		person1.setRightSuccessor(person3);
		person3.setRightSuccessor(person4);
		person2.setLeftSuccessor(person5);
		person5.setLeftSuccessor(person6);
		person4.setRightSuccessor(person7);
		person6.setLeftSuccessor(person8);
		person2.setRightSuccessor(person9);
		person4.setLeftSuccessor(person10);
		
		PhoneBook pb = new PhoneBook();
		pb.root = person1;
		
		System.out.println("====================================== Teste insertPerson() ======================================");
		System.out.println("Erwartete Ausgabe:");
		person6.setRightSuccessor(testPerson);
		pb.printTree();
		person6.setRightSuccessor(null);
		
		System.out.println("");
		System.out.println("====================================================================================================");
		System.out.println("");
		System.out.println("Ihre Ausgabe:");
		pb.insertPerson("Gauck", "Beate", 654456, false);
		pb.printTree();
		System.out.println("");
	}
	
	private static void testFindPerson() {
		Person person1 = new Person("Merkel", "Angela", 111111, false);
		Person person2 = new Person("Merkel", "Angela", 111110, false);
		Person person3 = new Person("Merkel", "Angela", 123556, false);
		Person person4 = new Person("Merkel", "Ingo", 123466, false);
		Person person5 = new Person("Merkel", "Adam", 123856, false);
		Person person6 = new Person("Merkel", "Bernd", 113456, false);
		
		Person person7 = new Person("Merkel", "Angela", 113456, false);
		
		person1.setLeftSuccessor(person2);
		person1.setRightSuccessor(person3);
		person3.setRightSuccessor(person4);
		person2.setLeftSuccessor(person5);
		person4.setLeftSuccessor(person6);
		
		PhoneBook pb = new PhoneBook();
		pb.root = person1;
		
		System.out.println("====================================== Teste findPerson() ======================================");
		System.out.println("");
		pb.printTree();
		System.out.println("");
		System.out.println("Suche Person: " + person6);
		System.out.println("Erwartete Ausgabe: true");
		System.out.println("Ihre Ausgabe: " + pb.findPerson(person6.getLastName(), person6.getFirstName(), person6.getNumber()));
		System.out.println("");
		System.out.println("Suche Person: " + person7);
		System.out.println("Erwartete Ausgabe: false");
		System.out.println("Ihre Ausgabe: " + pb.findPerson(person7.getLastName(), person7.getFirstName(), person7.getNumber()));
		System.out.println("");
	}
	
	private static void testCountPersons() {
		Person person1 = new Person("Meier", "Rainer", 123456, false);
		Person person2 = new Person("Maier", "Reiner", 123356, false);
		Person person3 = new Person("Meier", "Reiner", 123556, false);
		Person person4 = new Person("Neuberg", "Tina", 123466, false);
		Person person5 = new Person("Lutz", "Michaela", 123856, false);
		Person person6 = new Person("Fischer", "Bernd", 113456, false);
		
		person1.setLeftSuccessor(person2);
		person1.setRightSuccessor(person3);
		person3.setRightSuccessor(person4);
		person2.setLeftSuccessor(person5);
		person5.setLeftSuccessor(person6);
		
		PhoneBook pb = new PhoneBook();
		pb.root = person1;
		
		System.out.println("====================================== Teste countPerson() ======================================");
		System.out.println("");
		pb.printTree();
		System.out.println("");
		System.out.println("Erwartete Ausgabe: 6");
		System.out.println("Ihre Ausgabe: " + pb.countAllPersons());
	}
	
	private static void testFindPersons() {
		Person person1 = new Person("Meier", "Rainer", 123457, false);
		Person person2 = new Person("Meier", "Rainer", 123456, false);
		Person person3 = new Person("Neumann", "Rainer", 123456, false);
		Person person4 = new Person("Mustermann", "Max", 123456, false);
		Person person5 = new Person("Otto", "Reiner", 123456, false);
		Person person6 = new Person("Fischer", "Oskar", 123456, false);
		
		person1.setLeftSuccessor(person2);
		person1.setRightSuccessor(person3);
		person2.setLeftSuccessor(person6);
		person3.setLeftSuccessor(person4);
		person3.setRightSuccessor(person5);
		
		PhoneBook pb = new PhoneBook();
		pb.root = person1;
		
		Person[] testArray = new Person[3];
		testArray[0] = person2;
		testArray[1] = person1;
		testArray[2] = person3;
		
		
		System.out.println("================================= Teste findAllPersonsByFirstName() =================================");
		System.out.println("");
		pb.printTree();
		System.out.println("");
		System.out.println("Suche Person mit Vornamen: Rainer");
		System.out.println("Erwartete Ausgabe: " + Arrays.deepToString(testArray));
		System.out.println(Arrays.deepToString(testArray));
		System.out.println("Ihre Ausgabe: " + Arrays.deepToString(pb.findAllPersonsByFirstName("Rainer")));
		
	}
	
	private static void testMarryTheHochzeits() {
		Person person1 = new Person("Meier", "Rainer", 123456, false);
		Person person2 = new Person("Hochzeit", "Reiner", 123356, true);
		Person person3 = new Person("Meier", "Reiner", 123556, false);
		Person person4 = new Person("Fischer", "Bernd", 113456, false);
		Person person5 = new Person("Hochzeit", "Otto", 123356, true);
		Person person6 = new Person("Hochzeit", "Zacharias", 123356, true);
		
		person1.setLeftSuccessor(person2);
		person1.setRightSuccessor(person3);
		person2.setLeftSuccessor(person4);
		person4.setRightSuccessor(person5);
		person2.setRightSuccessor(person6);
		
		PhoneBook pb = new PhoneBook();
		pb.root = person1;
		
		System.out.println("====================================== Teste marryTheHochzeits() ======================================");
		System.out.println("");
		System.out.println("Erwartete Ausgabe:");
		pb.printTree();
		System.out.println("");
		System.out.println("====================================================================================================");
		System.out.println("");
		System.out.println("Ihre Ausgabe:");
		person2.setMarried(false);
		person5.setMarried(false);
		person6.setMarried(false);
		pb.marryTheHochzeits();
		pb.printTree();
		System.out.println("");
	}
	
	private static void testRemovePersonFromPhoneBook() {
		Person person1 = new Person("Meier", "Rainer", 123456, false);
		Person person2 = new Person("Maier", "Reiner", 123356, false);
		Person person3 = new Person("Meier", "Reiner", 123556, false);
		Person person4 = new Person("Neuberg", "Tina", 123466, false);
		Person person5 = new Person("Lutz", "Michaela", 123856, false);
		Person person6 = new Person("Fischer", "Bernd", 113456, false);
		
		person1.setLeftSuccessor(person2);
		person1.setRightSuccessor(person3);
		person3.setRightSuccessor(person4);
		person2.setLeftSuccessor(person5);
		person5.setLeftSuccessor(person6);
		
		PhoneBook pb = new PhoneBook();
		pb.root = person1;
		
		System.out.println("====================================== Teste removePersonFromPhoneBook() ======================================");
		System.out.println("");
		pb.printTree();
		System.out.println("");
		System.out.println("Lösche Person: " + person2);
		person1.setLeftSuccessor(person5);
		System.out.println("Erwartete Ausgabe: (Solange ihr Baum die gleichen Elemente enthaelt und sortiert ist, waere auch das eine korrekte Loesung!)");
		System.out.println("");
		pb.printTree();
		System.out.println("====================================================================================================");
		person1.setLeftSuccessor(person2);
		System.out.println("");
		System.out.println("Ihre Ausgabe:");
		System.out.println("");
		pb.removePersonFromPhoneBook("Maier", "Reiner", 123356);
		pb.printTree();
		
	}
	
	private static void testChangePerson() {
		Person person1 = new Person("Meier", "Rainer", 123456, false);
		Person person2 = new Person("Maier", "Reiner", 123356, false);
		Person person3 = new Person("Meier", "Reiner", 123556, false);
		Person person4 = new Person("Neuberg", "Tina", 123466, false);
		Person person5 = new Person("Lutz", "Michaela", 123856, false);
		Person person6 = new Person("Fischer", "Bernd", 113456, false);

		person1.setLeftSuccessor(person2);
		person1.setRightSuccessor(person3);
		person3.setRightSuccessor(person4);
		person2.setLeftSuccessor(person5);
		person5.setLeftSuccessor(person6);

		PhoneBook pb = new PhoneBook();
		pb.root = person1;

		System.out.println("====================================== Teste changePerson() ======================================");
		System.out.println("");
		pb.printTree();
		System.out.println("");
		System.out.println("Ändere Nachnamen von Person: " + person2 + " zu 'Zock'");
		person2.setLastName("Zock");
		person2.setMarried(true);
		person1.setLeftSuccessor(person5);
		person4.setRightSuccessor(person2);
		person2.setLeftSuccessor(null);
		System.out.println("Erwartete Ausgabe: (Solange ihr Baum die gleichen Elemente enthaelt und sortiert ist, waere auch das eine korrekte Loesung!)");
		System.out.println("");
		pb.printTree();
		System.out.println("====================================================================================================");
		person2.setLastName("Maier");
		person2.setMarried(false);
		person1.setLeftSuccessor(person2);
		person4.setRightSuccessor(null);
		person2.setLeftSuccessor(person5);
		System.out.println("");
		System.out.println("Ihre Ausgabe:");
		System.out.println("");
		pb.changePerson("Maier", "Reiner", 123356,"Zock");
		pb.printTree();
	}

}
