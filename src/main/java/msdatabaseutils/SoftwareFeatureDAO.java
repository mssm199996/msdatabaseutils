package msdatabaseutils;

import DomainModel.SoftwareFeature;

public class SoftwareFeatureDAO implements IDAO<SoftwareFeature> {

	private SessionFactoryHandler sessionFactoryHandler;
	private IOperationTypeDAO<? extends OperationType> iOperationTypeDAO;

	public SoftwareFeatureDAO(SessionFactoryHandler sessionFactoryHandler,
			IOperationTypeDAO<? extends OperationType> iOperationTypeDAO) {
		this.sessionFactoryHandler = sessionFactoryHandler;
		this.iOperationTypeDAO = iOperationTypeDAO;
	}

	@Override
	public String getGlobalSelectionQuery() {
		return "FROM SoftwareFeature";
	}

	@Override
	public String onInsert(SoftwareFeature entity) {
		return null;
	}

	@Override
	public String onDelete(SoftwareFeature entity) {
		return null;
	}

	@Override
	public String onUpdate(SoftwareFeature entity) {
		return "CHANGEMENT DE LA FONCTIONNALITEE: " + entity.getRepresentation();
	}

	@Override
	public String getNameOfEntity() {
		return "SoftwareFeature";
	}

	@Override
	public SessionFactoryHandler getSessionFactoryHandler() {
		return this.sessionFactoryHandler;
	}

	@Override
	public IOperationTypeDAO<? extends OperationType> getOperationDAO() {
		return this.iOperationTypeDAO;
	}
}
