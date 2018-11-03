package search;

public class Match {
	private String matchedAgainst;
	private int similarity;
	
	public String getMatchedAgainst() {
		return matchedAgainst;
	}
	public void setMatchedAgainst(String matchedAgainst) {
		this.matchedAgainst = matchedAgainst;
	}
	public int getSimilarity() {
		return similarity;
	}
	public void setSimilarity(int similarity) {
		this.similarity = similarity;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((matchedAgainst == null) ? 0 : matchedAgainst.hashCode());
		result = prime * result + similarity;
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
		Match other = (Match) obj;
		if (matchedAgainst == null) {
			if (other.matchedAgainst != null)
				return false;
		} else if (!matchedAgainst.equals(other.matchedAgainst))
			return false;
		if (similarity != other.similarity)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Match [matchedAgainst=" + matchedAgainst + ", similarity=" + similarity + "]";
	}
}
