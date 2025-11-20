/** Functions for checking if a given string is an anagram. */
public class Anagram {
	public static void main(String args[]) {
		// Tests the isAnagram function.
		System.out.println(isAnagram("silent","listen"));  // true
		System.out.println(isAnagram("William Shakespeare","I am a weakish speller")); // true
		System.out.println(isAnagram("Madam Curie","Radium came")); // true
		System.out.println(isAnagram("Tom Marvolo Riddle","I am Lord Voldemort")); // true

		// Tests the preProcess function.
		System.out.println(preProcess("What? No way!!!"));
		
		// Tests the randomAnagram function.
		System.out.println("silent and " + randomAnagram("silent") + " are anagrams.");
		
		// Performs a stress test of randomAnagram 
		String str = "1234567";
		Boolean pass = true;
		//// 10 can be changed to much larger values, like 1000
		for (int i = 0; i < 10; i++) {
			String randomAnagram = randomAnagram(str);
			System.out.println(randomAnagram);
			pass = pass && isAnagram(str, randomAnagram);
			if (!pass) break;
		}
		System.out.println(pass ? "test passed" : "test Failed");
	}  

	// Returns true if the two given strings are anagrams, false otherwise.
	public static boolean isAnagram(String str1, String str2) {
		// Preprocess both strings (remove non-letters and lowercase)
		String s1 = preProcess(str1);
		String s2 = preProcess(str2);
		// Quick length check
		if (s1.length() != s2.length()) return false;
		int n = s1.length();
		boolean[] used = new boolean[n];
		for (int i = 0; i < n; i++) {
			char c = s1.charAt(i);
			boolean found = false;
			for (int j = 0; j < n; j++) {
				if (!used[j] && s2.charAt(j) == c) {
					used[j] = true;
					found = true;
					break;
				}
			}
			if (!found) return false;
		}
		return true;
	}
	   
	// Returns a preprocessed version of the given string: all the letter characters are converted
	// to lower-case, and all the other characters are deleted, except for spaces, which are left
	// as is. For example, the string "What? No way!" becomes "whatnoway"
	public static String preProcess(String str) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			if (Character.isLetter(c)) {
				sb.append(Character.toLowerCase(c));
			}
			// else: skip digits, punctuation and spaces
		}
		return sb.toString();
	} 
	   
	// Returns a random anagram of the given string. The random anagram consists of the same
	// characters as the given string, re-arranged in a random order. 
	public static String randomAnagram(String str) {
		// Work on a mutable copy
		StringBuilder sb = new StringBuilder(str);
		StringBuilder out = new StringBuilder();
		java.util.Random rnd = new java.util.Random();
		while (sb.length() > 0) {
			int idx = rnd.nextInt(sb.length());
			out.append(sb.charAt(idx));
			sb.deleteCharAt(idx);
		}
		return out.toString();
	}
}
