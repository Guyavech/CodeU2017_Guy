import java.util.TreeSet;

public class Dictionary {
	TreeSet<String> words = new TreeSet<>();
	TreeSet<String> prefixes = new TreeSet<>();
	
	public Dictionary(String[] words, String[] prefixes) {
		for (int i = 0; i < words.length; i++) {
			this.words.add(words[i]);
		}
		
		for (int i = 0; i < prefixes.length; i++) {
			this.prefixes.add(prefixes[i]);
		}
	}
	
	public boolean isWord(String s) {
		return (this.words.contains(s));
	}
	
	public boolean isPrefix(String s) {
		return (this.prefixes.contains(s));
	}
}
