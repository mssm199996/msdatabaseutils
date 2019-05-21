package DomainModel;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import javafx.beans.property.SimpleStringProperty;
import msdatabaseutils.ITypeDroit;

@Entity
@Table(name = "DROITS")
public class Droit {

	private int id;
	private SimpleStringProperty representation = new SimpleStringProperty();

	@Id
	@GeneratedValue(generator = "droitIdGenerator", strategy = GenerationType.AUTO)
	@GenericGenerator(name = "droitIdGenerator", strategy = "increment")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRepresentation() {
		return representation.getValue();
	}

	public void setRepresentation(String representation) {
		this.representation.setValue(representation);
	}

	public SimpleStringProperty representationProperty() {
		return this.representation;
	}

	@Override
	public String toString() {
		return this.getRepresentation();
	}

	@Override
	public boolean equals(Object o) {
		return o instanceof Droit && this.getId() == ((Droit) o).getId();
	}

	@Override
	public int hashCode() {
		return this.getId();
	}

	public boolean hasRight(ITypeDroit typeDroit) {
		return this.getRepresentation().equals(typeDroit.getDesignation());
	}
}
