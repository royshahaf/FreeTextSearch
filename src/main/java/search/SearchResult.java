package search;

import java.util.List;

public class SearchResult {
	private List<Match> matches;

	public List<Match> getMatches() {
		return matches;
	}

	public void setMatches(List<Match> matches) {
		this.matches = matches;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((matches == null) ? 0 : matches.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SearchResult other = (SearchResult) obj;
		if (matches == null) {
			if (other.matches != null)
				return false;
		} else if (!matches.equals(other.matches))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SearchResult [matches=" + matches + "]";
	}
	
}
