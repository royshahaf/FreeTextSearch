package store;

import java.util.List;

public class StoreEntity {
	private String string;
	private List<String> tokens;
	public StoreEntity(String string, List<String> tokens) {
		super();
		this.string = string;
		this.tokens = tokens;
	}
	public String getString() {
		return string;
	}
	public List<String> getTokens() {
		return tokens;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((string == null) ? 0 : string.hashCode());
		result = prime * result + ((tokens == null) ? 0 : tokens.hashCode());
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
		StoreEntity other = (StoreEntity) obj;
		if (string == null) {
			if (other.string != null)
				return false;
		} else if (!string.equals(other.string))
			return false;
		if (tokens == null) {
			if (other.tokens != null)
				return false;
		} else if (!tokens.equals(other.tokens))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "StoreEntity [string=" + string + ", tokens=" + tokens + "]";
	}
	
}
