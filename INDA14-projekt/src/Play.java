import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class Play extends BasicGameState{

	Image playingfield;
	public Play(int state){
		
	}
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException{
		playingfield = new Image("pics/playingfield.png");
	}
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException{
		g.drawImage(playingfield, 200, 400);
		g.drawRect(200, 400, 40, 40);
		//pixelstorlek för de små rutorna x =  y = 
	}
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException{
		
	}
	public int getID(){
		return 1;
	}
}
