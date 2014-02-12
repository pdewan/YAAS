package yaas.trappers;

import java.util.ArrayList;
import java.util.List;

public class ATrapperChainSupporter<ObservingClass, Observable> implements
		TrapperChainSupporter<ObservingClass, Observable> {

	private List<EventTrapper<ObservingClass, Observable>> trapperChain;
	private EventGenerator<ObservingClass, Observable> eventGenerator;

	public ATrapperChainSupporter(
			EventGenerator<ObservingClass, Observable> eventGenerator) {
		trapperChain = new ArrayList<EventTrapper<ObservingClass, Observable>>();
		this.eventGenerator = eventGenerator;
	}

	public void addTrapper(EventTrapper<ObservingClass, Observable> trapper) throws Exception {

		if (trapperChain.contains(trapper)) {
			return;
		}
		if (trapperChain.size() == 0) {
			eventGenerator.addListener(trapper);
		} else {
			EventTrapper<ObservingClass, Observable> previousTrapper = trapperChain
					.get(trapperChain.size() - 1);

			previousTrapper.addListener(trapper);
		}

		trapperChain.add(trapper);
	}

	public void removeTrapper(EventTrapper<ObservingClass, Observable> trapper) throws Exception {

		if (!trapperChain.contains(trapper)) {
			return;
		}
		int pos = trapperChain.indexOf(trapper);

		// it is the first and last trapper
		if (pos == 0 && pos == trapperChain.size() - 1) {
			eventGenerator.removeListener(trapper);
		}
		// it is the first trapper but NOT the last trapper
		else if (pos == 0) {
			EventTrapper<ObservingClass, Observable> nextTrapper = trapperChain
					.get(trapperChain.size() + 1);
			eventGenerator.removeListener(trapper);
			eventGenerator.addListener(nextTrapper);
		}
		// it is the last trapper but NOT the first trapper
		else if (pos == trapperChain.size() - 1) {
			EventTrapper<ObservingClass, Observable> previousTrapper = trapperChain
					.get(trapperChain.size() - 1);
			previousTrapper.removeListener(trapper);

		}
		// it is not the first OR last trapper
		else {
			EventTrapper<ObservingClass, Observable> previousTrapper = trapperChain
					.get(trapperChain.size() - 1);
			EventTrapper<ObservingClass, Observable> nextTrapper = trapperChain
					.get(trapperChain.size() + 1);

			previousTrapper.removeListener(trapper);
			previousTrapper.addListener(nextTrapper);
		}

		trapperChain.remove(trapper);
	}
}
