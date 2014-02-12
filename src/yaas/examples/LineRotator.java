package yaas.examples;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import shapes.FlexibleLineShape;
import shapes.Rotatable;
import yaas.animators.AnimationUtil;
import bus.uigen.ObjectEditor;
import bus.uigen.shapes.ALineModel;

public class LineRotator {
	public static void main (String[] args) {
		FlexibleLineShape polarLine1 = new ALineModel(50, 50, 50, 0);
		FlexibleLineShape polarLine2 = new ALineModel(100, 100, 50, 0);
		List<FlexibleLineShape> lines = new ArrayList(); 
		lines.add(polarLine1);
		lines.add(polarLine2);
		ObjectEditor.edit(lines);
		Double[] angles = {Math.PI/4, Math.PI/2, 3*Math.PI/4};
		List<Double[]> angleListList = new ArrayList();
		angleListList.add(angles);
		angleListList.add(angles);
		AnimationUtil.moveLocationAndRotateShapes(
				polarLine1, 
				new Point (150, 150), 
				lines, angleListList);

//		ObjectEditor.edit(polarLine1);
//		AnimationUtil.rotate(polarLine, new Double[]{Math.PI/4, Math.PI/2, 3*Math.PI/4});
//		AnimationUtil.rotate(polarLine, new Double[]{Math.PI/4, Math.PI/2, 3*Math.PI/4});
//		AnimationUtil.moveLocationAndRotate(polarLine1,
//				new Point (150, 150),
//				new Double[]{Math.PI/4, Math.PI/2, 3*Math.PI/4});

	}

}
