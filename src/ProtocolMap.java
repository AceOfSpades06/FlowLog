import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ProtocolMap {
    private String FILE;
    Map<Integer, String> protocolMap = new HashMap<>();

    public ProtocolMap(String fileName) {
        FILE = fileName;
        loadProtocols();
    }

    private void loadProtocols() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\s+");
                if (parts.length == 2) {
                    protocolMap.put(Integer.valueOf(parts[0]), parts[1].toLowerCase());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getProtocolName(int protocolNumber) {
        return protocolMap.getOrDefault(protocolNumber, "Unknown");
    }
}