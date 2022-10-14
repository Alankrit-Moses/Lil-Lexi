import java.awt.Color;

public class CharacterObject {
	private char the_char;
	private int size;
	private String style;
	private Color colour;
	private boolean bold=false;
	private boolean italic=false;
	private boolean underline=false;			
	
	public CharacterObject(char Character, int size, String style, Color colour) {
		the_char = Character;
		this.size = size;
		this.style = style;
		this.colour = colour;
	}
	
	public char getThe_char() {
		return the_char;
	}
	public void setThe_char(char the_char) {
		this.the_char = the_char;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public String getStyle() {
		return style;
	}
	public void setStyle(String style) {
		this.style = style;
	}
	public Color getColour() {
		return colour;
	}
	public void setColour(Color colour) {
		this.colour = colour;
	}
	public boolean isBold() {
		return bold;
	}
	public void setBold(boolean bold) {
		this.bold = bold;
	}
	public boolean isItalic() {
		return italic;
	}
	public void setItalic(boolean italic) {
		this.italic = italic;
	}
	public boolean isUnderline() {
		return underline;
	}
	public void setUnderline(boolean underline) {
		this.underline = underline;
	}
	
}
