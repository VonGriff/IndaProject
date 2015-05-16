import org.lwjgl.input.Keyboard;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
import org.newdawn.slick.Input;
import java.awt.RenderingHints.Key;
import java.awt.event.KeyEvent;
import java.util.Random;

public class Play extends BasicGameState{

	Image playingfield;
	Image player;
	Image playerFront;
	Image playerBack;
	boolean faceBack;
	
	
	float playerX = 65;
	//float playerY = 540;
	
	Random random;
	private GameBoard gameboard = new GameBoard();
	
	public Play(int state){
		
	}
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException{

		playerFront = new Image("pics/batman_1.0.png");
		playerBack = new Image("pics/batman_1.0_back.png");
		player = playerFront;
		
	}
	
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException{
		gameboard.draw(g);
		player.draw(playerX, 540);
		g.drawString("Batmans X: " + playerX, 40, 40); //Batmans koordinater för collision detection
		
		}
	
	private int timeSinceMove = 0;
	
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException{
		timeSinceMove += delta;
		Input input = gc.getInput();
		
		float moveDistance = 80;
		
		if(timeSinceMove >= 1000) {
			timeSinceMove = 0;
			gameboard.moveObjects();
		}
		
		//Flyttar batman till höger
		if (input.isKeyPressed(Input.KEY_RIGHT)) {
			playerX += moveDistance;
			if (playerX > 245) {
				playerX -= moveDistance;
			}
		}
		
		//Flyttar batman till vänster
		if (input.isKeyPressed(Input.KEY_LEFT)) {
			playerX -= moveDistance;
			if (playerX < 65) {
				playerX += moveDistance;
			}
		}
		
		//Vid swap får batman att vända sig 180 grader
		if (input.isKeyPressed(Input.KEY_SPACE)) {
			if (faceBack) {
				player = playerFront;
				faceBack = false;
			} else {
				player = playerBack;
				faceBack = true;
			}
		}
	}
	
	public int getID(){
		return 1;
	}
	

}
