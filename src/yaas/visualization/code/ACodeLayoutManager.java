package yaas.visualization.code;

import java.awt.Point;

import shapes.AttributedShape;
import shapes.AttributedTextShape;
import util.javac.SourceMethod;
import yaas.Visualizer;

public class ACodeLayoutManager implements CodeLayoutManager{
	Visualizer visualizer;
	CodeLineVisualizer codeLineVisualizer = new AStringModelCodeLineVisualizer();
	MethodsCodeVisualization methodsCodeVisualization;
	public static final int DEFAULT_METHOD_LENGTH = 15;
	int methodLength = DEFAULT_METHOD_LENGTH;
	int maxLevel = -1;
	int curLevel = -1;
	public ACodeLayoutManager(Visualizer aVisualizer) {
		visualizer = aVisualizer;
		methodsCodeVisualization = visualizer.getMethodsCodeVisualization();
	}
//	public abstract AttributedTextShape createTextShape() ;
	public AttributedTextShape createHiddenTextShape() {
		AttributedTextShape newShape = codeLineVisualizer.createTextShape();
		codeLineVisualizer.hideTextShape(newShape);
//		newShape.setText("");
//		newShape.setWidth(0);
//		newShape.setHeight(0);
		return newShape;
	}
//	public abstract void hideTextShape(AttributedTextShape aTextShape) ;
//	public abstract void showTextShape(AttributedTextShape aTextShape);
	
	
	public void methodPushed (SourceMethod aSourceMethod) {
		curLevel++;		
	}
	
	void initializeMethodVisualization (MethodCodeVisualization aMethodCodeVisualization) {
		for (int i = 0; i < methodLength; i++) {			
			aMethodCodeVisualization.add(createHiddenTextShape());
		}
	}
	
    public void methodPopped (SourceMethod aSourceMethod) {
		
	}
    
    public void newMethodPointer(int newVal) {
    	
    }
    
    public AttributedTextShape getMethodLineVisualization(MethodCodeVisualization aMethodCodeVisualization, int aLineNumber) {
    	if (aLineNumber > aMethodCodeVisualization.size()) {
    		aMethodCodeVisualization.add(createHiddenTextShape());	   		
    	}
    	return aMethodCodeVisualization.get(aLineNumber);
    }
    
    public void displayCode(SourceMethod aSourceMethod, int aCurLevel, Point aStartLocation) {
    	MethodCodeVisualization aMethodCodeVisualization = getMethodCodeVisualization(aCurLevel);
//    	curLevel = aCurLevel;
    	maxLevel = Math.max(maxLevel, aCurLevel);
    	String[] codeLines = aSourceMethod.getCodeInfo().getSourceCodeLines();
    	AttributedTextShape previousTextShape = null;
    	for (int i = 0; i < codeLines.length; i++) {
    		AttributedTextShape textShape = getMethodLineVisualization( aMethodCodeVisualization, i);
    		textShape.setText(codeLines[i]);
			textShape.setX(aStartLocation.x);
    		if (previousTextShape == null) {
    			textShape.setY(aStartLocation.y);
    		} else {
    			textShape.setY(previousTextShape.getY() + codeLineVisualizer.getLineSpacing(previousTextShape));
    		}
    		previousTextShape = textShape;
			codeLineVisualizer.showTextShape(textShape);   		
    	}    	
    }
    
    public MethodCodeVisualization getMethodCodeVisualization(int curLevel) {
//    	if (curLevel > maxLevel) {
    		for (int i = maxLevel + 1; i <= curLevel; i++) {
    			MethodCodeVisualization methodCodeVisualization = new AMethodCodeVisualization();
        		initializeMethodVisualization(methodCodeVisualization);
    			methodsCodeVisualization.add(methodCodeVisualization);
    		}
    		
//    	}
    	return methodsCodeVisualization.get(curLevel);
    	
    }
	public CodeLineVisualizer getCodeLineVisualizer() {
		return codeLineVisualizer;
	}
	public void setCodeLineVisualizer(CodeLineVisualizer codeLineVisualizer) {
		this.codeLineVisualizer = codeLineVisualizer;
	}

}
