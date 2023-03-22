import java.util.Comparator;

public class AvengerComparatorMentionOrder implements Comparator<Avenger> {
	public int compare(Avenger o1, Avenger o2) {
		        return o2.getMentionedIndex() - o1.getMentionedIndex();
		    }
}
