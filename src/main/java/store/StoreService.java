package store;

import java.util.Iterator;

public interface StoreService {
	public void add(StoreEntity entity);
	public Iterator<StoreEntity> iterator();
}
