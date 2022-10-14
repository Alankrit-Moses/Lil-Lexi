import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.widgets.Shell;

public class Circle extends Shape{

	public Circle() {
		super(0,"Shape",0,0);
	}
	
	public void draw(Shell shell, PaintEvent e, int scrollPos) {
		e.gc.drawOval(this.x, this.y-scrollPos, 100, 100);
	}

}
