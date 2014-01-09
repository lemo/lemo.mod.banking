package lemo.mods.banking;

public class IDGenerator {
	static int currentId = 7000;

	public static int getID() {
		return currentId++;
	}
}
