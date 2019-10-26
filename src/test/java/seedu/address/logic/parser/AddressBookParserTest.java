package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.logic.parser.CliSyntax.PREFIX_OFF;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ON;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.util.PersonBuilder;
import seedu.address.commons.util.PolicyBuilder;
import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.AddPolicyCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListPeopleCommand;
import seedu.address.logic.commands.SuggestionCommand;
import seedu.address.logic.commands.SuggestionSwitchCommand;
import seedu.address.logic.commands.merge.MergePersonCommand;
import seedu.address.logic.commands.merge.MergePolicyCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.model.policy.Policy;
import seedu.address.testutil.EditPersonDescriptorBuilder;
import seedu.address.testutil.PersonUtil;
import seedu.address.testutil.PolicyUtil;

public class AddressBookParserTest {

    private final AddressBookParser parser = new AddressBookParser();

    @Test
    public void parseCommand_add() throws Exception {
        Person person = new PersonBuilder().withPolicies().withTags().build();
        AddCommand command = (AddCommand) parser.parseCommand(PersonUtil.getAddCommand(person));
        assertEquals(new AddCommand(person), command);
    }

    @Test
    public void parseCommand_addpolicy() throws Exception {
        Policy policy = new PolicyBuilder().withTags().withCriteria().build();
        AddPolicyCommand command = (AddPolicyCommand) parser.parseCommand(PolicyUtil.getAddPolicyCommand(policy));
        assertEquals(new AddPolicyCommand(policy), command);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " 3") instanceof ClearCommand);
    }

    @Test
    public void parseCommand_delete() throws Exception {
        DeleteCommand command = (DeleteCommand) parser.parseCommand(
            DeleteCommand.COMMAND_WORD + " " + INDEX_FIRST_PERSON.getOneBased());
        assertEquals(new DeleteCommand(INDEX_FIRST_PERSON), command);
    }

    @Test
    public void parseCommand_edit() throws Exception {
        Person person = new PersonBuilder().build();
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(person).build();
        EditCommand command = (EditCommand) parser.parseCommand(EditCommand.COMMAND_WORD + " "
            + INDEX_FIRST_PERSON.getOneBased() + " " + PersonUtil.getEditPersonDescriptorDetails(descriptor));
        assertEquals(new EditCommand(INDEX_FIRST_PERSON, descriptor), command);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }

    @Test
    public void parseCommand_find() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FindCommand command = (FindCommand) parser.parseCommand(
            FindCommand.COMMAND_WORD + " " + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new FindCommand(new NameContainsKeywordsPredicate(keywords)), command);
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " 3") instanceof HelpCommand);
    }

    @Test
    public void parseCommand_list() throws Exception {
        assertTrue(parser.parseCommand(ListPeopleCommand.COMMAND_WORD) instanceof ListPeopleCommand);
        assertTrue(parser.parseCommand(ListPeopleCommand.COMMAND_WORD + " 3") instanceof ListPeopleCommand);
    }

    /**
     * A new parser is created for this method as the parsing of a merge person command changes the state of the
     * AddressBookParser.
     *
     */
    @Test
    public void parseCommand_mergePerson() throws Exception {
        AddressBookParser parser = new AddressBookParser();
        Person person = new PersonBuilder().withPolicies().withTags().build();
        assertTrue(parser.parseCommand(MergePersonCommand.COMMAND_WORD + " "
                + PersonUtil.getAddCommand(person), true) instanceof MergePersonCommand);
    }

    /**
     * A new parser is created for this method as the parsing of a merge person command changes the state of the
     * AddressBookParser.
     *
     */
    @Test
    public void parseCommand_mergePolicy() throws Exception {
        AddressBookParser parser = new AddressBookParser();
        Policy policy = new PolicyBuilder().withTags().withCriteria().build();
        assertTrue(parser.parseCommand(MergePolicyCommand.COMMAND_WORD + " "
                + PolicyUtil.getAddPolicyCommand(policy), true)
                instanceof MergePolicyCommand);
    }


    @Test
    public void parseCommand_originalSuggestionOn_switchOff() throws Exception {
        AddressBookParser parser = new AddressBookParser();
        String switchOffCommand = SuggestionSwitchCommand.COMMAND_WORD + " " + PREFIX_OFF;
        assertTrue(parser.parseCommand(switchOffCommand) instanceof SuggestionSwitchCommand);
        String invalidCommand = "asdf";
        assertThrows(ParseException.class, () -> parser.parseCommand(invalidCommand));
    }

    @Test
    public void parseCommand_originalSuggestionOff_switchOff() throws Exception {
        AddressBookParser parser = new AddressBookParser(false);
        String switchOffCommand = SuggestionSwitchCommand.COMMAND_WORD + " " + PREFIX_OFF;
        assertTrue(parser.parseCommand(switchOffCommand) instanceof SuggestionSwitchCommand);
        String invalidCommand = "asdf";
        assertThrows(ParseException.class, () -> parser.parseCommand(invalidCommand));
    }

    @Test
    public void parseCommand_originalSuggestionOn_switchOn() throws Exception {
        AddressBookParser parser = new AddressBookParser();
        String switchOnCommand = SuggestionSwitchCommand.COMMAND_WORD + " " + PREFIX_ON;
        assertTrue(parser.parseCommand(switchOnCommand) instanceof SuggestionSwitchCommand);
        String invalidCommand = "asdf";
        assertTrue(parser.parseCommand(invalidCommand) instanceof SuggestionCommand);
    }

    @Test
    public void parseCommand_originalSuggestionOff_switchOn() throws Exception {
        AddressBookParser parser = new AddressBookParser(false);
        String switchOnCommand = SuggestionSwitchCommand.COMMAND_WORD + " " + PREFIX_ON;
        assertTrue(parser.parseCommand(switchOnCommand) instanceof SuggestionSwitchCommand);
        String invalidCommand = "asdf";
        assertTrue(parser.parseCommand(invalidCommand) instanceof SuggestionCommand);
    }

    @Test
    public void parseCommandWithoutSuggestions_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE), ()
            -> parser.parseCommand(""));
    }

    @Test
    public void parseCommandWithoutSuggestions_unknownCommand_throwsParseException() {
        AddressBookParser parserWithoutSuggestions = new AddressBookParser(false);
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, (
            ) -> parserWithoutSuggestions.parseCommand("unknownCommand"));
    }

    @Test
    public void parseCommandWithSuggestions_emptyString_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE), ()
            -> parser.parseCommand(""));
    }

    @Test
    public void parseCommandWithSuggestions_unknownCommand_success() throws Exception {
        assertTrue(parser.parseCommand("unknownCommand") instanceof SuggestionCommand);
    }
}
