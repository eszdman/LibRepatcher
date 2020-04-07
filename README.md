# LibRepatcher
Automatic patcher that finds similar patterns of data
# Usage

1.Create a file with the name of the file being processed with the addition .patch0 for the first patch

Example: file - example.bin patch - example.patch0

2.The patch is a text document, there is a sample in the examples folder

3.Use console commands to run patcher

# Console commands

LibRepatcher.jar (example file) (patch count) (patchname)
Example: LibRepatcher.jar Example.bin 2 examplefix

# All parameters for patch

FindPattern - is needed to find a similar pattern in the data structure

ReplaceArray - is needed to replace a similar pattern in the data structure

ShiftIndex - offset relative to the desired pattern for ReplaceArray

StartIndex - Starting index for searching for similar data

EveryPattern - Replace specific pattern by ReplaceArray
