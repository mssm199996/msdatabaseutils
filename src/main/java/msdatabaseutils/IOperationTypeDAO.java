package msdatabaseutils;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface IOperationTypeDAO<O extends OperationType> {

	public default void insertOperation(String description) {
		O operation = this.constructOperation();
		operation.setDateOperation(LocalDate.now());
		operation.setHeureOperation(LocalTime.now());
		operation.setDescription(description);

		this.linkWithOperateur(operation);
		this.getSessionFactoryHandler().insertArray(operation);
	}

	public abstract O constructOperation();

	public abstract void linkWithOperateur(O operation);
	
	public abstract List<O> fetchFromDatabase(String descriptionOperation, LocalDate dateOperation);

	public abstract SessionFactoryHandler getSessionFactoryHandler();
}
