/*
 * AUTHOR: Alankrit Moses
 * FILE: Cursor.java
 * ASSIGNMENT: Programming Assignment 2
 * PURPOSE: This class  represents the cursor which is employed in the doc.
 */
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.widgets.Shell;

public class Cursor extends Glyph{

	private int oneDimensionalPos;
	public Cursor(int size, int x, int y) {
		super(size, "Cursor", x, y);
		oneDimensionalPos = 0;
	}
	public void draw(Shell shell, PaintEvent e)
	{
		e.gc.drawLine(this.getX(), this.getY()-this.getOffset(), this.getX(), this.getY()+getSize()-this.getOffset());
	}
	public void incrementPos() {
		this.oneDimensionalPos+=1;
	}
	public int getPos() {
		return oneDimensionalPos;
	}
	public void decrementPos() {
		oneDimensionalPos-=1; 
	}

}
