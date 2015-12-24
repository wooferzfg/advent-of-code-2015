import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Day24
{
	public static ArrayList<Integer> weights = new ArrayList<Integer>();
	public static int totalWeights;
	public static int numWeights;
	public static long minQEValue;
	public static int minGroupSize;

	public static void main(String[] args) throws IOException
	{
		addWeights();
		totalWeights = getSum(weights);
		numWeights = weights.size();

		getFirstGroupQE(3);
		System.out.println("Part 1: " + minQEValue);

		getFirstGroupQE(4);
		System.out.println("Part 2: " + minQEValue);
	}

	public static void addWeights() throws IOException
	{
		BufferedReader br = new BufferedReader(new FileReader(new File("").getAbsolutePath() + "/bin/day24.txt"));
		String input;
		do
		{
			input = br.readLine();
			if (input != null)
			{
				weights.add(Integer.parseInt(input));
			}
		}
		while (input != null);
		br.close();
	}

	public static void getFirstGroupQE(int numGroups)
	{
		minQEValue = -1;
		minGroupSize = -1;
		getFirstGroupQE(new ArrayList<Integer>(), numGroups, 0);
	}

	public static void getFirstGroupQE(ArrayList<Integer> group, int numGroups, int n)
	{
		int sum = getSum(group);
		int groupSize = group.size();
		long groupQE = getQE(group);

		if (sum > totalWeights / numGroups 
				|| minGroupSize >= 0 && groupSize > minGroupSize
				|| groupSize == minGroupSize && minQEValue >= 0 && groupQE >= minQEValue)
			return;

		if (sum == totalWeights / numGroups)
		{
			minGroupSize = groupSize;
			minQEValue = groupQE;
		}

		if (n < weights.size())
		{
			ArrayList<Integer> newGroup = (ArrayList<Integer>)group.clone();
			newGroup.add(weights.get(numWeights - n - 1));
			getFirstGroupQE(newGroup, numGroups, n + 1);
			getFirstGroupQE(group, numGroups, n + 1);
		}
	}

	public static int getSum(ArrayList<Integer> group)
	{
		int total = 0;
		for (int x : group)
		{
			total += x;
		}
		return total;
	}

	public static long getQE(ArrayList<Integer> group)
	{
		long product = 1;
		for (int x : group)
		{
			product *= x;
		}
		return product;
	}
}
