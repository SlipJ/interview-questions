import java.util.Arrays;

public class Steps {

	// This solves the steps question: https://docs.google.com/presentation/d/1BRtQNgb4d4LlUwnNC17qYrxqqV46ChYurJGw85WADM4/edit?usp=sharing
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int n = 50;
		
		System.out.println(getNumberOfWaysRecursive(n));
		System.out.println(getNumberOfWaysIterative(n));
	}

	private static long getNumberOfWaysRecursive(int n) {
		long[] cache = new long[n+1];
		Arrays.fill(cache, -1);
		cache[0] = 0;
		cache[1] = 1;
		cache[2] = 2;
		return getNumberOfWaysRecursive(n, cache);
	}
	
	private static long getNumberOfWaysRecursive(int n, long[] cache) {
		if(n<0) {
			return 0;
		}
		
		if(cache[n]==-1) {
			cache[n] = getNumberOfWaysRecursive(n-1, cache) + getNumberOfWaysRecursive(n-2, cache);
		}
		return cache[n];
	}
	
	public static long getNumberOfWaysIterative(int n) {
		if(n<=0) {
			return 0;
		} else if(n==1) {
			return 1;
		} else if(n==2) {
			return 2;
		}
		
		long first = 1;
		long second = 2;
		long current = 0;
		
		for(int i=2; i<n; i++) {
			current = first + second;
			first = second;
			second = current;
		}
		
		return current;
	}
	
}
