package busservice.parsers;

import busservice.models.Bus;
import busservice.models.BusStop;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JacksonParser {

    private List<BusStop> busStops;

public static void parse(List<BusStop> busStops) throws Exception {
    ObjectMapper objectMapper = new ObjectMapper();
    if(Path.of("src/main/resources/json/shortestRouteFound.json").toFile().exists()){
        objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File("src/main/resources/json/shortestRouteFound1.json"), busStops);

        PrintWriter pw = new PrintWriter("src/main/resources/json/shortestRouteFound2.json");

        BufferedReader br1 = new BufferedReader(new FileReader("src/main/resources/json/shortestRouteFound.json"));
        BufferedReader br2 = new BufferedReader(new FileReader("src/main/resources/json/shortestRouteFound1.json"));

        String line1 = br1.readLine();
        String line2 = br2.readLine();


        while (line1 != null) {
            if (line1 != null) {
                pw.println(line1);
                line1 = br1.readLine();
            }

        }

         while (line2 != null) {
            if(line2 != null)
            {
                pw.println(line2);
                line2 = br2.readLine();
            }
        }

        pw.flush();

        br1.close();
        br2.close();
        pw.close();

        System.out.println("Merged file1.txt and file2.txt alternatively into file3.txt");
    }else {
        File fileName = new File ("src/main/resources/json/shortestRouteFound.json");
        objectMapper.writerWithDefaultPrettyPrinter().writeValue(fileName, busStops);;
    }
/*    File fileName = new File ("src/main/resources/json/shortestRouteFound.json");
    objectMapper.writerWithDefaultPrettyPrinter().writeValue(fileName, busStops);*/



    /*for (BusStop bs : busStops) {
        objectMapper.writeValue(fileName, bs);
    }*/
    for (int i = 0; i < busStops.size(); i++) {
        // busStop.setName(parts[i]);
        // listOfStates.add(parts[i]);
        //String part = parts[i];
        //objectMapper.writeValue(new File("src/main/resources/json/shortestRouteFound.json"), );

        /*BufferedWriter out = new BufferedWriter(
                new FileWriter(fileName, true));*/

        // Writing on output stream
        //out.write(parts[i]);
        // Closing the connection
        //out.close();
    }


    /*List<String> listOfStates = new ArrayList<String>();
    listOfStates.add(text);
    BusStop busStop = new BusStop();
    busStop.setName(text);

    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    objectMapper.writeValue(new File("src/main/resources/json/shortestRouteFound.json"), text);*/
}

}
