# BCREW - Batch Copy Replace Ends With

## About

A small programm I needed for a very specific file operation related to texture modding.
Could potentially be upgraded to do more in the future.

## Use

**There are no backups created. So use carefully.**
*Use Argument 4 to clone the file stucture contained in the directory  of A3 with the file from A2.As an less destructive alternative.*

```powershell
Java -jar BCREW.jar "Argument 1" "Argument 2" "Argument 3" "Argument 4(Optional)"
```

Run the program with the following 3 arguments:
- Argument 1: EndsWithSelector (Endstring of Filesnames to replace.)
- Argument 2: Source File (Copy Target)
- Argument 3: Argument 3: Target Directory (Files to replace/Filestructure to mirror with Argument 4)
- Argument 4(Optional): Output Directory

### Example

**Input:**
```powershell
java -jar .\BCREW.jar ".txt" "C:\Users\ANONYMOUS\Documents\test0.txt" "C:\Users\ANONYMOUS\Documents\test" "C:\Users\ANONYMOUS\Documents\testOUTPUT"
```

**Output:**

```powershell
Selected Origin File: "C:\Users\ANONYMOUS\Documents\test0.txt".
Created "C:\Users\ANONYMOUS\Documents\testOUTPUT\test1" Direcory.
Creating Copy at "C:\Users\ANONYMOUS\Documents\testOUTPUT\test1\test.txt" Successful.
Creating Copy at "C:\Users\ANONYMOUS\Documents\testOUTPUT\test2.txt" Successful.
```
