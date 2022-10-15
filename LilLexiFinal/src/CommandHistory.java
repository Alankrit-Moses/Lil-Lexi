/**
 * Author: Alankrit Jacinth Moses
 * File: CommandHistory.java
 * Assigment: A2
 * Description: This is the file that 
 */
import java.util.Stack;
public class CommandHistory {
	
	private Composition comp;
	private Stack<String> undo;
	private Stack<String> redo;
	private Stack<Glyph> undoGlyphs;
	private Stack<Glyph> redoGlyphs;
	
	public CommandHistory(Composition comp)
	{
		this.comp = comp;
		undo = new Stack<String>();
		redo = new Stack<String>();
		undoGlyphs = new Stack<Glyph>();
		redoGlyphs = new Stack<Glyph>();
	}
	
	public void newCommand(String command, Glyph g)
	{
		undo.push(command);
		undoGlyphs.push(g);
	}
	public void undo()
	{
		String command = null;
		Glyph g = null;
		if(undo.size()>0)
		{
			command = undo.pop();
			redo.push(command);
		}
		
		if(command==null)
			return;
		else if(command.equalsIgnoreCase("ADD"))
		{
			redoGlyphs.push(undoGlyphs.pop());
			comp.remove("undo");
		}
		else if(command.equalsIgnoreCase("REMOVE"))
		{
			Glyph popped = undoGlyphs.pop();
			redoGlyphs.push(popped);
			comp.add(popped,"undo");
		}
		else if(command.equalsIgnoreCase("left"))
		{
			comp.cursorUpdate("right","undo");
			redoGlyphs.add(undoGlyphs.pop());
		}
		else if(command.equalsIgnoreCase("right"))
		{
			comp.cursorUpdate("left","undo");
			redoGlyphs.add(undoGlyphs.pop());
		}
	}
	
	public void redo()
	{
		String command = null;
		Glyph g = null;
		if(redo.size()>0)
		{
			command = redo.pop();
			undo.push(command);
		}
		
		if(command==null)
			return;
		else if(command.equalsIgnoreCase("ADD"))
		{
			Glyph popped = redoGlyphs.pop();
			undoGlyphs.push(popped);
			comp.add(popped,"undo");
		}
		else if(command.equalsIgnoreCase("REMOVE"))
		{
			undoGlyphs.push(redoGlyphs.pop());
			comp.remove("undo");
		}
		else if(command.equalsIgnoreCase("left"))
		{
			comp.cursorUpdate("left","undo");
			undoGlyphs.add(redoGlyphs.pop());
		}
		else if(command.equalsIgnoreCase("right"))
		{
			comp.cursorUpdate("right","undo");
			undoGlyphs.add(redoGlyphs.pop());
		}
	}
}
