package yaas.animators;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import shapes.AttributedShape;
import shapes.BoundedShape;
import shapes.FlexibleShape;
import shapes.Rotatable;
import util.misc.Common;
//import shapes.RotatableBoundedShape;
import util.misc.ThreadSupport;
import yaas.layout.nodes.TreeNodeShape;

public abstract class AnimationUtil {
	public static int DEFAULT_ANIMATION_STEP = 10;
	public static int DEFAULT_ANIMATION_PAUSE_TIME = 100;
	public static int DEFAULT_NUM_STEPS = 6;

	private static int animationStep =  DEFAULT_ANIMATION_STEP;
	private static int animationPauseTime = DEFAULT_ANIMATION_PAUSE_TIME;
	static int numSteps = DEFAULT_NUM_STEPS;
	static AnimationStepGenerator animationStepGenerator = new AnAnimationStepGenerator();
//	static ObjectIncrementer<SimpleShape, Rectangle> boundsIncrementer = new ACartesianShapeDimensionIncrementer();
	static RangeBasedObjectIncrementer<BoundedShape, Integer> xIncrementer1 = new AShapeXIncrementer();
	static RangeBasedObjectIncrementer<BoundedShape, Integer> xIncrementer2 = new AShapeXIncrementer();


//	static ObjectIncrementer<AttributedShape, Rectangle> adjustingBoundsIncrementer = new AnInheritingShapeBoundsIncrementer(true);

	static RangeBasedObjectIncrementer<BoundedShape, Rectangle> boundsIncrementer1 = new AShapeBoundsIncrementer();
	static RangeBasedObjectIncrementer<BoundedShape, Rectangle> boundsIncrementer2 = new AShapeBoundsIncrementer();
	
	static RangeBasedObjectIncrementer<BoundedShape, Point> locationIncrementer1 = new AShapeLocationIncrementer();
	static RangeBasedObjectIncrementer<BoundedShape, Point> locationIncrementer2 = new AShapeLocationIncrementer();
	
	static RangeBasedObjectIncrementer<Rotatable, Double> angleIncrementer = new ARangeBasedShapeAngleIncrementer();

	
//	static ObjectIncrementer<BoundedShape, Dimension> dimensionIncrementer = new AShapeDimensionIncrementer();
	static RangeBasedObjectIncrementer<BoundedShape, Dimension> dimensionIncrementer = new AShapeDimensionIncrementer();

	
	static Dimension zeroDimension = new Dimension(0, 0);

	

	
	static RangeBasedObjectIncrementer<List<? extends BoundedShape>, List<Rectangle>> shapeListBoundsIncrementer = new ACompositeShapeListBoundsIncrementer(); 
	static RangeBasedObjectIncrementer<List<? extends BoundedShape>, List<Dimension>> shapeListSizeIncrementer = new ACompositeShapeListDimensionIncrementer(); 
	static RangeBasedObjectIncrementer<List<? extends BoundedShape>, List<Dimension>> shapeListSizeIncrementer2 = new ACompositeShapeListDimensionIncrementer(); 
	static RangeBasedObjectIncrementer<List<? extends BoundedShape>, List<Point>> shapeListLocationIncrementer = new ACompositeShapeListLocationIncrementer(); 
	static RangeBasedObjectIncrementer<List<? extends BoundedShape>, List<Integer>> shapeListXIncrementer = new ACompositeShapeListXIncrementer(); 
	static RangeBasedObjectIncrementer<List<? extends BoundedShape>, List<Dimension>> cartesianShapeListSizeIncrementer = new ACompositeCartesianShapeListDimensionIncrementer(); 
	
	static RangeBasedObjectIncrementer<List<? extends Rotatable>, List<Double>> shapeListAnglesIncrementer = new ACompositeShapeListAngleIncrementer(); 
	static ListBasedCompositeIncrementer<Rotatable, Double> listBasedShapeListAnglesIncrementer = new ACompositeCirculatingListBasedShapeListAngleIncrementer(); 

	static ListBasedObjectIncrementer< Rotatable, Double> listBasedAngleIncrementer = new  AListBasedShapeAngleIncrementer();

//	static ObjectIncrementer<BoundedShape, Rectangle> boundsIncrementer1 = new ACompositeShapeBoundsIncrementer();
//	static ObjectIncrementer<BoundedShape, Rectangle> boundsIncrementer2 = new ACompositeShapeBoundsIncrementer();

	static RangeBasedObjectIncrementer<BoundedShape, BoundedShape> oeShapeAttributesIncrementer = new AnOEShapeAttributesIncrementer();

	public static int getNumSteps() {
		return numSteps;
	}
	public static void setNumSteps(int numSteps) {
		AnimationUtil.numSteps = numSteps;
	}
	
	public static void rotate (Rotatable aRotatable, Double[] anAngleList) {
		animationStepGenerator.clearListeners();
		listBasedAngleIncrementer.setCirculate(true);
		listBasedAngleIncrementer.init(aRotatable, anAngleList);
		animationStepGenerator.addListener(listBasedAngleIncrementer);
		animationStepGenerator.animate();

	}
//	public static synchronized void moveLocationAndRotate(RotatableBoundedShape toBeMoved, Point newLocation, Double[] anAngleList) {

	public static synchronized void moveLocationAndRotate(BoundedShape toBeMoved, Point newLocation, Double[] anAngleList) {
		;
			animationStepGenerator.clearListeners();
			locationIncrementer1.init(toBeMoved, newLocation);
			listBasedAngleIncrementer.setCirculate(true);
//			listBasedAngleIncrementer.init(toBeMoved, anAngleList);
			listBasedAngleIncrementer.init((Rotatable)toBeMoved, anAngleList);

			 animationStepGenerator.addListener(locationIncrementer1);
				animationStepGenerator.addListener(listBasedAngleIncrementer);

			 animationStepGenerator.animate();
			
			
		}
	public static synchronized void moveLocationAndRotateShapes(
			BoundedShape toBeMoved, Point newLocation, 
			List<? extends Rotatable> rotatables, 
			List<Double[]> angleListList) {
		;
			animationStepGenerator.clearListeners();
			locationIncrementer1.init(toBeMoved, newLocation);
			
			listBasedShapeListAnglesIncrementer.init(rotatables, angleListList);
			 animationStepGenerator.addListener(locationIncrementer1);		 
			 animationStepGenerator.addListener(listBasedShapeListAnglesIncrementer);
			 animationStepGenerator.animate();
			
		}
	public static synchronized void swapX(BoundedShape aShape1, BoundedShape aShape2) {
		int newShape1X = aShape2.getX();
		int newShape2X = aShape1.getX();
		animationStepGenerator.clearListeners();
		 animationStepGenerator.addListener(xIncrementer1);
		 animationStepGenerator.addListener(xIncrementer2);
		 xIncrementer1.init(aShape1, newShape1X);
		 xIncrementer2.init(aShape2, newShape2X);
		 animationStepGenerator.animate();
		
	}
	public static synchronized void swapBounds(BoundedShape aShape1, BoundedShape aShape2) {
		Rectangle newShapeBounds1 = aShape2.getBounds();
		Rectangle newShapeBounds2 = aShape1.getBounds();

		animationStepGenerator.clearListeners();
		 animationStepGenerator.addListener(boundsIncrementer1);
		 animationStepGenerator.addListener(boundsIncrementer2);
		 boundsIncrementer1.init(aShape1, new Rectangle (newShapeBounds1));
		 boundsIncrementer2.init(aShape2, new Rectangle (newShapeBounds2));
		 animationStepGenerator.animate();
		
	}
	public static synchronized void swapLocations(BoundedShape aShape1, BoundedShape aShape2) {
		Rectangle newShapeBounds1 = aShape2.getBounds();
		Rectangle newShapeBounds2 = aShape1.getBounds();

		animationStepGenerator.clearListeners();
		 animationStepGenerator.addListener(locationIncrementer1);
		 animationStepGenerator.addListener(locationIncrementer2);
		 locationIncrementer1.init(aShape1, new Point (newShapeBounds1.x, newShapeBounds1.y));
		 locationIncrementer2.init(aShape2, new Point (newShapeBounds2.x, newShapeBounds2.y));
		 animationStepGenerator.animate();
		
	}
	public static synchronized void moveInsertAndDelete(
			BoundedShape fromShape, 
			BoundedShape toShape, 
			List<? extends BoundedShape> toBeRemoved, 
			List <? extends BoundedShape> toBeAdded,
			List <Dimension> addedSizeList) {
		Rectangle fromBounds = fromShape.getBounds();
		Rectangle toBounds = toShape.getBounds();

		animationStepGenerator.clearListeners();
		 animationStepGenerator.addListener(locationIncrementer1);
		 locationIncrementer1.init(fromShape, new Point (toBounds.x, toBounds.y));
		 List<Dimension> zeroSizeList =modifyForShrinking(toBeRemoved);
		 animationStepGenerator.addListener(shapeListSizeIncrementer);
		 shapeListSizeIncrementer.init(toBeRemoved, zeroSizeList);
		 animationStepGenerator.addListener(shapeListSizeIncrementer2);
		 shapeListSizeIncrementer2.init(toBeAdded, addedSizeList);
		 animationStepGenerator.animate();
		
	}
	public static synchronized void moveLocationAndMakeDisappear(BoundedShape toDisappear, List<BoundedShape> toBeMoved, List<Point> newLocations) {
		Dimension newDimension = new Dimension (0, 0);
		animationStepGenerator.clearListeners();
		shapeListLocationIncrementer.init(toBeMoved, newLocations);
		dimensionIncrementer.init(toDisappear, newDimension);
		 animationStepGenerator.addListener(shapeListLocationIncrementer);
		 
		 animationStepGenerator.addListener(dimensionIncrementer);
		 animationStepGenerator.animate();
		
		
	}
	
	public static synchronized void moveXAndMakeDisappear(BoundedShape toDisappear, List<BoundedShape> toBeMoved, List<Integer> newLocations) {
		Dimension newDimension = new Dimension (0, 0);
		animationStepGenerator.clearListeners();
		shapeListXIncrementer.init(toBeMoved, newLocations);
		dimensionIncrementer.init(toDisappear, newDimension);
		 animationStepGenerator.addListener(shapeListXIncrementer);
		 
		 animationStepGenerator.addListener(dimensionIncrementer);
		 animationStepGenerator.animate();
		
		
	}
	public static synchronized void moveX(List<BoundedShape> toBeMoved, List<Integer> newLocations) {
		Dimension newDimension = new Dimension (0, 0);
		animationStepGenerator.clearListeners();
		shapeListXIncrementer.init(toBeMoved, newLocations);
		 animationStepGenerator.addListener(shapeListXIncrementer);
		 
		 animationStepGenerator.animate();
		
		
	}
	public static synchronized void moveLocations(List<BoundedShape> toBeMoved, List<Point> newLocations) {
		Dimension newDimension = new Dimension (0, 0);
		animationStepGenerator.clearListeners();
		shapeListLocationIncrementer.init(toBeMoved, newLocations);
		 animationStepGenerator.addListener(shapeListLocationIncrementer);
		 
		 animationStepGenerator.animate();
		
		
	}
	public static synchronized void changeAngles(List<BoundedShape> toBeRotated, List<Point> newAngles) {
		Dimension newDimension = new Dimension (0, 0);
		animationStepGenerator.clearListeners();
		shapeListLocationIncrementer.init(toBeRotated, newAngles);
		 animationStepGenerator.addListener(shapeListAnglesIncrementer);
		 
		 animationStepGenerator.animate();
		
		
	}
	
	public static synchronized void moveXAndMakeAppear(BoundedShape toAppear, List<BoundedShape> toBeMoved, List<Integer> newLocations) {
		Dimension newShapeDimension = new Dimension (toAppear.getWidth(), toAppear.getHeight());
		toAppear.setWidth(0);
		toAppear.setHeight(0);
		animationStepGenerator.clearListeners();
		shapeListXIncrementer.init(toBeMoved, newLocations);
		dimensionIncrementer.init(toAppear, newShapeDimension);
		 animationStepGenerator.addListener(shapeListXIncrementer);
		 
		 animationStepGenerator.addListener(dimensionIncrementer);
		 animationStepGenerator.animate();
		
		
	}
	public static synchronized void moveXAndNewSize(BoundedShape toAppear, Dimension toAppearDimension, List<BoundedShape> toBeMoved, List<Integer> newLocations) {
	;
		animationStepGenerator.clearListeners();
		shapeListXIncrementer.init(toBeMoved, newLocations);
		dimensionIncrementer.init(toAppear, toAppearDimension);
		 animationStepGenerator.addListener(shapeListXIncrementer);
		 
		 animationStepGenerator.addListener(dimensionIncrementer);
		 animationStepGenerator.animate();
		
		
	}
	public static synchronized void moveLocationsAndNewSize(BoundedShape toAppear, Dimension toAppearDimension, List<BoundedShape> toBeMoved, List<Point> newLocations) {
	;
		animationStepGenerator.clearListeners();
		shapeListLocationIncrementer.init(toBeMoved, newLocations);
		dimensionIncrementer.init(toAppear, toAppearDimension);
		 animationStepGenerator.addListener(shapeListLocationIncrementer);		 
		 animationStepGenerator.addListener(dimensionIncrementer);
		 animationStepGenerator.animate();
		
	}
	
	
	
	public static synchronized void moveLocation(BoundedShape toBeMoved, Point newLocation) {
		;
			animationStepGenerator.clearListeners();
			locationIncrementer1.init(toBeMoved, newLocation);
			 
			 animationStepGenerator.addListener(locationIncrementer1);
			 animationStepGenerator.animate();
			
			
		}
	
	 public static synchronized void newBoundsModular (BoundedShape aShape, Rectangle newBounds) {
		 animationStepGenerator.clearListeners();
		 animationStepGenerator.addListener(boundsIncrementer1);
		 boundsIncrementer1.init(aShape, newBounds);
		 animationStepGenerator.animate();
	    	
	    }
	 public static synchronized void newBounds (List<? extends BoundedShape> aShapeList, List<Rectangle> aNewBoundsList) {
		 animationStepGenerator.clearListeners();
		 animationStepGenerator.addListener(shapeListBoundsIncrementer);
		 shapeListBoundsIncrementer.init(aShapeList, aNewBoundsList);
		 animationStepGenerator.animate();
	    	
	    }
	 public static synchronized void newSize (List<? extends BoundedShape> aShapeList, List<Dimension> aNewBoundsList) {
		 animationStepGenerator.clearListeners();
		 animationStepGenerator.addListener(shapeListSizeIncrementer);
		 shapeListSizeIncrementer.init(aShapeList, aNewBoundsList);
		 animationStepGenerator.animate();
	    	
	    }
	 public static synchronized void newSizeCartesian (List<? extends BoundedShape> aShapeList, List<Dimension> aNewBoundsList) {
		 animationStepGenerator.clearListeners();
		 animationStepGenerator.addListener(cartesianShapeListSizeIncrementer);
		 cartesianShapeListSizeIncrementer.init(aShapeList, aNewBoundsList);
		 animationStepGenerator.animate();
	    	
	    }
	 public static synchronized void newSizeCartesian (BoundedShape aShape, Dimension aSize) {
		 animationStepGenerator.clearListeners();
//		 ObjectIncrementer<BoundedShape, Dimension> dimensionIncrementer = new ACartesianShapeDimensionIncrementer();
		 RangeBasedObjectIncrementer<BoundedShape, Dimension> cartesianDimensionIncrementer = new ACartesianShapeDimensionIncrementer();

		 animationStepGenerator.addListener(cartesianDimensionIncrementer);
		 cartesianDimensionIncrementer.init(aShape, aSize);
		 animationStepGenerator.animate();
	    	
	    }
	 
	 public static synchronized void newSize (BoundedShape aShape, Dimension aSize) {
		 animationStepGenerator.clearListeners();
//		 ObjectIncrementer<BoundedShape, Dimension> dimensionIncrementer = new ACartesianShapeDimensionIncrementer();
//		 ObjectIncrementer<BoundedShape, Dimension> cartesianDimensionIncrementer = new ACartesianShapeDimensionIncrementer();

		 animationStepGenerator.addListener(dimensionIncrementer);
		 dimensionIncrementer.init(aShape, aSize);
		 animationStepGenerator.animate();
	    	
	    }
	 public static Dimension modifyForGrowing(BoundedShape aShape)  {
		 Dimension retVal = new Dimension (aShape.getWidth(), aShape.getHeight());
		 aShape.setWidth(0);
		 aShape.setHeight(0);
		 return retVal;
	 }
	 public static List<Dimension> modifyForGrowing(List<TreeNodeShape> aShapeList) {
			List<Dimension> oldSizeList = new ArrayList();

			for (int i = 0; i < aShapeList.size(); i++) {			 
				 Dimension oldSize = new Dimension( aShapeList.get(i).getWidth(), aShapeList.get(i).getHeight());
				 aShapeList.get(i).setWidth(0);
				 aShapeList.get(i).setHeight(0);
				
				 oldSizeList.add(oldSize);
				 
			 }
//			visualizer.addTreeNodeShapes(aShapeList);

			return oldSizeList;
		}
	 // no modify really
	 public static synchronized List<Dimension> modifyForShrinking (List<? extends BoundedShape> aShapeList) {
		 List<Dimension> zeroSizeList = new ArrayList();
		 for (int i = 0; i < aShapeList.size(); i++) {
//			 Dimension aSize = new Dimension( aShapeList.get(i).getWidth(), aShapeList.get(i).getHeight());
//			 aSize.width = 0;
//			 aSize.height = 0;
			 Dimension aSize = new Dimension( 0, 0);
//			 aSize.width = 0;
//			 aSize.height = 0;
			 zeroSizeList.add(aSize);
			 
		 }
		 return zeroSizeList;
	    }
	 public static synchronized void makeDisappear (List<? extends BoundedShape> aShapeList) {
//		 List<Dimension> zeroSizeList = new ArrayList();
//		 for (int i = 0; i < aShapeList.size(); i++) {
//			 Dimension aSize = new Dimension( aShapeList.get(i).getWidth(), aShapeList.get(i).getHeight());
//			 aSize.width = 0;
//			 aSize.height = 0;
//			 zeroSizeList.add(aSize);
//			 
//		 }
		 List<Dimension> zeroSizeList =modifyForShrinking(aShapeList);

		 newSize(aShapeList, zeroSizeList);	    	
	    }
	 public static synchronized void makeDisappearCartesian (List<? extends BoundedShape> aShapeList) {
//		 List<Dimension> zeroSizeList = new ArrayList();
//		 for (int i = 0; i < aShapeList.size(); i++) {
//			 Dimension aSize = new Dimension( aShapeList.get(i).getWidth(), aShapeList.get(i).getHeight());
//			 aSize.width = 0;
//			 aSize.height = 0;
//			 zeroSizeList.add(aSize);
//			 
//		 }
		 List<Dimension> zeroSizeList =modifyForShrinking(aShapeList);

		 newSizeCartesian(aShapeList, zeroSizeList);	    	
	    }
	 public static synchronized void makeDisappear (BoundedShape aShape) {
//		 List<Dimension> zeroSizeList = new ArrayList();
//		 for (int i = 0; i < aShapeList.size(); i++) {
//			 Dimension aSize = new Dimension( aShapeList.get(i).getWidth(), aShapeList.get(i).getHeight());
//			 aSize.width = 0;
//			 aSize.height = 0;
//			 zeroSizeList.add(aSize);
//			 
//		 }

		 newSize(aShape, zeroDimension);	    	
	    }
	 public static synchronized void makeAppear (BoundedShape aShape) {
		 Dimension oldDimension = new Dimension (aShape.getWidth(), aShape.getHeight());
		 aShape.setWidth(0);
		 aShape.setHeight(0);
//		 List<Dimension> zeroSizeList = new ArrayList();
//		 for (int i = 0; i < aShapeList.size(); i++) {
//			 Dimension aSize = new Dimension( aShapeList.get(i).getWidth(), aShapeList.get(i).getHeight());
//			 aSize.width = 0;
//			 aSize.height = 0;
//			 zeroSizeList.add(aSize);
//			 
//		 }

		 newSize(aShape, oldDimension);	    	
	    }
	 public static synchronized void makeAppear (List<? extends BoundedShape> aShapeList) {
		 List<Dimension> oldSizeList = new ArrayList();
		 for (int i = 0; i < aShapeList.size(); i++) {			 
			 Dimension oldSize = new Dimension( aShapeList.get(i).getWidth(), aShapeList.get(i).getHeight());
			 aShapeList.get(i).setWidth(0);
			 aShapeList.get(i).setHeight(0);
			
			 oldSizeList.add(oldSize);
			 
		 }
		 newSize(aShapeList, oldSizeList);	    	
	    }
	 
//	 public static synchronized void adjustingNewBoundsModular (AttributedShape aShape, Rectangle newBounds) {
//		 animationStepGenerator.clearListeners();
//		 animationStepGenerator.addListener(adjustingBoundsIncrementer);
//		 adjustingBoundsIncrementer.set(aShape, newBounds);
//		 animationStepGenerator.animate();
//	    	
//	    }
	 public static synchronized void newOEShapeAttributes (BoundedShape aShape, BoundedShape newShape) {
		 animationStepGenerator.clearListeners();
		 animationStepGenerator.addListener(oeShapeAttributesIncrementer);
		 oeShapeAttributesIncrementer.init(aShape, newShape);
		 animationStepGenerator.animate();
	    	
	    }
	    
	private static synchronized void moveHorizontally(BoundedShape model, int x,
			Color highlighting, Color finalColor) {
		boolean test = x > model.getX();
		int move = x - model.getX();
		if (move == 0) return;
		((FlexibleShape) model).setColor(highlighting);
		try {
			int count = 0;

//			while (count != move) {
			while (true) {
//			while (count < move) {
				if (test) {
					double newX = model.getX() + getAnimationStep();
					if (newX > x) {
						model.setX(x);
						break;
					}
//					double y = model.getY();
					model.setX((int) newX);
//					model.setY((int) y);
//					count = count + getAnimationStep();
				} else {
					double newX = model.getX() - getAnimationStep();
					if (newX < x) {
						model.setX(x);
						break;
					}
//					double y = model.getY();
					model.setX((int) newX);
//					model.setY((int) y);
//					count = count - getAnimationStep();
				}
				if (Math.abs(count - move) < 5) {
					((FlexibleShape) model).setColor(finalColor);
				}
				Thread.sleep(getAnimationPauseTime());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
    public static synchronized void grow(BoundedShape aShape) {
		int originalHeight = aShape.getHeight();
		int originalWidth = aShape.getWidth();
		int aHeightIncrement = originalHeight/numSteps;
		int aWidthIncrement = originalWidth/numSteps;	
		if (aWidthIncrement != 0)
		    aShape.setWidth(0);
		if (aHeightIncrement != 0) {
		aShape.setHeight(0);
		}
		for (int i=0; i <numSteps; i++) {
			if (aHeightIncrement > 0) {			
				aShape.setWidth(aShape.getWidth() + aWidthIncrement);
				aShape.setHeight(aShape.getHeight() + aHeightIncrement);
				ThreadSupport.sleep(animationPauseTime);
			}
		}
		aShape.setWidth(originalWidth);
		aShape.setHeight(originalHeight);
		
	}
    
    public static synchronized void newBounds (BoundedShape aShape, Rectangle newBounds, boolean adjustY) {
    	move(aShape, newBounds.x, newBounds.y, true, Color.BLACK, Color.BLACK);
    	grow(aShape, newBounds.width, newBounds.height,  adjustY);
    }
    
    public static synchronized void grow(BoundedShape aShape, int newWidth, int newHeight, boolean adjustY) {
//    	int originalX = aShape.getX();
//    	int originalY = aShape.getY();
		int originalHeight = aShape.getHeight();
		int originalWidth = aShape.getWidth();
		int aHeightIncrement = (newHeight - originalHeight)/numSteps;
		int aWidthIncrement = (newWidth - originalWidth)/numSteps;	
//		if (aWidthIncrement != 0)
//		    aShape.setWidth(0);
//		if (aHeightIncrement != 0) {
//		aShape.setHeight(0);
//		}
		for (int i=0; i <numSteps; i++) {
			if (aHeightIncrement != 0 || aWidthIncrement != 0) {			
				aShape.setWidth(aShape.getWidth() + aWidthIncrement);
//				aShape.setX(originalX);
				aShape.setHeight(aShape.getHeight() + aHeightIncrement);
				if (adjustY)
				aShape.setY(aShape.getY()- aHeightIncrement);
				ThreadSupport.sleep(animationPauseTime);
			}
		}
		
		aShape.setWidth(newWidth);
		aShape.setHeight(newHeight);
		
	}

	private static synchronized void moveVertically(BoundedShape model, int y,
			Color highlighting, Color finalColor) {
		boolean test = y > model.getY();
		int move = y - model.getY();
		if (move == 0) return;
		((FlexibleShape) model).setColor(highlighting);
		try {
			int count = 0;
//			while (count < move) {

//			while (count != move) {
			while (true) {
//				Thread.sleep(getAnimationPauseTime());
				if (test) {
//					double x = model.getX();
					
					double newY = model.getY() + getAnimationStep();
					if (newY > y) {
						model.setY(y);
						break;
					}
//					model.setX((int) x);
					model.setY((int) newY);
					count = count + getAnimationStep();
				} else {
//					double x = model.getX();
					double newY = model.getY() - getAnimationStep();
//					model.setX((int) x);
					if (newY < y) {
						model.setY(y);
						break;
					}
					model.setY((int) newY);
					count = count - getAnimationStep();
				}
				Thread.sleep(getAnimationPauseTime());

			}
			((FlexibleShape) model).setColor(finalColor);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static synchronized void moveAllVertically(List<BoundedShape> shapes,
			int y, Color highlighting, Color finalColor) {
		boolean positive = y > 0;
		try {
			int current_y_position = 0;
			while (current_y_position != Math.abs(y)) {
				for (BoundedShape shape : shapes) {
					if (positive) {
						moveVertically(shape, y, highlighting, finalColor);
					} else {
						moveVertically(shape, -y, highlighting, finalColor);
					}
				}
				current_y_position += getAnimationStep();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static synchronized void moveAllHorizontally(List<BoundedShape> shapes,
			int x, Color highlighting, Color finalColor) {
		boolean positive = x > 0;
		try {
			int current_x_position = 0;
			while (current_x_position != x) {
				for (BoundedShape shape : shapes) {
					if (positive) {
						moveHorizontally(shape, x, highlighting, finalColor);
					} else {
						moveHorizontally(shape, -x, highlighting, finalColor);
					}
				}
				current_x_position = current_x_position + getAnimationStep();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// This method moves an entire list of shapes to
	// (shapes.get(i).getX()+x,shapes.get(i).getY()+y)
	public static synchronized void move(List<BoundedShape> shapes, int x, int y,
			boolean animate, Color highlighting, Color finalColor) {
		if (animate) {
			moveAllHorizontally(shapes, x, highlighting, finalColor);
			moveAllVertically(shapes, y, highlighting, finalColor);
		} else {
			for (BoundedShape s : shapes) {
				s.setX(s.getX() + x);
				s.setY(s.getY() + y);
			}
		}
	}

	// This method moves one shape to the position (x,y)
	public static synchronized void move(BoundedShape shape, int x, int y,
			boolean animate, Color highlighting, Color finalColor) {
		if (animate) {
			moveHorizontally(shape, x, highlighting, finalColor);
			moveVertically(shape, y, highlighting, finalColor);
		} else {
			shape.setX(x);
			shape.setY(y);
		}
	}
	public static boolean getAnimateActions() {
		return AnAnimationController.getDefaultAnimationController().getAnimateActions();
	}
	public void setAnimateActions(boolean newValue) {
		 AnAnimationController.getDefaultAnimationController().setAnimateActions(newValue);

	}
	public static int getAnimationStep() {
		return animationStep;
	}
	public static void setAnimationStep(int animationStep) {
		AnimationUtil.animationStep = animationStep;
	}
	public static int MIN_ANIMATION_PAUSE_TIME = 50;
	public static int getRandomAnimationPauseTime() {
		int retVal = Common.random(MIN_ANIMATION_PAUSE_TIME, AnAnimationController.getDefaultAnimationController().getAnimationPauseTime() - MIN_ANIMATION_PAUSE_TIME);
	
		return retVal;
	}
	public static int getAnimationPauseTime() {
		return AnAnimationController.getDefaultAnimationController().getAnimationPauseTime();
	}
	public static void setAnimationPauseTime(int newVal) {
		 AnAnimationController.getDefaultAnimationController().setAnimationPauseTime(newVal);
	}
	
	public static int getAnimationSteps() {
		return AnAnimationController.getDefaultAnimationController().getAnimationSteps();
	}
	public static void setAnimationSteps(int newVal) {
		 AnAnimationController.getDefaultAnimationController().setAnimationSteps(newVal);
	}
	
	
	
	
}
