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
		String theWord = "";
//		for(Glyph g:glyphs) {
//			if((CharacterObject)g.getChar() != ' ') {
//			char the_char = Character.toLowerCase(g.getChar());
//			System.out.println(the_char);
//			theWord+=the_char;
//			}
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
//		}
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
		for(int i=0; i<glyphs.size();i++) {
			row the_row = ((row) (glyphs.get(i)));
			for(int j=0; j< the_row.size(); j++) {
				if(the_row.getCharObj(j).getChar() != ' ') {
				char the_char = Character.toLowerCase(the_row.getCharObj(j).getChar());
				//System.out.println(the_char);
				theWord+=the_char;
				}
				else if(the_row.getCharObj(j).getChar() == ' ') {
					if(words.contains(theWord)) {
						//System.out.println("Hoorah");
						underlined=false;
					}
					else {
						//System.out.println("Nope");
						underlined=true;
					}
					theWord="";
				}
			}
		}
		if(words.contains(theWord)) {
			//System.out.println("Hoorah");
			underlined=false;
		}
		else {
			//System.out.println("Nope");
			underlined=true;
		}
		theWord="";
		//System.out.println(theWord);
		for(int k=0; k<glyphs.size();k++) {
			row my_row = ((row) (glyphs.get(k)));
			CharacterObject chr = my_row.getCharObj(my_row.size()-1);
			if(glyphs.size()>1 && k!=glyphs.size()-1) {
				row my_row2 = ((row) (glyphs.get(k+1)));
				CharacterObject chr2 = my_row.getCharObj(0);
				if(chr.getChar() != ' ' && chr2.getChar() != ' ') {
					my_row2.insert((new CharacterObject('-', 0,0,0)), 0);
				}
			}
			
		}
	}
	
}


