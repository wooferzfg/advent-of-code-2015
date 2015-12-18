import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Day6
{
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new FileReader(new File("").getAbsolutePath() + "/bin/day6.txt"));
		String input;
		int[][] lights = new int[1000][1000];
		int[][] lights2 = new int[1000][1000];
		do
		{
			input = br.readLine();
			if (input != null)
			{
				parseLine(input, lights, false);
				parseLine(input, lights2, true);
			}
		}
		while (input != null);
		br.close();

		System.out.println("Part 1: " + countLights(lights));
		System.out.println("Part 2: " + countLights(lights2));
	}

	public static int countLights(int[][] grid)
	{
		int total = 0;
		for (int x = 0; x < grid.length; x++)
		{
			for (int y = 0; y < grid[x].length; y++)
			{
				total += grid[x][y];
			}
		}
		return total;
	}

	public static void parseLine(String input, int[][] grid, boolean part2)
	{
		if (input.startsWith("toggle"))
		{
			int[] coords = parseCoordinates(input.substring("toggle ".length()));
			changeLights(grid, coords, part2 ? GridAction.TOGGLE2 : GridAction.TOGGLE);
		}
		else if (input.startsWith("turn on"))
		{
			int[] coords = parseCoordinates(input.substring("turn on ".length()));
			changeLights(grid, coords, part2 ? GridAction.TURNON2 : GridAction.TURNON);
		}
		else
		{
			int[] coords = parseCoordinates(input.substring("turn off ".length()));
			changeLights(grid, coords, part2 ? GridAction.TURNOFF2 : GridAction.TURNOFF);
		}
	}

	public static void changeLights(int[][] grid, int[] coords, GridAction action)
	{
		for (int x = coords[0]; x <= coords[2]; x++)
		{
			for (int y = coords[1]; y <= coords[3]; y++)
			{
				switch (action)
				{
				case TOGGLE:
					grid[x][y] = 1 - grid[x][y];
					break;
				case TURNON:
					grid[x][y] = 1;
					break;
				case TURNOFF:
					grid[x][y] = 0;
					break;
				case TOGGLE2:
					grid[x][y] += 2;
					break;
				case TURNON2:
					grid[x][y]++;
					break;
				case TURNOFF2:
					grid[x][y]--;
					if (grid[x][y] < 0)
						grid[x][y] = 0;
					break;
				}
			}
		}
	}

	public static int[] parseCoordinates(String input)
	{
		String[] split = input.split("[\\s|,]");
		int[] coordinates = new int[4];
		coordinates[0] = Integer.parseInt(split[0]);
		coordinates[1] = Integer.parseInt(split[1]);
		coordinates[2] = Integer.parseInt(split[3]);
		coordinates[3] = Integer.parseInt(split[4]);
		return coordinates;
	}
}

enum GridAction
{
	TOGGLE, TURNON, TURNOFF, TOGGLE2, TURNON2, TURNOFF2
}
