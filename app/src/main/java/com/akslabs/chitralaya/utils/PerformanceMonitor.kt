package com.akslabs.SandeshVahak.utils

import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.atomic.AtomicLong

/**
 * Performance monitoring utility for tracking SMS operations
 */
object PerformanceMonitor {
    private const val TAG = "PerformanceMonitor"
    
    // Performance metrics storage
    private val operationTimes = ConcurrentHashMap<String, MutableList<Long>>()
    private val operationCounts = ConcurrentHashMap<String, AtomicLong>()
    private val errorCounts = ConcurrentHashMap<String, AtomicLong>()
    
    /**
     * Track the execution time of an operation
     */
    fun <T> trackOperation(operationName: String, operation: () -> T): T {
        val startTime = System.currentTimeMillis()
        return try {
            val result = operation()
            val duration = System.currentTimeMillis() - startTime
            recordSuccess(operationName, duration)
            result
        } catch (e: Exception) {
            val duration = System.currentTimeMillis() - startTime
            recordError(operationName, duration, e)
            throw e
        }
    }

    /**
     * Track the execution time of a suspend operation
     */
    suspend fun <T> trackSuspendOperation(operationName: String, operation: suspend () -> T): T {
        val startTime = System.currentTimeMillis()
        return try {
            val result = operation()
            val duration = System.currentTimeMillis() - startTime
            recordSuccess(operationName, duration)
            result
        } catch (e: Exception) {
            val duration = System.currentTimeMillis() - startTime
            recordError(operationName, duration, e)
            throw e
        }
    }
    
    /**
     * Record a successful operation
     */
    internal fun recordSuccess(operationName: String, duration: Long) {
        // Store timing data
        operationTimes.computeIfAbsent(operationName) { mutableListOf() }.add(duration)
        operationCounts.computeIfAbsent(operationName) { AtomicLong(0) }.incrementAndGet()
        
        // Log performance info
        when {
            duration > 5000 -> Log.w(TAG, "🐌 SLOW: $operationName took ${duration}ms")
            duration > 2000 -> Log.i(TAG, "⚠️ MODERATE: $operationName took ${duration}ms")
            else -> Log.d(TAG, "⚡ FAST: $operationName took ${duration}ms")
        }
        
        // Clean up old data to prevent memory leaks
        cleanupOldData(operationName)
    }
    
    /**
     * Record a failed operation
     */
    internal fun recordError(operationName: String, duration: Long, error: Exception) {
        errorCounts.computeIfAbsent(operationName) { AtomicLong(0) }.incrementAndGet()
        Log.e(TAG, "❌ ERROR: $operationName failed after ${duration}ms", error)
    }
    
    /**
     * Get performance statistics for an operation
     */
    fun getStats(operationName: String): OperationStats? {
        val times = operationTimes[operationName] ?: return null
        val count = operationCounts[operationName]?.get() ?: 0
        val errors = errorCounts[operationName]?.get() ?: 0
        
        if (times.isEmpty()) return null
        
        val sortedTimes = times.sorted()
        val avg = times.average()
        val min = sortedTimes.first()
        val max = sortedTimes.last()
        val p50 = sortedTimes[sortedTimes.size / 2]
        val p95 = sortedTimes[(sortedTimes.size * 0.95).toInt()]
        
        return OperationStats(
            operationName = operationName,
            totalCount = count,
            errorCount = errors,
            averageTime = avg,
            minTime = min,
            maxTime = max,
            p50Time = p50,
            p95Time = p95
        )
    }
    
    /**
     * Log performance summary for all operations
     */
    fun logPerformanceSummary() {
        Log.i(TAG, "📊 === PERFORMANCE SUMMARY ===")
        
        operationTimes.keys.forEach { operationName ->
            val stats = getStats(operationName)
            if (stats != null) {
                Log.i(TAG, "📈 $operationName:")
                Log.i(TAG, "   Count: ${stats.totalCount}, Errors: ${stats.errorCount}")
                Log.i(TAG, "   Avg: ${stats.averageTime.toInt()}ms, P50: ${stats.p50Time}ms, P95: ${stats.p95Time}ms")
                Log.i(TAG, "   Min: ${stats.minTime}ms, Max: ${stats.maxTime}ms")
            }
        }
        
        Log.i(TAG, "📊 === END PERFORMANCE SUMMARY ===")
    }
    
    /**
     * Clean up old performance data to prevent memory leaks
     */
    internal fun cleanupOldData(operationName: String) {
        val times = operationTimes[operationName]
        if (times != null && times.size > 1000) {
            // Keep only the most recent 500 measurements
            val recentTimes = times.takeLast(500).toMutableList()
            operationTimes[operationName] = recentTimes
        }
    }
    
    /**
     * Clear all performance data
     */
    fun clearStats() {
        operationTimes.clear()
        operationCounts.clear()
        errorCounts.clear()
        Log.i(TAG, "🧹 Performance stats cleared")
    }
    
    /**
     * Start periodic performance reporting
     */
    fun startPeriodicReporting(intervalMinutes: Int = 5) {
        CoroutineScope(Dispatchers.IO).launch {
            while (true) {
                kotlinx.coroutines.delay(intervalMinutes * 60 * 1000L)
                logPerformanceSummary()
            }
        }
    }
}

/**
 * Performance statistics for an operation
 */
data class OperationStats(
    val operationName: String,
    val totalCount: Long,
    val errorCount: Long,
    val averageTime: Double,
    val minTime: Long,
    val maxTime: Long,
    val p50Time: Long,
    val p95Time: Long
)

/**
 * Common operation names for consistency
 */
object Operations {
    const val SMS_READ_ALL = "sms_read_all"
    const val SMS_READ_NEW = "sms_read_new"
    const val SMS_SYNC_TO_DB = "sms_sync_to_db"
    const val SMS_SYNC_TO_TELEGRAM = "sms_sync_to_telegram"
    const val TELEGRAM_SEND_MESSAGE = "telegram_send_message"
    const val DATABASE_INSERT = "database_insert"
    const val DATABASE_UPDATE = "database_update"
    const val DATABASE_QUERY = "database_query"
}
