package com.wahyuabid.mulungcrypto.core.firebaseconfig

import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import java.util.concurrent.Executor
import java.util.concurrent.TimeUnit

/**
 * Created by Wahyu Abid A on 29/12/21
 * Email : wahyu.abied@gmail.com
 * Github: https://github.com/wahyuabied/
 */
class FirebaseConfig(
    private val firebaseRemoteConfig: FirebaseRemoteConfig? = FirebaseRemoteConfig.getInstance(),
    private val executor: Executor = BackgroundExecutor(),
    private val defaultInitialValue: Map<String, Any> = DEFAULT_INITIAL_VALUE
) : Config {
    override fun fetch(listener: FetchListener?) {
        firebaseRemoteConfig?.let { frc ->
            frc.setDefaultsAsync(defaultInitialValue)
            frc.fetch(TimeUnit.MINUTES.toSeconds(2))
                .addOnSuccessListener(executor, OnSuccessListener {
                    frc.activate()
                    listener?.onSuccess(this)
                })
                .addOnFailureListener(executor, OnFailureListener { e ->
                    listener?.onError(e)
                })
        }
    }

    override fun getString(key: String, defaultValue: String): String {
        return firebaseRemoteConfig?.getString(key) ?: defaultValue
    }

    override fun getBoolean(key: String, defaultValue: Boolean): Boolean {
        return firebaseRemoteConfig?.getBoolean(key) ?: defaultValue
    }

    override fun getDouble(key: String, defaultValue: Double): Double {
        return firebaseRemoteConfig?.getDouble(key) ?: defaultValue
    }

    override fun getLong(key: String, defaultValue: Long): Long {
        return firebaseRemoteConfig?.getLong(key) ?: defaultValue
    }
}