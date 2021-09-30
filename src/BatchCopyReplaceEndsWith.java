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

		if (helpCheck(args)) {
			return;
		}
		String fileEnding = args[0];
		String sourceFilePath = args[1];
		String workingPath = args[2];
		String outputPath = workingPath;
		if (args.length == 4) {outputPath = args[3];}
		Path sourceFile = new File("").toPath();
		if (args[1].equals("--JustCopy")||args[1].equals("-jc")) {}
		else{
			sourceFile = new File(sourceFilePath).toPath();
			if (!sourceFile.toFile().exists()) {
				System.out.println("No File Found!");
				helpText();
				return;

			}
			System.out.println("Selected Origin File: \"" + sourceFile + "\".");
		}
		List<File> fileList = searchFiles(workingPath,fileEnding);




		for (File f : fileList) {
			File targetFile = new File(f.getAbsolutePath().replace(workingPath, outputPath));
			if(!targetFile.getParentFile().exists()) {
				if(targetFile.getParentFile().mkdirs()) {
					System.out.println("Created \"" + targetFile.getParentFile() + "\" Directory.");
				} else {
					System.out.println("Failed to create \"" + targetFile.getParentFile() + "\" Directory.");
				}
			}
			try {
				if (args[1].equals("--JustCopy")||args[1].equals("-jc")){
					System.out.println("JUST COPY");
					Files.copy(f.toPath(), targetFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
				} else {
					Files.copy(sourceFile, targetFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
				}
				System.out.println("Creating Copy at \"" + targetFile + "\" Successful.");
			} catch (IOException e) {
				System.out.println("Creating Copy at \"" + targetFile + "\" Failed.");
			}
		}
	}
	
	public static List<File> searchFiles(String path, String ending){
		File workingDir = new File(path);
		File[] workingFileList = workingDir.listFiles();
		List<File> fileList = new ArrayList<>();
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
	
	public static Boolean helpCheck(String[] args) {

		for (String arg : args) {
			if (arg.equals("-h")
					|| arg.equals("-H")
					|| arg.equals("-help")
					|| arg.equals("-Help")) {
				helpText();
				return true;
			}
		}
		return false;
	}
	
	public static void helpText() {
		System.out.println("How to Use:\n");
		System.out.println("Argument 1: EndsWithSelector (EndString of Filenames to replace.)");
		System.out.println("Argument 2: Source File (Copy Target) Or --JustCopy/-jc to just copy the files and don't replace them.");
		System.out.println("Argument 3: Target Directory (Files to replace/FileStructure to mirror with Argument 4)");
		System.out.println("Argument 4: Output Directory\n");
		System.out.println("This creates NO backup of replaced files. Use Argument 4 to clone the file Structure contained in the directory of A3 with the file from A2.");
	}
}
