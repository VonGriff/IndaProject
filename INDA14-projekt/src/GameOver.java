import org.newdawn.slick.*;
import org.newdawn.slick.state.*;


public class GameOver extends BasicGameState {
	
	Image joker;

	public GameOver(int state) {
		
	}
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException{
		joker = new Image("pics/joker_face.png");
	}
	
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException{
		joker.draw(300, 100);
		g.drawString("1. Retry", 0, 0);
		g.drawString("2. Main Menu", 0, 20);
		g.drawString("3. Quit", 0, 40);
	}
	
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException{
		
	}
	
	public int getID() {
		return 3;
	}
}
