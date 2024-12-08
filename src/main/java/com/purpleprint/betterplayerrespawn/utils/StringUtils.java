package com.purpleprint.betterplayerrespawn.utils;

public class StringUtils {

    /**
     * Check if checkList contains value or not
     * @param value value to check
     * @param checkList check list
     * @return true if contains, else false
     */
    public static boolean String$isAnyEqual(String value, String... checkList) {
        for (String toCheck : checkList) {
            if (toCheck.equals(value)) return true;
        }

        return false;
    }

}
