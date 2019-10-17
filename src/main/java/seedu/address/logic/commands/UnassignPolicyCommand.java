package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PERSON_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_POLICY_INDEX;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_POLICIES;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.PersonBuilder;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.policy.Policy;

/**
 * Command to assign a new policy to a person.
 */
public class UnassignPolicyCommand extends Command {

    public static final String COMMAND_WORD = "unassignpolicy";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Removes an assigned policy from a person.\n"
            + "Parameters: "
            + PREFIX_POLICY_INDEX + "POLICY INDEX "
            + PREFIX_PERSON_INDEX + "PERSON INDEX\n"
            + "Example: "
            + COMMAND_WORD + " "
            + PREFIX_POLICY_INDEX + "1 "
            + PREFIX_PERSON_INDEX + "1";

    public static final String MESSAGE_UNASSIGN_POLICY_SUCCESS = "Unassigned Policy: %1$s from Person: %2$s";
    public static final String MESSAGE_ALREADY_UNASSIGNED = "Person: %1$s does not have the Policy: %2$s.";


    private final Index policyIndex;
    private final Index personIndex;

    /**
     * @param policyIndex Index of the policy to unassign
     * @param personIndex Index of the person to be unassigned
     */
    public UnassignPolicyCommand(Index policyIndex, Index personIndex) {
        requireNonNull(policyIndex);
        requireNonNull(personIndex);

        this.policyIndex = policyIndex;
        this.personIndex = personIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownPersonList = model.getFilteredPersonList();
        List<Policy> lastShownPolicyList = model.getFilteredPolicyList();

        if (policyIndex.getZeroBased() >= lastShownPolicyList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_POLICY_DISPLAYED_INDEX);
        }
        if (personIndex.getZeroBased() >= lastShownPersonList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Policy policy = lastShownPolicyList.get(policyIndex.getZeroBased());
        Person person = lastShownPersonList.get(personIndex.getZeroBased());

        if (!person.hasPolicy(policy)) {
            throw new CommandException(String.format(MESSAGE_ALREADY_UNASSIGNED,
                    person.getName(), policy.getName()));
        }

        Person assignedPerson = new PersonBuilder(person).removePolicies(policy).build();

        model.setPerson(person, assignedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        model.updateFilteredPolicyList(PREDICATE_SHOW_ALL_POLICIES);
        return new CommandResult(String.format(MESSAGE_UNASSIGN_POLICY_SUCCESS,
                policy.getName(), assignedPerson.getName()));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof UnassignPolicyCommand)) {
            return false;
        }

        // state check
        UnassignPolicyCommand e = (UnassignPolicyCommand) other;
        return personIndex.equals(e.personIndex)
                && policyIndex.equals(e.policyIndex);
    }

}
