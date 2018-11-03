package search;

public interface SearchService {
	public void index(String string);
	public SearchResult search(String string);
}
