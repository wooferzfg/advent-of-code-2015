import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Day16
{
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new FileReader(new File("").getAbsolutePath() + "/bin/day16.txt"));
		String input;
		int n = 1;
		boolean day1 = false, day2 = false;
		do
		{
			input = br.readLine();
			if (input != null)
			{
				if (parseInput(input, false) && !day1)
				{
					System.out.println("Day 1: " + n);
					day1 = true;
				}
				if (parseInput(input, true) && !day2)
				{
					System.out.println("Day 2: " + n);
					day2 = true;
				}
			}
			n++;
		}
		while (input != null);
		br.close();
	}

	public static boolean parseInput(String line, boolean day2)
	{
		String[] params = line.split(" ");
		String var1 = params[2].substring(0, params[2].length() - 1);
		String var2 = params[4].substring(0, params[4].length() - 1);
		String var3 = params[6].substring(0, params[6].length() - 1);
		int amount1 = Integer.parseInt(params[3].substring(0, params[3].length() - 1));
		int amount2 = Integer.parseInt(params[5].substring(0, params[5].length() - 1));
		int amount3 = Integer.parseInt(params[7]);
		return testRequirement(var1, amount1, day2) && testRequirement(var2, amount2, day2) && testRequirement(var3, amount3, day2);
	}

	public static boolean testRequirement(String variable, int amount, boolean day2)
	{
		if (variable.equals("children"))
			return amount == 3;
		if (variable.equals("cats"))
			return day2 ? amount > 7 : amount == 7;
		if (variable.equals("samoyeds"))
			return amount == 2;
		if (variable.equals("pomeranians"))
			return day2 ? amount < 3 : amount == 3;
		if (variable.equals("akitas"))
			return amount == 0;
		if (variable.equals("vizslas"))
			return amount == 0;
		if (variable.equals("goldfish"))
			return day2 ? amount < 5 : amount == 5;
		if (variable.equals("trees"))
			return day2 ? amount > 3 : amount == 3;
		if (variable.equals("cars"))
			return amount == 2;
		if (variable.equals("perfumes"))
			return amount == 1;
		return false;
	}
}

