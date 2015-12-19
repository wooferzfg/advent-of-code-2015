import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Day9
{
	public static ArrayList<String> cities = new ArrayList<String>();

	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new FileReader(new File("").getAbsolutePath() + "/bin/day9.txt"));
		String input;
		Map<Route, Integer> routesMap = new HashMap<Route, Integer>();
		do
		{
			input = br.readLine();
			if (input != null)
			{
				parseLine(routesMap, input);
			}
		}
		while (input != null);
		br.close();

		System.out.println("Part 1: " + findMaxMin(routesMap, false));
		System.out.println("Part 2: " + findMaxMin(routesMap, true));
	}

	public static void parseLine(Map<Route, Integer> map, String line)
	{
		String[] params = line.split(" ");
		String city1 = params[0];
		String city2 = params[2];
		if (!cities.contains(city1))
			cities.add(city1);
		if (!cities.contains(city2))
			cities.add(city2);
		int distance = Integer.parseInt(params[4]);
		addToMap(map, city1, city2, distance);
	}

	public static void addToMap(Map<Route, Integer> map, String c1, String c2, int distance)
	{
		map.put(new Route(c1, c2), distance);
	}

	public static int findMaxMin(Map<Route, Integer> map, boolean longest)
	{
		ArrayList<ArrayList<String>> permutations = new ArrayList<ArrayList<String>>();
		permute(permutations, cities, 0);
		int maxMinDist = -1;
		for (ArrayList<String> route : permutations)
		{
			int totalDist = 0;
			String previous = null;
			for (String curCity : route)
			{
				if (previous != null)
				{
					totalDist += map.get(new Route(curCity, previous));
				}
				previous = curCity;
			}
			if (maxMinDist < 0 || longest && totalDist > maxMinDist || !longest && totalDist < maxMinDist)
			{
				maxMinDist = totalDist;
			}
		}
		return maxMinDist;
	}

	public static void permute(ArrayList<ArrayList<String>> permutations, ArrayList<String> cities, int k)
	{
		for (int i = k; i < cities.size(); i++)
		{
			Collections.swap(cities, i, k);
			permute(permutations, cities, k + 1);
			Collections.swap(cities, k, i);
		}
		if (k == cities.size() - 1)
			permutations.add((ArrayList<String>)cities.clone());
	}
}

class Route
{
	private String city1;
	private String city2;

	public Route(String c1, String c2)
	{
		city1 = c1;
		city2 = c2;
	}

	@Override
	public boolean equals(Object o)
	{
		if (this == o)
			return true;
		if (!(o instanceof Route))
			return false;
		Route r = (Route)o;
		return city1.equals(r.city1) && city2.equals(r.city2) || city1.equals(r.city2) && city2.equals(r.city1);
	}

	@Override
	public int hashCode()
	{
		return city1.hashCode() + city2.hashCode();
	}
}