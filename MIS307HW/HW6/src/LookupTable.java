import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

/**
 * Code for HW6
 * @author Preston Camerer
 */
/**
 * A table for lookups and reverse lookups
 */
public class LookupTable {
	private ArrayList<Item> byKey;
	private ArrayList<Item> byValue;

	/**
	 * Constructs a LookupTable object.
	 */
	public LookupTable() {
		byKey = new ArrayList<Item>();
		byValue = new ArrayList<Item>();
	}

	/**
	 * Reads key/value pairs.
	 * 
	 * @param in
	 *            the scanner for reading the input
	 */
	public void read(Scanner in) {
		int i = 0;
		Item item;
		String k = null, v = null;
		while (in.hasNextLine()) {
			if (i % 2 == 0)
				k = in.nextLine();
			else {
				v = in.nextLine();
				item = new Item(k, v);
				byKey.add(item);
				item = new Item(v, k);
			}
			i++;
		}
		Collections.sort(byKey, new Comparator<Item>() {
			public int compare(Item o1, Item o2) {
				return o1.getKey().compareTo(o2.getKey());
			}
		});
		Collections.sort(byValue, new Comparator<Item>() {
			public int compare(Item o1, Item o2) {
				return o1.getKey().compareTo(o2.getKey());
			}
		});
	}

	/**
	 * Looks up an item in the table.
	 * 
	 * @param k
	 *            the key to find
	 * @return the value with the given key, or null if no such item was found.
	 */
	public String lookup(String k) {
		Comparator<Item> c = new Comparator<Item>() {
			public int compare(Item u1, Item u2) {
				return u1.compareTo(u2);
			}
		};
		int index = Collections.binarySearch(byKey, new Item(k, null), c);
		if (index < 0)
			return null;
		Item item = byKey.get(index);
		return item.getValue();
	}

	/**
	 * Looks up an item in the table.
	 * 
	 * @param v
	 *            the value to find
	 * @return the key with the given value, or null if no such item was found.
	 */
	public String reverseLookup(String v) {
		Comparator<Item> c = new Comparator<Item>() {
			public int compare(Item u1, Item u2) {
				return u1.compareTo(u2);
			}
		};
		int index = Collections.binarySearch(byValue, new Item(v, null), c);
		if (index < 0)
			return null;
		Item item = byValue.get(index);
		return item.getValue();
	}
}
