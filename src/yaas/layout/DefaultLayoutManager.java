package yaas.layout;

import java.awt.Component;

import javax.swing.JPanel;

import bus.uigen.shapes.ListenableShapeVector;
import shapes.AttributedShape;
import shapes.BoundedShape;
import yaas.Visualizer;
import yaas.common.VestigalListenableVector;
import yaas.shapemappers.PointerShapeCreator;

public class DefaultLayoutManager<Observer, ObservableDataType /*extends EventGenerator<Observer, ObservableDataType>*/>
		implements VisualizationBasedLayoutManager<ObservableDataType> {
	
	public DefaultLayoutManager(
			Visualizer<Observer, ObservableDataType> aCollectionVisualizer) {
	}

	public util.models.ListenableVector<BoundedShape> display(ObservableDataType v) {
		return null;
	}

	public util.models.ListenableVector<AttributedShape> constructPseudoCode() {
		// TODO Auto-generated method stub
		return null;
	}

	public yaas.common.VestigalListenableVector<AttributedShape> getPseudoCode() {
		// TODO Auto-generated method stub
		return null;
	}

	public void incrementPseudoCodeMarker() {
		// TODO Auto-generated method stub
		
	}

	public void decrementPseudoCodeMarker() {
		// TODO Auto-generated method stub
		
	}

	public int getPseudoCodeMarker() {
		// TODO Auto-generated method stub
		return 0;
	}

	public JPanel displayInPanel(ObservableDataType data) {
		// TODO Auto-generated method stub
		return null;
	}

	public JPanel getPanel() {
		// TODO Auto-generated method stub
		return null;
	}

	public Component displayInPanel() {
		// TODO Auto-generated method stub
		return null;
	}

	public PointerShapeCreator getPointerShapeCreator() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setPointerShapeCreator(PointerShapeCreator newVal) {
		// TODO Auto-generated method stub
		
	}

	public ObservableDataType createBuffer() {
		// TODO Auto-generated method stub
		return null;
	}

	public int getPreferredHeight() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void setPreferredHeight(int newVal) {
		// TODO Auto-generated method stub
		
	}

	public int getPreferredWidth() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void setPreferredWidth(int newVal) {
		// TODO Auto-generated method stub
		
	}

	public ListenableShapeVector getContainingShapes(ObservableDataType data) {
		// TODO Auto-generated method stub
		return null;
	}

}
