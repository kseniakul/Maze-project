import java.util.ArrayList;

public class Maze {

	ArrayList<Room> rooms;
	int sizeX, sizeY;
	char[][] mazeArray;
	final int roomSize = 7;
	Room treasureRoom;
	
	
	
	public Maze(int sizeX, int sizeY) {
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		rooms = new ArrayList<>();
		// create canvas for drawing the maze
		mazeArray = new char[sizeY*roomSize][sizeX*roomSize];		
		
		// create all rooms
		for (int x = 0; x < sizeX; x++) {
			for (int y = 0; y < sizeY; y++) {
				// create new room
				Room room = new Room(x, y, roomSize);			
								
				if ((x == 0 || x ==1 || x == 2) && (y == 0)) {
					room.setBot(Room.WALL.DOOR);
					room.setRight(Room.WALL.DOOR);
				}
				
				// add empty rooms
				if ((x == 3 && y == 3) || (x==2 && y== 3) || (x==0 && y==3) || (x==3 && y==0) || (x==0 && y ==1) || (x==0 && y ==2) || (x==1 && y==1)) {
					room.setEmpty(true);					
				}				
				
				if (x ==3 && y == 1) { 
					room.setBot(Room.WALL.DOOR);
					room.setLeft(Room.WALL.DOOR);
					room.setRight(Room.WALL.DOOR);
				}
				// add treasure at [3][2]
				if (x == 3 && y == 2) {					
					room.setTreasure(true);					
					treasureRoom = room;
				}
				
				// remove left walls for rooms 1,0; 2,0
				if(y == 0 && (x==1 || x==2)) {
					room.setLeft(Room.WALL.DOOR);
				}
				
				// remove right wall from 2, 1
				if (x==2 && y==1) {
					room.setRight(Room.WALL.DOOR);
				}
				
				// remove top wall
				if ((x == 2 && y == 1) || (x == 3 && y == 2)) {
					room.setTop(Room.WALL.DOOR);
				}
				
				// add room to rooms list
				rooms.add(room);
			}
		}
	}
	
	/**
	 * Draws the entire maze
	 */
	public void draw() {
		// call every room to draw itself
		for (Room r : rooms) {
			r.draw(mazeArray);
		}		
		
		// print all array
		for (int i = 0; i < sizeY*roomSize; i ++) {			
			for (int j =0 ; j < sizeX*roomSize; j++) {
				// draw all cells except duplicate walls
				//if ((i%roomSize != 0 || i == 0) || (j%roomSize != 0 && j==0))				
					System.out.print(mazeArray[i][j]);
			}
			// print new line - skip duplicated room walls
			//if (i%roomSize != 0 || i == 0)
				System.out.println();
		}
	}
	
	private Room getRoom(int x, int y) {
		for (Room r : rooms) {
			if (r.getX() == x && r.getY() == y)
				return r;
		}		
		return null;
	}
	
	public void makeMove(Player p, Player.DIRECTION move) {
		Room r = getRoom(p.getX(), p.getY());				
		switch (move) {
		case RIGHT:
			if (r.getRight() == Room.WALL.DOOR) {
				System.out.println("--[" + p.getX() + "][" + p.getY() + "] -> [" + (p.getX() + 1) + "][" + p.getY() + "] | Points: " + p.getPoints());
				p.setX(p.getX() + 1);				
			} else {
				System.out.println("--[" + p.getX() + "][" + p.getY() + "] -> WALL | Points: " + p.getPoints());
				p.setAlive(false);
			}			
			break;
		case LEFT:
			if (r.getLeft() == Room.WALL.DOOR) {
				System.out.println("--[" + p.getX() + "][" + p.getY() + "] -> [" + (p.getX() - 1) + "][" + p.getY() + "] | Points: " + p.getPoints());
				p.setX(p.getX() - 1);
			} else {
				System.out.println("--[" + p.getX() + "][" + p.getY() + "] -> WALL | Points: " + p.getPoints());
				p.setAlive(false);
			}
			break;
		case UP:
			if (r.getTop() == Room.WALL.DOOR) {
				System.out.println("--[" + p.getX() + "][" + p.getY() + "] -> [" + (p.getX()) + "][" + (p.getY()-1) + "] | Points: " + p.getPoints());
				p.setY(p.getY() - 1);
			} else {
				System.out.println("--[" + p.getX() + "][" + p.getY() + "] -> WALL | Points: " + p.getPoints());
				p.setAlive(false);
			}
			break;
		case DOWN:
			if (r.getBot() == Room.WALL.DOOR) {
				System.out.println("--[" + p.getX() + "][" + p.getY() + "] -> [" + (p.getX()) + "][" + (p.getY() +1) + "] | Points: " + p.getPoints());
				p.setY(p.getY() + 1);
			} else {
				System.out.println("--[" + p.getX() + "][" + p.getY() + "] -> WALL | Points: " + p.getPoints());
				p.setAlive(false);
			}
			break;
		case STAY:
			break;
		case PEEK_RIGHT: 
			System.out.println((r.getRight() == Room.WALL.DOOR ? "DOOR" : "WALL") + " is on the RIGHT");
			break;
		case PEEK_LEFT: 
			System.out.println((r.getLeft() == Room.WALL.DOOR ? "DOOR" : "WALL") + " is on the LEFT");
			break;
		case PEEK_UP: 
			System.out.println((r.getTop() == Room.WALL.DOOR ? "DOOR" : "WALL") + " is on TOP");
			break;
		case PEEK_DOWN: 
			System.out.println((r.getBot() == Room.WALL.DOOR ? "DOOR" : "WALL") + " is on BOTTOM");
			break;	
		case TREASURE_DISTANCE:
			int distance = Math.abs(treasureRoom.getX() - p.getX()) + Math.abs(treasureRoom.getY() - p.getY());
			System.out.println("Distance to treasure is: " + distance);
			break;
		default:
			break;
		}		
		
		// reduce 1 point for moving
		p.setPoints(p.getPoints()-1);
	}
	
	public boolean isTreasure(int x, int y) {
		Room r = getRoom(x, y);
		return r.isTreasure();
	}
	
	
}
