import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.widgets.Shell;

public class Cursor extends Glyph{

	public Cursor(int size, int x, int y) {
		super(size, "Cursor", x, y);
	}
	public void draw(Shell shell, PaintEvent e, int scrollPos)
	{
		e.gc.drawLine(x+5, y-scrollPos, x+5, y+getSize()-scrollPos);
	}

}
