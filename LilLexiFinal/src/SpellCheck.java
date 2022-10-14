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
	private List<String> words=new ArrayList<String>();
	private boolean underlined;
	
	public SpellCheck(List<Glyph> lst) {
		glyphs = lst; 
		underlined=false;
	}
	
	public void SetDictionary() throws IOException {
		File file = new File("new.txt");
		
		BufferedReader br = new BufferedReader(new FileReader(file));
		
		String str;
		while((str = br.readLine()) != null) {
			//System.out.println(str);
			words.add(str);
		}
	}
//		 Scanner sc = new Scanner(file);
//		 
//		    while (sc.hasNextLine()) {
//		      System.out.println(sc.nextLine());
//		  }
//	}
	
	public List<String> getDict(){
		return words;
	}
	public void checker() {
		List<CharacterObject> new_lst = new ArrayList<CharacterObject>();
		String theWord = "";
		for(Glyph g:glyphs) {
			if(g.getType() == "character") {
				CharacterObject the_char = (CharacterObject)  g;
				char new_char = the_char.getChar();
				if(new_char != ' ' && new_char != '\n') {
				new_lst.add(the_char);
				char new_char2 = Character.toLowerCase(new_char);
				System.out.println("Herer: "+new_char2);
				theWord+=new_char2;
				}
				else if(new_char == ' ') {
					new_lst.clear();
					if(words.contains(theWord)) {
						System.out.println("Hoorah me");
						the_char.setUnderline(false);
					}
					else {
						System.out.println("Nope");
						the_char.setUnderline(true);
					}
					theWord="";
				}
			}
		}
			if(words.contains(theWord)) {
				System.out.println("Hoorah");
			}
			else {
				System.out.println("Nope");
				for(CharacterObject chr:new_lst) {
					System.out.println(chr.getChar());
					chr.setUnderline(true);
					System.out.println(chr.isUnderline());
				}
			}
			theWord="";
			
//			for(int j=0; j<glyphs.size();j++) {
//				if(glyphs.get(j).getType() == "character") {
//					CharacterObject myChar = (CharacterObject) glyphs.get(j);
//					if(myChar.getChar() == '\n') {
//						if(j!=glyphs.size()-1) {
//							if(((CharacterObject) glyphs.get(j-1)).getChar() != ' ' && ((CharacterObject) glyphs.get(j+1)).getChar() != ' ') {
//								if(j+2<glyphs.size()) {
//									((CharacterObject) glyphs.get(j+2)).setChar(((CharacterObject) glyphs.get(j+1)).getChar());
//									((CharacterObject) glyphs.get(j+1)).setChar('-');
//									
//								}
//								else {
//								CharacterObject charac = new CharacterObject(((CharacterObject) glyphs.get(j+1)).get('-'));
//								((CharacterObject) glyphs.get(j+1)).setChar('-');
//								}
//							}
//						}
//					}
//				}
//			}
//			for(Glyph newer:glyphs) {
//				CharacterObject somer = (CharacterObject) newer;
//				System.out.println(somer.getChar());
//			}
			//System.out.println(theWord);
						
//			char the_char = Character.toLowerCase(g.getChar());
//			System.out.println(the_char);
//			theWord+=the_char;
//			else if(g.getChar() == ' ') {
//				if(words.contains(theWord)) {
//					System.out.println("Hoorah");
//					underlined=false;
//				}
//				else {
//					System.out.println("Nope");
//					underlined=true;
//				}
//				theWord="";
//			}
		}
//		if(words.contains(theWord)) {
//			System.out.println("Hoorah");
//			underlined=false;
//		}
//		else {
//			System.out.println("Nope");
//			underlined=true;
//		}
//		theWord="";
//		System.out.println(theWord);
//		for(int i=0; i<glyphs.size();i++) {
//			row the_row = ((row) (glyphs.get(i)));
//			for(int j=0; j< the_row.size(); j++) {
//				if(the_row.getCharObj(j).getChar() != ' ') {
//				char the_char = Character.toLowerCase(the_row.getCharObj(j).getChar());
//				//System.out.println(the_char);
//				theWord+=the_char;
//				}
//				else if(the_row.getCharObj(j).getChar() == ' ') {
//					if(words.contains(theWord)) {
//						//System.out.println("Hoorah");
//						underlined=false;
//					}
//					else {
//						//System.out.println("Nope");
//						underlined=true;
//					}
//					theWord="";
//				}
//			}
//		}
//		if(words.contains(theWord)) {
//			//System.out.println("Hoorah");
//			underlined=false;
//		}
//		else {
//			//System.out.println("Nope");
//			underlined=true;
//		}
//		theWord="";
		//System.out.println(theWord);
//		for(int k=0; k<glyphs.size();k++) {
//			row my_row = ((row) (glyphs.get(k)));
//			CharacterObject chr = my_row.getCharObj(my_row.size()-1);
//			if(glyphs.size()>1 && k!=glyphs.size()-1) {
//				row my_row2 = ((row) (glyphs.get(k+1)));
//				CharacterObject chr2 = my_row.getCharObj(0);
//				if(chr.getChar() != ' ' && chr2.getChar() != ' ') {
//					my_row2.insert((new CharacterObject('-', 0,0,0)), 0);
//				}
//			}
//			
//		}

	
}


