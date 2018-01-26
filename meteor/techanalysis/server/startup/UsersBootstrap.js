console.log("Loading <UsersBootstrap.js>...");

/* 
Meteor.startup(function () {
	if(Users.find().count() === 0) {
		console.log("Bootstraping users...");

		Users.insert(
		{
			"createdAt" : new Date(), 
			"services" : 
			{ 
				"password" : 
				{ 
					"bcrypt" : "$2a$10$hUyt1zoIuN.Z9QUaYapnjurRPRoFLa7J7eIzYu9qTNwwqcsdlD0uS" 
				}, 
				"resume" : 
				{ 
					"loginTokens" : 
					[ 
						{ 
							"when" : new Date(), 
							"hashedToken" : "cBUDiSjtFrjrl7Qb6iDCfdyUnVGMhrDddPFjDdQdS7Y=" 
						} 
					] 
				} 
			}, 
			"emails" : 
			[ 
				{ 
					"address" : "ddjohn@gmail.com", 
					"verified" : false 
				} 
			] 
		});
 	}
});
*/
