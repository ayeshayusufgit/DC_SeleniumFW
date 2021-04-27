package com.democart.utils;

import java.util.ArrayList;
import java.util.List;

public class Constants {

	public static final String FORGOT_PASSWORD_TITLE = "Forgot Your Password?";
	public static final String FORGOT_PASSWORD_EMAIL_SENT = "An email with a confirmation link has been sent your email address.";
	public static final String ACCOUNTS_PAGE_TITLE = "My Account";
	public static final int ACCOUNTS_SECTION_COUNT = 4;
	public static final String SEARCH_SHEET_NAME = "Search";
	public static final String ACCOUNT_CREATION_SUCCESS_MESSAGE = "Your Account Has Been Created!";
	public static final String REGISTRATION_SHEET_NAME = "Registration";
	public static final String LOGIN_SHEET_NAME = "Login";
	
	public static List<String> accSecList;

	public static final List<String> getAccountSectionList() {
		accSecList = new ArrayList<String>();
		accSecList.add("My Account");
		accSecList.add("My Orders");
		accSecList.add("My Affiliate Account");
		accSecList.add("Newsletter");
		return accSecList;
	}
}
