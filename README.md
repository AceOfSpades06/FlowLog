# FLow Log Parser

## Problem

Write a program that can parse [flow log data](./flowLogTest.txt)
and maps each row to a Port/Protocal and tag based on a [lookup table](./lookupTable.csv).

## Assumptions

1. The program only supports default log format (v2) (i.e, flow log file will
   have at least 14 fields in each line).
2. The protocols are populated from the [protocols file](/protocols.txt). It has
   the popular protocols listed in the format of
   `protocol_number protocol_name`. If a protocol is not found in this CSV file,
   it will be counted as `unknown`.

## Usage

```bash
cd /path/to/Illumio
# you can skip the next line if you have already created the out directory
javac -d out/production/Illumio src/*.java src/controller/*.java src/model/*.java
# run this command to execute the program
java -cp out/production/Illumio Main
```

The program will read the flow logs from `flowLogTest.txt` and the lookup table
from `lookupTable.csv`. It will write the output to `tagOutput.csv` and
`portProtocolOutput.txt`. Alternatively, you can specify the filenames in the
[configuration file `config.properties`](./config.properties).

## Code Features

- The code is highly extensible and can be easily modified to support additional features.
- We have interfaces that can be implemented so that we can change the way we get our input data.
- The FileFlowLogEmitter.java class pushes one line at a time from the file. This is to mimic pub/sub pattern. This is so that we can handle large files without any issue in production settings.
- We are storing all the loopUp data in memory so that we can get the data in O(1) time and there are only 10000 of them so it is lightweight.
- We also store the protocols in memory so that we can get the protocol in O(1) time.
- The input files can have empty lines and the program will ignore them.

## Requirements

The program should generate an output file containing the following:

1. Tag Counts: Count of matches for each tag

   ```
    Tag,Count
    sv_P2,1
    sv_P1,2
    email,3
    Untagged,8

   ```

2. Port/Protocol Combination Counts: Count of matches for each port/protocol
   combination

   ```
    Port,Protocol,Count
    1024,tcp,1
    993,tcp,1
    49157,tcp,1
    49158,tcp,1
    49155,tcp,1
    443,tcp,1
    49156,tcp,1
    25,tcp,1
    23,tcp,1
    143,tcp,1
    80,tcp,1
    110,tcp,1
    49153,tcp,1
    49154,tcp,1
   ```

## Specifications

- Input file as well as the file containing tag mappings are plain text (ascii)
  files.
- The flow log file size can be up to 10 MB.
- The lookup file can have up to 10000 mappings.
- The tags can map to more than one port, protocol combinations. For e.g. sv_P1
  and sv_P2 in the sample above.
- The matches should be case insensitive.