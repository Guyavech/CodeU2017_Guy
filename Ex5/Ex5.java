import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Ex5 {

	public static void main(String[] args) {
		String[] dictionary = {"art","rat","cat","car"};
		String[] dictionary2 = {"a","b","Ab","Ac"};
		String[] dictionary3 = {"$"};
		
		sortUnknownAlphabet(dictionary); // [a, t, r, c]
		sortUnknownAlphabet(dictionary2); // [a, b, A, c]
		sortUnknownAlphabet(dictionary3); // [$]
	}
	
	public static void sortUnknownAlphabet(String[] dictionary) {
		if (dictionary == null || dictionary.length == 0 ) {
			throw new IllegalArgumentException();
		}
		
		Graph<Character> graph = new Graph<>();
		insertNodes(dictionary, graph);
		insertEdges(dictionary, graph);
		
		System.out.println(graph.topologicSort());
	}
	
	private static void insertEdges(String[] dictionary, Graph<Character> graph) {
		for (int i = 0; i < dictionary.length - 1; i++) {
			String first = dictionary[i];
			String second = dictionary[i + 1];
			for (int j = 0; j < (int)Math.min(first.length(), second.length()); j++) {
				if (first.charAt(j) != second.charAt(j)) {
					graph.insertEdge(first.charAt(j), second.charAt(j));
					break;
				}
			}
		}	
	}

	private static void insertNodes(String[] dictionary, Graph<Character> graph) {
		for (int i = 0; i < dictionary.length; i++) {
			String word = dictionary[i];
			for (int j = 0; j < word.length(); j++) {
				graph.insertNode(word.charAt(j));
			}
		}
	}
}