package yaas.commands;


import bus.uigen.undo.Command;
import util.models.ListenableVector;

public interface CommandHistory {
	public void addCommand(Command c);
	public boolean undo();
	public boolean execute();
	public int getSize();
	public Command elementAt(int i);
	public boolean getSafeToGetCommand();
	void setSafeToGetCommand(boolean b);
	ListenableVector<Command> getCommands();
	public void deleteCommand();
	public void deleteCommand(int pos);
}
