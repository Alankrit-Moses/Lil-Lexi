/**
 * Lil Lexi Document Model
 * 
 */
import java.util.*;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Shell;

/**
 * LilLexiDoc
 */
public class LilLexiDoc 
{
	private LilLexiUI ui;
	
	private int fontSize;
	private Composition comp;
	public Font font;
	
	/**
	 * Ctor
	 */
	public LilLexiDoc()
	{
		comp = new Composition();
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
		comp.setFontSize(fontSize);
		comp.setBreakPoints();
	}

	
	public void setFont(Font font)
	{
		this.font = font;
		FontData fontDatas[] = font.getFontData();
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

	public void draw(Shell shell, PaintEvent e) {
		e.gc.setFont(font);
		comp.draw(shell,e);
	}
}