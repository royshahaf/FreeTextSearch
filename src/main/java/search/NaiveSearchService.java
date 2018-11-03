package search;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import similarities.SimilarityFunction;
import store.NaiveStoreService;
import store.StoreEntity;
import store.StoreService;
import tokenization.Tokenizer;

public class NaiveSearchService implements SearchService {

	private SimilarityFunction function;
	private Tokenizer tokenizer;
	private StoreService store = new NaiveStoreService();
	
	public NaiveSearchService(Tokenizer tokenizer, SimilarityFunction function) {
		this.tokenizer = tokenizer;
		this.function = function;
	}
	
	@Override
	public void index(String string) {
		store.add(new StoreEntity(string, tokenizer.tokenize(string)));
	}

	@Override
	public SearchResult search(String string) {
		Iterator<StoreEntity> iterator = store.iterator();
		List<String> newTokenized = tokenizer.tokenize(string);
		List<Match> matches = new ArrayList<>();
		while (iterator.hasNext()) {
			Match match = new Match();
			StoreEntity entity = iterator.next();
			match.setMatchedAgainst(entity.getString());
			match.setSimilarity(function.calculateSimilarity(newTokenized, entity.getTokens()));
			matches.add(match);
		}
		Collections.sort(matches, new Comparator<Match>() {
			@Override
			public int compare(Match o1, Match o2) {
				return o2.getSimilarity() - o1.getSimilarity();
			}
		});
		SearchResult result = new SearchResult();
		result.setMatches(matches);
		return result;
	}

}
