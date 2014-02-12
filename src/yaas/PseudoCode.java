package yaas;

import util.models.AListenableVector;
import util.models.ListenableVector;

public class PseudoCode {
	public static ListenableVector<String> pseudoCode = new AListenableVector<String>(){{add("BubbleSort(int array[])");add("	while (swapOcurred)");add("		swapOcurred = false");add("		for j = 0 to array.size - 1");add("			if array[j] > array[j + 1]	");add("				swap(a, j, j + 1)");add("				swapOcurred = true");}};
}