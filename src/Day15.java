import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Day15
{
	public static int[][] stats = new int[4][5];

	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new FileReader(new File("").getAbsolutePath() + "/bin/day15.txt"));
		String input;
		int n = 0;
		do
		{
			input = br.readLine();
			if (input != null)
			{
				parseLine(input, n);
			}
			n++;
		}
		while (input != null);
		br.close();

		System.out.println("Part 1: " + getMaxScore(false));
		System.out.println("Part 2: " + getMaxScore(true));
	}

	public static int getMaxScore(boolean part2)
	{
		int maxScore = 0;
		for (int a = 0; a <= 100; a++)
		{
			for (int b = 0; b <= 100 - a; b++)
			{
				for (int c = 0; c <= 100 - a - b; c++)
				{
					int d = 100 - a - b - c;
					int score = getScore(a, b, c, d);
					if (score > maxScore && (!part2 || getCalories(a, b, c, d) == 500))
						maxScore = score;
				}
			}
		}
		return maxScore;
	}

	public static void parseLine(String input, int n)
	{
		String[] splitted = input.split(" ");
		stats[n][0] = Integer.parseInt(splitted[2].substring(0, splitted[2].length() - 1));
		stats[n][1] = Integer.parseInt(splitted[4].substring(0, splitted[4].length() - 1));
		stats[n][2] = Integer.parseInt(splitted[6].substring(0, splitted[6].length() - 1));
		stats[n][3] = Integer.parseInt(splitted[8].substring(0, splitted[8].length() - 1));
		stats[n][4] = Integer.parseInt(splitted[10]);
	}

	public static int getScore(int a, int b, int c, int d)
	{
		int capacity = getIngredient(a, b, c, d, 0);
		int durability = getIngredient(a, b, c, d, 1);
		int flavor = getIngredient(a, b, c, d, 2);
		int texture = getIngredient(a, b, c, d, 3);
		return Math.max(capacity, 0) * Math.max(durability, 0) * Math.max(flavor, 0) * Math.max(texture, 0);
	}

	public static int getIngredient(int a, int b, int c, int d, int n)
	{
		return a * stats[0][n] + b * stats[1][n] + c * stats[2][n] + d * stats[3][n];
	}

	public static int getCalories(int a, int b, int c, int d)
	{
		return a * stats[0][4] + b * stats[1][4] + c * stats[2][4] + d * stats[3][4];
	}
}
