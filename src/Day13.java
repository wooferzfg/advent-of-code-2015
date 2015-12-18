import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Day13
{
	public static ArrayList<String> people = new ArrayList<String>();

	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new FileReader(new File("").getAbsolutePath() + "/bin/day13.txt"));
		String input;
		Map<Key, Integer> peopleMap = new HashMap<Key, Integer>();
		do
		{
			input = br.readLine();
			if (input != null)
			{
				parseLine(peopleMap, input);
			}
		}
		while (input != null);
		br.close();

		System.out.println("Part 1: " + findMaxHappiness(peopleMap));

		for (String person : people)
		{
			addToMap(peopleMap, person, "You", 0);
		}
		people.add("You");

		System.out.println("Part 2: " + findMaxHappiness(peopleMap));
	}

	public static void parseLine(Map<Key, Integer> map, String line)
	{
		String[] params = line.split(" ");
		String person1 = params[0];
		if (!people.contains(person1))
			people.add(person1);
		String person2 = params[10].substring(0, params[10].length() - 1);
		int happiness = Integer.parseInt(params[3]);
		if (params[2].equals("lose"))
			happiness = -happiness;
		addToMap(map, person1, person2, happiness);
	}

	public static void addToMap(Map<Key, Integer> map, String p1, String p2, int happiness)
	{
		Key key = new Key(p1, p2);
		if (map.containsKey(key))
			map.put(key, happiness + map.get(key));
		else
			map.put(key, happiness);
	}

	public static int findMaxHappiness(Map<Key, Integer> map)
	{
		ArrayList<ArrayList<String>> permutations = new ArrayList<ArrayList<String>>();
		permute(permutations, people, 0);
		int maxHappiness = 0;
		for (ArrayList<String> arrangement : permutations)
		{
			int happiness = 0;
			String previousPerson = arrangement.get(arrangement.size() - 1);
			for (String person : arrangement)
			{
				happiness += map.get(new Key(person, previousPerson));
				previousPerson = person;
			}
			if (happiness > maxHappiness)
			{
				maxHappiness = happiness;
			}
		}
		return maxHappiness;
	}

	public static void permute(ArrayList<ArrayList<String>> permutations, ArrayList<String> people, int k)
	{
		for (int i = k; i < people.size(); i++)
		{
			Collections.swap(people, i, k);
			permute(permutations, people, k + 1);
			Collections.swap(people, k, i);
		}
		if (k == people.size() - 1)
			permutations.add((ArrayList<String>)people.clone());
	}

}

class Key
{
	private String person1;
	private String person2;

	public Key(String p1, String p2)
	{
		person1 = p1;
		person2 = p2;
	}

	@Override
	public boolean equals(Object o)
	{
		if (this == o)
			return true;
		if (!(o instanceof Key))
			return false;
		Key key = (Key)o;
		return person1.equals(key.person1) && person2.equals(key.person2) || person1.equals(key.person2) && person2.equals(key.person1);
	}

	@Override
	public int hashCode()
	{
		return person1.hashCode() + person2.hashCode();
	}
}