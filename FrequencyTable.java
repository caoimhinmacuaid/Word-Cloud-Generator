package ie.gmit.dip;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class FrequencyTable {
	private Map<String,Integer> frequencyTable;
	
	public FrequencyTable(Map<String, Integer> frequencyTable) {
		this.frequencyTable = frequencyTable;
		
	}
	
	/** Sorts hashMap frequency table
	 * 
	 * @return a sorted map
	 * 
	 * <i>I estimate that the time complexity of this method is
	 * nlogn as we are sorting a hashmap</i>
	 */
	
	public Map<String, Integer> sort() {
		Map<String, Integer> sortedMap = frequencyTable.entrySet().stream()
				.sorted(Comparator.comparingInt(e -> -e.getValue()))
				.collect(Collectors.toMap(
						Map.Entry::getKey,
						Map.Entry::getValue,
						(a, b) -> { throw new AssertionError(); },
						LinkedHashMap::new
						));

		
		return sortedMap;
	}

}
