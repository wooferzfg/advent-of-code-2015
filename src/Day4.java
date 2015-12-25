import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Day4
{
	public static MessageDigest md;

	public static int getNumberOfZeros(String input)
	{
		byte[] messageDigest = md.digest(input.getBytes());
		BigInteger number = new BigInteger(1, messageDigest);
		String hashtext = number.toString(16);
		return 32 - hashtext.length();
	}

	public static void main(String[] args) throws IOException
	{
		try
		{
			md = MessageDigest.getInstance("MD5");
		}
		catch (NoSuchAlgorithmException e)
		{
		}

		BufferedReader br = new BufferedReader(new FileReader(new File("").getAbsolutePath() + "/bin/day4.txt"));
		String input = br.readLine();
		System.out.println("Part 1: " + getNumberForHash(5, input));
		System.out.println("Part 2: " + getNumberForHash(6, input));
		br.close();
	}

	public static int getNumberForHash(int zeros, String hash)
	{
		int num = 0;

		while (true)
		{
			num++;
			String cur = hash + num;
			if (getNumberOfZeros(cur) == zeros)
				return num;
		}
	}
}