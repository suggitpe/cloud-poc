package org.suggs.cloudpoc.producer.trade;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@Api("Trade Event Controller")
public class TradeController {

    private TradeRepository tradeRepository = TradeRepository.init();

    @ApiOperation(value = "Gets TradeEvents by ID, domain, version")
    @RequestMapping(value = "tradeEvent", method = GET)
    public TradeEvent getTradeEventFor(@RequestParam(value = "id") String id,
                                       @RequestParam(value = "domain") String domain,
                                       @RequestParam(value = "version") long version) {
        return tradeRepository.getTradeBy(new TradeIdentifier(id, domain, version));
    }
}
