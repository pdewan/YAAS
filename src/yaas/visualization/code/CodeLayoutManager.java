package yaas.visualization.code;

import java.awt.Point;

import shapes.AttributedTextShape;
import util.javac.SourceMethod;

public interface CodeLayoutManager {
	public AttributedTextShape createHiddenTextShape() ;
//	public abstract void hideTextShape(AttributedTextShape aTextShape) ;
//	public abstract void showTextShape(AttributedTextShape aTextShape);
	
	
	public void methodPushed (SourceMethod aSourceMethod) ;
	
	
    public void methodPopped (SourceMethod aSourceMethod) ;
    
    public void newMethodPointer(int newVal) ;
    
    public AttributedTextShape getMethodLineVisualization(MethodCodeVisualization aMethodCodeVisualization, int aLineNumber);
    	
    
    public void displayCode(SourceMethod aSourceMethod, int aCurLevel, Point aStartLocation) ;
    
    public MethodCodeVisualization getMethodCodeVisualization(int curLevel);
    		
//    	
	public CodeLineVisualizer getCodeLineVisualizer() ;
	public void setCodeLineVisualizer(CodeLineVisualizer codeLineVisualizer) ;
	

}
