package DomainModel;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface ICompte {

	public abstract int getId();

	public abstract String getUsername();

	public abstract String getPassword();

	public abstract LocalDate getDateDerniereConnexion();

	public abstract LocalTime getHeureDerniereConnexion();

	public abstract void setDateDerniereConnexion(LocalDate date);

	public abstract void setHeureDerniereConnexion(LocalTime heure);

	public abstract Boolean isAdmin();

	public abstract List<Droit> getDroits();
}
