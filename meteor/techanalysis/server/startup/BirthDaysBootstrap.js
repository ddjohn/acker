console.log("Loading <BirthdaysBootstrap.js>...");

Meteor.startup(function () {
	if(Birthdays.find().count() === 0) {
		console.log("Bootstraping birthdays...");

		Birthdays.insert({ "name" : "David Johansson", "date" : "1969-03-30", "_id" : "PXpiJAdPspZbTyvps" });
		Birthdays.insert({ "name" : "Patrik Johansson", "date" : "1974-05-13", "_id" : "N5yuCWEZDYNpzQJoJ" });
		Birthdays.insert({ "name" : "Pia Pettersson", "date" : "1966-07-18", "_id" : "mmRxnhGZGPhSuwNsh" });
		Birthdays.insert({ "name" : "Ãke Johansson", "date" : "1930-10-24", "_id" : "isrhSTfyve8G3uQZi" });
		Birthdays.insert({ "name" : "Bengt Haglund", "date" : "1968-02-14", "_id" : "i24fxQMux2MQqqkmb" });
		Birthdays.insert({ "name" : "Anna Cindric", "date" : "1973-02-10", "_id" : "Yivq87YwP7Xr6RTf3" });
		Birthdays.insert({ "name" : "Ãse Pettersson", "date" : "1936-02-16", "_id" : "kLZKmeTZergkzKj5B" });
		Birthdays.insert({ "name" : "Ann Pettersson", "date" : "1966-07-18", "_id" : "CuHMt46r8gP5DJ3Qd" });
		Birthdays.insert({ "name" : "Tomas Kollryd", "date" : "1965-07-11", "_id" : "JtJwFbnumtwG6CAEg" });
		Birthdays.insert({ "name" : "Silvia Johansson", "date" : "1938-06-18", "_id" : "hQ5wLYpxLQh3SjPgh" });
		Birthdays.insert({ "name" : "Mikael Nilsson", "date" : "1975-07-16", "_id" : "HykTRxbWz3kycGyty" });
		Birthdays.insert({ "name" : "Jan-Olof Johansson", "date" : "1967-07-18", "_id" : "siqwusZtdsp4RX92i" });
		Birthdays.insert({ "name" : "Gabriela Karlsson", "date" : "1972-03-03", "_id" : "bvzGZBZACmWgPZQQZ" });
		Birthdays.insert({ "name" : "Cecilia Ottosson", "date" : "1971-01-22", "_id" : "rYSB3ZzraufkWcYyY" });
		Birthdays.insert({ "name" : "Johan Bergren", "date" : "1971-09-22", "_id" : "2ecWwLwexQXbrMCyb" });
		Birthdays.insert({ "date" : "1975-02-17", "name" : "Catarina Olsson", "_id" : "jeh34rsdbSwnrBrrH" });
		Birthdays.insert({ "date" : "1975-04-09", "name" : "Linda Jansson", "_id" : "QLBEqLsMYLZPQA8fH" });
		Birthdays.insert({ "date" : "1971-03-01", "name" : "MÃ¥rten Hildell", "_id" : "jPcztBYMBgTWeawAB" });
		Birthdays.insert({ "date" : "1974-04-09", "name" : "Mats Egemalm", "_id" : "CZcSGEyadhDn8xNkb" });
		Birthdays.insert({ "date" : "1967-12-03", "name" : "Stefan Fredin", "_id" : "nYCzd98Bjehptd226" });
		Birthdays.insert({ "name" : "Rune Johansson", "date" : "1936-07-30", "_id" : "iTZTr6wPLgxSvafKX" });
		Birthdays.insert({ "name" : "Emma Johansson", "date" : "1933-06-14", "_id" : "2yowpCtPAFYXhtfCq" });
		Birthdays.insert({ "name" : "Stina Johansson", "date" : "1933-10-27", "_id" : "6uT9GfCfpMv68ZH7W" });
		Birthdays.insert({ "name" : "Britt Johansson", "date" : "1949-02-15", "_id" : "2KTQJQBSvnMAmjvx5" });
		Birthdays.insert({ "name" : "Sven Johansson", "date" : "1961-08-02", "_id" : "34iJuf4a2mpWj29ZE" });
		Birthdays.insert({ "name" : "Gunnel Andersson", "date" : "1963-04-18", "_id" : "Euq2qxW3xFxLpN4f4" });
		Birthdays.insert({ "name" : "Susanne Johansson", "date" : "1964-03-28", "_id" : "p4kWM8nBZuLGrj8Sb" });
		Birthdays.insert({ "date" : "1964-11-02", "name" : "Jan Andersson", "_id" : "Gr7xWzgpBTbpLMGi6" });
		Birthdays.insert({ "name" : "Mattias Andersson", "date" : "1991-03-20", "_id" : "EobqKRy4Rj5te6Jt7" });
		Birthdays.insert({ "name" : "Daniel Andersson", "date" : "1995-05-21", "_id" : "MgfbxaaDiA9uzcCRM" });
		Birthdays.insert({ "name" : "Nathalie Johansson", "date" : "1995-05-26", "_id" : "bspzFYJNNckBfutHh" });
		Birthdays.insert({ "name" : "Anna Andersson", "date" : "1988-05-24", "_id" : "wt5KXu4xEP7ZmoMiE" });
		Birthdays.insert({ "name" : "Jens Ottosson", "date" : "1970-06-06", "_id" : "juPwujNWuHbcYEFrk" });
		Birthdays.insert({ "name" : "Magnus Karlsson", "date" : "1972-04-22", "_id" : "NNt2H4MbZHZgboZsW" });
		Birthdays.insert({ "name" : "Lennart L", "_id" : "QCCCS4jDWdmLKkHi5" });
		Birthdays.insert({ "name" : "Lennart Lundeborg", "date" : "1969-09-23", "_id" : "dQo2BEgzEhHwMcunE" });
		Birthdays.insert({ "name" : "Matilda Lundeborg", "date" : "1970-01-15", "_id" : "B3LAsPe4g75MwntZi" });
		Birthdays.insert({ "name" : "Rosmarie Johansson", "date" : "1965-09-03", "_id" : "AyrAgtXbBovCCBBJu" });
		Birthdays.insert({ "name" : "Per-Erik Frejd", "date" : "1961-11-15", "_id" : "gXoeqvX6hrhJBHdCT" });
		Birthdays.insert({ "name" : "Ingvar Johansson", "date" : "1961-08-02", "_id" : "7CfdH6d7ogJ4e4m2E" });
		Birthdays.insert({ "name" : "Ola Salomonsson", "date" : "1966-09-15", "_id" : "h2g8mQku2m39aotvn" });
		Birthdays.insert({ "name" : "Ãsa Salomonsson", "date" : "1969-10-01", "_id" : "su59MGyEGPT54nhTC" });
		Birthdays.insert({ "name" : "Mikio Holum", "date" : "1974-09-19", "_id" : "AEZbEiGQsH6gqy92c" });
		Birthdays.insert({ "name" : "Anders Henja", "date" : "1971-06-08", "_id" : "2H6cKNcvAv97HX2Jh" });
		Birthdays.insert({ "name" : "Amelie Cid", "date" : "1973-09-19", "_id" : "kw7esKFGWWHheoLTR" });
		Birthdays.insert({ "name" : "Jesper Kullgren", "date" : "1969-07-04", "_id" : "F73dkZjyP53qshrae" });
		Birthdays.insert({ "name" : "Per Ullgren", "date" : "1969-08-14", "_id" : "C5D6LoJFyayYsAknQ" });
	}
});
