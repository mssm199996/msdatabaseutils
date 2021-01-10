package msdatabaseutils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SimpleCategorizerFactory {

	public static SimpleCategorizer fromString(String designation) {
		return new SimpleCategorizer(designation);
	}
	
	public static List<SimpleCategorizer> fromStrings(Collection<String> designations){
		List<SimpleCategorizer> result = new ArrayList<>(designations.size());
		
		for(String designation: designations)
			result.add(SimpleCategorizerFactory.fromString(designation));
		
		return result;
	}
}
