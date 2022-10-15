/*
 * AUTHOR: Soumay Agarwal
 * FILE: LilLexiControl.java
 * ASSIGNMENT: Programming Assignment 2
 * PURPOSE: This class is Picture glyph class for LilLexi doc editor which is responsible
 * 			for adding a particular image on the Lexi doc editor.
 */
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Shell;

public class Picture extends Glyph{

	private Image i;
	public Picture(Image i) {
		super(0, "Image", 100, 100);
		this.i = i;
	}
	
	/*
	 * This method is the draw method which is responsible
	 * for drawing the actual image on the screen.
	 *
	 * @param shell is the provided Shell object
	 *		  e is the provided PaintEvent object
	 */

	public void draw(Shell shell, PaintEvent e)
	{
		e.gc.drawImage(i, this.getX(), this.getY()-this.getOffset());
	}
}
