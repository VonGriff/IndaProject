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
	
	float playerX = 65;
	float playerY = 540;
	
	Random random;
	private GameObject previous = null;
	private GameObject node;
	private GameObject col1;
	private GameObject col2;
	private GameObject col3;
	private GameObject col4;
	private GameBoard gameboard = new GameBoard();
	
	public Play(int state){
		
	}
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException{
		Image image3 = new Image("pics/suits-diamond.png");
		player = new Image("pics/batman_1.0.png");
		GameObject dummy3 = new GameObject(image3, BlockType.DIAMOND);
		gameboard.Insert(1, 0, dummy3);
	}
	
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException{
		gameboard.draw(g);
		player.draw(playerX, playerY);
		g.drawString("Batmans X: " + playerX, 40, 40);
	}
	
	private int timeSinceMove = 0;
	
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException{
		timeSinceMove += delta;
		Input input = gc.getInput();
		
		if(timeSinceMove >= 1000) {
			timeSinceMove = 0;
			gameboard.moveObjects();
		}
		
		if (input.isKeyPressed(Input.KEY_RIGHT)) {
			playerX += delta * 2.5f;
			if (playerX > 240) {
				playerX -= delta * 2.5f;
			}
		}
		
		if (input.isKeyPressed(Input.KEY_LEFT)) {
			playerX -= delta * 2.5f;
			if (playerX < 65) {
				playerX += delta * 2.5f;
			}
		}
	}
	
	
	
	public int getID(){
		return 1;
	}
	

}
