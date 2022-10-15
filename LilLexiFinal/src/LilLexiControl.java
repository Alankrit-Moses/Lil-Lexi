import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Shell;

/**
 * Controller
 */
public class LilLexiControl 
{
	private LilLexiDoc currentDoc;
	private Font font;
	private int FontSize;
	/**
	 * LilLexiControl
	 */
	public LilLexiControl( LilLexiDoc doc)
	{
		this.currentDoc = doc;
		FontSize = 32;
	}
	
	public void addImage(Image i)
	{
		currentDoc.addImage(i);
	}
	
	/**
	 * selectCard  user selects a card
	 */
	void add( char c) 
	{	
		currentDoc.add(c,FontSize);
	}
	/**
	 * quitEditor  user quits
	 */
	void  quitEditor() 
	{ 
		System.exit(0); 
	}	
	
	void remove() {
		currentDoc.remove();
	}
	
//	int length() {
//		return currentDoc.length();
//	}

	public void drawShape(String string) {
		currentDoc.drawShape(string);
	}

	public void draw(Shell shell, PaintEvent e) {
		
		currentDoc.draw(shell,e);
		
	}
}