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

public class ARemoveEdgeCommand<VertexType,EdgeType> implements Command{
	
	private Graph<VertexType,EdgeType> graph;
	private Pair<VertexType> endPoints;
	EdgeType edge;
	JungGraphManager<VertexType, EdgeType> jungGraphManager;

	
	public ARemoveEdgeCommand(JungGraphManager<VertexType, EdgeType> aJungGraphManager,Graph<VertexType,EdgeType> theGraph, EdgeType theEdge ) 
	{
		graph = theGraph;		
		edge = theEdge;
		endPoints = graph.getEndpoints(theEdge);
		jungGraphManager = aJungGraphManager;
	}
	
	
	public Object execute() {
		jungGraphManager.removeAndDisplayEdge(edge);
		return null;
	}
	public void undo() {
		graph.addEdge(edge, endPoints);
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
