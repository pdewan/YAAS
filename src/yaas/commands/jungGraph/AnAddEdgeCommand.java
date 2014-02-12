package yaas.commands.jungGraph;

import java.lang.annotation.ElementType;

import yaas.buffers.vector.ALinearBuffer;

import bus.uigen.uiFrame;
import bus.uigen.jung.JungGraphManager;
import bus.uigen.reflect.MethodProxy;
import bus.uigen.undo.Command;
import bus.uigen.undo.CommandListener;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.util.Pair;

public class AnAddEdgeCommand<VertexType,EdgeType> implements Command{
	
	private Graph<VertexType,EdgeType> graph;
	private VertexType vertex1, vertex2;
	EdgeType edge;
	JungGraphManager<VertexType, EdgeType> jungGraphManager;

	
//	public AnAddEdgeCommand(Graph<VertexType,EdgeType> theGraph, EdgeType theEdge, VertexType theVertex1, VertexType theVertex2 ) 
//	{
//		graph = theGraph;
//		vertex1 = theVertex1;
//		vertex2 = theVertex2;
//		edge = theEdge;
//	}
//	public AnAddEdgeCommand(Graph<VertexType,EdgeType> theGraph, EdgeType theEdge, Pair<VertexType> theVerticies ) 
//	{
//		graph = theGraph;
//		vertex1 = theVerticies.getFirst();
//		vertex2 = theVerticies.getSecond();;
//		edge = theEdge;
//	}
	public AnAddEdgeCommand(JungGraphManager<VertexType, EdgeType> aJungGraphManager, EdgeType theEdge, Pair<VertexType> theVerticies ) 
	{
		jungGraphManager = aJungGraphManager;
		vertex1 = theVerticies.getFirst();
		vertex2 = theVerticies.getSecond();;
		edge = theEdge;
	}
	public AnAddEdgeCommand(JungGraphManager<VertexType, EdgeType> aJungGraphManager, EdgeType theEdge, VertexType aFirstVertex, VertexType aSecondVertex ) 
	{
		jungGraphManager = aJungGraphManager;
		vertex1 = aFirstVertex;
		vertex2 = aSecondVertex;
		edge = theEdge;
	}
	public Object execute() {
		jungGraphManager.addAndDisplayEdge(edge, vertex1, vertex2);
		return null;
	}
	public void undo() {
		jungGraphManager.removeAndDisplayEdge(edge);
	}
	public Object getObject(){
		
		return edge;
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
