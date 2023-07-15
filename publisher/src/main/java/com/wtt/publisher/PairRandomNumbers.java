package com.wtt.publisher;

import java.util.Random;
import java.util.UUID;

public class PairRandomNumbers {

    private static final Random random = new Random();
    private final UUID id;
    private final long x;
    private final long y;

    public PairRandomNumbers() {
        id = UUID.randomUUID();
        this.x = random.nextInt(100) + 1;
        this.y = random.nextInt(100) + 1;
    }

    public UUID getId() {
        return id;
    }

    public long getX() {
        return x;
    }

    public long getY() {
        return y;
    }

    @Override
    public String toString() {
        return "Random Pair number with ID: " + id + " and values X: " + x + ", Y: " + y;
    }
}
