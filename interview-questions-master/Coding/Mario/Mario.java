
public class Mario {

	// This solved Mario's game: https://docs.google.com/presentation/d/1ltxnAH79jkR7--hIpe3QAtaH5P31925WfMKQPMU0wdU/edit?usp=sharing
	
	public static void main(String[] args) {
		System.out.println(isPossibleToWin(new int[] {0,0,1,0,0}, 3)); // Should return true
		System.out.println(isPossibleToWin(new int[] {0,0,1,1,1}, 3)); // Should return false
		System.out.println(isPossibleToWin(new int[] {0,0,1,0,0,1,1,0,1}, 4)); // Should return true
		System.out.println(isPossibleToWin(new int[] {0,0,0,1,0,1,0,0,1}, 4)); // Should return true
		System.out.println(isPossibleToWin(new int[] {1,0,1,0,0}, 3)); // Should return false
	}
	
	public static boolean isPossibleToWin(int[] level, int m) {
		return isPossibleToWin(level, m, 0);
	}

	public static boolean isPossibleToWin(int[] level, int m, int index) {
		if(index>=level.length) {
			return true;
		} else if(index<0 || level[index]==1) {
			return false;
		}
		
		level[index]=1;
		
		return  isPossibleToWin(level, m, index+1) ||
				isPossibleToWin(level, m, index-1) ||
				isPossibleToWin(level, m, index+m);
	}
	
}
