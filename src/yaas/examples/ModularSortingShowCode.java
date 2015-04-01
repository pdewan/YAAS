package yaas.examples;

import java.awt.Point;

import util.javac.SourceClass;
import util.javac.SourceClassManager;
import util.javac.SourceCodeLines;
import util.javac.SourceMethod;
import yaas.common.Algorithms;
import yaas.layout.CompositeShapeLayoutManager;
import yaas.shapemappers.IntToLine;
import yaas.visualization.code.CodeLayoutManager;
import yaas.visualizers.collection.flat.AnIntegerBarChartEventTrapper;
import yaas.visualizers.collection.flat.AnIntegerBarChartLayoutManager;
import yaas.visualizers.collection.flat.AnotherIntegerBarChartEventTrapper;

public class ModularSortingShowCode extends ModularSorting {
	
	public static void setCodeParameters() {
		visualizer.setShowCode(true);
		SourceClass aSourceClass = SourceClassManager.getInstance().getOrCreateClassInfo(Algorithms.class, ".");
		SourceMethod sourceMethod = aSourceClass.getSourceMethodAtLineNumber(63);
		CodeLayoutManager codeLayoutManager = visualizer.getCodeLayoutManager();
		codeLayoutManager.displayCode(sourceMethod, 0, new Point (50, 50));
		
	}
	
	
	public static void main(String[] args) {
		fillFlatElements();
		createRootObjects();
		createAnimator();
		setCompositeLayout();
//		configureFlatLayout();
		addTextTrapper(vector);

		addFlatTrapper(vector);
		visualize(vector, configureFlatLayout());
		doSort(vector);
		setCodeParameters();
		displayAnimator();
	}

	

}
