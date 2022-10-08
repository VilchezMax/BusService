package busservice.dao;

import busservice.models.Bus;
import busservice.models.BusStop;

import java.util.HashMap;
import java.util.LinkedList;

public interface IBusDAO extends ICrudDAO<Bus> {

    HashMap<Bus, LinkedList<BusStop>> getAllRoutes();

}

