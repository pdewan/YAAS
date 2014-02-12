package yaas.layout.nodes;

public interface LinkedTree<ElementType> {
	
	public void setParent(ElementType p);
	public ElementType getParent();
	public void setPreviousChild(ElementType p);
	public ElementType getPreviousChild();
	public ElementType getNextChild(int aMyIndex);
}
