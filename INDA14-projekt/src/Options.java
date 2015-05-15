import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class Options extends BasicGameState{

	Image pic1, pic2, pic3;

	public String mouse = "No input yet!";


	//Ska byta bild bara för test
	
	public Options(int state){
	
	}
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException{
		pic1 = new Image("pics/control-options.png");
		pic2 = new Image("pics/notfullscreen.png");
		pic3 = new Image("pics/fullscreen.png");
	}
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException{
		g.drawString(mouse, 80, 550);
		g.drawString("options", 100, 100);
		g.drawString("Fullscreen", 650, 520);
		g.drawString("Back", 200, 500);

		
		pic1.draw(100, 10);
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
		//back button
		if((xpos < 240 && xpos > 195) && (ypos < 105 && ypos > 85)){
			if(Mouse.isButtonDown(0)){
				sbg.enterState(0);
			}
		}
	}
	public int getID(){
		return 2;
	}
}
