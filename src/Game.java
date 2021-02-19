import java.util.ArrayList;

public class Game {
	
	private ArrayList<Player> players;
	private Maze maze;
	private int rounds;
	
	public Game(int numPlayers, int numRounds) {
		// initialize players list 
		players = new ArrayList<Player>();
		// create 1 human player
		HumanPlayer hp = new HumanPlayer("HUMAN");
		players.add(hp);
		
		// create all players
		for (int i=0; i < numPlayers; i++) {
			Player p = new Player("BOT_" + i);			
			players.add(p);
		}
		
		maze = new Maze(4, 4);		
		this.rounds = numRounds;
	}
	
	public void printPlayers() {		
		// print all players
		for (int i=0; i < players.size(); i++) {
			Player p = players.get(i);
			p.print();
		}
	}
	
	public void startGame() {		
		int currentRound = 0;
		maze.draw();
		
		
		// until all rounds completed
		while (currentRound < rounds) {
			System.out.println("-------- ROUND #" + currentRound + " ----------");
			// set players to alive
			for (Player p : players) {
				p.setXY(0, 0);
				p.setAlive(true);
			}
			
			// set current player to 0
			int currentPlayer = 0;
			// update alive player num
			int numPlayersAlive = players.size();
						
			// do round logic
			while (true) {				
				Player p = players.get(currentPlayer);
				// print current player
				System.out.println("-----------------------------");

				// ask next move
				Player.DIRECTION move = p.getNextMove();

				// check next move legal
				maze.makeMove(p, move);
				if (!p.isAlive()) {
					System.out.println("--" + p.getName() + " DIED!!!");
					numPlayersAlive--;
				}

				// print maze
				// maze.draw();

				// print players
				//printPlayers();

				// check all players dead and gameOver
				if (numPlayersAlive == 0) {
					System.out.println("ALL PLAYERS DEAD!!!");
					break;
				}

				// check if player reached treasure
				if (maze.isTreasure(p.getX(), p.getY())) {
					System.out.println(p.getName() + " have reached the TREASURE and WON!");
					p.setPoints(p.getPoints() + 100);
					break;

				}
				
				// get next player
				
				
				do {
					currentPlayer++;
					if (currentPlayer == players.size())
						currentPlayer = 0;
				} while (!players.get(currentPlayer).isAlive()); 
				
			}
			
			currentRound++;
		}
		
		// print winner or game over
		System.out.println("Round Completed!");
	}
	
	
}
