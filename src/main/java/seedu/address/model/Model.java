package seedu.address.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.person.Person;
import seedu.address.model.policy.Policy;
import seedu.address.model.policy.PolicyName;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Person> PREDICATE_SHOW_ALL_PERSONS = unused -> true;

    /** {@code Predicate} that always evaluate to true */
    Predicate<Policy> PREDICATE_SHOW_ALL_POLICIES = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' address book file path.
     */
    Path getAddressBookFilePath();

    /**
     * Sets the user prefs' address book file path.
     */
    void setAddressBookFilePath(Path addressBookFilePath);

    /**
     * Replaces address book data with the data in {@code addressBook}.
     */
    void setAddressBook(ReadOnlyAddressBook addressBook);

    /** Returns the AddressBook */
    ReadOnlyAddressBook getAddressBook();

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    boolean hasPerson(Person person);

    /**
     * Returns the matching person in the address book.
     */
    Person getPerson(Person person);

    /**
     * Deletes the given person.
     * The person must exist in the address book.
     */
    void deletePerson(Person target);

    /**
     * Adds the given person.
     * {@code person} must not already exist in the address book.
     */
    void addPerson(Person person);

    /**
     * Replaces the given person {@code target} with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    void setPerson(Person target, Person editedPerson);

    /** Returns an unmodifiable view of the filtered person list */
    ObservableList<Person> getFilteredPersonList();

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonList(Predicate<Person> predicate);

    /**
     * Adds the given policy.
     * {@code policy} must not already exist in the address book.
     */
    void addPolicy(Policy policy);

    /**
     * Deletes the given policy.
     * The policy must exist in the address book.
     */
    void deletePolicy(Policy target);

    /**
     * Returns true if a policy with the same identity as {@code policy} exists in the address book.
     */
    boolean hasPolicy(Policy policy);

    /**
     * Returns true if a policy with the same name as {@code policyName} exists in the address book.
     */
    boolean hasPolicyWithName(PolicyName policyName);

    /**
     * Returns the matching policy in the address book.
     */
    Policy getPolicy(Policy policy);

    /**
     * Returns the matching policy with the same {@code policyName} in the address book.
     */
    Policy getPolicyWithName(PolicyName policyName);

    /**
     * Replaces the given policy {@code target} with {@code editedPolicy}.
     * {@code target} must exist in the address book.
     * The policy identity of {@code editedPolicy} must not be the same as another existing policy in the address book.
     */
    void setPolicy(Policy target, Policy editedPolicy);

    /** Returns an unmodifiable view of the filtered policy list */
    ObservableList<Policy> getFilteredPolicyList();

    /**
     * Updates the filter of the filtered policy list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPolicyList(Predicate<Policy> predicate);

    /**
     * Checks whether an undo is possible in the address book.
     */
    boolean canUndo();

    /**
     * Checks whether a redo is possible in the address book.
     */
    boolean canRedo();

    /**
     * Undo to a previous state of the address book.
     */
    void undo();

    /**
     * Redo to a previous undone state of the address book.
     */
    void redo();

    /**
     * Commit a person to the stateful person list of the address book.
     */
    void commitPerson();

    /**
     * Commit a policy to the stateful person list of the address book.
     */
    void commitPolicy();
}
