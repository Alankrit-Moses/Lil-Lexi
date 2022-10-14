import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.widgets.Shell;

public abstract class Glyph {
	
	protected int x, y;
	private int size;
	private String type;
	
	public Glyph(int size, String type, int x, int y){
		this.size = size;
		this.type = type;
		this.x = x;
		this.y = y;
	}
	
	public int getFontSize() {
		return size;
	}
	
	public String getType(){
		return type;
	}

	public void draw(Shell shell, PaintEvent e) {
		// TODO Auto-generated method stub
		
	}
	public void remove() {}
}
