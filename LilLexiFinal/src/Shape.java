/*
 * AUTHOR: Soumay Agarwal
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
	
	public void draw(Shell shell, PaintEvent e){}

}
