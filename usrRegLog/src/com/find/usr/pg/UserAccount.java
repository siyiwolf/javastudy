package com.find.usr.pg;

import java.io.*;

public class UserAccount implements Serializable{
	private static int usrCount = 0;
	public static int getUsrCount() {
		return usrCount;
	}

	public static void setUsrCount(int usrCount) {
		UserAccount.usrCount = usrCount;
	}

	public String getuName() {
		return uName;
	}

	public void setuName(String uName) {
		this.uName = uName;
	}

	public String getuPassword() {
		return uPassword;
	}

	public void setuPassword(String uPassword) {
		this.uPassword = uPassword;
	}

	private String uName;
	private String uPassword;
	
	public UserAccount()
	{
		usrCount++;
	}
	
	public UserAccount(String usrName, String usrPassword)
	{
		usrCount++;
		uName = usrName;
		uPassword = usrPassword;
	}
	
	public boolean passwordVerity(String uPassword)
	{
		return (this.uPassword.equals(uPassword));
	}
	
}
