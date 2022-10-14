/**
 * Lil Lexi Document Model
 * 
 */
import java.util.List;

import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;

import java.util.ArrayList;
import java.util.Collection;

/**
 * LilLexiDoc
 */
public class LilLexiDoc 
{
	private LilLexiUI ui;
	private List<Glyph> glyphs;
	private int fontSize;
	public Font font;
	public int cursor;
	
	/**
	 * Ctor
	 */
	public LilLexiDoc()
	{
		glyphs = new ArrayList<Glyph>();
		fontSize = 32;
		cursor = 0;
		glyphs.add(new Cursor(fontSize,20,0));
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
		glyphs.add(cursor, ob);
		cursor+=1;
	}
	public int length() {
		return glyphs.size();
	}
	
	public void remove()
	{
		if(cursor>0)
		{
			glyphs.remove(cursor-1);
			cursor-=1;
		}
	}
	
	public void cursorUpdate(String string) {
		if(string.equalsIgnoreCase("left") && cursor>0)
		{
			cursor-=1;
		}
		else if(string.equalsIgnoreCase("right") && cursor<glyphs.size())
			cursor+=1;
		else if(string.equalsIgnoreCase("up") && cursor>(750/fontSize))
			cursor-=(750/fontSize);
		else if(string.equalsIgnoreCase("down") && cursor<(glyphs.size()-(750/fontSize)-1))
			cursor+=(750/fontSize);
			
	}
	public void setFontSize(int fontSize)
	{
		this.fontSize = fontSize;
		for(Glyph g: glyphs)
		{
			if(g.getType().equalsIgnoreCase("character"))
				g.setSize(fontSize);
		}
	}
	/**
	 * gets
	 */
	public List<Glyph> getGlyphs(){return glyphs;}

	public int getFontSize() {
		System.out.println(fontSize);
		return fontSize;
	}
	
	public int getCursor() { return cursor; }
	
	public void setFont(Font font)
	{
		this.font = font;
	}
	public Font getFont() {
		return font;
	}

	public void addImage(Image i) {
		glyphs.add(cursor,new Picture(i));
		cursor+=1;
	}
}