import org.newdawn.slick.*;


public class GameObject {
	
	public int VALUE = 0;
	public Image image;
	public GameObject next;
	private boolean ockupide;
	private static int xpos;
	private static int ypos;
	public int xcordinate;
	public int ycordinate;
	
	
	
	public GameObject(int posx, int posy){
		xpos = posx;
		ypos = posy;
		xcordinate = 230 + 60*posx;
		ycordinate = 400 + 50*posy;
	}
	
	public boolean isOkcupied(){
		return ockupide;
	}
	
	public GameObject getNext(){
		return next;
	}
	
	
	public void move(){
		if(next == null){
			next.image = this.image;
			next.VALUE = this.VALUE;
			this.image = null;
			this.VALUE = 0;
		}
							
	}
	
}
