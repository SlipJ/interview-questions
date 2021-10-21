import java.util.Stack;

public class MarioItterative {

	// This solved Mario's game: https://docs.google.com/presentation/d/1ltxnAH79jkR7--hIpe3QAtaH5P31925WfMKQPMU0wdU/edit?usp=sharing
	
	// The only advantage this solution has over the recursive solution, is that it avoids the possibility of a stack overflow for
	// a very long input. Otherwise it has the same space and runtime complexities
	
	public static void main(String[] args) {
		System.out.println(isPossibleToWin(new int[] {0,0,1,0,0}, 3)); // Should return true
		System.out.println(isPossibleToWin(new int[] {0,0,1,1,1}, 3)); // Should return false
		System.out.println(isPossibleToWin(new int[] {0,0,1,0,0,1,1,0,1}, 4)); // Should return true
		System.out.println(isPossibleToWin(new int[] {0,0,0,1,0,1,0,0,1}, 4)); // Should return true
		System.out.println(isPossibleToWin(new int[] {1,0,1,0,0}, 3)); // Should return false
	}
	
	public static boolean isPossibleToWin(int[] level, int m) {
		Stack<Integer> stack = new Stack<Integer>();
		stack.push(0);
		
		while(!stack.isEmpty()) {
			int currentIndex = stack.pop();
			if(currentIndex >= level.length) {
				return true;
			}
			
			if(currentIndex>= 0 && level[currentIndex]==0) {
				level[currentIndex]=1;
				stack.push(currentIndex+1);
				stack.push(currentIndex-1);
				stack.push(currentIndex+m);
			}
		}
		
		return false;
	}
}
