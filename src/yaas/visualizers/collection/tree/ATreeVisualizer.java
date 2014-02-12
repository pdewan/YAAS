package yaas.visualizers.collection.tree;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import shapes.BoundedShape;
import shapes.LabelShape;
import shapes.TextShape;
import util.models.AListenableVector;
import util.models.Hashcodetable;
import util.models.ListenableVector;
import util.models.VectorMethodsListener;
import yaas.AVisualizationtBasedVisualizer;
import yaas.animators.AnimationUtil;
import yaas.buffers.vector.ATreeBuffer;
import yaas.controller.AGenericButtonPressTrapper;
import yaas.controller.ButtonPressTrapper;
import yaas.layout.LayoutManager;
import yaas.layout.nodes.ASimpleTreeNodeSkeleton;
import yaas.layout.nodes.ATreeNode;
import yaas.layout.nodes.ATreeNodeShape;
import yaas.layout.nodes.ATreeRoot;
import yaas.layout.nodes.TreeNode;
import yaas.layout.nodes.TreeNodeShape;
import yaas.shapemappers.ADefaultVectorLabelMapper;
import yaas.shapemappers.LabelMapper;
import yaas.visualization.collection.ACollectionVisualization;
import yaas.visualization.collection.CollectionVisualization;
import yaas.visualizers.collection.ACollectionVisualizer;
import bus.uigen.shapes.ALabelModel;
import bus.uigen.shapes.ALineModel;

@SuppressWarnings("rawtypes")
public class ATreeVisualizer<ElementShapeType>
		extends
//		AShapeBasedVisualizer<VectorMethodsListener<Object>, ListenableVector<Object>>
		ACollectionVisualizer<Object>
		implements TreeVisualizer<Object> {

	private static final long serialVersionUID = 2125646954227767702L;
//	private ListenableVector<ALineModel> LINE_VEC = new AListenableVector<ALineModel>();
//	private ListenableVector<SimpleShape> shapes = new AListenableVector();
//	private TreeNode rootTreeNode;
//	private boolean showLines = true;
//	private boolean showRoot = true;
//	private ALabelModel label;
//	Hashcodetable<Object, TreeNode> valueToTreeNode = new Hashcodetable();
//	Hashcodetable<Object, TreeNodeShape> valueToTreeNodeShape = new Hashcodetable();
//	boolean isDisplayName = true;
//	LabelMapper treeNodeLabelMapper = new ADefaultVectorLabelMapper();


//	private int initX = 20, initY = 20;
////	List<VectorMethodsListener<Object>> replayMethodsListener = new ArrayList();
//	public static final int EXPECTED_SHAPES = 10;

	/**************** Initialization *******************************/
	public ATreeVisualizer() {
		super();
//		this.getShapes().addAll(LINE_VEC);
//		shapes = new ACollectionVisualization();

//		animationPauseTime = 10;

//		layoutManager = createLayoutManager();
//		((ATreeLayoutManager<Object>) layoutManager).setShape(shape);
	}
	
//	public TreeLayoutManager<Object> createLayoutManager() {
//		return new ATreeLayoutManager<Object>(this);
//	}


	



	


//    @Override
//    public synchronized void visualize(ListenableVector<Object> data, LayoutManager aLayoutManager)  {
//          super.visualize(data, aLayoutManager);
//
//    }


























//	@util.annotations.Visible(false)
//	public ATreeLayoutManager<Object> getLayoutManagerOfRootObject(ListenableVector aBuffer) {
//		return (ATreeLayoutManager<Object>) super.getLayoutManagerOfRootObject(aBuffer);
//	}

//	/************************* Visualizer Methods ***********************************/
//	protected ListenableVector<Object> createBuffer() {
//		// TODO Auto-generated method stub
//		return new ATreeBuffer (this);
//	}
//	@Override
//	public ButtonPressTrapper initializeButtonPressTrapper() {
//		return new ATreeButtonPressTrapper(controller, this);
//	}
//	public ButtonPressTrapper initializeButtonPressTrapper() {
//		return new AGenericButtonPressTrapper(this.getController(), this, 40);
//	}
//	@Override
//	public ButtonPressTrapper initializeButtonPressTrapper() {
//		return null;
//	}

//	public void addReplayMethodListener(
//			VectorMethodsListener<Object> anObserver) {
////		buffer.addVectorMethodsListener(anObserver);
//		replayMethodsListener.add(anObserver);
//		getRootBuffer().addVectorMethodsListener(anObserver);
//		
//	}
	
//	public List<VectorMethodsListener<Object>> getReplayMethodListeners() {
//		return replayMethodsListener;
//	}
//	public CollectionVisualization getVisualization() {
//		// TODO Auto-generated method stub
//		return (CollectionVisualization) shapes;
//	}
////	@Override
//	public boolean isDisplayName() {
//		return isDisplayName;
//	}
////	@Override
////	@Implement
//
//	public void setDisplayName(boolean isDisplayName) {
//		this.isDisplayName = isDisplayName;
//	}
	
//
//	public boolean removeElement(SimpleShape c) {
//		// TODO Auto-generated method stub
//		return false;
//	}
}
