/*
 * AUTHOR: Soumay Agarwal
 * FILE: CharacterObject.java
 * ASSIGNMENT: Programming Assignment 2
 * PURPOSE: This class is known as the CharacterObject and it represents 
 * 			and its purpose is to represent each character typed by the user.
 */
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.events.PaintEvent;

public class CharacterObject extends Glyph{
	private char ch;
	private String style, color;
	private boolean bold;
	private boolean italic;
	private boolean underline;
	private int size;
	
	public CharacterObject(char charac, int size) {
		super(size,"character",0,0);
		this.ch = charac;
	}
	
	public void setCoord(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public char getChar() {
		return ch;
	}
	public void setChar(char ch) {
		this.ch = ch;
	}
	public String getStyle() {
		return style;
	}
	public void setStyle(String style) {
		this.style = style;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public boolean isBold() {
		return bold;
	}
	public void setBold(boolean bold) {
		this.bold = bold;
	}
	public boolean isItalic() {
		return italic;
	}
	public void setItalic(boolean italic) {
		this.italic = italic;
	}
	public boolean isUnderline() {
		return underline;
	}
	public void setUnderline(boolean underline) {
		this.underline = underline;
	}
	@Override
	public void draw(Shell shell, PaintEvent e, int scrollPos) {
		System.out.println("");
		e.gc.drawString(""+ch, x+10, y-scrollPos);
	}
}
