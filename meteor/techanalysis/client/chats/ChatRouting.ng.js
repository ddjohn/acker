console.log("Loading <ChatRouting.ng.js>...");

angular.module("avelon").config(function($urlRouterProvider, $stateProvider, $locationProvider) {

	console.log("Create state <chats>");
	$stateProvider.state("chats", {
		url: "/chats",
		templateUrl: "client/chats/ChatsView.ng.html",
		controller: "ChatCtrl"
	});
});
