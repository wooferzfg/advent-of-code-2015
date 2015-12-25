import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Day24
{
	public static ArrayList<Integer> weights = new ArrayList<Integer>();
	public static long minQEValue;
	public static int minGroupSize;

	public static void main(String[] args) throws IOException
	{
		addWeights();

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
		checkSum(new ArrayList<Integer>(), weights, numGroups, 0, true);
	}

	public static boolean checkSum(ArrayList<Integer> group, ArrayList<Integer> remainingWeights, int numGroups, int n, boolean updateMinimums)
	{
		int sum = getSum(group);
		int groupSize = group.size();
		long groupQE = getQE(group);
		int totalWeights = getSum(remainingWeights);

		if (sum > totalWeights / numGroups)
			return false;
		if (updateMinimums)
		{
			if (minGroupSize >= 0 && groupSize > minGroupSize 
				|| groupSize == minGroupSize && minQEValue >= 0 && groupQE >= minQEValue)
				return false;
		}

		if (sum == totalWeights / numGroups)
		{
			if (numGroups == 2)
				return true;

			ArrayList<Integer> newWeights = subtractSets(remainingWeights, group);
			if (checkSum(new ArrayList<Integer>(), newWeights, numGroups - 1, 0, false))
			{
				if (updateMinimums)
				{
					minGroupSize = groupSize;
					minQEValue = groupQE;
				}
				return true;
			}
			return false;
		}

		if (n < remainingWeights.size())
		{
			ArrayList<Integer> newGroup = addWeight(group, remainingWeights, n);
			boolean a = checkSum(newGroup, remainingWeights, numGroups, n + 1, updateMinimums);
			boolean b = false;
			if (updateMinimums || !a)
				b = checkSum(group, remainingWeights, numGroups, n + 1, updateMinimums);
			return a || b;
		}
		return false;
	}

	public static ArrayList<Integer> addWeight(ArrayList<Integer> group, ArrayList<Integer> remainingWeights, int n)
	{
		ArrayList<Integer> newGroup = (ArrayList<Integer>)group.clone();
		newGroup.add(remainingWeights.get(remainingWeights.size() - n - 1));
		return newGroup;
	}

	public static ArrayList<Integer> subtractSets(ArrayList<Integer> a, ArrayList<Integer> b)
	{
		ArrayList<Integer> newWeights = (ArrayList<Integer>)a.clone();
		for (int weight : b)
		{
			newWeights.remove(new Integer(weight));
		}
		return newWeights;
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
