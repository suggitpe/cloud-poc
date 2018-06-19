var express = require('express');
var cfenv = require('cfenv');

var app = express();
var appEnv = cfenv.getAppEnv();

var counter = 0;
app.get('/', function(request, response){
    response.setHeader('Content-Type', 'application/json');
    response.send(JSON.stringify(getDiscoveryServiceUri(cfenv)));
    //response.send(JSON.stringify({'id': ++counter, 'name': 'Hello ' + (request.query.name || "world") + "!" }));
});

var getDiscoveryServiceUri = function(cfenv){
    return appEnv.getServiceURL("discovery-service") || "localhost:8761";
}

app.listen(realPort = process.env.PORT || 8903);
console.log("server running on port " + realPort);