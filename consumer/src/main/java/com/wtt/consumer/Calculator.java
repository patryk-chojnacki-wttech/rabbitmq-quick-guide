package com.wtt.consumer;

import org.springframework.stereotype.Service;

import java.text.DecimalFormat;

@Service
class Calculator {

    void add(final RandomNumberPair pair) {
        System.out.println(pair + " after add = " + Math.addExact(pair.getX(), pair.getY()));
    }

    void subtract(final RandomNumberPair pair) {
        System.out.println(pair + " after subtract = " + Math.subtractExact(pair.getX(), pair.getY()));
    }

    void divide(final RandomNumberPair pair) {
        DecimalFormat decimalFormat = new DecimalFormat("#.00");
        System.out.println(pair + " after divide = " + decimalFormat.format((double) pair.getX() / pair.getY()));
    }

    void multiplication(final RandomNumberPair pair) {
        System.out.println(pair + " after multiplication = " + Math.multiplyExact(pair.getX(), pair.getY()));
    }
}
