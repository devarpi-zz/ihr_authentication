package org.istream.user.bo.impl;

import org.istream.user.bo.UserBo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserBoImpl implements UserBo{

	private static final Logger logger = LoggerFactory.getLogger(UserBoImpl.class);

	public void printUser(){
		System.out.println("printUser() is executed...");
		logger.debug("Entering UserBoImpl ::");
	}
	
}