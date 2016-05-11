package action;

import javax.servlet.http.HttpServletRequest;

public abstract class Azione {
	public abstract String esegui(HttpServletRequest request);
}
