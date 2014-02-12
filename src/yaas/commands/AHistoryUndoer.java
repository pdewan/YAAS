package yaas.commands;

import java.io.Serializable;

import bus.uigen.undo.Command;

import util.models.AListenableVector;
import util.models.ListenableVector;

public class AHistoryUndoer implements CommandHistory, Serializable {
	private static final long serialVersionUID = -2337339874104777286L;
	private ListenableVector<Command> historyList = new AListenableVector<Command>();
	private int nextCommandIndex = 0;
	private boolean safeToGetCommand;

	public int getSize() {
		return historyList.size();
	}

	public Command elementAt(int i) {
		return historyList.get(i);
	}

	public void addCommand(Command c) {
		historyList.add(c);
	}

	public boolean execute() {
		if (nextCommandIndex == historyList.size()) {
			setSafeToGetCommand(false);
			return false;

		}
		Command nextCommand = historyList.get(nextCommandIndex);
//		System.out.println("Execute Command:" + nextCommand);
		nextCommand.execute();
		System.out.println("Finished Command:" + nextCommand);

//		historyList.get(nextCommandIndex).execute();
		nextCommandIndex++;
		return true;
	}

	public boolean undo() {
		if (nextCommandIndex - 1 < 0) {
			setSafeToGetCommand(false);
			return false;
		}
		nextCommandIndex--;
		Command nextCommand = historyList.get(nextCommandIndex);

//		historyList.get(nextCommandIndex).undo();
		nextCommand.undo();
		System.out.println("Finished undo:" + nextCommand);


		return true;
	}
	
	public void deleteCommand() {

		historyList.remove(nextCommandIndex - 1);
	}

	public void deleteCommand(int pos) {

		historyList.remove(pos - 1);
		nextCommandIndex--;
	}

	public void setSafeToGetCommand(boolean newVal) {
		safeToGetCommand = newVal;
	}

	public boolean getSafeToGetCommand() {
		return safeToGetCommand;
	}

	public ListenableVector<Command> getCommands() {

		return historyList;
	}
}