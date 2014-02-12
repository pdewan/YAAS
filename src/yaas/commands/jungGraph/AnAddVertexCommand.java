package yaas.commands.jungGraph;

import java.lang.annotation.ElementType;

import yaas.buffers.vector.ALinearBuffer;
import yaas.visualizers.jungGraph.AJungGraphVisualizer;

import bus.uigen.uiFrame;
import bus.uigen.jung.JungGraphManager;
import bus.uigen.reflect.MethodProxy;
import bus.uigen.undo.Command;
import bus.uigen.undo.CommandListener;
import edu.uci.ics.jung.graph.Graph;

public class AnAddVertexCommand<VertexType,EdgeType> implements Command{
	
	private Graph<VertexType,EdgeType> graph;
	private VertexType vertex;
	JungGraphManager<VertexType, EdgeType> jungGraphManager;
	AJungGraphVisualizer<VertexType, EdgeType> jungGraphVisualizer;
	
	public AnAddVertexCommand(Graph<VertexType,EdgeType> theGraph, VertexType theVertex) 
	{
		graph = theGraph;
		vertex = theVertex;
	}
	public AnAddVertexCommand(JungGraphManager<VertexType, EdgeType>  aJungGraphManager, VertexType theVertex) 
	{
		jungGraphManager = aJungGraphManager;
		vertex = theVertex;
	}
	public Object execute() {
//		graph.addVertex(vertex);
		jungGraphManager.addAndDisplayVertex(vertex);
//		jungGraphManager.addAndLayoutVertex(vertex);

		return null;
	}
	public void undo() {
		jungGraphManager.removeAndUndisplayVertex(vertex);
	}
	public Object getObject(){
		
		return vertex;
	}
	public void redo() {
		// TODO Auto-generated method stub
		
	}
	public Command clone(Object arg0, Object[] arg1, uiFrame arg2,
			CommandListener arg3) {
		// TODO Auto-generated method stub
		return null;
	}
	public MethodProxy getMethod() {
		// TODO Auto-generated method stub
		return null;
	}
	public boolean getNotUndoablePurgesUndoHistory() {
		// TODO Auto-generated method stub
		return false;
	}
	public boolean isNoOp() {
		// TODO Auto-generated method stub
		return false;
	}
	public boolean isUndoable() {
		// TODO Auto-generated method stub
		return false;
	}
	public boolean isVoid() {
		// TODO Auto-generated method stub
		return false;
	}
	public void setNotUndoablePurgesUndoHistory(boolean arg0) {
		// TODO Auto-generated method stub
		
	}
}
