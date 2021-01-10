package msdatabaseutils;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public interface ISimpleDAO {

	public default String[] tokenMePlease(String something, String separators) {
		List<String> results = new ArrayList<String>();

		StringTokenizer stringTokenizer = new StringTokenizer(something, separators);

		while (stringTokenizer.hasMoreElements())
			results.add((String) stringTokenizer.nextElement());

		return results.toArray(new String[results.size()]);
	}
}
