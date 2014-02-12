package yaas.threads;

import yaas.VisualizationBasedVisualizer;
import yaas.Visualizer;
import yaas.commands.CommandHistory;
import yaas.controller.Control;

@SuppressWarnings("rawtypes")
public class BufferThread implements Runnable {

	private CommandHistory commandHistory;
	private Visualizer visualizer;
	private Control controller;

	public BufferThread(CommandHistory u, Control c, Visualizer v) {
		controller = c;
//		commandHistory = u;
		visualizer = v;
		commandHistory = v.getCommandHistory();
	}

	public void run() {
		while (true) {
			commandHistory.setSafeToGetCommand(true);
			visualizer.waitForNextBufferThreadStep();
			if (visualizer.getCanProceed()) {
				if (controller.getSynchronous()) {
					runAllCommands();
					controller.setSynchronous(false);
				} else {
					runCommand();
				}
				visualizer.setCanProceed(false);
			}
		}
	}

	private void runAllCommands() {
		while (visualizer.getCanProceed() && commandHistory.getSafeToGetCommand()) {
			runCommand();
		}
	}

	private void runCommand() {
		boolean commandSuccessfullyExecuted;
		if (controller.getMoveForward()) {
			commandSuccessfullyExecuted = commandHistory.execute();
		} else {
			commandSuccessfullyExecuted = commandHistory.undo();
		}
		if (commandSuccessfullyExecuted) {
			controller.commandExecuted();
		}
	}
}
