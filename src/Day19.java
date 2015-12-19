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
		ArrayList<String> keys = new ArrayList<String>();
		ArrayList<String> values = new ArrayList<String>();
		do
		{
			input = br.readLine();
			if (input.length() > 0)
			{
				addReplacement(input, keys, values);
			}
		}
		while (input.length() > 0);

		String chemical = br.readLine();
		br.close();

		ArrayList<String> chemicals = new ArrayList<String>();
		int n = 0;
		for (String key : keys)
		{
			addChemicals(chemical, key, values.get(n), chemicals);
			n++;
		}

		System.out.println("Part 1: " + chemicals.size());
		System.out.println("Part 2: " + fixChemical(chemical, keys, values));
	}

	public static int fixChemical(String chemical, ArrayList<String> keys, ArrayList<String> values)
	{
		int n = 0;
		while (!chemical.equals("e"))
		{
			int lastIndex = -1;
			String replaceVal = "";
			for (String val : values)
			{
				int index = chemical.lastIndexOf(val);
				if (index > lastIndex)
				{
					lastIndex = index;
					replaceVal = val;
				}
			}
			String firstPart = chemical.substring(0, lastIndex);
			String secondPart = chemical.substring(lastIndex).replaceFirst(replaceVal, keys.get(values.indexOf(replaceVal)));
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

	public static void addReplacement(String input, ArrayList<String> keys, ArrayList<String> values)
	{
		String[] splitted = input.split(" ");
		keys.add(splitted[0]);
		values.add(splitted[2]);
	}
}
