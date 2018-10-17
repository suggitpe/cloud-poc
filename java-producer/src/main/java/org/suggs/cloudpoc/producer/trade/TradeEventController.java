package org.suggs.cloudpoc.producer.trade;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.suggs.cloudpoc.producer.trade.domain.TradeEvent;
import org.suggs.cloudpoc.producer.trade.domain.TradeEventIdentifier;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@Api("Trade Event Controller")
public class TradeEventController {

    private TradeEventRepository tradeRepository = TradeEventRepository.init();

    @ApiOperation(value = "Gets TradeEvents by ID, domain, version")
    @RequestMapping(value = "tradeEvent", method = GET, produces = "application/json")
    public TradeEvent getTradeEventFor(@RequestParam(value = "id") long id,
                                       @RequestParam(value = "domain") String domain,
                                       @RequestParam(value = "version") long version) {
        return tradeRepository.getTradeBy(new TradeEventIdentifier(id, domain, version));
    }
}
