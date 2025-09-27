2025-09-26 20:40:00.343 13319-13319 AndroidRuntime          pid-13319                            E  FATAL EXCEPTION: main (Ask Gemini)
Process: com.akslabs.SandeshVahak, PID: 13319
java.lang.RuntimeException: Unable to start service com.akslabs.SandeshVahak.services.SmsObserverService@d810f3d with Intent { act=ACTION_START_SERVICE xflg=0x4 cmp=com.akslabs.SandeshVahak/.services.SmsObserverService }: kotlin.UninitializedPropertyAccessException: lateinit property manager has not been initialized
at android.app.ActivityThread.handleServiceArgs(ActivityThread.java:5471)
at android.app.ActivityThread.-$$Nest$mhandleServiceArgs(Unknown Source:0)
at android.app.ActivityThread$H.handleMessage(ActivityThread.java:2647)
at android.os.Handler.dispatchMessage(Handler.java:110)
at android.os.Looper.loopOnce(Looper.java:248)
at android.os.Looper.loop(Looper.java:338)
at android.app.ActivityThread.main(ActivityThread.java:9073)
at java.lang.reflect.Method.invoke(Native Method)
at com.android.internal.os.RuntimeInit$MethodAndArgsCaller.run(RuntimeInit.java:596)
at com.android.internal.os.ZygoteInit.main(ZygoteInit.java:932)
Caused by: kotlin.UninitializedPropertyAccessException: lateinit property manager has not been initialized
at com.akslabs.SandeshVahak.workers.WorkModule$SmsSync.enqueueKeepAlive(WorkModule.kt:206)
at com.akslabs.SandeshVahak.services.SmsObserverService.updateSyncStateBasedOnPreferences(SmsObserverService.kt:200)
at com.akslabs.SandeshVahak.services.SmsObserverService.onStartCommand(SmsObserverService.kt:113)
at android.app.ActivityThread.handleServiceArgs(ActivityThread.java:5453)
... 9 more
2025-09-26 20:40:03.718 14607-14607 AndroidRuntime          pid-14607                            E  FATAL EXCEPTION: main (Ask Gemini)
Process: com.akslabs.SandeshVahak, PID: 14607
java.lang.RuntimeException: Unable to start service com.akslabs.SandeshVahak.services.SmsObserverService@740057b with Intent { act=ACTION_START_SERVICE xflg=0x4 cmp=com.akslabs.SandeshVahak/.services.SmsObserverService }: kotlin.UninitializedPropertyAccessException: lateinit property manager has not been initialized
at android.app.ActivityThread.handleServiceArgs(ActivityThread.java:5471)
at android.app.ActivityThread.-$$Nest$mhandleServiceArgs(Unknown Source:0)
at android.app.ActivityThread$H.handleMessage(ActivityThread.java:2647)
at android.os.Handler.dispatchMessage(Handler.java:110)
at android.os.Looper.loopOnce(Looper.java:248)
at android.os.Looper.loop(Looper.java:338)
at android.app.ActivityThread.main(ActivityThread.java:9073)
at java.lang.reflect.Method.invoke(Native Method)
at com.android.internal.os.RuntimeInit$MethodAndArgsCaller.run(RuntimeInit.java:596)
at com.android.internal.os.ZygoteInit.main(ZygoteInit.java:932)
Caused by: kotlin.UninitializedPropertyAccessException: lateinit property manager has not been initialized
at com.akslabs.SandeshVahak.workers.WorkModule$SmsSync.enqueueKeepAlive(WorkModule.kt:206)
at com.akslabs.SandeshVahak.services.SmsObserverService.updateSyncStateBasedOnPreferences(SmsObserverService.kt:200)
at com.akslabs.SandeshVahak.services.SmsObserverService.onStartCommand(SmsObserverService.kt:113)
at android.app.ActivityThread.handleServiceArgs(ActivityThread.java:5453)
... 9 more
2025-09-26 20:40:23.176 13320-15101 DisplayManager          com.akslabs.SandeshVahak             I  Choreographer implicitly registered for the refresh rate.
2025-09-26 20:57:30.599 13320-15108 SandeshVahakApp         com.akslabs.SandeshVahak             D  awaitCoreInitializations: Waiting for all core services...
2025-09-26 20:57:30.599 13320-15108 SandeshVahakApp         com.akslabs.SandeshVahak             D  awaitCoreInitializations: All core services signaled ready.
2025-09-26 20:57:30.615 13320-15108 SandeshVahakApp         com.akslabs.SandeshVahak             D  awaitCoreInitializations: Waiting for all core services...
2025-09-26 20:57:30.615 13320-15108 SandeshVahakApp         com.akslabs.SandeshVahak             D  awaitCoreInitializations: All core services signaled ready.
2025-09-26 20:57:30.617 13320-13320 MainActivity            com.akslabs.SandeshVahak             D  Core services ready. Proceeding with onResume logic.
2025-09-26 20:57:30.623 13320-13320 MainActivity            com.akslabs.SandeshVahak             D  Core services ready. Proceeding with onStart logic.
2025-09-26 20:57:30.637 13320-13320 SmsContentObserver      com.akslabs.SandeshVahak             D  Registering content observer on URI=content://sms, notifyForDescendants=true, thread=main
2025-09-26 20:57:30.641 13320-13320 SmsContentObserver      com.akslabs.SandeshVahak             I  ✅ SMS content observer started and registered
2025-09-26 20:57:30.722 13320-15108 SmsReaderService        com.akslabs.SandeshVahak             D  NEW_ONLY mode: using baseline timestamp for incremental sync
2025-09-26 20:57:30.722 13320-15108 SmsReaderService        com.akslabs.SandeshVahak             I  Starting incremental SMS sync (mode: NEW_ONLY) from timestamp: 1758893221185 (lastDB=1758893245615, baseline=1758893226185, buffer=5000)
2025-09-26 20:57:30.728 13320-15108 SmsReaderService        com.akslabs.SandeshVahak             D  Timestamp details: lastDB=2025-09-26 18:57:25.615, baseline=2025-09-26 18:57:06.185, effective=2025-09-26 18:57:01.185
2025-09-26 20:57:30.832 13320-15108 SmsReaderService        com.akslabs.SandeshVahak             I  Reading SMS messages after timestamp: 1758893221185 (2025-09-26 18:57:01.185)
2025-09-26 20:57:30.832 13320-15108 SmsReaderService        com.akslabs.SandeshVahak             D  Query: uri=content://sms, selection='date > ?', args=[1758893221185], sort=date ASC
2025-09-26 20:57:30.876 13320-15108 SmsReaderService        com.akslabs.SandeshVahak             D  Cursor is null? false
2025-09-26 20:57:30.877 13320-15108 SmsReaderService        com.akslabs.SandeshVahak             V  Row #1 -> id=72, date=1758893245615, addr=+919545154067
2025-09-26 20:57:30.878 13320-15108 SmsReaderService        com.akslabs.SandeshVahak             D  Total rows iterated: 1
2025-09-26 20:57:30.882 13320-15108 SmsReaderService        com.akslabs.SandeshVahak             I  Read 1 new SMS messages after timestamp
2025-09-26 20:57:30.887 13320-15108 SmsReaderService        com.akslabs.SandeshVahak             V  Evaluating message id=72, date=1758893245615 (2025-09-26 18:57:25.615), exists=true
2025-09-26 20:57:30.887 13320-15108 SmsReaderService        com.akslabs.SandeshVahak             D  Message 72 already exists in DB, skipping
2025-09-26 20:57:30.887 13320-15108 SmsReaderService        com.akslabs.SandeshVahak             I  Incremental SMS sync complete: 0 new messages (batch)
2025-09-26 20:57:30.992 13320-13320 InsetsController        com.akslabs.SandeshVahak             D  hide(ime(), fromIme=false)
2025-09-26 20:57:30.992 13320-13320 ImeTracker              com.akslabs.SandeshVahak             I  com.akslabs.SandeshVahak:65254414: onCancelled at PHASE_CLIENT_ALREADY_HIDDEN
2025-09-26 20:57:50.467  1970-2271  InputDispatcher         system_server                        E  channel '58f8b82 com.akslabs.SandeshVahak/com.akslabs.SandeshVahak.ui.MainActivity' ~ Channel is unrecoverably broken and will be disposed!
2025-09-26 20:57:52.956 14111-14111 bs.SandeshVahak         com.akslabs.SandeshVahak             I  Late-enabling -Xcheck:jni
2025-09-26 20:57:52.986 14111-14111 bs.SandeshVahak         com.akslabs.SandeshVahak             I  Using CollectorTypeCC GC.
2025-09-26 20:57:53.027 14111-14111 bs.SandeshVahak         com.akslabs.SandeshVahak             W  Thread Pool max thread count is 0. Cannot cache binder as linkToDeath cannot be implemented. serviceName: activity
2025-09-26 20:57:53.076 14111-14111 nativeloader            com.akslabs.SandeshVahak             D  Load libframework-connectivity-tiramisu-jni.so using APEX ns com_android_tethering for caller /apex/com.android.tethering/javalib/framework-connectivity-t.jar: ok
2025-09-26 20:57:53.108 14111-14111 re-initialized>         com.akslabs.SandeshVahak             W  type=1400 audit(0.0:613): avc:  granted  { execute } for  path="/data/data/com.akslabs.SandeshVahak/code_cache/startup_agents/297fa85a-agent.so" dev="mmcblk0p61" ino=745505 scontext=u:r:untrusted_app:s0:c197,c258,c512,c768 tcontext=u:object_r:app_data_file:s0:c197,c258,c512,c768 tclass=file app=com.akslabs.SandeshVahak
2025-09-26 20:57:53.124 14111-14111 nativeloader            com.akslabs.SandeshVahak             D  Load /data/user/0/com.akslabs.SandeshVahak/code_cache/startup_agents/297fa85a-agent.so using system ns (caller=<unknown>): ok
2025-09-26 20:57:53.147 14111-14111 bs.SandeshVahak         com.akslabs.SandeshVahak             W  hiddenapi: DexFile /data/data/com.akslabs.SandeshVahak/code_cache/.studio/instruments-9bcd9200.jar is in boot class path but is not in a known location
2025-09-26 20:57:53.294 14111-14111 bs.SandeshVahak         com.akslabs.SandeshVahak             W  Redefining intrinsic method java.lang.Thread java.lang.Thread.currentThread(). This may cause the unexpected use of the original definition of java.lang.Thread java.lang.Thread.currentThread()in methods that have already been compiled.
2025-09-26 20:57:53.294 14111-14111 bs.SandeshVahak         com.akslabs.SandeshVahak             W  Redefining intrinsic method boolean java.lang.Thread.interrupted(). This may cause the unexpected use of the original definition of boolean java.lang.Thread.interrupted()in methods that have already been compiled.
2025-09-26 20:57:53.324 14111-14111 ApplicationLoaders      com.akslabs.SandeshVahak             D  Returning zygote-cached class loader: /system_ext/framework/androidx.window.extensions.jar
2025-09-26 20:57:53.324 14111-14111 ApplicationLoaders      com.akslabs.SandeshVahak             D  Returning zygote-cached class loader: /system_ext/framework/androidx.window.sidecar.jar
2025-09-26 20:57:53.896 14111-14111 nativeloader            com.akslabs.SandeshVahak             D  Configuring clns-9 for other apk /data/app/~~L8G6B02wSNpoSKtWz0k4Lw==/com.akslabs.SandeshVahak-xwfmNs8RMjTWOCfqtdGo4g==/base.apk. target_sdk_version=35, uses_libraries=, library_path=/data/app/~~L8G6B02wSNpoSKtWz0k4Lw==/com.akslabs.SandeshVahak-xwfmNs8RMjTWOCfqtdGo4g==/lib/arm64:/data/app/~~L8G6B02wSNpoSKtWz0k4Lw==/com.akslabs.SandeshVahak-xwfmNs8RMjTWOCfqtdGo4g==/base.apk!/lib/arm64-v8a, permitted_path=/data:/mnt/expand:/data/user/0/com.akslabs.SandeshVahak
2025-09-26 20:57:53.901 14111-14111 CompatChangeReporter    com.akslabs.SandeshVahak             D  Compat change id reported: 202956589; UID 10709; state: ENABLED
2025-09-26 20:57:53.911 14111-14111 bs.SandeshVahak         com.akslabs.SandeshVahak             I  AssetManager2(0x7ec1422e28) locale list changing from [] to [en-IN]
2025-09-26 20:57:53.916 14111-14111 bs.SandeshVahak         com.akslabs.SandeshVahak             I  AssetManager2(0x7ec1423728) locale list changing from [] to [en-IN]
2025-09-26 20:57:53.936 14111-14111 GraphicsEnvironment     com.akslabs.SandeshVahak             V  Currently set values for:
2025-09-26 20:57:53.936 14111-14111 GraphicsEnvironment     com.akslabs.SandeshVahak             V    angle_gl_driver_selection_pkgs=[com.android.angle, com.linecorp.b612.android, com.campmobile.snow, com.google.android.apps.tachyon]
2025-09-26 20:57:53.936 14111-14111 GraphicsEnvironment     com.akslabs.SandeshVahak             V    angle_gl_driver_selection_values=[angle, native, native, native]
2025-09-26 20:57:53.936 14111-14111 GraphicsEnvironment     com.akslabs.SandeshVahak             V  com.akslabs.SandeshVahak is not listed in per-application setting
2025-09-26 20:57:53.937 14111-14111 GraphicsEnvironment     com.akslabs.SandeshVahak             V  ANGLE allowlist from config:
2025-09-26 20:57:53.937 14111-14111 GraphicsEnvironment     com.akslabs.SandeshVahak             V  com.akslabs.SandeshVahak is not listed in ANGLE allowlist or settings, returning default
2025-09-26 20:57:53.937 14111-14111 GraphicsEnvironment     com.akslabs.SandeshVahak             V  Neither updatable production driver nor prerelease driver is supported.
2025-09-26 20:57:54.030 14111-14111 CompatChangeReporter    com.akslabs.SandeshVahak             D  Compat change id reported: 279646685; UID 10709; state: ENABLED
2025-09-26 20:57:54.049 14111-14111 WM-WrkMgrInitializer    com.akslabs.SandeshVahak             D  Initializing WorkManager with default configuration.
2025-09-26 20:57:54.181 14111-14111 WM-PackageManagerHelper com.akslabs.SandeshVahak             D  Skipping component enablement for androidx.work.impl.background.systemjob.SystemJobService
2025-09-26 20:57:54.181 14111-14111 WM-Schedulers           com.akslabs.SandeshVahak             D  Created SystemJobScheduler and enabled SystemJobService
2025-09-26 20:57:54.205 14111-14111 SandeshVahakApp         com.akslabs.SandeshVahak             I  Application onCreate called
2025-09-26 20:57:54.206 14111-14111 SandeshVahakApp         com.akslabs.SandeshVahak             D  WorkModule initialized successfully (main thread)
2025-09-26 20:57:54.206 14111-14111 SandeshVahakApp         com.akslabs.SandeshVahak             I  Application main thread onCreate tasks (excluding background init logic) completed.
2025-09-26 20:57:54.207 14111-20708 SandeshVahakApp         com.akslabs.SandeshVahak             D  Core background initialization thread started.
2025-09-26 20:57:54.238 14111-20707 ashmem                  com.akslabs.SandeshVahak             E  Pinning is deprecated since Android Q. Please use trim or other methods.
2025-09-26 20:57:54.249 14111-14111 bs.SandeshVahak         com.akslabs.SandeshVahak             I  AssetManager2(0x7ec1423a28) locale list changing from [] to [en-IN]
2025-09-26 20:57:54.254 14111-20710 DisplayManager          com.akslabs.SandeshVahak             I  Choreographer implicitly registered for the refresh rate.
2025-09-26 20:57:54.264 14111-20710 AdrenoGLES-0            com.akslabs.SandeshVahak             I  QUALCOMM build                   : 95db91f, Ifbc588260a
Build Date                       : 09/24/20
OpenGL ES Shader Compiler Version: EV031.32.02.01
Local Branch                     : mybrancheafe5b6d-fb5b-f1b0-b904-5cb90179c3e0
Remote Branch                    : quic/gfx-adreno.lnx.1.0.r114-rel
Remote Branch                    : NONE
Reconstruct Branch               : NOTHING
2025-09-26 20:57:54.264 14111-20710 AdrenoGLES-0            com.akslabs.SandeshVahak             I  Build Config                     : S P 10.0.7 AArch64
2025-09-26 20:57:54.264 14111-20710 AdrenoGLES-0            com.akslabs.SandeshVahak             I  Driver Path                      : /vendor/lib64/egl/libGLESv2_adreno.so
2025-09-26 20:57:54.269 14111-20710 AdrenoGLES-0            com.akslabs.SandeshVahak             I  PFP: 0x016ee190, ME: 0x00000000
2025-09-26 20:57:54.365 14111-20707 CompatChangeReporter    com.akslabs.SandeshVahak             D  Compat change id reported: 311402873; UID 10709; state: ENABLED
2025-09-26 20:57:54.365 14111-20707 CompatChangeReporter    com.akslabs.SandeshVahak             D  Compat change id reported: 323349338; UID 10709; state: ENABLED
2025-09-26 20:57:54.456 14111-20714 SandeshVahakApp         com.akslabs.SandeshVahak             D  awaitCoreInitializations: Waiting for background core services...
2025-09-26 20:57:54.480 14111-14111 CompatChangeReporter    com.akslabs.SandeshVahak             D  Compat change id reported: 309578419; UID 10709; state: ENABLED
2025-09-26 20:57:54.483 14111-14111 DesktopModeFlags        com.akslabs.SandeshVahak             D  Toggle override initialized to: OVERRIDE_UNSET
2025-09-26 20:57:54.530 14111-20715 SandeshVahakApp         com.akslabs.SandeshVahak             D  awaitCoreInitializations: Waiting for background core services...
2025-09-26 20:57:54.559 14111-20713 SandeshVahakApp         com.akslabs.SandeshVahak             D  awaitCoreInitializations: Waiting for background core services...
2025-09-26 20:57:54.561 14111-14111 CompatChangeReporter    com.akslabs.SandeshVahak             D  Compat change id reported: 352594277; UID 10709; state: ENABLED
2025-09-26 20:57:54.575 14111-14111 CompatChangeReporter    com.akslabs.SandeshVahak             D  Compat change id reported: 349153669; UID 10709; state: ENABLED
2025-09-26 20:57:54.580 14111-20719 Gralloc4                com.akslabs.SandeshVahak             I  mapper 4.x is not supported
2025-09-26 20:57:54.581 14111-20719 Gralloc3                com.akslabs.SandeshVahak             W  mapper 3.x is not supported
2025-09-26 20:57:54.585 14111-20708 EngineFactory           com.akslabs.SandeshVahak             I  Provider GmsCore_OpenSSL not available
2025-09-26 20:57:54.594 14111-20708 SandeshVahakApp         com.akslabs.SandeshVahak             D  Preferences initialized successfully (background)
2025-09-26 20:57:54.607 14111-20708 SandeshVahakApp         com.akslabs.SandeshVahak             D  DbHolder.create() called (background)
2025-09-26 20:57:54.633 14111-20721 Gralloc2                com.akslabs.SandeshVahak             I  Adding additional valid usage bits: 0x8202000
2025-09-26 20:57:54.687 14111-14111 InsetsController        com.akslabs.SandeshVahak             D  hide(ime(), fromIme=false)
2025-09-26 20:57:54.688 14111-14111 ImeTracker              com.akslabs.SandeshVahak             I  com.akslabs.SandeshVahak:c3d371f: onCancelled at PHASE_CLIENT_ALREADY_HIDDEN
2025-09-26 20:57:54.798 14111-20708 SandeshVahakApp         com.akslabs.SandeshVahak             D  BotApi.create() called (background)
2025-09-26 20:57:54.798 14111-20708 SandeshVahakApp         com.akslabs.SandeshVahak             D  ConnectivityObserver initialized successfully (background)
2025-09-26 20:57:54.800 14111-20708 SandeshVahakApp         com.akslabs.SandeshVahak             D  Performance monitoring started (background)
2025-09-26 20:57:54.800 14111-20708 SandeshVahakApp         com.akslabs.SandeshVahak             I  All core background initializations completed successfully.
2025-09-26 20:57:54.800 14111-20708 SandeshVahakApp         com.akslabs.SandeshVahak             D  Core background initialization thread finished, latch counted down.
2025-09-26 20:57:54.800 14111-20714 SandeshVahakApp         com.akslabs.SandeshVahak             D  awaitCoreInitializations: Background core services signaled ready.
2025-09-26 20:57:54.800 14111-20715 SandeshVahakApp         com.akslabs.SandeshVahak             D  awaitCoreInitializations: Background core services signaled ready.
2025-09-26 20:57:54.801 14111-14111 MainActivity            com.akslabs.SandeshVahak             D  Core services ready. Proceeding with onCreate setup.
2025-09-26 20:57:54.802 14111-20713 SandeshVahakApp         com.akslabs.SandeshVahak             D  awaitCoreInitializations: Background core services signaled ready.
2025-09-26 20:57:54.805 14111-14111 NotificationHelper      com.akslabs.SandeshVahak             D  Notification channel SmsObserverServiceChannel created/ensured.
2025-09-26 20:57:54.811 14111-14111 NotificationHelper      com.akslabs.SandeshVahak             D  Notification channel PostBootNotificationChannel created/ensured.
2025-09-26 20:57:54.812 14111-14111 MainActivity            com.akslabs.SandeshVahak             D  Core services ready. Proceeding with onStart logic.
2025-09-26 20:57:54.814 14111-20717 DatabaseDebugHelper     com.akslabs.SandeshVahak             I  === DATABASE DEBUG REPORT ===
2025-09-26 20:57:54.815 14111-14111 SmsContentObserver      com.akslabs.SandeshVahak             D  Registering content observer on URI=content://sms, notifyForDescendants=true, thread=main
2025-09-26 20:57:54.818 14111-14111 SmsContentObserver      com.akslabs.SandeshVahak             I  ✅ SMS content observer started and registered
2025-09-26 20:57:54.818 14111-14111 MainActivity            com.akslabs.SandeshVahak             D  Core services ready. Proceeding with onResume logic.
2025-09-26 20:57:54.821 14111-14111 MainActivity            com.akslabs.SandeshVahak             D  onResume: startDestination updated to: main. NavHost should recompose if key is used or NavController handles state.
2025-09-26 20:57:54.835 14111-20717 DatabaseDebugHelper     com.akslabs.SandeshVahak             I  Database version: 7
2025-09-26 20:57:54.860 14111-20716 DatabaseDebugHelper     com.akslabs.SandeshVahak             I  Record counts:
2025-09-26 20:57:54.860 14111-20716 DatabaseDebugHelper     com.akslabs.SandeshVahak             I    Total SMS messages: 69
2025-09-26 20:57:54.860 14111-20716 DatabaseDebugHelper     com.akslabs.SandeshVahak             I    Synced SMS messages: 1
2025-09-26 20:57:54.860 14111-20716 DatabaseDebugHelper     com.akslabs.SandeshVahak             I    Total remote SMS messages: 1
2025-09-26 20:57:54.860 14111-20716 DatabaseDebugHelper     com.akslabs.SandeshVahak             I  Sample synced SMS messages:
2025-09-26 20:57:54.860 14111-20716 DatabaseDebugHelper     com.akslabs.SandeshVahak             I    SMS: id=72, remoteId=3358, address=+919545154067, syncedAt=1758893248152
2025-09-26 20:57:54.860 14111-20716 DatabaseDebugHelper     com.akslabs.SandeshVahak             I  Sample remote SMS messages:
2025-09-26 20:57:54.860 14111-20716 DatabaseDebugHelper     com.akslabs.SandeshVahak             I    RemoteSMS: remoteId=3358, originalId=72, address=+919545154067
2025-09-26 20:57:54.860 14111-20716 DatabaseDebugHelper     com.akslabs.SandeshVahak             I  === END DATABASE DEBUG REPORT ===
2025-09-26 20:57:54.878 14111-14111 MainActivity            com.akslabs.SandeshVahak             D  Initial startDestination set to: main
2025-09-26 20:57:55.250 14111-14111 bs.SandeshVahak         com.akslabs.SandeshVahak             W  Method boolean androidx.compose.runtime.snapshots.SnapshotStateList.conditionalUpdate(boolean, kotlin.jvm.functions.Function1) failed lock verification and will run slower.
Common causes for lock verification issues are non-optimized dex code
and incorrect proguard optimizations.
2025-09-26 20:57:55.250 14111-14111 bs.SandeshVahak         com.akslabs.SandeshVahak             W  Method boolean androidx.compose.runtime.snapshots.SnapshotStateList.conditionalUpdate$default(androidx.compose.runtime.snapshots.SnapshotStateList, boolean, kotlin.jvm.functions.Function1, int, java.lang.Object) failed lock verification and will run slower.
2025-09-26 20:57:55.250 14111-14111 bs.SandeshVahak         com.akslabs.SandeshVahak             W  Method java.lang.Object androidx.compose.runtime.snapshots.SnapshotStateList.mutate(kotlin.jvm.functions.Function1) failed lock verification and will run slower.
2025-09-26 20:57:55.250 14111-14111 bs.SandeshVahak         com.akslabs.SandeshVahak             W  Method void androidx.compose.runtime.snapshots.SnapshotStateList.update(boolean, kotlin.jvm.functions.Function1) failed lock verification and will run slower.
2025-09-26 20:57:55.250 14111-14111 bs.SandeshVahak         com.akslabs.SandeshVahak             W  Method void androidx.compose.runtime.snapshots.SnapshotStateList.update$default(androidx.compose.runtime.snapshots.SnapshotStateList, boolean, kotlin.jvm.functions.Function1, int, java.lang.Object) failed lock verification and will run slower.
2025-09-26 20:57:55.390 14111-20716 SmsReaderService        com.akslabs.SandeshVahak             D  NEW_ONLY mode: using baseline timestamp for incremental sync
2025-09-26 20:57:55.390 14111-20716 SmsReaderService        com.akslabs.SandeshVahak             I  Starting incremental SMS sync (mode: NEW_ONLY) from timestamp: 1758893221185 (lastDB=1758893245615, baseline=1758893226185, buffer=5000)
2025-09-26 20:57:55.438 14111-20716 SmsReaderService        com.akslabs.SandeshVahak             D  Timestamp details: lastDB=2025-09-26 18:57:25.615, baseline=2025-09-26 18:57:06.185, effective=2025-09-26 18:57:01.185
2025-09-26 20:57:55.558 14111-20716 SmsReaderService        com.akslabs.SandeshVahak             I  Reading SMS messages after timestamp: 1758893221185 (2025-09-26 18:57:01.185)
2025-09-26 20:57:55.558 14111-20716 SmsReaderService        com.akslabs.SandeshVahak             D  Query: uri=content://sms, selection='date > ?', args=[1758893221185], sort=date ASC
2025-09-26 20:57:55.570 14111-14111 LocalSmsScreen_Diag     com.akslabs.SandeshVahak             D  LocalSmsScreen composed for test. Total count: 0, Item count: 0
2025-09-26 20:57:55.581 14111-14111 AppNavHost              com.akslabs.SandeshVahak             D  Local count recomposed: 0
2025-09-26 20:57:55.582 14111-20716 SmsReaderService        com.akslabs.SandeshVahak             D  Cursor is null? false
2025-09-26 20:57:55.583 14111-20716 SmsReaderService        com.akslabs.SandeshVahak             V  Row #1 -> id=72, date=1758893245615, addr=+919545154067
2025-09-26 20:57:55.583 14111-20716 SmsReaderService        com.akslabs.SandeshVahak             D  Total rows iterated: 1
2025-09-26 20:57:55.586 14111-20716 SmsReaderService        com.akslabs.SandeshVahak             I  Read 1 new SMS messages after timestamp
2025-09-26 20:57:55.591 14111-20716 SmsReaderService        com.akslabs.SandeshVahak             V  Evaluating message id=72, date=1758893245615 (2025-09-26 18:57:25.615), exists=true
2025-09-26 20:57:55.591 14111-20716 SmsReaderService        com.akslabs.SandeshVahak             D  Message 72 already exists in DB, skipping
2025-09-26 20:57:55.591 14111-20716 SmsReaderService        com.akslabs.SandeshVahak             I  Incremental SMS sync complete: 0 new messages (batch)
2025-09-26 20:57:55.831 14111-14111 LocalSmsScreen_Diag     com.akslabs.SandeshVahak             D  LocalSmsScreen composed for test. Total count: 69, Item count: 60
2025-09-26 20:57:55.833 14111-14111 AppNavHost              com.akslabs.SandeshVahak             D  Local count recomposed: 69
2025-09-26 20:58:00.127 14111-20748 ProfileInstaller        com.akslabs.SandeshVahak             D  Installing profile for com.akslabs.SandeshVahak
2025-09-26 20:58:30.032 14111-20684 bs.SandeshVahak         com.akslabs.SandeshVahak             I  Background concurrent copying GC freed 5241KB AllocSpace bytes, 18(616KB) LOS objects, 66% free, 5277KB/15MB, paused 148us,42us total 107.483ms
2025-09-26 20:58:55.908 14111-14111 MainActivity            com.akslabs.SandeshVahak             D  Core services were initialized. Proceeding with onStop logic.
2025-09-26 20:58:55.909 14111-14111 MainActivity            com.akslabs.SandeshVahak             I  onStop: SMS Sync is enabled, SmsContentObserver will continue running.
2025-09-26 20:59:02.580 15069-15069 bs.SandeshVahak         com.akslabs.SandeshVahak             I  Late-enabling -Xcheck:jni
2025-09-26 20:59:02.610 15069-15069 bs.SandeshVahak         com.akslabs.SandeshVahak             I  Using CollectorTypeCC GC.
2025-09-26 20:59:02.642 15069-15069 bs.SandeshVahak         com.akslabs.SandeshVahak             W  Thread Pool max thread count is 0. Cannot cache binder as linkToDeath cannot be implemented. serviceName: activity
2025-09-26 20:59:02.677 15069-15069 nativeloader            com.akslabs.SandeshVahak             D  Load libframework-connectivity-tiramisu-jni.so using APEX ns com_android_tethering for caller /apex/com.android.tethering/javalib/framework-connectivity-t.jar: ok
2025-09-26 20:59:02.698 15069-15069 re-initialized>         com.akslabs.SandeshVahak             W  type=1400 audit(0.0:643): avc:  granted  { execute } for  path="/data/data/com.akslabs.SandeshVahak/code_cache/startup_agents/297fa85a-agent.so" dev="mmcblk0p61" ino=745505 scontext=u:r:untrusted_app:s0:c197,c258,c512,c768 tcontext=u:object_r:app_data_file:s0:c197,c258,c512,c768 tclass=file app=com.akslabs.SandeshVahak
2025-09-26 20:59:02.715 15069-15069 nativeloader            com.akslabs.SandeshVahak             D  Load /data/user/0/com.akslabs.SandeshVahak/code_cache/startup_agents/297fa85a-agent.so using system ns (caller=<unknown>): ok
2025-09-26 20:59:02.731 15069-15069 bs.SandeshVahak         com.akslabs.SandeshVahak             W  hiddenapi: DexFile /data/data/com.akslabs.SandeshVahak/code_cache/.studio/instruments-9bcd9200.jar is in boot class path but is not in a known location
2025-09-26 20:59:02.893 15069-15069 bs.SandeshVahak         com.akslabs.SandeshVahak             W  Redefining intrinsic method java.lang.Thread java.lang.Thread.currentThread(). This may cause the unexpected use of the original definition of java.lang.Thread java.lang.Thread.currentThread()in methods that have already been compiled.
2025-09-26 20:59:02.893 15069-15069 bs.SandeshVahak         com.akslabs.SandeshVahak             W  Redefining intrinsic method boolean java.lang.Thread.interrupted(). This may cause the unexpected use of the original definition of boolean java.lang.Thread.interrupted()in methods that have already been compiled.
2025-09-26 20:59:02.925 15069-15069 ApplicationLoaders      com.akslabs.SandeshVahak             D  Returning zygote-cached class loader: /system_ext/framework/androidx.window.extensions.jar
2025-09-26 20:59:02.925 15069-15069 ApplicationLoaders      com.akslabs.SandeshVahak             D  Returning zygote-cached class loader: /system_ext/framework/androidx.window.sidecar.jar
2025-09-26 20:59:03.338 15069-15069 nativeloader            com.akslabs.SandeshVahak             D  Configuring clns-9 for other apk /data/app/~~L8G6B02wSNpoSKtWz0k4Lw==/com.akslabs.SandeshVahak-xwfmNs8RMjTWOCfqtdGo4g==/base.apk. target_sdk_version=35, uses_libraries=, library_path=/data/app/~~L8G6B02wSNpoSKtWz0k4Lw==/com.akslabs.SandeshVahak-xwfmNs8RMjTWOCfqtdGo4g==/lib/arm64:/data/app/~~L8G6B02wSNpoSKtWz0k4Lw==/com.akslabs.SandeshVahak-xwfmNs8RMjTWOCfqtdGo4g==/base.apk!/lib/arm64-v8a, permitted_path=/data:/mnt/expand:/data/user/0/com.akslabs.SandeshVahak
2025-09-26 20:59:03.341 15069-15069 CompatChangeReporter    com.akslabs.SandeshVahak             D  Compat change id reported: 202956589; UID 10709; state: ENABLED
2025-09-26 20:59:03.344 15069-15069 bs.SandeshVahak         com.akslabs.SandeshVahak             I  AssetManager2(0x7ec1422e28) locale list changing from [] to [en-IN]
2025-09-26 20:59:03.346 15069-15069 bs.SandeshVahak         com.akslabs.SandeshVahak             I  AssetManager2(0x7ec1423728) locale list changing from [] to [en-IN]
2025-09-26 20:59:03.352 15069-15069 GraphicsEnvironment     com.akslabs.SandeshVahak             V  Currently set values for:
2025-09-26 20:59:03.352 15069-15069 GraphicsEnvironment     com.akslabs.SandeshVahak             V    angle_gl_driver_selection_pkgs=[com.android.angle, com.linecorp.b612.android, com.campmobile.snow, com.google.android.apps.tachyon]
2025-09-26 20:59:03.352 15069-15069 GraphicsEnvironment     com.akslabs.SandeshVahak             V    angle_gl_driver_selection_values=[angle, native, native, native]
2025-09-26 20:59:03.352 15069-15069 GraphicsEnvironment     com.akslabs.SandeshVahak             V  com.akslabs.SandeshVahak is not listed in per-application setting
2025-09-26 20:59:03.352 15069-15069 GraphicsEnvironment     com.akslabs.SandeshVahak             V  ANGLE allowlist from config:
2025-09-26 20:59:03.352 15069-15069 GraphicsEnvironment     com.akslabs.SandeshVahak             V  com.akslabs.SandeshVahak is not listed in ANGLE allowlist or settings, returning default
2025-09-26 20:59:03.352 15069-15069 GraphicsEnvironment     com.akslabs.SandeshVahak             V  Neither updatable production driver nor prerelease driver is supported.
2025-09-26 20:59:03.415 15069-15069 CompatChangeReporter    com.akslabs.SandeshVahak             D  Compat change id reported: 279646685; UID 10709; state: ENABLED
2025-09-26 20:59:03.427 15069-15069 WM-WrkMgrInitializer    com.akslabs.SandeshVahak             D  Initializing WorkManager with default configuration.
2025-09-26 20:59:03.478 15069-15069 WM-PackageManagerHelper com.akslabs.SandeshVahak             D  Skipping component enablement for androidx.work.impl.background.systemjob.SystemJobService
2025-09-26 20:59:03.479 15069-15069 WM-Schedulers           com.akslabs.SandeshVahak             D  Created SystemJobScheduler and enabled SystemJobService
2025-09-26 20:59:03.491 15069-15069 SandeshVahakApp         com.akslabs.SandeshVahak             I  Application onCreate called
2025-09-26 20:59:03.492 15069-15069 SandeshVahakApp         com.akslabs.SandeshVahak             D  WorkModule initialized successfully (main thread)
2025-09-26 20:59:03.492 15069-15069 SandeshVahakApp         com.akslabs.SandeshVahak             I  Application main thread onCreate tasks (excluding background init logic) completed.
2025-09-26 20:59:03.493 15069-21184 SandeshVahakApp         com.akslabs.SandeshVahak             D  Core background initialization thread started.
2025-09-26 20:59:03.514 15069-21184 ashmem                  com.akslabs.SandeshVahak             E  Pinning is deprecated since Android Q. Please use trim or other methods.
2025-09-26 20:59:03.517 15069-21186 DisplayManager          com.akslabs.SandeshVahak             I  Choreographer implicitly registered for the refresh rate.
2025-09-26 20:59:03.520 15069-15069 bs.SandeshVahak         com.akslabs.SandeshVahak             I  AssetManager2(0x7ec1423a28) locale list changing from [] to [en-IN]
2025-09-26 20:59:03.521 15069-21186 AdrenoGLES-0            com.akslabs.SandeshVahak             I  QUALCOMM build                   : 95db91f, Ifbc588260a
Build Date                       : 09/24/20
OpenGL ES Shader Compiler Version: EV031.32.02.01
Local Branch                     : mybrancheafe5b6d-fb5b-f1b0-b904-5cb90179c3e0
Remote Branch                    : quic/gfx-adreno.lnx.1.0.r114-rel
Remote Branch                    : NONE
Reconstruct Branch               : NOTHING
2025-09-26 20:59:03.521 15069-21186 AdrenoGLES-0            com.akslabs.SandeshVahak             I  Build Config                     : S P 10.0.7 AArch64
2025-09-26 20:59:03.521 15069-21186 AdrenoGLES-0            com.akslabs.SandeshVahak             I  Driver Path                      : /vendor/lib64/egl/libGLESv2_adreno.so
2025-09-26 20:59:03.529 15069-21186 AdrenoGLES-0            com.akslabs.SandeshVahak             I  PFP: 0x016ee190, ME: 0x00000000
2025-09-26 20:59:03.618 15069-21183 CompatChangeReporter    com.akslabs.SandeshVahak             D  Compat change id reported: 311402873; UID 10709; state: ENABLED
2025-09-26 20:59:03.618 15069-21183 CompatChangeReporter    com.akslabs.SandeshVahak             D  Compat change id reported: 323349338; UID 10709; state: ENABLED
2025-09-26 20:59:03.632 15069-21190 SandeshVahakApp         com.akslabs.SandeshVahak             D  awaitCoreInitializations: Waiting for background core services...
2025-09-26 20:59:03.644 15069-15069 CompatChangeReporter    com.akslabs.SandeshVahak             D  Compat change id reported: 309578419; UID 10709; state: ENABLED
2025-09-26 20:59:03.647 15069-15069 DesktopModeFlags        com.akslabs.SandeshVahak             D  Toggle override initialized to: OVERRIDE_UNSET
2025-09-26 20:59:03.670 15069-21192 SandeshVahakApp         com.akslabs.SandeshVahak             D  awaitCoreInitializations: Waiting for background core services...
2025-09-26 20:59:03.682 15069-21191 SandeshVahakApp         com.akslabs.SandeshVahak             D  awaitCoreInitializations: Waiting for background core services...
2025-09-26 20:59:03.684 15069-15069 CompatChangeReporter    com.akslabs.SandeshVahak             D  Compat change id reported: 352594277; UID 10709; state: ENABLED
2025-09-26 20:59:03.694 15069-15069 CompatChangeReporter    com.akslabs.SandeshVahak             D  Compat change id reported: 349153669; UID 10709; state: ENABLED
2025-09-26 20:59:03.697 15069-21198 Gralloc4                com.akslabs.SandeshVahak             I  mapper 4.x is not supported
2025-09-26 20:59:03.699 15069-21198 Gralloc3                com.akslabs.SandeshVahak             W  mapper 3.x is not supported
2025-09-26 20:59:03.745 15069-21199 Gralloc2                com.akslabs.SandeshVahak             I  Adding additional valid usage bits: 0x8202000
2025-09-26 20:59:03.768 15069-21184 EngineFactory           com.akslabs.SandeshVahak             I  Provider GmsCore_OpenSSL not available
2025-09-26 20:59:03.775 15069-21184 SandeshVahakApp         com.akslabs.SandeshVahak             D  Preferences initialized successfully (background)
2025-09-26 20:59:03.786 15069-21184 SandeshVahakApp         com.akslabs.SandeshVahak             D  DbHolder.create() called (background)
2025-09-26 20:59:03.787 15069-15069 InsetsController        com.akslabs.SandeshVahak             D  hide(ime(), fromIme=false)
2025-09-26 20:59:03.788 15069-15069 ImeTracker              com.akslabs.SandeshVahak             I  com.akslabs.SandeshVahak:bd605b70: onCancelled at PHASE_CLIENT_ALREADY_HIDDEN
2025-09-26 20:59:03.938 15069-21184 SandeshVahakApp         com.akslabs.SandeshVahak             D  BotApi.create() called (background)
2025-09-26 20:59:03.939 15069-21184 SandeshVahakApp         com.akslabs.SandeshVahak             D  ConnectivityObserver initialized successfully (background)
2025-09-26 20:59:03.940 15069-21184 SandeshVahakApp         com.akslabs.SandeshVahak             D  Performance monitoring started (background)
2025-09-26 20:59:03.940 15069-21184 SandeshVahakApp         com.akslabs.SandeshVahak             I  All core background initializations completed successfully.
2025-09-26 20:59:03.940 15069-21184 SandeshVahakApp         com.akslabs.SandeshVahak             D  Core background initialization thread finished, latch counted down.
2025-09-26 20:59:03.942 15069-21190 SandeshVahakApp         com.akslabs.SandeshVahak             D  awaitCoreInitializations: Background core services signaled ready.
2025-09-26 20:59:03.942 15069-21191 SandeshVahakApp         com.akslabs.SandeshVahak             D  awaitCoreInitializations: Background core services signaled ready.
2025-09-26 20:59:03.942 15069-21192 SandeshVahakApp         com.akslabs.SandeshVahak             D  awaitCoreInitializations: Background core services signaled ready.
2025-09-26 20:59:03.946 15069-15069 MainActivity            com.akslabs.SandeshVahak             D  Core services ready. Proceeding with onResume logic.
2025-09-26 20:59:03.947 15069-15069 MainActivity            com.akslabs.SandeshVahak             D  Core services ready. Proceeding with onStart logic.
2025-09-26 20:59:03.950 15069-15069 SmsContentObserver      com.akslabs.SandeshVahak             D  Registering content observer on URI=content://sms, notifyForDescendants=true, thread=main
2025-09-26 20:59:03.953 15069-15069 SmsContentObserver      com.akslabs.SandeshVahak             I  ✅ SMS content observer started and registered
2025-09-26 20:59:03.958 15069-15069 MainActivity            com.akslabs.SandeshVahak             D  onResume: startDestination updated to: main. NavHost should recompose if key is used or NavController handles state.
2025-09-26 20:59:03.971 15069-15069 MainActivity            com.akslabs.SandeshVahak             D  Core services ready. Proceeding with onCreate setup.
2025-09-26 20:59:03.977 15069-15069 NotificationHelper      com.akslabs.SandeshVahak             D  Notification channel SmsObserverServiceChannel created/ensured.
2025-09-26 20:59:03.979 15069-15069 NotificationHelper      com.akslabs.SandeshVahak             D  Notification channel PostBootNotificationChannel created/ensured.
2025-09-26 20:59:03.981 15069-21190 DatabaseDebugHelper     com.akslabs.SandeshVahak             I  === DATABASE DEBUG REPORT ===
2025-09-26 20:59:04.002 15069-21190 DatabaseDebugHelper     com.akslabs.SandeshVahak             I  Database version: 7
2025-09-26 20:59:04.020 15069-21190 DatabaseDebugHelper     com.akslabs.SandeshVahak             I  Record counts:
2025-09-26 20:59:04.020 15069-21190 DatabaseDebugHelper     com.akslabs.SandeshVahak             I    Total SMS messages: 69
2025-09-26 20:59:04.020 15069-21190 DatabaseDebugHelper     com.akslabs.SandeshVahak             I    Synced SMS messages: 1
2025-09-26 20:59:04.020 15069-21190 DatabaseDebugHelper     com.akslabs.SandeshVahak             I    Total remote SMS messages: 1
2025-09-26 20:59:04.020 15069-21190 DatabaseDebugHelper     com.akslabs.SandeshVahak             I  Sample synced SMS messages:
2025-09-26 20:59:04.020 15069-21190 DatabaseDebugHelper     com.akslabs.SandeshVahak             I    SMS: id=72, remoteId=3358, address=+919545154067, syncedAt=1758893248152
2025-09-26 20:59:04.020 15069-21190 DatabaseDebugHelper     com.akslabs.SandeshVahak             I  Sample remote SMS messages:
2025-09-26 20:59:04.020 15069-21190 DatabaseDebugHelper     com.akslabs.SandeshVahak             I    RemoteSMS: remoteId=3358, originalId=72, address=+919545154067
2025-09-26 20:59:04.020 15069-21190 DatabaseDebugHelper     com.akslabs.SandeshVahak             I  === END DATABASE DEBUG REPORT ===
2025-09-26 20:59:04.021 15069-21190 SmsReaderService        com.akslabs.SandeshVahak             D  NEW_ONLY mode: using baseline timestamp for incremental sync
2025-09-26 20:59:04.021 15069-21190 SmsReaderService        com.akslabs.SandeshVahak             I  Starting incremental SMS sync (mode: NEW_ONLY) from timestamp: 1758893221185 (lastDB=1758893245615, baseline=1758893226185, buffer=5000)
2025-09-26 20:59:04.036 15069-15069 MainActivity            com.akslabs.SandeshVahak             D  Initial startDestination set to: main
2025-09-26 20:59:04.052 15069-21190 SmsReaderService        com.akslabs.SandeshVahak             D  Timestamp details: lastDB=2025-09-26 18:57:25.615, baseline=2025-09-26 18:57:06.185, effective=2025-09-26 18:57:01.185
2025-09-26 20:59:04.162 15069-21190 SmsReaderService        com.akslabs.SandeshVahak             I  Reading SMS messages after timestamp: 1758893221185 (2025-09-26 18:57:01.185)
2025-09-26 20:59:04.162 15069-21190 SmsReaderService        com.akslabs.SandeshVahak             D  Query: uri=content://sms, selection='date > ?', args=[1758893221185], sort=date ASC
2025-09-26 20:59:04.197 15069-21190 SmsReaderService        com.akslabs.SandeshVahak             D  Cursor is null? false
2025-09-26 20:59:04.197 15069-21190 SmsReaderService        com.akslabs.SandeshVahak             V  Row #1 -> id=72, date=1758893245615, addr=+919545154067
2025-09-26 20:59:04.198 15069-21190 SmsReaderService        com.akslabs.SandeshVahak             D  Total rows iterated: 1
2025-09-26 20:59:04.200 15069-21190 SmsReaderService        com.akslabs.SandeshVahak             I  Read 1 new SMS messages after timestamp
2025-09-26 20:59:04.210 15069-21190 SmsReaderService        com.akslabs.SandeshVahak             V  Evaluating message id=72, date=1758893245615 (2025-09-26 18:57:25.615), exists=true
2025-09-26 20:59:04.210 15069-21190 SmsReaderService        com.akslabs.SandeshVahak             D  Message 72 already exists in DB, skipping
2025-09-26 20:59:04.211 15069-21190 SmsReaderService        com.akslabs.SandeshVahak             I  Incremental SMS sync complete: 0 new messages (batch)
2025-09-26 20:59:04.370 15069-15069 bs.SandeshVahak         com.akslabs.SandeshVahak             W  Method boolean androidx.compose.runtime.snapshots.SnapshotStateList.conditionalUpdate(boolean, kotlin.jvm.functions.Function1) failed lock verification and will run slower.
Common causes for lock verification issues are non-optimized dex code
and incorrect proguard optimizations.
2025-09-26 20:59:04.370 15069-15069 bs.SandeshVahak         com.akslabs.SandeshVahak             W  Method boolean androidx.compose.runtime.snapshots.SnapshotStateList.conditionalUpdate$default(androidx.compose.runtime.snapshots.SnapshotStateList, boolean, kotlin.jvm.functions.Function1, int, java.lang.Object) failed lock verification and will run slower.
2025-09-26 20:59:04.370 15069-15069 bs.SandeshVahak         com.akslabs.SandeshVahak             W  Method java.lang.Object androidx.compose.runtime.snapshots.SnapshotStateList.mutate(kotlin.jvm.functions.Function1) failed lock verification and will run slower.
2025-09-26 20:59:04.370 15069-15069 bs.SandeshVahak         com.akslabs.SandeshVahak             W  Method void androidx.compose.runtime.snapshots.SnapshotStateList.update(boolean, kotlin.jvm.functions.Function1) failed lock verification and will run slower.
2025-09-26 20:59:04.370 15069-15069 bs.SandeshVahak         com.akslabs.SandeshVahak             W  Method void androidx.compose.runtime.snapshots.SnapshotStateList.update$default(androidx.compose.runtime.snapshots.SnapshotStateList, boolean, kotlin.jvm.functions.Function1, int, java.lang.Object) failed lock verification and will run slower.
2025-09-26 20:59:04.682 15069-15069 LocalSmsScreen_Diag     com.akslabs.SandeshVahak             D  LocalSmsScreen composed for test. Total count: 0, Item count: 0
2025-09-26 20:59:04.694 15069-15069 AppNavHost              com.akslabs.SandeshVahak             D  Local count recomposed: 0
2025-09-26 20:59:04.833 15069-15069 LocalSmsScreen_Diag     com.akslabs.SandeshVahak             D  LocalSmsScreen composed for test. Total count: 69, Item count: 0
2025-09-26 20:59:04.877 15069-15069 AppNavHost              com.akslabs.SandeshVahak             D  Local count recomposed: 69
2025-09-26 20:59:04.941 15069-15069 LocalSmsScreen_Diag     com.akslabs.SandeshVahak             D  LocalSmsScreen composed for test. Total count: 69, Item count: 60
2025-09-26 20:59:08.445 15069-15069 LocalSmsScreen_Diag     com.akslabs.SandeshVahak             D  LocalSmsScreen composed for test. Total count: 69, Item count: 60
2025-09-26 20:59:08.490 15069-15069 AppNavHost              com.akslabs.SandeshVahak             D  Local count recomposed: 69
2025-09-26 20:59:08.554 15069-15069 LocalSmsScreen_Diag     com.akslabs.SandeshVahak             D  LocalSmsScreen composed for test. Total count: 69, Item count: 60
2025-09-26 20:59:08.592 15069-15069 AppNavHost              com.akslabs.SandeshVahak             D  Local count recomposed: 69
2025-09-26 20:59:08.894 15069-21218 ProfileInstaller        com.akslabs.SandeshVahak             D  Installing profile for com.akslabs.SandeshVahak
2025-09-26 20:59:10.014 15069-15069 WindowOnBackDispatcher  com.akslabs.SandeshVahak             W  sendCancelIfRunning: isInProgress=false callback=androidx.activity.OnBackPressedDispatcher$Api34Impl$createOnBackAnimationCallback$1@cae682
2025-09-26 20:59:10.042 15069-15069 LocalSmsScreen_Diag     com.akslabs.SandeshVahak             D  LocalSmsScreen composed for test. Total count: 0, Item count: 60
2025-09-26 20:59:10.061 15069-15069 AppNavHost              com.akslabs.SandeshVahak             D  Local count recomposed: 0
2025-09-26 20:59:10.148 15069-15069 LocalSmsScreen_Diag     com.akslabs.SandeshVahak             D  LocalSmsScreen composed for test. Total count: 69, Item count: 60
2025-09-26 20:59:10.150 15069-15069 AppNavHost              com.akslabs.SandeshVahak             D  Local count recomposed: 69
2025-09-26 20:59:10.369 15069-15069 LocalSmsScreen_Diag     com.akslabs.SandeshVahak             D  LocalSmsScreen composed for test. Total count: 69, Item count: 60
2025-09-26 20:59:10.394 15069-15069 AppNavHost              com.akslabs.SandeshVahak             D  Local count recomposed: 69
2025-09-26 20:59:10.414 15069-15069 LocalSmsScreen_Diag     com.akslabs.SandeshVahak             D  LocalSmsScreen composed for test. Total count: 69, Item count: 60
2025-09-26 20:59:10.418 15069-15069 AppNavHost              com.akslabs.SandeshVahak             D  Local count recomposed: 69
