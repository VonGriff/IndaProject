import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

import org.lwjgl.input.Mouse;
import org.lwjgl.input.Keyboard;;


public class Menu extends BasicGameState{
	
	public String mouse = "No mouse input!";
	Image pic1;
	
	public Menu(int state){
		
	}
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException{
		pic1 = new Image("pics/main.png");
	}
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException{
		g.drawString(mouse, 100, 100);
		g.drawString("1. Play", 800, 500);
		g.drawString("2. Options", 800, 550);
		g.drawString("3. Exit", 800, 600);
		
		pic1.draw(650, 10);
	}
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException{
		int xpos = Mouse.getX();
		int ypos = Mouse.getY();
		mouse = "Mouse position x: " + xpos + "y: " + ypos;
		
		//play button
		if(Keyboard.isKeyDown(2)){
			sbg.enterState(1);
		}
		if((xpos < 870 && xpos > 785) && (ypos < 590 && ypos > 560)){
			if(Mouse.isButtonDown(0)){
				sbg.enterState(1);
			}
		}
		//option button
		if(Keyboard.isKeyDown(3)){
			sbg.enterState(2);
		}
		if((xpos < 900 && xpos > 785) && (ypos < 540 && ypos > 509)){
			if(Mouse.isButtonDown(0)){
				sbg.enterState(2);
			}
		}
		//exit button
		if(Keyboard.isKeyDown(4)){
			System.exit(0);
		}
		if((xpos < 875 && xpos > 785) && (ypos < 485 && ypos > 460)){
			if(Mouse.isButtonDown(0)){
				System.exit(0);
			
		}
		}
	}
	public int getID(){
		return 0;
	}
}
