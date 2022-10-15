import java.util.List;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

class Scroll
{	
	private int offset;
	List<Glyph> glyphs;
	public Scroll(int scrollPos, List<Glyph> g)
	{
		offset = scrollPos;
		glyphs = g;
		update();
	}
	public void update()
	{
		for(Glyph g: glyphs)
		{
			System.out.println("Reached");
			g.setOffset(offset);
			System.out.println("OFFSET set to : "+g.getX()+" s"+(g.getY()-g.getOffset())+"\n");
		}
	}
	
	public void setOffset(int offset)
	{
		this.offset = offset;
		update();
	}
}