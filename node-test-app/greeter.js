var express = require('express');
var app = express();

var counter = 0;
app.get('/', function(request, response){
    response.setHeader('Content-Type', 'application/json');
    response.send(JSON.stringify({'id': ++counter, 'name': 'Hello ' + (request.query.name || "world") + "!" }));
});

app.listen(realPort = process.env.PORT || 3000);
console.log("server running on port " + realPort);