//import org.lwjgl.input.Keyboard;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;


public class GameOver extends BasicGameState {
	
	Image joker;
	Image bubble;
	GameBoard gb = Play.getBoard();

	public GameOver(int state) {
		
	}
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException{
		joker = new Image("pics/joker_face.png");
		bubble = new Image("pics/bubble_own.png");
	}
	
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException{
		joker.draw(300, 100);
		bubble.draw(500, 50);
		
		g.drawString("Game Over, Bats! \nScore: " + gb.getScore(), 575, 125);
		
		g.drawString("1. Retry", 0, 0);
		g.drawString("2. Main Menu", 0, 20);
		g.drawString("3. Quit", 0, 40);
	}
	
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException{
		Input input = gc.getInput();
		
		//Enter play
		if(input.isKeyPressed(Input.KEY_1)){
			gb.setRetry();
			gb.setRowsEmpty();
			sbg.enterState(1);
		}
		
		//Enter menu
		if(input.isKeyPressed(Input.KEY_2)){
			gb.setRetry();
			gb.setRowsEmpty();
			sbg.enterState(0);
		}
		
		//Quit game
		if(input.isKeyDown(Input.KEY_3)){
			System.exit(0);
		}
	}
	
	public int getID() {
		return 3;
	}
}
