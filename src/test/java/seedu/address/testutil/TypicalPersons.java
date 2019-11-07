package seedu.address.testutil;

import static seedu.address.commons.util.TimeUtil.ABOVE_SIXTYFIVE;
import static seedu.address.commons.util.TimeUtil.BELOW_TWENTY;
import static seedu.address.commons.util.TimeUtil.TWENTY_TO_SIXTYFOUR;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_OF_BIRTH_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_OF_BIRTH_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GENDER_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GENDER_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NRIC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NRIC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_DIABETIC;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_SMOKER;
import static seedu.address.model.person.Gender.FEMALE;
import static seedu.address.model.person.Gender.MALE;
import static seedu.address.testutil.TypicalPolicy.FIRE_INSURANCE;
import static seedu.address.testutil.TypicalPolicy.HEALTH_INSURANCE;
import static seedu.address.testutil.TypicalPolicy.LIFE_INSURANCE;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import seedu.address.commons.util.PersonBuilder;
import seedu.address.model.person.Person;

/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalPersons {

    public static final Person ALICE = new PersonBuilder().withName("Alice Pauline")
        .withNric("S0000001J").withAddress("123, Jurong West Ave 6, #08-111").withEmail("alice@example.com")
        .withPhone("94351253").withDateOfBirth("12.12.1982").withGender("Female")
        .withPolicies(HEALTH_INSURANCE).withTags("diabetic", "high blood pressure").build();
    public static final Person BENSON = new PersonBuilder().withName("Benson Meier")
        .withNric("S0000002J").withAddress("311, Clementi Ave 2, #02-25")
        .withEmail("johnd@example.com").withPhone("98765432").withDateOfBirth("12.12.1942").withGender("Male")
        .withPolicies(LIFE_INSURANCE).withTags("smoker", "disabled", "high blood pressure").build();
    public static final Person CARL = new PersonBuilder().withName("Carl Kurz").withNric("S0000003J")
        .withPhone("95352563").withEmail("heinz@example.com").withAddress("wall street")
        .withDateOfBirth("6.6.1996").withGender("Male").withTags("asthmatic").build();
    public static final Person DANIEL = new PersonBuilder().withName("Daniel Meier").withNric("S0000004J")
        .withPhone("87652533").withEmail("cornelia@example.com").withAddress("10th street")
        .withDateOfBirth("14.2.2019").withGender("Male").withTags("tuberculosis").build();
    public static final Person ELLE = new PersonBuilder().withName("Elle Meyer").withNric("S0000005J")
        .withPhone("94822247").withEmail("werner@example.com").withAddress("michegan ave")
        .withDateOfBirth("17.5.2000").withGender("Male").withTags("myopic").build();
    public static final Person FIONA = new PersonBuilder().withName("Fiona Kunz").withNric("S0000006J")
        .withPhone("94824279").withEmail("lydia@example.com").withAddress("little tokyo")
        .withDateOfBirth("15.8.2008").withGender("Female").withTags("pregnant").build();
    public static final Person GEORGE = new PersonBuilder().withName("George Best").withNric("S0000007J")
        .withPhone("94824425").withEmail("anna@example.com").withAddress("4th street")
        .withDateOfBirth("5.5.2015").withGender("Male").withTags("glaucoma").build();

    // Manually added
    public static final Person HOON = new PersonBuilder().withName("Hoon Meier").withNric("S0123456H")
        .withPhone("84824248").withEmail("stefan@example.com").withAddress("little india")
        .withDateOfBirth("22.4.1988").withGender("Female").build();
    public static final Person IDA = new PersonBuilder().withName("Ida Mueller").withNric("T0987656H")
        .withPhone("84821318").withEmail("hans@example.com").withAddress("chicago ave")
        .withDateOfBirth("16.7.2017").withGender("Male").build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Person AMY = new PersonBuilder().withName(VALID_NAME_AMY).withNric(VALID_NRIC_AMY)
        .withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY)
        .withDateOfBirth(VALID_DATE_OF_BIRTH_AMY).withGender(VALID_GENDER_AMY)
        .withTags(VALID_TAG_DIABETIC).build();
    public static final Person BOB = new PersonBuilder().withName(VALID_NAME_BOB).withNric(VALID_NRIC_BOB)
        .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB)
        .withDateOfBirth(VALID_DATE_OF_BIRTH_BOB).withGender(VALID_GENDER_BOB)
        .withTags(VALID_TAG_DIABETIC, VALID_TAG_SMOKER)
        .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalPersons() {
    } // prevents instantiation

    public static List<Person> getTypicalPersons() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }

    public static Person getSinglePerson() {
        return ALICE;
    }

    public static ObservableMap<String, Integer> getTypicalPolicyPopularityBreakdown() {
        ObservableMap<String, Integer> result = FXCollections.observableHashMap();
        result.put(HEALTH_INSURANCE.getName().toString(), 1);
        result.put(LIFE_INSURANCE.getName().toString(), 1);
        result.put(FIRE_INSURANCE.getName().toString(), 0);
        return result;
    }

    public static ObservableMap<String, Integer> getAgeGroupBreakdown() {
        ObservableMap<String, Integer> result = FXCollections.observableHashMap();
        result.put(BELOW_TWENTY, 4);
        result.put(TWENTY_TO_SIXTYFOUR, 2);
        result.put(ABOVE_SIXTYFIVE, 1);
        return result;
    }

    public static ObservableMap<String, Integer> getGenderBreakdown() {
        ObservableMap<String, Integer> result = FXCollections.observableHashMap();
        result.put(MALE, 5);
        result.put(FEMALE, 2);
        return result;
    }
}
