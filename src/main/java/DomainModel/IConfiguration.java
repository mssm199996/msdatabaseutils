package DomainModel;

public interface IConfiguration {

	public String getCheminSauvegarde();

	public void setCheminSauvegarde(String cheminSauvegarde);

	public Boolean isActivateCamera();

	public void setActivateCamera(Boolean activateCamera);

	public String getCheminMysql();

	public void setCheminMysql(String cheminMysql);

	public Boolean isAdvancedPrinting();

	public void setAdvancedPrinting(Boolean advancedPrinting);
}
