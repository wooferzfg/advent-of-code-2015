import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Day21
{
	public static int health = 100;
	public static int bossHealth;
	public static int bossDamage;
	public static int bossArmor;

	public static ArrayList<Item> weapons;
	public static ArrayList<Item> armor;
	public static ArrayList<Item> rings;

	public static void main(String[] args) throws NumberFormatException, IOException
	{
		parseBossInfo();
		createItems();

		System.out.println("Part 1: " + findMinCost());
		System.out.println("Part 2: " + findMaxCost());
	}

	public static int findMaxCost()
	{
		int maxCost = -1;
		for (ArrayList<Item> combination : generateCombinations())
		{
			int cost = getCost(combination);
			if (!simulateFight(combination) && (maxCost < 0 || cost > maxCost))
			{
				maxCost = cost;
			}
		}
		return maxCost;
	}

	public static int findMinCost()
	{
		int minCost = -1;
		for (ArrayList<Item> combination : generateCombinations())
		{
			int cost = getCost(combination);
			if (simulateFight(combination) && (minCost < 0 || cost < minCost))
			{
				minCost = cost;
			}
		}
		return minCost;
	}

	public static int getCost(ArrayList<Item> items)
	{
		int cost = 0;
		for (Item item : items)
		{
			cost += item.cost;
		}
		return cost;
	}

	public static ArrayList<ArrayList<Item>> generateCombinations()
	{
		ArrayList<ArrayList<Item>> combinations = new ArrayList<ArrayList<Item>>();
		for (int w = 0; w <= 4; w++)
		{
			for (int a = -1; a <= 4; a++)
			{
				for (int r1 = -1; r1 <= 5; r1++)
				{
					for (int r2 = -1; r2 < r1 || r2 < 0; r2++)
					{
						ArrayList<Item> items = new ArrayList<Item>();
						if (w >= 0)
							items.add(weapons.get(w));
						if (a >= 0)
							items.add(armor.get(a));
						if (r1 >= 0)
							items.add(rings.get(r1));
						if (r2 >= 0)
							items.add(rings.get(r2));
						combinations.add(items);
					}
				}
			}
		}
		return combinations;
	}

	public static boolean simulateFight(ArrayList<Item> playerItems)
	{
		int damage = 0;
		int armor = 0;
		for (Item item : playerItems)
		{
			damage += item.damage;
			armor += item.armor;
		}

		int playerHP = health;
		int bossHP = bossHealth;
		boolean playerTurn = true;
		while (playerHP > 0 && bossHP > 0)
		{
			if (playerTurn)
			{
				bossHP -= Math.max(damage - bossArmor, 1);
			}
			else
			{
				playerHP -= Math.max(bossDamage - armor, 1);
			}
			playerTurn = !playerTurn;
		}
		return playerHP > 0;
	}

	public static void createItems()
	{
		weapons = new ArrayList<Item>();
		weapons.add(new Item(8, 4, 0));
		weapons.add(new Item(10, 5, 0));
		weapons.add(new Item(25, 6, 0));
		weapons.add(new Item(40, 7, 0));
		weapons.add(new Item(74, 8, 0));

		armor = new ArrayList<Item>();
		armor.add(new Item(13, 0, 1));
		armor.add(new Item(31, 0, 2));
		armor.add(new Item(53, 0, 3));
		armor.add(new Item(75, 0, 4));
		armor.add(new Item(102, 0, 5));

		rings = new ArrayList<Item>();
		rings.add(new Item(25, 1, 0));
		rings.add(new Item(50, 2, 0));
		rings.add(new Item(100, 3, 0));
		rings.add(new Item(20, 0, 1));
		rings.add(new Item(40, 0, 2));
		rings.add(new Item(80, 0, 3));
	}

	public static void parseBossInfo() throws NumberFormatException, IOException
	{
		BufferedReader br = new BufferedReader(new FileReader(new File("").getAbsolutePath() + "/bin/day21.txt"));
		bossHealth = Integer.parseInt(br.readLine().split(" ")[2]);
		bossDamage = Integer.parseInt(br.readLine().split(" ")[1]);
		bossArmor = Integer.parseInt(br.readLine().split(" ")[1]);
		br.close();
	}
}

class Item
{
	public int cost;
	public int damage;
	public int armor;

	public Item(int c, int d, int a)
	{
		cost = c;
		damage = d;
		armor = a;
	}
}
