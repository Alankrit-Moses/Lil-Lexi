/*
 * AUTHOR: Alankrit Moses and Soumay Agarwal
 * FILE: LilLexiControl.java
 * ASSIGNMENT: Programming Assignment 2
 * PURPOSE: This class is Model class for LilLexi doc editor.
 */
import java.util.*;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

/**
 * LilLexiDoc
 */
public class LilLexiDoc 
{
	private LilLexiUI ui;
	private Composition comp;
	public Font font;
	private int fontSize;
	
	/**
	 * Ctor
	 */
	public LilLexiDoc()
	{
		comp = new Composition();
		fontSize = 32;
	}
	
	/**
	 * setUI
	 */
	public void setUI(LilLexiUI ui) {this.ui = ui;}
	/**
	 * add a char
	 */
	public void add(char c,int size) 
	{
		CharacterObject ob = new CharacterObject(c, size);
		comp.add(ob);
		comp.setBreakPoints();
	}
	
	public Composition getComposition()
	{
		return comp;
	}
	
	public void remove()
	{
		comp.remove();
		comp.setBreakPoints();
	}
	
	
	public void setFontSize(int fontSize)
	{
		this.fontSize = fontSize;
		comp.setFontSize(fontSize);
		comp.setBreakPoints();
	}

	
	public void setFont(Font font)
	{
		this.font = font;
		FontData fontDatas[] = font.getFontData();
		System.out.println("Size: "+fontDatas[0].getHeight());
		comp.setFontSize(fontDatas[0].getHeight());
		comp.setBreakPoints();
	}
	public Font getFont() {
		return font;
	}

	public void addImage(Image i) {
		comp.add(new Picture(i));
		comp.setBreakPoints();
	}

	public void drawShape(String string) {
		if(string.equalsIgnoreCase("rectangle"))
			comp.add(new Rect());
		if(string.equalsIgnoreCase("circle"))
			comp.add(new Circle());
		comp.setBreakPoints();
	}

	public void draw(Shell shell, PaintEvent e, Display display) {
		e.gc.setFont(this.font);
		comp.draw(shell,e,display);
	}

	public void undo() {
		comp.undo();
	}
	public void redo() {
		comp.redo();
		
	}
}