import java.util.List;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

class Draw
{
	private Shell shell;
	private PaintEvent e;
	private LilLexiDoc currentDoc;
	private Display display;
	private int fontSize;
	public Draw(Shell shell, PaintEvent e, LilLexiDoc currentDoc, Display display, int fontSize,Device device)
	{
		this.shell = shell;
		this.e = e;
		this.currentDoc = currentDoc;
		this.display = display;
		this.fontSize = fontSize;
		this.setBreakPoints();
		this.execute();
	}
	
	public void execute()
	{
		//System.out.println("PaintListener");
		Rectangle rect = shell.getClientArea();
		e.gc.setBackground(display.getSystemColor(SWT.COLOR_WHITE)); 
        e.gc.fillRectangle(rect.x, rect.y, rect.width, rect.height);
        e.gc.setForeground(display.getSystemColor(SWT.COLOR_BLUE));
		List<Glyph> glyphs = currentDoc.getGlyphs();
		for (Glyph g: glyphs)
		{
			g.draw(shell,e);
		}
		//e.gc.drawString("|", column+32, row+10);
	}
	public void setBreakPoints()
	{
		List<Glyph> lst = currentDoc.getGlyphs();
		int allowed = (int)(750/fontSize);
		int x=0,y=0;
		for(Glyph g: lst)
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
					x+=fontSize;
					allowed-=1;
					if(allowed<=0)
					{
						allowed = (int)(750/fontSize);
						x=0;
						y+=(int)(fontSize*1.6);
					}
				}
			}
			else if(g.getType().equalsIgnoreCase("Image") || g.getType().equalsIgnoreCase("Shape"))
			{
				g.setCoord(200, y+50);
				y+=200;
				allowed = (int)(750/fontSize);
				x=0;
			}
			else if(g.getType().equalsIgnoreCase("cursor"))
			{
				System.out.println("Cursor present");
				if(currentDoc.getCursor()>0)
				{
					Glyph active= lst.get(currentDoc.getCursor()-1);
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