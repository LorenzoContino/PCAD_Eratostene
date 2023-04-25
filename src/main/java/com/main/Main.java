package com.main;

import com.eratostene_sieve.Sieve;

public class Main {
    public static void main(String[] args) {
        Sieve mysieve = new Sieve();
        var prime_list = mysieve.doSieve(10000);
        System.out.println(prime_list.toString());
        System.out.println(prime_list.size());
        // var prime_list_2 = mysieve.doSieve(10);
        // System.out.println(prime_list_2.toString());
    }
}
