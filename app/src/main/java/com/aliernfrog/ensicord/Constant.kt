package com.aliernfrog.ensicord

object AddonFetchingState {
    const val ADDONS_LOADING = 0
    const val ADDONS_DONE = 1
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
    const val DEFAULT_ADDON_REPO = "https://aliernfrog.github.io/ensicord-addons/addons.json"
}

object NavDestinations {
    const val CHAT = "chat"
    const val PROFILE = "profile"
    const val ADDONS = "addons"
    const val ADDONS_REPOS = "addonsRepos"
    const val OPTIONS = "options"
}

object Theme {
    const val SYSTEM = 0
    const val LIGHT = 1
    const val DARK = 2
}