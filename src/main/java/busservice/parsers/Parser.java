package busservice.parsers;

import busservice.models.Bus;
import busservice.models.BusStop;
import busservice.models.BusStops;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Parser {
    private static final Logger logger = LogManager.getLogger(Parser.class);

    private static final ObjectMapper MAPPER = new ObjectMapper();

    public static void writeXml(ArrayList<BusStop> busStops, File file) {
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

    public static void writeJson(ArrayList<BusStop> busStops, File file) {
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
