package com.board.web.service;

import java.util.Date;

public interface RegService {
	boolean registMember(String name, String nickname, String id, String password, String confirmationPassword, String email, Date birthday);
}
