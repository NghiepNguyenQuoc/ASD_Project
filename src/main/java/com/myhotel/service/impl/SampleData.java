package com.myhotel.service.impl;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 
 * @author Cong Khanh Tran - trancongkhanh@gmail.com Data is downloaded from
 *         https://www.briandunning.com/sample-data/ which are randomly
 *         generated. Names are random, constructed from real first and last
 *         names. Company names are real, but are randomized along with street
 *         addresses and do not represent actual locations.
 */
public class SampleData {
	public final static int ROW_COUNT = 50;

//		Address (street, city, state, zip)
	public static final String[] streets = { "6649 N Blue Gum St", "4 B Blue Ridge Blvd", "8 W Cerritos Ave #54",
			"639 Main St", "34 Center St", "3 Mcauley Dr", "7 Eads St", "7 W Jackson Blvd", "5 Boston Ave #88",
			"228 Runamuck Pl #2808", "2371 Jerrold Ave", "37275 St  Rt 17m M", "25 E 75th St #69",
			"98 Connecticut Ave Nw", "56 E Morehead St", "73 State Road 434 E", "69734 E Carrillo St",
			"322 New Horizon Blvd", "1 State Route 27", "394 Manchester Blvd", "6 S 33rd St", "6 Greenleaf Ave",
			"618 W Yakima Ave", "74 S Westgate St", "3273 State St", "1 Central Ave", "86 Nw 66th St #8673",
			"2 Cedar Ave #84", "90991 Thorburn Ave", "386 9th Ave N", "74874 Atlantic Ave", "366 South Dr",
			"45 E Liberty St", "4 Ralph Ct", "2742 Distribution Way", "426 Wolf St", "128 Bransten Rd",
			"17 Morena Blvd", "775 W 17th St", "6980 Dorsett Rd", "2881 Lewis Rd", "7219 Woodfield Rd", "1048 Main St",
			"678 3rd Ave", "20 S Babcock St", "2 Lighthouse Ave", "38938 Park Blvd", "5 Tomahawk Dr", "762 S Main St",
			"209 Decker Dr" };
	public static final String[] cities = { "New Orleans", "Brighton", "Bridgeport", "Anchorage", "Hamilton", "Ashland",
			"Chicago", "San Jose", "Sioux Falls", "Baltimore", "Kulpsville", "Middle Island", "Los Angeles",
			"Chagrin Falls", "Laredo", "Phoenix", "Mc Minnville", "Milwaukee", "Taylor", "Rockford", "Aston",
			"San Jose", "Irving", "Albany", "Middlesex", "Stevens Point", "Shawnee", "Easton", "New York", "Conroe",
			"Columbus", "Las Cruces", "Ridgefield Park", "Dunellen", "New York", "Metairie", "New York", "Camarillo",
			"San Antonio", "Abilene", "Prineville", "Overland Park", "Fairbanks", "Miami", "Fairbanks", "Hopkins",
			"Boston", "Los Angeles", "Madison", "Philadelphia" };

	public static final String[] states = { "LA", "MI", "NJ", "AK", "OH", "OH", "IL", "CA", "SD", "MD", "PA", "NY",
			"CA", "OH", "TX", "AZ", "TN", "WI", "MI", "IL", "PA", "CA", "TX", "NY", "NJ", "WI", "KS", "MD", "NY", "TX",
			"OH", "NM", "NJ", "NJ", "NY", "LA", "NY", "CA", "TX", "KS", "OR", "KS", "AK", "FL", "AK", "MN", "MA", "CA",
			"WI", "PA" };

	public static final String[] zips = { "70116", "48116", "8014", "99501", "45011", "44805", "60632", "95111",
			"57105", "21224", "19443", "11953", "90034", "44023", "78045", "85013", "37110", "53207", "48180", "61109",
			"19014", "95111", "75062", "12204", "8846", "54481", "66218", "21601", "10011", "77301", "43215", "88011",
			"7660", "8812", "10025", "70002", "10011", "93012", "78204", "67410", "97754", "66204", "99708", "33196",
			"99712", "55343", "2128", "90006", "53711", "19132" };

//		Customer (First Name, Last Name, Phone, Email); 
	public static final String[] firstNames = { "James", "Josephine", "Art", "Lenna", "Donette", "Simona", "Mitsue",
			"Leota", "Sage", "Kris", "Minna", "Abel", "Kiley", "Graciela", "Cammy", "Mattie", "Meaghan", "Gladys",
			"Yuki", "Fletcher", "Bette", "Veronika", "Willard", "Maryann", "Alisha", "Allene", "Chanel", "Ezekiel",
			"Willow", "Bernardo", "Ammie", "Francine", "Ernie", "Albina", "Alishia", "Solange", "Jose", "Rozella",
			"Valentine", "Kati", "Youlanda", "Dyan", "Roxane", "Lavera", "Erick", "Fatima", "Jina", "Kanisha",
			"Emerson", "Blair" };

	public static final String[] lastNames = { "Butt", "Darakjy", "Venere", "Paprocki", "Foller", "Morasca", "Tollner",
			"Dilliard", "Wieser", "Marrier", "Amigon", "Maclead", "Caldarera", "Ruta", "Albares", "Poquette", "Garufi",
			"Rim", "Whobrey", "Flosi", "Nicka", "Inouye", "Kolmetz", "Royster", "Slusarski", "Iturbide", "Caudy",
			"Chui", "Kusko", "Figeroa", "Corrio", "Vocelka", "Stenseth", "Glick", "Sergi", "Shinko", "Stockham",
			"Ostrosky", "Gillian", "Rulapaugh", "Schemmer", "Oldroyd", "Campain", "Perin", "Ferencz", "Saylors",
			"Briddick", "Waycott", "Bowley", "Malet" };

	public static final String[] phones = { "504-621-8927", "810-292-9388", "856-636-8749", "907-385-4412",
			"513-570-1893", "419-503-2484", "773-573-6914", "408-752-3500", "605-414-2147", "410-655-8723",
			"215-874-1229", "631-335-3414", "310-498-5651", "440-780-8425", "956-537-6195", "602-277-4385",
			"931-313-9635", "414-661-9598", "313-288-7937", "815-828-2147", "610-545-3615", "408-540-1785",
			"972-303-9197", "518-966-7987", "732-658-3154", "715-662-6764", "913-388-2079", "410-669-1642",
			"212-582-4976", "936-336-3951", "614-801-9788", "505-977-3911", "201-709-6245", "732-924-7882",
			"212-860-1579", "504-979-9175", "212-675-8570", "805-832-6163", "210-812-9597", "785-463-7829",
			"541-548-8197", "913-413-4604", "907-231-4722", "305-606-7291", "907-741-1044", "952-768-2416",
			"617-399-5124", "323-453-2780", "608-336-7444", "215-907-9111" };

	public static final String[] emails = { "jbutt@gmail.com", "josephine_darakjy@darakjy.org", "art@venere.org",
			"lpaprocki@hotmail.com", "donette.foller@cox.net", "simona@morasca.com", "mitsue_tollner@yahoo.com",
			"leota@hotmail.com", "sage_wieser@cox.net", "kris@gmail.com", "minna_amigon@yahoo.com",
			"amaclead@gmail.com", "kiley.caldarera@aol.com", "gruta@cox.net", "calbares@gmail.com", "mattie@aol.com",
			"meaghan@hotmail.com", "gladys.rim@rim.org", "yuki_whobrey@aol.com", "fletcher.flosi@yahoo.com",
			"bette_nicka@cox.net", "vinouye@aol.com", "willard@hotmail.com", "mroyster@royster.com",
			"alisha@slusarski.com", "allene_iturbide@cox.net", "chanel.caudy@caudy.org", "ezekiel@chui.com",
			"wkusko@yahoo.com", "bfigeroa@aol.com", "ammie@corrio.com", "francine_vocelka@vocelka.com",
			"ernie_stenseth@aol.com", "albina@glick.com", "asergi@gmail.com", "solange@shinko.com", "jose@yahoo.com",
			"rozella.ostrosky@ostrosky.com", "valentine_gillian@gmail.com", "kati.rulapaugh@hotmail.com",
			"youlanda@aol.com", "doldroyd@aol.com", "roxane@hotmail.com", "lperin@perin.org", "erick.ferencz@aol.com",
			"fsaylors@saylors.org", "jina_briddick@briddick.com", "kanisha_waycott@yahoo.com",
			"emerson.bowley@bowley.org", "bmalet@yahoo.com" };

	public static int months[] = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12 };

	public static int dates[] = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24,
			25, 26, 27, 28 };

	public static int random(int max) {
		return ThreadLocalRandom.current().nextInt(0, max);
	}

	public static Date randomDate() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(2018, months[random(months.length)] - 1, dates[random(dates.length)]);
		return calendar.getTime();
	}
	
	public static Date randomDate(int maxMonth) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(2018, months[random(months.length)] % maxMonth, dates[random(dates.length)]);
		return calendar.getTime();
	}

	public static Date nextDate(Date input) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(input);
		calendar.add(Calendar.DAY_OF_MONTH, 1);
		return calendar.getTime();
	}
}
