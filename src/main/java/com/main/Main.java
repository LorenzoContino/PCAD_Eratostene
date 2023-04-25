package com.main;

import com.eratostene_sieve.Sieve;

public class Main {
    public static void main(String[] args) {
        Sieve mysieve = new Sieve();
        var prime_list_20 = mysieve.doSieve(20);
        var prime_list_100 = mysieve.doSieve(100);
        var prime_list_500 = mysieve.doSieve(500);
        var prime_list_1000 = mysieve.doSieve(1000);
        var prime_list_10000 = mysieve.doSieve(10000);
        System.out.println("=== Sieve ===================================================");
        System.out.println("Max Number = 20");
        System.out.println(" - Primes list: " + prime_list_20.toString());
        System.out.println(" - Primes count: " + prime_list_20.size());
        System.out.println("=============================================================");
        System.out.println("Max Number = 100");
        System.out.println(" - Primes list: " + prime_list_100.toString());
        System.out.println(" - Primes count: " + prime_list_100.size());
        System.out.println("=============================================================");
        System.out.println("Max Number = 500");
        System.out.println(" - Primes list: " + prime_list_500.toString());
        System.out.println(" - Primes count: " + prime_list_500.size());
        System.out.println("=============================================================");
        System.out.println("Max Number = 1000");
        System.out.println(" - Primes list: <List is too long>");
        System.out.println(" - Primes count: " + prime_list_1000.size());
        System.out.println("=============================================================");
        System.out.println("Max Number = 10000");
        System.out.println(" - Primes list: <List is too long>");
        System.out.println(" - Primes count: " + prime_list_10000.size());
        System.out.println("=============================================================");
    }
}
