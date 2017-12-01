package com.benovskyi.bohdan;

import java.util.*;
import java.math.*;

public class DSS {
	public static BigInteger q;
	public static BigInteger p;

	public static void main(String[] args) {
		gen_pq();
		gen_pq();
	}
	
	public static void gen_pq() {
		Random rnd = new Random();
		int bitLength = 15;
		p = BigInteger.probablePrime(bitLength, rnd);
		
		
		q = new BigInteger("2");
		
		System.out.println("We found p: " + p);
		while(q.compareTo(p.subtract(BigInteger.ONE)) == -1) {
			q = q.add(BigInteger.ONE);
			
			if(q.isProbablePrime(1)) {
				BigInteger tmp_mod = (p.subtract(BigInteger.ONE)).mod(q);
				if(tmp_mod.equals(BigInteger.ZERO)) {
					System.out.println("We found q: " + q);
					break;
				}
			}
			else {
				System.out.println("Not found: " + q);
				continue; 
			}
		}
		
		System.out.println("p = " + p);
		System.out.println("q = " + q);
	}

}
