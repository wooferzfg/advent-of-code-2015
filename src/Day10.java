import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class Day10
{
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new FileReader(new File("").getAbsolutePath() + "/bin/day10.txt"));
		String input = br.readLine();
		br.close();
		byte[] num = new byte[10];
		for (int x = 0; x < input.length(); x++)
		{
			num[x] = Byte.parseByte(input.substring(x, x + 1));
		}

		for (int x = 1; x <= 50; x++)
		{
			num = convertNum(num);
			if (x == 40)
				System.out.println("Part 1: " + num.length);
		}
		System.out.println("Part 2: " + num.length);
	}

	public static byte[] convertNum(byte[] digits)
	{
		int currentNum = 0;
		int repetitions = 0;
		int index = 0;
		byte[] newNum = new byte[(int)Math.max(digits.length * 1.33, 1000)];
		for (int x = 0; x < digits.length; x++)
		{
			int digit = digits[x];
			if (currentNum == 0)
			{
				currentNum = digit;
			}
			if (digit != currentNum)
			{
				updateNewNum(newNum, index, repetitions, currentNum);
				index += 2;
				currentNum = digit;
				repetitions = 0;
			}
			repetitions++;
			if (x == digits.length - 1)
			{
				updateNewNum(newNum, index, repetitions, currentNum);
				index += 2;
			}
		}
		return Arrays.copyOf(newNum, index);
	}

	public static void updateNewNum(byte[] newNum, int index, int repetitions, int currentNum)
	{
		newNum[index] = (byte)repetitions;
		newNum[index + 1] = (byte)currentNum;
	}
}
