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
	
	public void setCoord(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	public int getX() {return x;}
	
	public int getY() {return y;}
	
	public int getFontSize() {
		return size;
	}
	
	public String getType(){
		return type;
	}
	
	public int getSize()
	{
		return size;
	}
	public void setSize(int size)
	{
		this.size = size;
	}
	
	public void draw(Shell shell, PaintEvent e, int scrollPos) {
	}
	public void remove() {}
}
