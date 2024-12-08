package com.purpleprint.betterplayerrespawn.utils;

public class ASMUtils {


    /**
     * Type to field desc
     *
     * @return desc version
     */
    public static String ASM$asObjectDesc(String... multiple) {
        StringBuilder builder = new StringBuilder();
        for (String type : multiple) {
            builder.append(ASM$asObjectDesc(type));
        }
        return builder.toString();
    }

    /**
     * Type to field desc
     *
     * @return desc version
     */
    public static String ASM$asObjectDesc(String type) {
        return "L" + type + ";";
    }
}
