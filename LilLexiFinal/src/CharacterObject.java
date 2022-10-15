/*
 * AUTHOR: Soumay Agarwal
 * FILE: CharacterObject.java
 * ASSIGNMENT: Programming Assignment 2
 * PURPOSE: This class is known as the CharacterObject and it represents 
 * 			and its purpose is to represent each character typed by the user.
 *			This class inherits our Glyph class.
 */
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.events.PaintEvent;

public class CharacterObject extends Glyph{
	private char ch;
	private boolean underline;
	private int size;
	
	public CharacterObject(char charac, int size) {
		super(size,"character",0,0);
		this.ch = charac;
	}
	
	/*
	 * This method is a getter for the character.
	 * 
	 * @return ch is the character
	 */
	public char getChar() {
		return ch;
	}

	/*
	 * This method is the setter for the character.
	 * 
	 * @param ch is the character
	 */
	public void setChar(char ch) {
		this.ch = ch;
	}

	/*
	 * This method is a getter for the underline boolean.
	 *
	 * @return underline is a boolean
	 */
	public boolean isUnderline() {
		return underline;
	}

	/*
	 * This method is a setter for the underline boolean.
	 * 
	 * @param underline is a boolean
	 */

	public void setUnderline(boolean underline) {
		this.underline = underline;
	}

	/*
	 * This method is the draw function which overrides from the glyph and is
	 * responsible for drawing the actual character on the screen.
	 * 
	 * @param shell is the provided Shell object
	 *	      e is the PaintEvents
	 */
	@Override
	public void draw(Shell shell, PaintEvent e) {
		System.out.println("Size: "+this.getSize());
		e.gc.drawString(""+ch,this.getX(), this.getY()-this.getOffset()+5);
		if(this.isUnderline())
		{
			e.gc.drawLine(this.getX(),this.getY()-this.getOffset()+this.getSize(),(int)(this.getX()+(this.getSize()*1.55)),this.getY()-this.getOffset()+this.getSize());
		}
	}
}
