package com.knubisoft;

import java.time.LocalDate;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Randomizer {

    public int setRandomInt(){
        return (int)(Math.random() * 100);
    }

    public double serRandomDouble(){
        double v = Math.random() * 2000;
        return Math.round(v);
    }

    public long setRandomLong(){
        return (long)(Math.random() * 10000000);
    }

    public float setRandomFloat(){
        return (float)(Math.random() * 10000F);
    }

    public boolean setRandomBoolean(){
        Random random = new Random();
        return random.nextBoolean();
    }

    public LocalDate setRandomDate(){
        long minDay = LocalDate.of(1970, 1, 1).toEpochDay();
        long maxDay = LocalDate.of(2015, 12, 31).toEpochDay();
        long randomDay = ThreadLocalRandom.current().nextLong(minDay, maxDay);
        return LocalDate.ofEpochDay(randomDay);
    }

        public String setRandomString() {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
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
