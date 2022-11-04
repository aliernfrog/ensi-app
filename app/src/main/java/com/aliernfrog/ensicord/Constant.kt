package com.aliernfrog.ensicord

object AddonFetchState {
    const val ADDONS_LOADING = 0
    const val ADDONS_DONE = 1
}

object ChatConstants {
    const val MESSAGE_CHAR_LIMIT = 4000
}

object ConfigKey {
    const val PREF_NAME = "APP_CONFIG"
    const val KEY_APP_THEME = "appTheme"
    const val KEY_USER_NAME = "userName"
    const val KEY_USER_STATUS = "userStatus"
    const val KEY_ENSI_NAME = "ensiName"
    const val KEY_ADDON_REPOS = "addonRepos"
    const val DEFAULT_USER_NAME = "Some frok"
    const val DEFAULT_ENSI_NAME = "Ensi"
    val DEFAULT_ADDON_REPOS = setOf("https://aliernfrog.github.io/ensicord-addons/addons.json")
}

object NavDestinations {
    const val CHAT = "chat"
    const val PROFILE = "profile"
    const val ADDONS = "addons"
    const val ADDONS_REPOS = "addonsRepos"
    const val OPTIONS = "options"
}

object Path {
    const val PATH_DATA = "/Android/data/com.aliernfrog.ensicord/files"
    const val PATH_AVATAR = "$PATH_DATA/avatar.png"
}

object Theme {
    const val SYSTEM = 0
    const val LIGHT = 1
    const val DARK = 2
}