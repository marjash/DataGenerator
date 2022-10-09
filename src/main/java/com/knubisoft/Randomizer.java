package com.knubisoft;

import java.time.LocalDate;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Helper class for generating random data
 */
public class Randomizer {
    /**
     * Set random int
     * @return Random int value
     */
    public int setRandomInt(){
        return (int)(Math.random() * 100);
    }

    /**
     * Set random double
     * @return Random double value
     */
    public double serRandomDouble(){
        double v = Math.random() * 2000;
        return Math.round(v);
    }

    /**
     * Set random long
     * @return Random long value
     */
    public long setRandomLong(){
        return (long)(Math.random() * 10000000);
    }

    /**
     * Set random float
     * @return Random float value
     */
    public float setRandomFloat(){
        return (float)(Math.random() * 10000F);
    }

    /**
     * Set random boolean
     * @return Random boolean value
     */

    public boolean setRandomBoolean(){
        Random random = new Random();
        return random.nextBoolean();
    }

    /**
     * Set random date
     * @return Random date value
     */
    public LocalDate setRandomDate(){
        long minDay = LocalDate.of(1970, 1, 1).toEpochDay();
        long maxDay = LocalDate.of(2015, 12, 31).toEpochDay();
        long randomDay = ThreadLocalRandom.current().nextLong(minDay, maxDay);
        return LocalDate.ofEpochDay(randomDay);
    }

    /**
     * Set random String
     * @return Random String value
     */
        public String setRandomString() {
        int leftLimit = 97;
        int rightLimit = 122;
        int targetStringLength = 10;
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int)
                    (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        return buffer.toString();
    }
}
