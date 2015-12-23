import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Day23
{
	public static HashMap<String, Integer> registers;
	public static ArrayList<String> instructions;
	public static int currentInstruction;

	public static void main(String[] args) throws IOException
	{
		initialize();
		addInstructions();
		runInstructions();

		System.out.println("Part 1: " + registers.get("b"));

		initializePart2();
		runInstructions();

		System.out.println("Part 2: " + registers.get("b"));
	}

	public static void runInstructions()
	{
		while (currentInstruction < instructions.size())
		{
			parseInstruction(instructions.get(currentInstruction));
		}
	}

	public static void parseInstruction(String instruction)
	{
		String[] splitted = instruction.split(" ");
		String type = splitted[0];

		if (type.equals("hlf") || type.equals("tpl") || type.equals("inc"))
		{
			String register = splitted[1];

			if (type.equals("hlf"))
				registers.put(register, registers.get(register) / 2);
			else if (type.equals("tpl"))
				registers.put(register, registers.get(register) * 3);
			else if (type.equals("inc"))
				registers.put(register, registers.get(register) + 1);

			currentInstruction++;
		}
		else if (type.equals("jmp"))
		{
			int offset = parseOffset(splitted[1]);
			currentInstruction += offset;
		}
		else if (type.equals("jie") || type.equals("jio"))
		{
			String register = splitted[1].substring(0, 1);
			int offset = parseOffset(splitted[2]);
			int regValue = registers.get(register);

			if (type.equals("jie") && regValue % 2 == 0 || type.equals("jio") && regValue == 1)
				currentInstruction += offset;
			else
				currentInstruction++;
		}
	}

	public static int parseOffset(String offset)
	{
		return Integer.parseInt(offset.startsWith("+") ? offset.substring(1) : offset);
	}

	public static void addInstructions() throws IOException
	{
		BufferedReader br = new BufferedReader(new FileReader(new File("").getAbsolutePath() + "/bin/day23.txt"));
		String input;
		do
		{
			input = br.readLine();
			if (input != null)
			{
				instructions.add(input);
			}
		}
		while (input != null);
		br.close();
	}

	public static void initialize()
	{
		registers = new HashMap<String, Integer>();
		registers.put("a", 0);
		registers.put("b", 0);
		instructions = new ArrayList<String>();
		currentInstruction = 0;
	}

	public static void initializePart2()
	{
		registers.put("a", 1);
		registers.put("b", 0);
		currentInstruction = 0;
	}
}
