package dev.haqim.netplix.core.domain.model

import dev.haqim.netplix.BuildConfig

data class Movie(
	val overview: String,
	val originalLanguage: String,
	val originalTitle: String,
	val video: Boolean,
	val title: String,
	val posterPath: String,
	val backdropPath: String,
	val releaseDate: String,
	val popularity: Double,
	val voteAverage: Double,
	val id: Int,
	val adult: Boolean,
	val voteCount: Int,
	val trailer: MovieTrailer? = null
){
	val posterUrl = BuildConfig.BASE_IMAGE_URL + posterPath
}

val dummyMovies = listOf(
	Movie(
		overview = """
            The story begins with a daring heist that sets the stage for a thrilling adventure. 
            The characters face numerous obstacles as they try to escape with their prize. 
            The twists and turns keep you at the edge of your seat, and by the end, nothing is as it seems. 
            A must-watch for fans of high-octane thrillers.
        """.trimIndent(),
		originalLanguage = "en",
		originalTitle = "Stolen Secrets",
		video = false,
		title = "The Great Heist",
		posterPath = "/ngl2FKBlU4fhbdsrtdom9LVLBXw.jpg",
		backdropPath = "/ngl2FKBlU4fhbdsrtdom9LVLBXw.jpg",
		releaseDate = "2024-02-01",
		popularity = 91.0,
		voteAverage = 8.1,
		id = 1,
		adult = false,
		voteCount = 305,
		trailer = MovieTrailer(site = "YouTube", key = "O-b2VfmmbyA")
	),
	Movie(
		overview = """
            A young woman embarks on a journey to find the truth about her family’s mysterious past. 
            Along the way, she uncovers hidden secrets and faces life-changing challenges. 
            The emotional stakes rise as she unravels a conspiracy that could alter her destiny forever.
        """.trimIndent(),
		originalLanguage = "en",
		originalTitle = "Uncovering the Past",
		video = false,
		title = "Legacy of Lies",
		posterPath = "/uDsvma9dAwnDPVuCFi99YpWvBk0.jpg",
		backdropPath = "/tFaC1Fb1sv1dALB0i9Avi76MHmn.jpg",
		releaseDate = "2024-02-01",
		popularity = 93.0,
		voteAverage = 7.8,
		id = 2,
		adult = false,
		voteCount = 312,
		trailer = MovieTrailer(site = "YouTube", key = "O-b2VfmmbyA")
	),
	Movie(
		overview = """
            A legendary fighter comes out of retirement for one last battle. 
            With time running out and rivals closing in, this final confrontation could mean the end of everything. 
            The movie explores themes of redemption, sacrifice, and the true cost of glory.
        """.trimIndent(),
		originalLanguage = "en",
		originalTitle = "The Final Round",
		video = false,
		title = "Blood and Glory",
		posterPath = "/qNBAXBIQlnOThrVvA6mA2B5ggV6.jpg",
		backdropPath = "/qNBAXBIQlnOThrVvA6mA2B5ggV6.jpg",
		releaseDate = "2024-03-01",
		popularity = 95.0,
		voteAverage = 7.4,
		id = 3,
		adult = false,
		voteCount = 198,
		trailer = MovieTrailer(site = "YouTube", key = "O-b2VfmmbyA")
	),
	Movie(
		overview = """
            A struggling musician, in search of his big break, finds himself caught in a whirlwind of love, fame, and betrayal. 
            His rise to stardom tests his loyalty to friends and family. The movie is a raw look into the music industry's dark side.
        """.trimIndent(),
		originalLanguage = "en",
		originalTitle = "Fame's Price",
		video = false,
		title = "The Sound of Ambition",
		posterPath = "/uDsvma9dAwnDPVuCFi99YpWvBk0.jpg",
		backdropPath = "/tFaC1Fb1sv1dALB0i9Avi76MHmn.jpg",
		releaseDate = "2024-03-01",
		popularity = 78.0,
		voteAverage = 6.9,
		id = 4,
		adult = false,
		voteCount = 185,
		trailer = MovieTrailer(site = "YouTube", key = "O-b2VfmmbyA")
	),
	Movie(
		overview = """
            In a dystopian future, a group of rebels must fight against an oppressive regime that controls all aspects of life. 
            The stakes are high, and the cost of freedom could be the ultimate sacrifice. 
            Will they succeed or will they fall into the hands of a regime that shows no mercy?
        """.trimIndent(),
		originalLanguage = "en",
		originalTitle = "Revolution Rising",
		video = false,
		title = "The Last Stand",
		posterPath = "/qNBAXBIQlnOThrVvA6mA2B5ggV6.jpg",
		backdropPath = "/qNBAXBIQlnOThrVvA6mA2B5ggV6.jpg",
		releaseDate = "2024-04-01",
		popularity = 100.0,
		voteAverage = 7.3,
		id = 5,
		adult = false,
		voteCount = 420,
		trailer = MovieTrailer(site = "YouTube", key = "O-b2VfmmbyA")
	),
	Movie(
		overview = """
            A mysterious alien species lands on Earth, and the world must come together to understand their intentions. 
            As tensions rise, a group of scientists and military leaders try to make contact. 
            But with each passing day, the question becomes: Are they here to help, or to conquer?
        """.trimIndent(),
		originalLanguage = "en",
		originalTitle = "The Unknown Visitor",
		video = false,
		title = "Contact",
		posterPath = "/ngl2FKBlU4fhbdsrtdom9LVLBXw.jpg",
		backdropPath = "/ngl2FKBlU4fhbdsrtdom9LVLBXw.jpg",
		releaseDate = "2024-04-01",
		popularity = 85.0,
		voteAverage = 8.0,
		id = 6,
		adult = false,
		voteCount = 342,
		trailer = MovieTrailer(site = "YouTube", key = "O-b2VfmmbyA")
	),
	Movie(
		overview = """
            A young prodigy enters a prestigious university, but soon realizes that it’s a place filled with cutthroat competition. 
            As he strives for success, he begins to question the cost of his dreams. 
            A tale of ambition, pressure, and the price of success.
        """.trimIndent(),
		originalLanguage = "en",
		originalTitle = "Dreams in the Ivy League",
		video = false,
		title = "Pressure",
		posterPath = "/qNBAXBIQlnOThrVvA6mA2B5ggV6.jpg",
		backdropPath = "/qNBAXBIQlnOThrVvA6mA2B5ggV6.jpg",
		releaseDate = "2024-05-01",
		popularity = 77.0,
		voteAverage = 6.8,
		id = 7,
		adult = false,
		voteCount = 312,
		trailer = MovieTrailer(site = "YouTube", key = "O-b2VfmmbyA")
	),
	Movie(
		overview = """
            After a brutal war, the world is left in ruins. A small band of survivors must navigate the wasteland to find safety. 
            Along the way, they encounter threats both human and monstrous, and must decide whether to stay together or go it alone. 
            Survival has a new meaning in this post-apocalyptic world.
        """.trimIndent(),
		originalLanguage = "en",
		originalTitle = "Wasteland Survivors",
		video = false,
		title = "After the Fall",
		posterPath = "/uDsvma9dAwnDPVuCFi99YpWvBk0.jpg",
		backdropPath = "/tFaC1Fb1sv1dALB0i9Avi76MHmn.jpg",
		releaseDate = "2024-05-01",
		popularity = 80.0,
		voteAverage = 7.1,
		id = 8,
		adult = false,
		voteCount = 291,
		trailer = MovieTrailer(site = "YouTube", key = "O-b2VfmmbyA")
	),
	Movie(
		overview = """
            A haunted house with a dark past. A family of four move in, unaware of the sinister secrets the house holds. 
            As strange occurrences increase, they realize they may be living with more than just ghosts. 
            A terrifying journey to uncover the truth awaits them.
        """.trimIndent(),
		originalLanguage = "en",
		originalTitle = "The Haunted House",
		video = false,
		title = "Whispers in the Dark",
		posterPath = "/2VK4d3mqqTc7LVZLnLPeRiPaJ71.jpg",
		backdropPath = "/2VK4d3mqqTc7LVZLnLPeRiPaJ71.jpg",
		releaseDate = "2024-06-01",
		popularity = 68.0,
		voteAverage = 6.6,
		id = 9,
		adult = false,
		voteCount = 245,
		trailer = MovieTrailer(site = "YouTube", key = "O-b2VfmmbyA")
	),
	Movie(
		overview = """
            In the midst of a global crisis, a group of scientists races against time to find a cure for a deadly virus. 
            With the world on the brink of collapse, they must navigate betrayal, loss, and ethical dilemmas to save humanity. 
            Will they find a solution, or is it too late?
        """.trimIndent(),
		originalLanguage = "en",
		originalTitle = "Virus Containment",
		video = false,
		title = "Pandemic Protocol",
		posterPath = "/uDsvma9dAwnDPVuCFi99YpWvBk0.jpg",
		backdropPath = "/tFaC1Fb1sv1dALB0i9Avi76MHmn.jpg",
		releaseDate = "2024-06-01",
		popularity = 92.0,
		voteAverage = 7.9,
		id = 10,
		adult = false,
		voteCount = 358,
		trailer = MovieTrailer(site = "YouTube", key = "O-b2VfmmbyA")
	),
	Movie(
		overview = """
            A young detective with a troubled past is assigned to a high-profile case that challenges her skills and her morals. 
            As she delves deeper into the investigation, she begins to question everything she thought she knew about justice. 
            The line between right and wrong becomes increasingly blurred in this gripping crime thriller.
        """.trimIndent(),
		originalLanguage = "en",
		originalTitle = "Criminal Justice",
		video = false,
		title = "The Longest Night",
		posterPath = "/ngl2FKBlU4fhbdsrtdom9LVLBXw.jpg",
		backdropPath = "/ngl2FKBlU4fhbdsrtdom9LVLBXw.jpg",
		releaseDate = "2024-07-01",
		popularity = 75.0,
		voteAverage = 7.2,
		id = 11,
		adult = false,
		voteCount = 273,
		trailer = MovieTrailer(site = "YouTube", key = "O-b2VfmmbyA")
	),
	Movie(
		overview = """
            A seemingly ordinary family discovers that their home is built on ancient ruins with dark, supernatural powers. 
            As strange events unfold, they must decide whether to leave or confront the malevolent forces at play. 
            A psychological horror that will keep you questioning what’s real until the very end.
        """.trimIndent(),
		originalLanguage = "en",
		originalTitle = "Haunting Legacy",
		video = false,
		title = "The Forgotten House",
		posterPath = "/uDsvma9dAwnDPVuCFi99YpWvBk0.jpg",
		backdropPath = "/tFaC1Fb1sv1dALB0i9Avi76MHmn.jpg",
		releaseDate = "2024-07-01",
		popularity = 82.0,
		voteAverage = 6.9,
		id = 12,
		adult = false,
		voteCount = 219,
		trailer = MovieTrailer(site = "YouTube", key = "O-b2VfmmbyA")
	),
	Movie(
		overview = """
            In a future where technology rules, a team of elite hackers must break into the most secure systems to expose a global conspiracy. 
            As they face increasing dangers, trust becomes their most valuable currency. Can they outwit those who control the digital world?
        """.trimIndent(),
		originalLanguage = "en",
		originalTitle = "Digital Revolution",
		video = false,
		title = "Codebreakers",
		posterPath = "/uDsvma9dAwnDPVuCFi99YpWvBk0.jpg",
		backdropPath = "/tFaC1Fb1sv1dALB0i9Avi76MHmn.jpg",
		releaseDate = "2024-08-01",
		popularity = 87.0,
		voteAverage = 7.7,
		id = 13,
		adult = false,
		voteCount = 330,
		trailer = MovieTrailer(site = "YouTube", key = "O-b2VfmmbyA")
	),
	Movie(
		overview = """
            A world-weary scientist discovers an ancient artifact that holds the key to unlocking mankind’s greatest potential. 
            But with power comes responsibility, and forces beyond his control are determined to claim it for themselves. 
            This action-packed sci-fi adventure delves into the price of innovation.
        """.trimIndent(),
		originalLanguage = "en",
		originalTitle = "Unlocking the Future",
		video = false,
		title = "The Quantum Key",
		posterPath = "/ngl2FKBlU4fhbdsrtdom9LVLBXw.jpg",
		backdropPath = "/ngl2FKBlU4fhbdsrtdom9LVLBXw.jpg",
		releaseDate = "2024-08-01",
		popularity = 91.0,
		voteAverage = 8.0,
		id = 14,
		adult = false,
		voteCount = 411,
		trailer = MovieTrailer(site = "YouTube", key = "O-b2VfmmbyA")
	),
	Movie(
		overview = """
            A group of childhood friends reunite at a secluded cabin for a weekend getaway, only to discover that someone is watching them. 
            As paranoia sets in, old wounds resurface, and the group realizes that the real threat might come from within. 
            A taut psychological thriller with unexpected twists.
        """.trimIndent(),
		originalLanguage = "en",
		originalTitle = "Return to Fear",
		video = false,
		title = "The Watchers",
		posterPath = "/uDsvma9dAwnDPVuCFi99YpWvBk0.jpg",
		backdropPath = "/tFaC1Fb1sv1dALB0i9Avi76MHmn.jpg",
		releaseDate = "2024-09-01",
		popularity = 74.0,
		voteAverage = 7.0,
		id = 15,
		adult = false,
		voteCount = 238,
		trailer = MovieTrailer(site = "YouTube", key = "O-b2VfmmbyA")
	),
	Movie(
		overview = """
            In a small town, a tragic accident leads to the disappearance of a beloved local teacher. 
            A determined young reporter sets out to uncover the truth behind the mystery, only to realize that the town’s darkest secrets are hidden in plain sight. 
            A compelling mystery that explores the cost of uncovering the truth.
        """.trimIndent(),
		originalLanguage = "en",
		originalTitle = "Silent Echoes",
		video = false,
		title = "The Vanishing Point",
		posterPath = "/uDsvma9dAwnDPVuCFi99YpWvBk0.jpg",
		backdropPath = "/tFaC1Fb1sv1dALB0i9Avi76MHmn.jpg",
		releaseDate = "2024-09-01",
		popularity = 88.0,
		voteAverage = 7.5,
		id = 16,
		adult = false,
		voteCount = 277,
		trailer = MovieTrailer(site = "YouTube", key = "O-b2VfmmbyA")
	),
	Movie(
		overview = """
            A young couple inherits a farmhouse and moves in, eager to start a new life. However, they soon realize that the house is haunted by the spirits of its former owners. 
            As strange occurrences escalate, the couple is forced to confront their own fears and unravel the mystery of the house before it's too late.
        """.trimIndent(),
		originalLanguage = "en",
		originalTitle = "House of Shadows",
		video = false,
		title = "Echoes of the Past",
		posterPath = "/ngl2FKBlU4fhbdsrtdom9LVLBXw.jpg",
		backdropPath = "/ngl2FKBlU4fhbdsrtdom9LVLBXw.jpg",
		releaseDate = "2024-10-01",
		popularity = 90.0,
		voteAverage = 6.8,
		id = 17,
		adult = false,
		voteCount = 325,
		trailer = MovieTrailer(site = "YouTube", key = "O-b2VfmmbyA")
	),
	Movie(
		overview = """
            A team of astronauts embarks on a mission to explore an unknown planet, but when they arrive, they find themselves trapped in a deadly game of survival. 
            As they uncover the planet's secrets, they realize they are not alone—and the planet itself is alive. 
            A science-fiction thriller that will keep you guessing until the last minute.
        """.trimIndent(),
		originalLanguage = "en",
		originalTitle = "Beyond the Stars",
		video = false,
		title = "Survival on Orion",
		posterPath = "/uDsvma9dAwnDPVuCFi99YpWvBk0.jpg",
		backdropPath = "/tFaC1Fb1sv1dALB0i9Avi76MHmn.jpg",
		releaseDate = "2024-10-01",
		popularity = 96.0,
		voteAverage = 8.2,
		id = 18,
		adult = false,
		voteCount = 400,
		trailer = MovieTrailer(site = "YouTube", key = "O-b2VfmmbyA")
	),
	Movie(
		overview = """
            A promising young lawyer is assigned to a high-profile case involving a powerful corporation. 
            As she digs deeper, she discovers the lengths the corporation is willing to go to protect its secrets. 
            Torn between her morals and ambition, she must decide how far she’s willing to go to seek justice.
        """.trimIndent(),
		originalLanguage = "en",
		originalTitle = "Corporate Justice",
		video = false,
		title = "The Legal Gambit",
		posterPath = "/qNBAXBIQlnOThrVvA6mA2B5ggV6.jpg",
		backdropPath = "/qNBAXBIQlnOThrVvA6mA2B5ggV6.jpg",
		releaseDate = "2024-11-01",
		popularity = 79.0,
		voteAverage = 7.3,
		id = 19,
		adult = false,
		voteCount = 245,
		trailer = MovieTrailer(site = "YouTube", key = "O-b2VfmmbyA")
	),
	Movie(
		overview = """
            In a dystopian future, a group of rebels fights to overthrow a tyrannical government that controls all aspects of society. 
            As they plan their final strike, they must confront their own fears and doubts, knowing that one wrong move could mean the end of their rebellion—and their lives.
        """.trimIndent(),
		originalLanguage = "en",
		originalTitle = "Revolt Against Control",
		video = false,
		title = "The Last Stand",
		posterPath = "/ngl2FKBlU4fhbdsrtdom9LVLBXw.jpg",
		backdropPath = "/ngl2FKBlU4fhbdsrtdom9LVLBXw.jpg",
		releaseDate = "2024-11-01",
		popularity = 105.0,
		voteAverage = 8.0,
		id = 20,
		adult = false,
		voteCount = 455,
		trailer = MovieTrailer(site = "YouTube", key = "O-b2VfmmbyA")
	)
)



