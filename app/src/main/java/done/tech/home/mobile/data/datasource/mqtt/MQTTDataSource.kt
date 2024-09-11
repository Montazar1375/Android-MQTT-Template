package done.tech.home.mobile.data.datasource.mqtt

import kotlinx.coroutines.flow.Flow


interface MQTTDataSource {
    suspend fun connect()
    fun isConnected(): Flow<Boolean>
    fun subscribe(topic: String): Flow<String>
    suspend fun publish(topic: String, message: ByteArray)
}