/*
 * AUTHOR: Soumay Agarwal
 * FILE: SpellCheck.java
 * ASSIGNMENT: Programming Assignment 2
 * PURPOSE: This class is the spell check class which is resonsible for recognising the faulty word
 *			and underlining to show the user about a spelling mistake.
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SpellCheck {
	private List<Glyph> glyphs;
	private List<String> words;
	//private boolean underlined;
	
	public SpellCheck(List<Glyph> lst) {
		glyphs = lst; 
		words=new ArrayList<String>();
		//underlined=false;
	}
	
	public void SetDictionary() throws IOException {
		File file = new File("dictionary.txt");
		
		BufferedReader br = new BufferedReader(new FileReader(file));
		
		String str;
		while((str = br.readLine()) != null) {
			//System.out.println(str);
			words.add(str);
		}
	}
	
	public List<String> getDict(){
		return words;
	}
	public void checker() {
		List<CharacterObject> new_lst = new ArrayList<CharacterObject>();
		String theWord = "";
		for(Glyph g:glyphs) {
			if(g.getType() == "character") {
				CharacterObject the_char = (CharacterObject)  g;
				char new_char = Character.toLowerCase(the_char.getChar());
				
				if(new_char>=97 && new_char<=122) {
					new_lst.add(the_char);
					char new_char2 = Character.toLowerCase(new_char);
					System.out.println("Herer: "+new_char2);
					theWord+=new_char2;
				}
				else{
					new_lst.clear();
					if(words.contains(theWord) || the_char.getChar()==' ') {
						the_char.setUnderline(false);
					}
					else {
						the_char.setUnderline(true);
					}
					theWord="";
				}
			}
		}
		if(!words.contains(theWord)) {
			for(CharacterObject chr:new_lst) {
				System.out.println(chr.getChar());
				if(chr.getChar()!=' ')
					chr.setUnderline(true);
				System.out.println(chr.isUnderline());
			}
		}
		else
		{
			for(CharacterObject chr:new_lst) {
				System.out.println(chr.getChar());
				chr.setUnderline(false);
				System.out.println(chr.isUnderline());
			}
		}
		theWord="";
	}
}