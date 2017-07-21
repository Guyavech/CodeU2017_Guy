public class Main {

	public static void main(String[] args) {
		//int[] initial1 = {2,4,3,1,0};
		//int[] desired1 = {0,1,2,3,4};
		//int[] initial2 = {0,2,1,3,4,5};
		//int[] desired2 = {0,1,5,4,3,2};
		int[] initial = {1,2,0,3};
		int[] desired = {3,1,2,0};
		Ex6 ex6 = new Ex6(initial, desired);
		ex6.printMoves();
	}
}
