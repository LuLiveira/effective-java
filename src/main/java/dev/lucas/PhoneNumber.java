package dev.lucas;


import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;

import java.util.Comparator;
import java.util.Formattable;
import java.util.Formatter;
import java.util.Objects;

/**
 * This class is a Value Object
 */
public class PhoneNumber implements Formattable, Comparable<PhoneNumber> {

    /**
     * Comparator can be a unique instance because it is Thread Safe
     */
    private static final Comparator<PhoneNumber> COMPARATOR = Comparator.comparingInt(PhoneNumber::getAreaCode)
            .thenComparingInt(PhoneNumber::getNumber);
    private final int areaCode;
    private final int number;

    private PhoneNumber(int areaCode, int number) {
        this.areaCode = areaCode;
        this.number = number;
    }

    /**
     * Factory Method
     * @param areaCode Area code of your region
     * @param number Number of your telephone
     * @return PhoneNumber
     */
    public static PhoneNumber of(int areaCode, int number) {
        Preconditions.checkArgument(areaCode > 0, "areaCode must be greater than 0");
        Preconditions.checkArgument(number > 0, "number must be greater than 0");
        return new PhoneNumber(areaCode, number);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PhoneNumber that = (PhoneNumber) o;
        return areaCode == that.areaCode && number == that.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(areaCode, number);
    }

/*
    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("areaCode", areaCode)
                .add("number", number)
                .toString();
    }
*/

    @Override
    public String toString() {
        return "PhoneNumber{" +
                "areaCode=" + areaCode +
                ", number=" + number +
                '}';
    }


    @Override
    public void formatTo(Formatter formatter, int flags, int width, int precision) {
        formatter.format("(%d) %d", areaCode, number);
    }

    public int getAreaCode() {
        return areaCode;
    }

    public int getNumber() {
        return number;
    }

    @Override
    public int compareTo(PhoneNumber o) {
        return COMPARATOR.compare(this, o);
    }
}
