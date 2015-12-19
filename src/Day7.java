import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Day7
{
	public static int part1Answer;

	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new FileReader(new File("").getAbsolutePath() + "/bin/day7.txt"));
		ArrayList<String> lines = new ArrayList<String>();
		String input;
		do
		{
			input = br.readLine();
			if (input != null)
			{
				lines.add(input);
			}
		}
		while (input != null);
		br.close();
		ArrayList<String> lines2 = (ArrayList<String>)lines.clone();

		HashMap<String, Integer> wires = new HashMap<String, Integer>();
		HashMap<String, Integer> wires2 = new HashMap<String, Integer>();

		populateWires(lines, wires, false);
		part1Answer = wires.get("a");
		populateWires(lines2, wires2, true);

		System.out.println("Part 1: " + wires.get("a"));
		System.out.println("Part 2: " + wires2.get("a"));
	}

	public static void populateWires(ArrayList<String> lines, HashMap<String, Integer> wires, boolean part2)
	{
		while (lines.size() > 0)
		{
			for (int x = 0; x < lines.size(); x++)
			{
				String line = lines.get(x);
				if (parseLine(line, wires))
				{
					lines.remove(x);
					x--;
				}
				if (part2)
					wires.put("b", part1Answer);
			}
		}
	}

	public static boolean parseLine(String input, HashMap<String, Integer> wires)
	{
		String[] splitted = input.split(" ");
		if (splitted[1].equals("AND"))
		{
			return and(splitted[0], splitted[2], splitted[4], wires);
		}
		else if (splitted[1].equals("OR"))
		{
			return or(splitted[0], splitted[2], splitted[4], wires);
		}
		else if (splitted[1].equals("LSHIFT"))
		{
			return lShift(splitted[0], splitted[2], splitted[4], wires);
		}
		else if (splitted[1].equals("RSHIFT"))
		{
			return rShift(splitted[0], splitted[2], splitted[4], wires);
		}
		else if (splitted[0].equals("NOT"))
		{
			return not(splitted[1], splitted[3], wires);
		}
		else
		{
			return assign(splitted[0], splitted[2], wires);
		}
	}

	public static boolean and(String wire1, String wire2, String target, HashMap<String, Integer> wires)
	{
		Integer w1 = resolve(wire1, wires);
		Integer w2 = resolve(wire2, wires);
		if (w1 == null || w2 == null)
			return false;
		wires.put(target, w1 & w2);
		return true;
	}

	public static boolean or(String wire1, String wire2, String target, HashMap<String, Integer> wires)
	{
		Integer w1 = resolve(wire1, wires);
		Integer w2 = resolve(wire2, wires);
		if (w1 == null || w2 == null)
			return false;
		wires.put(target, w1 | w2);
		return true;
	}

	public static boolean lShift(String wire1, String wire2, String target, HashMap<String, Integer> wires)
	{
		Integer w1 = resolve(wire1, wires);
		Integer w2 = resolve(wire2, wires);
		if (w1 == null || w2 == null)
			return false;
		wires.put(target, w1 << w2);
		return true;
	}

	public static boolean rShift(String wire1, String wire2, String target, HashMap<String, Integer> wires)
	{
		Integer w1 = resolve(wire1, wires);
		Integer w2 = resolve(wire2, wires);
		if (w1 == null || w2 == null)
			return false;
		wires.put(target, w1 >> w2);
		return true;
	}

	public static boolean not(String wire, String target, HashMap<String, Integer> wires)
	{
		Integer w = resolve(wire, wires);
		if (w == null)
			return false;
		int mask = 0xffff;
		wires.put(target, ~w & mask);
		return true;
	}

	public static boolean assign(String wire, String target, HashMap<String, Integer> wires)
	{
		Integer w = resolve(wire, wires);
		if (w == null)
			return false;
		wires.put(target, w);
		return true;
	}

	public static Integer resolve(String wire, HashMap<String, Integer> wires)
	{
		if (wire.matches("\\d*"))
			return Integer.parseInt(wire);
		return wires.get(wire);
	}
}
