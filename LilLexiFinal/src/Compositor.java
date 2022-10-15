import java.util.List;

public class Compositor {
	
	public void add(List<Glyph> glyphs, Glyph g, Cursor cursor) {
		glyphs.add(cursor.getPos(),g);
		cursor.incrementPos();
	}
	
	public void remove(List<Glyph> g, Cursor cursor)
	{
		g.remove(cursor.getPos());
		cursor.decrementPos();
	}
}
