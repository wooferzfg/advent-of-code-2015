import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Day17
{
	public static ArrayList<Integer> containers;
	public static int minBits = -1;

	public static void main(String[] args) throws IOException
	{
		parseInput();
		int[] bits = new int[containers.size()];
		System.out.println("Part 1: " + getNumCombinations(bits, 0, -1));
		System.out.println("Part 2: " + getNumCombinations(bits, 0, getMinBits(bits, 0)));
	}

	public static void parseInput() throws IOException
	{
		containers = new ArrayList<Integer>();
		BufferedReader br = new BufferedReader(new FileReader(new File("").getAbsolutePath() + "/bin/day17.txt"));
		String input;
		do
		{
			input = br.readLine();
			if (input != null)
			{
				containers.add(Integer.parseInt(input));
			}
		}
		while (input != null);
		br.close();
	}

	public static int getNumCombinations(int[] bits, int startIndex, int numBits)
	{
		if (startIndex == bits.length)
		{
			if (checkCombination(bits) && (numBits < 0 || countBits(bits) == numBits))
				return 1;
			return 0;
		}
		bits[startIndex] = 0;
		int combinations1 = getNumCombinations(bits, startIndex + 1, numBits);
		bits[startIndex] = 1;
		int combinations2 = getNumCombinations(bits, startIndex + 1, numBits);
		return combinations1 + combinations2;
	}

	public static int getMinBits(int[] bits, int startIndex)
	{
		if (startIndex == bits.length)
		{
			if (checkCombination(bits))
				return countBits(bits);
			return Integer.MAX_VALUE;
		}
		bits[startIndex] = 0;
		int combinations1 = getMinBits(bits, startIndex + 1);
		bits[startIndex] = 1;
		int combinations2 = getMinBits(bits, startIndex + 1);
		return Math.min(combinations1, combinations2);
	}

	public static int countBits(int[] bits)
	{
		int total = 0;
		for (int x = 0; x < bits.length; x++)
		{
			total += bits[x];
		}
		return total;
	}

	public static boolean checkCombination(int[] bits)
	{
		int total = 0;
		for (int x = 0; x < bits.length; x++)
		{
			total += bits[x] * containers.get(x);
		}
		return total == 150;
	}
}