import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Day5
{
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new FileReader(new File("").getAbsolutePath() + "/bin/day5.txt"));
		String input;
		int part1 = 0;
		int part2 = 0;
		do
		{
			input = br.readLine();
			if (input != null)
			{
				if (checkPart1(input))
					part1++;
				if (checkPart2(input))
					part2++;
			}
		}
		while (input != null);
		br.close();

		System.out.println("Part 1: " + part1);
		System.out.println("Part 2: " + part2);
	}

	public static boolean checkPart1(String input)
	{
		return input.matches(".*[aeiou].*[aeiou].*[aeiou].*") && input.matches(".*(.)\\1.*") && !input.matches(".*(ab|cd|pq|xy).*");
	}

	public static boolean checkPart2(String input)
	{
		return input.matches(".*(..).*\\1.*") && input.matches(".*(.).\\1.*");
	}
}
