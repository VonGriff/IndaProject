import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
public class Options extends BasicGameState{
	Image pic1;

	public Options(int state){
	
	}
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException{
		pic1 = new Image("pics/control-options.png");
	}
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException{
		g.drawString("options", 100, 100);
		
		pic1.draw(650, 100);
	}
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException{
		
	}
	public int getID(){
		return 2;
	}
}
