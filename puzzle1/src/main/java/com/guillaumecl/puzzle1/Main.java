package com.guillaumecl.puzzle1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

	static void part1() throws IOException {
		List<String> lines = Files.readAllLines(Paths.get("sources.txt"));
		int sum = lines.stream()
				.mapToInt(Integer::parseInt)
				.sum();
		System.out.println("Sum: " + sum);
	}

	static void part2() throws IOException {
		List<String> lines = Files.readAllLines(Paths.get("sources.txt"));
		List<Integer> offsets = lines.stream().map(Integer::parseInt).collect(Collectors.toList());
		Set<Integer> foundNumbers = new HashSet<>();
		int numOffsets = offsets.size();
		int idx = 0;
		int sum = 0;
		while (true) {
			int offset = offsets.get(idx);
			sum += offset;
			if (foundNumbers.contains(sum)) {
				System.out.println("FOUND: " + sum);
				return;
			} else {
				foundNumbers.add(sum);
			}
			idx = (idx + 1) % numOffsets;
		}
	}

	public static void main(String[] args) {
		try {
			part2();
		} catch (IOException e) {
			System.err.println(e);
		}
	}
}
