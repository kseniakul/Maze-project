import java.util.Scanner;

public class HumanPlayer extends Player {
	
	/**
	 * Constructor
	 * @param name Player Name
	 */
	public HumanPlayer (String name) {
		super(name);		
	}
	
	public DIRECTION getNextMove() {
		Scanner scan = new Scanner(System.in);
        System.out.print(name + " enter your next move(0-9): ");
        int num = scan.nextInt();                
		return intToMove(num);
	}
	
}
