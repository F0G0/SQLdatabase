package org.ngu.Controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternMatcher {
    static Pattern letter = Pattern.compile("[a-zA-zа-яА-я]");
    static Pattern digit = Pattern.compile("[0-9]");
    static Pattern special = Pattern.compile ("[!@#$%&*()_+=|<>?{}\\[\\]~-]");

    public static boolean onlyLetters(String name)
    {
        Matcher hasLetter = letter.matcher(name);
        Matcher hasDigit = digit.matcher(name);
        Matcher hasSpecial = special.matcher(name);
        return hasLetter.find() && !hasDigit.find() && !hasSpecial.find();
    }

    public static boolean onlyNumbers(String name)
    {
        Matcher hasLetter = letter.matcher(name);
        Matcher hasDigit = digit.matcher(name);
        Matcher hasSpecial = special.matcher(name);
        return !hasLetter.find() && hasDigit.find() && !hasSpecial.find();
    }

}
