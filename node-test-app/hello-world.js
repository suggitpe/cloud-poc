var http = require('http');
var cfenv = require('cfenv')

var server = http.createServer(function (request, response) {
    response.writeHead(200, {"Content-Type": "text/plain"});

    var appEnv = cfenv.getAppEnv();
    console.log(appEnv);

    var options = {
        host: 'hello-world-service.cfapps.io',
        path: '/greeting?name=foo'
    };



    http.request(options, function(thing){

        var str = '';

        thing.on('data', function(chunk){
            str += chunk;
        });

        thing.on('end', function(){
            console.log("from the other chap we got: " + str);
            response.end(JSON.stringify(appEnv) + "\n\n" + JSON.stringify(process.env.VCAP_SERVICES)  + "\n\n"+"the other app said: " + JSON.parse(str).content + "\n");
        });

    }).end();


});


console.log("Serverunning at http://127.0.0.1:7979/ or somewhere in the cloud");

server.listen(process.env.PORT || 7979);