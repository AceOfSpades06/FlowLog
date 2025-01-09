package model;

public record FlowLog(int version,
                      String account_id,
                      String interface_id,
                      String srcaddr,
                      String dstaddr,
                      int srcport,
                      int dstport,
                      int protocol,
                      long packets,
                      long bytes,
                      long start,
                      long end,
                      String action,
                      String log_status) {
    // method to process an array of Strings and return a model.FlowLog object
    public static FlowLog fromStringArray(String[] parts) {
        return new FlowLog(
                Integer.parseInt(parts[0]),
                parts[1],
                parts[2],
                parts[3],
                parts[4],
                Integer.parseInt(parts[5]),
                Integer.parseInt(parts[6]),
                Integer.parseInt(parts[7]),
                Long.parseLong(parts[8]),
                Long.parseLong(parts[9]),
                Long.parseLong(parts[10]),
                Long.parseLong(parts[11]),
                parts[12],
                parts[13]
        );
    }

}
