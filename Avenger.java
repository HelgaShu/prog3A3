public class Avenger implements Comparable<Avenger>{
	
	private String heroAlias;
	private String lastName;
	private int frequencyMentioned;
	private int sequenceMentioned;
	/**
	 * Class Constructor. Instantiates an Avenger object of the specified alias and lastName, with a frequency mentioned of 0.
	 * @param alias - String representation of the Avenger alias.
	 * @param lastName - String representation of the Avenger last name.
	 */
	public Avenger(String alias, String lastName) {
		heroAlias = alias;
		this.lastName = lastName;
		frequencyMentioned = 0;
		sequenceMentioned = 0;
	}
	
	/**
	 * Class Constructor. Instantiates an Avenger object of the specified alias, lastName, and frequency mentioned (frequencyMentioned). 
	 * Used to keep track of times an Avenger is mentioned.
	 * @param alias - String representing the Avenger alias.
	 * @param lastName - String representing the Avenger last name.
	 * @param frequencyMentioned - int representing the amount of times an Avenger is mentioned.
	 */
	public Avenger(String alias, String lastName, int frequencyMentioned) {
		heroAlias = alias;
		this.lastName = lastName;
		this.frequencyMentioned = frequencyMentioned;
		sequenceMentioned = 0;
	}
	
	/**
	 * Sets the heroAlias for the Avenger.
	 * @param alias
	 */
	public void setAlias(String alias) {
		heroAlias = alias;
	}
	
	/**
	 * Sets the lastName for the Avenger.
	 * @param lastName
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setMentionedIndex(int numInSequence) {
		sequenceMentioned = numInSequence;
	}

	public int getMentionedIndex() {
		return sequenceMentioned;
	}
	
	/**
	 * Sets the frequency mentioned for the Avenger.
	 * @param frequency
	 */
	public void setFrequencyMentioned(int frequency) {
		frequencyMentioned = frequency;
	}
	
	/**
	 * Returns the heroAlias of the Avenger.
	 * @return heroAlias
	 */
	public String getAlias() {
		return heroAlias;
	}
	
	/**
	 * Returns the Avenger's last name.
	 * @return lastName
	 */
	public String getLastName() {
		return lastName;
	}
	
	/**
	 * Returns the number of times the Avenger was mentioned.
	 * @return
	 */
	public int getFrequencyMentioned() {
		return frequencyMentioned;
	}
	
	/**
	 * Increments the frequency mentioned by 1.
	 */
	public void mentioned() {
		frequencyMentioned++;
	}
	
	/**
	 * Comparable implementation. Used to order Avenger objects by increasing alphabetic order of heroAlias.
	 * TODO: @return ??
	 */
	public int compareTo(Avenger other) {
		return heroAlias.compareTo(other.getAlias());
	}
	
	/**
	 * Returns a String representation of the Avenger object. Overrides Object.toString().
	 */
	@Override
	public String toString() {
		return heroAlias + " aka " + lastName + " mentioned " + frequencyMentioned + " time(s)";
	}
	
	/**
	 * Overrides Object.equals(Object o). 
     * Does not compare the frequencyMentioned fields.
	 * This override results in a "pseudo-equivalent" comparison.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
			
		if (getClass() != obj.getClass()) {
			return false;
		}
		
		Avenger other = (Avenger) obj;
		
		if (heroAlias == null) {
			if (other.heroAlias != null) {
				return false;
			}
			
		} else if (!heroAlias.equals(other.heroAlias)) {
			return false;
		}
		
		return true;
    }
