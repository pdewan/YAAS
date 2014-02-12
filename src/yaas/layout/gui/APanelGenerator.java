package yaas.layout.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

import yaas.VisualizationBasedVisualizer;

import bus.uigen.ObjectEditor;
import bus.uigen.attributes.AttributeNames;

public class APanelGenerator {
	private JButton next = new JButton(">");
	private JButton previous = new JButton("<");
	private JButton last = new JButton(">>");
	private JButton first = new JButton("<<");

	private JPanel dataPanel = new JPanel();
	private JPanel codePanel = new JPanel();
	private JPanel buttonPanel = new JPanel();

	private JSplitPane dataAndCodePanel = new JSplitPane();

	public JPanel layoutAndPopulateButtonPanel() {
		buttonPanel.add(first);
		buttonPanel.add(previous);
		buttonPanel.add(next);
		buttonPanel.add(last);
		return buttonPanel;
	}
	public void bindButtons(VisualizationBasedVisualizer visualizer){
		ObjectEditor.bind(visualizer.getController(), "first", first);
		ObjectEditor.bind(visualizer.getController(), "previous", previous);
		ObjectEditor.bind(visualizer.getController(), "next", next);
		ObjectEditor.bind(visualizer.getController(), "last", last);
	}
	
	private void layoutAndPopulateCodeAndDataPanel() {
		dataPanel.setPreferredSize(new Dimension(300, 200));
		dataAndCodePanel.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
		dataAndCodePanel.setLeftComponent(dataPanel);
		dataAndCodePanel.setRightComponent(codePanel);

	}

	// static Class[] nullArgs = {};

	@SuppressWarnings("rawtypes")
	public JPanel creatView(VisualizationBasedVisualizer visualizer, Object code,
			boolean showButtons, boolean showCode) {

//		if (showCode) {
//			layoutAndPopulateCodeAndDataPanel();
//		}

		dataPanel.add(layoutAndPopulateButtonPanel(), BorderLayout.NORTH);
		ObjectEditor.editInDrawingContainer(visualizer.getVisualization(), dataPanel, false);
		
//		ObjectEditor.editInDrawingContainer(code, codePanel, false);

	//	this.bindButtons(visualizer);

		return dataPanel;

	}
}
