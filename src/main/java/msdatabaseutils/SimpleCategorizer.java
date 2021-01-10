package msdatabaseutils;

import javax.persistence.Embeddable;

import javafx.beans.property.SimpleStringProperty;

@Embeddable
public class SimpleCategorizer implements ICategorizer, Comparable<SimpleCategorizer> {

	private SimpleStringProperty designation = new SimpleStringProperty();

	public SimpleCategorizer() {
	}

	public SimpleCategorizer(String designation) {
		this.designation.set(designation);
	}

	@Override
	public String getDesignation() {
		return this.designation.get();
	}

	@Override
	public void setDesignation(String designation) {
		this.designation.set(designation);
	}

	@Override
	public SimpleStringProperty designationProperty() {
		return this.designation;
	}

	@Override
	public boolean equals(Object o) {
		return o instanceof SimpleCategorizer && this.getDesignation().equals(((SimpleCategorizer) o).getDesignation());
	}

	@Override
	public int hashCode() {
		return this.getDesignation().hashCode();
	}

	@Override
	public int compareTo(SimpleCategorizer o) {
		return this.getDesignation().compareTo(o.getDesignation());
	}
}
