/*
 * AUTHOR: Alankrit Moses
 * FILE: Shape.java
 * ASSIGNMENT: Programming Assignment 2
 * PURPOSE: This class is responsible for inserting the shapes into our LilLexi editor.
 *			It provides a choice between a rectangle and a circle. This class extends our
 *			glyph class.
 */
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.widgets.Shell;

public abstract class Shape extends Glyph{

	public Shape(int size, String type, int x, int y) {
		super(size, type, x, y);
	}
	
	/*
	 * This method is the draw method of an abstract class
	 * which is then implemented by the circle and rectangle.
	 *
	 * @param shell is the provided Shell object
	 *		  e is the provided PaintEvent object
	 */
	public void draw(Shell shell, PaintEvent e){}

}
