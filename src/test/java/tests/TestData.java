package tests;

import com.github.javafaker.Faker;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class TestData {

    final Map<String, String[]> states = new HashMap<>();
    {
        states.put("NCR", (new String[]{"Delhi", "Gurgaon", "Noida"}));
        states.put("Uttar Pradesh", (new String[]{"Agra", "Lucknow", "Merrut"}));
        states.put("Haryana", (new String[]{"Karnal", "Panipat"}));
        states.put("Rajasthan", (new String[]{"Jaipur", "Jaiselmer"}));
    }

    Faker faker = new Faker(new Locale("en"));
    String firstName = faker.name().firstName(),
            lastName = faker.name().lastName(),
            email = faker.internet().emailAddress(),
            gender = faker.options().option("Male", "Female", "Other"),
            mobile = faker.phoneNumber().subscriberNumber(10),
            month = faker.options().option("January", "February", "March",
                    "April", "May", "June", "July", "August", "September", "October",
                    "November", "December"),
            subject = faker.options().option("Maths", "Chemistry", "Computer Science", "Commerce", "Economics"),
            hobbies = faker.options().option("Sports", "Reading", "Music"),
            picture = "10.jpg",
            address = faker.address().fullAddress(),
            stateRandom = getRandomStates(),
            cityRandom = getRandomStateCities(stateRandom);
    int day = faker.number().numberBetween(1, 28);
    int year = faker.number().numberBetween(1900, 2010);
    String daystr = String.valueOf(day);
    String emailInvalid = "alex123"; // невалидный email

    public String getRandomStates(){
        return faker.options().option(states.keySet().toArray()).toString();
    }

    public String getRandomStateCities(String state) {
        return faker.options().option(states.get(state));
    }
}
