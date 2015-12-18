import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Day8
{
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new FileReader(new File("").getAbsolutePath() + "/bin/day8.txt"));
		String input;
		int difference1 = 0;
		int difference2 = 0;
		do
		{
			input = br.readLine();
			if (input != null)
			{
				String shorter = input.replaceAll("\\\\\"", "\"").replaceAll("\\\\\\\\", "\\\\").replaceAll("\\\\x[0-9a-f]{2}", "x");
				String longer = input.replaceAll("\\\\", "\\\\\\\\").replaceAll("\"", "\\\\\"");
				difference1 += input.length() - shorter.length() + 2;
				difference2 += longer.length() - input.length() + 2;
			}
		}
		while (input != null);
		br.close();

		System.out.println("Part 1: " + difference1);
		System.out.println("Part 2: " + difference2);
	}
}
