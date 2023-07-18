package com.wtt.consumer;

import java.util.Random;
import java.util.UUID;

public class RandomNumberPair {

    private static final Random random = new Random();
    private final UUID id;
    private final long x;
    private final long y;

    public RandomNumberPair() {
        id = UUID.randomUUID();
        this.x = random.nextInt(100) + 1;
        this.y = random.nextInt(100) + 1;
    }

    public long getX() {
        return x;
    }

    public long getY() {
        return y;
    }
    public UUID getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Random Pair number with ID: " + id + " and values X: " + x + ", Y: " + y;
    }

}
