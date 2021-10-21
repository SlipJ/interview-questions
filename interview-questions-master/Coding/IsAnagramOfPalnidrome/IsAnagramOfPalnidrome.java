import java.util.HashSet;

public class IsAnagramOfPalnidrome {

	// This solves the question https://docs.google.com/presentation/d/1Zk7kBTA30LfVoPujaxHqrh8AoM2dFhBmrGbcBNMraM8/edit?usp=sharing
	
	public static void main(String[] args) {
		System.out.println(IsAnagramOfPalnidrome("AANN")); // return true
		System.out.println(IsAnagramOfPalnidrome("ANAN")); // return true
		System.out.println(IsAnagramOfPalnidrome("ABC")); // return false
		System.out.println(IsAnagramOfPalnidrome("AAANNN")); // return false
		
		System.out.println(IsAnagramOfPalnidromeSpaceEfficent("AANN")); // return true
		System.out.println(IsAnagramOfPalnidromeSpaceEfficent("ANAN")); // return true
		System.out.println(IsAnagramOfPalnidromeSpaceEfficent("ABC")); // return false
		System.out.println(IsAnagramOfPalnidromeSpaceEfficent("AAANNN")); // return false

	}
	
	public static boolean IsAnagramOfPalnidrome(String input) {
		HashSet<Character> set = new HashSet<Character>();
		for(char c : input.toCharArray()) {
			if(set.contains(c)) {
				set.remove(c);
			} else {
				set.add(c);
			}
		}
		
		return set.size()<=1;
	}

	public static boolean IsAnagramOfPalnidromeSpaceEfficent(String input) {
		// assuming all characters are only 'a' to 'z' lower case; uses O(1) space by using bit manipulation
		
		input = input.toLowerCase(); // can remove if input is lower case already
		
		int counter = 0;
		
		for(char c : input.toCharArray()) {
			counter ^= 1 << (c-'a');
		}
		
		int total = 0;
		while (counter > 0) {
			total += counter & 1; 
			counter >>= 1;
		}
		
		return total <= 1;
	}
}
