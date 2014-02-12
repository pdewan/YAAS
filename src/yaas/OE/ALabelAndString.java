package yaas.OE;

import shapes.LabelShape;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import bus.uigen.OEFrame;
import bus.uigen.ObjectEditor;
import bus.uigen.shapes.ALabelModel;

@StructurePattern(StructurePatternNames.BEAN_PATTERN)
public class ALabelAndString {
	String GREETING = "hello";
	LabelShape labelModel = new ALabelModel(GREETING, null, 0, 0, 100, 20);
	String text = "hello";

	public LabelShape getLabelModel() {
		return labelModel;
	}

	public void setLabelModel(LabelShape labelModel) {
		this.labelModel = labelModel;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
		labelModel.setText(text);
	}

	public static void main(String args[]) {
		ALabelAndString obj = new ALabelAndString();
		OEFrame frame = ObjectEditor.edit(obj);

		try {
			Thread.sleep(5000);
			frame.select(obj, "text");
			Thread.sleep(5000);
			frame.select(obj, "text");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
