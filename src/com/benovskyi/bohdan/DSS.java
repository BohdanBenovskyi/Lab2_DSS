package com.benovskyi.bohdan;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.math.*;

public class DSS {
	public static BigInteger p;
	public static BigInteger g;
	public static BigInteger q;
	public static BigInteger y;

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.println("Enter your message: ");
		String msg = in.nextLine();
		
		gen_pgq();
		
		//int x = rnd.nextInt(q.intValue());
		int x = ThreadLocalRandom.current().nextInt(1, q.intValue());
		System.out.println("x = " + x);
		calc_Y(x);
		
		BigInteger m = calcIntHash(msg);
		
		int k = ThreadLocalRandom.current().nextInt(1, q.intValue());
		System.out.println("k = " + k);
		int r = calcR(k);
		
		int s = calcS(x, r, k, m);
		
		if(r == 0 | s == 0) {
			while(r == 0 || s == 0) {
				System.out.println("\t\t\tПерегенерація");
				k = ThreadLocalRandom.current().nextInt(1, q.intValue());
				r = calcR(k);
				s = calcS(x, r, k, m);
			}
		}
		
		System.out.println("Your message: " + msg);
		System.out.println("\tr: " + r);
		System.out.println("\ts: " + s);
		
	}
	
	public static void gen_pgq() {
		Random rnd = new Random();
		int bitLength = 25;
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
	
	public static void calc_Y(int x) {
		BigInteger tmp_1 = g.pow(x);
		y = tmp_1.mod(p);
		System.out.println("y = " + y);
	}
	
	public static BigInteger calcIntHash(String msg) {
		BigInteger hash;
		int h = 0;
		for(int i = 0; i < msg.length(); i++) {
			h += msg.codePointAt(i);
		}
		hash = new BigInteger(String.valueOf(h));
		hash = hash.mod(q);
		System.out.println("hash = " + hash);
		return hash;
	}
	
	public static int calcR(int k) {
		BigInteger tmp1 = g.pow(k);
		BigInteger tmp2 = tmp1.mod(p);
		BigInteger tmp3 = tmp2.mod(q);
		int r  = tmp3.intValue();
		return r;
	}
	
	public static int calcS(int x, int r, int k, BigInteger m) {
		int tmp1 = (m.intValue() + (r * x))/k;
		BigInteger tmp2 = new BigInteger(String.valueOf(tmp1));
		tmp2 = tmp2.mod(q);
		int s = tmp2.intValue();
		return s;
	}

}
