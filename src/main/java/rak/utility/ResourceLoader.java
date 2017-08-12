package rak.utility;

import java.io.IOException;
import java.io.InputStream;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

/**
 * Can we eventually use this and pull code out of the application / main classes?
 *
 */
public class ResourceLoader {
	private static Class<?> rootClass;
	
	/**
	 * Must be called before other methods are used
	 * @param rootClass
	 */
	public static void setRootClass(Class<?> rootClass){
		ResourceLoader.rootClass = rootClass;
	}
	
	public static Scene loadFXML(String fileName) throws IOException {
		checkRootClass();
		FXMLLoader fxmlLoader = new FXMLLoader();
		fxmlLoader.setLocation(rootClass.getResource("view/" + fileName + ".fxml"));
		Parent root = fxmlLoader.load();
		
		Scene scene = new Scene(root);
		return scene;
	}
	
	public static InputStream getResourceAsStream(String pathToResource) {
		checkRootClass();
		return rootClass.getResourceAsStream(pathToResource);
	}
	
	private static void checkRootClass(){
		if (rootClass == null){
			throw new IllegalStateException("Resource Loader must be given a root class before it can load resources");
		}
	}
	
}
