package bbcar.persistence.bean;

import java.util.Map;

import javax.faces.context.FacesContext;

public class JsfUtil {
	public static String getRequestParametersAll() {
		Map<String, String[]> strs = FacesContext.getCurrentInstance().getExternalContext()
				.getRequestParameterValuesMap();
		String result = null;
		for (String s : strs.keySet()) {
			String[] str = strs.get(s);
			if (str.length > 0) {
				for (String value : str) {
					if (result == null) {
						result = s + "=" + value;
					} else {
						result = result + "&" + s + "=" + value;
					}
				}
			}
		}
		return result;
	}

	public static Integer getIntegerHttpRequestParameter(String name) {
		String s = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get(name);
		if (s == null) {
			return null;
		}
		Integer result;
		try {
			result = Integer.parseInt(s);
		} catch (NumberFormatException ex) {
			return null;
		}
		return result;
	}
}
