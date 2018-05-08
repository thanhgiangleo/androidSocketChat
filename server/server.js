var express = require('express');
var app = express();
var server = require('http').createServer(app);
var io = require('socket.io').listen(server);
var fs = require('fs');

server.listen(process.env.PORT || 3000, function() {
	console.log("Server running on port 3000");
});

var listUser = [];

io.sockets.on('connection', function(socket) {
	console.log("Client connected.");

	// check username
	socket.on('register', function(data) {
		console.log("User : " + data.username + " " + data.password);

	});
});