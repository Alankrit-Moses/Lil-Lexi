/*
 * AUTHOR: Alankrit Moses
 * FILE: Composition.java
 * ASSIGNMENT: Programming Assignment 2
 * PURPOSE: This class is the composition class for LilLexi doc editor.
 */
import java.io.IOException;
import java.util.*;

import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.widgets.Shell;

public class Composition {
	
	private List<Glyph> glyphs;
	private int fontSize;
	private Cursor cursor;
	private Compositor compositor;
	private Scroll scroll;
	private SpellCheck sc;
	private CommandHistory cmh;
	
	public Composition()
	{
		glyphs = new ArrayList<Glyph>();
		fontSize  = 20;
		cursor = new Cursor(fontSize,20,0);
		glyphs.add(cursor);
		compositor = new Compositor();
		scroll = new Scroll(0,glyphs);
		sc = new SpellCheck(glyphs);
		try {
			sc.SetDictionary();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		cmh = new CommandHistory(this);
	}
	/*
	 * This method is responsible for setting the scroll with the
	 * provided offset.
	 *
	 * @param offset is the provided offset.
	 */

	public void setScroll(int offset)
	{
		scroll.setOffset(offset);
	}

	/*
	 * This method is responsible for getting the size of the glyphs list.
	 *
	 * @return size of the glyph list.
	 */

	public int size() {
		return glyphs.size();
	}

	/*
	 * This method is responsible for removing a glyph object from 
	 * our glyph list.
	 */
	
	public void remove()
	{
		if(cursor.getPos()>0)
		{
			cmh.newCommand("REMOVE", glyphs.get(cursor.getPos()-1));
			glyphs.remove(cursor.getPos()-1);
			cursor.decrementPos();
		}
	}
	
	/*
	 * This method is responsible for removing a glyph object from 
	 * our glyph list.
	 * @param undo is the provided string using whose position we remove a
	 * 		  glpyh object.
	 */

	public void remove(String undo)
	{
		if(cursor.getPos()>0)
		{
			glyphs.remove(cursor.getPos()-1);
			cursor.decrementPos();
		}
	}
	
	/*
	 * This method is responsible for setting up the fontsize.
	 * @param fontSize is the provided int font size.
	 */

	public void setFontSize(int fontSize)
	{
		this.fontSize = fontSize;
		for(Glyph g: glyphs)
		{
			if(g.getType().equalsIgnoreCase("character"))
				g.setSize(fontSize);
		}
	}
	
	/*
	 * This method is responsible for getting the command history.
	 * @param cmh is the CommandHistory object.
	 */

	public CommandHistory getCMH()
	{
		return cmh;
	}
	
	/*
	 * This method is responsible for updating the cursor of our doc.
	 * @param String tells whether the cursor moves left or right.
	 */

	public void cursorUpdate(String string) {
		cmh.newCommand(string,null);
		if(string.equalsIgnoreCase("left") && cursor.getPos()>0)
			cursor.decrementPos();
		else if(string.equalsIgnoreCase("right") && cursor.getPos()<glyphs.size()-1)
			cursor.incrementPos();
	}

	/*
	 * This method is responsible for updating the cursor of our doc.
	 * @param String tells whether the cursor moves left or right.
	 *        undo is the command for undo
	 */

	public void cursorUpdate(String string, String undo) {
		if(string.equalsIgnoreCase("left") && cursor.getPos()>0)
			cursor.decrementPos();
		else if(string.equalsIgnoreCase("right") && cursor.getPos()<glyphs.size()-1)
			cursor.incrementPos();
	}

	/*
	 * This method is responsible for calling draw on glyphs.
	 * @param shell is the Shell object
	 *		  e is the provided PaintEvent
	 */

	public void draw(Shell shell, PaintEvent e) {
		for(Glyph g: glyphs)
			g.draw(shell, e);
	}

	/*
	 * This method is responsible for adding the glyph object.
	 * @param obj is the provided glyph object
	 */

	public void add(Glyph ob) {
		cmh.newCommand("ADD", ob);
		compositor.add(glyphs,ob,cursor);
	}

	/*
	 * This method is responsible for adding the glyph object.
	 * @param obj is the provided glyph object
	 * 		  undo is the command for undo
	 */

	public void add(Glyph ob,String undo) {
		compositor.add(glyphs,ob,cursor);
	}

	/*
	 * This method is responsible for getting the font size.
	 * @return fontSize is the provided font
	 */

	public int getFontSize()
	{
		return fontSize;
	}

	/*
	 * This method is responsible for getting the setting the break points.
	 */

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
					y+=(int)(fontSize*2.3);
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
						y+=(int)(fontSize*2.3);
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
		sc.checker();
	}
}