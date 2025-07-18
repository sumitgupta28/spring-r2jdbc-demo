package com.spring.react.demo;

import java.math.BigInteger;
import java.util.Random;

public class PrimeGenerator implements Runnable {


    private final ResultClass resultClass;

    public PrimeGenerator(ResultClass resultClass)
    {
        this.resultClass = resultClass;
    }

    @Override
    public void run() {
        BigInteger bigInteger = new BigInteger(2000,new Random());
        resultClass.getPrimes().add(bigInteger);
    }
}
