package msdatabaseutils;

import java.util.LinkedList;
import java.util.List;

public interface ICategorizerDAO<C extends ICategorizer> extends IDAO<C> {

	@SuppressWarnings("unchecked")
	public default List<C> getSpecifiedCategorizers(String designation) {
		if (designation == null || designation.replaceAll(" ", "").equals(""))
			return this.getAll();

		String[] values = this.tokenMePlease(designation, " ");

		String request = "SELECT DISTINCT categorizer FROM " + this.getNameOfEntity() + " categorizer " + this.getBeforeWhereClauseQuery()
				+ " WHERE (";
		List<Object> params = new LinkedList<Object>();

		for (String param : values) {
			request += "(categorizer." + this.getDesignationFieldName() + " like ?) and ";

			params.add("%" + param + "%");
		}

		request = request.substring(0, request.length() - 4);
		request += ")";

		return (List<C>) (Object) this.getSessionFactoryHandler().select(request, params);
	}

	@SuppressWarnings("unchecked")
	public default List<ICategorizer> getCategorizerByDesignation(String designation) {
		return (List<ICategorizer>) (Object) this.getSessionFactoryHandler().select("FROM " + this.getNameOfEntity()
				+ " categorizer WHERE (categorizer." + this.getDesignationFieldName() + " = ?)", designation);
	}

	public default boolean doesCategorizerExist(String designation) {
		return !this.getSessionFactoryHandler().select("FROM " + this.getNameOfEntity()
				+ " categorizer WHERE (categorizer." + this.getDesignationFieldName() + " = ?)", designation).isEmpty();
	}

	public default String getBeforeWhereClauseQuery() {
		return "";
	}

	public abstract String getDesignationFieldName();

	public abstract String getNameOfEntity();

	public abstract SessionFactoryHandler getSessionFactoryHandler();
}
