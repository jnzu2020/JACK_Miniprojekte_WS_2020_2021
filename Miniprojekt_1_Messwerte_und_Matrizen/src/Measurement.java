import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Measurement {

	private int[] data;

	public Measurement() {
		data = new int[0];
	}

	public void addValue(int value) {
		int[] newData = new int[data.length + 1];
		System.arraycopy(data, 0, newData, 0, data.length);
		newData[newData.length - 1] = value;
		data = newData;
	}

	public void addValues(int[] values) {
		int[] newData = new int[data.length + values.length];
		System.arraycopy(data, 0, newData, 0, data.length);
		System.arraycopy(values, 0, newData, data.length, values.length);
		data = newData;
	}

	public int getMinimumValue() {
		int min = data[0];
		for (int value : data) {
			min = Math.min(min, value);
		}
		return min;
	}

	public int[] getValuesAboveThreshold(int threshold) {
		if (data.length == 0) {
			return new int[0];
		}
		List<Integer> list = new ArrayList<>();
		for (int value : data) {
			if (value > threshold) {
				list.add(value);
			}
		}
		if (list.size() == 0) {
			return new int[0];
		} else {
			int[] result = new int[list.size()];
			for (int i = 0 ; i < result.length ; i++) {
				result[i] = list.get(i);
			}
			return result;
		}
	}

	// --------------------------------------------------------------
	
	public void printData() {
		System.out.println(Arrays.toString(data));
	}

	public static void main(String[] args) {
		Measurement m = new Measurement();
		System.out.println("Neues Measurement-Objekt m erzeugt. m:");
		// ErwarteteAusgabe:
		// []
		m.printData();

		System.out.println("------------------------------------------------------------------");
		
		m.addValue(85);
		System.out.println("Hinzufuegen eines neuen Wertes. m:");
		// Erwartete Ausgabe:
		// [85]
		m.printData();
		
		System.out.println("------------------------------------------------------------------");
		
		m.addValues(new int[] { 58, 78, 61, 72, 93, 81, 79, 78, 75, 81, 93 });
		System.out.println("Hinzufuegen einer Menge von Werten. m:");
		// Erwartete Ausgabe:
		// [85, 58, 78, 61, 72, 93, 81, 79, 78, 75, 81, 93]
		m.printData();
		
		System.out.println("------------------------------------------------------------------");
		
		int minimumValue = m.getMinimumValue();
		System.out.println("Minimaler Wert der Messreihe m:");
		// Erwartete Ausgabe: 58
		System.out.println(minimumValue);
		
		System.out.println("------------------------------------------------------------------");
		
		int[] valuesAbove78 = m.getValuesAboveThreshold(78);
		System.out.println("Messwerte ueber 78:");
		// Erwartete Ausgabe:
		// [85, 93, 81, 79, 81, 93]
		System.out.println(Arrays.toString(valuesAbove78));
		
		System.out.println("------------------------------------------------------------------");
		
		int[] valuesAbove93 = m.getValuesAboveThreshold(93);
		System.out.println("Messwerte ueber 93:");
		// Erwartete Ausgabe:
		// []
		System.out.println(Arrays.toString(valuesAbove93));
	}

}