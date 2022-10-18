package busservice.parsers;

import busservice.models.BusStop;
import busservice.models.BusStops;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class Parser {
    private static final Logger logger = LogManager.getLogger(Parser.class);

    private static final ObjectMapper MAPPER = new ObjectMapper();

    public static void writeXml(List<BusStop> busStops, File file) {
        try {
            BusStops busStops1 = new BusStops();
            busStops1.setBusStopList(busStops);
            JAXBContext jaxbContext = JAXBContext.newInstance(BusStops.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(busStops1, file);
        } catch (JAXBException e) {
            logger.error("Unable to write xml " + e.getMessage(), e);
        }
    }

    public static void writeJson(List<BusStop> busStops, File file) {
        try {
            BusStops busStops1 = new BusStops();
            busStops1.setBusStopList(busStops);
            ObjectWriter writer = MAPPER.writer(new DefaultPrettyPrinter());
            writer.writeValue(file, busStops1);
        } catch (IOException e) {
            logger.error("Unable to write json " + e.getMessage(), e);
        }
    }

}
