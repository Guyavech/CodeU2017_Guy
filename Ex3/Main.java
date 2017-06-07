
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
		
		
		Ex3 ex3 = new Ex3(matrix.length, matrix[0].length, matrix, new Dictionary(words, prefix));
		Ex3 ex3_2 = new Ex3(catrix.length, catrix[0].length, catrix, new Dictionary(words2, prefix2));
		
		//expected: [car, card, cat]
		System.out.println(ex3.getWordsInMatrix().toString());
		
		//expected: [c, cat, cc, fish]
		System.out.println(ex3_2.getWordsInMatrix().toString());
	}

}
