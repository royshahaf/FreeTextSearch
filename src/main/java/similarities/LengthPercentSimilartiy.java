package similarities;
import java.util.List;

public class LengthPercentSimilartiy implements SimilarityFunction {

	@Override
	public int calculateSimilarity(List<String> tokenized1, List<String> tokenized2) {
		return (int) (((double) Math.min(tokenized1.size(), tokenized2.size()) / Math.max(tokenized1.size(), tokenized2.size()))
				* 100);
	}

}
