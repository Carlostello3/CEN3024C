/**
 * Carlos Tello
 * Word Analyzer
 * CEN-3024C
 * Soft Development 1
 */


import java.io.IOException;
import java.net.URL;
import java.util.*;

public class textAnalyzer {

	public static void main(String[] args) throws IOException {
		
		URL url = new URL("http://shakespeare.mit.edu/macbeth/full.html");
		
		String wordHold = null;
		
		int wordCount = 1;
		
		HashMap <String, Integer> wordList = new HashMap <String, Integer>();
		
		Scanner sc = new Scanner(url.openStream());
		
		
		//Iterate via scanner and removed undesired characters
		while (sc.hasNext()) {
			wordCount = 1;
			wordHold = sc.next().replaceAll("[0-9, , ,\\s+ ,<$>, /*, . , =, ;, :, !, ', NAME]", "");
			
			
			if (wordList.isEmpty()) {
				wordList.put(wordHold, wordCount);
			} else {
				if (wordList.containsKey(wordHold)) {
					wordCount = wordList.get(wordHold);
					wordCount++;
					wordList.put(wordHold, wordCount);
				} else {
					wordList.put(wordHold, wordCount);
				}
			}
			
			
		}//end of while
		
		//Call method to sort HashMap	
		Map<String, Integer> wordSorted = sortByValue(wordList);
		wordSorted.remove("");
		
		int counter = 0;
		
		//Print map with sorted words
		System.out.println("WORD ANALYSIS (Top 20 words) - URL: " + url);
		System.out.println("-----------------------------------------------------------------------------------");
		for (Map.Entry<String, Integer> lastWordBank : wordSorted.entrySet()) {
			if(counter < 20) {
				System.out.println("Word: " + lastWordBank.getKey() + " - Times: " + lastWordBank.getValue());
				counter++;
				}
			
			}
		
		}//end of main

	
	
	
	
	
	
	
	public static HashMap<String, Integer> sortByValue (HashMap<String, Integer> wordList){
		
		//Create new List to hold data
		List<Map.Entry<String, Integer> > List = new LinkedList<Map.Entry<String, Integer>>(wordList.entrySet());
		
		
		//Sort Data
		Collections.sort(List, new Comparator<Map.Entry<String, Integer>> (){
			public int compare (Map.Entry<String, Integer> obj1,
					Map.Entry<String, Integer> obj2)
			{
				return(obj2.getValue()).compareTo(obj1.getValue());
			}
			
		});
		
		//Create new HashMap to hold sorted data
		HashMap<String, Integer> temp = new LinkedHashMap<String, Integer>();
		for (Map.Entry<String, Integer> aa: List) {
			temp.put(aa.getKey(),aa.getValue());
		}
		
		//Return map
		return temp;
		
	}

}
