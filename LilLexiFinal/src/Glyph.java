import org.eclipse.swt.events.PaintEvent;
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
/*
 * AUTHOR: Alankrit Moses and Soumay Agarwal
 * FILE: LilLexiControl.java
 * ASSIGNMENT: Programming Assignment 2
 * PURPOSE: This class is Model class for LilLexi doc editor.
 */
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
}
