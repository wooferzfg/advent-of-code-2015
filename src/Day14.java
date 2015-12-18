import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Day14
{
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new FileReader(new File("").getAbsolutePath() + "/bin/day14.txt"));
		String input;
		int[][] stats = new int[9][3]; // speed, length, rest
		int n = 0;
		do
		{
			input = br.readLine();
			if (input != null)
			{
				parseLine(input, stats, n);
			}
			n++;
		}
		while (input != null);
		br.close();

		int[] points = new int[9];
		int maxDistance = 0;
		for (int x = 1; x <= 2503; x++)
		{
			maxDistance = 0;
			ArrayList<Integer> pointsToAdd = new ArrayList<Integer>();
			for (int deer = 0; deer < points.length; deer++)
			{
				int distance = getDistance(x, stats[deer][0], stats[deer][1], stats[deer][2]);
				if (distance == maxDistance)
				{
					pointsToAdd.add(deer);
				}
				else if (distance > maxDistance)
				{
					pointsToAdd.clear();
					pointsToAdd.add(deer);
					maxDistance = distance;
				}
			}
			for (int deer : pointsToAdd)
			{
				points[deer]++;
			}
		}
		int maxPoints = 0;
		for (int deer = 0; deer < points.length; deer++)
		{
			maxPoints = Math.max(maxPoints, points[deer]);
		}

		System.out.println("Part 1: " + maxDistance);
		System.out.println("Part 2: " + maxPoints);
	}

	public static void parseLine(String line, int[][] stats, int n)
	{
		String[] params = line.split(" ");
		int speed = Integer.parseInt(params[3]);
		int length = Integer.parseInt(params[6]);
		int rest = Integer.parseInt(params[13]);
		stats[n][0] = speed;
		stats[n][1] = length;
		stats[n][2] = rest;
	}

	public static int getDistance(int time, int speed, int length, int rest)
	{
		int repetitions = time / (length + rest);
		int distance = repetitions * speed * length;
		int remaining = time - repetitions * (length + rest);
		distance += Math.min(remaining, length) * speed;
		return distance;
	}
}