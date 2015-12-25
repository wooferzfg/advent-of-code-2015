import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day25
{
	public static void main(String[] args) throws IOException
	{
		int desiredIndex = getDesiredIndex();
		System.out.println(generateCode(desiredIndex));
	}

	public static int getDesiredIndex() throws IOException
	{
		BufferedReader br = new BufferedReader(new FileReader(new File("").getAbsolutePath() + "/bin/day25.txt"));
		String input = br.readLine();
		br.close();

		Pattern pattern = Pattern.compile("(\\d+)\\D+(\\d+)");
		Matcher matcher = pattern.matcher(input);
		matcher.find();
		int row = Integer.parseInt(matcher.group(1));
		int column = Integer.parseInt(matcher.group(2));

		return getIndex(row, column);
	}

	public static long generateCode(int index)
	{
		long number = 20151125;
		for (int x = 1; x < index; x++)
		{
			number *= 252533;
			number = number % 33554393;
		}
		return number;
	}

	public static int getIndex(int row, int col)
	{
		return sumOfNumbers(row + col - 2) + col;
	}

	public static int sumOfNumbers(int n)
	{
		int total = 0;
		for (int x = 1; x <= n; x++)
		{
			total += x;
		}
		return total;
	}
}
