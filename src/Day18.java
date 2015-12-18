import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Day18
{
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new FileReader(new File("").getAbsolutePath() + "/bin/day18.txt"));
		String input;
		int[][] grid = new int[100][100];
		int[][] grid2 = new int[100][100];
		int n = 0;
		do
		{
			input = br.readLine();
			if (input != null)
			{
				addLineToGrid(grid, input, n);
				addLineToGrid(grid2, input, n);
			}
			n++;
		}
		while (input != null);
		br.close();

		freezeLights(grid2);

		for (int x = 0; x < 100; x++)
		{
			grid = updateGrid(grid, false);
			grid2 = updateGrid(grid2, true);
		}

		System.out.println("Part 1: " + countLights(grid));
		System.out.println("Part 2: " + countLights(grid2));
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

	public static int[][] updateGrid(int[][] grid, boolean freeze)
	{
		int[][] newGrid = new int[grid.length][grid[0].length];

		for (int x = 0; x < grid.length; x++)
		{
			for (int y = 0; y < grid[x].length; y++)
			{
				int neighbors = getNumNeighbors(grid, x, y);
				if (grid[x][y] > 0 && neighbors == 2 || neighbors == 3)
					newGrid[x][y] = 1;
				else
					newGrid[x][y] = 0;
			}
		}
		if (freeze)
			freezeLights(newGrid);

		return newGrid;
	}

	public static void freezeLights(int[][] grid)
	{
		grid[0][0] = 1;
		grid[grid.length - 1][0] = 1;
		grid[0][grid[0].length - 1] = 1;
		grid[grid.length - 1][grid[0].length - 1] = 1;
	}

	public static int getNumNeighbors(int[][] grid, int x, int y)
	{
		return getLight(grid, x - 1, y - 1) + getLight(grid, x, y - 1) + getLight(grid, x + 1, y - 1) + getLight(grid, x + 1, y) + getLight(grid, x + 1, y + 1) + getLight(grid, x, y + 1)
				+ getLight(grid, x - 1, y + 1) + getLight(grid, x - 1, y);
	}

	public static int getLight(int[][] grid, int x, int y)
	{
		if (x < 0 || y < 0 || x >= grid.length || y >= grid[x].length)
			return 0;

		return grid[x][y];
	}

	public static void addLineToGrid(int[][] grid, String input, int n)
	{
		for (int x = 0; x < input.length(); x++)
		{
			char c = input.charAt(x);
			if (c == '#')
				grid[n][x] = 1;
			else
				grid[n][x] = 0;
		}
	}
}
