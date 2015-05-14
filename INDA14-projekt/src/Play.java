import org.lwjgl.input.Keyboard;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
import org.newdawn.slick.Input;
import java.awt.RenderingHints.Key;
import java.awt.event.KeyEvent;
import java.util.Random;

public class Play extends BasicGameState{

	Image playingfield;
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
		GameObject dummy3 = new GameObject(image3, BlockType.DIAMOND);
		gameboard.Insert(0, 1, dummy3);
	}
	
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException{
		gameboard.draw(g);
	}
	
	private int timeSinceMove = 0;
	
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException{
		timeSinceMove += delta;
		
		if(timeSinceMove >= 1000) {
			timeSinceMove = 0;
			gameboard.moveObjects();
		}
	}
	
	
	
	public int getID(){
		return 1;
	}
	

}
