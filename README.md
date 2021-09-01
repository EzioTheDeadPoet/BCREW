# BCREW - Batch Copy Replace Ends With

## About

A small programm I needed for a very specific file operation related to texture modding.
Could potentially be upgraded to do more in the future.

## Use

**There are no backups created. So use carefully.**

`Java -jar BCREW.jar "Argument 1" "Argument 2" "Argument 3"`

These are the used arguments:
- Argument 1: Source File (Copy Target)
- Argument 2: Target Directory (Folder containing the files to replace)
- Argument 3: EndsWithSelector (Endstring of files to replace.)

### Example:

**Input:**
```powershell
java -jar .\BCREW.jar "C:\Users\ANONYMOUS\Documents\test0.txt" "C:\Users\ANONYMOUS\Documents\test" ".txt"
```

**Output:**
```powershell
Copying "C:\Users\ANONYMOUS\Documents\test0.txt".
Replacing "C:\Users\ANONYMOUS\Documents\test\test1\test.txt" Successful.
Replacing "C:\Users\ANONYMOUS\Documents\test\test2.txt" Successful.
```
