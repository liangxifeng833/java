package lxf.util;

import java.util.ArrayList;

import lxf.entity.User;

public class SessionUtil {
	public static User getUserBySessionId(ArrayList<User> userList, String sessionIdString) {
		// TODO Auto-generated method stub
		for (int i = 0; i < userList.size(); i++) {
			User user = userList.get(i);
			if( user.getSessionIdString().equals(sessionIdString)){
				return user;
			}
		}
		return null;
	}
}
