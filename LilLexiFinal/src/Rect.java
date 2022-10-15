import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.widgets.Shell;

public class Rect extends Shape{

	public Rect() {
		super(0, "shape", 0, 0);
	}
	
	public void draw(Shell shell, PaintEvent e){
		e.gc.drawRectangle(this.getX(), this.getY()-this.getOffset(), 200, 100);
	}

}
