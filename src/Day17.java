public class Day17
{
	public static int[] containers = new int[] { 11, 30, 47, 31, 32, 36, 3, 1, 5, 3, 32, 36, 15, 11, 46, 26, 28, 1, 19, 3 };
	public static int minBits = -1;

	public static void main(String[] args)
	{
		int[] bits = new int[containers.length];
		System.out.println("Part 1: " + getNumCombinations(bits, 0, -1));
		System.out.println("Part 2: " + getNumCombinations(bits, 0, getMinBits(bits, 0)));
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
		for (int x = 0; x < containers.length; x++)
		{
			total += bits[x] * containers[x];
		}
		return total == 150;
	}
}