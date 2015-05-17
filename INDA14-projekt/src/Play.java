import org.lwjgl.input.Keyboard;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
import org.newdawn.slick.Input;
import java.awt.RenderingHints.Key;
import java.awt.event.KeyEvent;
import java.util.Random;

public class Play extends BasicGameState{

	Image playingfield;
	
	Player player;
	float playerX = 65;
	
	Random random;
	private GameBoard gameboard = new GameBoard();
	
	public Play(int state){
		
	}
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException{
		player = new Player();
		player.setPlayerX(playerX);
		
	}
	
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException{
		gameboard.draw(g);
		player.draw(gc, g);
		g.drawString("Batmans X: " + player.getPlayerX(), 40, 40); //Batmans koordinater för collision detection
		
		if(gameboard.hasMovingObjects() != true){
			gameboard.spwanObjects();
		}
		}
	
	private int timeSinceMove = 0;
	
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException{
		timeSinceMove += delta;
		
		if(timeSinceMove >= 1000) {
			timeSinceMove = 0;
			gameboard.moveObjects();
		}
		//Uppdaterar spelarens koordinater
		player.movePlayer(gc);
	}
	
	public int getID(){
		return 1;
	}
	
}
