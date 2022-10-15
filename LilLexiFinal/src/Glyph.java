/*
 * AUTHOR: Alankrit Moses
 * FILE: Glyph.java
 * ASSIGNMENT: Programming Assignment 2
 * PURPOSE: This class is the glyph class which represents the main component
 *			of our LilLexi doc editor.
 */
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public abstract class Glyph {
	
	private int x, y, scroll_offset;
	private int size;
	private String type;
	
	public Glyph(int size, String type, int x, int y){
		this.size = size;
		this.type = type;
		this.x = x;
		this.y = y;
		scroll_offset = 0;
	}
	
	public void setCoord(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	public void setOffset(int offset)
	{
		scroll_offset = offset;
	}

	public int getOffset()
	{
		return scroll_offset;
	}
	public int getX() {return x;}
	
	public int getY() {return y;}
	
	public String getType(){
		return type;
	}
	
	public int getSize()
	{
		return size;
	}
	public void setSize(int size)
	{
		this.size = size;
	}
	
	public void draw(Shell shell, PaintEvent e) {
	}
	public void remove() {}

	public void draw(Shell shell, PaintEvent e, Display display) {
		// TODO Auto-generated method stub
	}
}
