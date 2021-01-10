package msdatabaseutils;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import org.hibernate.annotations.GenericGenerator;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class OperationType {

	private int id;
	private SimpleStringProperty description = new SimpleStringProperty();
	private SimpleObjectProperty<LocalDate> dateOperation = new SimpleObjectProperty<>();
	private SimpleObjectProperty<LocalTime> heureOperation = new SimpleObjectProperty<>();

	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description.getValue();
	}

	public void setDescription(String description) {
		this.description.setValue(description);
	}

	public LocalDate getDateOperation() {
		return dateOperation.getValue();
	}

	public void setDateOperation(LocalDate dateOperation) {
		this.dateOperation.setValue(dateOperation);
	}

	public LocalTime getHeureOperation() {
		return heureOperation.getValue();
	}

	public void setHeureOperation(LocalTime heureOperation) {
		this.heureOperation.setValue(heureOperation);
	}

	public SimpleStringProperty descriptionProperty() {
		return this.description;
	}

	public SimpleObjectProperty<LocalDate> dateIOperationProperty() {
		return this.dateOperation;
	}

	public SimpleObjectProperty<LocalTime> heureIOperationProperty() {
		return this.heureOperation;
	}

	public abstract SimpleStringProperty designationOperateurProperty();
}
