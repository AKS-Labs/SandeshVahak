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
--------- beginning of system
--------- beginning of main
2025-08-09 10:19:36.955  7362-7362  bs.SandeshVahak         com.akslabs.SandeshVahak             I  Late-enabling -Xcheck:jni
2025-08-09 10:19:37.037  7362-7362  bs.SandeshVahak         com.akslabs.SandeshVahak             I  Using CollectorTypeCC GC.
2025-08-09 10:19:37.096  7362-7362  nativeloader            com.akslabs.SandeshVahak             D  Load libframework-connectivity-tiramisu-jni.so using APEX ns com_android_tethering for caller /apex/com.android.tethering/javalib/framework-connectivity-t.jar: ok
2025-08-09 10:19:37.161  7362-7362  re-initialized>         com.akslabs.SandeshVahak             W  type=1400 audit(0.0:13531): avc:  granted  { execute } for  path="/data/data/com.akslabs.SandeshVahak/code_cache/startup_agents/b13c65d9-agent.so" dev="mmcblk0p61" ino=221339 scontext=u:r:untrusted_app:s0:c35,c259,c512,c768 tcontext=u:object_r:app_data_file:s0:c35,c259,c512,c768 tclass=file app=com.akslabs.SandeshVahak
2025-08-09 10:19:37.177  7362-7362  nativeloader            com.akslabs.SandeshVahak             D  Load /data/user/0/com.akslabs.SandeshVahak/code_cache/startup_agents/b13c65d9-agent.so using system ns (caller=<unknown>): ok
2025-08-09 10:19:37.196  7362-7362  bs.SandeshVahak         com.akslabs.SandeshVahak             W  DexFile /data/data/com.akslabs.SandeshVahak/code_cache/.studio/instruments-462f9421.jar is in boot class path but is not in a known location
2025-08-09 10:19:37.428  7362-7362  bs.SandeshVahak         com.akslabs.SandeshVahak             W  Redefining intrinsic method java.lang.Thread java.lang.Thread.currentThread(). This may cause the unexpected use of the original definition of java.lang.Thread java.lang.Thread.currentThread()in methods that have already been compiled.
2025-08-09 10:19:37.428  7362-7362  bs.SandeshVahak         com.akslabs.SandeshVahak             W  Redefining intrinsic method boolean java.lang.Thread.interrupted(). This may cause the unexpected use of the original definition of boolean java.lang.Thread.interrupted()in methods that have already been compiled.
2025-08-09 10:19:37.499  7362-7362  ApplicationLoaders      com.akslabs.SandeshVahak             D  Returning zygote-cached class loader: /system_ext/framework/androidx.window.extensions.jar
2025-08-09 10:19:37.500  7362-7362  ApplicationLoaders      com.akslabs.SandeshVahak             D  Returning zygote-cached class loader: /system_ext/framework/androidx.window.sidecar.jar
2025-08-09 10:19:37.518  7362-7362  ziparchive              com.akslabs.SandeshVahak             W  Unable to open '/data/data/com.akslabs.SandeshVahak/code_cache/.overlay/base.apk/classes12.dm': No such file or directory
2025-08-09 10:19:37.523  7362-7362  ziparchive              com.akslabs.SandeshVahak             W  Unable to open '/data/data/com.akslabs.SandeshVahak/code_cache/.overlay/base.apk/classes15.dm': No such file or directory
2025-08-09 10:19:37.528  7362-7362  ziparchive              com.akslabs.SandeshVahak             W  Unable to open '/data/data/com.akslabs.SandeshVahak/code_cache/.overlay/base.apk/classes8.dm': No such file or directory
2025-08-09 10:19:37.530  7362-7362  ziparchive              com.akslabs.SandeshVahak             W  Unable to open '/data/data/com.akslabs.SandeshVahak/code_cache/.overlay/base.apk/classes5.dm': No such file or directory
2025-08-09 10:19:37.535  7362-7362  ziparchive              com.akslabs.SandeshVahak             W  Unable to open '/data/data/com.akslabs.SandeshVahak/code_cache/.overlay/base.apk/classes10.dm': No such file or directory
2025-08-09 10:19:37.538  7362-7362  ziparchive              com.akslabs.SandeshVahak             W  Unable to open '/data/data/com.akslabs.SandeshVahak/code_cache/.overlay/base.apk/classes13.dm': No such file or directory
2025-08-09 10:19:37.543  7362-7362  ziparchive              com.akslabs.SandeshVahak             W  Unable to open '/data/data/com.akslabs.SandeshVahak/code_cache/.overlay/base.apk/classes6.dm': No such file or directory
2025-08-09 10:19:37.549  7362-7362  ziparchive              com.akslabs.SandeshVahak             W  Unable to open '/data/data/com.akslabs.SandeshVahak/code_cache/.overlay/base.apk/classes14.dm': No such file or directory
2025-08-09 10:19:37.553  7362-7362  ziparchive              com.akslabs.SandeshVahak             W  Unable to open '/data/app/~~LgqftdFAcnHcJ0tuHjj0DQ==/com.akslabs.SandeshVahak-XgQfX1-16n-qIWBnfKwihg==/base.dm': No such file or directory
2025-08-09 10:19:37.553  7362-7362  ziparchive              com.akslabs.SandeshVahak             W  Unable to open '/data/app/~~LgqftdFAcnHcJ0tuHjj0DQ==/com.akslabs.SandeshVahak-XgQfX1-16n-qIWBnfKwihg==/base.dm': No such file or directory
2025-08-09 10:19:38.599  7362-7362  nativeloader            com.akslabs.SandeshVahak             D  Configuring clns-7 for other apk /data/app/~~LgqftdFAcnHcJ0tuHjj0DQ==/com.akslabs.SandeshVahak-XgQfX1-16n-qIWBnfKwihg==/base.apk. target_sdk_version=34, uses_libraries=, library_path=/data/app/~~LgqftdFAcnHcJ0tuHjj0DQ==/com.akslabs.SandeshVahak-XgQfX1-16n-qIWBnfKwihg==/lib/arm64:/data/app/~~LgqftdFAcnHcJ0tuHjj0DQ==/com.akslabs.SandeshVahak-XgQfX1-16n-qIWBnfKwihg==/base.apk!/lib/arm64-v8a, permitted_path=/data:/mnt/expand:/data/user/0/com.akslabs.SandeshVahak
2025-08-09 10:19:38.679  7362-7362  GraphicsEnvironment     com.akslabs.SandeshVahak             V  Currently set values for:
2025-08-09 10:19:38.679  7362-7362  GraphicsEnvironment     com.akslabs.SandeshVahak             V    angle_gl_driver_selection_pkgs=[com.android.angle, com.linecorp.b612.android, com.campmobile.snow, com.google.android.apps.tachyon]
2025-08-09 10:19:38.680  7362-7362  GraphicsEnvironment     com.akslabs.SandeshVahak             V    angle_gl_driver_selection_values=[angle, native, native, native]
2025-08-09 10:19:38.680  7362-7362  GraphicsEnvironment     com.akslabs.SandeshVahak             V  com.akslabs.SandeshVahak is not listed in per-application setting
2025-08-09 10:19:38.680  7362-7362  GraphicsEnvironment     com.akslabs.SandeshVahak             V  Neither updatable production driver nor prerelease driver is supported.
2025-08-09 10:19:38.761  7362-7362  WM-WrkMgrInitializer    com.akslabs.SandeshVahak             D  Initializing WorkManager with default configuration.
2025-08-09 10:19:38.857  7362-7362  WM-PackageManagerHelper com.akslabs.SandeshVahak             D  Skipping component enablement for androidx.work.impl.background.systemjob.SystemJobService
2025-08-09 10:19:38.857  7362-7362  WM-Schedulers           com.akslabs.SandeshVahak             D  Created SystemJobScheduler and enabled SystemJobService
2025-08-09 10:19:39.225  7362-7362  EngineFactory           com.akslabs.SandeshVahak             I  Provider GmsCore_OpenSSL not available
2025-08-09 10:19:39.483  7362-7362  Choreographer           com.akslabs.SandeshVahak             I  Skipped 36 frames!  The application may be doing too much work on its main thread.
2025-08-09 10:19:39.621  7362-7390  DatabaseDebugHelper     com.akslabs.SandeshVahak             I  === DATABASE DEBUG REPORT ===
2025-08-09 10:19:39.651  7362-7390  DatabaseDebugHelper     com.akslabs.SandeshVahak             I  Database version: 7
2025-08-09 10:19:39.668  7362-7362  DesktopModeFlagsUtil    com.akslabs.SandeshVahak             D  Toggle override initialized to: OVERRIDE_UNSET
2025-08-09 10:19:39.727  7362-7390  DatabaseDebugHelper     com.akslabs.SandeshVahak             I  Record counts:
2025-08-09 10:19:39.727  7362-7390  DatabaseDebugHelper     com.akslabs.SandeshVahak             I    Total SMS messages: 438
2025-08-09 10:19:39.728  7362-7390  DatabaseDebugHelper     com.akslabs.SandeshVahak             I    Synced SMS messages: 117
2025-08-09 10:19:39.728  7362-7390  DatabaseDebugHelper     com.akslabs.SandeshVahak             I    Total remote SMS messages: 119
2025-08-09 10:19:39.728  7362-7390  DatabaseDebugHelper     com.akslabs.SandeshVahak             I  Sample synced SMS messages:
2025-08-09 10:19:39.728  7362-7390  DatabaseDebugHelper     com.akslabs.SandeshVahak             I    SMS: id=117, remoteId=2177, address=BZ-INBSNL-S, syncedAt=1754714629899
2025-08-09 10:19:39.728  7362-7390  DatabaseDebugHelper     com.akslabs.SandeshVahak             I    SMS: id=116, remoteId=2175, address=BZ-INBSNL-S, syncedAt=1754714629899
2025-08-09 10:19:39.728  7362-7390  DatabaseDebugHelper     com.akslabs.SandeshVahak             I    SMS: id=115, remoteId=2174, address=BZ-INBSNL-S, syncedAt=1754714629898
2025-08-09 10:19:39.728  7362-7390  DatabaseDebugHelper     com.akslabs.SandeshVahak             I  Sample remote SMS messages:
2025-08-09 10:19:39.728  7362-7390  DatabaseDebugHelper     com.akslabs.SandeshVahak             I    RemoteSMS: remoteId=2178, originalId=111, address=BZ-INBSNL-S
2025-08-09 10:19:39.728  7362-7390  DatabaseDebugHelper     com.akslabs.SandeshVahak             I    RemoteSMS: remoteId=2177, originalId=117, address=BZ-INBSNL-S
2025-08-09 10:19:39.729  7362-7390  DatabaseDebugHelper     com.akslabs.SandeshVahak             I    RemoteSMS: remoteId=2176, originalId=110, address=BZ-INBSNL-S
2025-08-09 10:19:39.729  7362-7390  DatabaseDebugHelper     com.akslabs.SandeshVahak             W  SMS DATA INCONSISTENCY: Synced SMS count (117) != Remote SMS count (119)
2025-08-09 10:19:39.729  7362-7390  DatabaseDebugHelper     com.akslabs.SandeshVahak             I  === END DATABASE DEBUG REPORT ===
2025-08-09 10:19:39.877  7362-7362  SmsContentObserver      com.akslabs.SandeshVahak             D  Registering content observer on URI=content://sms, notifyForDescendants=true, thread=main
2025-08-09 10:19:39.879  7362-7362  SmsContentObserver      com.akslabs.SandeshVahak             I  ✅ SMS content observer started and registered
2025-08-09 10:19:40.108  7362-7373  bs.SandeshVahak         com.akslabs.SandeshVahak             I  Compiler allocated 4431KB to compile void android.view.ViewRootImpl.performTraversals()
2025-08-09 10:19:40.410  7362-7362  bs.SandeshVahak         com.akslabs.SandeshVahak             W  Method boolean androidx.compose.runtime.snapshots.SnapshotStateList.conditionalUpdate(boolean, kotlin.jvm.functions.Function1) failed lock verification and will run slower.
                                                                                                    Common causes for lock verification issues are non-optimized dex code
                                                                                                    and incorrect proguard optimizations.
2025-08-09 10:19:40.410  7362-7362  bs.SandeshVahak         com.akslabs.SandeshVahak             W  Method boolean androidx.compose.runtime.snapshots.SnapshotStateList.conditionalUpdate$default(androidx.compose.runtime.snapshots.SnapshotStateList, boolean, kotlin.jvm.functions.Function1, int, java.lang.Object) failed lock verification and will run slower.
2025-08-09 10:19:40.410  7362-7362  bs.SandeshVahak         com.akslabs.SandeshVahak             W  Method java.lang.Object androidx.compose.runtime.snapshots.SnapshotStateList.mutate(kotlin.jvm.functions.Function1) failed lock verification and will run slower.
2025-08-09 10:19:40.410  7362-7362  bs.SandeshVahak         com.akslabs.SandeshVahak             W  Method void androidx.compose.runtime.snapshots.SnapshotStateList.update(boolean, kotlin.jvm.functions.Function1) failed lock verification and will run slower.
2025-08-09 10:19:40.410  7362-7362  bs.SandeshVahak         com.akslabs.SandeshVahak             W  Method void androidx.compose.runtime.snapshots.SnapshotStateList.update$default(androidx.compose.runtime.snapshots.SnapshotStateList, boolean, kotlin.jvm.functions.Function1, int, java.lang.Object) failed lock verification and will run slower.
2025-08-09 10:19:40.958  7362-7362  AppNavHost              com.akslabs.SandeshVahak             D  Local count recomposed: 0
2025-08-09 10:19:40.958  7362-7362  LocalSmsScreen          com.akslabs.SandeshVahak             D  Recompose LocalSmsScreen: items=0, loadState=CombinedLoadStates(refresh=Loading(endOfPaginationReached=false), prepend=NotLoading(endOfPaginationReached=false), append=NotLoading(endOfPaginationReached=false), source=LoadStates(refresh=Loading(endOfPaginationReached=false), prepend=NotLoading(endOfPaginationReached=false), append=NotLoading(endOfPaginationReached=false)), mediator=null)
2025-08-09 10:19:40.982  7362-7394  AdrenoGLES-0            com.akslabs.SandeshVahak             I  QUALCOMM build                   : 95db91f, Ifbc588260a
                                                                                                    Build Date                       : 09/24/20
                                                                                                    OpenGL ES Shader Compiler Version: EV031.32.02.01
                                                                                                    Local Branch                     : mybrancheafe5b6d-fb5b-f1b0-b904-5cb90179c3e0
                                                                                                    Remote Branch                    : quic/gfx-adreno.lnx.1.0.r114-rel
                                                                                                    Remote Branch                    : NONE
                                                                                                    Reconstruct Branch               : NOTHING
2025-08-09 10:19:40.982  7362-7394  AdrenoGLES-0            com.akslabs.SandeshVahak             I  Build Config                     : S P 10.0.7 AArch64
2025-08-09 10:19:40.982  7362-7394  AdrenoGLES-0            com.akslabs.SandeshVahak             I  Driver Path                      : /vendor/lib64/egl/libGLESv2_adreno.so
2025-08-09 10:19:41.054  7362-7394  AdrenoGLES-0            com.akslabs.SandeshVahak             I  PFP: 0x016ee190, ME: 0x00000000
2025-08-09 10:19:41.132  7362-7405  Gralloc4                com.akslabs.SandeshVahak             I  mapper 4.x is not supported
2025-08-09 10:19:41.133  7362-7405  Gralloc3                com.akslabs.SandeshVahak             W  mapper 3.x is not supported
2025-08-09 10:19:41.141  7362-7405  Gralloc2                com.akslabs.SandeshVahak             I  Adding additional valid usage bits: 0x8202000
2025-08-09 10:19:41.198  7362-7390  SmsReaderService        com.akslabs.SandeshVahak             D  Using baseline timestamp for 'sync only new messages' mode
2025-08-09 10:19:41.200  7362-7379  HWUI                    com.akslabs.SandeshVahak             I  Davey! duration=1218ms; Flags=1, FrameTimelineVsyncId=167071368, IntendedVsync=267221385833071, Vsync=267221385833071, InputEventId=0, HandleInputStart=267221398133165, AnimationStart=267221398137800, PerformTraversalsStart=267221398139675, DrawStart=267222543826446, FrameDeadline=267221407166403, FrameInterval=267221398119832, FrameStartTime=16669912, SyncQueued=267222584171862, SyncStart=267222584291289, IssueDrawCommandsStart=267222585015977, SwapBuffers=267222602678998, FrameCompleted=267222604845612, DequeueBufferDuration=15781, QueueBufferDuration=324531, GpuCompleted=267222604845612, SwapBuffersCompleted=267222604121185, DisplayPresentTime=8929200823284531200, CommandSubmissionCompleted=267222602678998, 
2025-08-09 10:19:41.224  7362-7390  SmsReaderService        com.akslabs.SandeshVahak             I  Starting incremental SMS sync from timestamp: 1754672769958 (db=1754714623675 (2025-08-09 10:13:43.675), baseline=1754672774958 (2025-08-08 22:36:14.958), buffer=5000)
2025-08-09 10:19:41.230  7362-7390  SmsReaderService        com.akslabs.SandeshVahak             I  Reading SMS messages after timestamp: 1754672769958 (2025-08-08 22:36:09.958)
2025-08-09 10:19:41.230  7362-7390  SmsReaderService        com.akslabs.SandeshVahak             D  Query: uri=content://sms, selection='date > ?', args=[1754672769958], sort=date ASC
2025-08-09 10:19:41.298  7362-7390  SmsReaderService        com.akslabs.SandeshVahak             D  Cursor is null? false
2025-08-09 10:19:41.300  7362-7390  SmsReaderService        com.akslabs.SandeshVahak             V  Row #1 -> id=434, date=1754672806032, addr=+919545154067
2025-08-09 10:19:41.301  7362-7390  SmsReaderService        com.akslabs.SandeshVahak             V  Row #2 -> id=435, date=1754674612251, addr=BZ-INBSNL-S
2025-08-09 10:19:41.301  7362-7390  SmsReaderService        com.akslabs.SandeshVahak             V  Row #3 -> id=436, date=1754705828625, addr=+919545154067
2025-08-09 10:19:41.302  7362-7390  SmsReaderService        com.akslabs.SandeshVahak             V  Row #4 -> id=437, date=1754706938510, addr=+919545154067
2025-08-09 10:19:41.303  7362-7390  SmsReaderService        com.akslabs.SandeshVahak             V  Row #5 -> id=438, date=1754707399114, addr=+919545154067
2025-08-09 10:19:41.304  7362-7390  SmsReaderService        com.akslabs.SandeshVahak             V  Row #6 -> id=439, date=1754707468104, addr=+919545154067
2025-08-09 10:19:41.305  7362-7390  SmsReaderService        com.akslabs.SandeshVahak             V  Row #7 -> id=440, date=1754714623675, addr=+919545154067
2025-08-09 10:19:41.305  7362-7390  SmsReaderService        com.akslabs.SandeshVahak             D  Total rows iterated: 7
2025-08-09 10:19:41.319  7362-7362  Choreographer           com.akslabs.SandeshVahak             I  Skipped 77 frames!  The application may be doing too much work on its main thread.
2025-08-09 10:19:41.341  7362-7390  SmsReaderService        com.akslabs.SandeshVahak             I  Read 7 new SMS messages after timestamp
2025-08-09 10:19:41.351  7362-7390  SmsReaderService        com.akslabs.SandeshVahak             V  Evaluating message id=434, date=1754672806032 (2025-08-08 22:36:46.032), exists=true
2025-08-09 10:19:41.351  7362-7390  SmsReaderService        com.akslabs.SandeshVahak             D  Message 434 already exists in DB, skipping
2025-08-09 10:19:41.356  7362-7390  SmsReaderService        com.akslabs.SandeshVahak             V  Evaluating message id=435, date=1754674612251 (2025-08-08 23:06:52.251), exists=true
2025-08-09 10:19:41.356  7362-7390  SmsReaderService        com.akslabs.SandeshVahak             D  Message 435 already exists in DB, skipping
2025-08-09 10:19:41.362  7362-7390  SmsReaderService        com.akslabs.SandeshVahak             V  Evaluating message id=436, date=1754705828625 (2025-08-09 07:47:08.625), exists=true
2025-08-09 10:19:41.362  7362-7390  SmsReaderService        com.akslabs.SandeshVahak             D  Message 436 already exists in DB, skipping
2025-08-09 10:19:41.368  7362-7390  SmsReaderService        com.akslabs.SandeshVahak             V  Evaluating message id=437, date=1754706938510 (2025-08-09 08:05:38.510), exists=true
2025-08-09 10:19:41.368  7362-7390  SmsReaderService        com.akslabs.SandeshVahak             D  Message 437 already exists in DB, skipping
2025-08-09 10:19:41.373  7362-7390  SmsReaderService        com.akslabs.SandeshVahak             V  Evaluating message id=438, date=1754707399114 (2025-08-09 08:13:19.114), exists=true
2025-08-09 10:19:41.373  7362-7390  SmsReaderService        com.akslabs.SandeshVahak             D  Message 438 already exists in DB, skipping
2025-08-09 10:19:41.379  7362-7390  SmsReaderService        com.akslabs.SandeshVahak             V  Evaluating message id=439, date=1754707468104 (2025-08-09 08:14:28.104), exists=true
2025-08-09 10:19:41.379  7362-7390  SmsReaderService        com.akslabs.SandeshVahak             D  Message 439 already exists in DB, skipping
2025-08-09 10:19:41.385  7362-7390  SmsReaderService        com.akslabs.SandeshVahak             V  Evaluating message id=440, date=1754714623675 (2025-08-09 10:13:43.675), exists=true
2025-08-09 10:19:41.385  7362-7390  SmsReaderService        com.akslabs.SandeshVahak             D  Message 440 already exists in DB, skipping
2025-08-09 10:19:41.385  7362-7390  SmsReaderService        com.akslabs.SandeshVahak             I  Incremental SMS sync complete: 0 new messages (batch)
2025-08-09 10:19:41.512  7362-7380  HWUI                    com.akslabs.SandeshVahak             I  Davey! duration=1460ms; Flags=0, FrameTimelineVsyncId=167071426, IntendedVsync=267221452487931, Vsync=267222735992461, InputEventId=0, HandleInputStart=267222741328164, AnimationStart=267222741331237, PerformTraversalsStart=267222830626237, DrawStart=267222879282800, FrameDeadline=267222640739891, FrameInterval=267222740263581, FrameStartTime=16668890, SyncQueued=267222905319466, SyncStart=267222905408789, IssueDrawCommandsStart=267222905531029, SwapBuffers=267222910672643, FrameCompleted=267222913276029, DequeueBufferDuration=15261, QueueBufferDuration=455937, GpuCompleted=267222913276029, SwapBuffersCompleted=267222911988321, DisplayPresentTime=72904454214516736, CommandSubmissionCompleted=267222910672643, 
2025-08-09 10:19:41.566  7362-7362  AppNavHost              com.akslabs.SandeshVahak             D  Local count recomposed: 0
2025-08-09 10:19:41.567  7362-7362  LocalSmsScreen          com.akslabs.SandeshVahak             D  Recompose LocalSmsScreen: items=0, loadState=CombinedLoadStates(refresh=Loading(endOfPaginationReached=false), prepend=NotLoading(endOfPaginationReached=false), append=NotLoading(endOfPaginationReached=false), source=LoadStates(refresh=Loading(endOfPaginationReached=false), prepend=NotLoading(endOfPaginationReached=false), append=NotLoading(endOfPaginationReached=false)), mediator=null)
2025-08-09 10:19:41.757  7362-7362  AppNavHost              com.akslabs.SandeshVahak             D  Local count recomposed: 438
2025-08-09 10:19:41.758  7362-7362  LocalSmsScreen          com.akslabs.SandeshVahak             D  Recompose LocalSmsScreen: items=60, loadState=CombinedLoadStates(refresh=NotLoading(endOfPaginationReached=false), prepend=NotLoading(endOfPaginationReached=true), append=NotLoading(endOfPaginationReached=false), source=LoadStates(refresh=NotLoading(endOfPaginationReached=false), prepend=NotLoading(endOfPaginationReached=true), append=NotLoading(endOfPaginationReached=false)), mediator=null)
2025-08-09 10:19:42.451  7362-7362  Choreographer           com.akslabs.SandeshVahak             I  Skipped 46 frames!  The application may be doing too much work on its main thread.
2025-08-09 10:19:42.475  7362-7380  HWUI                    com.akslabs.SandeshVahak             I  Davey! duration=789ms; Flags=0, FrameTimelineVsyncId=167072498, IntendedVsync=267223085756094, Vsync=267223085756094, InputEventId=0, HandleInputStart=267223095162800, AnimationStart=267223095165143, PerformTraversalsStart=267223180812748, DrawStart=267223180989102, FrameDeadline=267223107089426, FrameInterval=267223095152956, FrameStartTime=16663749, SyncQueued=267223868656185, SyncStart=267223868904726, IssueDrawCommandsStart=267223869164414, SwapBuffers=267223873420143, FrameCompleted=267223875993528, DequeueBufferDuration=17552, QueueBufferDuration=227343, GpuCompleted=267223875993528, SwapBuffersCompleted=267223874143424, DisplayPresentTime=0, CommandSubmissionCompleted=267223873420143, 
2025-08-09 10:19:42.544  7362-7362  AppNavHost              com.akslabs.SandeshVahak             D  Local count recomposed: 438
2025-08-09 10:19:42.545  7362-7362  LocalSmsScreen          com.akslabs.SandeshVahak             D  Recompose LocalSmsScreen: items=60, loadState=CombinedLoadStates(refresh=NotLoading(endOfPaginationReached=false), prepend=NotLoading(endOfPaginationReached=true), append=NotLoading(endOfPaginationReached=false), source=LoadStates(refresh=NotLoading(endOfPaginationReached=false), prepend=NotLoading(endOfPaginationReached=true), append=NotLoading(endOfPaginationReached=false)), mediator=null)
2025-08-09 10:19:42.720  7362-7362  AppNavHost              com.akslabs.SandeshVahak             D  Local count recomposed: 438
2025-08-09 10:19:42.720  7362-7362  LocalSmsScreen          com.akslabs.SandeshVahak             D  Recompose LocalSmsScreen: items=60, loadState=CombinedLoadStates(refresh=NotLoading(endOfPaginationReached=false), prepend=NotLoading(endOfPaginationReached=true), append=NotLoading(endOfPaginationReached=false), source=LoadStates(refresh=NotLoading(endOfPaginationReached=false), prepend=NotLoading(endOfPaginationReached=true), append=NotLoading(endOfPaginationReached=false)), mediator=null)
2025-08-09 10:19:45.344  7362-7412  ProfileInstaller        com.akslabs.SandeshVahak             D  Installing profile for com.akslabs.SandeshVahak
2025-08-09 10:19:55.005  7362-7362  SmsContentObserver      com.akslabs.SandeshVahak             D  onChange(selfChange=false) called with no URI
2025-08-09 10:19:55.007  7362-7362  SmsContentObserver      com.akslabs.SandeshVahak             D  onChange(selfChange=false, uri=content://sms/raw/2)
2025-08-09 10:19:55.007  7362-7362  SmsContentObserver      com.akslabs.SandeshVahak             D  ⏱️ SMS change debounced (2ms)
2025-08-09 10:19:55.008  7362-7390  SmsContentObserver      com.akslabs.SandeshVahak             I  ⚡ SMS content changed, triggering lightning-fast sync
2025-08-09 10:19:55.210  7362-7390  SmsContentObserver      com.akslabs.SandeshVahak             D  Preference isSmsSyncEnabledKey read: false
2025-08-09 10:19:55.210  7362-7390  SmsContentObserver      com.akslabs.SandeshVahak             D  SMS sync enabled: false
2025-08-09 10:19:55.213  7362-7390  SmsReaderService        com.akslabs.SandeshVahak             D  Using baseline timestamp for 'sync only new messages' mode
2025-08-09 10:19:55.216  7362-7390  SmsReaderService        com.akslabs.SandeshVahak             I  Starting incremental SMS sync from timestamp: 1754672769958 (db=1754714623675 (2025-08-09 10:13:43.675), baseline=1754672774958 (2025-08-08 22:36:14.958), buffer=5000)
2025-08-09 10:19:55.218  7362-7390  SmsReaderService        com.akslabs.SandeshVahak             I  Reading SMS messages after timestamp: 1754672769958 (2025-08-08 22:36:09.958)
2025-08-09 10:19:55.218  7362-7390  SmsReaderService        com.akslabs.SandeshVahak             D  Query: uri=content://sms, selection='date > ?', args=[1754672769958], sort=date ASC
2025-08-09 10:19:55.240  7362-7390  SmsReaderService        com.akslabs.SandeshVahak             D  Cursor is null? false
2025-08-09 10:19:55.241  7362-7390  SmsReaderService        com.akslabs.SandeshVahak             V  Row #1 -> id=434, date=1754672806032, addr=+919545154067
2025-08-09 10:19:55.241  7362-7390  SmsReaderService        com.akslabs.SandeshVahak             V  Row #2 -> id=435, date=1754674612251, addr=BZ-INBSNL-S
2025-08-09 10:19:55.241  7362-7390  SmsReaderService        com.akslabs.SandeshVahak             V  Row #3 -> id=436, date=1754705828625, addr=+919545154067
2025-08-09 10:19:55.241  7362-7390  SmsReaderService        com.akslabs.SandeshVahak             V  Row #4 -> id=437, date=1754706938510, addr=+919545154067
2025-08-09 10:19:55.241  7362-7390  SmsReaderService        com.akslabs.SandeshVahak             V  Row #5 -> id=438, date=1754707399114, addr=+919545154067
2025-08-09 10:19:55.241  7362-7390  SmsReaderService        com.akslabs.SandeshVahak             V  Row #6 -> id=439, date=1754707468104, addr=+919545154067
2025-08-09 10:19:55.242  7362-7390  SmsReaderService        com.akslabs.SandeshVahak             V  Row #7 -> id=440, date=1754714623675, addr=+919545154067
2025-08-09 10:19:55.242  7362-7390  SmsReaderService        com.akslabs.SandeshVahak             D  Total rows iterated: 7
2025-08-09 10:19:55.280  7362-7390  SmsReaderService        com.akslabs.SandeshVahak             I  Read 7 new SMS messages after timestamp
2025-08-09 10:19:55.285  7362-7390  SmsReaderService        com.akslabs.SandeshVahak             V  Evaluating message id=434, date=1754672806032 (2025-08-08 22:36:46.032), exists=true
2025-08-09 10:19:55.285  7362-7390  SmsReaderService        com.akslabs.SandeshVahak             D  Message 434 already exists in DB, skipping
2025-08-09 10:19:55.291  7362-7390  SmsReaderService        com.akslabs.SandeshVahak             V  Evaluating message id=435, date=1754674612251 (2025-08-08 23:06:52.251), exists=true
2025-08-09 10:19:55.291  7362-7390  SmsReaderService        com.akslabs.SandeshVahak             D  Message 435 already exists in DB, skipping
2025-08-09 10:19:55.296  7362-7390  SmsReaderService        com.akslabs.SandeshVahak             V  Evaluating message id=436, date=1754705828625 (2025-08-09 07:47:08.625), exists=true
2025-08-09 10:19:55.296  7362-7390  SmsReaderService        com.akslabs.SandeshVahak             D  Message 436 already exists in DB, skipping
2025-08-09 10:19:55.300  7362-7390  SmsReaderService        com.akslabs.SandeshVahak             V  Evaluating message id=437, date=1754706938510 (2025-08-09 08:05:38.510), exists=true
2025-08-09 10:19:55.300  7362-7390  SmsReaderService        com.akslabs.SandeshVahak             D  Message 437 already exists in DB, skipping
2025-08-09 10:19:55.303  7362-7390  SmsReaderService        com.akslabs.SandeshVahak             V  Evaluating message id=438, date=1754707399114 (2025-08-09 08:13:19.114), exists=true
2025-08-09 10:19:55.303  7362-7390  SmsReaderService        com.akslabs.SandeshVahak             D  Message 438 already exists in DB, skipping
2025-08-09 10:19:55.305  7362-7390  SmsReaderService        com.akslabs.SandeshVahak             V  Evaluating message id=439, date=1754707468104 (2025-08-09 08:14:28.104), exists=true
2025-08-09 10:19:55.308  7362-7390  SmsReaderService        com.akslabs.SandeshVahak             D  Message 439 already exists in DB, skipping
2025-08-09 10:19:55.311  7362-7390  SmsReaderService        com.akslabs.SandeshVahak             V  Evaluating message id=440, date=1754714623675 (2025-08-09 10:13:43.675), exists=true
2025-08-09 10:19:55.311  7362-7390  SmsReaderService        com.akslabs.SandeshVahak             D  Message 440 already exists in DB, skipping
2025-08-09 10:19:55.311  7362-7390  SmsReaderService        com.akslabs.SandeshVahak             I  Incremental SMS sync complete: 0 new messages (batch)
2025-08-09 10:19:55.311  7362-7390  SmsContentObserver      com.akslabs.SandeshVahak             I  📱 DB update: inserted 0 new messages into local database (incremental)
2025-08-09 10:19:55.311  7362-7390  SmsContentObserver      com.akslabs.SandeshVahak             D  ✅ No new SMS messages found (304ms)
2025-08-09 10:19:55.594  7362-7362  SmsContentObserver      com.akslabs.SandeshVahak             D  onChange(selfChange=false) called with no URI
2025-08-09 10:19:55.595  7362-7362  SmsContentObserver      com.akslabs.SandeshVahak             D  onChange(selfChange=false, uri=content://sms/raw)
2025-08-09 10:19:55.595  7362-7362  SmsContentObserver      com.akslabs.SandeshVahak             D  ⏱️ SMS change debounced (1ms)
2025-08-09 10:19:55.595  7362-7390  SmsContentObserver      com.akslabs.SandeshVahak             I  ⚡ SMS content changed, triggering lightning-fast sync
2025-08-09 10:19:55.797  7362-7390  SmsContentObserver      com.akslabs.SandeshVahak             D  Preference isSmsSyncEnabledKey read: false
2025-08-09 10:19:55.797  7362-7390  SmsContentObserver      com.akslabs.SandeshVahak             D  SMS sync enabled: false
2025-08-09 10:19:55.800  7362-7390  SmsReaderService        com.akslabs.SandeshVahak             D  Using baseline timestamp for 'sync only new messages' mode
2025-08-09 10:19:55.803  7362-7390  SmsReaderService        com.akslabs.SandeshVahak             I  Starting incremental SMS sync from timestamp: 1754672769958 (db=1754714623675 (2025-08-09 10:13:43.675), baseline=1754672774958 (2025-08-08 22:36:14.958), buffer=5000)
2025-08-09 10:19:55.804  7362-7390  SmsReaderService        com.akslabs.SandeshVahak             I  Reading SMS messages after timestamp: 1754672769958 (2025-08-08 22:36:09.958)
2025-08-09 10:19:55.804  7362-7390  SmsReaderService        com.akslabs.SandeshVahak             D  Query: uri=content://sms, selection='date > ?', args=[1754672769958], sort=date ASC
2025-08-09 10:19:55.828  7362-7390  SmsReaderService        com.akslabs.SandeshVahak             D  Cursor is null? false
2025-08-09 10:19:55.828  7362-7390  SmsReaderService        com.akslabs.SandeshVahak             V  Row #1 -> id=434, date=1754672806032, addr=+919545154067
2025-08-09 10:19:55.829  7362-7390  SmsReaderService        com.akslabs.SandeshVahak             V  Row #2 -> id=435, date=1754674612251, addr=BZ-INBSNL-S
2025-08-09 10:19:55.829  7362-7390  SmsReaderService        com.akslabs.SandeshVahak             V  Row #3 -> id=436, date=1754705828625, addr=+919545154067
2025-08-09 10:19:55.829  7362-7390  SmsReaderService        com.akslabs.SandeshVahak             V  Row #4 -> id=437, date=1754706938510, addr=+919545154067
2025-08-09 10:19:55.829  7362-7390  SmsReaderService        com.akslabs.SandeshVahak             V  Row #5 -> id=438, date=1754707399114, addr=+919545154067
2025-08-09 10:19:55.829  7362-7390  SmsReaderService        com.akslabs.SandeshVahak             V  Row #6 -> id=439, date=1754707468104, addr=+919545154067
2025-08-09 10:19:55.829  7362-7390  SmsReaderService        com.akslabs.SandeshVahak             V  Row #7 -> id=440, date=1754714623675, addr=+919545154067
2025-08-09 10:19:55.829  7362-7390  SmsReaderService        com.akslabs.SandeshVahak             V  Row #8 -> id=441, date=1754714995569, addr=+919545154067
2025-08-09 10:19:55.829  7362-7390  SmsReaderService        com.akslabs.SandeshVahak             D  Total rows iterated: 8
2025-08-09 10:19:55.830  7362-7362  SmsContentObserver      com.akslabs.SandeshVahak             D  onChange(selfChange=false) called with no URI
2025-08-09 10:19:55.830  7362-7362  SmsContentObserver      com.akslabs.SandeshVahak             D  ⏱️ SMS change debounced (236ms)
2025-08-09 10:19:55.830  7362-7362  SmsContentObserver      com.akslabs.SandeshVahak             D  onChange(selfChange=false, uri=content://sms/441)
2025-08-09 10:19:55.830  7362-7362  SmsContentObserver      com.akslabs.SandeshVahak             D  ⏱️ SMS change debounced (236ms)
2025-08-09 10:19:55.861  7362-7390  SmsReaderService        com.akslabs.SandeshVahak             I  Read 8 new SMS messages after timestamp
2025-08-09 10:19:55.864  7362-7390  SmsReaderService        com.akslabs.SandeshVahak             V  Evaluating message id=434, date=1754672806032 (2025-08-08 22:36:46.032), exists=true
2025-08-09 10:19:55.865  7362-7390  SmsReaderService        com.akslabs.SandeshVahak             D  Message 434 already exists in DB, skipping
2025-08-09 10:19:55.868  7362-7390  SmsReaderService        com.akslabs.SandeshVahak             V  Evaluating message id=435, date=1754674612251 (2025-08-08 23:06:52.251), exists=true
2025-08-09 10:19:55.868  7362-7390  SmsReaderService        com.akslabs.SandeshVahak             D  Message 435 already exists in DB, skipping
2025-08-09 10:19:55.872  7362-7390  SmsReaderService        com.akslabs.SandeshVahak             V  Evaluating message id=436, date=1754705828625 (2025-08-09 07:47:08.625), exists=true
2025-08-09 10:19:55.872  7362-7390  SmsReaderService        com.akslabs.SandeshVahak             D  Message 436 already exists in DB, skipping
2025-08-09 10:19:55.875  7362-7390  SmsReaderService        com.akslabs.SandeshVahak             V  Evaluating message id=437, date=1754706938510 (2025-08-09 08:05:38.510), exists=true
2025-08-09 10:19:55.875  7362-7390  SmsReaderService        com.akslabs.SandeshVahak             D  Message 437 already exists in DB, skipping
2025-08-09 10:19:55.878  7362-7390  SmsReaderService        com.akslabs.SandeshVahak             V  Evaluating message id=438, date=1754707399114 (2025-08-09 08:13:19.114), exists=true
2025-08-09 10:19:55.878  7362-7390  SmsReaderService        com.akslabs.SandeshVahak             D  Message 438 already exists in DB, skipping
2025-08-09 10:19:55.881  7362-7390  SmsReaderService        com.akslabs.SandeshVahak             V  Evaluating message id=439, date=1754707468104 (2025-08-09 08:14:28.104), exists=true
2025-08-09 10:19:55.881  7362-7390  SmsReaderService        com.akslabs.SandeshVahak             D  Message 439 already exists in DB, skipping
2025-08-09 10:19:55.884  7362-7390  SmsReaderService        com.akslabs.SandeshVahak             V  Evaluating message id=440, date=1754714623675 (2025-08-09 10:13:43.675), exists=true
2025-08-09 10:19:55.885  7362-7390  SmsReaderService        com.akslabs.SandeshVahak             D  Message 440 already exists in DB, skipping
2025-08-09 10:19:55.887  7362-7390  SmsReaderService        com.akslabs.SandeshVahak             V  Evaluating message id=441, date=1754714995569 (2025-08-09 10:19:55.569), exists=false
2025-08-09 10:19:55.888  7362-7390  SmsReaderService        com.akslabs.SandeshVahak             I  ✅ New message queued for insert: 441 from +919545154067 at 1754714995569
2025-08-09 10:19:55.892  7362-7390  SmsReaderService        com.akslabs.SandeshVahak             I  Incremental SMS sync complete: 1 new messages (batch)
2025-08-09 10:19:55.892  7362-7390  SmsContentObserver      com.akslabs.SandeshVahak             I  📱 DB update: inserted 1 new messages into local database (incremental)
2025-08-09 10:19:55.892  7362-7390  SmsContentObserver      com.akslabs.SandeshVahak             I  🚀 Found 1 new SMS messages in 297ms
2025-08-09 10:19:55.892  7362-7390  SmsContentObserver      com.akslabs.SandeshVahak             D  ⏸️ SMS sync disabled, skipping cloud sync
2025-08-09 10:19:56.003  7362-7362  AppNavHost              com.akslabs.SandeshVahak             D  Local count recomposed: 439
2025-08-09 10:19:56.003  7362-7362  LocalSmsScreen          com.akslabs.SandeshVahak             D  Recompose LocalSmsScreen: items=60, loadState=CombinedLoadStates(refresh=Loading(endOfPaginationReached=false), prepend=NotLoading(endOfPaginationReached=false), append=NotLoading(endOfPaginationReached=false), source=LoadStates(refresh=Loading(endOfPaginationReached=false), prepend=NotLoading(endOfPaginationReached=false), append=NotLoading(endOfPaginationReached=false)), mediator=null)
2025-08-09 10:19:56.051  7362-7362  LocalSmsScreen          com.akslabs.SandeshVahak             D  Recompose LocalSmsScreen: items=60, loadState=CombinedLoadStates(refresh=NotLoading(endOfPaginationReached=false), prepend=NotLoading(endOfPaginationReached=true), append=NotLoading(endOfPaginationReached=false), source=LoadStates(refresh=NotLoading(endOfPaginationReached=false), prepend=NotLoading(endOfPaginationReached=true), append=NotLoading(endOfPaginationReached=false)), mediator=null)
2025-08-09 10:19:56.158  7362-7373  bs.SandeshVahak         com.akslabs.SandeshVahak             I  Compiler allocated 5021KB to compile void com.akslabs.chitralaya.ui.components.SmsListItemKt$SmsListItem$2.invoke(androidx.compose.foundation.layout.ColumnScope, androidx.compose.runtime.Composer, int)
2025-08-09 10:19:56.649  7362-7362  Choreographer           com.akslabs.SandeshVahak             I  Skipped 36 frames!  The application may be doing too much work on its main thread.
2025-08-09 10:20:07.974  7362-7362  AppNavHost              com.akslabs.SandeshVahak             D  Local count recomposed: 439
2025-08-09 10:20:07.975  7362-7362  LocalSmsScreen          com.akslabs.SandeshVahak             D  Recompose LocalSmsScreen: items=60, loadState=CombinedLoadStates(refresh=NotLoading(endOfPaginationReached=false), prepend=NotLoading(endOfPaginationReached=true), append=NotLoading(endOfPaginationReached=false), source=LoadStates(refresh=NotLoading(endOfPaginationReached=false), prepend=NotLoading(endOfPaginationReached=true), append=NotLoading(endOfPaginationReached=false)), mediator=null)
2025-08-09 10:20:08.241  7362-7362  AppNavHost              com.akslabs.SandeshVahak             D  Local count recomposed: 439
2025-08-09 10:20:08.241  7362-7362  LocalSmsScreen          com.akslabs.SandeshVahak             D  Recompose LocalSmsScreen: items=60, loadState=CombinedLoadStates(refresh=NotLoading(endOfPaginationReached=false), prepend=NotLoading(endOfPaginationReached=true), append=NotLoading(endOfPaginationReached=false), source=LoadStates(refresh=NotLoading(endOfPaginationReached=false), prepend=NotLoading(endOfPaginationReached=true), append=NotLoading(endOfPaginationReached=false)), mediator=null)
2025-08-09 10:20:09.931  7362-7362  WindowOnBackDispatcher  com.akslabs.SandeshVahak             W  sendCancelIfRunning: isInProgress=false callback=androidx.activity.OnBackPressedDispatcher$Api34Impl$createOnBackAnimationCallback$1@dc7a0b7
2025-08-09 10:20:10.004  7362-7362  SmsContentObserver      com.akslabs.SandeshVahak             I  SMS content observer stopped
2025-08-09 10:20:56.479  7362-7362  SmsContentObserver      com.akslabs.SandeshVahak             D  Registering content observer on URI=content://sms, notifyForDescendants=true, thread=main
2025-08-09 10:20:56.482  7362-7362  SmsContentObserver      com.akslabs.SandeshVahak             I  ✅ SMS content observer started and registered
2025-08-09 10:20:56.705  7362-7392  SmsReaderService        com.akslabs.SandeshVahak             D  Using baseline timestamp for 'sync only new messages' mode
2025-08-09 10:20:56.708  7362-7392  SmsReaderService        com.akslabs.SandeshVahak             I  Starting incremental SMS sync from timestamp: 1754672769958 (db=1754714995569 (2025-08-09 10:19:55.569), baseline=1754672774958 (2025-08-08 22:36:14.958), buffer=5000)
2025-08-09 10:20:56.710  7362-7392  SmsReaderService        com.akslabs.SandeshVahak             I  Reading SMS messages after timestamp: 1754672769958 (2025-08-08 22:36:09.958)
2025-08-09 10:20:56.710  7362-7392  SmsReaderService        com.akslabs.SandeshVahak             D  Query: uri=content://sms, selection='date > ?', args=[1754672769958], sort=date ASC
2025-08-09 10:20:56.776  7362-7392  SmsReaderService        com.akslabs.SandeshVahak             D  Cursor is null? false
2025-08-09 10:20:56.776  7362-7392  SmsReaderService        com.akslabs.SandeshVahak             V  Row #1 -> id=434, date=1754672806032, addr=+919545154067
2025-08-09 10:20:56.776  7362-7392  SmsReaderService        com.akslabs.SandeshVahak             V  Row #2 -> id=435, date=1754674612251, addr=BZ-INBSNL-S
2025-08-09 10:20:56.776  7362-7392  SmsReaderService        com.akslabs.SandeshVahak             V  Row #3 -> id=436, date=1754705828625, addr=+919545154067
2025-08-09 10:20:56.777  7362-7392  SmsReaderService        com.akslabs.SandeshVahak             V  Row #4 -> id=437, date=1754706938510, addr=+919545154067
2025-08-09 10:20:56.777  7362-7392  SmsReaderService        com.akslabs.SandeshVahak             V  Row #5 -> id=438, date=1754707399114, addr=+919545154067
2025-08-09 10:20:56.777  7362-7392  SmsReaderService        com.akslabs.SandeshVahak             V  Row #6 -> id=439, date=1754707468104, addr=+919545154067
2025-08-09 10:20:56.777  7362-7392  SmsReaderService        com.akslabs.SandeshVahak             V  Row #7 -> id=440, date=1754714623675, addr=+919545154067
2025-08-09 10:20:56.777  7362-7392  SmsReaderService        com.akslabs.SandeshVahak             V  Row #8 -> id=441, date=1754714995569, addr=+919545154067
2025-08-09 10:20:56.777  7362-7392  SmsReaderService        com.akslabs.SandeshVahak             D  Total rows iterated: 8
2025-08-09 10:20:56.829  7362-7392  SmsReaderService        com.akslabs.SandeshVahak             I  Read 8 new SMS messages after timestamp
2025-08-09 10:20:56.831  7362-7392  SmsReaderService        com.akslabs.SandeshVahak             V  Evaluating message id=434, date=1754672806032 (2025-08-08 22:36:46.032), exists=true
2025-08-09 10:20:56.831  7362-7392  SmsReaderService        com.akslabs.SandeshVahak             D  Message 434 already exists in DB, skipping
2025-08-09 10:20:56.833  7362-7392  SmsReaderService        com.akslabs.SandeshVahak             V  Evaluating message id=435, date=1754674612251 (2025-08-08 23:06:52.251), exists=true
2025-08-09 10:20:56.833  7362-7392  SmsReaderService        com.akslabs.SandeshVahak             D  Message 435 already exists in DB, skipping
2025-08-09 10:20:56.836  7362-7392  SmsReaderService        com.akslabs.SandeshVahak             V  Evaluating message id=436, date=1754705828625 (2025-08-09 07:47:08.625), exists=true
2025-08-09 10:20:56.836  7362-7392  SmsReaderService        com.akslabs.SandeshVahak             D  Message 436 already exists in DB, skipping
2025-08-09 10:20:56.839  7362-7392  SmsReaderService        com.akslabs.SandeshVahak             V  Evaluating message id=437, date=1754706938510 (2025-08-09 08:05:38.510), exists=true
2025-08-09 10:20:56.839  7362-7392  SmsReaderService        com.akslabs.SandeshVahak             D  Message 437 already exists in DB, skipping
2025-08-09 10:20:56.843  7362-7392  SmsReaderService        com.akslabs.SandeshVahak             V  Evaluating message id=438, date=1754707399114 (2025-08-09 08:13:19.114), exists=true
2025-08-09 10:20:56.843  7362-7392  SmsReaderService        com.akslabs.SandeshVahak             D  Message 438 already exists in DB, skipping
2025-08-09 10:20:56.846  7362-7392  SmsReaderService        com.akslabs.SandeshVahak             V  Evaluating message id=439, date=1754707468104 (2025-08-09 08:14:28.104), exists=true
2025-08-09 10:20:56.846  7362-7392  SmsReaderService        com.akslabs.SandeshVahak             D  Message 439 already exists in DB, skipping
2025-08-09 10:20:56.849  7362-7392  SmsReaderService        com.akslabs.SandeshVahak             V  Evaluating message id=440, date=1754714623675 (2025-08-09 10:13:43.675), exists=true
2025-08-09 10:20:56.849  7362-7392  SmsReaderService        com.akslabs.SandeshVahak             D  Message 440 already exists in DB, skipping
2025-08-09 10:20:56.852  7362-7392  SmsReaderService        com.akslabs.SandeshVahak             V  Evaluating message id=441, date=1754714995569 (2025-08-09 10:19:55.569), exists=true
2025-08-09 10:20:56.852  7362-7392  SmsReaderService        com.akslabs.SandeshVahak             D  Message 441 already exists in DB, skipping
2025-08-09 10:20:56.852  7362-7392  SmsReaderService        com.akslabs.SandeshVahak             I  Incremental SMS sync complete: 0 new messages (batch)
2025-08-09 10:20:59.825  7362-7362  WindowOnBackDispatcher  com.akslabs.SandeshVahak             W  sendCancelIfRunning: isInProgress=false callback=androidx.activity.OnBackPressedDispatcher$Api34Impl$createOnBackAnimationCallback$1@dc7a0b7
2025-08-09 10:20:59.861  7362-7362  SmsContentObserver      com.akslabs.SandeshVahak             I  SMS content observer stopped
2025-08-09 10:21:27.649  6481-18996 WorkSourceUtil          com.google.android.gms               E  Could not find package: com.akslabs.SandeshVahak
2025-08-09 10:21:39.961  1882-2022  VerityUtils             system_server                        E  Failed to check whether fs-verity is enabled, errno 38: /data/app/~~STS3-dEC9vyB4GUFlhmJcA==/com.akslabs.SandeshVahak-ngVlgjDZZ392LD9EBPVEvA==/base.apk
---------------------------- PROCESS STARTED (8306) for package com.akslabs.SandeshVahak ----------------------------
2025-08-09 10:21:41.721  8306-8306  ApplicationLoaders      com.akslabs.SandeshVahak             D  Returning zygote-cached class loader: /system_ext/framework/androidx.window.extensions.jar
2025-08-09 10:21:41.722  8306-8306  ApplicationLoaders      com.akslabs.SandeshVahak             D  Returning zygote-cached class loader: /system_ext/framework/androidx.window.sidecar.jar
2025-08-09 10:21:41.773  8306-8306  ziparchive              com.akslabs.SandeshVahak             W  Unable to open '/data/app/~~STS3-dEC9vyB4GUFlhmJcA==/com.akslabs.SandeshVahak-ngVlgjDZZ392LD9EBPVEvA==/base.dm': No such file or directory
2025-08-09 10:21:41.773  8306-8306  ziparchive              com.akslabs.SandeshVahak             W  Unable to open '/data/app/~~STS3-dEC9vyB4GUFlhmJcA==/com.akslabs.SandeshVahak-ngVlgjDZZ392LD9EBPVEvA==/base.dm': No such file or directory
2025-08-09 10:21:42.290  8306-8306  nativeloader            com.akslabs.SandeshVahak             D  Configuring clns-7 for other apk /data/app/~~STS3-dEC9vyB4GUFlhmJcA==/com.akslabs.SandeshVahak-ngVlgjDZZ392LD9EBPVEvA==/base.apk. target_sdk_version=34, uses_libraries=, library_path=/data/app/~~STS3-dEC9vyB4GUFlhmJcA==/com.akslabs.SandeshVahak-ngVlgjDZZ392LD9EBPVEvA==/lib/arm64:/data/app/~~STS3-dEC9vyB4GUFlhmJcA==/com.akslabs.SandeshVahak-ngVlgjDZZ392LD9EBPVEvA==/base.apk!/lib/arm64-v8a, permitted_path=/data:/mnt/expand:/data/user/0/com.akslabs.SandeshVahak
2025-08-09 10:21:42.511  8306-8306  GraphicsEnvironment     com.akslabs.SandeshVahak             V  Currently set values for:
2025-08-09 10:21:42.511  8306-8306  GraphicsEnvironment     com.akslabs.SandeshVahak             V    angle_gl_driver_selection_pkgs=[com.android.angle, com.linecorp.b612.android, com.campmobile.snow, com.google.android.apps.tachyon]
2025-08-09 10:21:42.511  8306-8306  GraphicsEnvironment     com.akslabs.SandeshVahak             V    angle_gl_driver_selection_values=[angle, native, native, native]
2025-08-09 10:21:42.511  8306-8306  GraphicsEnvironment     com.akslabs.SandeshVahak             V  com.akslabs.SandeshVahak is not listed in per-application setting
2025-08-09 10:21:42.511  8306-8306  GraphicsEnvironment     com.akslabs.SandeshVahak             V  Neither updatable production driver nor prerelease driver is supported.
2025-08-09 10:21:42.643  8306-8306  WM-WrkMgrInitializer    com.akslabs.SandeshVahak             D  Initializing WorkManager with default configuration.
2025-08-09 10:21:42.747  8306-8306  WM-PackageManagerHelper com.akslabs.SandeshVahak             D  androidx.work.impl.background.systemjob.SystemJobService enabled
2025-08-09 10:21:42.747  8306-8306  WM-Schedulers           com.akslabs.SandeshVahak             D  Created SystemJobScheduler and enabled SystemJobService
2025-08-09 10:21:43.266  8306-8306  AndroidKeysetManager    com.akslabs.SandeshVahak             W  keyset not found, will generate a new one
                                                                                                    java.io.FileNotFoundException: can't read keyset; the pref value __androidx_security_crypto_encrypted_prefs_key_keyset__ does not exist
                                                                                                    	at com.google.crypto.tink.integration.android.SharedPrefKeysetReader.readPref(SharedPrefKeysetReader.java:71)
                                                                                                    	at com.google.crypto.tink.integration.android.SharedPrefKeysetReader.readEncrypted(SharedPrefKeysetReader.java:89)
                                                                                                    	at com.google.crypto.tink.KeysetHandle.read(KeysetHandle.java:105)
                                                                                                    	at com.google.crypto.tink.integration.android.AndroidKeysetManager$Builder.read(AndroidKeysetManager.java:311)
                                                                                                    	at com.google.crypto.tink.integration.android.AndroidKeysetManager$Builder.readOrGenerateNewKeyset(AndroidKeysetManager.java:287)
                                                                                                    	at com.google.crypto.tink.integration.android.AndroidKeysetManager$Builder.build(AndroidKeysetManager.java:238)
                                                                                                    	at androidx.security.crypto.EncryptedSharedPreferences.create(EncryptedSharedPreferences.java:123)
                                                                                                    	at com.akslabs.SandeshVahak.data.localdb.Preferences.init(Preferences.kt:44)
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
2025-08-09 10:21:43.400  8306-8306  AndroidKeysetManager    com.akslabs.SandeshVahak             W  keyset not found, will generate a new one
                                                                                                    java.io.FileNotFoundException: can't read keyset; the pref value __androidx_security_crypto_encrypted_prefs_value_keyset__ does not exist
                                                                                                    	at com.google.crypto.tink.integration.android.SharedPrefKeysetReader.readPref(SharedPrefKeysetReader.java:71)
                                                                                                    	at com.google.crypto.tink.integration.android.SharedPrefKeysetReader.readEncrypted(SharedPrefKeysetReader.java:89)
                                                                                                    	at com.google.crypto.tink.KeysetHandle.read(KeysetHandle.java:105)
                                                                                                    	at com.google.crypto.tink.integration.android.AndroidKeysetManager$Builder.read(AndroidKeysetManager.java:311)
                                                                                                    	at com.google.crypto.tink.integration.android.AndroidKeysetManager$Builder.readOrGenerateNewKeyset(AndroidKeysetManager.java:287)
                                                                                                    	at com.google.crypto.tink.integration.android.AndroidKeysetManager$Builder.build(AndroidKeysetManager.java:238)
                                                                                                    	at androidx.security.crypto.EncryptedSharedPreferences.create(EncryptedSharedPreferences.java:128)
                                                                                                    	at com.akslabs.SandeshVahak.data.localdb.Preferences.init(Preferences.kt:44)
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
2025-08-09 10:21:43.460  8306-8306  EngineFactory           com.akslabs.SandeshVahak             I  Provider GmsCore_OpenSSL not available
2025-08-09 10:21:43.826  8306-8306  Choreographer           com.akslabs.SandeshVahak             I  Skipped 62 frames!  The application may be doing too much work on its main thread.
2025-08-09 10:21:43.950  8306-8360  DatabaseDebugHelper     com.akslabs.SandeshVahak             I  === DATABASE DEBUG REPORT ===
2025-08-09 10:21:44.019  8306-8306  DesktopModeFlagsUtil    com.akslabs.SandeshVahak             D  Toggle override initialized to: OVERRIDE_UNSET
2025-08-09 10:21:44.022  8306-8360  DatabaseDebugHelper     com.akslabs.SandeshVahak             I  Database version: 7
2025-08-09 10:21:44.040  8306-8360  DatabaseDebugHelper     com.akslabs.SandeshVahak             I  Record counts:
2025-08-09 10:21:44.040  8306-8360  DatabaseDebugHelper     com.akslabs.SandeshVahak             I    Total SMS messages: 0
2025-08-09 10:21:44.040  8306-8360  DatabaseDebugHelper     com.akslabs.SandeshVahak             I    Synced SMS messages: 0
2025-08-09 10:21:44.040  8306-8360  DatabaseDebugHelper     com.akslabs.SandeshVahak             I    Total remote SMS messages: 0
2025-08-09 10:21:44.041  8306-8360  DatabaseDebugHelper     com.akslabs.SandeshVahak             I  === END DATABASE DEBUG REPORT ===
2025-08-09 10:21:44.414  8306-8329  bs.SandeshVahak         com.akslabs.SandeshVahak             I  Compiler allocated 4431KB to compile void android.view.ViewRootImpl.performTraversals()
2025-08-09 10:21:44.949  8306-8306  bs.SandeshVahak         com.akslabs.SandeshVahak             W  Method boolean androidx.compose.runtime.snapshots.SnapshotStateList.conditionalUpdate(boolean, kotlin.jvm.functions.Function1) failed lock verification and will run slower.
                                                                                                    Common causes for lock verification issues are non-optimized dex code
                                                                                                    and incorrect proguard optimizations.
2025-08-09 10:21:44.949  8306-8306  bs.SandeshVahak         com.akslabs.SandeshVahak             W  Method boolean androidx.compose.runtime.snapshots.SnapshotStateList.conditionalUpdate$default(androidx.compose.runtime.snapshots.SnapshotStateList, boolean, kotlin.jvm.functions.Function1, int, java.lang.Object) failed lock verification and will run slower.
2025-08-09 10:21:44.949  8306-8306  bs.SandeshVahak         com.akslabs.SandeshVahak             W  Method java.lang.Object androidx.compose.runtime.snapshots.SnapshotStateList.mutate(kotlin.jvm.functions.Function1) failed lock verification and will run slower.
2025-08-09 10:21:44.949  8306-8306  bs.SandeshVahak         com.akslabs.SandeshVahak             W  Method void androidx.compose.runtime.snapshots.SnapshotStateList.update(boolean, kotlin.jvm.functions.Function1) failed lock verification and will run slower.
2025-08-09 10:21:44.949  8306-8306  bs.SandeshVahak         com.akslabs.SandeshVahak             W  Method void androidx.compose.runtime.snapshots.SnapshotStateList.update$default(androidx.compose.runtime.snapshots.SnapshotStateList, boolean, kotlin.jvm.functions.Function1, int, java.lang.Object) failed lock verification and will run slower.
2025-08-09 10:21:45.609  8306-8364  AdrenoGLES-0            com.akslabs.SandeshVahak             I  QUALCOMM build                   : 95db91f, Ifbc588260a
                                                                                                    Build Date                       : 09/24/20
                                                                                                    OpenGL ES Shader Compiler Version: EV031.32.02.01
                                                                                                    Local Branch                     : mybrancheafe5b6d-fb5b-f1b0-b904-5cb90179c3e0
                                                                                                    Remote Branch                    : quic/gfx-adreno.lnx.1.0.r114-rel
                                                                                                    Remote Branch                    : NONE
                                                                                                    Reconstruct Branch               : NOTHING
2025-08-09 10:21:45.609  8306-8364  AdrenoGLES-0            com.akslabs.SandeshVahak             I  Build Config                     : S P 10.0.7 AArch64
2025-08-09 10:21:45.609  8306-8364  AdrenoGLES-0            com.akslabs.SandeshVahak             I  Driver Path                      : /vendor/lib64/egl/libGLESv2_adreno.so
2025-08-09 10:21:45.633  8306-8364  AdrenoGLES-0            com.akslabs.SandeshVahak             I  PFP: 0x016ee190, ME: 0x00000000
2025-08-09 10:21:45.735  8306-8373  Gralloc4                com.akslabs.SandeshVahak             I  mapper 4.x is not supported
2025-08-09 10:21:45.735  8306-8373  Gralloc3                com.akslabs.SandeshVahak             W  mapper 3.x is not supported
2025-08-09 10:21:45.748  8306-8373  Gralloc2                com.akslabs.SandeshVahak             I  Adding additional valid usage bits: 0x8202000
2025-08-09 10:21:45.931  8306-8306  Choreographer           com.akslabs.SandeshVahak             I  Skipped 93 frames!  The application may be doing too much work on its main thread.
2025-08-09 10:21:45.934  8306-8336  HWUI                    com.akslabs.SandeshVahak             I  Davey! duration=1706ms; Flags=1, FrameTimelineVsyncId=167157035, IntendedVsync=267345619149515, Vsync=267345619149515, InputEventId=0, HandleInputStart=267345619810045, AnimationStart=267345619812336, PerformTraversalsStart=267345619813378, DrawStart=267347139482856, FrameDeadline=267345640482847, FrameInterval=267345619802961, FrameStartTime=16668456, SyncQueued=267347234642388, SyncStart=267347234812440, IssueDrawCommandsStart=267347235636450, SwapBuffers=267347323060200, FrameCompleted=267347325430721, DequeueBufferDuration=20937, QueueBufferDuration=471458, GpuCompleted=267347325430721, SwapBuffersCompleted=267347324578325, DisplayPresentTime=0, CommandSubmissionCompleted=267347323060200, 
2025-08-09 10:21:48.345  8306-8306  Choreographer           com.akslabs.SandeshVahak             I  Skipped 35 frames!  The application may be doing too much work on its main thread.
2025-08-09 10:21:49.319  8306-8393  ProfileInstaller        com.akslabs.SandeshVahak             D  Installing profile for com.akslabs.SandeshVahak
2025-08-09 10:21:50.696  8306-8306  InsetsController        com.akslabs.SandeshVahak             D  show(ime(), fromIme=false)
2025-08-09 10:21:50.698  8306-8306  ImeTracker              com.akslabs.SandeshVahak             I  com.akslabs.SandeshVahak:e8ba4d3d: onRequestShow at ORIGIN_CLIENT reason SHOW_SOFT_INPUT_BY_INSETS_API fromUser false
2025-08-09 10:21:50.700  8306-8306  InputMethodManager      com.akslabs.SandeshVahak             D  showSoftInput() view=androidx.compose.ui.platform.AndroidComposeView{8fd4e07 VFED..... .F....ID 0,0-1080,2263 aid=1073741824} flags=0 reason=SHOW_SOFT_INPUT_BY_INSETS_API
2025-08-09 10:21:50.743  8306-8306  ImeTracker              com.akslabs.SandeshVahak             I  com.akslabs.SandeshVahak:7769fc1b: onRequestShow at ORIGIN_CLIENT reason SHOW_SOFT_INPUT fromUser false
2025-08-09 10:21:50.744  8306-8306  InputMethodManager      com.akslabs.SandeshVahak             D  showSoftInput() view=androidx.compose.ui.platform.AndroidComposeView{8fd4e07 VFED..... .F...... 0,0-1080,2263 aid=1073741824} flags=0 reason=SHOW_SOFT_INPUT
2025-08-09 10:21:51.070  8306-8306  InsetsController        com.akslabs.SandeshVahak             D  show(ime(), fromIme=true)
2025-08-09 10:21:51.081  8306-8306  InsetsController        com.akslabs.SandeshVahak             D  show(ime(), fromIme=true)
2025-08-09 10:21:51.082  8306-8306  ImeTracker              com.akslabs.SandeshVahak             I  com.akslabs.SandeshVahak:7769fc1b: onCancelled at PHASE_CLIENT_APPLY_ANIMATION
2025-08-09 10:21:51.097  8306-8400  InteractionJankMonitor  com.akslabs.SandeshVahak             W  Initializing without READ_DEVICE_CONFIG permission. enabled=false, interval=1, missedFrameThreshold=3, frameTimeThreshold=64, package=com.akslabs.SandeshVahak
2025-08-09 10:21:51.309  8306-8306  ImeTracker              com.akslabs.SandeshVahak             I  com.akslabs.SandeshVahak:e8ba4d3d: onShown
2025-08-09 10:21:51.412  8306-8330  bs.SandeshVahak         com.akslabs.SandeshVahak             I  Background concurrent copying GC freed 9072KB AllocSpace bytes, 20(784KB) LOS objects, 49% free, 6720KB/13MB, paused 86us,43us total 106.280ms
2025-08-09 10:21:53.462  8306-8306  WindowOnBackDispatcher  com.akslabs.SandeshVahak             W  sendCancelIfRunning: isInProgress=false callback=ImeCallback=ImeOnBackInvokedCallback@218750357 Callback=android.window.IOnBackInvokedCallback$Stub$Proxy@76eb5e3
2025-08-09 10:21:53.463  8306-8306  InsetsController        com.akslabs.SandeshVahak             D  hide(ime(), fromIme=true)
2025-08-09 10:21:53.467  8306-8306  ImeTracker              com.akslabs.SandeshVahak             I  com.android.systemui:9d98e3ea: onCancelled at PHASE_CLIENT_ANIMATION_CANCEL
2025-08-09 10:21:53.468  8306-8306  ImeTracker              com.akslabs.SandeshVahak             I  com.akslabs.SandeshVahak:d85a8534: onRequestHide at ORIGIN_CLIENT reason HIDE_SOFT_INPUT_ON_ANIMATION_STATE_CHANGED fromUser false
2025-08-09 10:21:53.469  8306-8306  ImeTracker              com.akslabs.SandeshVahak             I  com.akslabs.SandeshVahak:d85a8534: onFailed at PHASE_CLIENT_VIEW_SERVED
2025-08-09 10:21:57.827  8306-8306  ImeBackDispatcher       com.akslabs.SandeshVahak             E  Ime callback not found. Ignoring unregisterReceivedCallback. callbackId: 218750357
2025-08-09 10:22:12.479  8306-8306  InsetsController        com.akslabs.SandeshVahak             D  show(ime(), fromIme=false)
2025-08-09 10:22:12.481  8306-8306  ImeTracker              com.akslabs.SandeshVahak             I  com.akslabs.SandeshVahak:360433de: onRequestShow at ORIGIN_CLIENT reason SHOW_SOFT_INPUT_BY_INSETS_API fromUser false
2025-08-09 10:22:12.481  8306-8306  InputMethodManager      com.akslabs.SandeshVahak             D  showSoftInput() view=androidx.compose.ui.platform.AndroidComposeView{8fd4e07 VFED..... .F....ID 0,0-1080,2263 aid=1073741824} flags=0 reason=SHOW_SOFT_INPUT_BY_INSETS_API
2025-08-09 10:22:12.496  8306-8306  ImeTracker              com.akslabs.SandeshVahak             I  com.akslabs.SandeshVahak:d8d8e385: onRequestShow at ORIGIN_CLIENT reason SHOW_SOFT_INPUT fromUser false
2025-08-09 10:22:12.496  8306-8306  InputMethodManager      com.akslabs.SandeshVahak             D  showSoftInput() view=androidx.compose.ui.platform.AndroidComposeView{8fd4e07 VFED..... .F...... 0,0-1080,2263 aid=1073741824} flags=0 reason=SHOW_SOFT_INPUT
2025-08-09 10:22:12.567  8306-8306  InsetsController        com.akslabs.SandeshVahak             D  show(ime(), fromIme=true)
2025-08-09 10:22:12.785  8306-8306  ImeTracker              com.akslabs.SandeshVahak             I  com.akslabs.SandeshVahak:d8d8e385: onShown
2025-08-09 10:22:17.136  8306-8306  InsetsController        com.akslabs.SandeshVahak             D  show(ime(), fromIme=false)
2025-08-09 10:22:17.136  8306-8306  ImeTracker              com.akslabs.SandeshVahak             I  com.akslabs.SandeshVahak:a5588bf0: onRequestShow at ORIGIN_CLIENT reason SHOW_SOFT_INPUT_BY_INSETS_API fromUser false
2025-08-09 10:22:17.137  8306-8306  ImeTracker              com.akslabs.SandeshVahak             I  com.akslabs.SandeshVahak:a5588bf0: onCancelled at PHASE_CLIENT_APPLY_ANIMATION
2025-08-09 10:22:17.187  8306-8306  ImeTracker              com.akslabs.SandeshVahak             I  com.akslabs.SandeshVahak:c36ec7e3: onRequestShow at ORIGIN_CLIENT reason SHOW_SOFT_INPUT fromUser false
2025-08-09 10:22:17.187  8306-8306  InputMethodManager      com.akslabs.SandeshVahak             D  showSoftInput() view=androidx.compose.ui.platform.AndroidComposeView{8fd4e07 VFED..... .F...... 0,0-1080,2263 aid=1073741824} flags=0 reason=SHOW_SOFT_INPUT
2025-08-09 10:22:17.427  8306-8306  InsetsController        com.akslabs.SandeshVahak             D  show(ime(), fromIme=true)
2025-08-09 10:22:17.428  8306-8306  ImeTracker              com.akslabs.SandeshVahak             I  com.akslabs.SandeshVahak:2bb6cda1: onCancelled at PHASE_CLIENT_APPLY_ANIMATION
2025-08-09 10:22:17.430  8306-8306  InsetsController        com.akslabs.SandeshVahak             D  show(ime(), fromIme=true)
2025-08-09 10:22:17.430  8306-8306  ImeTracker              com.akslabs.SandeshVahak             I  com.akslabs.SandeshVahak:c36ec7e3: onCancelled at PHASE_CLIENT_APPLY_ANIMATION
2025-08-09 10:22:22.821  8306-8306  GettingStartedScreen    com.akslabs.SandeshVahak             I  Validating chat ID: -1002651869724
2025-08-09 10:22:22.822  8306-8360  BotApi                  com.akslabs.SandeshVahak             I  Attempting to get chat info for: Id(id=-1002651869724)
2025-08-09 10:22:23.876  8306-8360  BotApi                  com.akslabs.SandeshVahak             I  getChat result - isSuccess: false
2025-08-09 10:22:23.876  8306-8360  BotApi                  com.akslabs.SandeshVahak             W  getChat failed - result: Unknown(exception=com.google.gson.JsonSyntaxException: java.lang.IllegalStateException: Expected a string but was BEGIN_OBJECT at line 1 column 944 path $.result.pinned_message)
2025-08-09 10:22:23.876  8306-8360  BotApi                  com.akslabs.SandeshVahak             W  🔧 Known Telegram API issue: pinned_message format changed. Trying alternative validation...
2025-08-09 10:22:23.876  8306-8360  BotApi                  com.akslabs.SandeshVahak             I  🔧 Validating chat access using alternative method (due to Telegram API JSON issue)...
2025-08-09 10:22:23.876  8306-8360  BotApi                  com.akslabs.SandeshVahak             W  ⚠️ Skipping test message due to known Telegram Bot API library issue
2025-08-09 10:22:23.876  8306-8360  BotApi                  com.akslabs.SandeshVahak             W  📝 The pinned_message JSON parsing error doesn't affect actual message sending
2025-08-09 10:22:23.876  8306-8360  BotApi                  com.akslabs.SandeshVahak             I  ✅ Assuming chat is accessible - SMS sync should work normally
2025-08-09 10:22:24.263  8306-8306  WindowOnBackDispatcher  com.akslabs.SandeshVahak             W  sendCancelIfRunning: isInProgress=false callback=ImeCallback=ImeOnBackInvokedCallback@218750357 Callback=android.window.IOnBackInvokedCallback$Stub$Proxy@70deba9
2025-08-09 10:22:24.299  8306-8306  WindowOnBackDispatcher  com.akslabs.SandeshVahak             W  sendCancelIfRunning: isInProgress=false callback=ImeCallback=ImeOnBackInvokedCallback@218750357 Callback=android.window.IOnBackInvokedCallback$Stub$Proxy@70deba9
2025-08-09 10:22:24.301  8306-8306  InsetsController        com.akslabs.SandeshVahak             D  hide(ime(), fromIme=true)
2025-08-09 10:22:24.357  8306-8306  ImeTracker              com.akslabs.SandeshVahak             I  com.akslabs.SandeshVahak:42c04f9: onCancelled at PHASE_CLIENT_ANIMATION_CANCEL
2025-08-09 10:22:24.358  8306-8306  ImeTracker              com.akslabs.SandeshVahak             I  com.akslabs.SandeshVahak:2d5d3570: onRequestHide at ORIGIN_CLIENT reason HIDE_SOFT_INPUT_ON_ANIMATION_STATE_CHANGED fromUser false
2025-08-09 10:22:24.358  8306-8306  ImeTracker              com.akslabs.SandeshVahak             I  com.akslabs.SandeshVahak:2d5d3570: onFailed at PHASE_CLIENT_VIEW_SERVED
2025-08-09 10:22:24.367  8306-8306  ImeTracker              com.akslabs.SandeshVahak             I  com.akslabs.SandeshVahak:63c34ae7: onRequestHide at ORIGIN_CLIENT reason HIDE_SOFT_INPUT fromUser false
2025-08-09 10:22:24.367  8306-8306  ImeTracker              com.akslabs.SandeshVahak             I  com.akslabs.SandeshVahak:63c34ae7: onFailed at PHASE_CLIENT_VIEW_SERVED
2025-08-09 10:22:24.367  8306-8306  InsetsController        com.akslabs.SandeshVahak             D  hide(ime(), fromIme=false)
2025-08-09 10:22:24.368  8306-8306  ImeTracker              com.akslabs.SandeshVahak             I  com.akslabs.SandeshVahak:402a7aea: onRequestHide at ORIGIN_CLIENT reason HIDE_SOFT_INPUT_BY_INSETS_API fromUser false
2025-08-09 10:22:24.368  8306-8306  ImeTracker              com.akslabs.SandeshVahak             I  com.akslabs.SandeshVahak:402a7aea: onCancelled at PHASE_CLIENT_APPLY_ANIMATION
2025-08-09 10:22:28.289  8306-8360  SmsReaderService        com.akslabs.SandeshVahak             I  🔄 Starting optimized SMS database sync...
2025-08-09 10:22:28.290  8306-8360  SmsReaderService        com.akslabs.SandeshVahak             I  🚀 Starting optimized SMS reading...
2025-08-09 10:22:28.290  8306-8306  MainActivity            com.akslabs.SandeshVahak             I  SMS permission granted; waiting for user to enable sync
2025-08-09 10:22:28.355  8306-8306  WindowOnBackDispatcher  com.akslabs.SandeshVahak             W  sendCancelIfRunning: isInProgress=false callback=androidx.activity.OnBackPressedDispatcher$Api34Impl$createOnBackAnimationCallback$1@2dff472
2025-08-09 10:22:28.666  8306-8360  SmsReaderService        com.akslabs.SandeshVahak             I  ✅ Successfully read 439 SMS messages
2025-08-09 10:22:28.668  8306-8360  PerformanceMonitor      com.akslabs.SandeshVahak             D  ⚡ FAST: sms_read_all took 378ms
2025-08-09 10:22:28.671  8306-8360  SmsReaderService        com.akslabs.SandeshVahak             D  Processing batch 1 with 100 messages
2025-08-09 10:22:28.681  8306-8306  AppNavHost              com.akslabs.SandeshVahak             D  Local count recomposed: 0
2025-08-09 10:22:28.681  8306-8306  LocalSmsScreen          com.akslabs.SandeshVahak             D  Recompose LocalSmsScreen: items=0, loadState=CombinedLoadStates(refresh=Loading(endOfPaginationReached=false), prepend=NotLoading(endOfPaginationReached=false), append=NotLoading(endOfPaginationReached=false), source=LoadStates(refresh=Loading(endOfPaginationReached=false), prepend=NotLoading(endOfPaginationReached=false), append=NotLoading(endOfPaginationReached=false)), mediator=null)
2025-08-09 10:22:28.809  8306-8360  SmsReaderService        com.akslabs.SandeshVahak             D  Processing batch 2 with 100 messages
2025-08-09 10:22:28.863  8306-8306  AppNavHost              com.akslabs.SandeshVahak             D  Local count recomposed: 0
2025-08-09 10:22:28.863  8306-8306  LocalSmsScreen          com.akslabs.SandeshVahak             D  Recompose LocalSmsScreen: items=0, loadState=CombinedLoadStates(refresh=Loading(endOfPaginationReached=false), prepend=NotLoading(endOfPaginationReached=false), append=NotLoading(endOfPaginationReached=false), source=LoadStates(refresh=Loading(endOfPaginationReached=false), prepend=NotLoading(endOfPaginationReached=false), append=NotLoading(endOfPaginationReached=false)), mediator=null)
2025-08-09 10:22:28.901  8306-8459  SmsReaderService        com.akslabs.SandeshVahak             D  Processing batch 3 with 100 messages
2025-08-09 10:22:28.925  8306-8306  AppNavHost              com.akslabs.SandeshVahak             D  Local count recomposed: 200
2025-08-09 10:22:28.926  8306-8306  LocalSmsScreen          com.akslabs.SandeshVahak             D  Recompose LocalSmsScreen: items=0, loadState=CombinedLoadStates(refresh=Loading(endOfPaginationReached=false), prepend=NotLoading(endOfPaginationReached=false), append=NotLoading(endOfPaginationReached=false), source=LoadStates(refresh=Loading(endOfPaginationReached=false), prepend=NotLoading(endOfPaginationReached=false), append=NotLoading(endOfPaginationReached=false)), mediator=null)
2025-08-09 10:22:28.942  8306-8361  SmsReaderService        com.akslabs.SandeshVahak             I  🔄 Starting optimized SMS database sync...
2025-08-09 10:22:28.943  8306-8361  SmsReaderService        com.akslabs.SandeshVahak             I  🚀 Starting optimized SMS reading...
2025-08-09 10:22:29.046  8306-8360  SmsReaderService        com.akslabs.SandeshVahak             D  Processing batch 4 with 100 messages
2025-08-09 10:22:29.068  8306-8306  AppNavHost              com.akslabs.SandeshVahak             D  Local count recomposed: 300
2025-08-09 10:22:29.068  8306-8306  LocalSmsScreen          com.akslabs.SandeshVahak             D  Recompose LocalSmsScreen: items=0, loadState=CombinedLoadStates(refresh=Loading(endOfPaginationReached=false), prepend=NotLoading(endOfPaginationReached=false), append=NotLoading(endOfPaginationReached=false), source=LoadStates(refresh=Loading(endOfPaginationReached=false), prepend=NotLoading(endOfPaginationReached=false), append=NotLoading(endOfPaginationReached=false)), mediator=null)
2025-08-09 10:22:29.105  8306-8361  SmsReaderService        com.akslabs.SandeshVahak             I  ✅ Successfully read 439 SMS messages
2025-08-09 10:22:29.105  8306-8361  PerformanceMonitor      com.akslabs.SandeshVahak             D  ⚡ FAST: sms_read_all took 163ms
2025-08-09 10:22:29.106  8306-8361  SmsReaderService        com.akslabs.SandeshVahak             D  Processing batch 1 with 100 messages
2025-08-09 10:22:29.136  8306-8462  SmsReaderService        com.akslabs.SandeshVahak             D  Processing batch 5 with 39 messages
2025-08-09 10:22:29.205  8306-8306  AppNavHost              com.akslabs.SandeshVahak             D  Local count recomposed: 400
2025-08-09 10:22:29.205  8306-8306  LocalSmsScreen          com.akslabs.SandeshVahak             D  Recompose LocalSmsScreen: items=0, loadState=CombinedLoadStates(refresh=Loading(endOfPaginationReached=false), prepend=NotLoading(endOfPaginationReached=false), append=NotLoading(endOfPaginationReached=false), source=LoadStates(refresh=Loading(endOfPaginationReached=false), prepend=NotLoading(endOfPaginationReached=false), append=NotLoading(endOfPaginationReached=false)), mediator=null)
2025-08-09 10:22:29.265  8306-8360  SmsReaderService        com.akslabs.SandeshVahak             D  Processing batch 2 with 100 messages
2025-08-09 10:22:29.290  8306-8360  SmsReaderService        com.akslabs.SandeshVahak             I  ✅ SMS sync complete: 439 new, 0 updated
2025-08-09 10:22:29.306  8306-8306  PerformanceMonitor      com.akslabs.SandeshVahak             D  ⚡ FAST: sms_sync_to_db took 1029ms
2025-08-09 10:22:29.316  8306-8306  AppNavHost              com.akslabs.SandeshVahak             D  Local count recomposed: 439
2025-08-09 10:22:29.317  8306-8306  LocalSmsScreen          com.akslabs.SandeshVahak             D  Recompose LocalSmsScreen: items=0, loadState=CombinedLoadStates(refresh=Loading(endOfPaginationReached=false), prepend=NotLoading(endOfPaginationReached=false), append=NotLoading(endOfPaginationReached=false), source=LoadStates(refresh=Loading(endOfPaginationReached=false), prepend=NotLoading(endOfPaginationReached=false), append=NotLoading(endOfPaginationReached=false)), mediator=null)
2025-08-09 10:22:29.412  8306-8462  SmsReaderService        com.akslabs.SandeshVahak             D  Processing batch 3 with 100 messages
2025-08-09 10:22:29.546  8306-8462  SmsReaderService        com.akslabs.SandeshVahak             D  Processing batch 4 with 100 messages
2025-08-09 10:22:29.670  8306-8462  SmsReaderService        com.akslabs.SandeshVahak             D  Processing batch 5 with 39 messages
2025-08-09 10:22:29.714  8306-8361  SmsReaderService        com.akslabs.SandeshVahak             I  ✅ SMS sync complete: 0 new, 439 updated
2025-08-09 10:22:29.720  8306-8306  PerformanceMonitor      com.akslabs.SandeshVahak             D  ⚡ FAST: sms_sync_to_db took 779ms
2025-08-09 10:22:29.794  8306-8306  LocalSmsScreen          com.akslabs.SandeshVahak             D  Recompose LocalSmsScreen: items=60, loadState=CombinedLoadStates(refresh=NotLoading(endOfPaginationReached=false), prepend=NotLoading(endOfPaginationReached=true), append=NotLoading(endOfPaginationReached=false), source=LoadStates(refresh=NotLoading(endOfPaginationReached=false), prepend=NotLoading(endOfPaginationReached=true), append=NotLoading(endOfPaginationReached=false)), mediator=null)
2025-08-09 10:22:30.300  8306-8306  Choreographer           com.akslabs.SandeshVahak             I  Skipped 33 frames!  The application may be doing too much work on its main thread.
2025-08-09 10:23:08.348  8306-8306  AppNavHost              com.akslabs.SandeshVahak             D  Local count recomposed: 439
2025-08-09 10:23:08.348  8306-8306  LocalSmsScreen          com.akslabs.SandeshVahak             D  Recompose LocalSmsScreen: items=60, loadState=CombinedLoadStates(refresh=NotLoading(endOfPaginationReached=false), prepend=NotLoading(endOfPaginationReached=true), append=NotLoading(endOfPaginationReached=false), source=LoadStates(refresh=NotLoading(endOfPaginationReached=false), prepend=NotLoading(endOfPaginationReached=true), append=NotLoading(endOfPaginationReached=false)), mediator=null)
2025-08-09 10:23:08.413  8306-8306  AppNavHost              com.akslabs.SandeshVahak             D  Local count recomposed: 439
2025-08-09 10:23:08.414  8306-8306  LocalSmsScreen          com.akslabs.SandeshVahak             D  Recompose LocalSmsScreen: items=60, loadState=CombinedLoadStates(refresh=NotLoading(endOfPaginationReached=false), prepend=NotLoading(endOfPaginationReached=true), append=NotLoading(endOfPaginationReached=false), source=LoadStates(refresh=NotLoading(endOfPaginationReached=false), prepend=NotLoading(endOfPaginationReached=true), append=NotLoading(endOfPaginationReached=false)), mediator=null)
2025-08-09 10:23:08.461  8306-8306  AppNavHost              com.akslabs.SandeshVahak             D  Local count recomposed: 439
2025-08-09 10:23:08.462  8306-8306  LocalSmsScreen          com.akslabs.SandeshVahak             D  Recompose LocalSmsScreen: items=60, loadState=CombinedLoadStates(refresh=NotLoading(endOfPaginationReached=false), prepend=NotLoading(endOfPaginationReached=true), append=NotLoading(endOfPaginationReached=false), source=LoadStates(refresh=NotLoading(endOfPaginationReached=false), prepend=NotLoading(endOfPaginationReached=true), append=NotLoading(endOfPaginationReached=false)), mediator=null)
2025-08-09 10:23:08.503  8306-8306  AppNavHost              com.akslabs.SandeshVahak             D  Local count recomposed: 439
2025-08-09 10:23:08.503  8306-8306  LocalSmsScreen          com.akslabs.SandeshVahak             D  Recompose LocalSmsScreen: items=60, loadState=CombinedLoadStates(refresh=NotLoading(endOfPaginationReached=false), prepend=NotLoading(endOfPaginationReached=true), append=NotLoading(endOfPaginationReached=false), source=LoadStates(refresh=NotLoading(endOfPaginationReached=false), prepend=NotLoading(endOfPaginationReached=true), append=NotLoading(endOfPaginationReached=false)), mediator=null)
2025-08-09 10:23:08.560  8306-8306  AppNavHost              com.akslabs.SandeshVahak             D  Local count recomposed: 439
2025-08-09 10:23:08.560  8306-8306  LocalSmsScreen          com.akslabs.SandeshVahak             D  Recompose LocalSmsScreen: items=60, loadState=CombinedLoadStates(refresh=NotLoading(endOfPaginationReached=false), prepend=NotLoading(endOfPaginationReached=true), append=NotLoading(endOfPaginationReached=false), source=LoadStates(refresh=NotLoading(endOfPaginationReached=false), prepend=NotLoading(endOfPaginationReached=true), append=NotLoading(endOfPaginationReached=false)), mediator=null)
2025-08-09 10:23:08.601  8306-8306  AppNavHost              com.akslabs.SandeshVahak             D  Local count recomposed: 439
2025-08-09 10:23:08.602  8306-8306  LocalSmsScreen          com.akslabs.SandeshVahak             D  Recompose LocalSmsScreen: items=60, loadState=CombinedLoadStates(refresh=NotLoading(endOfPaginationReached=false), prepend=NotLoading(endOfPaginationReached=true), append=NotLoading(endOfPaginationReached=false), source=LoadStates(refresh=NotLoading(endOfPaginationReached=false), prepend=NotLoading(endOfPaginationReached=true), append=NotLoading(endOfPaginationReached=false)), mediator=null)
2025-08-09 10:23:08.641  8306-8306  AppNavHost              com.akslabs.SandeshVahak             D  Local count recomposed: 439
2025-08-09 10:23:08.641  8306-8306  LocalSmsScreen          com.akslabs.SandeshVahak             D  Recompose LocalSmsScreen: items=60, loadState=CombinedLoadStates(refresh=NotLoading(endOfPaginationReached=false), prepend=NotLoading(endOfPaginationReached=true), append=NotLoading(endOfPaginationReached=false), source=LoadStates(refresh=NotLoading(endOfPaginationReached=false), prepend=NotLoading(endOfPaginationReached=true), append=NotLoading(endOfPaginationReached=false)), mediator=null)
2025-08-09 10:23:08.687  8306-8306  AppNavHost              com.akslabs.SandeshVahak             D  Local count recomposed: 439
2025-08-09 10:23:08.688  8306-8306  LocalSmsScreen          com.akslabs.SandeshVahak             D  Recompose LocalSmsScreen: items=60, loadState=CombinedLoadStates(refresh=NotLoading(endOfPaginationReached=false), prepend=NotLoading(endOfPaginationReached=true), append=NotLoading(endOfPaginationReached=false), source=LoadStates(refresh=NotLoading(endOfPaginationReached=false), prepend=NotLoading(endOfPaginationReached=true), append=NotLoading(endOfPaginationReached=false)), mediator=null)
2025-08-09 10:23:08.731  8306-8306  AppNavHost              com.akslabs.SandeshVahak             D  Local count recomposed: 439
2025-08-09 10:23:08.731  8306-8306  LocalSmsScreen          com.akslabs.SandeshVahak             D  Recompose LocalSmsScreen: items=60, loadState=CombinedLoadStates(refresh=NotLoading(endOfPaginationReached=false), prepend=NotLoading(endOfPaginationReached=true), append=NotLoading(endOfPaginationReached=false), source=LoadStates(refresh=NotLoading(endOfPaginationReached=false), prepend=NotLoading(endOfPaginationReached=true), append=NotLoading(endOfPaginationReached=false)), mediator=null)
2025-08-09 10:23:14.365  8306-8330  bs.SandeshVahak         com.akslabs.SandeshVahak             W  Cleared Reference was only reachable from finalizer (only reported once)
2025-08-09 10:23:14.400  8306-8330  bs.SandeshVahak         com.akslabs.SandeshVahak             I  Background concurrent copying GC freed 6357KB AllocSpace bytes, 0(0B) LOS objects, 49% free, 8914KB/17MB, paused 216us,91us total 204.573ms
2025-08-09 10:23:17.359  8306-8306  SmsContentObserver      com.akslabs.SandeshVahak             D  Registering content observer on URI=content://sms, notifyForDescendants=true, thread=main
2025-08-09 10:23:17.362  8306-8306  SmsContentObserver      com.akslabs.SandeshVahak             I  ✅ SMS content observer started and registered
2025-08-09 10:23:17.468  8306-8462  SmsReaderService        com.akslabs.SandeshVahak             D  Using incremental sync from latest DB message
2025-08-09 10:23:17.482  8306-8462  SmsReaderService        com.akslabs.SandeshVahak             I  Starting incremental SMS sync from timestamp: 1754714995569 (db=1754714995569 (2025-08-09 10:19:55.569), baseline=0 (1970-01-01 05:30:00.000), buffer=5000)
2025-08-09 10:23:17.484  8306-8462  SmsReaderService        com.akslabs.SandeshVahak             I  Reading SMS messages after timestamp: 1754714995569 (2025-08-09 10:19:55.569)
2025-08-09 10:23:17.484  8306-8462  SmsReaderService        com.akslabs.SandeshVahak             D  Query: uri=content://sms, selection='date > ?', args=[1754714995569], sort=date ASC
2025-08-09 10:23:17.528  8306-8462  SmsReaderService        com.akslabs.SandeshVahak             D  Cursor is null? false
2025-08-09 10:23:17.528  8306-8462  SmsReaderService        com.akslabs.SandeshVahak             V  Row #1 -> id=442, date=1754715174111, addr=+919545154067
2025-08-09 10:23:17.528  8306-8462  SmsReaderService        com.akslabs.SandeshVahak             D  Total rows iterated: 1
2025-08-09 10:23:17.567  8306-8462  SmsReaderService        com.akslabs.SandeshVahak             I  Read 1 new SMS messages after timestamp
2025-08-09 10:23:17.572  8306-8462  SmsReaderService        com.akslabs.SandeshVahak             V  Evaluating message id=442, date=1754715174111 (2025-08-09 10:22:54.111), exists=false
2025-08-09 10:23:17.572  8306-8462  SmsReaderService        com.akslabs.SandeshVahak             I  ✅ New message queued for insert: 442 from +919545154067 at 1754715174111
2025-08-09 10:23:17.578  8306-8462  SmsReaderService        com.akslabs.SandeshVahak             I  Incremental SMS sync complete: 1 new messages (batch)
2025-08-09 10:23:17.665  8306-8306  AppNavHost              com.akslabs.SandeshVahak             D  Local count recomposed: 440
2025-08-09 10:23:17.666  8306-8306  LocalSmsScreen          com.akslabs.SandeshVahak             D  Recompose LocalSmsScreen: items=60, loadState=CombinedLoadStates(refresh=Loading(endOfPaginationReached=false), prepend=NotLoading(endOfPaginationReached=false), append=NotLoading(endOfPaginationReached=false), source=LoadStates(refresh=Loading(endOfPaginationReached=false), prepend=NotLoading(endOfPaginationReached=false), append=NotLoading(endOfPaginationReached=false)), mediator=null)
2025-08-09 10:23:17.704  8306-8306  LocalSmsScreen          com.akslabs.SandeshVahak             D  Recompose LocalSmsScreen: items=60, loadState=CombinedLoadStates(refresh=NotLoading(endOfPaginationReached=false), prepend=NotLoading(endOfPaginationReached=true), append=NotLoading(endOfPaginationReached=false), source=LoadStates(refresh=NotLoading(endOfPaginationReached=false), prepend=NotLoading(endOfPaginationReached=true), append=NotLoading(endOfPaginationReached=false)), mediator=null)
2025-08-09 10:23:17.810  8306-8329  bs.SandeshVahak         com.akslabs.SandeshVahak             I  Compiler allocated 5014KB to compile void com.akslabs.chitralaya.ui.components.SmsListItemKt$SmsListItem$2.invoke(androidx.compose.foundation.layout.ColumnScope, androidx.compose.runtime.Composer, int)
2025-08-09 10:23:24.010  8306-8306  AppNavHost              com.akslabs.SandeshVahak             D  Local count recomposed: 440
2025-08-09 10:23:24.010  8306-8306  LocalSmsScreen          com.akslabs.SandeshVahak             D  Recompose LocalSmsScreen: items=60, loadState=CombinedLoadStates(refresh=NotLoading(endOfPaginationReached=false), prepend=NotLoading(endOfPaginationReached=true), append=NotLoading(endOfPaginationReached=false), source=LoadStates(refresh=NotLoading(endOfPaginationReached=false), prepend=NotLoading(endOfPaginationReached=true), append=NotLoading(endOfPaginationReached=false)), mediator=null)
2025-08-09 10:23:24.114  8306-8306  AppNavHost              com.akslabs.SandeshVahak             D  Local count recomposed: 440
2025-08-09 10:23:24.115  8306-8306  LocalSmsScreen          com.akslabs.SandeshVahak             D  Recompose LocalSmsScreen: items=60, loadState=CombinedLoadStates(refresh=NotLoading(endOfPaginationReached=false), prepend=NotLoading(endOfPaginationReached=true), append=NotLoading(endOfPaginationReached=false), source=LoadStates(refresh=NotLoading(endOfPaginationReached=false), prepend=NotLoading(endOfPaginationReached=true), append=NotLoading(endOfPaginationReached=false)), mediator=null)
2025-08-09 10:23:24.205  8306-8306  AppNavHost              com.akslabs.SandeshVahak             D  Local count recomposed: 440
2025-08-09 10:23:24.205  8306-8306  LocalSmsScreen          com.akslabs.SandeshVahak             D  Recompose LocalSmsScreen: items=60, loadState=CombinedLoadStates(refresh=NotLoading(endOfPaginationReached=false), prepend=NotLoading(endOfPaginationReached=true), append=NotLoading(endOfPaginationReached=false), source=LoadStates(refresh=NotLoading(endOfPaginationReached=false), prepend=NotLoading(endOfPaginationReached=true), append=NotLoading(endOfPaginationReached=false)), mediator=null)
2025-08-09 10:23:24.294  8306-8306  AppNavHost              com.akslabs.SandeshVahak             D  Local count recomposed: 440
2025-08-09 10:23:24.295  8306-8306  LocalSmsScreen          com.akslabs.SandeshVahak             D  Recompose LocalSmsScreen: items=60, loadState=CombinedLoadStates(refresh=NotLoading(endOfPaginationReached=false), prepend=NotLoading(endOfPaginationReached=true), append=NotLoading(endOfPaginationReached=false), source=LoadStates(refresh=NotLoading(endOfPaginationReached=false), prepend=NotLoading(endOfPaginationReached=true), append=NotLoading(endOfPaginationReached=false)), mediator=null)
2025-08-09 10:23:24.379  8306-8306  AppNavHost              com.akslabs.SandeshVahak             D  Local count recomposed: 440
2025-08-09 10:23:24.379  8306-8306  LocalSmsScreen          com.akslabs.SandeshVahak             D  Recompose LocalSmsScreen: items=60, loadState=CombinedLoadStates(refresh=NotLoading(endOfPaginationReached=false), prepend=NotLoading(endOfPaginationReached=true), append=NotLoading(endOfPaginationReached=false), source=LoadStates(refresh=NotLoading(endOfPaginationReached=false), prepend=NotLoading(endOfPaginationReached=true), append=NotLoading(endOfPaginationReached=false)), mediator=null)
2025-08-09 10:23:24.897  8306-8306  SmsContentObserver      com.akslabs.SandeshVahak             I  SMS content observer stopped
2025-08-09 10:23:31.564  8306-8306  SmsContentObserver      com.akslabs.SandeshVahak             D  Registering content observer on URI=content://sms, notifyForDescendants=true, thread=main
2025-08-09 10:23:31.565  8306-8306  SmsContentObserver      com.akslabs.SandeshVahak             I  ✅ SMS content observer started and registered
2025-08-09 10:23:31.665  8306-8361  SmsReaderService        com.akslabs.SandeshVahak             D  Using incremental sync from latest DB message
2025-08-09 10:23:31.666  8306-8361  SmsReaderService        com.akslabs.SandeshVahak             I  Starting incremental SMS sync from timestamp: 1754715174111 (db=1754715174111 (2025-08-09 10:22:54.111), baseline=0 (1970-01-01 05:30:00.000), buffer=5000)
2025-08-09 10:23:31.667  8306-8361  SmsReaderService        com.akslabs.SandeshVahak             I  Reading SMS messages after timestamp: 1754715174111 (2025-08-09 10:22:54.111)
2025-08-09 10:23:31.668  8306-8361  SmsReaderService        com.akslabs.SandeshVahak             D  Query: uri=content://sms, selection='date > ?', args=[1754715174111], sort=date ASC
2025-08-09 10:23:31.686  8306-8361  SmsReaderService        com.akslabs.SandeshVahak             D  Cursor is null? false
2025-08-09 10:23:31.686  8306-8361  SmsReaderService        com.akslabs.SandeshVahak             D  Total rows iterated: 0
2025-08-09 10:23:31.723  8306-8361  SmsReaderService        com.akslabs.SandeshVahak             I  Read 0 new SMS messages after timestamp
2025-08-09 10:23:31.723  8306-8361  SmsReaderService        com.akslabs.SandeshVahak             I  Incremental SMS sync complete: 0 new messages (batch)
