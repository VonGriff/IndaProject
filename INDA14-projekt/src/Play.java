import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
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
	
	public Play(int state){
		
	}
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException{
		playingfield = new Image("pics/playingfield.png");
	}
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException{
		
		g.drawImage(playingfield, 200, 400);
		for(int i = 0; i<4; i++){ 
			for(int b =0; b<9; b++){
				GameObject obj = new GameObject(i,b);
				Image img = new Image("pics/suits-diamond.png");
				g.drawImage(img, obj.xcordinate, obj.ycordinate);
		if(previous != null){
			previous.next = obj;
			previous = obj;
		}else
		previous = obj; //Should only happen once per column. 
		}
		}
		//pixelstorlek för de små rutorna x = 60 y = 50.
	}
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException{
		
	}
	
	public void createBackendField() {
		
	}
	
	public int getID(){
		return 1;
	}
	

}
