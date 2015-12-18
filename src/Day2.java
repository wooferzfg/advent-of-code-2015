import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Day2
{
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new FileReader(new File("").getAbsolutePath() + "/bin/day2.txt"));
		String input;
		int totalWrapping = 0;
		int totalRibbon = 0;
		do
		{
			input = br.readLine();
			if (input != null)
			{
				totalWrapping += getWrapping(input);
				totalRibbon += getRibbon(input);
			}
		}
		while (input != null);
		br.close();

		System.out.println("Part 1: " + totalWrapping);
		System.out.println("Part 2: " + totalRibbon);
	}

	public static int[] getDimensions(String input)
	{
		String[] splitted = input.split("x");
		int[] dimensions = new int[3];
		dimensions[0] = Integer.parseInt(splitted[0]);
		dimensions[1] = Integer.parseInt(splitted[1]);
		dimensions[2] = Integer.parseInt(splitted[2]);
		return dimensions;
	}

	public static int getWrapping(String input)
	{
		int[] d = getDimensions(input);
		int surfaceArea = 2 * d[0] * d[1] + 2 * d[1] * d[2] + 2 * d[2] * d[0];
		int smallestSide = Math.min(Math.min(d[0] * d[1], d[1] * d[2]), d[2] * d[0]);
		return surfaceArea + smallestSide;
	}

	public static int getRibbon(String input)
	{
		int[] d = getDimensions(input);
		int shortestPerimeter = Math.min(Math.min(2 * (d[0] + d[1]), 2 * (d[1] + d[2])), 2 * (d[2] + d[0]));
		int bow = d[0] * d[1] * d[2];
		return shortestPerimeter + bow;
	}
}
