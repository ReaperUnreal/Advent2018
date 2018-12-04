package com.guillaumecl.puzzle3;

import java.util.Objects;

public final class Claim {
	public int id;
	public int top;
	public int left;
	public int width;
	public int height;

	private Claim(int id, int left, int top, int width, int height) {
		this.id = id;
		this.top = top;
		this.left = left;
		this.width = width;
		this.height = height;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Claim claim = (Claim) o;
		return id == claim.id &&
				top == claim.top &&
				left == claim.left &&
				width == claim.width &&
				height == claim.height;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, top, left, width, height);
	}

	@Override
	public String toString() {
		return "Claim{" +
				"id=" + id +
				", left=" + left +
				", top=" + top +
				", width=" + width +
				", height=" + height +
				'}';
	}

	static Claim parseFrom(String str) {
		String[] parts = str.split(" ");
		int id = Integer.parseInt(parts[0].substring(1));

		int separator = parts[2].indexOf(',');
		int left = Integer.parseInt(parts[2].substring(0, separator));
		int colon = parts[2].indexOf(':');
		int top = Integer.parseInt(parts[2].substring(separator + 1, colon));

		separator = parts[3].indexOf('x');
		int width = Integer.parseInt(parts[3].substring(0, separator));
		int height = Integer.parseInt(parts[3].substring(separator + 1));

		return new Claim(id, left, top, width, height);
	}
}
