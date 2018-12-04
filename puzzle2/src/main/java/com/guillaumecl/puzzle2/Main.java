package com.guillaumecl.puzzle2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

	static int[] histogram(String str) {
		int[] counts = new int[26];
		for (int idx = 0; idx < 26; idx++) {
			counts[idx] = 0;
		}

		for (char c : str.toCharArray()) {
			counts[c - 'a']++;
		}

		return counts;
	}

	static boolean contains2(int[] counts) {
		for (int idx = 0; idx < 26; idx++) {
			if (counts[idx] == 2) {
				return true;
			}
		}
		return false;
	}

	static boolean contains3(int[] counts) {
		for (int idx = 0; idx < 26; idx++) {
			if (counts[idx] == 3) {
				return true;
			}
		}
		return false;
	}

	static void part1(List<String> lines) {
		List<int[]> histograms = lines.stream().map(Main::histogram).collect(Collectors.toList());
		int twos = 0;
		int threes = 0;
		for (int[] histogram : histograms) {
			if (contains2(histogram)) {
				twos++;
			}
			if (contains3(histogram)) {
				threes++;
			}
		}

		System.out.println("2s: " + twos + " 3s: " + threes);
		System.out.println("Checksum: " + (twos * threes));
	}

	static boolean hasSingleDiff(String str1, String str2) {
		char[] chars1 = str1.toCharArray();
		char[] chars2 = str2.toCharArray();

		int numChars = chars1.length;
		boolean foundDifference = false;
		for (int idx = 0; idx < numChars; idx++) {
			if (chars1[idx] != chars2[idx]) {
				if (foundDifference) {
					return false;
				}
				foundDifference = true;
			}
		}

		return foundDifference;
	}

	static void part2(List<String> lines) {
		int numLines = lines.size();
		for (int firstIdx = 0; firstIdx < numLines; firstIdx++) {
			for (int secondIdx = 0; secondIdx < numLines; secondIdx++) {
				if (firstIdx == secondIdx) {
					continue;
				}
				if (hasSingleDiff(lines.get(firstIdx), lines.get(secondIdx))) {
					System.out.println(lines.get(firstIdx));
					System.out.println(lines.get(secondIdx));
					return;
				}
			}
		}
	}

	public static void main(String[] args) {
		try {
			part2(Files.readAllLines(Paths.get("sources.txt")));
		} catch (IOException e) {
			System.err.println(e);
		}
	}
}
