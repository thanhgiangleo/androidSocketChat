var express = require('express');
var app = express();
var server = require('http').createServer(app);
var io = require('socket.io').listen(server);
var fs = require('fs');

server.listen(process.env.PORT || 3000);

var listUser = [];
io.socket.on('connection', function(socket) {
	console.log("Client connected.");

	// check username
	socket.on('user_login', function(username) {
		if (listUser.indexOf(username) > -1)
			return;

		listUser.push(username);
		socket.user = username;
	});

	socket.on('send_msg', function(message) {
		io.sockets.emit('receive_msg', {
			data: socket.user + ": " + message
		});
	});
});