import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class PortLog {
    public static void main(String[] args){
        try (Scanner scan = new Scanner(new File("connections.txt"))){
            Map<String, Integer> hosts = new HashMap<>();
            Map<Integer, Integer> ports = new HashMap<>();
            int result = counter(hosts, ports, scan);
            filePrint(result,ports,hosts);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }
    public static int counter(Map<String, Integer> hosts,Map<Integer, Integer> ports, Scanner scan){
        int total=0;
        while (scan.hasNext()){
            String host = scan.next();
            int port = scan.nextInt();
            hosts.put(host, hosts.getOrDefault(host,0)+1);
            ports.put(port, ports.getOrDefault(port,0)+1);
            total++;
        }
        return total;
    }
    public static void filePrint(int result, Map<Integer, Integer> ports, Map<String, Integer> hosts){
        try (FileWriter fileWriter = new FileWriter(new File("report.txt"))){
            fileWriter.write("total "+result+"\nports\n");
            for (Map.Entry<Integer, Integer> entry : ports.entrySet()){
                fileWriter.write(entry.getKey()+" "+entry.getValue()+"\n");
            }
            fileWriter.write("hosts\n");
            for (Map.Entry<String, Integer> entry : hosts.entrySet()){
                fileWriter.write(entry.getKey()+" "+entry.getValue()+"\n");
            }
        } catch (IOException e){
            System.out.println("File not found");
        }
    }
}
