package busservice.service;

import busservice.models.Bus;
import busservice.models.BusStop;

import java.util.HashMap;
import java.util.LinkedList;

public interface IBusService extends ICrudService<Bus> {

    HashMap<Bus, LinkedList<BusStop>> getAllRoutes(); // Each key (bus) has a list of values that are bus stops that it passes through(the route).


}
