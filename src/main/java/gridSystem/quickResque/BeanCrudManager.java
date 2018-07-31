package gridSystem.quickResque;

import java.util.ArrayList;

public interface BeanCrudManager {
	public ArrayList<Object> viewAll();

	public int addNew(Object account);

	public Object update(int id, Object accountNew);

	public void delete(int id);
	
	public Object get(int id);
}
