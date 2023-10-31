package app.thirtyninth.compose.navigation.example.screens

object DefaultScreenNameExtensions {
    /**
     * Default realisation of screen name for compose navigation, without parameters
     *
     * @return the simple name of the underlying class
     */
    fun Screen.defaultScreenName(): String = this::class.java.simpleName

    /**
     * Default realisation of screen name for compose navigation, with required parameters
     *
     * @return the string with parameters of navigation like in web: "screenName/argument"
     */
    fun Screen.defaultScreenNameWithParams(vararg params: String): String {
        return defaultScreenName() + params.joinToString { "/{$it}" }
    }

    /**
     * Default realisation of screen name for compose navigation,
     * with required and optional parameters
     *
     * @return the string with parameters of navigation like in web: "screenName/argument" or
     * with optional parameters: "screenName/requiredArgument?optionalArgument={optionalValue}"
     */
    fun Screen.defaultScreenNameWithOptionalParams(
        params: List<String>,
        optionalParams: List<String>
    ): String {
        val optionals = optionalParams.joinToString { "?$it={$it}" }
        return defaultScreenName() + params.joinToString { "/{$it}" } + optionals
    }

    /**
     * Default realisation of screen name for compose navigation,
     * with optional parameters
     *
     * @return the string with parameters of navigation like in web:
     * "screenName/?optionalArgument={optionalValue}"
     */
    fun Screen.defaultScreenNameWithOptionalParams(optionalParams: List<String>): String {
        val optionals = optionalParams.joinToString { "?$it={$it}" }
        return defaultScreenName() + optionals
    }
}
