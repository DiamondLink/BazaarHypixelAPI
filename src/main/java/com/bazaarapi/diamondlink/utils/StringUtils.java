package com.bazaarapi.diamondlink.utils;

import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.util.ArrayList;
import java.util.List;
public class StringUtils {
	public static List<String> getStringBetweenElements(String fullString, String element1,String element2){
		List<String> extractedStrings = new ArrayList<String>();
	    
	    CharacterIterator fullStringIterator = new StringCharacterIterator(fullString);
	    
	    String extractedString = "";
	    
	    boolean addingString = false;

		while (fullStringIterator.current() != CharacterIterator.DONE) {
			
			if (String.valueOf(fullStringIterator.current()).equals(element2) && addingString) {
				addingString = false;
				extractedStrings.add(extractedString);
			}
			
			if (addingString) {
				extractedString += String.valueOf(fullStringIterator.current());
			}
			
			if (String.valueOf(fullStringIterator.current()).equals(element1) && !addingString) {
				addingString = true;
				extractedString = "";
			}
			
			
			fullStringIterator.next();
		}
		
		return extractedStrings;
	    
	}
	

}
