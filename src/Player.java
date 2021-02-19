import java.util.Random;

/**
 * Basic Player class used for both Bot and Human Player
 * @author KseniaKul
 *
 */
public class Player {

	enum DIRECTION {LEFT, RIGHT, UP, DOWN, STAY, PEEK_UP, PEEK_DOWN, PEEK_RIGHT, PEEK_LEFT, TREASURE_DISTANCE};
	
	private int x, y;
	protected String name;
	private int points;	
	private boolean isAlive;
	
	/**
	 * Constructor
	 * @param name player name
	 */
	public Player(String name) {
		this.name = name;
		this.points = 0;
		this.isAlive = true;
		this.x = 0;
		this.y = 0;
	}
	
	/**
	 * Default constructor
	 */
	public Player() {
		super();
	}
	
	public boolean isAlive() {
		return isAlive;
	}

	public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}

	public String getName() {
		return name;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	/**
	 * Set both X and Y for Player
	 * @param x X
	 * @param y Y
	 */
	public void setXY(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Prints out player location and Points
	 */
	public void print() {
		System.out.println(name + "location: [" + x + "][" + y + "], points:" + points);
	}
	 
	/**
	 * Given user key input - returns corresponding move
	 * @param move input number
	 * @return DIRECTION of move
	 */
	protected DIRECTION intToMove(int move) {
		switch (move) {
		case 0:
			System.out.println(name + " chose to move DOWN");
			return DIRECTION.DOWN;
		case 1:
			System.out.println(name + " chose to move UP");
			return DIRECTION.UP;
		case 2:
			System.out.println(name + " chose to move RIGHT");
			return DIRECTION.RIGHT;
		case 3:
			System.out.println(name + " chose to move LEFT");
			return DIRECTION.LEFT;
		case 4:
			System.out.println(name + " chose to stay in place");
			return DIRECTION.STAY;
		case 5:
			System.out.println(name + " chose to peek UP");
			return DIRECTION.PEEK_UP;
		case 6:
			System.out.println(name + " chose to peek down");
			return DIRECTION.PEEK_DOWN;
		case 7:
			System.out.println(name + " chose to peek LEFT");
			return DIRECTION.PEEK_LEFT;
		case 8:
			System.out.println(name + " chose to peek RIGHT");
			return DIRECTION.PEEK_RIGHT;
		case 9:
			System.out.println(name + " checks air distance to treasure");
			return DIRECTION.TREASURE_DISTANCE;
		default:
			return DIRECTION.STAY;
		}
	}
	
	/**
	 * BOT movement returning nextMove of any random direction
	 * @return next move direction
	 */
	public DIRECTION getNextMove() {
		Random rand = new Random();		
		int randomNum = rand.nextInt((3 - 0));
		// return side according to random number
		return intToMove(randomNum);		
		
	}
	
}

