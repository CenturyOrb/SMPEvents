package com.rosed.sMPEvents.Utils;

import java.util.Random;

public class Util {

    /**
     * Selecting a random enum
     * @param enumClass Enum class with enums
     * @return random Enum of that class
     */
    public static <E extends Enum<E>> E getRandomEnum(Class<E> enumClass) {
        Random random = new Random();
        E[] enumConstants = enumClass.getEnumConstants();
        return enumConstants[random.nextInt(enumConstants.length)];
    }

}
