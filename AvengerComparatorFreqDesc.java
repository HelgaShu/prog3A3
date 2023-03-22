import java.util.Comparator;

public class AvengerComparatorFreqDesc implements Comparator<Avenger> {

	@Override
	public int compare(Avenger o1, Avenger o2) {
		int compareFrequency = o2.getFrequencyMentioned() - o1.getFrequencyMentioned();
		
		if (compareFrequency == 0) {
			return o1.getAlias().compareTo(o2.getAlias());
			
		} else {
            return compareFrequency;
		}
	}
}
