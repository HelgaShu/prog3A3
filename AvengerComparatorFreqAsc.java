import java.util.Comparator;

public class AvengerComparatorFreqAsc implements Comparator<Avenger> {

	@Override
	public int compare(Avenger o1, Avenger o2) {
		int frequency = o1.getFrequencyMentioned() - o2.getFrequencyMentioned();
		int lastNameSize = o1.getLastName().length() - o2.getLastName().length();
		
		if (frequency != 0) {
			return frequency;
			
		} else if (lastNameSize != 0){
			return lastNameSize;
			
		} else {
			return o1.getLastName().compareTo(o2.getLastName());
		}
	}
}
