import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.stream.Stream;

public class HomeExercise {

	public static void main(String[] args) {
		Cache cache = new Cache(70);
		String a = cache.getFileContent("0BytesFile.txt"); // Expected: read from file, order of queue: 0
		String b = cache.getFileContent("10BytesFile.txt");// Expected: read from file, order of queue: 0, 10
		String c = cache.getFileContent("20BytesFile.txt"); // Expected: read from file, order of queue: 0, 10, 20
		String d = cache.getFileContent("10BytesFile.txt"); // Expected: read from cache, order of queue: 0, 20, 10
		String e = cache.getFileContent("50BytesFile.txt"); // Expected: read from file, order of queue: 10, 50
		String f = cache.getFileContent("100BytesFile.txt"); // Expected: read from file but not save to cache order of queue: 10, 50
		String g = cache.getFileContent("NonExistingFile.txt"); // Expected: cache unchanged, exception is printed
		String h = cache.getFileContent("0BytesFile.txt"); // Expected: read from file, order of queue: 10,50, 0
		String i = cache.getFileContent("20BytesFile.txt");// Expected: read from file, order of queue: 50, 0, 20
	}
}

class Cache implements CacheInterface {
	
	int cacheSize; // Size in Bytes
	int currentContentSize;
	HashMap<String, String> cacheContent;
	LinkedList<String> usageOrder;
	
	public Cache(int cacheSize) {
		this.cacheSize = cacheSize; 
		this.cacheContent = new HashMap<String, String>();
		this.currentContentSize = 0;
		this.usageOrder = new LinkedList<String>();
	}
	
	public String getFileContent(String path) {
		if(cacheContent.containsKey(path)) {
			synchronized (usageOrder) {
				usageOrder.remove(path); // This line makes the method run at O(n) complexity.
									     // It can be resolved by implementing a double ended linked list and 
										 // storing the references in the cacheContent map values.
				usageOrder.add(path);
			}
			return cacheContent.get(path);
		}
		
		String fileContent = readFile(path); 
		if(fileContent!=null && fileContent.length()<cacheSize) {
			while(currentContentSize+fileContent.length() > cacheSize) {
				String pathToRemove = usageOrder.poll();
				currentContentSize -= cacheContent.get(pathToRemove).length();
				cacheContent.remove(pathToRemove);
			}

			// add file to cache
			usageOrder.add(path);
			currentContentSize += fileContent.length();
			cacheContent.put(path, fileContent);
		}
		return fileContent;
	}
	
	private String readFile(String path) {
		String result = null;
		try {
			result = Files.readString(Path.of(path)); // Java 11 and above
			
			/* If using Java 8
			 * 
			 * StringBuilder content = new StringBuilder();
			 * Files.lines(Paths.get(path)).forEach(s-> content.append(s).append("\n"));
			 * result = content.toString();
			 * 
			 */
			
		}catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
}