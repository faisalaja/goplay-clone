object Constants {
    const val API_VERSION = "3"
    const val DEFAULT_LANGUAGE = "en-US"

    const val MOVIE = "movie"
    const val TV = "tv"

    const val SEARCH = "$API_VERSION/search"
    const val PERSON = "$API_VERSION/person"
}

object Type {
    const val TOP_RATED = "top_rated"
    const val POPULAR = "popular"
}

object MovieType {
    const val NOW_PLAYING = "now_playing"
    const val TOP_RATED = Type.TOP_RATED
    const val UPCOMING = "upcoming"
    const val POPULAR = Type.POPULAR
}

object TvShowType {
    const val AIRING_TODAY = "airing_today"
    const val ON_THE_AIR = "on_the_air"
    const val TOP_RATED = Type.TOP_RATED
    const val POPULAR = Type.POPULAR
}