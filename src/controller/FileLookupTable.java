package controller;

import model.Key;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class FileLookupTable implements LookupTable {
    private final String FILE_NAME;

    public FileLookupTable(String fileName) {
        FILE_NAME = fileName;
    }

    @Override
    public Map<Key, String> getLookupTable() {
        Map<Key, String> map = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            // Skip the header
            boolean skipHeader = true;

            while ((line = br.readLine()) != null) {
                if (skipHeader) {
                    skipHeader = false;
                    continue;
                }
                // skip empty lines
                if (line.isEmpty()) {
                    continue;
                }
                String[] tokens = line.split(",");
                if (tokens.length == 3) {
                    int dstport = Integer.parseInt(tokens[0].trim());
                    String protocol = tokens[1].trim().toLowerCase();
                    String tag = tokens[2].trim();

                    // Create a model.Key object
                    Key key = new Key(dstport, protocol);

                    // Put into the map
                    map.put(key, tag);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }

}
