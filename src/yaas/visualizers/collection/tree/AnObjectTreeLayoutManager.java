package yaas.visualizers.collection.tree;

import shapes.BoundedTextShape;
import util.models.ListenableVector;
import yaas.shapemappers.ObjectToShapeTranslator;
import yaas.shapemappers.AnObjectToStringModel;
import yaas.shapemappers.AnObjectToTextModel;

public class AnObjectTreeLayoutManager  extends ATreeLayoutManager<Object>{

	public AnObjectTreeLayoutManager(
			TreeVisualizer<Object> cv) {
		super(cv);
		
//		ObjectToShapeTranslator<Object>  objectToShapeTranslator = new ObjectToStringModel(visualizer.getObjectColorManager());
		ObjectToShapeTranslator<Object>  objectToShapeTranslator = new AnObjectToStringModel();

		ObjectToShapeTranslator<ListenableVector>  vectorToShapeTranslator =new AnObjectToTextModel(visualizer.getObjectColorManager());

		setLeafNodeToContentShapeTranslator (objectToShapeTranslator);
		setInternalNodeToContentShapeTranslator (vectorToShapeTranslator);
		setRootNodeToContentShapeTranslator (vectorToShapeTranslator);


	
		

	}

}
