package similarities;

import java.util.List;

public interface SimilarityFunction {
	public int calculateSimilarity(List<String> tokenized1, List<String> tokenized2);
}
