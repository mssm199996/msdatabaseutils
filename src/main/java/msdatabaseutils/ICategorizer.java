package msdatabaseutils;

import javafx.beans.property.SimpleStringProperty;

public interface ICategorizer {

	public abstract String getDesignation();

	public abstract void setDesignation(String designation);

	public SimpleStringProperty designationProperty();
}
