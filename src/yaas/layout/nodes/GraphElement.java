package yaas.layout.nodes;

import java.util.HashMap;

import bus.uigen.shapes.AnArcModel;
import util.models.ListenableVector;

public interface GraphElement extends VisualizerElement<GraphElement>{
	HashMap<GraphElement, AnArcModel >getLines();
	ListenableVector<GraphElement >getAdjacencyList();
}
