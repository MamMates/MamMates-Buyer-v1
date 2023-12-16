package com.mammates.mammates_buyer_v1.data.source.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


class IntroPreference @Inject constructor(
    private val dataStore: DataStore<Preferences>
) {

    private val INTRO_IS_DONE = booleanPreferencesKey("intro_is_done")

    suspend fun setIntroIsDone(isDone: Boolean) {
        dataStore.edit { preferences ->
            preferences[INTRO_IS_DONE] = isDone
        }
    }

    fun getIntroIsDone(): Flow<Boolean> {
        return dataStore.data.map { preferences ->
            preferences[INTRO_IS_DONE] ?: false
        }
    }
}