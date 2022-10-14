import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.widgets.Shell;

public abstract class Shape extends Glyph{

	public Shape(int size, String type, int x, int y) {
		super(size, type, x, y);
	}
	
	public void draw(Shell shell, PaintEvent e, int scrollPos){}

}
