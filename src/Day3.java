import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Day3
{
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new FileReader(new File("").getAbsolutePath() + "/bin/day3.txt"));
		String input = br.readLine();
		br.close();

		System.out.println("Part 1: " + getTotalSantaOnly(input));
		System.out.println("Part 2: " + getTotalWithRobot(input));
	}

	public static int getTotalSantaOnly(String input)
	{
		int[][] grid = new int[200][200];
		int santaX = 100;
		int santaY = 100;
		grid[santaX][santaY] = 1;
		int total = 1;
		for (char c : input.toCharArray())
		{
			switch (c)
			{
			case '^':
				santaY--;
				break;
			case 'v':
				santaY++;
				break;
			case '<':
				santaX--;
				break;
			case '>':
				santaX++;
				break;
			}
			if (grid[santaX][santaY] == 0)
				total++;
			grid[santaX][santaY]++;
		}
		return total;
	}

	public static int getTotalWithRobot(String input)
	{
		int[][] grid = new int[200][200];
		int santaX = 100;
		int santaY = 100;
		int robotX = 100;
		int robotY = 100;
		grid[santaX][santaY] = 1;
		boolean isRobot = false;
		int total = 1;
		for (char c : input.toCharArray())
		{
			switch (c)
			{
			case '^':
				if (isRobot)
					robotY--;
				else
					santaY--;
				break;
			case 'v':
				if (isRobot)
					robotY++;
				else
					santaY++;
				break;
			case '<':
				if (isRobot)
					robotX--;
				else
					santaX--;
				break;
			case '>':
				if (isRobot)
					robotX++;
				else
					santaX++;
				break;
			}
			if (isRobot)
			{
				if (grid[robotX][robotY] == 0)
					total++;
				grid[robotX][robotY]++;
			}
			else
			{
				if (grid[santaX][santaY] == 0)
					total++;
				grid[santaX][santaY]++;
			}
			isRobot = !isRobot;
		}
		return total;
	}
}