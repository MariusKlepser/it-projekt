package de.hdm.team7.server;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.hdm.team7.client.LoginInfo;
import de.hdm.team7.client.LoginService;

public class LoginServiceImpl extends RemoteServiceServlet implements
		LoginService {
//	private static final long serialVersionUID = 270628040929463623L;
//
//	public String login(User user) {
//		if (user != null && user.getUser().equalsIgnoreCase("vagner")
//				&& user.getPasswd().equals("Javagner")) {
//			HttpSession httpSession = getThreadLocalRequest().getSession();
//			httpSession.setMaxInactiveInterval(1000 * 60 * 2);
//			return httpSession.getId();
//		}// end if
//		return null;
//	}// end login
 
	private static final long serialVersionUID = 1L;

	public LoginInfo login(String requestUri) {
		UserService userService = UserServiceFactory.getUserService();
		com.google.appengine.api.users.User user = userService.getCurrentUser();
		LoginInfo loginInfo = new LoginInfo();

		if (user != null) {
			loginInfo.setLoggedIn(true);
			loginInfo.setEmailAddress(user.getEmail());
			loginInfo.setNickname(user.getNickname());
			loginInfo.setLogoutUrl(userService.createLogoutURL(requestUri));
		} else {
			loginInfo.setLoggedIn(false);
			loginInfo.setLoginUrl(userService.createLoginURL(requestUri));
		}
		return loginInfo;
	}

}