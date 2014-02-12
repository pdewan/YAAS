package yaas.visualizers.yaas;

import java.util.Observable;
import java.util.Observer;

import yaas.VisualizationBasedVisualizer;
import yaas.Visualizer;
import yaas.controller.Control;
import yaas.visualizers.observer.AnObserverBuffer;
import yaas.visualizers.observer.ObservableEventGenerator;
import bus.uigen.introspect.IntrospectUtility;
import bus.uigen.reflect.ClassProxy;
import bus.uigen.reflect.MethodProxy;
import bus.uigen.reflect.RemoteSelector;
import bus.uigen.undo.Command;
import bus.uigen.undo.CommandCreator;
import bus.uigen.undo.Inverses;


public class ARecursiveVisualizationBuffer extends AnObserverBuffer {

	public ARecursiveVisualizationBuffer(
			VisualizationBasedVisualizer<Observer, ObservableEventGenerator> visualizer) {
		super(visualizer);
	}

	@Override
	public void setObservable(ObservableEventGenerator observable) {
		super.setObservable(observable);

		((Control) copy).init(this.getVisualizer());

	}

	@Override
	public void update(Observable observable, Object arg1) {
		
		ClassProxy objClass = RemoteSelector.getClass(copy);
		MethodProxy method = IntrospectUtility.getMethod(objClass, arg1.toString(),
				objClass.voidType(), new ClassProxy[] {});
		
		Inverses.add("next", "previous");
		Command inverse = CommandCreator.createCommandBasic(null, method, copy,
				new Object[] {});

		this.getCommandHistory().addCommand(inverse);
	}

}
