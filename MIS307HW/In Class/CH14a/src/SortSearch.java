import java.util.*;

/**
 * CH14 Sorting/Searching/HW6
 * @author YWP
 */
public class SortSearch {

	public static void main(String[] args) {
		
		// Example1: sorting an array of random numbers
		System.out.println("[1. Sorting an array]");
		
		int total_numbers = 10;
		Random rnd = new Random(1);
		int[] numbers_array = new int[total_numbers];
		for(int i=0; i<total_numbers; i++){
			numbers_array[i] = rnd.nextInt(100); 
		}
		
		System.out.print("unsorted: ");
		for(int i=0; i<total_numbers; i++){
			System.out.print(numbers_array[i] + " ");
		}
		System.out.println();
		
		Arrays.sort(numbers_array); // uses quick sort algorithm
		
		System.out.print("sorted: ");
		for(int i=0; i<total_numbers; i++){
			System.out.print(numbers_array[i] + " ");
		}
		System.out.println();
		System.out.println();
		
		
		
		// Example2: searching an array
		System.out.println("[2. Searching an array]");
		int index_found = Arrays.binarySearch(numbers_array, 34); // (array name, number to search)
		if(index_found >=0){
			System.out.println("Found at index = " + index_found);
		}
		System.out.println();

		
		// Quesetion1: Sort the following array and use binary search to find "Q" and "I"
		System.out.println("[Question1]");
		String[] letters = {"R", "F","O","Q", "C", "L", "J","G","A"};
		System.out.print("unsorted: ");
		for(int i=0; i<letters.length; i++){
			System.out.print(letters[i] + " ");
		}
		System.out.println();
		
		// place your code for sorting
		
		Arrays.sort(letters);
		
		
		
		
		
		System.out.print("sorted: ");
		for(int i=0; i<letters.length; i++){
			System.out.print(letters[i] + " ");
		}
		System.out.println();
		
		index_found = -1; // place your code for searching
		if(index_found >=0){
			System.out.println("'Q' Found at index = " + index_found);
		}else {
			System.out.println("'Q' Not found");
		}
		
		index_found = Arrays.binarySearch(letters, "I"); // (array name, number to search)
		if(index_found >=0){
			System.out.println("'I' Found at index = " + index_found);
		}else {
			System.out.println("'I' Not found");
		}
		System.out.println();
		
		
		
		
		// Example3: sorting an array list
		ArrayList<Integer> numbers = new ArrayList<Integer>();
		System.out.println("[3. Sorting an array list]");
		
		for(int i=0; i<total_numbers; i++){
			numbers.add(rnd.nextInt(100));
		}
		
		System.out.println("unsorted: " + numbers);
		Collections.sort(numbers); // uses merge sort algorithm
		System.out.println("sorted: " + numbers);
		System.out.println();
		
		// Example4: searching an array list
		System.out.println("[4. Searching an array list]");
		// binarySearch returns the location if it found the number
		// binarySearch returns a negative number if not found
		index_found = Collections.binarySearch(numbers, 69);
		
		if(index_found >= 0){
			System.out.println("Found at index = " + index_found);
		}
		System.out.println(); // (your array list, number to search)
		
		
		
		// Quesetion2: Sort the following array list and use binary search to find "Q" and "I"
		
		ArrayList<String> letterslist = new ArrayList<String>();
		System.out.println("[Question2]");
		letterslist.add("R");	letterslist.add("F");	letterslist.add("O");	
		letterslist.add("Q");	letterslist.add("C");	letterslist.add("L");
		letterslist.add("J");	letterslist.add("G");	letterslist.add("A");
		System.out.println("unsorted: " + letterslist);
		
		// place your code for sorting
		
		Collections.sort(letterslist);
		
		
		System.out.println("sorted: " + letterslist);
		
		index_found = -1; // place your code for searching
		
		if(index_found >= 0){
			System.out.println("'Q' Found at index = " + index_found);
		}else {
			System.out.println("'Q' Not found");
		}
		
		index_found = Collections.binarySearch(letterslist, "I");
		
		if(index_found >= 0){
			System.out.println("'I' Found at index = " + index_found);
		}else {
			System.out.println("'I' Not found");
		}
		System.out.println(); 
		
		
		
		
		// Example5: sorting an array list of Item objects with key = country name
		System.out.println("[5. Sorting an array list of Item objects with key = country name]");
		// You can actually sort and search your own class objects 
		// Consider HW6 class Item.java.
		
		// The first argument is the key (that will be used in sorting the array list)
		// In the following array list, the key is country name.
		ArrayList<Item> pops = new ArrayList<Item>();
		pops.add(new Item("Indonesia","263510146"));
		pops.add(new Item("Mexico","130222815"));
		pops.add(new Item("Bangladesh","164827718"));
		pops.add(new Item("Brazil","211243220"));
		pops.add(new Item("Pakistan","196744376"));
		pops.add(new Item("U.S.","326474013"));
		pops.add(new Item("Nigeria","191835936"));
		pops.add(new Item("Russia","143375006"));
		
		// sort the array list by the key (the first argument, which is country name here)
		Collections.sort(pops);
		
		
		for(int i=0; i<pops.size(); i++){
			// pops.get(i) returns Item object at index i
			// Item class has methods getKey() and getValue() to access Key and Value
			System.out.println(pops.get(i).getKey() + "\t" + pops.get(i).getValue());
		}
		System.out.println();
		
		// search the population given a country
		// Example6: searching an array list of Item objects with key = country name
		System.out.println("[6. Searching an array list of Item objects with key = country name]");
		String target_country ="U.S.";
		index_found = Collections.binarySearch(pops, new Item(target_country, null));
		if(index_found >= 0){ // if we found a country with name "U.S."
			System.out.println("Population = " + pops.get(index_found).getValue());
		}
		System.out.println();
		
		
		
		// Example7: sorting an array list of Item objects with key = population
		// The first argument is the key (that will be used in sorting the array list)
		// In the following array list, the key is population.
		System.out.println("[7. Sorting an array list of Item objects with key = population]");
		ArrayList<Item> pops2 = new ArrayList<Item>();
		pops2.add(new Item("263510146","Indonesia"));
		pops2.add(new Item("130222815","Mexico"));
		pops2.add(new Item("164827718","Bangladesh"));
		pops2.add(new Item("211243220","Brazil"));
		pops2.add(new Item("196744376","Pakistan"));
		pops2.add(new Item("326474013","U.S."));
		pops2.add(new Item("191835936","Nigeria"));
		pops2.add(new Item("143375006","Russia"));
		
		// sort the array list by the key (the first argument, which is population here)
		Collections.sort(pops2);
		for(int i=0; i<pops2.size(); i++){
			System.out.println(pops2.get(i).getValue() + "\t" + pops2.get(i).getKey());
		}
		System.out.println();
		
		// Example8: searching an array list of Item objects with key = population
		// search a country with population = 191835936
		System.out.println("[8. Searching an array list of Item objects with key = population]");
		String target_pop ="191835936";
		index_found = Collections.binarySearch(pops2, new Item(target_pop, null));
		if(index_found >= 0){
			System.out.println("Country name = " + pops2.get(index_found).getValue());
		}
		
		
		// Example9: sorting an array list
		System.out.println("[9. Sort an array list by the length");
		ArrayList<String> countrynames = new ArrayList<String>();
		for(int i=0; i<pops.size(); i++){
			countrynames.add(pops.get(i).getKey());
		}
		System.out.print("unsorted: ");
		System.out.println(countrynames);
		Collections.sort(countrynames,(a,b)->a.length()-b.length());
		System.out.print("sorted: ");
		System.out.println(countrynames);
		System.out.println();
				
		// Question3: sort an array list by the last character
		System.out.println("[Question3. Sort an array list by the last character");
		System.out.print("unsorted: ");
		System.out.println(countrynames);
		
		// place your code for sorting
		
		
		
		
		
		
		System.out.print("sorted: ");
		System.out.println(countrynames);		
		
	}

}
