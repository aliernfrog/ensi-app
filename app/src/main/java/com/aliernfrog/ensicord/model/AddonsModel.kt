package com.aliernfrog.ensicord.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aliernfrog.ensicord.AddonFetchingState
import com.aliernfrog.ensicord.data.Addon
import com.aliernfrog.ensicord.util.AddonsUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddonsModel: ViewModel() {
    var addons = ArrayList<Addon>()
    var state by mutableStateOf(AddonFetchingState.ADDONS_LOADING)

    fun fetchAddons(repos: Set<String>) {
        viewModelScope.launch(Dispatchers.IO) {
            addons = AddonsUtil.getAddons(repos)
            state = AddonFetchingState.ADDONS_DONE
        }
    }
}