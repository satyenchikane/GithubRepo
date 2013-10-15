package model;

import java.io.*;
import java.util.*;
import java.util.logging.Logger;

public class Dictionary{
	protected final Logger logger = Logger.getLogger(getClass().getName());  
	static Dictionary wl = null;
	static final List<String> allWordsList = new ArrayList<String>();
	private final String WORD_LIST = "word.lst";
	
	private Dictionary(){
		  BufferedReader br = null;
		  InputStream fstream = null;
	try{	  		
		  fstream = getClass().getClassLoader().getResourceAsStream(WORD_LIST);
		  logger.info("fstream for word.lst = " + fstream);
		  
		  br = new BufferedReader(new InputStreamReader(fstream));
		  String strLine;
			
		  //Read File Line By Line
		  while ((strLine = br.readLine()) != null)   {
			  allWordsList.add(strLine);
		  }
			Collections.sort(allWordsList);

		  br.close();
	  } catch (IOException ioe){
		  logger.severe(ioe.getMessage());
	  } finally {
		  if(br != null){
			 try{
				 br.close();
			 } catch (IOException closebrEx){
				 logger.severe(closebrEx.getMessage());
			 }
		  }
		  if(fstream != null){
			 try{
				fstream.close();
			 } catch (IOException closeEx){
				 logger.severe(closeEx.getMessage());
			 }
		  }
	  }
	}
	
	public static synchronized Dictionary getInstance(){
		if (wl == null){
			wl = new Dictionary();
		}
		return wl;
	}
	
	
	public List<String> getWordsStartingWith(String startwith){
		List<String> listStartingWith = new ArrayList<String>();
		
		Iterator<String> i = allWordsList.iterator();
		String nextString = null;
		boolean breakSearch = false;
		
		while(i.hasNext()){
			nextString = i.next();
			if(nextString.startsWith(startwith)){
				listStartingWith.add(nextString);
				breakSearch = true;
			} else {
				// As the list is sorted, once the words starting with the entered string are finished, then break out of the loop
				if(breakSearch)
					break;
			}
		}
		
		return listStartingWith;
	}
	
}
