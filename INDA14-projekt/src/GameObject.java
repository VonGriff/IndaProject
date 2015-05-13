import org.newdawn.slick.*;


public class GameObject {
	
	public int VALUE = 0;
	private Image image;
	private GameObject next;
	
	
	public boolean hasNext(){
		if(next != null){
			return true;
		}else
			return false;
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
