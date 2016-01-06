
/**
 * Module dependencies.
 */

var express = require('express')
  , routes = require('./routes');

var path = require('path');

var app = module.exports = express.createServer();

// Configuration

app.configure(function(){
  app.set('views', path.join( __dirname + '/views'));
  app.set('/static', express.static(__dirname + '/public'));
  app.set('view engine', 'ejs'); // set up ejs for templating
  app.set("view options", { layout: false });   
  app.use(express.bodyParser());
  app.use(express.methodOverride());
  app.use(app.router);
});

app.configure('development', function(){
  app.use(express.errorHandler({ dumpExceptions: true, showStack: true }));
});

app.configure('production', function(){
  app.use(express.errorHandler());
});

// Routes

app.get('/', routes.index);

app.listen(3000, function(){
  console.log("Express server listening on port %d in %s mode", app.address().port, app.settings.env);
});
