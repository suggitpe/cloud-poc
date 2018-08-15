package org.suggs.cloudpoc.consumer.trade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
public class TradeEventController {

    @Autowired
    private RestTemplate restTemplate;

    private static final String DATA_PRODUCER = "java-producer";

    @RequestMapping(value = "/tradeEvent/{domain}/{id}/{version}", method = GET, produces = "application/json")
    @ResponseBody
    public TradeEvent getTradeDataAsJson(@PathVariable String domain, @PathVariable String id, @PathVariable Long version) {
        return executeTradeEventRestCallFor(domain, id, version);
    }

    @RequestMapping(value = "/tradeEvent/{domain}/{id}/{version}", method = GET)
    public String getTradeEventAsWebPage(@PathVariable String domain, @PathVariable String id, @PathVariable Long version, Model model){
        model.addAttribute("domain", domain);
        model.addAttribute("id", id);
        model.addAttribute("version", version);
        model.addAttribute("data", executeTradeEventRestCallFor(domain, id, version));
        return "tradeEventDataView";
    }

    private TradeEvent executeTradeEventRestCallFor(String tradeDomain, String tradeId, Long tradeVersion) {
        return restTemplate.getForObject(buildRestUrl(tradeDomain, tradeId, tradeVersion), TradeEvent.class);
    }

    private String buildRestUrl(String tradeDomain, String tradeId, Long tradeVersion) {
        return "http://" + DATA_PRODUCER + "/tradeEvent?domain=" + tradeDomain + "&id=" + tradeId + "&version=" + tradeVersion;
    }

}
