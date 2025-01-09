import model.Key;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class ParsedOutput {
    private String TAG_FILE;
    private String PORT_PROTOCOL_FILE;

    public ParsedOutput(String tagOutput, String portProtocolOutput) {
        TAG_FILE = tagOutput;
        PORT_PROTOCOL_FILE = portProtocolOutput;
    }

    public void writeTagOutput(Map<String, Integer> tagCount) {
        // Write the tag count to the TAG_FILE
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(TAG_FILE))) {
            writer.write("Tag,Count");
            writer.newLine();
            for (Map.Entry<String, Integer> entry : tagCount.entrySet()) {
                writer.write(entry.getKey() + "," + entry.getValue());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writePortProtocolOutput(Map<Key, Integer> portProtocolCount) {
        // Write the port protocol count to the PORT_PROTOCOL_FILE
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(PORT_PROTOCOL_FILE))) {
            writer.write("Port,Protocol,Count");
            writer.newLine();
            for (Map.Entry<Key, Integer> entry : portProtocolCount.entrySet()) {
                Key key = entry.getKey();
                writer.write(key.toString() + "," + entry.getValue());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
