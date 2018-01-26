console.log("Loading <ChatsController.ng.js>...");

angular.module("avelon").controller("ChatCtrl", function($scope, $meteor) {
 
	console.log("Bind to collection chats");
	$scope.chats = $meteor.collection(Chats).subscribe("chats");
	//$scope.c = $meteor.collection(Chats);

	$scope.add = function(event, user) {
		if(event.keyCode != 13) return;

		var iso = new Date().toISOString();
		var now = Math.floor(new Date()/1000);
		$scope.chats.save({epoch: now, time: iso, from: user, body: $scope.newChat});
		$scope.newChat = '';
	}
});
