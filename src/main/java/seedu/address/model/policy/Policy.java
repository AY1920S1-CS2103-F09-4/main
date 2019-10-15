package seedu.address.model.policy;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.tag.Tag;

/**
 * Represents a Policy in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Policy {

    public static final String MESSAGE_CONSTRAINTS =
            "This policy is not available.";

    public static final String DATA_TYPE = "POLICY";

    private final PolicyName name;
    private final Description description;
    private final Coverage coverage;
    private final Price price;
    private final StartAge startAge;
    private final EndAge endAge;

    private final Set<Tag> criteria = new HashSet<>();
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Policy(PolicyName name, Description description, Coverage coverage, Price price, StartAge startAge,
                  EndAge endAge, Set<Tag> criteria, Set<Tag> tags) {
        requireAllNonNull(name, description, coverage, price, startAge, endAge, criteria, tags);
        this.name = name;
        this.description = description;
        this.coverage = coverage;
        this.price = price;
        this.startAge = startAge;
        this.endAge = endAge;
        this.criteria.addAll(criteria);
        this.tags.addAll(tags);
    }

    /**
     * Creates a copy of the policy {@code toCopy}.
     */
    public static Policy createCopy(Policy toCopy) {
        return new Policy(
            toCopy.getName(),
            toCopy.getDescription(),
            toCopy.getCoverage(),
            toCopy.getPrice(),
            toCopy.getStartAge(),
            toCopy.getEndAge(),
            new HashSet<>(toCopy.getCriteria()),
            new HashSet<>(toCopy.getTags())
        );
    }

    public PolicyName getName() {
        return name;
    }

    public Description getDescription() {
        return description;
    }

    public Coverage getCoverage() {
        return coverage;
    }

    public Price getPrice() {
        return price;
    }

    public StartAge getStartAge() {
        return startAge;
    }

    public EndAge getEndAge() {
        return endAge;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns an immutable criteria set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getCriteria() {
        return Collections.unmodifiableSet(criteria);
    }

    /**
     * Returns true if both policies of the same name have at least one other identity field that is the same.
     * This defines a weaker notion of equality between two policies.
     */
    public boolean isSamePolicy(Policy otherPolicy) {
        if (otherPolicy == this) {
            return true;
        }

        return otherPolicy != null
                && otherPolicy.getName().equals(getName());
    }

    /**
     * Returns true if both policies have the same identity and data fields.
     * This defines a stronger notion of equality between two policies.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Policy)) {
            return false;
        }

        Policy otherPolicy = (Policy) other;
        return otherPolicy.getName().equals(getName())
                && otherPolicy.getDescription().equals(getDescription())
                && otherPolicy.getCoverage().equals(getCoverage())
                && otherPolicy.getPrice().equals(getPrice())
                && otherPolicy.getStartAge().equals(getStartAge())
                && otherPolicy.getEndAge().equals(getEndAge())
                && otherPolicy.getCriteria().equals(getCriteria())
                && otherPolicy.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, description, coverage, price, startAge, endAge, tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append(" Description: ")
                .append(getDescription())
                .append(" Coverage: ")
                .append(getCoverage())
                .append(" Price: ")
                .append(getPrice());
        if (!startAge.getAge().equals(StartAge.AGE_ZERO)) {
            builder.append(" Start Age: ")
                    .append(getStartAge());
        }
        if (!endAge.getAge().equals(EndAge.AGE_INFINITY)) {
            builder.append(" End Age: ")
                    .append(getEndAge());
        }
        if (getCriteria().size() != 0) {
            builder.append(" Criteria: ");
            getCriteria().forEach(builder::append);
        }
        if (getTags().size() != 0) {
            builder.append(" Tags: ");
            getTags().forEach(builder::append);
        }
        return builder.toString();
    }
}
