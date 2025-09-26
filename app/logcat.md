--------- beginning of crash
2025-09-07 20:49:52.217 25427-25427 AndroidRuntime          pid-25427                            E  FATAL EXCEPTION: main (Ask Gemini)
Process: com.akslabs.SandeshVahak, PID: 25427
android.app.RemoteServiceException$ForegroundServiceDidNotStartInTimeException: Context.startForegroundService() did not then call Service.startForeground(): ServiceRecord{bfad56 u0 com.akslabs.SandeshVahak/com.akslabs.SandeshVahak.services.SmsObserverService c:com.akslabs.SandeshVahak}
at android.app.ActivityThread.generateForegroundServiceDidNotStartInTimeException(ActivityThread.java:2298)
at android.app.ActivityThread.throwRemoteServiceException(ActivityThread.java:2266)
at android.app.ActivityThread.-$$Nest$mthrowRemoteServiceException(Unknown Source:0)
at android.app.ActivityThread$H.handleMessage(ActivityThread.java:2640)
at android.os.Handler.dispatchMessage(Handler.java:109)
at android.os.Looper.loopOnce(Looper.java:232)
at android.os.Looper.loop(Looper.java:317)
at android.app.ActivityThread.main(ActivityThread.java:8782)
at java.lang.reflect.Method.invoke(Native Method)
at com.android.internal.os.RuntimeInit$MethodAndArgsCaller.run(RuntimeInit.java:595)
at com.android.internal.os.ZygoteInit.main(ZygoteInit.java:871)
Caused by: android.app.StackTrace: Last startServiceCommon() call for this service was made here
at android.app.ContextImpl.startServiceCommon(ContextImpl.java:2033)
at android.app.ContextImpl.startForegroundService(ContextImpl.java:1987)
at android.content.ContextWrapper.startForegroundService(ContextWrapper.java:853)
at androidx.core.content.ContextCompat$Api26Impl.startForegroundService(ContextCompat.java:1128)
at androidx.core.content.ContextCompat.startForegroundService(ContextCompat.java:700)
at com.akslabs.SandeshVahak.services.SmsObserverService$Companion.stop(SmsObserverService.kt:56)
at com.akslabs.SandeshVahak.ui.main.MainPageKt$MainPage$6$1$1$2$1.invoke(MainPage.kt:120)
at com.akslabs.SandeshVahak.ui.main.MainPageKt$MainPage$6$1$1$2$1.invoke(MainPage.kt:116)
at androidx.compose.foundation.ClickableNode$clickPointerInput$3.invoke-k-4lQ0M(Clickable.kt:639)
at androidx.compose.foundation.ClickableNode$clickPointerInput$3.invoke(Clickable.kt:633)
at androidx.compose.foundation.gestures.TapGestureDetectorKt$detectTapAndPress$2$1.invokeSuspend(TapGestureDetector.kt:255)
at kotlin.coroutines.jvm.internal.BaseContinuationImpl.resumeWith(ContinuationImpl.kt:33)
at kotlinx.coroutines.DispatchedTaskKt.resume(DispatchedTask.kt:175)
at kotlinx.coroutines.DispatchedTaskKt.dispatch(DispatchedTask.kt:164)
at kotlinx.coroutines.CancellableContinuationImpl.dispatchResume(CancellableContinuationImpl.kt:470)
at kotlinx.coroutines.CancellableContinuationImpl.resumeImpl(CancellableContinuationImpl.kt:504)
at kotlinx.coroutines.CancellableContinuationImpl.resumeImpl$default(CancellableContinuationImpl.kt:493)
at kotlinx.coroutines.CancellableContinuationImpl.resumeWith(CancellableContinuationImpl.kt:364)
at androidx.compose.ui.input.pointer.SuspendingPointerInputModifierNodeImpl$PointerEventHandlerCoroutine.offerPointerEvent(SuspendingPointerInputFilter.kt:719)
at androidx.compose.ui.input.pointer.SuspendingPointerInputModifierNodeImpl.dispatchPointerEvent(SuspendingPointerInputFilter.kt:598)
at androidx.compose.ui.input.pointer.SuspendingPointerInputModifierNodeImpl.onPointerEvent-H0pRuoY(SuspendingPointerInputFilter.kt:620)
at androidx.compose.foundation.AbstractClickableNode.onPointerEvent-H0pRuoY(Clickable.kt:1044)
at androidx.compose.ui.input.pointer.Node.dispatchMainEventPass(HitPathTracker.kt:387)
at androidx.compose.ui.input.pointer.Node.dispatchMainEventPass(HitPathTracker.kt:373)
at androidx.compose.ui.input.pointer.Node.dispatchMainEventPass(HitPathTracker.kt:373)
at androidx.compose.ui.input.pointer.Node.dispatchMainEventPass(HitPathTracker.kt:373)
at androidx.compose.ui.input.pointer.NodeParent.dispatchMainEventPass(HitPathTracker.kt:229)
at androidx.compose.ui.input.pointer.HitPathTracker.dispatchChanges(HitPathTracker.kt:144)
2025-09-07 20:50:56.335 25429-25429 AndroidRuntime          android.process.acore                E  FATAL EXCEPTION: main (Ask Gemini)
Process: com.akslabs.SandeshVahak, PID: 25429
android.app.RemoteServiceException$ForegroundServiceDidNotStartInTimeException: Context.startForegroundService() did not then call Service.startForeground(): ServiceRecord{89faa26 u0 com.akslabs.SandeshVahak/com.akslabs.SandeshVahak.services.SmsObserverService c:com.akslabs.SandeshVahak}
at android.app.ActivityThread.generateForegroundServiceDidNotStartInTimeException(ActivityThread.java:2298)
at android.app.ActivityThread.throwRemoteServiceException(ActivityThread.java:2266)
at android.app.ActivityThread.-$$Nest$mthrowRemoteServiceException(Unknown Source:0)
at android.app.ActivityThread$H.handleMessage(ActivityThread.java:2640)
at android.os.Handler.dispatchMessage(Handler.java:109)
at android.os.Looper.loopOnce(Looper.java:232)
at android.os.Looper.loop(Looper.java:317)
at android.app.ActivityThread.main(ActivityThread.java:8782)
at java.lang.reflect.Method.invoke(Native Method)
at com.android.internal.os.RuntimeInit$MethodAndArgsCaller.run(RuntimeInit.java:595)
at com.android.internal.os.ZygoteInit.main(ZygoteInit.java:871)
Caused by: android.app.StackTrace: Last startServiceCommon() call for this service was made here
at android.app.ContextImpl.startServiceCommon(ContextImpl.java:2033)
at android.app.ContextImpl.startForegroundService(ContextImpl.java:1987)
at android.content.ContextWrapper.startForegroundService(ContextWrapper.java:853)
at androidx.core.content.ContextCompat$Api26Impl.startForegroundService(ContextCompat.java:1128)
at androidx.core.content.ContextCompat.startForegroundService(ContextCompat.java:700)
at com.akslabs.SandeshVahak.services.SmsObserverService$Companion.stop(SmsObserverService.kt:56)
at com.akslabs.SandeshVahak.ui.main.MainPageKt$MainPage$6$1$1$2$1.invoke(MainPage.kt:120)
at com.akslabs.SandeshVahak.ui.main.MainPageKt$MainPage$6$1$1$2$1.invoke(MainPage.kt:116)
at androidx.compose.foundation.ClickableNode$clickPointerInput$3.invoke-k-4lQ0M(Clickable.kt:639)
at androidx.compose.foundation.ClickableNode$clickPointerInput$3.invoke(Clickable.kt:633)
at androidx.compose.foundation.gestures.TapGestureDetectorKt$detectTapAndPress$2$1.invokeSuspend(TapGestureDetector.kt:255)
at kotlin.coroutines.jvm.internal.BaseContinuationImpl.resumeWith(ContinuationImpl.kt:33)
at kotlinx.coroutines.DispatchedTaskKt.resume(DispatchedTask.kt:175)
at kotlinx.coroutines.DispatchedTaskKt.dispatch(DispatchedTask.kt:164)
at kotlinx.coroutines.CancellableContinuationImpl.dispatchResume(CancellableContinuationImpl.kt:470)
at kotlinx.coroutines.CancellableContinuationImpl.resumeImpl(CancellableContinuationImpl.kt:504)
at kotlinx.coroutines.CancellableContinuationImpl.resumeImpl$default(CancellableContinuationImpl.kt:493)
at kotlinx.coroutines.CancellableContinuationImpl.resumeWith(CancellableContinuationImpl.kt:364)
at androidx.compose.ui.input.pointer.SuspendingPointerInputModifierNodeImpl$PointerEventHandlerCoroutine.offerPointerEvent(SuspendingPointerInputFilter.kt:719)
at androidx.compose.ui.input.pointer.SuspendingPointerInputModifierNodeImpl.dispatchPointerEvent(SuspendingPointerInputFilter.kt:598)
at androidx.compose.ui.input.pointer.SuspendingPointerInputModifierNodeImpl.onPointerEvent-H0pRuoY(SuspendingPointerInputFilter.kt:620)
at androidx.compose.foundation.AbstractClickableNode.onPointerEvent-H0pRuoY(Clickable.kt:1044)
at androidx.compose.ui.input.pointer.Node.dispatchMainEventPass(HitPathTracker.kt:387)
at androidx.compose.ui.input.pointer.Node.dispatchMainEventPass(HitPathTracker.kt:373)
at androidx.compose.ui.input.pointer.Node.dispatchMainEventPass(HitPathTracker.kt:373)
at androidx.compose.ui.input.pointer.Node.dispatchMainEventPass(HitPathTracker.kt:373)
at androidx.compose.ui.input.pointer.NodeParent.dispatchMainEventPass(HitPathTracker.kt:229)
at androidx.compose.ui.input.pointer.HitPathTracker.dispatchChanges(HitPathTracker.kt:144)
--------- beginning of system
2025-09-07 22:16:50.389  1813-1965  VerityUtils             system_server                        E  Failed to check whether fs-verity is enabled, errno 38: /data/app/~~eX9q9n9wrn2f_Hner-7KcA==/com.akslabs.SandeshVahak-0qlo1gmt-vIYJrnkgSbU4g==/base.apk
--------- beginning of main
2025-09-07 22:29:26.025 10283-24277 bs.SandeshVahak         com.akslabs.SandeshVahak             I  Background concurrent copying GC freed 8253KB AllocSpace bytes, 4(80KB) LOS objects, 49% free, 6657KB/13MB, paused 76us,49us total 114.471ms
2025-09-07 22:31:52.092 10283-24405 PerformanceMonitor      com.akslabs.SandeshVahak             I  📊 === PERFORMANCE SUMMARY ===
2025-09-07 22:31:52.093 10283-24405 PerformanceMonitor      com.akslabs.SandeshVahak             I  📈 sms_read_all:
2025-09-07 22:31:52.094 10283-24405 PerformanceMonitor      com.akslabs.SandeshVahak             I     Count: 2, Errors: 0
2025-09-07 22:31:52.094 10283-24405 PerformanceMonitor      com.akslabs.SandeshVahak             I     Avg: 182ms, P50: 251ms, P95: 251ms
2025-09-07 22:31:52.094 10283-24405 PerformanceMonitor      com.akslabs.SandeshVahak             I     Min: 113ms, Max: 251ms
2025-09-07 22:31:52.094 10283-24405 PerformanceMonitor      com.akslabs.SandeshVahak             I  📈 telegram_send_message:
2025-09-07 22:31:52.094 10283-24405 PerformanceMonitor      com.akslabs.SandeshVahak             I     Count: 3, Errors: 0
2025-09-07 22:31:52.094 10283-24405 PerformanceMonitor      com.akslabs.SandeshVahak             I     Avg: 820ms, P50: 847ms, P95: 880ms
2025-09-07 22:31:52.094 10283-24405 PerformanceMonitor      com.akslabs.SandeshVahak             I     Min: 734ms, Max: 880ms
2025-09-07 22:31:52.094 10283-24405 PerformanceMonitor      com.akslabs.SandeshVahak             I  📈 sms_sync_to_db:
2025-09-07 22:31:52.094 10283-24405 PerformanceMonitor      com.akslabs.SandeshVahak             I     Count: 2, Errors: 0
2025-09-07 22:31:52.094 10283-24405 PerformanceMonitor      com.akslabs.SandeshVahak             I     Avg: 958ms, P50: 1035ms, P95: 1035ms
2025-09-07 22:31:52.094 10283-24405 PerformanceMonitor      com.akslabs.SandeshVahak             I     Min: 882ms, Max: 1035ms
2025-09-07 22:31:52.094 10283-24405 PerformanceMonitor      com.akslabs.SandeshVahak             I  📊 === END PERFORMANCE SUMMARY ===
2025-09-07 22:31:53.074 10283-24405 KeepAliveWorker         com.akslabs.SandeshVahak             D  KeepAlive: ensuring SmsObserverService is running
2025-09-07 22:31:53.079 10283-24405 KeepAliveWorker         com.akslabs.SandeshVahak             D  KeepAlive: SmsObserverService started successfully
2025-09-07 22:31:53.081 10283-10283 SmsObserverService      com.akslabs.SandeshVahak             I  onStartCommand: Received action: null, startId: 2
2025-09-07 22:31:53.082 10283-10283 SmsObserverService      com.akslabs.SandeshVahak             W  onStartCommand: Received unknown or null action. Starting service and checking prefs.
2025-09-07 22:31:53.082 10283-24298 WM-WorkerWrapper        com.akslabs.SandeshVahak             I  Worker result SUCCESS for Work [ id=0030ff36-259a-4250-a7ba-e21d8434f7a5, tags={ com.akslabs.SandeshVahak.workers.KeepAliveWorker } ]
2025-09-07 22:31:53.083 10283-10283 SmsObserverService      com.akslabs.SandeshVahak             D  Creating notification with text: Initializing SMS Sync...
2025-09-07 22:31:53.090 10283-10283 SmsObserverService      com.akslabs.SandeshVahak             I  updateSyncStateBasedOnPreferences: isEnabled=true, mode=NEW_ONLY, since=1757263655064, manuallyStopped=false, isInitialCall=false
2025-09-07 22:31:53.091 10283-10283 SmsObserverService      com.akslabs.SandeshVahak             I  updateSyncStateBasedOnPreferences: Sync NEW_ONLY mode detected. Starting catch-up and monitoring.
2025-09-07 22:31:53.092 10283-10283 SmsObserverService      com.akslabs.SandeshVahak             D  Creating notification with text: Monitoring new SMS messages...
2025-09-07 22:31:53.098 10283-24405 SmsObserverService      com.akslabs.SandeshVahak             I  performCatchUpSyncAndMonitorNewSms: Starting with syncEnabledSince=1757263655064.
2025-09-07 22:31:53.099 10283-24405 SmsObserverService      com.akslabs.SandeshVahak             D  Creating notification with text: Monitoring new SMS messages...
2025-09-07 22:31:53.104 10283-24405 SmsObserverService      com.akslabs.SandeshVahak             D  Notification updated with text: Monitoring new SMS messages...
2025-09-07 22:31:53.104 10283-24405 SmsObserverService      com.akslabs.SandeshVahak             D  Performing initial catch-up for NEW_ONLY mode.
2025-09-07 22:31:53.105 10283-24405 SmsSyncService          com.akslabs.SandeshVahak             D  Performing quick SMS sync
2025-09-07 22:31:53.105 10283-24405 SmsSyncService          com.akslabs.SandeshVahak             D  Preference isSmsSyncEnabledKey = true
2025-09-07 22:31:53.108 10283-24405 SmsSyncService          com.akslabs.SandeshVahak             D  Configured channelId = -1002651869724
2025-09-07 22:31:53.120 10283-24405 SmsReaderService        com.akslabs.SandeshVahak             D  NEW_ONLY mode: using baseline timestamp for incremental sync
2025-09-07 22:31:53.120 10283-24405 SmsReaderService        com.akslabs.SandeshVahak             I  Starting incremental SMS sync (mode: NEW_ONLY) from timestamp: 1757263650064 (lastDB=1757263767342, baseline=1757263655064, buffer=5000)
2025-09-07 22:31:53.123 10283-24405 SmsReaderService        com.akslabs.SandeshVahak             D  Timestamp details: lastDB=2025-09-07 22:19:27.342, baseline=2025-09-07 22:17:35.064, effective=2025-09-07 22:17:30.064
2025-09-07 22:31:53.228 10283-24405 SmsReaderService        com.akslabs.SandeshVahak             I  Reading SMS messages after timestamp: 1757263650064 (2025-09-07 22:17:30.064)
2025-09-07 22:31:53.228 10283-24405 SmsReaderService        com.akslabs.SandeshVahak             D  Query: uri=content://sms, selection='date > ?', args=[1757263650064], sort=date ASC
2025-09-07 22:31:53.327 10283-24405 SmsReaderService        com.akslabs.SandeshVahak             D  Cursor is null? false
2025-09-07 22:31:53.329 10283-24405 SmsReaderService        com.akslabs.SandeshVahak             V  Row #1 -> id=739, date=1757263678038, addr=+919545154067
2025-09-07 22:31:53.330 10283-24405 SmsReaderService        com.akslabs.SandeshVahak             V  Row #2 -> id=740, date=1757263728874, addr=+919545154067
2025-09-07 22:31:53.330 10283-24405 SmsReaderService        com.akslabs.SandeshVahak             V  Row #3 -> id=741, date=1757263767342, addr=+919545154067
2025-09-07 22:31:53.330 10283-24405 SmsReaderService        com.akslabs.SandeshVahak             V  Row #4 -> id=742, date=1757263798564, addr=+919545154067
2025-09-07 22:31:53.330 10283-24405 SmsReaderService        com.akslabs.SandeshVahak             D  Total rows iterated: 4
2025-09-07 22:31:53.343 10283-24405 SmsReaderService        com.akslabs.SandeshVahak             I  Read 4 new SMS messages after timestamp
2025-09-07 22:31:53.350 10283-24405 SmsReaderService        com.akslabs.SandeshVahak             V  Evaluating message id=739, date=1757263678038 (2025-09-07 22:17:58.038), exists=true
2025-09-07 22:31:53.350 10283-24405 SmsReaderService        com.akslabs.SandeshVahak             D  Message 739 already exists in DB, skipping
2025-09-07 22:31:53.354 10283-24405 SmsReaderService        com.akslabs.SandeshVahak             V  Evaluating message id=740, date=1757263728874 (2025-09-07 22:18:48.874), exists=true
2025-09-07 22:31:53.354 10283-24405 SmsReaderService        com.akslabs.SandeshVahak             D  Message 740 already exists in DB, skipping
2025-09-07 22:31:53.358 10283-24405 SmsReaderService        com.akslabs.SandeshVahak             V  Evaluating message id=741, date=1757263767342 (2025-09-07 22:19:27.342), exists=true
2025-09-07 22:31:53.358 10283-24405 SmsReaderService        com.akslabs.SandeshVahak             D  Message 741 already exists in DB, skipping
2025-09-07 22:31:53.362 10283-24405 SmsReaderService        com.akslabs.SandeshVahak             V  Evaluating message id=742, date=1757263798564 (2025-09-07 22:19:58.564), exists=false
2025-09-07 22:31:53.362 10283-24405 SmsReaderService        com.akslabs.SandeshVahak             I  ✅ New message queued for insert: 742 from +919545154067 at 1757263798564
2025-09-07 22:31:53.371 10283-24405 SmsReaderService        com.akslabs.SandeshVahak             I  Incremental SMS sync complete: 1 new messages (batch)
2025-09-07 22:31:53.372 10283-24405 SmsSyncService          com.akslabs.SandeshVahak             D  syncNewSmsToDatabase returned 1 new messages
2025-09-07 22:31:53.372 10283-24405 SmsSyncService          com.akslabs.SandeshVahak             D  Proceeding to unsynced fetch; newLocalMessages=1
2025-09-07 22:31:53.372 10283-24405 SmsSyncService          com.akslabs.SandeshVahak             D  Sync mode: NEW_ONLY, baseline: 1757263655064
2025-09-07 22:31:53.373 10283-24405 SmsSyncService          com.akslabs.SandeshVahak             I  ✅ NEW_ONLY mode properly configured - baseline: 1757263655064 (2025-09-07 22:17:35)
2025-09-07 22:31:53.373 10283-24405 SmsSyncService          com.akslabs.SandeshVahak             D  NEW_ONLY mode: getting unsynced messages after baseline 1757263655064
2025-09-07 22:31:53.380 10283-24405 SmsSyncService          com.akslabs.SandeshVahak             D  NEW_ONLY quick sync query returned 1 messages
2025-09-07 22:31:53.380 10283-24405 SmsSyncService          com.akslabs.SandeshVahak             D  Retrieved 1 unsynced messages (mode: NEW_ONLY, baseline: 1757263655064)
2025-09-07 22:31:53.386 10283-24405 SmsSyncService          com.akslabs.SandeshVahak             D  📤 Sending SMS message 1/1: 742 (1757263798564)
2025-09-07 22:31:53.387 10283-24405 BotApi                  com.akslabs.SandeshVahak             D  📤 Sending SMS message to channel: -1002651869724
2025-09-07 22:31:56.008 10283-24405 TelegramRateLimiter     com.akslabs.SandeshVahak             D  ✅ Request successful, rate limiter reset
2025-09-07 22:31:56.009 10283-24405 PerformanceMonitor      com.akslabs.SandeshVahak             I  ⚠️ MODERATE: telegram_send_message took 2622ms
2025-09-07 22:31:56.010 10283-24405 SmsSyncService          com.akslabs.SandeshVahak             I  ✅ Successfully synced SMS message: 742 (1/20 in current burst, total: 1)
2025-09-07 22:31:56.018 10283-24405 SmsSyncService          com.akslabs.SandeshVahak             D  📦 Applied DB updates in batch: synced=1, remoteInserted=1, failed=0
2025-09-07 22:31:56.018 10283-24405 SmsSyncService          com.akslabs.SandeshVahak             D  Quick sync complete: 1 messages synced
2025-09-07 22:31:56.019 10283-24405 SmsObserverService      com.akslabs.SandeshVahak             I  Initial catch-up sync: 1 messages.
2025-09-07 22:31:56.019 10283-24405 SmsObserverService      com.akslabs.SandeshVahak             I  performCatchUpSyncAndMonitorNewSms: Initial catch-up complete. Starting ContentObserver.
2025-09-07 22:31:56.020 10283-24405 SmsObserverService      com.akslabs.SandeshVahak             D  Creating notification with text: Monitoring new SMS messages.
2025-09-07 22:31:56.026 10283-24405 SmsObserverService      com.akslabs.SandeshVahak             D  Notification updated with text: Monitoring new SMS messages.
2025-09-07 22:31:56.026 10283-24405 SmsObserverService      com.akslabs.SandeshVahak             D  SmsContentObserver already registered.
2025-09-07 22:32:59.334 10283-24277 bs.SandeshVahak         com.akslabs.SandeshVahak             I  Background concurrent copying GC freed 5922KB AllocSpace bytes, 20(592KB) LOS objects, 49% free, 6688KB/13MB, paused 75us,50us total 102.120ms
2025-09-07 22:33:24.975 21017-21017 bs.SandeshVahak         com.akslabs.SandeshVahak             I  Late-enabling -Xcheck:jni
2025-09-07 22:33:25.046 21017-21017 bs.SandeshVahak         com.akslabs.SandeshVahak             I  Using CollectorTypeCC GC.
2025-09-07 22:33:25.094 21017-21017 nativeloader            com.akslabs.SandeshVahak             D  Load libframework-connectivity-tiramisu-jni.so using APEX ns com_android_tethering for caller /apex/com.android.tethering/javalib/framework-connectivity-t.jar: ok
2025-09-07 22:33:25.213 21017-21017 re-initialized>         com.akslabs.SandeshVahak             W  type=1400 audit(0.0:14246): avc:  granted  { execute } for  path="/data/data/com.akslabs.SandeshVahak/code_cache/startup_agents/297fa85a-agent.so" dev="mmcblk0p61" ino=358676 scontext=u:r:untrusted_app:s0:c34,c259,c512,c768 tcontext=u:object_r:app_data_file:s0:c34,c259,c512,c768 tclass=file app=com.akslabs.SandeshVahak
2025-09-07 22:33:25.218 21017-21017 nativeloader            com.akslabs.SandeshVahak             D  Load /data/user/0/com.akslabs.SandeshVahak/code_cache/startup_agents/297fa85a-agent.so using system ns (caller=<unknown>): ok
2025-09-07 22:33:25.232 21017-21017 bs.SandeshVahak         com.akslabs.SandeshVahak             W  DexFile /data/data/com.akslabs.SandeshVahak/code_cache/.studio/instruments-9bcd9200.jar is in boot class path but is not in a known location
2025-09-07 22:33:25.485 21017-21017 bs.SandeshVahak         com.akslabs.SandeshVahak             W  Redefining intrinsic method java.lang.Thread java.lang.Thread.currentThread(). This may cause the unexpected use of the original definition of java.lang.Thread java.lang.Thread.currentThread()in methods that have already been compiled.
2025-09-07 22:33:25.485 21017-21017 bs.SandeshVahak         com.akslabs.SandeshVahak             W  Redefining intrinsic method boolean java.lang.Thread.interrupted(). This may cause the unexpected use of the original definition of boolean java.lang.Thread.interrupted()in methods that have already been compiled.
2025-09-07 22:33:25.595 21017-21017 ApplicationLoaders      com.akslabs.SandeshVahak             D  Returning zygote-cached class loader: /system_ext/framework/androidx.window.extensions.jar
2025-09-07 22:33:25.595 21017-21017 ApplicationLoaders      com.akslabs.SandeshVahak             D  Returning zygote-cached class loader: /system_ext/framework/androidx.window.sidecar.jar
2025-09-07 22:33:25.607 21017-21017 ziparchive              com.akslabs.SandeshVahak             W  Unable to open '/data/data/com.akslabs.SandeshVahak/code_cache/.overlay/base.apk/classes12.dm': No such file or directory
2025-09-07 22:33:25.611 21017-21017 ziparchive              com.akslabs.SandeshVahak             W  Unable to open '/data/data/com.akslabs.SandeshVahak/code_cache/.overlay/base.apk/classes13.dm': No such file or directory
2025-09-07 22:33:25.615 21017-21017 ziparchive              com.akslabs.SandeshVahak             W  Unable to open '/data/data/com.akslabs.SandeshVahak/code_cache/.overlay/base.apk/classes15.dm': No such file or directory
2025-09-07 22:33:25.618 21017-21017 ziparchive              com.akslabs.SandeshVahak             W  Unable to open '/data/data/com.akslabs.SandeshVahak/code_cache/.overlay/base.apk/classes8.dm': No such file or directory
2025-09-07 22:33:25.622 21017-21017 ziparchive              com.akslabs.SandeshVahak             W  Unable to open '/data/app/~~eX9q9n9wrn2f_Hner-7KcA==/com.akslabs.SandeshVahak-0qlo1gmt-vIYJrnkgSbU4g==/base.dm': No such file or directory
2025-09-07 22:33:25.622 21017-21017 ziparchive              com.akslabs.SandeshVahak             W  Unable to open '/data/app/~~eX9q9n9wrn2f_Hner-7KcA==/com.akslabs.SandeshVahak-0qlo1gmt-vIYJrnkgSbU4g==/base.dm': No such file or directory
2025-09-07 22:33:26.768 21017-21017 nativeloader            com.akslabs.SandeshVahak             D  Configuring clns-7 for other apk /data/app/~~eX9q9n9wrn2f_Hner-7KcA==/com.akslabs.SandeshVahak-0qlo1gmt-vIYJrnkgSbU4g==/base.apk. target_sdk_version=35, uses_libraries=, library_path=/data/app/~~eX9q9n9wrn2f_Hner-7KcA==/com.akslabs.SandeshVahak-0qlo1gmt-vIYJrnkgSbU4g==/lib/arm64:/data/app/~~eX9q9n9wrn2f_Hner-7KcA==/com.akslabs.SandeshVahak-0qlo1gmt-vIYJrnkgSbU4g==/base.apk!/lib/arm64-v8a, permitted_path=/data:/mnt/expand:/data/user/0/com.akslabs.SandeshVahak
2025-09-07 22:33:26.850 21017-21017 GraphicsEnvironment     com.akslabs.SandeshVahak             V  Currently set values for:
2025-09-07 22:33:26.850 21017-21017 GraphicsEnvironment     com.akslabs.SandeshVahak             V    angle_gl_driver_selection_pkgs=[com.android.angle, com.linecorp.b612.android, com.campmobile.snow, com.google.android.apps.tachyon]
2025-09-07 22:33:26.850 21017-21017 GraphicsEnvironment     com.akslabs.SandeshVahak             V    angle_gl_driver_selection_values=[angle, native, native, native]
2025-09-07 22:33:26.850 21017-21017 GraphicsEnvironment     com.akslabs.SandeshVahak             V  com.akslabs.SandeshVahak is not listed in per-application setting
2025-09-07 22:33:26.850 21017-21017 GraphicsEnvironment     com.akslabs.SandeshVahak             V  Neither updatable production driver nor prerelease driver is supported.
2025-09-07 22:33:26.897 21017-21017 WM-WrkMgrInitializer    com.akslabs.SandeshVahak             D  Initializing WorkManager with default configuration.
2025-09-07 22:33:26.989 21017-21017 WM-PackageManagerHelper com.akslabs.SandeshVahak             D  Skipping component enablement for androidx.work.impl.background.systemjob.SystemJobService
2025-09-07 22:33:26.990 21017-21017 WM-Schedulers           com.akslabs.SandeshVahak             D  Created SystemJobScheduler and enabled SystemJobService
2025-09-07 22:33:27.031 21017-21017 SandeshVahakApp         com.akslabs.SandeshVahak             I  Application onCreate called
2025-09-07 22:33:27.345 21017-21017 EngineFactory           com.akslabs.SandeshVahak             I  Provider GmsCore_OpenSSL not available
2025-09-07 22:33:27.358 21017-21017 SandeshVahakApp         com.akslabs.SandeshVahak             D  Preferences initialized successfully
2025-09-07 22:33:27.372 21017-21017 SandeshVahakApp         com.akslabs.SandeshVahak             D  Database initialized successfully
2025-09-07 22:33:27.372 21017-21017 SandeshVahakApp         com.akslabs.SandeshVahak             D  WorkManager initialized successfully
2025-09-07 22:33:27.409 21017-21017 SandeshVahakApp         com.akslabs.SandeshVahak             D  Keep-alive worker scheduled
2025-09-07 22:33:27.409 21017-21017 SandeshVahakApp         com.akslabs.SandeshVahak             D  ConnectivityObserver initialized successfully
2025-09-07 22:33:27.558 21017-21017 SandeshVahakApp         com.akslabs.SandeshVahak             D  BotApi initialized successfully
2025-09-07 22:33:27.565 21017-21017 SandeshVahakApp         com.akslabs.SandeshVahak             D  Performance monitoring started
2025-09-07 22:33:27.565 21017-21017 SandeshVahakApp         com.akslabs.SandeshVahak             I  Application initialization completed
2025-09-07 22:33:27.588 21017-21017 Choreographer           com.akslabs.SandeshVahak             I  Skipped 33 frames!  The application may be doing too much work on its main thread.
2025-09-07 22:33:27.720 21017-25782 DatabaseDebugHelper     com.akslabs.SandeshVahak             I  === DATABASE DEBUG REPORT ===
2025-09-07 22:33:27.753 21017-25782 DatabaseDebugHelper     com.akslabs.SandeshVahak             I  Database version: 7
2025-09-07 22:33:27.765 21017-21017 DesktopModeFlagsUtil    com.akslabs.SandeshVahak             D  Toggle override initialized to: OVERRIDE_UNSET
2025-09-07 22:33:27.843 21017-25782 DatabaseDebugHelper     com.akslabs.SandeshVahak             I  Record counts:
2025-09-07 22:33:27.844 21017-25782 DatabaseDebugHelper     com.akslabs.SandeshVahak             I    Total SMS messages: 504
2025-09-07 22:33:27.844 21017-25782 DatabaseDebugHelper     com.akslabs.SandeshVahak             I    Synced SMS messages: 4
2025-09-07 22:33:27.844 21017-25782 DatabaseDebugHelper     com.akslabs.SandeshVahak             I    Total remote SMS messages: 4
2025-09-07 22:33:27.844 21017-25782 DatabaseDebugHelper     com.akslabs.SandeshVahak             I  Sample synced SMS messages:
2025-09-07 22:33:27.846 21017-25782 DatabaseDebugHelper     com.akslabs.SandeshVahak             I    SMS: id=742, remoteId=3080, address=+919545154067, syncedAt=1757264516014
2025-09-07 22:33:27.847 21017-25782 DatabaseDebugHelper     com.akslabs.SandeshVahak             I    SMS: id=741, remoteId=3079, address=+919545154067, syncedAt=1757263773228
2025-09-07 22:33:27.847 21017-25782 DatabaseDebugHelper     com.akslabs.SandeshVahak             I    SMS: id=740, remoteId=3078, address=+919545154067, syncedAt=1757263773227
2025-09-07 22:33:27.847 21017-25782 DatabaseDebugHelper     com.akslabs.SandeshVahak             I  Sample remote SMS messages:
2025-09-07 22:33:27.847 21017-25782 DatabaseDebugHelper     com.akslabs.SandeshVahak             I    RemoteSMS: remoteId=3080, originalId=742, address=+919545154067
2025-09-07 22:33:27.848 21017-25782 DatabaseDebugHelper     com.akslabs.SandeshVahak             I    RemoteSMS: remoteId=3079, originalId=741, address=+919545154067
2025-09-07 22:33:27.848 21017-25782 DatabaseDebugHelper     com.akslabs.SandeshVahak             I    RemoteSMS: remoteId=3078, originalId=740, address=+919545154067
2025-09-07 22:33:27.848 21017-25782 DatabaseDebugHelper     com.akslabs.SandeshVahak             I  === END DATABASE DEBUG REPORT ===
2025-09-07 22:33:28.003 21017-21017 SmsContentObserver      com.akslabs.SandeshVahak             D  Registering content observer on URI=content://sms, notifyForDescendants=true, thread=main
2025-09-07 22:33:28.007 21017-21017 SmsContentObserver      com.akslabs.SandeshVahak             I  ✅ SMS content observer started and registered
2025-09-07 22:33:28.245 21017-25762 bs.SandeshVahak         com.akslabs.SandeshVahak             I  Compiler allocated 4431KB to compile void android.view.ViewRootImpl.performTraversals()
2025-09-07 22:33:28.546 21017-21017 bs.SandeshVahak         com.akslabs.SandeshVahak             W  Method boolean androidx.compose.runtime.snapshots.SnapshotStateList.conditionalUpdate(boolean, kotlin.jvm.functions.Function1) failed lock verification and will run slower.
Common causes for lock verification issues are non-optimized dex code
and incorrect proguard optimizations.
2025-09-07 22:33:28.547 21017-21017 bs.SandeshVahak         com.akslabs.SandeshVahak             W  Method boolean androidx.compose.runtime.snapshots.SnapshotStateList.conditionalUpdate$default(androidx.compose.runtime.snapshots.SnapshotStateList, boolean, kotlin.jvm.functions.Function1, int, java.lang.Object) failed lock verification and will run slower.
2025-09-07 22:33:28.547 21017-21017 bs.SandeshVahak         com.akslabs.SandeshVahak             W  Method java.lang.Object androidx.compose.runtime.snapshots.SnapshotStateList.mutate(kotlin.jvm.functions.Function1) failed lock verification and will run slower.
2025-09-07 22:33:28.547 21017-21017 bs.SandeshVahak         com.akslabs.SandeshVahak             W  Method void androidx.compose.runtime.snapshots.SnapshotStateList.update(boolean, kotlin.jvm.functions.Function1) failed lock verification and will run slower.
2025-09-07 22:33:28.547 21017-21017 bs.SandeshVahak         com.akslabs.SandeshVahak             W  Method void androidx.compose.runtime.snapshots.SnapshotStateList.update$default(androidx.compose.runtime.snapshots.SnapshotStateList, boolean, kotlin.jvm.functions.Function1, int, java.lang.Object) failed lock verification and will run slower.
2025-09-07 22:33:29.184 21017-21017 AppNavHost              com.akslabs.SandeshVahak             D  Local count recomposed: 0
2025-09-07 22:33:29.184 21017-21017 LocalSmsScreen          com.akslabs.SandeshVahak             D  Recompose LocalSmsScreen: items=0, loadState=CombinedLoadStates(refresh=Loading(endOfPaginationReached=false), prepend=NotLoading(endOfPaginationReached=false), append=NotLoading(endOfPaginationReached=false), source=LoadStates(refresh=Loading(endOfPaginationReached=false), prepend=NotLoading(endOfPaginationReached=false), append=NotLoading(endOfPaginationReached=false)), mediator=null)
2025-09-07 22:33:29.227 21017-25786 AdrenoGLES-0            com.akslabs.SandeshVahak             I  QUALCOMM build                   : 95db91f, Ifbc588260a
Build Date                       : 09/24/20
OpenGL ES Shader Compiler Version: EV031.32.02.01
Local Branch                     : mybrancheafe5b6d-fb5b-f1b0-b904-5cb90179c3e0
Remote Branch                    : quic/gfx-adreno.lnx.1.0.r114-rel
Remote Branch                    : NONE
Reconstruct Branch               : NOTHING
2025-09-07 22:33:29.227 21017-25786 AdrenoGLES-0            com.akslabs.SandeshVahak             I  Build Config                     : S P 10.0.7 AArch64
2025-09-07 22:33:29.227 21017-25786 AdrenoGLES-0            com.akslabs.SandeshVahak             I  Driver Path                      : /vendor/lib64/egl/libGLESv2_adreno.so
2025-09-07 22:33:29.290 21017-25786 AdrenoGLES-0            com.akslabs.SandeshVahak             I  PFP: 0x016ee190, ME: 0x00000000
2025-09-07 22:33:29.349 21017-25811 Gralloc4                com.akslabs.SandeshVahak             I  mapper 4.x is not supported
2025-09-07 22:33:29.350 21017-25811 Gralloc3                com.akslabs.SandeshVahak             W  mapper 3.x is not supported
2025-09-07 22:33:29.358 21017-25811 Gralloc2                com.akslabs.SandeshVahak             I  Adding additional valid usage bits: 0x8202000
2025-09-07 22:33:29.416 21017-25782 SmsReaderService        com.akslabs.SandeshVahak             D  NEW_ONLY mode: using baseline timestamp for incremental sync
2025-09-07 22:33:29.417 21017-25782 SmsReaderService        com.akslabs.SandeshVahak             I  Starting incremental SMS sync (mode: NEW_ONLY) from timestamp: 1757263650064 (lastDB=1757263798564, baseline=1757263655064, buffer=5000)
2025-09-07 22:33:29.429 21017-25767 HWUI                    com.akslabs.SandeshVahak             I  Davey! duration=1292ms; Flags=1, FrameTimelineVsyncId=44668407, IntendedVsync=78996392875543, Vsync=78996392875543, InputEventId=0, HandleInputStart=78996403841947, AnimationStart=78996403844343, PerformTraversalsStart=78996403845281, DrawStart=78997623411322, FrameDeadline=78996414208875, FrameInterval=78996403832781, FrameStartTime=16664591, SyncQueued=78997647938040, SyncStart=78997648105801, IssueDrawCommandsStart=78997649411426, SwapBuffers=78997683162572, FrameCompleted=78997685492988, DequeueBufferDuration=685781, QueueBufferDuration=586823, GpuCompleted=78997685492988, SwapBuffersCompleted=78997685252363, DisplayPresentTime=0, CommandSubmissionCompleted=78997683162572,
2025-09-07 22:33:29.461 21017-25782 SmsReaderService        com.akslabs.SandeshVahak             D  Timestamp details: lastDB=2025-09-07 22:19:58.564, baseline=2025-09-07 22:17:35.064, effective=2025-09-07 22:17:30.064
2025-09-07 22:33:29.567 21017-25782 SmsReaderService        com.akslabs.SandeshVahak             I  Reading SMS messages after timestamp: 1757263650064 (2025-09-07 22:17:30.064)
2025-09-07 22:33:29.568 21017-25782 SmsReaderService        com.akslabs.SandeshVahak             D  Query: uri=content://sms, selection='date > ?', args=[1757263650064], sort=date ASC
2025-09-07 22:33:29.573 21017-21017 Choreographer           com.akslabs.SandeshVahak             I  Skipped 83 frames!  The application may be doing too much work on its main thread.
2025-09-07 22:33:29.662 21017-25782 SmsReaderService        com.akslabs.SandeshVahak             D  Cursor is null? false
2025-09-07 22:33:29.662 21017-25782 SmsReaderService        com.akslabs.SandeshVahak             V  Row #1 -> id=739, date=1757263678038, addr=+919545154067
2025-09-07 22:33:29.663 21017-25782 SmsReaderService        com.akslabs.SandeshVahak             V  Row #2 -> id=740, date=1757263728874, addr=+919545154067
2025-09-07 22:33:29.663 21017-25782 SmsReaderService        com.akslabs.SandeshVahak             V  Row #3 -> id=741, date=1757263767342, addr=+919545154067
2025-09-07 22:33:29.664 21017-25782 SmsReaderService        com.akslabs.SandeshVahak             V  Row #4 -> id=742, date=1757263798564, addr=+919545154067
2025-09-07 22:33:29.664 21017-25782 SmsReaderService        com.akslabs.SandeshVahak             D  Total rows iterated: 4
2025-09-07 22:33:29.678 21017-25782 SmsReaderService        com.akslabs.SandeshVahak             I  Read 4 new SMS messages after timestamp
2025-09-07 22:33:29.684 21017-25782 SmsReaderService        com.akslabs.SandeshVahak             V  Evaluating message id=739, date=1757263678038 (2025-09-07 22:17:58.038), exists=true
2025-09-07 22:33:29.684 21017-25782 SmsReaderService        com.akslabs.SandeshVahak             D  Message 739 already exists in DB, skipping
2025-09-07 22:33:29.689 21017-25782 SmsReaderService        com.akslabs.SandeshVahak             V  Evaluating message id=740, date=1757263728874 (2025-09-07 22:18:48.874), exists=true
2025-09-07 22:33:29.689 21017-25782 SmsReaderService        com.akslabs.SandeshVahak             D  Message 740 already exists in DB, skipping
2025-09-07 22:33:29.695 21017-25782 SmsReaderService        com.akslabs.SandeshVahak             V  Evaluating message id=741, date=1757263767342 (2025-09-07 22:19:27.342), exists=true
2025-09-07 22:33:29.696 21017-25782 SmsReaderService        com.akslabs.SandeshVahak             D  Message 741 already exists in DB, skipping
2025-09-07 22:33:29.700 21017-25782 SmsReaderService        com.akslabs.SandeshVahak             V  Evaluating message id=742, date=1757263798564 (2025-09-07 22:19:58.564), exists=true
2025-09-07 22:33:29.700 21017-25782 SmsReaderService        com.akslabs.SandeshVahak             D  Message 742 already exists in DB, skipping
2025-09-07 22:33:29.700 21017-25782 SmsReaderService        com.akslabs.SandeshVahak             I  Incremental SMS sync complete: 0 new messages (batch)
2025-09-07 22:33:29.741 21017-25770 HWUI                    com.akslabs.SandeshVahak             I  Davey! duration=1539ms; Flags=0, FrameTimelineVsyncId=44668460, IntendedVsync=78996459569897, Vsync=78997842841340, InputEventId=0, HandleInputStart=78997858564759, AnimationStart=78997858567988, PerformTraversalsStart=78997946329551, DrawStart=78997976908822, FrameDeadline=78997714046973, FrameInterval=78997857721738, FrameStartTime=16665921, SyncQueued=78997990303301, SyncStart=78997990389082, IssueDrawCommandsStart=78997990511009, SwapBuffers=78997995470540, FrameCompleted=78997998853457, DequeueBufferDuration=15469, QueueBufferDuration=318385, GpuCompleted=78997998853457, SwapBuffersCompleted=78997996588145, DisplayPresentTime=72904454214516736, CommandSubmissionCompleted=78997995470540,
2025-09-07 22:33:29.856 21017-21017 AppNavHost              com.akslabs.SandeshVahak             D  Local count recomposed: 0
2025-09-07 22:33:29.856 21017-21017 LocalSmsScreen          com.akslabs.SandeshVahak             D  Recompose LocalSmsScreen: items=0, loadState=CombinedLoadStates(refresh=Loading(endOfPaginationReached=false), prepend=NotLoading(endOfPaginationReached=false), append=NotLoading(endOfPaginationReached=false), source=LoadStates(refresh=Loading(endOfPaginationReached=false), prepend=NotLoading(endOfPaginationReached=false), append=NotLoading(endOfPaginationReached=false)), mediator=null)
2025-09-07 22:33:30.047 21017-21017 AppNavHost              com.akslabs.SandeshVahak             D  Local count recomposed: 504
2025-09-07 22:33:30.048 21017-21017 LocalSmsScreen          com.akslabs.SandeshVahak             D  Recompose LocalSmsScreen: items=0, loadState=CombinedLoadStates(refresh=Loading(endOfPaginationReached=false), prepend=NotLoading(endOfPaginationReached=false), append=NotLoading(endOfPaginationReached=false), source=LoadStates(refresh=Loading(endOfPaginationReached=false), prepend=NotLoading(endOfPaginationReached=false), append=NotLoading(endOfPaginationReached=false)), mediator=null)
2025-09-07 22:33:30.330 21017-21017 LocalSmsScreen          com.akslabs.SandeshVahak             D  Recompose LocalSmsScreen: items=60, loadState=CombinedLoadStates(refresh=NotLoading(endOfPaginationReached=false), prepend=NotLoading(endOfPaginationReached=true), append=NotLoading(endOfPaginationReached=false), source=LoadStates(refresh=NotLoading(endOfPaginationReached=false), prepend=NotLoading(endOfPaginationReached=true), append=NotLoading(endOfPaginationReached=false)), mediator=null)
2025-09-07 22:33:31.037 21017-25771 HWUI                    com.akslabs.SandeshVahak             I  Davey! duration=880ms; Flags=0, FrameTimelineVsyncId=44669712, IntendedVsync=78998426230151, Vsync=78998542893880, InputEventId=0, HandleInputStart=78998547092519, AnimationStart=78998547094863, PerformTraversalsStart=78998615799863, DrawStart=78998615892051, FrameDeadline=78998497544754, FrameInterval=78998546705176, FrameStartTime=16666247, SyncQueued=78999297627467, SyncStart=78999297684029, IssueDrawCommandsStart=78999297954342, SwapBuffers=78999304092571, FrameCompleted=78999306943248, DequeueBufferDuration=16875, QueueBufferDuration=238907, GpuCompleted=78999306943248, SwapBuffersCompleted=78999305063717, DisplayPresentTime=0, CommandSubmissionCompleted=78999304092571,
2025-09-07 22:33:31.256 21017-21017 Choreographer           com.akslabs.SandeshVahak             I  Skipped 58 frames!  The application may be doing too much work on its main thread.
2025-09-07 22:33:31.404 21017-25771 HWUI                    com.akslabs.SandeshVahak             I  Davey! duration=1108ms; Flags=0, FrameTimelineVsyncId=44669776, IntendedVsync=78998559604098, Vsync=78999526364744, InputEventId=0, HandleInputStart=78999541546269, AnimationStart=78999541548925, PerformTraversalsStart=78999549964811, DrawStart=78999550121738, FrameDeadline=78999330874574, FrameInterval=78999540844602, FrameStartTime=16668287, SyncQueued=78999662956633, SyncStart=78999663273248, IssueDrawCommandsStart=78999663514290, SwapBuffers=78999665295488, FrameCompleted=78999668153665, DequeueBufferDuration=16771, QueueBufferDuration=308125, GpuCompleted=78999668153665, SwapBuffersCompleted=78999666072623, DisplayPresentTime=72904454231491835, CommandSubmissionCompleted=78999665295488,
2025-09-07 22:33:31.652 21017-21017 AppNavHost              com.akslabs.SandeshVahak             D  Local count recomposed: 504
2025-09-07 22:33:31.652 21017-21017 LocalSmsScreen          com.akslabs.SandeshVahak             D  Recompose LocalSmsScreen: items=60, loadState=CombinedLoadStates(refresh=NotLoading(endOfPaginationReached=false), prepend=NotLoading(endOfPaginationReached=true), append=NotLoading(endOfPaginationReached=false), source=LoadStates(refresh=NotLoading(endOfPaginationReached=false), prepend=NotLoading(endOfPaginationReached=true), append=NotLoading(endOfPaginationReached=false)), mediator=null)
2025-09-07 22:33:31.886 21017-21017 AppNavHost              com.akslabs.SandeshVahak             D  Local count recomposed: 504
2025-09-07 22:33:31.886 21017-21017 LocalSmsScreen          com.akslabs.SandeshVahak             D  Recompose LocalSmsScreen: items=60, loadState=CombinedLoadStates(refresh=NotLoading(endOfPaginationReached=false), prepend=NotLoading(endOfPaginationReached=true), append=NotLoading(endOfPaginationReached=false), source=LoadStates(refresh=NotLoading(endOfPaginationReached=false), prepend=NotLoading(endOfPaginationReached=true), append=NotLoading(endOfPaginationReached=false)), mediator=null)
2025-09-07 22:33:32.648 21017-25819 ProfileInstaller        com.akslabs.SandeshVahak             D  Installing profile for com.akslabs.SandeshVahak
2025-09-07 22:34:43.849 21017-21017 SmsObserverService      com.akslabs.SandeshVahak             D  Static stop() called
2025-09-07 22:34:43.864 21017-21017 SmsContentObserver      com.akslabs.SandeshVahak             I  SMS content observer stopped
2025-09-07 22:34:58.643 21017-21017 SmsObserverService      com.akslabs.SandeshVahak             D  Static start() called
2025-09-07 22:34:58.666 21017-21017 SmsContentObserver      com.akslabs.SandeshVahak             D  Registering content observer on URI=content://sms, notifyForDescendants=true, thread=main
2025-09-07 22:34:58.668 21017-21017 SmsContentObserver      com.akslabs.SandeshVahak             I  ✅ SMS content observer started and registered
2025-09-07 22:34:58.723 21017-21017 WindowOnBackDispatcher  com.akslabs.SandeshVahak             W  sendCancelIfRunning: isInProgress=false callback=androidx.activity.OnBackPressedDispatcher$Api34Impl$createOnBackAnimationCallback$1@19eb846
2025-09-07 22:34:58.794 21017-21017 SmsObserverService      com.akslabs.SandeshVahak             I  onCreate: Service creating.
2025-09-07 22:34:58.798 21017-21017 SmsObserverService      com.akslabs.SandeshVahak             D  Notification channel created.
2025-09-07 22:34:58.798 21017-21017 SmsObserverService      com.akslabs.SandeshVahak             I  onCreate: Preference change listener registered.
2025-09-07 22:34:58.798 21017-21017 SmsObserverService      com.akslabs.SandeshVahak             I  updateSyncStateBasedOnPreferences: isEnabled=true, mode=NEW_ONLY, since=1757263655064, manuallyStopped=false, isInitialCall=true
2025-09-07 22:34:58.798 21017-21017 SmsObserverService      com.akslabs.SandeshVahak             I  updateSyncStateBasedOnPreferences: Sync NEW_ONLY mode detected. Starting catch-up.
2025-09-07 22:34:58.799 21017-21017 SmsObserverService      com.akslabs.SandeshVahak             D  Creating notification with text: Processing recent SMS...
2025-09-07 22:34:58.812 21017-25782 SmsObserverService      com.akslabs.SandeshVahak             I  performCatchUpSyncAndMonitorNewSms: Starting with syncEnabledSince=1757263655064.
2025-09-07 22:34:58.812 21017-25782 SmsObserverService      com.akslabs.SandeshVahak             D  Performing initial catch-up for NEW_ONLY mode.
2025-09-07 22:34:58.813 21017-21017 SmsObserverService      com.akslabs.SandeshVahak             I  onStartCommand: Received action: ACTION_START_SERVICE, startId: 1
2025-09-07 22:34:58.813 21017-21017 SmsObserverService      com.akslabs.SandeshVahak             I  onStartCommand: ACTION_START_SERVICE. Updating sync state.
2025-09-07 22:34:58.814 21017-21017 SmsObserverService      com.akslabs.SandeshVahak             D  Creating notification with text: Initializing SMS Sync...
2025-09-07 22:34:58.816 21017-25782 SmsSyncService          com.akslabs.SandeshVahak             D  Performing quick SMS sync
2025-09-07 22:34:58.816 21017-25782 SmsSyncService          com.akslabs.SandeshVahak             D  Preference isSmsSyncEnabledKey = true
2025-09-07 22:34:58.818 21017-21017 SmsObserverService      com.akslabs.SandeshVahak             I  updateSyncStateBasedOnPreferences: isEnabled=true, mode=NEW_ONLY, since=1757263655064, manuallyStopped=false, isInitialCall=false
2025-09-07 22:34:58.819 21017-25782 SmsSyncService          com.akslabs.SandeshVahak             D  Configured channelId = -1002651869724
2025-09-07 22:34:58.820 21017-21017 SmsObserverService      com.akslabs.SandeshVahak             I  updateSyncStateBasedOnPreferences: Sync NEW_ONLY mode detected. Starting catch-up.
2025-09-07 22:34:58.820 21017-21017 SmsObserverService      com.akslabs.SandeshVahak             D  Creating notification with text: Processing recent SMS...
2025-09-07 22:34:58.826 21017-25784 SmsObserverService      com.akslabs.SandeshVahak             I  performCatchUpSyncAndMonitorNewSms: Starting with syncEnabledSince=1757263655064.
2025-09-07 22:34:58.826 21017-25784 SmsObserverService      com.akslabs.SandeshVahak             D  Performing initial catch-up for NEW_ONLY mode.
2025-09-07 22:34:58.826 21017-25784 SmsSyncService          com.akslabs.SandeshVahak             D  Performing quick SMS sync
2025-09-07 22:34:58.826 21017-25784 SmsSyncService          com.akslabs.SandeshVahak             D  Preference isSmsSyncEnabledKey = true
2025-09-07 22:34:58.827 21017-25784 SmsSyncService          com.akslabs.SandeshVahak             D  Configured channelId = -1002651869724
2025-09-07 22:34:58.829 21017-25784 SmsReaderService        com.akslabs.SandeshVahak             D  NEW_ONLY mode: using baseline timestamp for incremental sync
2025-09-07 22:34:58.829 21017-25784 SmsReaderService        com.akslabs.SandeshVahak             I  Starting incremental SMS sync (mode: NEW_ONLY) from timestamp: 1757263650064 (lastDB=1757263798564, baseline=1757263655064, buffer=5000)
2025-09-07 22:34:58.833 21017-25784 SmsReaderService        com.akslabs.SandeshVahak             D  Timestamp details: lastDB=2025-09-07 22:19:58.564, baseline=2025-09-07 22:17:35.064, effective=2025-09-07 22:17:30.064
2025-09-07 22:34:58.835 21017-25782 SmsReaderService        com.akslabs.SandeshVahak             E  Error in incremental SMS sync
kotlinx.coroutines.JobCancellationException: StandaloneCoroutine was cancelled; job=StandaloneCoroutine{Cancelling}@4a67052
2025-09-07 22:34:58.836 21017-25782 SmsSyncService          com.akslabs.SandeshVahak             E  Exception during quick SMS sync
kotlinx.coroutines.JobCancellationException: StandaloneCoroutine was cancelled; job=StandaloneCoroutine{Cancelling}@4a67052
2025-09-07 22:34:58.837 21017-25782 SmsObserverService      com.akslabs.SandeshVahak             E  Exception during initial catch-up sync
kotlinx.coroutines.JobCancellationException: StandaloneCoroutine was cancelled; job=StandaloneCoroutine{Cancelling}@4a67052
2025-09-07 22:34:58.837 21017-25782 SmsObserverService      com.akslabs.SandeshVahak             I  performCatchUpSyncAndMonitorNewSms: Initial catch-up complete.
2025-09-07 22:34:58.838 21017-25782 SmsObserverService      com.akslabs.SandeshVahak             D  Creating notification with text: SMS sync active (new messages handled by system observer).
2025-09-07 22:34:58.841 21017-25782 SmsObserverService      com.akslabs.SandeshVahak             D  Notification updated with text: SMS sync active (new messages handled by system observer).
2025-09-07 22:34:58.841 21017-25782 SmsObserverService      com.akslabs.SandeshVahak             I  performCatchUpSyncAndMonitorNewSms: END (catch-up finished)
2025-09-07 22:34:58.936 21017-25782 SmsReaderService        com.akslabs.SandeshVahak             I  Reading SMS messages after timestamp: 1757263650064 (2025-09-07 22:17:30.064)
2025-09-07 22:34:58.936 21017-25782 SmsReaderService        com.akslabs.SandeshVahak             D  Query: uri=content://sms, selection='date > ?', args=[1757263650064], sort=date ASC
2025-09-07 22:34:58.968 21017-25782 SmsReaderService        com.akslabs.SandeshVahak             D  Cursor is null? false
2025-09-07 22:34:58.968 21017-25782 SmsReaderService        com.akslabs.SandeshVahak             V  Row #1 -> id=739, date=1757263678038, addr=+919545154067
2025-09-07 22:34:58.968 21017-25782 SmsReaderService        com.akslabs.SandeshVahak             V  Row #2 -> id=740, date=1757263728874, addr=+919545154067
2025-09-07 22:34:58.968 21017-25782 SmsReaderService        com.akslabs.SandeshVahak             V  Row #3 -> id=741, date=1757263767342, addr=+919545154067
2025-09-07 22:34:58.968 21017-25782 SmsReaderService        com.akslabs.SandeshVahak             V  Row #4 -> id=742, date=1757263798564, addr=+919545154067
2025-09-07 22:34:58.968 21017-25782 SmsReaderService        com.akslabs.SandeshVahak             D  Total rows iterated: 4
2025-09-07 22:34:58.981 21017-25782 SmsReaderService        com.akslabs.SandeshVahak             I  Read 4 new SMS messages after timestamp
2025-09-07 22:34:58.984 21017-25782 SmsReaderService        com.akslabs.SandeshVahak             V  Evaluating message id=739, date=1757263678038 (2025-09-07 22:17:58.038), exists=true
2025-09-07 22:34:58.984 21017-25782 SmsReaderService        com.akslabs.SandeshVahak             D  Message 739 already exists in DB, skipping
2025-09-07 22:34:58.987 21017-25782 SmsReaderService        com.akslabs.SandeshVahak             V  Evaluating message id=740, date=1757263728874 (2025-09-07 22:18:48.874), exists=true
2025-09-07 22:34:58.988 21017-25782 SmsReaderService        com.akslabs.SandeshVahak             D  Message 740 already exists in DB, skipping
2025-09-07 22:34:58.991 21017-25782 SmsReaderService        com.akslabs.SandeshVahak             V  Evaluating message id=741, date=1757263767342 (2025-09-07 22:19:27.342), exists=true
2025-09-07 22:34:58.991 21017-25782 SmsReaderService        com.akslabs.SandeshVahak             D  Message 741 already exists in DB, skipping
2025-09-07 22:34:58.993 21017-25782 SmsReaderService        com.akslabs.SandeshVahak             V  Evaluating message id=742, date=1757263798564 (2025-09-07 22:19:58.564), exists=true
2025-09-07 22:34:58.994 21017-25782 SmsReaderService        com.akslabs.SandeshVahak             D  Message 742 already exists in DB, skipping
2025-09-07 22:34:58.994 21017-25782 SmsReaderService        com.akslabs.SandeshVahak             I  Incremental SMS sync complete: 0 new messages (batch)
2025-09-07 22:34:58.994 21017-25782 SmsSyncService          com.akslabs.SandeshVahak             D  syncNewSmsToDatabase returned 0 new messages
2025-09-07 22:34:58.994 21017-25782 SmsSyncService          com.akslabs.SandeshVahak             D  Proceeding to unsynced fetch; newLocalMessages=0
2025-09-07 22:34:58.994 21017-25782 SmsSyncService          com.akslabs.SandeshVahak             D  Sync mode: NEW_ONLY, baseline: 1757263655064
2025-09-07 22:34:58.995 21017-25782 SmsSyncService          com.akslabs.SandeshVahak             I  ✅ NEW_ONLY mode properly configured - baseline: 1757263655064 (2025-09-07 22:17:35)
2025-09-07 22:34:58.995 21017-25782 SmsSyncService          com.akslabs.SandeshVahak             D  NEW_ONLY mode: getting unsynced messages after baseline 1757263655064
2025-09-07 22:34:58.998 21017-25782 SmsSyncService          com.akslabs.SandeshVahak             D  NEW_ONLY quick sync query returned 0 messages
2025-09-07 22:34:58.998 21017-25782 SmsSyncService          com.akslabs.SandeshVahak             D  Retrieved 0 unsynced messages (mode: NEW_ONLY, baseline: 1757263655064)
2025-09-07 22:34:58.998 21017-25782 SmsSyncService          com.akslabs.SandeshVahak             D  No unsynced messages to sync
2025-09-07 22:34:58.998 21017-25782 SmsObserverService      com.akslabs.SandeshVahak             I  Initial catch-up sync: 0 messages.
2025-09-07 22:34:58.998 21017-25782 SmsObserverService      com.akslabs.SandeshVahak             I  performCatchUpSyncAndMonitorNewSms: Initial catch-up complete.
2025-09-07 22:34:58.999 21017-25782 SmsObserverService      com.akslabs.SandeshVahak             D  Creating notification with text: SMS sync active (new messages handled by system observer).
2025-09-07 22:34:59.002 21017-25782 SmsObserverService      com.akslabs.SandeshVahak             D  Notification updated with text: SMS sync active (new messages handled by system observer).
2025-09-07 22:34:59.002 21017-25782 SmsObserverService      com.akslabs.SandeshVahak             I  performCatchUpSyncAndMonitorNewSms: END (catch-up finished)
2025-09-07 22:35:51.905 21017-21017 SmsContentObserver      com.akslabs.SandeshVahak             D  onChange(selfChange=false) called with no URI
2025-09-07 22:35:51.907 21017-21017 SmsContentObserver      com.akslabs.SandeshVahak             D  onChange(selfChange=false, uri=content://sms/raw/9)
2025-09-07 22:35:51.907 21017-21017 SmsContentObserver      com.akslabs.SandeshVahak             D  ⏱️ SMS change debounced (2ms)
2025-09-07 22:35:51.908 21017-25782 SmsContentObserver      com.akslabs.SandeshVahak             I  ⚡ SMS content changed, triggering lightning-fast sync
2025-09-07 22:35:52.110 21017-25782 SmsContentObserver      com.akslabs.SandeshVahak             D  Preference isSmsSyncEnabledKey read: true
2025-09-07 22:35:52.110 21017-25782 SmsContentObserver      com.akslabs.SandeshVahak             D  SMS sync enabled: true
2025-09-07 22:35:52.110 21017-25782 SmsContentObserver      com.akslabs.SandeshVahak             D  Content observer triggered - Mode: NEW_ONLY, Baseline: 1757263655064, IsEnabled: true
2025-09-07 22:35:52.112 21017-25782 SmsContentObserver      com.akslabs.SandeshVahak             I  ✅ NEW_ONLY mode properly configured with baseline: 1757263655064 (2025-09-07 22:17:35)
2025-09-07 22:35:52.117 21017-25782 SmsReaderService        com.akslabs.SandeshVahak             D  NEW_ONLY mode: using baseline timestamp for incremental sync
2025-09-07 22:35:52.117 21017-25782 SmsReaderService        com.akslabs.SandeshVahak             I  Starting incremental SMS sync (mode: NEW_ONLY) from timestamp: 1757263650064 (lastDB=1757263798564, baseline=1757263655064, buffer=5000)
2025-09-07 22:35:52.120 21017-25782 SmsReaderService        com.akslabs.SandeshVahak             D  Timestamp details: lastDB=2025-09-07 22:19:58.564, baseline=2025-09-07 22:17:35.064, effective=2025-09-07 22:17:30.064
2025-09-07 22:35:52.225 21017-25782 SmsReaderService        com.akslabs.SandeshVahak             I  Reading SMS messages after timestamp: 1757263650064 (2025-09-07 22:17:30.064)
2025-09-07 22:35:52.226 21017-25782 SmsReaderService        com.akslabs.SandeshVahak             D  Query: uri=content://sms, selection='date > ?', args=[1757263650064], sort=date ASC
2025-09-07 22:35:52.251 21017-25782 SmsReaderService        com.akslabs.SandeshVahak             D  Cursor is null? false
2025-09-07 22:35:52.251 21017-25782 SmsReaderService        com.akslabs.SandeshVahak             V  Row #1 -> id=739, date=1757263678038, addr=+919545154067
2025-09-07 22:35:52.251 21017-25782 SmsReaderService        com.akslabs.SandeshVahak             V  Row #2 -> id=740, date=1757263728874, addr=+919545154067
2025-09-07 22:35:52.251 21017-25782 SmsReaderService        com.akslabs.SandeshVahak             V  Row #3 -> id=741, date=1757263767342, addr=+919545154067
2025-09-07 22:35:52.252 21017-25782 SmsReaderService        com.akslabs.SandeshVahak             V  Row #4 -> id=742, date=1757263798564, addr=+919545154067
2025-09-07 22:35:52.252 21017-25782 SmsReaderService        com.akslabs.SandeshVahak             D  Total rows iterated: 4
2025-09-07 22:35:52.264 21017-25782 SmsReaderService        com.akslabs.SandeshVahak             I  Read 4 new SMS messages after timestamp
2025-09-07 22:35:52.268 21017-25782 SmsReaderService        com.akslabs.SandeshVahak             V  Evaluating message id=739, date=1757263678038 (2025-09-07 22:17:58.038), exists=true
2025-09-07 22:35:52.268 21017-25782 SmsReaderService        com.akslabs.SandeshVahak             D  Message 739 already exists in DB, skipping
2025-09-07 22:35:52.271 21017-25782 SmsReaderService        com.akslabs.SandeshVahak             V  Evaluating message id=740, date=1757263728874 (2025-09-07 22:18:48.874), exists=true
2025-09-07 22:35:52.271 21017-25782 SmsReaderService        com.akslabs.SandeshVahak             D  Message 740 already exists in DB, skipping
2025-09-07 22:35:52.275 21017-25782 SmsReaderService        com.akslabs.SandeshVahak             V  Evaluating message id=741, date=1757263767342 (2025-09-07 22:19:27.342), exists=true
2025-09-07 22:35:52.275 21017-25782 SmsReaderService        com.akslabs.SandeshVahak             D  Message 741 already exists in DB, skipping
2025-09-07 22:35:52.278 21017-25782 SmsReaderService        com.akslabs.SandeshVahak             V  Evaluating message id=742, date=1757263798564 (2025-09-07 22:19:58.564), exists=true
2025-09-07 22:35:52.278 21017-25782 SmsReaderService        com.akslabs.SandeshVahak             D  Message 742 already exists in DB, skipping
2025-09-07 22:35:52.278 21017-25782 SmsReaderService        com.akslabs.SandeshVahak             I  Incremental SMS sync complete: 0 new messages (batch)
2025-09-07 22:35:52.278 21017-25782 SmsContentObserver      com.akslabs.SandeshVahak             I  📱 DB update: inserted 0 new messages into local database (incremental, mode: NEW_ONLY)
2025-09-07 22:35:52.278 21017-25782 SmsContentObserver      com.akslabs.SandeshVahak             D  ✅ No new SMS messages found (370ms)
2025-09-07 22:35:52.939 21017-21017 SmsContentObserver      com.akslabs.SandeshVahak             D  onChange(selfChange=false) called with no URI
2025-09-07 22:35:52.940 21017-21017 SmsContentObserver      com.akslabs.SandeshVahak             D  onChange(selfChange=false, uri=content://sms/743)
2025-09-07 22:35:52.940 21017-21017 SmsContentObserver      com.akslabs.SandeshVahak             D  ⏱️ SMS change debounced (1ms)
2025-09-07 22:35:52.940 21017-25782 SmsContentObserver      com.akslabs.SandeshVahak             I  ⚡ SMS content changed, triggering lightning-fast sync
2025-09-07 22:35:53.143 21017-25782 SmsContentObserver      com.akslabs.SandeshVahak             D  Preference isSmsSyncEnabledKey read: true
2025-09-07 22:35:53.143 21017-25782 SmsContentObserver      com.akslabs.SandeshVahak             D  SMS sync enabled: true
2025-09-07 22:35:53.143 21017-25782 SmsContentObserver      com.akslabs.SandeshVahak             D  Content observer triggered - Mode: NEW_ONLY, Baseline: 1757263655064, IsEnabled: true
2025-09-07 22:35:53.145 21017-25782 SmsContentObserver      com.akslabs.SandeshVahak             I  ✅ NEW_ONLY mode properly configured with baseline: 1757263655064 (2025-09-07 22:17:35)
2025-09-07 22:35:53.149 21017-25782 SmsReaderService        com.akslabs.SandeshVahak             D  NEW_ONLY mode: using baseline timestamp for incremental sync
2025-09-07 22:35:53.150 21017-25782 SmsReaderService        com.akslabs.SandeshVahak             I  Starting incremental SMS sync (mode: NEW_ONLY) from timestamp: 1757263650064 (lastDB=1757263798564, baseline=1757263655064, buffer=5000)
2025-09-07 22:35:53.156 21017-25782 SmsReaderService        com.akslabs.SandeshVahak             D  Timestamp details: lastDB=2025-09-07 22:19:58.564, baseline=2025-09-07 22:17:35.064, effective=2025-09-07 22:17:30.064
2025-09-07 22:35:53.262 21017-25782 SmsReaderService        com.akslabs.SandeshVahak             I  Reading SMS messages after timestamp: 1757263650064 (2025-09-07 22:17:30.064)
2025-09-07 22:35:53.262 21017-25782 SmsReaderService        com.akslabs.SandeshVahak             D  Query: uri=content://sms, selection='date > ?', args=[1757263650064], sort=date ASC
2025-09-07 22:35:53.283 21017-25782 SmsReaderService        com.akslabs.SandeshVahak             D  Cursor is null? false
2025-09-07 22:35:53.284 21017-25782 SmsReaderService        com.akslabs.SandeshVahak             V  Row #1 -> id=739, date=1757263678038, addr=+919545154067
2025-09-07 22:35:53.284 21017-25782 SmsReaderService        com.akslabs.SandeshVahak             V  Row #2 -> id=740, date=1757263728874, addr=+919545154067
2025-09-07 22:35:53.284 21017-25782 SmsReaderService        com.akslabs.SandeshVahak             V  Row #3 -> id=741, date=1757263767342, addr=+919545154067
2025-09-07 22:35:53.285 21017-25782 SmsReaderService        com.akslabs.SandeshVahak             V  Row #4 -> id=742, date=1757263798564, addr=+919545154067
2025-09-07 22:35:53.285 21017-25782 SmsReaderService        com.akslabs.SandeshVahak             V  Row #5 -> id=743, date=1757264752417, addr=+919545154067
2025-09-07 22:35:53.285 21017-25782 SmsReaderService        com.akslabs.SandeshVahak             D  Total rows iterated: 5
2025-09-07 22:35:53.298 21017-25782 SmsReaderService        com.akslabs.SandeshVahak             I  Read 5 new SMS messages after timestamp
2025-09-07 22:35:53.301 21017-25782 SmsReaderService        com.akslabs.SandeshVahak             V  Evaluating message id=739, date=1757263678038 (2025-09-07 22:17:58.038), exists=true
2025-09-07 22:35:53.301 21017-25782 SmsReaderService        com.akslabs.SandeshVahak             D  Message 739 already exists in DB, skipping
2025-09-07 22:35:53.304 21017-25782 SmsReaderService        com.akslabs.SandeshVahak             V  Evaluating message id=740, date=1757263728874 (2025-09-07 22:18:48.874), exists=true
2025-09-07 22:35:53.304 21017-25782 SmsReaderService        com.akslabs.SandeshVahak             D  Message 740 already exists in DB, skipping
2025-09-07 22:35:53.307 21017-25782 SmsReaderService        com.akslabs.SandeshVahak             V  Evaluating message id=741, date=1757263767342 (2025-09-07 22:19:27.342), exists=true
2025-09-07 22:35:53.307 21017-25782 SmsReaderService        com.akslabs.SandeshVahak             D  Message 741 already exists in DB, skipping
2025-09-07 22:35:53.310 21017-25782 SmsReaderService        com.akslabs.SandeshVahak             V  Evaluating message id=742, date=1757263798564 (2025-09-07 22:19:58.564), exists=true
2025-09-07 22:35:53.310 21017-25782 SmsReaderService        com.akslabs.SandeshVahak             D  Message 742 already exists in DB, skipping
2025-09-07 22:35:53.313 21017-25782 SmsReaderService        com.akslabs.SandeshVahak             V  Evaluating message id=743, date=1757264752417 (2025-09-07 22:35:52.417), exists=false
2025-09-07 22:35:53.313 21017-25782 SmsReaderService        com.akslabs.SandeshVahak             I  ✅ New message queued for insert: 743 from +919545154067 at 1757264752417
2025-09-07 22:35:53.347 21017-25782 SmsReaderService        com.akslabs.SandeshVahak             I  Incremental SMS sync complete: 1 new messages (batch)
2025-09-07 22:35:53.348 21017-25784 SmsContentObserver      com.akslabs.SandeshVahak             I  📱 DB update: inserted 1 new messages into local database (incremental, mode: NEW_ONLY)
2025-09-07 22:35:53.348 21017-25784 SmsContentObserver      com.akslabs.SandeshVahak             I  🚀 Found 1 new SMS messages in 408ms
2025-09-07 22:35:53.348 21017-25784 SmsContentObserver      com.akslabs.SandeshVahak             I  ☁️ Triggering immediate cloud sync (Instant worker) since SMS sync is enabled (mode: NEW_ONLY)
2025-09-07 22:35:53.636 21017-21017 AppNavHost              com.akslabs.SandeshVahak             D  Local count recomposed: 505
2025-09-07 22:35:53.640 21017-21017 LocalSmsScreen          com.akslabs.SandeshVahak             D  Recompose LocalSmsScreen: items=60, loadState=CombinedLoadStates(refresh=Loading(endOfPaginationReached=false), prepend=NotLoading(endOfPaginationReached=false), append=NotLoading(endOfPaginationReached=false), source=LoadStates(refresh=Loading(endOfPaginationReached=false), prepend=NotLoading(endOfPaginationReached=false), append=NotLoading(endOfPaginationReached=false)), mediator=null)
2025-09-07 22:35:53.741 21017-21017 LocalSmsScreen          com.akslabs.SandeshVahak             D  Recompose LocalSmsScreen: items=60, loadState=CombinedLoadStates(refresh=NotLoading(endOfPaginationReached=false), prepend=NotLoading(endOfPaginationReached=true), append=NotLoading(endOfPaginationReached=false), source=LoadStates(refresh=NotLoading(endOfPaginationReached=false), prepend=NotLoading(endOfPaginationReached=true), append=NotLoading(endOfPaginationReached=false)), mediator=null)
2025-09-07 22:35:53.850 21017-25782 WorkModule.SmsSync      com.akslabs.SandeshVahak             D  enqueueInstant() called -> InstantSmsSyncWorker expedited
2025-09-07 22:35:53.864 21017-25782 SmsContentObserver      com.akslabs.SandeshVahak             D  ✅ Instant sync worker enqueued successfully
2025-09-07 22:35:53.915 21017-25762 bs.SandeshVahak         com.akslabs.SandeshVahak             I  Compiler allocated 5009KB to compile void com.akslabs.SandeshVahak.ui.components.SmsListItemKt$SmsListItem$2.invoke(androidx.compose.foundation.layout.ColumnScope, androidx.compose.runtime.Composer, int)
2025-09-07 22:35:54.356 21017-21017 SmsContentObserver      com.akslabs.SandeshVahak             D  onChange(selfChange=false) called with no URI
2025-09-07 22:35:54.356 21017-21017 SmsContentObserver      com.akslabs.SandeshVahak             D  onChange(selfChange=false, uri=content://sms/raw)
2025-09-07 22:35:54.356 21017-21017 SmsContentObserver      com.akslabs.SandeshVahak             D  ⏱️ SMS change debounced (0ms)
2025-09-07 22:35:54.357 21017-25782 SmsContentObserver      com.akslabs.SandeshVahak             I  ⚡ SMS content changed, triggering lightning-fast sync
2025-09-07 22:35:54.386 21017-26006 HWUI                    com.akslabs.SandeshVahak             I  Davey! duration=802ms; Flags=0, FrameTimelineVsyncId=44740511, IntendedVsync=79141842911097, Vsync=79141959578578, InputEventId=0, HandleInputStart=79141973901840, AnimationStart=79141973906788, PerformTraversalsStart=79142026986892, DrawStart=79142027096944, FrameDeadline=79141964248655, FrameInterval=79141973386683, FrameStartTime=16666783, SyncQueued=79142639388610, SyncStart=79142639773089, IssueDrawCommandsStart=79142640104912, SwapBuffers=79142642540798, FrameCompleted=79142645350173, DequeueBufferDuration=16302, QueueBufferDuration=388281, GpuCompleted=79142645350173, SwapBuffersCompleted=79142643382725, DisplayPresentTime=0, CommandSubmissionCompleted=79142642540798,
2025-09-07 22:35:54.426 21017-21017 Choreographer           com.akslabs.SandeshVahak             I  Skipped 44 frames!  The application may be doing too much work on its main thread.
2025-09-07 22:35:54.449 21017-25782 InstantSmsSyncWorker    com.akslabs.SandeshVahak             D  Instant SMS sync worker started
2025-09-07 22:35:54.449 21017-25782 InstantSmsSyncWorker    com.akslabs.SandeshVahak             D  Instant sync worker - Enabled: true, Mode: NEW_ONLY, Baseline: 1757263655064
2025-09-07 22:35:54.449 21017-25782 InstantSmsSyncWorker    com.akslabs.SandeshVahak             D  Invoking performQuickSync() from InstantSmsSyncWorker
2025-09-07 22:35:54.451 21017-25782 SmsSyncService          com.akslabs.SandeshVahak             D  Performing quick SMS sync
2025-09-07 22:35:54.451 21017-25782 SmsSyncService          com.akslabs.SandeshVahak             D  Preference isSmsSyncEnabledKey = true
2025-09-07 22:35:54.453 21017-25782 SmsSyncService          com.akslabs.SandeshVahak             D  Configured channelId = -1002651869724
2025-09-07 22:35:54.453 21017-26006 HWUI                    com.akslabs.SandeshVahak             I  Davey! duration=739ms; Flags=0, FrameTimelineVsyncId=44740589, IntendedVsync=79141976242692, Vsync=79142709577536, InputEventId=0, HandleInputStart=79142710956579, AnimationStart=79142710959027, PerformTraversalsStart=79142710959964, DrawStart=79142711047048, FrameDeadline=79142680916796, FrameInterval=79142710248350, FrameStartTime=16666701, SyncQueued=79142711389704, SyncStart=79142711552100, IssueDrawCommandsStart=79142711740225, SwapBuffers=79142713474704, FrameCompleted=79142716334808, DequeueBufferDuration=17500, QueueBufferDuration=262969, GpuCompleted=79142716334808, SwapBuffersCompleted=79142714203766, DisplayPresentTime=0, CommandSubmissionCompleted=79142713474704,
2025-09-07 22:35:54.457 21017-25782 SmsReaderService        com.akslabs.SandeshVahak             D  NEW_ONLY mode: using baseline timestamp for incremental sync
2025-09-07 22:35:54.457 21017-25782 SmsReaderService        com.akslabs.SandeshVahak             I  Starting incremental SMS sync (mode: NEW_ONLY) from timestamp: 1757263650064 (lastDB=1757264752417, baseline=1757263655064, buffer=5000)
2025-09-07 22:35:54.460 21017-25782 SmsReaderService        com.akslabs.SandeshVahak             D  Timestamp details: lastDB=2025-09-07 22:35:52.417, baseline=2025-09-07 22:17:35.064, effective=2025-09-07 22:17:30.064
2025-09-07 22:35:54.558 21017-25782 SmsContentObserver      com.akslabs.SandeshVahak             D  Preference isSmsSyncEnabledKey read: true
2025-09-07 22:35:54.559 21017-25782 SmsContentObserver      com.akslabs.SandeshVahak             D  SMS sync enabled: true
2025-09-07 22:35:54.559 21017-25782 SmsContentObserver      com.akslabs.SandeshVahak             D  Content observer triggered - Mode: NEW_ONLY, Baseline: 1757263655064, IsEnabled: true
2025-09-07 22:35:54.559 21017-25782 SmsContentObserver      com.akslabs.SandeshVahak             I  ✅ NEW_ONLY mode properly configured with baseline: 1757263655064 (2025-09-07 22:17:35)
2025-09-07 22:35:54.562 21017-25782 SmsReaderService        com.akslabs.SandeshVahak             D  NEW_ONLY mode: using baseline timestamp for incremental sync
2025-09-07 22:35:54.562 21017-25782 SmsReaderService        com.akslabs.SandeshVahak             I  Starting incremental SMS sync (mode: NEW_ONLY) from timestamp: 1757263650064 (lastDB=1757264752417, baseline=1757263655064, buffer=5000)
2025-09-07 22:35:54.564 21017-25783 SmsReaderService        com.akslabs.SandeshVahak             I  Reading SMS messages after timestamp: 1757263650064 (2025-09-07 22:17:30.064)
2025-09-07 22:35:54.564 21017-25783 SmsReaderService        com.akslabs.SandeshVahak             D  Query: uri=content://sms, selection='date > ?', args=[1757263650064], sort=date ASC
2025-09-07 22:35:54.566 21017-25782 SmsReaderService        com.akslabs.SandeshVahak             D  Timestamp details: lastDB=2025-09-07 22:35:52.417, baseline=2025-09-07 22:17:35.064, effective=2025-09-07 22:17:30.064
2025-09-07 22:35:54.586 21017-25783 SmsReaderService        com.akslabs.SandeshVahak             D  Cursor is null? false
2025-09-07 22:35:54.586 21017-25783 SmsReaderService        com.akslabs.SandeshVahak             V  Row #1 -> id=739, date=1757263678038, addr=+919545154067
2025-09-07 22:35:54.586 21017-25783 SmsReaderService        com.akslabs.SandeshVahak             V  Row #2 -> id=740, date=1757263728874, addr=+919545154067
2025-09-07 22:35:54.587 21017-25783 SmsReaderService        com.akslabs.SandeshVahak             V  Row #3 -> id=741, date=1757263767342, addr=+919545154067
2025-09-07 22:35:54.587 21017-25783 SmsReaderService        com.akslabs.SandeshVahak             V  Row #4 -> id=742, date=1757263798564, addr=+919545154067
2025-09-07 22:35:54.587 21017-25783 SmsReaderService        com.akslabs.SandeshVahak             V  Row #5 -> id=743, date=1757264752417, addr=+919545154067
2025-09-07 22:35:54.587 21017-25783 SmsReaderService        com.akslabs.SandeshVahak             D  Total rows iterated: 5
2025-09-07 22:35:54.602 21017-25783 SmsReaderService        com.akslabs.SandeshVahak             I  Read 5 new SMS messages after timestamp
2025-09-07 22:35:54.608 21017-25783 SmsReaderService        com.akslabs.SandeshVahak             V  Evaluating message id=739, date=1757263678038 (2025-09-07 22:17:58.038), exists=true
2025-09-07 22:35:54.608 21017-25783 SmsReaderService        com.akslabs.SandeshVahak             D  Message 739 already exists in DB, skipping
2025-09-07 22:35:54.614 21017-25783 SmsReaderService        com.akslabs.SandeshVahak             V  Evaluating message id=740, date=1757263728874 (2025-09-07 22:18:48.874), exists=true
2025-09-07 22:35:54.614 21017-25783 SmsReaderService        com.akslabs.SandeshVahak             D  Message 740 already exists in DB, skipping
2025-09-07 22:35:54.619 21017-25783 SmsReaderService        com.akslabs.SandeshVahak             V  Evaluating message id=741, date=1757263767342 (2025-09-07 22:19:27.342), exists=true
2025-09-07 22:35:54.619 21017-25783 SmsReaderService        com.akslabs.SandeshVahak             D  Message 741 already exists in DB, skipping
2025-09-07 22:35:54.624 21017-25783 SmsReaderService        com.akslabs.SandeshVahak             V  Evaluating message id=742, date=1757263798564 (2025-09-07 22:19:58.564), exists=true
2025-09-07 22:35:54.624 21017-25783 SmsReaderService        com.akslabs.SandeshVahak             D  Message 742 already exists in DB, skipping
2025-09-07 22:35:54.629 21017-25783 SmsReaderService        com.akslabs.SandeshVahak             V  Evaluating message id=743, date=1757264752417 (2025-09-07 22:35:52.417), exists=true
2025-09-07 22:35:54.630 21017-25783 SmsReaderService        com.akslabs.SandeshVahak             D  Message 743 already exists in DB, skipping
2025-09-07 22:35:54.630 21017-25783 SmsReaderService        com.akslabs.SandeshVahak             I  Incremental SMS sync complete: 0 new messages (batch)
2025-09-07 22:35:54.630 21017-25783 SmsSyncService          com.akslabs.SandeshVahak             D  syncNewSmsToDatabase returned 0 new messages
2025-09-07 22:35:54.630 21017-25783 SmsSyncService          com.akslabs.SandeshVahak             D  Proceeding to unsynced fetch; newLocalMessages=0
2025-09-07 22:35:54.630 21017-25783 SmsSyncService          com.akslabs.SandeshVahak             D  Sync mode: NEW_ONLY, baseline: 1757263655064
2025-09-07 22:35:54.631 21017-25783 SmsSyncService          com.akslabs.SandeshVahak             I  ✅ NEW_ONLY mode properly configured - baseline: 1757263655064 (2025-09-07 22:17:35)
2025-09-07 22:35:54.631 21017-25783 SmsSyncService          com.akslabs.SandeshVahak             D  NEW_ONLY mode: getting unsynced messages after baseline 1757263655064
2025-09-07 22:35:54.636 21017-25783 SmsSyncService          com.akslabs.SandeshVahak             D  NEW_ONLY quick sync query returned 1 messages
2025-09-07 22:35:54.636 21017-25783 SmsSyncService          com.akslabs.SandeshVahak             D  Retrieved 1 unsynced messages (mode: NEW_ONLY, baseline: 1757263655064)
2025-09-07 22:35:54.647 21017-25783 SmsSyncService          com.akslabs.SandeshVahak             D  📤 Sending SMS message 1/1: 743 (1757264752417)
2025-09-07 22:35:54.651 21017-25783 BotApi                  com.akslabs.SandeshVahak             D  📤 Sending SMS message to channel: -1002651869724
2025-09-07 22:35:54.669 21017-25782 SmsReaderService        com.akslabs.SandeshVahak             I  Reading SMS messages after timestamp: 1757263650064 (2025-09-07 22:17:30.064)
2025-09-07 22:35:54.669 21017-25782 SmsReaderService        com.akslabs.SandeshVahak             D  Query: uri=content://sms, selection='date > ?', args=[1757263650064], sort=date ASC
2025-09-07 22:35:54.690 21017-25782 SmsReaderService        com.akslabs.SandeshVahak             D  Cursor is null? false
2025-09-07 22:35:54.690 21017-25782 SmsReaderService        com.akslabs.SandeshVahak             V  Row #1 -> id=739, date=1757263678038, addr=+919545154067
2025-09-07 22:35:54.690 21017-25782 SmsReaderService        com.akslabs.SandeshVahak             V  Row #2 -> id=740, date=1757263728874, addr=+919545154067
2025-09-07 22:35:54.690 21017-25782 SmsReaderService        com.akslabs.SandeshVahak             V  Row #3 -> id=741, date=1757263767342, addr=+919545154067
2025-09-07 22:35:54.691 21017-25782 SmsReaderService        com.akslabs.SandeshVahak             V  Row #4 -> id=742, date=1757263798564, addr=+919545154067
2025-09-07 22:35:54.691 21017-25782 SmsReaderService        com.akslabs.SandeshVahak             V  Row #5 -> id=743, date=1757264752417, addr=+919545154067
2025-09-07 22:35:54.691 21017-25782 SmsReaderService        com.akslabs.SandeshVahak             D  Total rows iterated: 5
2025-09-07 22:35:54.703 21017-25782 SmsReaderService        com.akslabs.SandeshVahak             I  Read 5 new SMS messages after timestamp
2025-09-07 22:35:54.707 21017-25782 SmsReaderService        com.akslabs.SandeshVahak             V  Evaluating message id=739, date=1757263678038 (2025-09-07 22:17:58.038), exists=true
2025-09-07 22:35:54.707 21017-25782 SmsReaderService        com.akslabs.SandeshVahak             D  Message 739 already exists in DB, skipping
2025-09-07 22:35:54.709 21017-25782 SmsReaderService        com.akslabs.SandeshVahak             V  Evaluating message id=740, date=1757263728874 (2025-09-07 22:18:48.874), exists=true
2025-09-07 22:35:54.709 21017-25782 SmsReaderService        com.akslabs.SandeshVahak             D  Message 740 already exists in DB, skipping
2025-09-07 22:35:54.712 21017-25782 SmsReaderService        com.akslabs.SandeshVahak             V  Evaluating message id=741, date=1757263767342 (2025-09-07 22:19:27.342), exists=true
2025-09-07 22:35:54.712 21017-25782 SmsReaderService        com.akslabs.SandeshVahak             D  Message 741 already exists in DB, skipping
2025-09-07 22:35:54.716 21017-25782 SmsReaderService        com.akslabs.SandeshVahak             V  Evaluating message id=742, date=1757263798564 (2025-09-07 22:19:58.564), exists=true
2025-09-07 22:35:54.716 21017-25782 SmsReaderService        com.akslabs.SandeshVahak             D  Message 742 already exists in DB, skipping
2025-09-07 22:35:54.718 21017-25782 SmsReaderService        com.akslabs.SandeshVahak             V  Evaluating message id=743, date=1757264752417 (2025-09-07 22:35:52.417), exists=true
2025-09-07 22:35:54.719 21017-25782 SmsReaderService        com.akslabs.SandeshVahak             D  Message 743 already exists in DB, skipping
2025-09-07 22:35:54.719 21017-25782 SmsReaderService        com.akslabs.SandeshVahak             I  Incremental SMS sync complete: 0 new messages (batch)
2025-09-07 22:35:54.719 21017-25782 SmsContentObserver      com.akslabs.SandeshVahak             I  📱 DB update: inserted 0 new messages into local database (incremental, mode: NEW_ONLY)
2025-09-07 22:35:54.719 21017-25782 SmsContentObserver      com.akslabs.SandeshVahak             D  ✅ No new SMS messages found (362ms)
2025-09-07 22:35:58.737 21017-25783 TelegramRateLimiter     com.akslabs.SandeshVahak             D  ✅ Request successful, rate limiter reset
2025-09-07 22:35:58.738 21017-25783 PerformanceMonitor      com.akslabs.SandeshVahak             I  ⚠️ MODERATE: telegram_send_message took 4087ms
2025-09-07 22:35:58.738 21017-25783 SmsSyncService          com.akslabs.SandeshVahak             I  ✅ Successfully synced SMS message: 743 (1/20 in current burst, total: 1)
2025-09-07 22:35:58.744 21017-25783 SmsSyncService          com.akslabs.SandeshVahak             D  📦 Applied DB updates in batch: synced=1, remoteInserted=1, failed=0
2025-09-07 22:35:58.744 21017-25783 SmsSyncService          com.akslabs.SandeshVahak             D  Quick sync complete: 1 messages synced
2025-09-07 22:35:58.747 21017-25782 InstantSmsSyncWorker    com.akslabs.SandeshVahak             D  performQuickSync() returned Success(messagesSynced=1)
2025-09-07 22:35:58.747 21017-25782 InstantSmsSyncWorker    com.akslabs.SandeshVahak             D  Instant sync completed: 1 messages synced
2025-09-07 22:35:58.747 21017-25782 InstantSmsSyncWorker    com.akslabs.SandeshVahak             D  Attempting to show instant sync notification for 1 messages
2025-09-07 22:35:58.760 21017-25780 WM-WorkerWrapper        com.akslabs.SandeshVahak             I  Worker result SUCCESS for Work [ id=27250e0c-e112-4e4d-9b00-520f864ece4f, tags={ com.akslabs.SandeshVahak.workers.InstantSmsSyncWorker } ]
2025-09-07 22:35:58.836 21017-21017 LocalSmsScreen          com.akslabs.SandeshVahak             D  Recompose LocalSmsScreen: items=60, loadState=CombinedLoadStates(refresh=Loading(endOfPaginationReached=false), prepend=NotLoading(endOfPaginationReached=false), append=NotLoading(endOfPaginationReached=false), source=LoadStates(refresh=Loading(endOfPaginationReached=false), prepend=NotLoading(endOfPaginationReached=false), append=NotLoading(endOfPaginationReached=false)), mediator=null)
2025-09-07 22:35:58.898 21017-21017 LocalSmsScreen          com.akslabs.SandeshVahak             D  Recompose LocalSmsScreen: items=60, loadState=CombinedLoadStates(refresh=NotLoading(endOfPaginationReached=false), prepend=NotLoading(endOfPaginationReached=true), append=NotLoading(endOfPaginationReached=false), source=LoadStates(refresh=NotLoading(endOfPaginationReached=false), prepend=NotLoading(endOfPaginationReached=true), append=NotLoading(endOfPaginationReached=false)), mediator=null)
2025-09-07 22:35:59.465 21017-21017 Choreographer           com.akslabs.SandeshVahak             I  Skipped 34 frames!  The application may be doing too much work on its main thread.
2025-09-07 22:36:07.447 21017-21017 WindowOnBackDispatcher  com.akslabs.SandeshVahak             W  sendCancelIfRunning: isInProgress=false callback=androidx.activity.OnBackPressedDispatcher$Api34Impl$createOnBackAnimationCallback$1@c35e92b
2025-09-07 22:36:07.477 21017-21017 SmsContentObserver      com.akslabs.SandeshVahak             I  SMS content observer stopped
2025-09-07 22:36:11.850 21017-21017 WindowOnBackDispatcher  com.akslabs.SandeshVahak             W  sendCancelIfRunning: isInProgress=false callback=android.app.Activity$$ExternalSyntheticLambda0@777fbc6
