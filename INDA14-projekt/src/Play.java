import org.lwjgl.input.Keyboard;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
import org.newdawn.slick.Input;

import java.awt.RenderingHints.Key;
import java.awt.event.KeyEvent;
import java.util.Random;
import java.util.Timer;;
public class Play extends BasicGameState{

	Image playingfield;
	Image scorefigure;
	Timer timer = new Timer();
	
	int speed = 750;
	int levelScore = 100;
	
	Player player;
	
	Random random;
	private static GameBoard gameboard = new GameBoard();
	
	public Play(int state){
		
	}
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException{
		player = new Player();
		scorefigure = new Image("pics/harley_score.png");
		
	}
	
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException{
		gameboard.draw(g);
		player.draw(gc, g);
		scorefigure.draw(400, 250);
		
		g.drawString("Score: " + gameboard.getScore(), 500, 250);
		g.drawString("Batmans X: " + player.getPlayerX(), 40, 40); //Batmans koordinater för collision detection
		g.drawString("Right Hand: " + player.getRightHand(), 80, 50);
		g.drawString("Left hand: " + player.getLeftHand(), 120, 60);
		gameboard.stopMoving();
		
	}
	private int timeSinceMove = 0;
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException{
		timeSinceMove += delta;

		if (gameboard.getRetry()) {
			speed = 750;
		}
		
		gameboard.clear();

		speed = incLevel();
		
		
		if(gameboard.hasMovingObjects() != true){
			gameboard.spawnObjects();
		}

		if(timeSinceMove >= speed) {
			timeSinceMove = 0;
			gameboard.moveObjects();
		}
		//Uppdaterar spelarens koordinater
		if  (player.movePlayer(gc)) {
			gameboard.swapColumns(player.getLeftHand(), player.getRightHand());
		}
		
		//Kollar om det spelet är slut
		if (gameboard.areRowsFull()) {
			sbg.enterState(3);
		}
	}
	
	public int getID(){
		return 1;
	}
	
	public static GameBoard getBoard()  {
		return gameboard;
	}
	
	public int incLevel() {
		int score = gameboard.getScore();
		if (score >= levelScore && !(speed < 0)) {
			levelScore += 100;
			return speed -= 100;
		}
		return speed;
	}
}
