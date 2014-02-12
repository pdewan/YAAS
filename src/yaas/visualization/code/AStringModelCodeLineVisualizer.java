package yaas.visualization.code;

import java.awt.Font;

import bus.uigen.shapes.AStringModel;
import shapes.AttributedTextShape;
import util.misc.Common;

public class AStringModelCodeLineVisualizer implements CodeLineVisualizer{

	public AttributedTextShape createTextShape() {
		return new AStringModel("");
	}

	public void hideTextShape(AttributedTextShape aTextShape) {
		aTextShape.setWidth(0);
		aTextShape.setHeight(0);
		
	}

	public void showTextShape(AttributedTextShape aTextShape) {
		aTextShape.setWidth(-1);
		aTextShape.setHeight(-1);
		
	}
	// need to get
	public int getLineSpacing(AttributedTextShape aTextShape) {
		int height = aTextShape.getHeight();
		if (height <= 0) {
			Font font = aTextShape.getFont();
			if (font == null) {
				font = Common.getDefaultFont();
			}
			height = Common.getFontHeight(font, null);
		}
		
		return (int) (height* (1 +DEFAULT_RELATIVE_LINE_SPACING));

	}

}
