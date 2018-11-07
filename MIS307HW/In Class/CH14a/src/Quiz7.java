import java.util.*;

public class Quiz7 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<Integer> nums = new ArrayList<Integer>();
		nums.add(100); nums.add(5); nums.add(31); nums.add(24); nums.add(71);
		nums.add(133); nums.add(97); nums.add(65); nums.add(15); nums.add(37);
		nums.add(49); nums.add(52); nums.add(73); nums.add(7); nums.add(3);
		nums.add(1); nums.add(91); nums.add(86); nums.add(21); nums.add(58);
		nums.add(66); nums.add(121); nums.add(36); nums.add(74); nums.add(112);
		nums.add(123); nums.add(12); nums.add(47); nums.add(76); nums.add(83);
		
		
		
		Collections.sort(nums);
		nums.get(11);
		
		Collections.binarySearch(nums, 65);
		
		System.out.println(nums.get(11));
		System.out.println(Collections.binarySearch(nums, 65));
		
		
	}

}
