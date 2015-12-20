import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Day20
{
	public static void main(String[] args) throws NumberFormatException, IOException
	{
		BufferedReader br = new BufferedReader(new FileReader(new File("").getAbsolutePath() + "/bin/day20.txt"));
		int minNumber = Integer.parseInt(br.readLine());
		br.close();

		int a = 1;
		while (getSumOfFactors(a) < minNumber / 10)
		{
			a++;
		}
		System.out.println("Part 1: " + a);

		int b = 1;
		while (getSumOfFactorsPart2(b) < minNumber / 11)
		{
			b++;
		}
		System.out.println("Part 2: " + b);
	}

	/*
	 * 	The sum of factors consists of sums of pairs (x, num / x).
	 * 	This means that we only need to iterate up to sqrt(num).
	 */
	public static int getSumOfFactors(int num)
	{
		int total = 0;
		int root = (int)Math.sqrt(num);
		for (int x = 1; x <= root; x++)
		{
			if (x * x == num)
			{
				total += x;
			}
			else if (num % x == 0)
			{
				total += x + num / x;
			}
		}
		return total;
	}

	/*
	 * 	Each elf only delivers to 50 houses.
	 *	So for house h and elf e, h <= 50 * e
	 */
	public static int getSumOfFactorsPart2(int h)
	{
		int total = 0;
		int root = (int)Math.sqrt(h);
		for (int e = 1; e <= root; e++)
		{
			if (e * e == h && e > 50)
			{
				total += e;
			}
			else if (h % e == 0)
			{
				if (h <= 50 * e)
				{
					total += e;
				}
				if (e <= 50)
				{
					total += h / e;
				}
			}
		}
		return total;
	}
}
