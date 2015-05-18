import org.lwjgl.input.Keyboard;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
import org.newdawn.slick.Input;
import java.awt.RenderingHints.Key;
import java.awt.event.KeyEvent;
import java.util.Random;

public class Play extends BasicGameState{

	Image playingfield;
	Image scorefigure;
	
	Player player;
	
	Random random;
	private GameBoard gameboard = new GameBoard();
	
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
		g.drawString("Batmans X: " + player.getPlayerX(), 40, 40); //Batmans koordinater för collision detection
		g.drawString("Right Hand: " + player.getRightHand(), 80, 50);
		g.drawString("Left hand: " + player.getLeftHand(), 120, 60);
		gameboard.stopMoving();
		
	}
	
	private int timeSinceMove = 0;
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException{
		timeSinceMove += delta;
		
		if(gameboard.hasMovingObjects() != true) 
			gameboard.spawnObjects();
			
		
		if(timeSinceMove >= 1000) {
			timeSinceMove = 0;
			gameboard.moveObjects();
		}
		//Uppdaterar spelarens koordinater
		if  (player.movePlayer(gc)) {
			gameboard.swapColumns(player.getLeftHand(), player.getRightHand());
		}
	}
	
	public int getID(){
		return 1;
	}
	
}
