package utilities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

import com.github.javafaker.Faker;
import com.github.javafaker.PhoneNumber;

public class RandomDataUtility {

	public static Faker faker;

	public static String getLastName() {

		Faker faker = new Faker(new Locale("en-IND"));
		return faker.name().lastName();

	}

	public static String getAddressLine1() {

		Faker faker = new Faker();
		return faker.address().streetName();

	}

	public static String getCity() {

		Faker faker = new Faker();
		return faker.address().city();

	}

	public static String getPassword() {
		Faker faker = new Faker();
		Random random = new Random();

		String upper = faker.lorem().characters(2).toUpperCase();
		String lower = faker.lorem().characters(4).toLowerCase();
		String digits = String.valueOf(100 + random.nextInt(900)); // 3-digit number
		String symbols = "!@#$%^&*";
		char special = symbols.charAt(random.nextInt(symbols.length()));

		String password = upper + lower + digits + special;
		System.out.println("Generated Password: " + password);
		return password;
	}

	public static String getCountryCode() {

		Faker faker = new Faker();
		return faker.address().countryCode();

	}

	public static String getState() {

		Faker faker = new Faker();
		return faker.address().state();

	}

	public static String getPincode(String state) {

		Faker faker = new Faker();
		return faker.address().zipCodeByState(state);

	}

	public static String getMobileNumber() {

		Faker faker = new Faker();
		return faker.phoneNumber().cellPhone();

	}

	public static String getCategoryName() {
		faker = new Faker();
		return faker.commerce().department();
	}

	public static int getRandomNumberBetWeen(int startNumber, int endNumber) {
		faker = new Faker();
		return faker.number().numberBetween(startNumber, endNumber);
	}

	public static String getFirstName() {
		faker = new Faker(new Locale("en-IND"));
		return faker.name().firstName();
	}

	public static String getFullAddress() {
		faker = new Faker(new Locale("en-IND"));
		return faker.address().fullAddress();
	}

	public static String getBuldingNumberWithStreetAddress() {
		faker = new Faker(new Locale("en-IND"));
		String buildingNumber = faker.address().buildingNumber();
		String streetAddress = faker.address().streetAddress();
		return buildingNumber + streetAddress;
	}

	public static String getPhoneNumber() {

		faker = new Faker(new Locale("en-IND"));
		String extension = faker.phoneNumber().extension();
		String phoneNumber = faker.phoneNumber().cellPhone().concat(extension);
		return phoneNumber;

	}

	public static String getEmail() {

		String alphabet = "abcdefghijklmnopqrstuvwxyz";
		StringBuilder sb = new StringBuilder();
		Random random = new Random();
		int length = 5;
		for (int i = 0; i < length; i++) {
			int index = random.nextInt(alphabet.length());
			char randomChar = alphabet.charAt(index);
			sb.append(randomChar);
		}
		String randomStringEmail = sb.toString() + "@gmail.com";
		System.out.println("Generated e-mail: "+randomStringEmail);
		return randomStringEmail;

	}

	public static String getDateOfBirth_Day() {

		// for getting only date in 'dd' format
		SimpleDateFormat simpledate = new SimpleDateFormat("dd");
		faker = new Faker();
		String date = simpledate.format(faker.date().birthday());
		return date;

	}

	public static Date getAge() {
		faker = new Faker();
		// return age between 18 to 60
		return faker.date().birthday(18, 60);

	}

	public static String getDateOfBirth() {

		// for getting date in 'dd-mm-yyyy' format
		SimpleDateFormat simpledate = new SimpleDateFormat("dd-mm-yyyy");
		faker = new Faker();
		Date date = faker.date().birthday(18, 60);
		return simpledate.format(date);

	}

}
