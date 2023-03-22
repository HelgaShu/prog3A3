import java.util.Scanner;

import java.util.Iterator;
/**
 * COMP 2503 Winter 2023 Assignment 3
 * 
 * This program must read a input stream and keeps track of the frequency at
 * which an avenger is mentioned either by name or alias. The program uses a BST
 * for storing the data. Multiple BSTs with alternative orderings are
 * constructed to show the required output.
 * 
 * @author Maryam Elahi
 * @date Winter 2023
 */

public class A3 {

	public String[][] avengerRoster = { { "captainamerica", "rogers" }, { "ironman", "stark" },
			{ "blackwidow", "romanoff" }, { "hulk", "banner" }, { "blackpanther", "tchalla" }, { "thor", "odinson" },
			{ "hawkeye", "barton" }, { "warmachine", "rhodes" }, { "spiderman", "parker" },
			{ "wintersoldier", "barnes" } };

	private int topN = 4;
	private int totalwordcount = 0;
	private int avengerSequenceIndex = 0;
	private Scanner input = new Scanner(System.in);
	private BST<Avenger> alphabticalBST = new BST<>();
	private BST<Avenger> mentionBST = new BST<Avenger>(new AvengerComparatorMentionOrder());
	private BST<Avenger> mostPopularBST = new BST<Avenger>(new AvengerComparatorFreqDesc());
	private BST<Avenger> leastPopularBST = new BST<Avenger>(new AvengerComparatorFreqAsc());

	public static void main(String[] args) {
		A3 a1 = new A3();
		a1.run();
	}

	public void run() {
		readInput();
		createdAlternativeOrderBSTs();
		removeProtected("hawkeye", "barton");
		printResults();
	}

	private void createdAlternativeOrderBSTs() {
		/* TODO:
		 *   - delete the avenger "hawkeye"/"barton" from the alphabetical tree
		 *   use the tree iterator to do an in-order traversal of the alphabetical tree,
		 *   and add avengers to the other trees
		 */
		
		for (Avenger a : alphabticalBST)
        {
            mentionBST.add(a);
            mostPopularBST.add(a);
            leastPopularBST.add(a);
        }
	}
	protected void removeProtected(String avengerAlias, String string) {
        Avenger protectedIdentity = createAvenger(getRosterIndex(avengerAlias));
        alphabticalBST.remove(protectedIdentity);
    }
    

	/**
	 * read the input stream and keep track how many times avengers are mentioned by
	 * alias or last name.
	 */
	private void readInput() {
		/*
		 * While the scanner object has not reached end of stream, 
		 * 	- read a word. 
		 * 	- clean up the word 
		 * 	- if the word is not empty, add the word count. 
		 * 	- Check if the word is either an avenger alias or last name then 
		 *  - Create a new avenger object with the corresponding alias and last name. 
		 *  - if this avenger has already been mentioned, increase the frequency count 
		 *  for the object already in the list. 
		 *  - if this avenger has not been mentioned before, add the 
		 *  newly created avenger to the list, remember to set the frequency and the mention order.
		 * (Remember to keep track of the mention order by setting the mention order for each new avenger.)
		 */
		
		  while (input.hasNext()) {

		        String word = cleanWord(input.next());

		        if (word.length() > 0) {
		            totalwordcount++;
		            
		            int rosterIndex = getRosterIndex(word);

		            if (rosterIndex != -1) {
		                Avenger hero = createAvenger(rosterIndex);
						if (alphabticalBST.find(hero) != null) {
							alphabticalBST.find(hero).mentioned();
						}
				
						else {
		                    hero.setMentionedIndex(avengerSequenceIndex);
							alphabticalBST.add(hero);
							avengerSequenceIndex++;
						}
		            }
		        }
		    }
		}

private String cleanWord(String next) {
    // First, if there is an apostrophe, the substring
    // before the apostrophe is used and the rest is ignored.
    // Words are converted to all lowercase.
    // All other punctuation and numbers are skipped.
    String ret;
    int inx = next.indexOf('\'');
    if (inx != -1) {
        ret = next.substring(0, inx).toLowerCase().trim().replaceAll("[^a-z]", "");
    } else {
        ret = next.toLowerCase().trim().replaceAll("[^a-z]", "");
    }
    return ret;
}

    
    private int getRosterIndex(String input) {
		int index= -1;
		for(int i = 0; i < avengerRoster.length; i++) {
			for (int j = 0; j < 2; j++) {
				if (avengerRoster[i][j].equals(input)) {
					index = i;
					return index;
				}
			}
		}
		return index;
	}
    
    private Avenger createAvenger(int rosterIndex) {
		return new Avenger(avengerRoster[rosterIndex][0], avengerRoster[rosterIndex][1], 1);
	}

	/**
	 * print the results
	 */
private void printResults() {
	System.out.println("Total number of words: " + totalwordcount);
	System.out.println("Number of Avengers Mentioned: " + mentionBST.size());
	System.out.println();
	System.out.println("All avengers in the order they appeared in the input stream:");

    for( Avenger a : mentionBST) {
        System.out.println(a);
    }
	System.out.println();
	
	System.out.println("Top " + topN + " most popular avengers:");
    Iterator<Avenger> mostPopularPrint = mostPopularBST.iterator();
    for (int i = 0; i < 4; i++){
        if (mostPopularPrint.hasNext()){
            System.out.println(mostPopularPrint.next());
        }
    }
	
	System.out.println();

	System.out.println("Top " + topN + " least popular avengers:");
    Iterator<Avenger> leastPopularPrint = leastPopularBST.iterator();
    for (int i = 0; i < 4; i++){
        if (leastPopularPrint.hasNext()) {
            System.out.println(leastPopularPrint.next());
        }
    }
	
	System.out.println();

	System.out.println("All mentioned avengers in alphabetical order:");
    Iterator<Avenger> alphabeticalPrint = alphabticalBST.iterator();

    while (alphabeticalPrint.hasNext())
    {
        System.out.println(alphabeticalPrint.next());
    }
	
    System.out.println();

    System.out.println("Height of the mention order tree is : " + mentionBST.height()
    + " (Optimal height for this tree is : " + optimalHeight(mentionBST.size()) + ")");

    System.out.println("Height of the alphabetical tree is : " + alphabticalBST.height()
    + " (Optimal height for this tree is : " + optimalHeight(alphabticalBST.size()) + ")");

    System.out.println("Height of the most frequent tree is : " + mostPopularBST.height()
    + " (Optimal height for this tree is : " + optimalHeight(mostPopularBST.size()) + ")");
    
    System.out.println("Height of the least frequent tree is : " + leastPopularBST.height()
    + " (Optimal height for this tree is : " + optimalHeight(leastPopularBST.size()) + ")");
	}

private int optimalHeight (int n){
    double h = Math.log(n + 1) / Math.log(2) - 1;
    if (Math.round(h) < h)
        return (int) Math.round(h) + 1;
    else
        return (int) Math.round(h);
}

}
