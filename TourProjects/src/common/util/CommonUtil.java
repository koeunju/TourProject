package common.util;

import javax.servlet.http.HttpServletRequest;

public class CommonUtil {
	
	public static void addMsgLoc(HttpServletRequest req, String msg, String loc) {
		req.setAttribute("message", msg);
		req.setAttribute("loc", loc);
	}
	public static void addMsgBack(HttpServletRequest req, String msg) {
		req.setAttribute("message", msg);
		req.setAttribute("loc", "javascript:history.back()");
	}
}
