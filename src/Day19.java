import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Day19
{
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new FileReader(new File("").getAbsolutePath() + "/bin/day19.txt"));
		String input;
		ArrayList<Pair> pairs = new ArrayList<Pair>();
		do
		{
			input = br.readLine();
			if (input.length() > 0)
			{
				addReplacement(input, pairs);
			}
		}
		while (input.length() > 0);

		String chemical = br.readLine();
		br.close();

		ArrayList<String> chemicals = new ArrayList<String>();
		int n = 0;
		for (Pair pair : pairs)
		{
			addChemicals(chemical, pair.key, pair.value, chemicals);
			n++;
		}

		System.out.println("Part 1: " + chemicals.size());
		System.out.println("Part 2: " + fixChemical(chemical, pairs));
	}

	public static int fixChemical(String chemical, ArrayList<Pair> pairs)
	{
		int n = 0;
		while (!chemical.equals("e"))
		{
			int lastIndex = -1;
			Pair replacePair = null;
			for (Pair pair : pairs)
			{
				String value = pair.value;
				int index = chemical.lastIndexOf(value);
				if (index > lastIndex)
				{
					lastIndex = index;
					replacePair = pair;
				}
			}
			String firstPart = chemical.substring(0, lastIndex);
			String secondPart = chemical.substring(lastIndex).replaceFirst(replacePair.value, replacePair.key);
			chemical = firstPart + secondPart;
			n++;
		}
		return n;
	}

	public static void addChemicals(String chemical, String key, String value, ArrayList<String> chemicals)
	{
		for (int x = 0; x < chemical.length(); x++)
		{
			String firstHalf = chemical.substring(0, x);
			String secondHalf = chemical.substring(x);
			if (secondHalf.contains(key))
			{
				secondHalf = secondHalf.replaceFirst(key, value);
				String combined = firstHalf + secondHalf;
				if (!chemicals.contains(combined))
				{
					chemicals.add(combined);
				}
			}
		}
	}

	public static void addReplacement(String input, ArrayList<Pair> pairs)
	{
		String[] splitted = input.split(" ");
		pairs.add(new Pair(splitted[0], splitted[2]));
	}
}

class Pair
{
	public String key;
	public String value;

	public Pair(String k, String v)
	{
		key = k;
		value = v;
	}
}
