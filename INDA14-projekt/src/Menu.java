import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

import org.lwjgl.input.Mouse;
import org.lwjgl.input.Keyboard;;


public class Menu extends BasicGameState{
	
	public String mouse = "No mouse input!";
	Image pic1, pic2, pic3;
	
	
	public Menu(int state){
		
	}
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException{
		pic1 = new Image("pics/main800x600.png");
		pic2 = new Image("pics/notfullscreen.png");
		pic3 = new Image("pics/fullscreen.png");
	}
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException{
		g.drawString(mouse, 0, 550);
		g.drawString("1. Play", 320, 300);
		g.drawString("2. Options", 320, 350);
		g.drawString("3. Exit", 320, 400);
		g.drawString("Fullscreen", 650, 520);
		
		pic1.draw(250, 10);
		pic1.setFilter(Image.FILTER_NEAREST);
		if(gc.isFullscreen() == false){
			pic2.draw(650, 500);
		}
		if(gc.isFullscreen() == true){
			pic3.draw(650, 500);
		}
	}
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException{
		int xpos = Mouse.getX();
		int ypos = Mouse.getY();
		mouse = "Mouse position x: " + xpos + " y: " + ypos;
		
		//play button
		if(Keyboard.isKeyDown(Input.KEY_1)){
			sbg.enterState(1);
		}
		if((xpos < 390 && xpos > 315) && (ypos < 305 && ypos > 280)){
			if(Mouse.isButtonDown(0)){
				sbg.enterState(1);
			}
		}
		//option button
		if(Keyboard.isKeyDown(Input.KEY_2)){
			sbg.enterState(2);
		}
		if((xpos < 415 && xpos > 315) && (ypos < 255 && ypos > 230)){
			if(Mouse.isButtonDown(0)){
				sbg.enterState(2);
			}
		}
		//exit button
		if(Keyboard.isKeyDown(Input.KEY_3)){
			System.exit(0);
		}
		if((xpos < 390 && xpos > 315) && (ypos < 205 && ypos > 180)){
			if(Mouse.isButtonDown(0)){
				System.exit(0);
			
			}

		}
		
		//fullscreen button
		if((xpos < 670 && xpos > 645) && (ypos < 110 && ypos > 80)){
			if(Mouse.isButtonDown(0)){
				AppGameContainer appgc = (AppGameContainer) gc;
				appgc.setFullscreen(true);
			}
		}
		//window button
		if((xpos < 670 && xpos > 645) && (ypos < 110 && ypos > 80)){
			if(Mouse.isButtonDown(0)){
				AppGameContainer appgc = (AppGameContainer) gc;
				appgc.setFullscreen(false);
			}
		}
	}
	public int getID(){
		return 0;
	}
}
