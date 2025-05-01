package dataProvider;

import org.testng.annotations.Test;

import utilities.SharedUserData;

import org.testng.annotations.DataProvider;

public class DataProviderClass {

	String email=SharedUserData.getEmail();
	String password=SharedUserData.getPassword();
	
	@DataProvider(name = "Unsuccessful Login with correct email")
	public Object[][] dp1() {
		return new Object[][] { new Object[] { email, "3554" }

		};
	}

	@DataProvider(name = "Unsuccessful Login with incorrect email")
	public Object[][] dp2() {
		

	return new Object[][] { new Object[]{"ghgj989", password }, new Object[] { "bnbhjhj898", "gjhg$56" } 
	};
	}
	
	
	@DataProvider(name = "Search product names")
	public Object[][] dp3() {
		return new Object[][] { new Object[] { "Radiant Tee" }, new Object[] { "Bella Tank"},new Object[] { "Selene Yoga Hoodie"}

		};
	}

}