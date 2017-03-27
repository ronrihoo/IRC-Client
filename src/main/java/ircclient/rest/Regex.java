package ircclient.rest;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regex {

    String numericPattern = null;
    String zipCodePattern = null;

    public Regex(String numericPattern, String zipCodePattern) {
        this.numericPattern = numericPattern;
        this.zipCodePattern = zipCodePattern;
    }

    public String testZipCode(String str) {
        Pattern pattern = Pattern.compile(numericPattern);
        Matcher matcher = pattern.matcher(str);
        if (matcher.find()) {
            str = extractZipCode(str, matcher);
        }
        pattern = Pattern.compile(zipCodePattern);
        matcher = pattern.matcher(str);
        int length = 0;
        if (matcher.find()) {
            return extractZipCode(str, matcher);
        } else {
            length = str.length();
            for (String s : str.split(" ")) {
                matcher = pattern.matcher(s.split("-")[0]);
                if (matcher.find()) {
                    str = s;
                    return extractZipCode(s, matcher);
                }
            }
            if (str.length() == length) {
                str = str.substring(0, 5);
                matcher = pattern.matcher(str);
                if (matcher.find()) {
                    return str;
                } else {
                    return null;
                }
            }
        }
        return "";
    }

    public String cleanZipCode(String str) {
        if (str.length() > 0 && str.toCharArray()[str.length() - 1] == '-') {
            str = str.substring(0, str.length() - 1);
        }
        return str.replace(" ", "").replace("--", "-");
    }

    public String extractZipCode(String str, Matcher matcher) {
        String s = str.substring(matcher.start(), matcher.end());
        return cleanZipCode(s);
    }

}
