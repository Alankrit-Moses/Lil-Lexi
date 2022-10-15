import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.widgets.Shell;

public class Circle extends Shape{

	public Circle() {
		super(0,"Shape",0,0);
	}
	
	public void draw(Shell shell, PaintEvent e) {
		e.gc.drawOval(this.getX(), this.getY()-this.getOffset(), 100, 100);
	}

}
