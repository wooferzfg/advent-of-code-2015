import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Day1
{
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new FileReader(new File("").getAbsolutePath() + "/bin/day1.txt"));
		String input = br.readLine();
		br.close();

		PuzzleAnswer answer = getAnswer(input);
		System.out.println("Part 1: " + answer.floor);
		System.out.println("Part 2: " + answer.basementIndex);
	}

	public static PuzzleAnswer getAnswer(String input)
	{
		int floor = 0;
		int n = 1;
		int basement = -1;
		for (char c : input.toCharArray())
		{
			switch (c)
			{
			case '(':
				floor++;
				break;
			case ')':
				floor--;
				break;
			}
			if (floor == -1 && basement < 0)
				basement = n;
			n++;
		}
		return new PuzzleAnswer(floor, basement);
	}
}

class PuzzleAnswer
{
	public int floor;
	public int basementIndex;

	public PuzzleAnswer(int f, int b)
	{
		floor = f;
		basementIndex = b;
	}
}
