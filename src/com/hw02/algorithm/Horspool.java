package com.hw02.algorithm;

import com.hw02.AAlgorithm;

public class Horspool extends AAlgorithm {
	private static int[] shiftTable = new int[ALPHABET.length];

	@Override
	public int sort(char[] text, char[] pattern) {
		prepareTable(pattern);

		// start with lenght of pattern
		int i = pattern.length - 1, k;
		isMatch: while (i < text.length) {
			k = i;
			for (int j = pattern.length - 1; j >= 0; j--) {
				if (pattern[j] == text[k]) {
					k--;
					continue;
				}
				else {
					i += shiftTable[getCharIndex(text[i])];
					continue isMatch;
				}
			}
			return k + 1;
		}

		return text.length;
	}

	public int getCharIndex(char i) {
		int j = 0;
		for (char ch : ALPHABET) {
			if (ch == i)
				return j;
			j++;
		}
		return -1;
	}

	public void prepareTable(char[] pattern) {
		for (int i = 0; i < shiftTable.length; i++) {
			shiftTable[i] = pattern.length;
		}
		for (char c : pattern) {
			shiftTable[getCharIndex(c)] = shiftCount(c, pattern);
		}
	}

	// if 'c' exist (but not last char) in pattern get index of right most character,
	// unless assign lenght of pattern to all characters
	private Integer shiftCount(char c, char[] pattern) {
		for (int i = pattern.length - 2/* ignore last char of pattern */; i >= 0; i--) {
			// rightMostIndex
			if (pattern[i] == c)
				return pattern.length - 1 - i; // m-1-n
		}
		// if doesn't match of any character in pattern return lenght of pattern
		return pattern.length;
	}
}