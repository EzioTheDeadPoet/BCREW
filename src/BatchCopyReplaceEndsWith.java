import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;


public class BatchCopyReplaceEndsWith {

	public static void main(String[] args) {
		if (args.length < 3 || args.length > 4) {
			helpText();
			return;
		}
		if (args[0].equals("-h")
		||args[0].equals("-H")
		||args[0].equals("-help")
		||args[0].equals("-Help")) {
			helpText();
			return;
		}
		if (args.length == 3) {
			String fileEnding = args[0];
			String sourceFilePath = args[1];
			String workingPath = args[2];
				
			Path sourceFile = new File(sourceFilePath).toPath();
					
			List<File> fileList = searchFiles(workingPath,fileEnding);
			if (fileList == null) {
				System.out.println("No File Found!");
				helpText();
				return;
			}
			System.out.println("Selected Origin File: \"" + sourceFile + "\"");
			
			for (File f : fileList) {
				try {
					Files.copy(sourceFile, f.toPath(), StandardCopyOption.REPLACE_EXISTING);
					System.out.println("Replacing \"" + f + "\" Successful.");
				} catch (IOException e) {
					System.out.println("Replacing \"" + f + "\" Failed.");
				}
			}
	
			return;
		}
		if (args.length == 4) {
			
			String fileEnding = args[0];
			String sourceFilePath = args[1];
			String workingPath = args[2];
			String outputPath = args[3];
				
			Path sourceFile = new File(sourceFilePath).toPath();
					
			List<File> fileList = searchFiles(workingPath,fileEnding);
			if (fileList == null) {
				System.out.println("No File Found!");
				helpText();
				return;
			}
			System.out.println("Selected Origin File: \"" + sourceFile + "\".");
			
			for (File f : fileList) {
				File targetFile = new File(f.getAbsolutePath().replace(workingPath, outputPath));
				if(!targetFile.getParentFile().exists()) {
					if(targetFile.getParentFile().mkdirs()) {
						System.out.println("Created \"" + targetFile.getParentFile() + "\" Direcory.");
					} else {
						System.out.println("Failed to create \"" + targetFile.getParentFile() + "\" Direcory.");
					}
				}	
				try {
					Files.copy(sourceFile, targetFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
					System.out.println("Creating Copy at \"" + targetFile + "\" Successful.");
				} catch (IOException e) {
					System.out.println("Creating Copy at \"" + targetFile + "\" Failed.");
				}
			}
		
			return;
		}
	}
	
	public static List<File> searchFiles(String path, String ending){
		File workingDir = new File(path);
		File[] workingFileList = workingDir.listFiles();
		List<File> fileList = new ArrayList<File>();
		if (workingFileList != null) {  // In case of access error, list is null
            for (File f : workingFileList) {
                if (f.isDirectory()) {
                	fileList.addAll(searchFiles(f.getAbsolutePath(),ending));
                } else {
                	if (f.getAbsolutePath().endsWith(ending)) {
                		fileList.add(f.getAbsoluteFile());
                	}
                }
            }
        }
		return fileList;
	}
	
	
	public static void helpText() {
		System.out.println("How to Use:");
		System.out.println("Argument 1: EndsWithSelector (Endstring of Filesnames to replace.)");
		System.out.println("Argument 2: Source File (Copy Target)");
		System.out.println("Argument 3: Target Directory (Files to replace/Filestructure to mirror with Argument 4)");
		System.out.println("Argument 4: Output Directory");
		System.out.println("This creates NO backup of replaced files. Use Argument 4 to clone the file stucture contained in the directory of A3 with the file from A2.");
		return;
		
	}

}
