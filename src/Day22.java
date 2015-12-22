import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Day22
{
	public static int bossHP;
	public static int bossDamage;
	public static int overallMinCost;
	public static boolean hardMode;

	public static ArrayList<Spell> spells;

	public static void main(String[] args) throws NumberFormatException, IOException
	{
		parseBossInfo();
		addSpells();

		State state = new State(new ArrayList<Effect>(), 50, bossHP, 500);

		hardMode = false;
		overallMinCost = -1;
		System.out.println("Part 1: " + getMinCost(state, 0));

		hardMode = true;
		overallMinCost = -1;
		System.out.println("Part 2: " + getMinCost(state, 0));
	}

	public static int getMinCost(State state, int costSoFar)
	{
		if (hardMode)
			state.playerHP--;
		if (overallMinCost >= 0 && costSoFar >= overallMinCost)
			return -1;
		if (state.playerHP <= 0)
			return -1;

		State afterPlayerTurn = simulateTurn(state, false); // player turn
		if (state.bossHP <= 0)
			return costSoFar;

		int minCost = -1;
		for (Spell spell : spells)
		{
			State newState = useSpell(afterPlayerTurn, spell);
			if (newState != null)
			{
				newState = simulateTurn(newState, true); // boss turn
				int cost = getMinCost(newState, costSoFar + spell.cost);
				if (cost >= 0 && (minCost < 0 || cost < minCost))
				{
					minCost = cost;
					if (overallMinCost < 0 || minCost < overallMinCost)
						overallMinCost = minCost;
				}
			}
		}

		return minCost;
	}

	public static State useSpell(State previous, Spell spell)
	{
		int mana = previous.mana - spell.cost;
		if (mana >= 0 && !previous.effects.contains(spell.effect))
		{
			int playerHP = previous.playerHP + spell.healAmount;
			int bossHP = previous.bossHP - spell.damage;

			ArrayList<Effect> effects = (ArrayList<Effect>)previous.effects.clone();
			if (spell.effect != null)
				effects.add(spell.effect);

			return new State(effects, playerHP, bossHP, mana);
		}
		return null;
	}

	public static State simulateTurn(State previous, boolean bossTurn)
	{
		int armor = 0;
		int playerHP = previous.playerHP;
		int bossHP = previous.bossHP;
		int mana = previous.mana;
		ArrayList<Effect> effects = (ArrayList<Effect>)previous.effects.clone();

		for (int x = 0; x < effects.size(); x++)
		{
			Effect effect = effects.get(x);
			armor += effect.armorBonus;
			bossHP -= effect.damage;
			mana += effect.manaBoost;

			Effect newEffect = effect.useTurn();
			if (newEffect != null)
			{
				effects.set(x, newEffect);
			}
			else
			{
				effects.remove(x);
				x--;
			}
		}

		if (bossHP > 0 && bossTurn)
			playerHP -= Math.max(bossDamage - armor, 1);

		return new State(effects, playerHP, bossHP, mana);
	}

	public static void parseBossInfo() throws NumberFormatException, IOException
	{
		BufferedReader br = new BufferedReader(new FileReader(new File("").getAbsolutePath() + "/bin/day22.txt"));
		bossHP = Integer.parseInt(br.readLine().split(" ")[2]);
		bossDamage = Integer.parseInt(br.readLine().split(" ")[1]);
		br.close();
	}

	public static void addSpells()
	{
		spells = new ArrayList<Spell>();
		spells.add(new Spell(53, 4, 0, null));
		spells.add(new Spell(73, 2, 2, null));
		spells.add(new Spell(113, 0, 0, new Effect(7, 0, 0, 6)));
		spells.add(new Spell(173, 0, 0, new Effect(0, 3, 0, 6)));
		spells.add(new Spell(229, 0, 0, new Effect(0, 0, 101, 5)));
	}
}

class State
{
	public ArrayList<Effect> effects;
	public int playerHP;
	public int bossHP;
	public int mana;

	public State(ArrayList<Effect> e, int p, int b, int m)
	{
		effects = e;
		playerHP = p;
		bossHP = b;
		mana = m;
	}
}

class Effect
{
	public int armorBonus;
	public int damage;
	public int manaBoost;
	public int lengthRemaining;

	public Effect(int armor, int dmg, int mana, int length)
	{
		armorBonus = armor;
		damage = dmg;
		manaBoost = mana;
		lengthRemaining = length;
	}

	public Effect useTurn()
	{
		if (lengthRemaining <= 1)
			return null;
		return new Effect(armorBonus, damage, manaBoost, lengthRemaining - 1);
	}

	public boolean equals(Object obj)
	{
		if (!(obj instanceof Effect))
			return false;
		Effect e = (Effect)obj;
		return e.armorBonus == armorBonus && e.damage == damage && e.manaBoost == manaBoost;
	}
}

class Spell
{
	public int cost;
	public int damage;
	public int healAmount;
	public Effect effect;

	public Spell(int c, int d, int heal, Effect e)
	{
		cost = c;
		damage = d;
		healAmount = heal;
		effect = e;
	}
}
