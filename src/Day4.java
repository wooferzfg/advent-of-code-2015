import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Day4
{
	public static String getMD5(String input)
	{
		try
		{
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] messageDigest = md.digest(input.getBytes());
			BigInteger number = new BigInteger(1, messageDigest);
			String hashtext = number.toString(16);
			// Now we need to zero pad it if you actually want the full 32
			// chars.
			while (hashtext.length() < 32)
			{
				hashtext = "0" + hashtext;
			}
			return hashtext;
		}
		catch (NoSuchAlgorithmException e)
		{
			throw new RuntimeException(e);
		}
	}

	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new FileReader(new File("").getAbsolutePath() + "/bin/day4.txt"));
		String input = br.readLine();
		System.out.println("Part 1: " + getNumberForHash(5, input));
		System.out.println("Part 2: " + getNumberForHash(6, input));
		br.close();
	}

	public static int getNumberForHash(int zeros, String hash)
	{
		int num = 0;
		String md5;
		do
		{
			num++;
			md5 = getMD5(hash + num);
		}
		while (!md5.substring(0, zeros).equals(new String(new char[zeros]).replace("\0", "0")));
		return num;
	}
}