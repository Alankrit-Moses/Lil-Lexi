/*
 * AUTHOR: Soumay Agarwal
 * FILE: Rect.java
 * ASSIGNMENT: Programming Assignment 2
 * PURPOSE: This class is responsible for inserting the rectangle shape in our LilLexi
 *			doc editor.
 */
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class Rect extends Shape{

	public Rect() {
		super(0, "shape", 0, 0);
	}
	
	/*
	 * This method is the draw method which is responsible
	 * for drawing the actual rectangle on the screen.
	 *
	 * @param shell is the provided Shell object
	 *		  e is the provided PaintEvent object
	 */
	public void draw(Shell shell, PaintEvent e, Display display){
		e.gc.drawRectangle(this.getX(), this.getY()-this.getOffset(), 200, 100);
	}

}
