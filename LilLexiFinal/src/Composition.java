import java.util.*;

import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.widgets.Shell;

public class Composition {
	
	private List<Glyph> glyphs;
	private int fontSize;
	private Cursor cursor;
	private Compositor compositor;
	private Scroll scroll;
	
	public Composition()
	{
		glyphs = new ArrayList<Glyph>();
		fontSize  = 20;
		cursor = new Cursor(fontSize,20,0);
		glyphs.add(cursor);
		compositor = new Compositor();
		scroll = new Scroll(0,glyphs);
	}

	public void setScroll(int offset)
	{
		scroll.setOffset(offset);
	}

	public int size() {
		return glyphs.size();
	}
	
	public void remove()
	{
		if(cursor.getPos()>0)
		{
			glyphs.remove(cursor.getPos()-1);
			cursor.decrementPos();
		}
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
	
	public void cursorUpdate(String string) {
		if(string.equalsIgnoreCase("left") && cursor.getPos()>0)
			cursor.decrementPos();
		else if(string.equalsIgnoreCase("right") && cursor.getPos()<glyphs.size()-1)
			cursor.incrementPos();
	}

	public void draw(Shell shell, PaintEvent e) {
		for(Glyph g: glyphs)
			g.draw(shell, e);
	}

	public void add(Glyph ob) {
		compositor.add(glyphs,ob,cursor);
	}
	public int getFontSize()
	{
		return fontSize;
	}
	
	public void setBreakPoints()
	{
		double spacing = 1.5;
		int allowed = (int)(750/(fontSize*spacing));
		int x=0,y=0;
		for(Glyph g: glyphs)
		{
			if(g.getType().equalsIgnoreCase("character"))
			{
				if(((CharacterObject)(g)).getChar()=='\n')
				{
					allowed = (int)(750/fontSize);
					x=0;
					y+=(int)(fontSize*1.6);
					g.setCoord(x, y);
				}
				else
				{
					g.setCoord(x, y);
					x+=(int)(fontSize*spacing);
					allowed-=1;
					if(allowed<=0)
					{
						allowed = (int)(750/(fontSize*spacing));
						x=0;
						y+=(int)(fontSize*1.6);
					}
				}
			}
			else if(g.getType().equalsIgnoreCase("Image") || g.getType().equalsIgnoreCase("Shape"))
			{
				g.setCoord(200, y+25+fontSize);
				y+=150+fontSize;
				allowed = (int)(750/fontSize);
				x=0;
			}
			else if(g.getType().equalsIgnoreCase("cursor"))
			{
				System.out.println("Cursor present");
				if(cursor.getPos()>0)
				{
					Glyph active= glyphs.get(cursor.getPos()-1);
					if(active.getType().equalsIgnoreCase("character"))
						g.setCoord(active.getX()==0? active.getX():(active.getX()+20),active.getY());
					else
						g.setCoord(active.getX()+200,active.getY()+50);
				}
				else
					g.setCoord(0, 0);
			}
		}
	}
}