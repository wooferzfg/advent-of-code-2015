import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Day11
{
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new FileReader(new File("").getAbsolutePath() + "/bin/day11.txt"));
		String password = br.readLine();
		br.close();
		String part1Answer = getPassword(password);
		System.out.println("Part 1: " + part1Answer);
		System.out.println("Part 2: " + getPassword(part1Answer));
	}

	public static String getPassword(String original)
	{
		char[] pass = original.toCharArray();
		do
		{
			increment(pass);
		}
		while (!checkPass(pass));
		return new String(pass);
	}

	public static boolean checkPass(char[] pass)
	{
		return checkTriple(pass) && checkNoIOL(pass) && checkTwoPairs(pass);
	}

	public static boolean checkTriple(char[] pass)
	{
		char prev2 = pass[0];
		char prev = pass[1];
		for (int x = 2; x <= 7; x++)
		{
			if (pass[x] == prev + 1 && prev == prev2 + 1)
				return true;
			prev2 = prev;
			prev = pass[x];
		}
		return false;
	}

	public static boolean checkNoIOL(char[] pass)
	{
		for (int x = 0; x <= 7; x++)
		{
			char cur = pass[x];
			if (cur == 'i' || cur == 'o' || cur == 'l')
				return false;
		}
		return true;
	}

	public static boolean checkTwoPairs(char[] pass)
	{
		char prev = pass[0];
		int pairs = 0;
		for (int x = 1; x <= 7; x++)
		{
			if (pass[x] == prev)
			{
				pairs++;
				x++;
			}
			if (x <= 7)
				prev = pass[x];
		}
		return pairs >= 2;
	}

	public static void increment(char[] pass)
	{
		for (int x = 7; x >= 0; x--)
		{
			pass[x] = getNextChar(pass[x]);
			if (pass[x] != 'a')
				break;
		}
	}

	public static char getNextChar(char cur)
	{
		cur++;
		if (cur > 'z')
			cur = 'a';
		return cur;
	}
}