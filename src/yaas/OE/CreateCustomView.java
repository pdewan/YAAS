package yaas.OE;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

import yaas.VestigalVisualizer;
import yaas.VisualizationBasedVisualizer;
import yaas.Visualizer;
import bus.uigen.ObjectEditor;
import bus.uigen.uiFrame;
import bus.uigen.attributes.AttributeNames;

public class CreateCustomView {
	private JButton next = new JButton(">");
	private JButton previous = new JButton("<");
	private JButton last = new JButton(">>");
	private JButton first = new JButton("<<");

	private Component dataPanel = new JPanel();
	private Component codePanel = new JPanel();
	private Container buttonPanel = new JPanel();

	private JSplitPane dataAndCodePanel = new JSplitPane();

	private JFrame frame = new JFrame();	
	

	private void layoutAndPopulateButtonPanel() {
		buttonPanel.add(first);
		buttonPanel.add(previous);
		buttonPanel.add(next);
		buttonPanel.add(last);
	}

	JPanel topPanel = new JPanel();
	
	int frameWidth = 600;
	int frameHeight = 600;
	
	public CreateCustomView (int aFrameWidth, int aFrameHeight) {
		frameWidth = aFrameWidth;
		aFrameHeight = aFrameHeight;
	}
	
	public CreateCustomView () {
		
	}

	@SuppressWarnings("deprecation")
	private void layoutAndPopulateFrame(boolean showCode) {

		frame.getContentPane().setLayout(new BorderLayout());
//		buttonPanel.setBackground(Color.green);
		frame.getContentPane().add(buttonPanel, BorderLayout.NORTH);
		if (showCode) {
			frame.getContentPane().add(dataAndCodePanel);
		} else {
			frame.getContentPane().add(dataPanel);
//			dataPanel.setBackground(Color.yellow);
		}
		frame.setSize(600, 400);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setCursor(Cursor.DEFAULT_CURSOR);

	}

	private void layoutAndPopulateCodeAndDataPanel() {
		dataPanel.setPreferredSize(new Dimension(300, 200));
		dataAndCodePanel.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
		dataAndCodePanel.setLeftComponent(dataPanel);
		dataAndCodePanel.setRightComponent(codePanel);

	}

	// static Class[] nullArgs = {};

	@SuppressWarnings("rawtypes")
	public uiFrame createNestedView(Object[] menuItems,
			VisualizationBasedVisualizer visualizer, Object code, boolean showButtons,
			boolean showCode) {
		ObjectEditor.setDefaultAttribute(
				AttributeNames.PREDEFINED_MENUS_CHOICE, new String[] {});
		ObjectEditor.setDefaultAttribute(AttributeNames.SEPARATE_THREAD, true);
		ObjectEditor.setDefaultAttribute(AttributeNames.SHOW_SYSTEM_MENUS,
				false);

		if (showButtons) {
			layoutAndPopulateButtonPanel();
		}
		if (showCode) {
			layoutAndPopulateCodeAndDataPanel();
		}

		uiFrame editor = ObjectEditor.createOEFrame(frame);

		Object[] menuObjects = menuItems;
		ObjectEditor.addMenuObjects(editor, menuObjects);

		layoutAndPopulateFrame(showCode);

		// ObjectEditor.editInDrawingContainer(editor, visualizer, dataPanel,
		// false);

		ObjectEditor.editInDrawingContainer(code, (Container) codePanel, false);

		ObjectEditor.bind(editor, visualizer.getController(), "first", first);
		ObjectEditor.bind(editor, visualizer.getController(), "previous",
				previous);
		ObjectEditor.bind(editor, visualizer.getController(), "next", next);
		ObjectEditor.bind(editor, visualizer.getController(), "last", last);

		return editor;

	}

	public void setDataPanel(Component panel) {
		dataPanel = panel;
	}
	public Component getDataPanel() {
		return dataPanel;
	}
	public JSplitPane getDataAndCodePanel() {
		return dataAndCodePanel;
	}
	public uiFrame createView(Object[] menuItems,
			Visualizer visualizer) {
		return createView(menuItems, visualizer, visualizer.getPseudoCode(), visualizer.getShowControls(), visualizer.getShowPsuedoCode(), visualizer.getShowData());
		
	}

	@SuppressWarnings("rawtypes")
	public uiFrame createView(Object[] menuItems,
			Visualizer visualizer, Object code, boolean showButtons,
			boolean showCode, boolean showData) {
//		ObjectEditor.setDefaultAttribute(
//				AttributeNames.PREDEFINED_MENUS_CHOICE, new String[] {});
		ObjectEditor.setDefaultAttribute(AttributeNames.SEPARATE_THREAD, true);
//		ObjectEditor.setDefaultAttribute(AttributeNames.SHOW_SYSTEM_MENUS,
//				false);

		if (showButtons) {
			layoutAndPopulateButtonPanel();
		}
		if (showCode) {
			layoutAndPopulateCodeAndDataPanel();
		}

		uiFrame editor = ObjectEditor.createOEFrame(frame);
		
		if (menuItems != null) {

		Object[] menuObjects = menuItems;
		ObjectEditor.addMenuObjects(editor, menuObjects);
		}

		layoutAndPopulateFrame(showCode);
		dataPanel.setBackground(AttributeNames.DEFAULT_GRAPHICS_COLOR);
		codePanel.setBackground(AttributeNames.DEFAULT_MAIN_WINDOW_COLOR);
		if (showData & visualizer instanceof VisualizationBasedVisualizer) {
			
			VisualizationBasedVisualizer oeVisualizer = (VisualizationBasedVisualizer) visualizer;
//		ObjectEditor.editInDrawingContainer(editor, visualizer, (Container) dataPanel,
//				false);
		ObjectEditor.editInDrawingContainer(editor, oeVisualizer.getVisualization(), (Container) dataPanel,
				false);
		}
		// check for code != null
		if (code != null)
		ObjectEditor.editInDrawingContainer(code, (Container) codePanel, false);

		ObjectEditor.bind(editor, visualizer.getController(), "first", first);
		ObjectEditor.bind(editor, visualizer.getController(), "previous",
				previous);
		ObjectEditor.bind(editor, visualizer.getController(), "next", next);
		ObjectEditor.bind(editor, visualizer.getController(), "last", last);

		return editor;

	}

}
