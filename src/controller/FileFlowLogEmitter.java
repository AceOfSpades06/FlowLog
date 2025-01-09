package controller;

import model.FlowLog;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileFlowLogEmitter implements FlowLogEmitter {
    private final String readPath;
    private int offset;
    public FileFlowLogEmitter(String path) {
        this.readPath = path;
        this.offset = 0;
    }

    public FileFlowLogEmitter(String path, int offset) {
        this.readPath = path;
        this.offset = offset;
    }

    @Override
    public FlowLog emit() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(readPath));
            for (int i = 0; i < offset; i++) {
                reader.readLine(); // Skip lines up to the offset
            }
            String line = reader.readLine();
            // skip empty lines
            while (line != null && line.isEmpty()) {
                line = reader.readLine();
            }
            if (line != null) {
                offset++;
                String[] parts = line.split(" ");
                return FlowLog.fromStringArray(parts);
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to read flow log", e);
        }
        return null; // No more flow logs
    }
}
