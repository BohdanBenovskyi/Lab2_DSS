package com.benovskyi.bohdan;

import java.util.*;
import java.math.*;

public class DSS {
	public static BigInteger p;
	public static BigInteger g;
	public static BigInteger q;

	public static void main(String[] args) {
		gen_pgq();
	}
	
	public static void gen_pgq() {
		Random rnd = new Random();
		int bitLength = 20;
		p = BigInteger.probablePrime(bitLength, rnd);
		g = BigInteger.probablePrime(bitLength, rnd);
		
		
		q = new BigInteger("2");
		
		while(q.compareTo(p.subtract(BigInteger.ONE)) == -1) {
			q = q.add(BigInteger.ONE);
			
			if(q.isProbablePrime(1)) {
				BigInteger tmp_mod = (p.subtract(BigInteger.ONE)).mod(q);
				if(tmp_mod.equals(BigInteger.ZERO)) {
					break;
				}
			}
			else {
				continue; 
			}
		}
		
		System.out.println("p = " + p);
		System.out.println("g = " + g);
		System.out.println("q = " + q);
	}

}
