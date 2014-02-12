package yaas.common.builders;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;

import yaas.PseudoCode;
import yaas.common.*;



public class PseudoCodeBuilder {

	public static void loadPseudocode() {

		String className = PseudoCode.class.getSimpleName();
		String packageName = PseudoCode.class.getPackage().getName();
		String constantName = PseudoCode.class.getFields()[0].getName();
		String vectorType = VestigalListenableVector.class.getSimpleName();
		String vectorName = AListenableVector.class.getSimpleName();
		String vectorTypeImports = VestigalListenableVector.class.getPackage()
				.getName();
		String vectorNameImports = AListenableVector.class.getPackage()
				.getName();
		String packageDeclaration = "package " + packageName + ";\n";
		String vectorTypeImportDeclaration = "import " + vectorTypeImports
				+ "." + vectorType + ";\n";
		String vectorNameImportDeclaration = "import " + vectorNameImports
				+ "." + vectorName + ";\n";
		String classHeader = "public class " + className + " {\n";

		String constantDeclarationOpen = "	public static " + vectorType
				+ "<String> " + constantName + " = new " + vectorName
				+ "<String>(){{";
		String vectorAdditions = readPseudoCodeFromFile();
		String constantDeclarationClose = "}};\n";
		String classEnd = "}";
		String classSource = packageDeclaration + vectorTypeImportDeclaration
				+ vectorNameImportDeclaration + classHeader
				+ constantDeclarationOpen + vectorAdditions
				+ constantDeclarationClose + classEnd;
		String packageFolderName = packageName.replace('.', '/');
		String fileName = null;
		FileWriter writer = null;
		try {
			fileName = "src/" + packageFolderName + "/" + className + ".java";
			writer = new FileWriter(fileName);
		} catch (Exception e) {
			try {
				fileName = packageFolderName + "/" + className + ".java";
				writer = new FileWriter(fileName);
			} catch (Exception e2) {
				e.printStackTrace();
			}
		}
		try {
			writer.write(classSource);
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private static String readPseudoCodeFromFile() {
		try {
			FileInputStream fstream = new FileInputStream("BubbleSortPseudoCode.txt");

//			FileInputStream fstream = new FileInputStream("nestedPseudoCode.txt");

			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;
			
			String returnString = "";

			while ((strLine = br.readLine()) != null) {

				returnString +="add(\"" +strLine + "\");";
			}
			in.close();
			return returnString;
		} catch (Exception e) {// Catch exception if any
			System.err.println("Error: " + e.getMessage());
			return "";
		}
	}

	public static void main(String args[]) {
		loadPseudocode();

	}

}
