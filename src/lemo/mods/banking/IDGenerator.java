package lemo.mods.banking;

public class IDGenerator {
	static int currentId = 3000;

	public static int getID() {
		return currentId++;
	}
}
