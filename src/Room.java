
/**
 * Represents a room in the maze
 * @author Ksenia
 *
 */
public class Room {
	
	enum WALL {DOOR, WALL};
	
	private int x, y;					// room x, y coordinate
	private int roomSize;				// room size for printing
	private boolean empty;				// true if room is empty
	private WALL top, bot, right, left; // WALL/DOOR for every room side
	private boolean treasure;			// true if treasure in the room
	
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

	public WALL getTop() {
		return top;
	}

	public void setTop(WALL top) {
		this.top = top;
	}

	public WALL getBot() {
		return bot;
	}

	public void setBot(WALL bot) {
		this.bot = bot;
	}

	public WALL getRight() {
		return right;
	}

	public void setRight(WALL right) {
		this.right = right;
	}

	public WALL getLeft() {
		return left;
	}

	public void setLeft(WALL left) {
		this.left = left;
	}

	/**
	 * Constructor
	 * @param x	room X
	 * @param y room Y
	 * @param size print size
	 */
	public Room (int x, int y, int size) {
		this.x = x;
		this.y = y;
		roomSize = size;
		
		this.treasure = false;	
		
		top = bot = right = left = WALL.WALL;
		// left = WALL.DOOR;
		
		this.empty = false;				
	}
	
	/**
	 * Draws this room in the big mazeArray
	 * @param mazeArray array representing entire maze
	 */
	public void draw(char[][] mazeArray) {
		
		// fill the room
		for (int i = 0; i < roomSize; i++) {
			for (int j = 0; j < roomSize; j++) {
				mazeArray[roomSize * y + i][roomSize * x + j] = empty ? ' ' : (treasure ? 'T' : '/');
			}
		}

		// draw walls if room is not empty
		if (!empty) {
			
			for (int i = 0; i < roomSize; i++) {
				// draw left wall
				mazeArray[y * roomSize + i][x * roomSize] = left == WALL.WALL ? '*' : '.';
				// draw right wall
				mazeArray[y * roomSize + i][x * roomSize + roomSize - 1] = right == WALL.WALL ? '*' : '.';
				// draw top wall
				mazeArray[y * roomSize][x * roomSize + i] = top == WALL.WALL ? '*' : '.';
				// draw bottom wall
				mazeArray[y * roomSize + roomSize - 1][x * roomSize + i] = bot == WALL.WALL ? '*' : '.';
			}
		}

	}

	public boolean isEmpty() {
		return empty;
	}

	public void setEmpty(boolean empty) {
		this.empty = empty;
	}

	public boolean isTreasure() {
		return treasure;
	}

	public void setTreasure(boolean treasure) {
		this.treasure = treasure;
	}

}
