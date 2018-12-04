package com.guillaumecl.puzzle3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

	static void part1(List<String> lines) {
		List<Claim> claims = lines.stream().map(Claim::parseFrom).collect(Collectors.toList());
		//int maxX = claims.stream().mapToInt(claim -> claim.left + claim.width).max().orElse(0);
		//int maxY = claims.stream().mapToInt(claim -> claim.top + claim.height).max().orElse(0);
		//System.out.println("[" + maxX + ", " + maxY + "], count: " + claims.size());
		int[] map = new int[1000 * 1000];
		Arrays.fill(map, 0);
		for (Claim claim : claims) {
			for (int y = claim.top; y < claim.top + claim.height; y++) {
				for (int x = claim.left; x < claim.left + claim.width; x++) {
					map[x + y * 1000]++;
				}
			}
		}

		int count = 0;
		for (int idx = 0; idx < 1000 * 1000; idx++) {
			if (map[idx] > 1) {
				count++;
			}
		}

		System.out.println("Overlap: " + count);
	}

	static boolean verifyClaim(int[] map, Claim claim) {
		for (int y = claim.top; y < claim.top + claim.height; y++) {
			for (int x = claim.left; x < claim.left + claim.width; x++) {
				if (map[x + y * 1000] != 1) {
					return false;
				}
			}
		}
		return true;
	}

	static void part2(List<String> lines) {
		// apply the claims
		List<Claim> claims = lines.stream().map(Claim::parseFrom).collect(Collectors.toList());
		int[] map = new int[1000 * 1000];
		Arrays.fill(map, 0);
		for (Claim claim : claims) {
			for (int y = claim.top; y < claim.top + claim.height; y++) {
				for (int x = claim.left; x < claim.left + claim.width; x++) {
					map[x + y * 1000]++;
				}
			}
		}

		// find the claim the coords correspond to
		List<Claim> zeroOverlapClaims = claims.stream().filter(claim -> verifyClaim(map, claim)).collect(Collectors.toList());
		System.out.println("Found " + zeroOverlapClaims.size() + " claims.");
		zeroOverlapClaims.forEach(claim -> System.out.println(claim.toString()));

	}

	public static void main(String[] args) {
		try {
			part2(Files.readAllLines(Paths.get("sources.txt")));
		} catch (IOException e) {
			System.err.println(e);
		}
	}
}
