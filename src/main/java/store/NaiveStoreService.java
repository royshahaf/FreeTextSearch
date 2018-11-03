package store;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class NaiveStoreService implements StoreService {

	private List<StoreEntity> entities = new CopyOnWriteArrayList<>();
	
	@Override
	public void add(StoreEntity entity) {
		entities.add(entity);
	}

	@Override
	public Iterator<StoreEntity> iterator() {
		return entities.iterator();
	}

}
