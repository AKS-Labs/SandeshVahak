package com.akslabs.SandeshVahak.data.localdb // Corrected package declaration

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys

object Preferences {

    // encrypted preferences
    const val botToken: String = "botToken"
    const val channelId: String = "channelId"

    // non-encrypted preferences
    const val startTabKey: String = "startTab"
    const val isAutoBackupEnabledKey: String = "isPeriodicPhotoBackupEnabled" // Legacy key, prefer isSmsSyncEnabledKey for SMS sync
    const val autoBackupIntervalKey: String = "periodicPhotoBackupInterval"
    const val autoBackupNetworkTypeKey: String = "periodicPhotoBackupNetworkType"
    const val isAutoExportDatabaseEnabledKey: String = "isAutoExportDatabaseEnabled"
    const val autoExportDatabaseIntervalKey: String = "autoExportDatabaseInterval"
    const val autoExportDatabseLocation: String = "autoExportDatabaseLocation"

    const val defaultAutoExportDatabaseIntervalKey: Long = 7
    const val defaultAutoBackupInterval: Long = 7

    // SMS sync preferences
    const val isSmsSyncEnabledKey: String = "isSmsSyncEnabled" // Master switch for SMS sync
    const val smsSyncModeKey: String = "smsSyncMode" // values: SMS_SYNC_MODE_ALL, SMS_SYNC_MODE_NEW_ONLY
    const val SMS_SYNC_MODE_ALL = "ALL"
    const val SMS_SYNC_MODE_NEW_ONLY = "NEW_ONLY"
    const val smsSyncEnabledSinceKey: String = "smsSyncEnabledSince" // Timestamp of the last synced SMS or when "NEW_ONLY" was set

    // Power & startup preferences
    const val isAutoStartOnBootEnabledKey: String = "isAutoStartOnBootEnabled"
    const val isBatteryOptimizationExemptionRequestedKey: String = "isBatteryOptimizationExemptionRequested"

    private const val prefFile: String = "preferences"
    private const val encryptedPrefFile: String = "encryptedPreferences"
    private lateinit var preferences: SharedPreferences
    private lateinit var encryptedPreferences: SharedPreferences

    fun init(context: Context) {
        try {
            preferences = context.getSharedPreferences(prefFile, Context.MODE_PRIVATE)
            val keyGenParameterSpec = MasterKeys.AES256_GCM_SPEC
            val masterKeyAlias = MasterKeys.getOrCreate(keyGenParameterSpec)
            encryptedPreferences = EncryptedSharedPreferences.create(
                encryptedPrefFile,
                masterKeyAlias,
                context,
                EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
            )
            migrateToEncrypted(preferences.all.filterKeys { it == botToken || it == channelId })
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun getBoolean(key: String, defValue: Boolean): Boolean {
        return try {
            if (::preferences.isInitialized) {
                preferences.getBoolean(key, defValue)
            } else {
                android.util.Log.w("Preferences", "Preferences not initialized, returning default value for key: $key")
                defValue
            }
        } catch (e: Exception) {
            android.util.Log.e("Preferences", "Error reading boolean preference $key", e)
            defValue
        }
    }

    fun getString(key: String, defValue: String): String {
        return try {
            if (::preferences.isInitialized) {
                preferences.getString(key, defValue) ?: defValue
            } else {
                android.util.Log.w("Preferences", "Preferences not initialized, returning default value for key: $key")
                defValue
            }
        } catch (e: Exception) {
            android.util.Log.e("Preferences", "Error reading string preference $key", e)
            defValue
        }
    }

    fun getFloat(key: String, defValue: Float): Float {
        return try {
            if (::preferences.isInitialized) {
                preferences.getFloat(key, defValue)
            } else {
                android.util.Log.w("Preferences", "Preferences not initialized, returning default value for key: $key")
                defValue
            }
        } catch (e: Exception) {
            android.util.Log.e("Preferences", "Error reading float preference $key", e)
            defValue
        }
    }

    fun getLong(key: String, defValue: Long): Long {
        return try {
            if (::preferences.isInitialized) {
                preferences.getLong(key, defValue)
            } else {
                android.util.Log.w("Preferences", "Preferences not initialized, returning default value for key: $key")
                defValue
            }
        } catch (e: Exception) {
            android.util.Log.e("Preferences", "Error reading long preference $key", e)
            defValue
        }
    }

    fun getStringSet(key: String, defValue: Set<String>): Set<String> {
        return try {
            if (::preferences.isInitialized) {
                preferences.getStringSet(key, defValue) ?: defValue
            } else {
                android.util.Log.w("Preferences", "Preferences not initialized, returning default value for key: $key")
                defValue
            }
        } catch (e: Exception) {
            android.util.Log.e("Preferences", "Error reading string set preference $key", e)
            defValue
        }
    }

    fun edit(action: SharedPreferences.Editor.() -> Unit) {
        try {
            if (::preferences.isInitialized) {
                preferences.edit().apply(action).apply()
            } else {
                android.util.Log.w("Preferences", "Preferences not initialized, cannot edit")
            }
        } catch (e: Exception) {
            android.util.Log.e("Preferences", "Error editing preferences", e)
        }
    }

    fun getEncryptedBoolean(key: String, defValue: Boolean) = encryptedPreferences.getBoolean(key, defValue)
    fun getEncryptedString(key: String, defValue: String) = encryptedPreferences.getString(key, defValue) ?: defValue
    fun getEncryptedFloat(key: String, defValue: Float) = encryptedPreferences.getFloat(key, defValue)
    fun getEncryptedLong(key: String, defValue: Long) = encryptedPreferences.getLong(key, defValue)
    fun getEncryptedStringSet(key: String, defValue: Set<String>) =
        encryptedPreferences.getStringSet(key, defValue) ?: defValue

    fun editEncrypted(action: SharedPreferences.Editor.() -> Unit) {
        encryptedPreferences.edit().apply(action).apply()
    }

    fun migrateAllToEncrypted(context: Context) {
        if (preferences.all.isNotEmpty()) {
            migrateToEncrypted(preferences.all)
        }
    }

    private fun migrateToEncrypted(keys: Map<String, *>) {
        for ((key, value1) in keys) {
            val value = value1!!
            Log.d("map values", "$key: $value")
            when (value) {
                is Int -> {
                    editEncrypted { putInt(key, value) }
                    edit { remove(key) }
                }

                is Boolean -> {
                    editEncrypted { putBoolean(key, value) }
                    edit { remove(key) }
                }

                is Long -> {
                    editEncrypted { putLong(key, value) }
                    edit { remove(key) }
                }

                is Float -> {
                    editEncrypted { putFloat(key, value) }
                    edit { remove(key) }
                }

                else -> {
                    editEncrypted { putString(key, value.toString()) }
                    edit { remove(key) }
                }
            }
        }
    }
}
