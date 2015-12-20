import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Day12
{
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new FileReader(new File("").getAbsolutePath() + "/bin/day12.txt"));
		String input;
		ArrayList<Level> levels = new ArrayList<Level>();
		levels.add(new Level());
		int total = 0;

		do
		{
			input = br.readLine();
			if (input != null)
			{
				parseLine(input, levels);
				total += getNumber(input);
			}
		}
		while (input != null);
		br.close();

		System.out.println("Part 1: " + total);
		System.out.println("Part 2: " + levels.get(0).totals.get(0));
	}

	public static void parseLine(String input, ArrayList<Level> levels)
	{
		if (input.contains("{") || input.contains("["))
		{
			levels.add(new Level());
		}
		else if (input.contains("red"))
		{
			Level curLevel = levels.get(levels.size() - 1);
			curLevel.hasRed = true;
		}
		else if (input.contains("]"))
		{
			moveTotalUp(levels);
		}
		else if (input.contains("}"))
		{
			Level curLevel = levels.get(levels.size() - 1);
			if (!curLevel.hasRed)
				moveTotalUp(levels);
			else
				levels.remove(levels.size() - 1);
		}
		else
		{
			Level curLevel = levels.get(levels.size() - 1);
			curLevel.totals.add(getNumber(input));
		}
	}

	public static void moveTotalUp(ArrayList<Level> levels)
	{
		Level curLevel = levels.get(levels.size() - 1);
		int total = getTotal(curLevel.totals);
		levels.remove(levels.size() - 1);

		curLevel = levels.get(levels.size() - 1);
		curLevel.totals.add(total);
	}

	public static int getTotal(ArrayList<Integer> totals)
	{
		int total = 0;
		for (int x = 0; x < totals.size(); x++)
		{
			total += totals.get(x);
		}
		return total;
	}

	public static int getNumber(String input)
	{
		Pattern pattern = Pattern.compile("-?\\d+");
		Matcher matcher = pattern.matcher(input);
		if (matcher.find())
		{
			return Integer.parseInt(matcher.group());
		}
		return 0;
	}
}

class Level
{
	public ArrayList<Integer> totals;
	public boolean hasRed;

	public Level()
	{
		totals = new ArrayList<Integer>();
		hasRed = false;
	}
}
