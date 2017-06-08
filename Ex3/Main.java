
public class Main {

	public static void main(String[] args) {
		String[] words = {"car", "card", "cart", "cat"};
		String[] prefix = {"c", "ca", "car", "card", "cart", "cat"};
		
		String[] words2 = {"c", "cc", "cat","catd", "fish"};
		String[] prefix2 = {"c", "ca","cat", "caisc", "catf","f", "fi","fis"};
		
		char[][] matrix = {{'a','a','r'},
				   		   {'t','c','d'}};
		
		char[][] catrix =  {{'t','r','c','a'},
				 		    {'h','s','i','t'},
						    {'i','c','c','f'}};
		
		char[][] test = {{'a','b'},
						 {'c','d'}};
		
		String[] testSamePathWords = {"abc","acb","aca"};
		String[] testSamePathPrefix = {"a","ab","abc","ac","acb"};
		
		
		Ex3 ex3 = new Ex3(matrix.length, matrix[0].length, matrix, new Dictionary(words, prefix));
		Ex3 ex3_2 = new Ex3(catrix.length, catrix[0].length, catrix, new Dictionary(words2, prefix2));
		Ex3 ex3_3 = new Ex3(test.length, test[0].length, test, new Dictionary(testSamePathWords, testSamePathPrefix));
		
		//expected: [car, card, cat]
		System.out.println(ex3.getWordsInMatrix().toString());
		
		//expected: [c, cat, cc, fish]
		System.out.println(ex3_2.getWordsInMatrix().toString());
		
		//expected: [abc, acb]
		System.out.println(ex3_3.getWordsInMatrix().toString());
	}
}
