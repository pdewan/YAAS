package yaas.visualization.code;

import shapes.AttributedTextShape;

public interface CodeLineVisualizer {
	public static final int DEFAULT_ABSOLUTE_LINE_SPACING = 10;
	public static final double  DEFAULT_RELATIVE_LINE_SPACING= 0.2;
	 AttributedTextShape createTextShape() ;
	void hideTextShape(AttributedTextShape aTextShape) ;
	void showTextShape(AttributedTextShape aTextShape);
	int getLineSpacing(AttributedTextShape aTextShape);

}
