import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Ex6 {
	private int availableSpot;
	private int carToMove;
	private int[] initial;
	private int[] desired;
	private ArrayList<Move> moves;
	
	public Ex6(int[] initial, int[] desired) {
		this.initial = Arrays.copyOf(initial, initial.length);
		this.desired = Arrays.copyOf(desired, desired.length);
		moves = moveCars();
	}
	
	/**
	 * Print all the moves used in the program by order of occurrence
	 * @param moves List of moves
	 */
	public void printMoves() {
		if (this.moves.size() == 0) {
			System.out.println("no need to move.");
			return;
		}
		
		this.moves.forEach(System.out::println);
	}
	
	/**
	 * Move cars in the parking lot from initial state to desired state
	 * @return A list of Move objects representing the moves done
	 */
	public ArrayList<Move> moveCars() {
		//a map between cars and their current position
		HashMap<Integer, Integer> parkingLot = new HashMap<>();
		
		//list of moves to be returned
		ArrayList<Move> moves = new ArrayList<Move>();
		
		//initialize map with cars which are not already in place
		for (int i = 0; i < initial.length; i++) {
			if (initial[i] == 0 || (initial[i] != 0 && initial[i] != desired[i])) {
				parkingLot.put(initial[i], i);
			}
		}
		
		//if only one available space left, arrays were identical
		if (parkingLot.keySet().size() == 1) {
			return moves;
		}
		
		this.availableSpot = parkingLot.get(0);
		this.carToMove = desired[availableSpot];
		
		//handle a case where "0" is in it's place
		//just swap the first available car with it
		if(this.carToMove == 0) { 
			doIfZero(parkingLot);
		}
		
		//Move all cars to their place, until only available space is left
		while(parkingLot.size() > 1) {
			moves.add(moveCarToEmptySpot(parkingLot, true));
			
			//handle a situation where we have multiple "blocks" of cars
			//which don't depend on each other
			if(this.carToMove == 0) { 
				doIfZero(parkingLot);
			}
		}
		
		return moves;		
	}
	
	/**
	 * Moves a car to the next available space
	 * If it's a "parking" move, delete it from the table,
	 * Else update its new position
	 * @param parkingLot the mapping between cars and where they're parked
	 * @param isParking boolean flag to tell if it's an actual move or a temporary
	 * @return The move that was done
	 */
	private Move moveCarToEmptySpot(HashMap<Integer, Integer> parkingLot, boolean isParking) {
		Move move = new Move(parkingLot.get(carToMove), this.availableSpot);
		
		//update where available space is
		parkingLot.put(0, parkingLot.get(this.carToMove));
		if (isParking) {
			//on actual parking, remove car from parking lot
			parkingLot.remove(carToMove);
		} else {
			//on temporary parking, update car's lot number
			parkingLot.put(carToMove, this.availableSpot);
		}
		
		this.availableSpot = parkingLot.get(0);
		this.carToMove = desired[this.availableSpot];
		
		return move;
	}
	
	/**
	 * Handle a case where available parking space ("0") should be where it is currently.
	 * @param parkingLot the mapping between cars and where they're parked
	 * @param moves the moves list to be updated with a new move
	 */
	private void doIfZero(HashMap<Integer, Integer> parkingLot) {
		for (Integer car : parkingLot.keySet()) {
			if (car != 0) {
				this.carToMove = car;
				this.moves.add(moveCarToEmptySpot(parkingLot, false));
				break;
			}
		}
	}
}
