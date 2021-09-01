import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

public class BatchCopyReplaceEndsWith {

	public static void main(String[] args) {
		if (args.length < 3 || args.length > 3) {
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
		String sourceFilePath = args[0];
		String workingPath = args[1];
		String fileEnding = args[2];

		Path sourceFile = new File(sourceFilePath).toPath();
				
		List<File> fileList = searchFiles(workingPath,fileEnding);
		if (fileList == null) {
			System.out.println("No File Found!");
			return;
		}
		System.out.println("Copying \"" + sourceFile + "\".");
		
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
	
	public static List<File> searchFiles(String path, String ending){
		File workingDir = new File(path);
		File[] workingFileList = workingDir.listFiles();
		List<File> fileList = new ArrayList<File>();
		if (workingFileList != null) {  // In case of access error, list is null
            for (File f : workingFileList) {
                if (f.isDirectory()) {
                    fileList.addAll(searchFiles(f.getAbsolutePath(),ending));
                } else {
                    fileList.add(f.getAbsoluteFile());
                }
            }
        }
		return fileList;
	}
	
	public static void helpText() {
		System.out.println("How to Use:");
		System.out.println("Argument 1: Source File (Copy Target)");
		System.out.println("Argument 2: Target Directory (Files to replace)");
		System.out.println("Argument 3: EndsWithSelector (Endstring of Files to replace.)");
		System.out.println("This creates NO backup of replaced files.");
		return;
		
	}

}
