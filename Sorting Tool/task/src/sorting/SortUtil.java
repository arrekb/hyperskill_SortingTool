package sorting;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class SortUtil {
    public static <V> List<Map.Entry<V, Integer>> sortByFrequency(List<V> inputList) {
        Map<V, Integer> freqMap = new TreeMap<>();
        for (var item : inputList) {
            freqMap.putIfAbsent(item, 0);
            freqMap.put(item, freqMap.get(item) + 1);
        }

        List<Map.Entry<V, Integer>> result = new ArrayList<>(freqMap.entrySet());
        result.sort(Map.Entry.comparingByValue());
        return result;
    }
}
