package msdatabaseutils;

import java.util.Collection;
import java.util.List;

@SuppressWarnings("unchecked")
public interface IDAO<T> extends ISimpleDAO {

	public default List<T> getAll() {
		return (List<T>) (Object) this.getSessionFactoryHandler().select(this.getGlobalSelectionQuery());
	}

	public default void insertArray(T... array) {
		this.getOperationDAO().insertOperation(this.getMultipleElementsInsertReport(array));
		this.getSessionFactoryHandler().insertArray(array);
	}

	public default void insertCollection(Collection<T> collection) {
		this.getOperationDAO().insertOperation(this.getMultipleElementsInsertReport(collection));
		this.getSessionFactoryHandler().insert(collection);
	}

	public default void deleteArray(T... array) {
		this.getOperationDAO().insertOperation(this.getMultipleElementsDeleteReport(array));
		this.getSessionFactoryHandler().removeArray(array);
	}

	public default void deleteCollection(Collection<T> collection) {
		this.getOperationDAO().insertOperation(this.getMultipleElementsDeleteReport(collection));
		this.getSessionFactoryHandler().remove(collection);
	}

	public default void updateArray(T... array) {
		this.getOperationDAO().insertOperation(this.getMultipleElementsUpdateReport(array));
		this.getSessionFactoryHandler().updateArray(array);
	}

	public default void updateCollection(Collection<T> collection) {
		this.getOperationDAO().insertOperation(this.getMultipleElementsUpdateReport(collection));
		this.getSessionFactoryHandler().update(collection);
	}

	public default void insertEntity(T entity) {
		this.getOperationDAO().insertOperation(this.onInsert(entity));
		this.getSessionFactoryHandler().insertArray(entity);
	}

	public default void deleteEntity(T entity) {
		this.getOperationDAO().insertOperation(this.onDelete(entity));
		this.getSessionFactoryHandler().removeArray(entity);
	}

	public default void updateEntity(T entity) {
		this.getOperationDAO().insertOperation(this.onUpdate(entity));
		this.getSessionFactoryHandler().updateArray(entity);
	}

	public default String getMultipleElementsInsertReport(T... elements) {
		return "INSERTION DE " + elements.length + " " + this.getNameOfEntity();
	}

	public default String getMultipleElementsDeleteReport(T... elements) {
		return "SUPPRESSION DE " + elements.length + " " + this.getNameOfEntity();
	}

	public default String getMultipleElementsUpdateReport(T... elements) {
		return "MISE Ã JOUR DE " + elements.length + " " + this.getNameOfEntity();
	}

	public default String getMultipleElementsInsertReport(Collection<T> elements) {
		return "INSERTION DE " + elements.size() + " " + this.getNameOfEntity();
	}

	public default String getMultipleElementsDeleteReport(Collection<T> elements) {
		return "SUPPRESSION DE " + elements.size() + " " + this.getNameOfEntity();
	}

	public default String getMultipleElementsUpdateReport(Collection<T> elements) {
		return "MISE Ã JOUR DE " + elements.size() + " " + this.getNameOfEntity();
	}

	public abstract String getGlobalSelectionQuery();

	public abstract String onInsert(T entity);

	public abstract String onDelete(T entity);

	public abstract String onUpdate(T entity);

	public abstract String getNameOfEntity();

	public abstract SessionFactoryHandler getSessionFactoryHandler();

	public abstract IOperationTypeDAO<? extends OperationType> getOperationDAO();
}
