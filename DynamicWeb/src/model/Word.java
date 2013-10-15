package model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

public class Word {
	protected final Logger logger = Logger.getLogger(getClass().getName());   

	public String getStringWithNextLetter(String enteredString){
		String subStringOfSelectedWord = null;
		String selectedWord = null;
		List<String> selectedList = new ArrayList<String>();
		
		List<String> wordList = Dictionary.getInstance().getWordsStartingWith(enteredString);
		if (wordList.isEmpty())
			return GameConstants.PLAYER_LOST_WORD_DOES_NOT_EXIST+enteredString;

		Iterator<String> i = wordList.iterator();
		while (i.hasNext()){
			String nextWord = i.next();
			
			// Try to get words with more than 4 letters 
			if(nextWord.length() > 4){
				// Select words that have odd number of letters so that the computer wins
				if(nextWord.length() % 2 == 1){
					selectedList.add(nextWord);
				}
			}
		}
		
		// Words with less than 4 letters are played if words with more than 4 letters do not have odd number of letters.  
		if (selectedList.isEmpty())
			selectedList = wordList;
		
		// Get random word from selected list
		int index = (int)(Math.random() * selectedList.size());
		selectedWord = selectedList.get(index);
//		logger.info("selectedWord = " + selectedWord);		
		
		if(selectedWord.equalsIgnoreCase(enteredString)){
			return GameConstants.PLAYER_LOST + enteredString;
		}
		
		subStringOfSelectedWord = selectedWord.substring(0, enteredString.length()+1);
//		logger.info("enteredString = " + enteredString + " subStringOfSelectedWord = " + subStringOfSelectedWord);		
		if(subStringOfSelectedWord.length() == selectedWord.length()){
			return GameConstants.PLAYER_WIN + selectedWord;
		}
		
		return GameConstants.GAME_IN_PROGRESS+subStringOfSelectedWord;
	}
	
}
