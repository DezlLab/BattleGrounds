package test;

import java.util.regex.Pattern;

public class RegexTest {
	public static void main(String[] args) {
		System.out.println(Pattern.matches("^[ \\t]+public static void main\\(String\\[\\] args\\)", "  public static void main(String[] args)"));
	}
}
