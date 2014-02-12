package yaas.visualizers.collection.tree;

import shapes.BoundedShape;
import shapes.BoundedTextShape;

public class AnObjectTreeVisualizer extends ATreeVisualizer<BoundedTextShape> {

//	public AnObjectTreeVisualizer(BoundedShape shape) {
//		super(shape);
//		// TODO Auto-generated constructor stub
//	}
	public TreeLayoutManager<Object> createLayoutManager() {
		return new AnObjectTreeLayoutManager(this);
	}

}
