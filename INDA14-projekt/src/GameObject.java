import org.newdawn.slick.*;


public class GameObject {
	
	public int VALUE = 0;
	private Image image;
	private GameObject next;
	private boolean ockupide;
	private int xpos;
	private int ypos;
	public GameObject(int posx, int posy){
		xpos = posx;
		ypos = posy;
	}
	
	public boolean isOkcupied(){
		return ockupide;
	}
	
	public GameObject getNext(){
		return next;
	}
	
	private void addImage(int randomInt) {
		
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
