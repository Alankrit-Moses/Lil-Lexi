import java.util.List;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.widgets.Shell;

import java.util.ArrayList;

public class row extends Glyph{
	
	List<Glyph> glyphs;
	private int current_pos,size;
	private int elements;
	private int column_left = 750;
	
	public row(int y_offset, int size) {
		super(0,"row",0, y_offset);
		glyphs = new ArrayList<Glyph>();
		this.size = size;
		current_pos = 0;
		elements = 0;
	}
	public void insert(Glyph ch)
	{
		if(ch.getType().equals("character"))
		{
			glyphs.add(current_pos, ch);
			current_pos+=1;
			column_left-=size;
			elements+=1;
			//System.out.println("Current Position");
		}
	}
	
	public int getCurrentPos()
	{
		System.out.println("Asked: ");
		return current_pos;
	}
	public void setSize(int size)
	{
		this.size = size;
	}
	
	public void remove()
	{
		if(current_pos>0)
		{
			glyphs.remove(current_pos-1);
			current_pos-=1;
			column_left+=size;
		}
	}
	
	public int size()
	{
		return glyphs.size();
	}
	
	public int spaceLeft()
	{
		return column_left;
	}
	
	public void draw(Shell shell, PaintEvent e)
	{
		for(Glyph g: glyphs)
			g.draw(shell,e);
	}
	public List<CharacterObject> rearrange() {
		int allowed = 800/size;
		List<CharacterObject> disposed;
		
		for(int x=0;x<glyphs.size();x++)
		{
			CharacterObject current = (CharacterObject) glyphs.get(x);
			if(x<allowed)
				current.setCoord(x,this.y);
			
		}
		return null;
	}
}
