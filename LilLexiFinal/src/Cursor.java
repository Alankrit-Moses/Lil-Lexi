import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.widgets.Shell;

public class Cursor extends Glyph{

	public Cursor(int size, int x, int y) {
		super(size, "Cursor", x, y);
	}
	public void draw(Shell shell, PaintEvent e)
	{
		e.gc.drawLine(x+20, y, x+20, y+getSize());
	}

}
