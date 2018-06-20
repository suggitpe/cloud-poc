package org.suggs.cloudfoundry.greeting.clientdata;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.web.bind.annotation.RequestMethod.GET;


@RestController
public class ClientDataController {


    private Map<Long, ClientData> mapOfClientData = new HashMap<>();

    {
        mapOfClientData.put(1L, new ClientData(1L, "Foo", "shhhh", "London"));
        mapOfClientData.put(2L, new ClientData(2L, "Bar", "shhhh", "Paris"));
        mapOfClientData.put(3L, new ClientData(3L, "Baz", "shhhh", "New York"));
    }

    @ApiOperation(value = "Gets data about clients by ID")
    @RequestMapping(value = "/clientData", method = GET)
    public ClientData getClientDataFor(@RequestParam(value = "id") long id) {
        return mapOfClientData.get(id);
    }

}
