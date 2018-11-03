package similarities;

import java.util.ArrayList;
import java.util.List;

public class SharedTokensSimilartiy implements SimilarityFunction {

	public int calculateSimilarity(List<String> tokenized1, List<String> tokenized2) {
		ArrayList<String> tempList = new ArrayList<>(tokenized1);
		tempList.retainAll(tokenized2);
		return tempList.size();
	}

}
