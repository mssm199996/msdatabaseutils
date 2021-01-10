package DomainModel;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;

@Entity
public class SoftwareFeature {

	private int id;
	private SimpleStringProperty representation = new SimpleStringProperty();
	private SimpleBooleanProperty visiblity = new SimpleBooleanProperty();
	private SimpleBooleanProperty enability = new SimpleBooleanProperty();

	private ISoftwareFeatureType ISoftwareFeatureType;

	@Id
	@GeneratedValue(generator = "softwareFeatureIdGenerator", strategy = GenerationType.AUTO)
	@GenericGenerator(name = "softwareFeatureIdGenerator", strategy = "increment")
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

	public Boolean getVisiblity() {
		return visiblity.getValue();
	}

	public void setVisiblity(Boolean visiblity) {
		this.visiblity.setValue(visiblity);
	}

	public SimpleBooleanProperty visibilityProperty() {
		return this.visiblity;
	}

	public Boolean getEnability() {
		return enability.getValue();
	}

	public void setEnability(Boolean enability) {
		this.enability.setValue(enability);
	}

	public SimpleBooleanProperty enabilityProperty() {
		return this.enability;
	}

	@Transient
	public ISoftwareFeatureType getISoftwareFeatureType() {
		return ISoftwareFeatureType;
	}

	public void setISoftwareFeatureType(ISoftwareFeatureType iSoftwareFeatureType) {
		ISoftwareFeatureType = iSoftwareFeatureType;
	}

	@Override
	public String toString() {
		return this.getRepresentation();
	}

	@Override
	public boolean equals(Object o) {
		return o instanceof SoftwareFeature && this.getId() == ((SoftwareFeature) o).getId();
	}

	@Override
	public int hashCode() {
		return this.getId();
	}

	public boolean compareToISoftwareFeatureType(ISoftwareFeatureType iSoftwareFeatureType) {
		if (iSoftwareFeatureType == null)
			return false;
		return this.getRepresentation().equals(iSoftwareFeatureType.getDesignation());
	}
}
