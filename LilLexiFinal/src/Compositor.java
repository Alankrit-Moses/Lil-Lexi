/*
 * AUTHOR: Alankrit Moses
 * FILE: Composition.java
 * ASSIGNMENT: Programming Assignment 2
 * PURPOSE: This class is the compositor class for LilLexi doc editor.
 */
import java.util.List;

public class Compositor {
	/*
	 * This method is responsible for adding element at cursor position.
	 *
	 * @param glyphs is the provided glyph object list.
	 *		  g is the glyph object
	 *		  cursor is the provided Cursor object
	 */

	public void add(List<Glyph> glyphs, Glyph g, Cursor cursor) {
		glyphs.add(cursor.getPos(),g);
		cursor.incrementPos();
	}

	/*
	 * This method is responsible for removing element at cursor position.
	 *
	 * @param g is the provided glyph object list.
	 *		  cursor is the provided Cursor object
	 */

	public void remove(List<Glyph> g, Cursor cursor)
	{
		g.remove(cursor.getPos());
		cursor.decrementPos();
	}
}
