package com.spring.react.demo;

import java.math.BigInteger;
import java.util.SortedSet;
import java.util.TreeSet;

public class ResultClass {

    public SortedSet<BigInteger> getPrimes() {
        return primes;
    }

    private final SortedSet<BigInteger> primes;

    public ResultClass()
    {
        primes = new TreeSet<>();
    }


}
