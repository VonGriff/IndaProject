import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class Options extends BasicGameState{
	
	Image pic1;
	public String mouse = "No input yet!";

	public Options(int state){
	
	}
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException{
		pic1 = new Image("pics/control-options.png");
	}
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException{
		g.drawString(mouse, 80, 80);
		g.drawString("options", 100, 100);
		g.drawString("back", 950, 550);
		
		pic1.draw(650, 100);
	}
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException{
		int xpos = Mouse.getX();
		int ypos = Mouse.getY();
		mouse = "Mouse position x: " + xpos + "y: " + ypos;
		
		//back button
		if ((xpos > 950 && xpos < 990) &&  (ypos > 510 && ypos < 530)) {
			if (Mouse.isButtonDown(0)) {
				sbg.enterState(0);
			}
		}
	}
	public int getID(){
		return 2;
	}
}
