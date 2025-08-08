--------- beginning of crash
2025-08-08 09:57:34.661 15667-15667 AndroidRuntime          pid-15667                            E  FATAL EXCEPTION: main
                                                                                                    Process: com.akslabs.Suchak, PID: 15667
                                                                                                    java.lang.RuntimeException: Unable to create service com.akslabs.chitralaya.services.SmsObserverService: kotlin.UninitializedPropertyAccessException: lateinit property preferences has not been initialized
                                                                                                    	at android.app.ActivityThread.handleCreateService(ActivityThread.java:5071)
                                                                                                    	at android.app.ActivityThread.-$$Nest$mhandleCreateService(Unknown Source:0)
                                                                                                    	at android.app.ActivityThread$H.handleMessage(ActivityThread.java:2496)
                                                                                                    	at android.os.Handler.dispatchMessage(Handler.java:109)
                                                                                                    	at android.os.Looper.loopOnce(Looper.java:232)
                                                                                                    	at android.os.Looper.loop(Looper.java:317)
                                                                                                    	at android.app.ActivityThread.main(ActivityThread.java:8782)
                                                                                                    	at java.lang.reflect.Method.invoke(Native Method)
                                                                                                    	at com.android.internal.os.RuntimeInit$MethodAndArgsCaller.run(RuntimeInit.java:595)
                                                                                                    	at com.android.internal.os.ZygoteInit.main(ZygoteInit.java:871)
                                                                                                    Caused by: kotlin.UninitializedPropertyAccessException: lateinit property preferences has not been initialized
                                                                                                    	at com.akslabs.SandeshVahak.data.localdb.Preferences.getBoolean(Preferences.kt:54)
                                                                                                    	at com.akslabs.chitralaya.services.SmsObserverService.onCreate(SmsObserverService.kt:26)
                                                                                                    	at android.app.ActivityThread.handleCreateService(ActivityThread.java:5058)
                                                                                                    	at android.app.ActivityThread.-$$Nest$mhandleCreateService(Unknown Source:0) 
                                                                                                    	at android.app.ActivityThread$H.handleMessage(ActivityThread.java:2496) 
                                                                                                    	at android.os.Handler.dispatchMessage(Handler.java:109) 
                                                                                                    	at android.os.Looper.loopOnce(Looper.java:232) 
                                                                                                    	at android.os.Looper.loop(Looper.java:317) 
                                                                                                    	at android.app.ActivityThread.main(ActivityThread.java:8782) 
                                                                                                    	at java.lang.reflect.Method.invoke(Native Method) 
                                                                                                    	at com.android.internal.os.RuntimeInit$MethodAndArgsCaller.run(RuntimeInit.java:595) 
                                                                                                    	at com.android.internal.os.ZygoteInit.main(ZygoteInit.java:871) 
--------- beginning of system
2025-08-08 09:57:42.161 15842-15842 AndroidRuntime          pid-15842                            E  FATAL EXCEPTION: main
                                                                                                    Process: com.akslabs.Suchak, PID: 15842
                                                                                                    java.lang.RuntimeException: Unable to create service com.akslabs.chitralaya.services.SmsObserverService: kotlin.UninitializedPropertyAccessException: lateinit property preferences has not been initialized
                                                                                                    	at android.app.ActivityThread.handleCreateService(ActivityThread.java:5071)
                                                                                                    	at android.app.ActivityThread.-$$Nest$mhandleCreateService(Unknown Source:0)
                                                                                                    	at android.app.ActivityThread$H.handleMessage(ActivityThread.java:2496)
                                                                                                    	at android.os.Handler.dispatchMessage(Handler.java:109)
                                                                                                    	at android.os.Looper.loopOnce(Looper.java:232)
                                                                                                    	at android.os.Looper.loop(Looper.java:317)
                                                                                                    	at android.app.ActivityThread.main(ActivityThread.java:8782)
                                                                                                    	at java.lang.reflect.Method.invoke(Native Method)
                                                                                                    	at com.android.internal.os.RuntimeInit$MethodAndArgsCaller.run(RuntimeInit.java:595)
                                                                                                    	at com.android.internal.os.ZygoteInit.main(ZygoteInit.java:871)
                                                                                                    Caused by: kotlin.UninitializedPropertyAccessException: lateinit property preferences has not been initialized
                                                                                                    	at com.akslabs.SandeshVahak.data.localdb.Preferences.getBoolean(Preferences.kt:54)
                                                                                                    	at com.akslabs.chitralaya.services.SmsObserverService.onCreate(SmsObserverService.kt:26)
                                                                                                    	at android.app.ActivityThread.handleCreateService(ActivityThread.java:5058)
                                                                                                    	at android.app.ActivityThread.-$$Nest$mhandleCreateService(Unknown Source:0) 
                                                                                                    	at android.app.ActivityThread$H.handleMessage(ActivityThread.java:2496) 
                                                                                                    	at android.os.Handler.dispatchMessage(Handler.java:109) 
                                                                                                    	at android.os.Looper.loopOnce(Looper.java:232) 
                                                                                                    	at android.os.Looper.loop(Looper.java:317) 
                                                                                                    	at android.app.ActivityThread.main(ActivityThread.java:8782) 
                                                                                                    	at java.lang.reflect.Method.invoke(Native Method) 
                                                                                                    	at com.android.internal.os.RuntimeInit$MethodAndArgsCaller.run(RuntimeInit.java:595) 
                                                                                                    	at com.android.internal.os.ZygoteInit.main(ZygoteInit.java:871) 
2025-08-08 10:11:10.305  1882-2022  VerityUtils             system_server                        E  Failed to check whether fs-verity is enabled, errno 38: /data/app/~~_yshFgze50LC_BIEwu6onw==/com.akslabs.SandeshVahak-mOsMXocaPS8nudBudMm3QQ==/base.apk
2025-08-08 10:11:35.640  1882-2022  VerityUtils             system_server                        E  Failed to check whether fs-verity is enabled, errno 38: /data/app/~~QHz8P4gWz_fCMf2csvuyvA==/com.akslabs.SandeshVahak-YpAiObI4CRcZUO0pjajFSw==/base.apk
2025-08-08 10:11:35.959  1882-2022  VerityUtils             system_server                        E  Failed to check whether fs-verity is enabled, errno 38: /data/app/~~QHz8P4gWz_fCMf2csvuyvA==/com.akslabs.SandeshVahak-YpAiObI4CRcZUO0pjajFSw==/base.apk
2025-08-08 10:11:49.381  1882-2022  VerityUtils             system_server                        E  Failed to check whether fs-verity is enabled, errno 38: /data/app/~~v7lJE-HkWlIhlYA8t6LL9w==/com.akslabs.SandeshVahak-JA4k20bXeD4xOEuaHc3Keg==/base.apk
2025-08-08 10:11:49.872  1882-2022  VerityUtils             system_server                        E  Failed to check whether fs-verity is enabled, errno 38: /data/app/~~v7lJE-HkWlIhlYA8t6LL9w==/com.akslabs.SandeshVahak-JA4k20bXeD4xOEuaHc3Keg==/base.apk
2025-08-08 10:12:37.790  1882-2022  VerityUtils             system_server                        E  Failed to check whether fs-verity is enabled, errno 38: /data/app/~~aoJRmOVeNy02m3a5Fu8c0A==/com.akslabs.SandeshVahak-U1YWr9JNWH4B2LA-YUpwKQ==/base.apk
2025-08-08 10:12:38.206  1882-2022  VerityUtils             system_server                        E  Failed to check whether fs-verity is enabled, errno 38: /data/app/~~aoJRmOVeNy02m3a5Fu8c0A==/com.akslabs.SandeshVahak-U1YWr9JNWH4B2LA-YUpwKQ==/base.apk
--------- beginning of main
2025-08-08 10:39:15.837 22624-22656 PerformanceMonitor      com.akslabs.SandeshVahak             I  📊 === PERFORMANCE SUMMARY ===
2025-08-08 10:39:15.837 22624-22656 PerformanceMonitor      com.akslabs.SandeshVahak             I  📊 === END PERFORMANCE SUMMARY ===
2025-08-08 10:41:49.384 22624-22624 SmsObserverService      com.akslabs.SandeshVahak             I  SMS Observer Service created
2025-08-08 10:41:49.389 22624-22624 SmsContentObserver      com.akslabs.SandeshVahak             I  SMS content observer started
2025-08-08 10:41:49.390 22624-22624 SmsObserverService      com.akslabs.SandeshVahak             I  SMS Observer Service started
2025-08-08 10:41:49.460 22624-22624 WindowOnBackDispatcher  com.akslabs.SandeshVahak             W  sendCancelIfRunning: isInProgress=false callback=androidx.activity.OnBackPressedDispatcher$Api34Impl$createOnBackAnimationCallback$1@207d858
2025-08-08 10:41:53.452 22624-22624 Choreographer           com.akslabs.SandeshVahak             I  Skipped 42 frames!  The application may be doing too much work on its main thread.
2025-08-08 10:41:53.453 22624-22669 HWUI                    com.akslabs.SandeshVahak             I  Davey! duration=833ms; Flags=0, FrameTimelineVsyncId=132909976, IntendedVsync=208410541477151, Vsync=208410658143596, InputEventId=0, HandleInputStart=208410659381954, AnimationStart=208410659384922, PerformTraversalsStart=208411020744401, DrawStart=208411020854349, FrameDeadline=208410946144699, FrameInterval=208410658849401, FrameStartTime=16666747, SyncQueued=208411367086432, SyncStart=208411367191797, IssueDrawCommandsStart=208411367721589, SwapBuffers=208411368959193, FrameCompleted=208411375263880, DequeueBufferDuration=29896, QueueBufferDuration=533229, GpuCompleted=208411375263880, SwapBuffersCompleted=208411370203307, DisplayPresentTime=0, CommandSubmissionCompleted=208411368959193, 
2025-08-08 10:41:53.472 22624-22656 BackupHelper            com.akslabs.SandeshVahak             D  Backup status - Has backup: false, Data unchanged: true
2025-08-08 10:41:53.472 22624-22656 BackupHelper            com.akslabs.SandeshVahak             D  Current: 0 SMS, 0 remote | Last backup: 0 SMS, 0 remote
2025-08-08 10:41:53.501 22624-22636 HWUI                    com.akslabs.SandeshVahak             I  Davey! duration=743ms; Flags=0, FrameTimelineVsyncId=132910111, IntendedVsync=208410674812356, Vsync=208411374811572, InputEventId=0, HandleInputStart=208411383149401, AnimationStart=208411383152162, PerformTraversalsStart=208411406422891, DrawStart=208411406513828, FrameDeadline=208411412813796, FrameInterval=208411382723047, FrameStartTime=16666747, SyncQueued=208411412705547, SyncStart=208411412840495, IssueDrawCommandsStart=208411412984818, SwapBuffers=208411416285235, FrameCompleted=208411418838151, DequeueBufferDuration=16407, QueueBufferDuration=299791, GpuCompleted=208411418838151, SwapBuffersCompleted=208411417206224, DisplayPresentTime=0, CommandSubmissionCompleted=208411416285235, 
2025-08-08 10:41:55.092 22624-22624 Choreographer           com.akslabs.SandeshVahak             I  Skipped 31 frames!  The application may be doing too much work on its main thread.
2025-08-08 10:41:55.117 22624-22655 BackupHelper            com.akslabs.SandeshVahak             D  Backup status - Has backup: false, Data unchanged: true
2025-08-08 10:41:55.118 22624-22655 BackupHelper            com.akslabs.SandeshVahak             D  Current: 0 SMS, 0 remote | Last backup: 0 SMS, 0 remote
2025-08-08 10:41:56.765 22624-22656 BackupHelper            com.akslabs.SandeshVahak             D  Backup status - Has backup: false, Data unchanged: true
2025-08-08 10:41:56.765 22624-22656 BackupHelper            com.akslabs.SandeshVahak             D  Current: 0 SMS, 0 remote | Last backup: 0 SMS, 0 remote
2025-08-08 10:42:09.627 22624-22624 WindowOnBackDispatcher  com.akslabs.SandeshVahak             W  sendCancelIfRunning: isInProgress=false callback=androidx.activity.OnBackPressedDispatcher$Api34Impl$createOnBackAnimationCallback$1@fe43edd
2025-08-08 10:42:10.167 22624-22624 WindowOnBackDispatcher  com.akslabs.SandeshVahak             W  sendCancelIfRunning: isInProgress=false callback=android.app.Activity$$ExternalSyntheticLambda0@144e196
2025-08-08 10:42:26.804 22624-22656 DatabaseDebugHelper     com.akslabs.SandeshVahak             I  === DATABASE DEBUG REPORT ===
2025-08-08 10:42:26.805 22624-22656 DatabaseDebugHelper     com.akslabs.SandeshVahak             I  Database version: 7
2025-08-08 10:42:26.812 22624-22656 DatabaseDebugHelper     com.akslabs.SandeshVahak             I  Record counts:
2025-08-08 10:42:26.812 22624-22656 DatabaseDebugHelper     com.akslabs.SandeshVahak             I    Total SMS messages: 0
2025-08-08 10:42:26.812 22624-22656 DatabaseDebugHelper     com.akslabs.SandeshVahak             I    Synced SMS messages: 0
2025-08-08 10:42:26.812 22624-22656 DatabaseDebugHelper     com.akslabs.SandeshVahak             I    Total remote SMS messages: 0
2025-08-08 10:42:26.812 22624-22656 DatabaseDebugHelper     com.akslabs.SandeshVahak             I  === END DATABASE DEBUG REPORT ===
2025-08-08 10:42:29.542 22624-22624 VRI[MainActivity]       com.akslabs.SandeshVahak             D  visibilityChanged oldVisibility=true newVisibility=false
2025-08-08 10:42:29.566 22624-22624 WindowOnBackDispatcher  com.akslabs.SandeshVahak             W  sendCancelIfRunning: isInProgress=false callback=androidx.activity.OnBackPressedDispatcher$Api34Impl$createOnBackAnimationCallback$1@f8b74da
2025-08-08 10:42:39.263 22624-22624 WindowOnBackDispatcher  com.akslabs.SandeshVahak             W  sendCancelIfRunning: isInProgress=false callback=androidx.activity.OnBackPressedDispatcher$Api34Impl$createOnBackAnimationCallback$1@f8b74da
2025-08-08 10:42:39.964 22624-22624 WindowOnBackDispatcher  com.akslabs.SandeshVahak             W  sendCancelIfRunning: isInProgress=false callback=android.app.Activity$$ExternalSyntheticLambda0@ca44e92
---------------------------- PROCESS STARTED (24705) for package com.akslabs.SandeshVahak ----------------------------
2025-08-08 10:43:14.164  1882-2022  VerityUtils             system_server                        E  Failed to check whether fs-verity is enabled, errno 38: /data/app/~~NHc6WR1irh4P9p10QSseGA==/com.akslabs.SandeshVahak-Vl_mCswhzeMh7m3J-wSfDA==/base.apk
2025-08-08 10:43:14.247 24705-24705 ApplicationLoaders      com.akslabs.SandeshVahak             D  Returning zygote-cached class loader: /system_ext/framework/androidx.window.extensions.jar
2025-08-08 10:43:14.247 24705-24705 ApplicationLoaders      com.akslabs.SandeshVahak             D  Returning zygote-cached class loader: /system_ext/framework/androidx.window.sidecar.jar
2025-08-08 10:43:14.301 24705-24705 ziparchive              com.akslabs.SandeshVahak             W  Unable to open '/data/app/~~NHc6WR1irh4P9p10QSseGA==/com.akslabs.SandeshVahak-Vl_mCswhzeMh7m3J-wSfDA==/base.dm': No such file or directory
2025-08-08 10:43:14.301 24705-24705 ziparchive              com.akslabs.SandeshVahak             W  Unable to open '/data/app/~~NHc6WR1irh4P9p10QSseGA==/com.akslabs.SandeshVahak-Vl_mCswhzeMh7m3J-wSfDA==/base.dm': No such file or directory
2025-08-08 10:43:14.874 24705-24705 nativeloader            com.akslabs.SandeshVahak             D  Configuring clns-7 for other apk /data/app/~~NHc6WR1irh4P9p10QSseGA==/com.akslabs.SandeshVahak-Vl_mCswhzeMh7m3J-wSfDA==/base.apk. target_sdk_version=34, uses_libraries=, library_path=/data/app/~~NHc6WR1irh4P9p10QSseGA==/com.akslabs.SandeshVahak-Vl_mCswhzeMh7m3J-wSfDA==/lib/arm64:/data/app/~~NHc6WR1irh4P9p10QSseGA==/com.akslabs.SandeshVahak-Vl_mCswhzeMh7m3J-wSfDA==/base.apk!/lib/arm64-v8a, permitted_path=/data:/mnt/expand:/data/user/0/com.akslabs.SandeshVahak
2025-08-08 10:43:15.015 24705-24705 GraphicsEnvironment     com.akslabs.SandeshVahak             V  Currently set values for:
2025-08-08 10:43:15.015 24705-24705 GraphicsEnvironment     com.akslabs.SandeshVahak             V    angle_gl_driver_selection_pkgs=[com.android.angle, com.linecorp.b612.android, com.campmobile.snow, com.google.android.apps.tachyon]
2025-08-08 10:43:15.015 24705-24705 GraphicsEnvironment     com.akslabs.SandeshVahak             V    angle_gl_driver_selection_values=[angle, native, native, native]
2025-08-08 10:43:15.015 24705-24705 GraphicsEnvironment     com.akslabs.SandeshVahak             V  com.akslabs.SandeshVahak is not listed in per-application setting
2025-08-08 10:43:15.016 24705-24705 GraphicsEnvironment     com.akslabs.SandeshVahak             V  Neither updatable production driver nor prerelease driver is supported.
2025-08-08 10:43:15.160 24705-24705 WM-WrkMgrInitializer    com.akslabs.SandeshVahak             D  Initializing WorkManager with default configuration.
2025-08-08 10:43:15.253 24705-24705 WM-PackageManagerHelper com.akslabs.SandeshVahak             D  androidx.work.impl.background.systemjob.SystemJobService enabled
2025-08-08 10:43:15.254 24705-24705 WM-Schedulers           com.akslabs.SandeshVahak             D  Created SystemJobScheduler and enabled SystemJobService
2025-08-08 10:43:15.792 24705-24705 AndroidKeysetManager    com.akslabs.SandeshVahak             W  keyset not found, will generate a new one
                                                                                                    java.io.FileNotFoundException: can't read keyset; the pref value __androidx_security_crypto_encrypted_prefs_key_keyset__ does not exist
                                                                                                    	at com.google.crypto.tink.integration.android.SharedPrefKeysetReader.readPref(SharedPrefKeysetReader.java:71)
                                                                                                    	at com.google.crypto.tink.integration.android.SharedPrefKeysetReader.readEncrypted(SharedPrefKeysetReader.java:89)
                                                                                                    	at com.google.crypto.tink.KeysetHandle.read(KeysetHandle.java:105)
                                                                                                    	at com.google.crypto.tink.integration.android.AndroidKeysetManager$Builder.read(AndroidKeysetManager.java:311)
                                                                                                    	at com.google.crypto.tink.integration.android.AndroidKeysetManager$Builder.readOrGenerateNewKeyset(AndroidKeysetManager.java:287)
                                                                                                    	at com.google.crypto.tink.integration.android.AndroidKeysetManager$Builder.build(AndroidKeysetManager.java:238)
                                                                                                    	at androidx.security.crypto.EncryptedSharedPreferences.create(EncryptedSharedPreferences.java:123)
                                                                                                    	at com.akslabs.SandeshVahak.data.localdb.Preferences.init(Preferences.kt:41)
                                                                                                    	at com.akslabs.chitralaya.App.onCreate(App.kt:19)
                                                                                                    	at android.app.Instrumentation.callApplicationOnCreate(Instrumentation.java:1390)
                                                                                                    	at android.app.ActivityThread.handleBindApplication(ActivityThread.java:7586)
                                                                                                    	at android.app.ActivityThread.-$$Nest$mhandleBindApplication(Unknown Source:0)
                                                                                                    	at android.app.ActivityThread$H.handleMessage(ActivityThread.java:2449)
                                                                                                    	at android.os.Handler.dispatchMessage(Handler.java:109)
                                                                                                    	at android.os.Looper.loopOnce(Looper.java:232)
                                                                                                    	at android.os.Looper.loop(Looper.java:317)
                                                                                                    	at android.app.ActivityThread.main(ActivityThread.java:8782)
                                                                                                    	at java.lang.reflect.Method.invoke(Native Method)
                                                                                                    	at com.android.internal.os.RuntimeInit$MethodAndArgsCaller.run(RuntimeInit.java:595)
                                                                                                    	at com.android.internal.os.ZygoteInit.main(ZygoteInit.java:871)
2025-08-08 10:43:15.917 24705-24705 AndroidKeysetManager    com.akslabs.SandeshVahak             W  keyset not found, will generate a new one
                                                                                                    java.io.FileNotFoundException: can't read keyset; the pref value __androidx_security_crypto_encrypted_prefs_value_keyset__ does not exist
                                                                                                    	at com.google.crypto.tink.integration.android.SharedPrefKeysetReader.readPref(SharedPrefKeysetReader.java:71)
                                                                                                    	at com.google.crypto.tink.integration.android.SharedPrefKeysetReader.readEncrypted(SharedPrefKeysetReader.java:89)
                                                                                                    	at com.google.crypto.tink.KeysetHandle.read(KeysetHandle.java:105)
                                                                                                    	at com.google.crypto.tink.integration.android.AndroidKeysetManager$Builder.read(AndroidKeysetManager.java:311)
                                                                                                    	at com.google.crypto.tink.integration.android.AndroidKeysetManager$Builder.readOrGenerateNewKeyset(AndroidKeysetManager.java:287)
                                                                                                    	at com.google.crypto.tink.integration.android.AndroidKeysetManager$Builder.build(AndroidKeysetManager.java:238)
                                                                                                    	at androidx.security.crypto.EncryptedSharedPreferences.create(EncryptedSharedPreferences.java:128)
                                                                                                    	at com.akslabs.SandeshVahak.data.localdb.Preferences.init(Preferences.kt:41)
                                                                                                    	at com.akslabs.chitralaya.App.onCreate(App.kt:19)
                                                                                                    	at android.app.Instrumentation.callApplicationOnCreate(Instrumentation.java:1390)
                                                                                                    	at android.app.ActivityThread.handleBindApplication(ActivityThread.java:7586)
                                                                                                    	at android.app.ActivityThread.-$$Nest$mhandleBindApplication(Unknown Source:0)
                                                                                                    	at android.app.ActivityThread$H.handleMessage(ActivityThread.java:2449)
                                                                                                    	at android.os.Handler.dispatchMessage(Handler.java:109)
                                                                                                    	at android.os.Looper.loopOnce(Looper.java:232)
                                                                                                    	at android.os.Looper.loop(Looper.java:317)
                                                                                                    	at android.app.ActivityThread.main(ActivityThread.java:8782)
                                                                                                    	at java.lang.reflect.Method.invoke(Native Method)
                                                                                                    	at com.android.internal.os.RuntimeInit$MethodAndArgsCaller.run(RuntimeInit.java:595)
                                                                                                    	at com.android.internal.os.ZygoteInit.main(ZygoteInit.java:871)
2025-08-08 10:43:15.972 24705-24705 EngineFactory           com.akslabs.SandeshVahak             I  Provider GmsCore_OpenSSL not available
2025-08-08 10:43:16.434 24705-24705 Choreographer           com.akslabs.SandeshVahak             I  Skipped 69 frames!  The application may be doing too much work on its main thread.
2025-08-08 10:43:16.652 24705-24749 DatabaseDebugHelper     com.akslabs.SandeshVahak             I  === DATABASE DEBUG REPORT ===
2025-08-08 10:43:16.739 24705-24705 DesktopModeFlagsUtil    com.akslabs.SandeshVahak             D  Toggle override initialized to: OVERRIDE_UNSET
2025-08-08 10:43:16.807 24705-24749 DatabaseDebugHelper     com.akslabs.SandeshVahak             I  Database version: 7
2025-08-08 10:43:16.843 24705-24749 DatabaseDebugHelper     com.akslabs.SandeshVahak             I  Record counts:
2025-08-08 10:43:16.843 24705-24749 DatabaseDebugHelper     com.akslabs.SandeshVahak             I    Total SMS messages: 0
2025-08-08 10:43:16.843 24705-24749 DatabaseDebugHelper     com.akslabs.SandeshVahak             I    Synced SMS messages: 0
2025-08-08 10:43:16.843 24705-24749 DatabaseDebugHelper     com.akslabs.SandeshVahak             I    Total remote SMS messages: 0
2025-08-08 10:43:16.843 24705-24749 DatabaseDebugHelper     com.akslabs.SandeshVahak             I  === END DATABASE DEBUG REPORT ===
2025-08-08 10:43:17.545 24705-24714 bs.SandeshVahak         com.akslabs.SandeshVahak             I  Compiler allocated 4431KB to compile void android.view.ViewRootImpl.performTraversals()
2025-08-08 10:43:17.787 24705-24705 bs.SandeshVahak         com.akslabs.SandeshVahak             W  Method boolean androidx.compose.runtime.snapshots.SnapshotStateList.conditionalUpdate(boolean, kotlin.jvm.functions.Function1) failed lock verification and will run slower.
                                                                                                    Common causes for lock verification issues are non-optimized dex code
                                                                                                    and incorrect proguard optimizations.
2025-08-08 10:43:17.787 24705-24705 bs.SandeshVahak         com.akslabs.SandeshVahak             W  Method boolean androidx.compose.runtime.snapshots.SnapshotStateList.conditionalUpdate$default(androidx.compose.runtime.snapshots.SnapshotStateList, boolean, kotlin.jvm.functions.Function1, int, java.lang.Object) failed lock verification and will run slower.
2025-08-08 10:43:17.787 24705-24705 bs.SandeshVahak         com.akslabs.SandeshVahak             W  Method java.lang.Object androidx.compose.runtime.snapshots.SnapshotStateList.mutate(kotlin.jvm.functions.Function1) failed lock verification and will run slower.
2025-08-08 10:43:17.788 24705-24705 bs.SandeshVahak         com.akslabs.SandeshVahak             W  Method void androidx.compose.runtime.snapshots.SnapshotStateList.update(boolean, kotlin.jvm.functions.Function1) failed lock verification and will run slower.
2025-08-08 10:43:17.788 24705-24705 bs.SandeshVahak         com.akslabs.SandeshVahak             W  Method void androidx.compose.runtime.snapshots.SnapshotStateList.update$default(androidx.compose.runtime.snapshots.SnapshotStateList, boolean, kotlin.jvm.functions.Function1, int, java.lang.Object) failed lock verification and will run slower.
2025-08-08 10:43:18.669 24705-24752 AdrenoGLES-0            com.akslabs.SandeshVahak             I  QUALCOMM build                   : 95db91f, Ifbc588260a
                                                                                                    Build Date                       : 09/24/20
                                                                                                    OpenGL ES Shader Compiler Version: EV031.32.02.01
                                                                                                    Local Branch                     : mybrancheafe5b6d-fb5b-f1b0-b904-5cb90179c3e0
                                                                                                    Remote Branch                    : quic/gfx-adreno.lnx.1.0.r114-rel
                                                                                                    Remote Branch                    : NONE
                                                                                                    Reconstruct Branch               : NOTHING
2025-08-08 10:43:18.669 24705-24752 AdrenoGLES-0            com.akslabs.SandeshVahak             I  Build Config                     : S P 10.0.7 AArch64
2025-08-08 10:43:18.669 24705-24752 AdrenoGLES-0            com.akslabs.SandeshVahak             I  Driver Path                      : /vendor/lib64/egl/libGLESv2_adreno.so
2025-08-08 10:43:18.691 24705-24752 AdrenoGLES-0            com.akslabs.SandeshVahak             I  PFP: 0x016ee190, ME: 0x00000000
2025-08-08 10:43:18.790 24705-24764 Gralloc4                com.akslabs.SandeshVahak             I  mapper 4.x is not supported
2025-08-08 10:43:18.791 24705-24764 Gralloc3                com.akslabs.SandeshVahak             W  mapper 3.x is not supported
2025-08-08 10:43:18.804 24705-24764 Gralloc2                com.akslabs.SandeshVahak             I  Adding additional valid usage bits: 0x8202000
2025-08-08 10:43:18.955 24705-24705 Choreographer           com.akslabs.SandeshVahak             I  Skipped 113 frames!  The application may be doing too much work on its main thread.
2025-08-08 10:43:18.963 24705-24728 HWUI                    com.akslabs.SandeshVahak             I  Davey! duration=2004ms; Flags=1, FrameTimelineVsyncId=132994149, IntendedVsync=208494858159026, Vsync=208494858159026, InputEventId=0, HandleInputStart=208494869949682, AnimationStart=208494869952026, PerformTraversalsStart=208494869953171, DrawStart=208496704972546, FrameDeadline=208494879492358, FrameInterval=208494869941036, FrameStartTime=16666677, SyncQueued=208496778964994, SyncStart=208496780229785, IssueDrawCommandsStart=208496781233327, SwapBuffers=208496857385566, FrameCompleted=208496863977650, DequeueBufferDuration=18854, QueueBufferDuration=684063, GpuCompleted=208496863977650, SwapBuffersCompleted=208496859624681, DisplayPresentTime=0, CommandSubmissionCompleted=208496857385566, 
2025-08-08 10:43:22.059 24705-24785 ProfileInstaller        com.akslabs.SandeshVahak             D  Installing profile for com.akslabs.SandeshVahak
2025-08-08 10:43:24.365 24705-24717 bs.SandeshVahak         com.akslabs.SandeshVahak             I  Background concurrent copying GC freed 5208KB AllocSpace bytes, 20(784KB) LOS objects, 49% free, 5746KB/11MB, paused 76us,61us total 125.289ms
2025-08-08 10:43:29.454 24705-24728 HWUI                    com.akslabs.SandeshVahak             I  Davey! duration=749ms; Flags=0, FrameTimelineVsyncId=133005048, IntendedVsync=208506624819777, Vsync=208506624819777, InputEventId=40177616, HandleInputStart=208506640631813, AnimationStart=208506640676865, PerformTraversalsStart=208506702250302, DrawStart=208507314430927, FrameDeadline=208506729486418, FrameInterval=208506640622542, FrameStartTime=16666661, SyncQueued=208507363903687, SyncStart=208507364023167, IssueDrawCommandsStart=208507364248323, SwapBuffers=208507365725458, FrameCompleted=208507374069417, DequeueBufferDuration=20000, QueueBufferDuration=272761, GpuCompleted=208507374069417, SwapBuffersCompleted=208507366376135, DisplayPresentTime=0, CommandSubmissionCompleted=208507365725458, 
2025-08-08 10:43:29.458 24705-24705 Choreographer           com.akslabs.SandeshVahak             I  Skipped 41 frames!  The application may be doing too much work on its main thread.
2025-08-08 10:43:32.695 24705-24705 InsetsController        com.akslabs.SandeshVahak             D  show(ime(), fromIme=false)
2025-08-08 10:43:32.697 24705-24705 ImeTracker              com.akslabs.SandeshVahak             I  com.akslabs.SandeshVahak:1cc08428: onRequestShow at ORIGIN_CLIENT reason SHOW_SOFT_INPUT_BY_INSETS_API fromUser false
2025-08-08 10:43:32.702 24705-24705 InputMethodManager      com.akslabs.SandeshVahak             D  showSoftInput() view=androidx.compose.ui.platform.AndroidComposeView{8fd4e07 VFED..... .F....ID 0,0-1080,2263 aid=1073741824} flags=0 reason=SHOW_SOFT_INPUT_BY_INSETS_API
2025-08-08 10:43:32.755 24705-24705 ImeTracker              com.akslabs.SandeshVahak             I  com.akslabs.SandeshVahak:73373d48: onRequestShow at ORIGIN_CLIENT reason SHOW_SOFT_INPUT fromUser false
2025-08-08 10:43:32.755 24705-24705 InputMethodManager      com.akslabs.SandeshVahak             D  showSoftInput() view=androidx.compose.ui.platform.AndroidComposeView{8fd4e07 VFED..... .F...... 0,0-1080,2263 aid=1073741824} flags=0 reason=SHOW_SOFT_INPUT
2025-08-08 10:43:33.049 24705-24705 InsetsController        com.akslabs.SandeshVahak             D  show(ime(), fromIme=true)
2025-08-08 10:43:33.060 24705-24705 InsetsController        com.akslabs.SandeshVahak             D  show(ime(), fromIme=true)
2025-08-08 10:43:33.060 24705-24705 ImeTracker              com.akslabs.SandeshVahak             I  com.akslabs.SandeshVahak:73373d48: onCancelled at PHASE_CLIENT_APPLY_ANIMATION
2025-08-08 10:43:33.097 24705-24824 InteractionJankMonitor  com.akslabs.SandeshVahak             W  Initializing without READ_DEVICE_CONFIG permission. enabled=false, interval=1, missedFrameThreshold=3, frameTimeThreshold=64, package=com.akslabs.SandeshVahak
2025-08-08 10:43:33.099 24705-24824 InteractionJankMonitor  com.akslabs.SandeshVahak             D  Build configuration failed!
                                                                                                    java.lang.IllegalArgumentException: Must pass in a valid surface control if only instrument surface; 
                                                                                                    	at com.android.internal.jank.InteractionJankMonitor$Configuration.validate(InteractionJankMonitor.java:909)
                                                                                                    	at com.android.internal.jank.InteractionJankMonitor$Configuration.<init>(InteractionJankMonitor.java:845)
                                                                                                    	at com.android.internal.jank.InteractionJankMonitor$Configuration.<init>(Unknown Source:0)
                                                                                                    	at com.android.internal.jank.InteractionJankMonitor$Configuration$Builder.build(InteractionJankMonitor.java:815)
                                                                                                    	at com.android.internal.jank.InteractionJankMonitor.begin(InteractionJankMonitor.java:377)
                                                                                                    	at android.view.inputmethod.ImeTracker$ImeJankTracker.onRequestAnimation(ImeTracker.java:927)
                                                                                                    	at android.view.InsetsController$InternalAnimationControlListener$1.onAnimationStart(InsetsController.java:441)
                                                                                                    	at android.animation.Animator$AnimatorListener.onAnimationStart(Animator.java:692)
                                                                                                    	at android.animation.Animator$AnimatorCaller$$ExternalSyntheticLambda0.call(D8$$SyntheticClass:0)
                                                                                                    	at android.animation.Animator.callOnList(Animator.java:666)
                                                                                                    	at android.animation.Animator.notifyListeners(Animator.java:609)
                                                                                                    	at android.animation.Animator.notifyStartListeners(Animator.java:626)
                                                                                                    	at android.animation.ValueAnimator.startAnimation(ValueAnimator.java:1334)
                                                                                                    	at android.animation.ValueAnimator.start(ValueAnimator.java:1149)
                                                                                                    	at android.animation.ValueAnimator.start(ValueAnimator.java:1173)
                                                                                                    	at android.view.InsetsController$InternalAnimationControlListener.onReady(InsetsController.java:460)
                                                                                                    	at android.view.InsetsAnimationThreadControlRunner.lambda$new$0(InsetsAnimationThreadControlRunner.java:127)
                                                                                                    	at android.view.InsetsAnimationThreadControlRunner.$r8$lambda$DLuG7Ht_vy5T5uYr29Rzhu2CZeY(Unknown Source:0)
                                                                                                    	at android.view.InsetsAnimationThreadControlRunner$$ExternalSyntheticLambda0.run(D8$$SyntheticClass:0)
                                                                                                    	at android.os.Handler.handleCallback(Handler.java:991)
                                                                                                    	at android.os.Handler.dispatchMessage(Handler.java:102)
                                                                                                    	at android.os.Looper.loopOnce(Looper.java:232)
                                                                                                    	at android.os.Looper.loop(Looper.java:317)
                                                                                                    	at android.os.HandlerThread.run(HandlerThread.java:85)
2025-08-08 10:43:33.300 24705-24705 ImeTracker              com.akslabs.SandeshVahak             I  com.akslabs.SandeshVahak:1cc08428: onShown
2025-08-08 10:43:39.359 24705-24705 InsetsController        com.akslabs.SandeshVahak             D  show(ime(), fromIme=false)
2025-08-08 10:43:39.360 24705-24705 ImeTracker              com.akslabs.SandeshVahak             I  com.akslabs.SandeshVahak:9a78baa5: onRequestShow at ORIGIN_CLIENT reason SHOW_SOFT_INPUT_BY_INSETS_API fromUser false
2025-08-08 10:43:39.360 24705-24705 ImeTracker              com.akslabs.SandeshVahak             I  com.akslabs.SandeshVahak:9a78baa5: onCancelled at PHASE_CLIENT_APPLY_ANIMATION
2025-08-08 10:43:39.463 24705-24705 ImeTracker              com.akslabs.SandeshVahak             I  com.akslabs.SandeshVahak:87ba9522: onRequestShow at ORIGIN_CLIENT reason SHOW_SOFT_INPUT fromUser false
2025-08-08 10:43:39.463 24705-24705 InputMethodManager      com.akslabs.SandeshVahak             D  showSoftInput() view=androidx.compose.ui.platform.AndroidComposeView{8fd4e07 VFED..... .F...... 0,0-1080,2263 aid=1073741824} flags=0 reason=SHOW_SOFT_INPUT
2025-08-08 10:43:39.721 24705-24705 InsetsController        com.akslabs.SandeshVahak             D  show(ime(), fromIme=true)
2025-08-08 10:43:39.721 24705-24705 ImeTracker              com.akslabs.SandeshVahak             I  com.akslabs.SandeshVahak:bb4ae537: onCancelled at PHASE_CLIENT_APPLY_ANIMATION
2025-08-08 10:43:39.724 24705-24705 InsetsController        com.akslabs.SandeshVahak             D  show(ime(), fromIme=true)
2025-08-08 10:43:39.724 24705-24705 ImeTracker              com.akslabs.SandeshVahak             I  com.akslabs.SandeshVahak:87ba9522: onCancelled at PHASE_CLIENT_APPLY_ANIMATION
2025-08-08 10:43:47.376 24705-24705 GettingStartedScreen    com.akslabs.SandeshVahak             I  Validating chat ID: -1002651869724
2025-08-08 10:43:47.379 24705-24917 BotApi                  com.akslabs.SandeshVahak             I  Attempting to get chat info for: Id(id=-1002651869724)
2025-08-08 10:43:48.523 24705-24917 BotApi                  com.akslabs.SandeshVahak             I  getChat result - isSuccess: false
2025-08-08 10:43:48.523 24705-24917 BotApi                  com.akslabs.SandeshVahak             W  getChat failed - result: Unknown(exception=com.google.gson.JsonSyntaxException: java.lang.IllegalStateException: Expected a string but was BEGIN_OBJECT at line 1 column 944 path $.result.pinned_message)
2025-08-08 10:43:48.524 24705-24917 BotApi                  com.akslabs.SandeshVahak             W  🔧 Known Telegram API issue: pinned_message format changed. Trying alternative validation...
2025-08-08 10:43:48.524 24705-24917 BotApi                  com.akslabs.SandeshVahak             I  🔧 Validating chat access using alternative method (due to Telegram API JSON issue)...
2025-08-08 10:43:48.524 24705-24917 BotApi                  com.akslabs.SandeshVahak             W  ⚠️ Skipping test message due to known Telegram Bot API library issue
2025-08-08 10:43:48.524 24705-24917 BotApi                  com.akslabs.SandeshVahak             W  📝 The pinned_message JSON parsing error doesn't affect actual message sending
2025-08-08 10:43:48.524 24705-24917 BotApi                  com.akslabs.SandeshVahak             I  ✅ Assuming chat is accessible - SMS sync should work normally
2025-08-08 10:43:49.115 24705-24705 WindowOnBackDispatcher  com.akslabs.SandeshVahak             W  sendCancelIfRunning: isInProgress=false callback=ImeCallback=ImeOnBackInvokedCallback@218750357 Callback=android.window.IOnBackInvokedCallback$Stub$Proxy@c71052e
2025-08-08 10:43:49.174 24705-24705 WindowOnBackDispatcher  com.akslabs.SandeshVahak             W  sendCancelIfRunning: isInProgress=false callback=ImeCallback=ImeOnBackInvokedCallback@218750357 Callback=android.window.IOnBackInvokedCallback$Stub$Proxy@c71052e
2025-08-08 10:43:49.176 24705-24705 InsetsController        com.akslabs.SandeshVahak             D  hide(ime(), fromIme=true)
2025-08-08 10:43:49.245 24705-24705 ImeTracker              com.akslabs.SandeshVahak             I  com.akslabs.SandeshVahak:52900043: onCancelled at PHASE_CLIENT_ANIMATION_CANCEL
2025-08-08 10:43:49.251 24705-24705 ImeTracker              com.akslabs.SandeshVahak             I  com.akslabs.SandeshVahak:a578778b: onRequestHide at ORIGIN_CLIENT reason HIDE_SOFT_INPUT_ON_ANIMATION_STATE_CHANGED fromUser false
2025-08-08 10:43:49.252 24705-24705 ImeTracker              com.akslabs.SandeshVahak             I  com.akslabs.SandeshVahak:a578778b: onFailed at PHASE_CLIENT_VIEW_SERVED
2025-08-08 10:43:49.289 24705-24705 ImeTracker              com.akslabs.SandeshVahak             I  com.akslabs.SandeshVahak:9fb43b29: onRequestHide at ORIGIN_CLIENT reason HIDE_SOFT_INPUT fromUser false
2025-08-08 10:43:49.290 24705-24705 ImeTracker              com.akslabs.SandeshVahak             I  com.akslabs.SandeshVahak:9fb43b29: onFailed at PHASE_CLIENT_VIEW_SERVED
2025-08-08 10:43:49.290 24705-24705 InsetsController        com.akslabs.SandeshVahak             D  hide(ime(), fromIme=false)
2025-08-08 10:43:49.290 24705-24705 ImeTracker              com.akslabs.SandeshVahak             I  com.akslabs.SandeshVahak:8a58f543: onRequestHide at ORIGIN_CLIENT reason HIDE_SOFT_INPUT_BY_INSETS_API fromUser false
2025-08-08 10:43:49.291 24705-24705 ImeTracker              com.akslabs.SandeshVahak             I  com.akslabs.SandeshVahak:8a58f543: onCancelled at PHASE_CLIENT_APPLY_ANIMATION
2025-08-08 10:43:54.590 24705-24705 MainActivity            com.akslabs.SandeshVahak             I  SMS permission granted; waiting for user to enable sync
2025-08-08 10:43:54.664 24705-24705 WindowOnBackDispatcher  com.akslabs.SandeshVahak             W  sendCancelIfRunning: isInProgress=false callback=androidx.activity.OnBackPressedDispatcher$Api34Impl$createOnBackAnimationCallback$1@80200c3
