--------- beginning of crash
2025-08-04 07:51:32.074 28882-28882 AndroidRuntime          pid-28882                            E  FATAL EXCEPTION: main
                                                                                                    Process: com.akslabs.Suchak, PID: 28882
                                                                                                    java.lang.RuntimeException: Unable to start service com.akslabs.chitralaya.services.SmsObserverService@bc7466f with Intent { cmp=com.akslabs.Suchak/com.akslabs.chitralaya.services.SmsObserverService }: android.app.MissingForegroundServiceTypeException: Starting FGS without a type  callerApp=ProcessRecord{c87a7f8 28882:com.akslabs.Suchak/u0a797} targetSDK=34
                                                                                                    	at android.app.ActivityThread.handleServiceArgs(ActivityThread.java:5246)
                                                                                                    	at android.app.ActivityThread.-$$Nest$mhandleServiceArgs(Unknown Source:0)
                                                                                                    	at android.app.ActivityThread$H.handleMessage(ActivityThread.java:2546)
                                                                                                    	at android.os.Handler.dispatchMessage(Handler.java:109)
                                                                                                    	at android.os.Looper.loopOnce(Looper.java:232)
                                                                                                    	at android.os.Looper.loop(Looper.java:317)
                                                                                                    	at android.app.ActivityThread.main(ActivityThread.java:8782)
                                                                                                    	at java.lang.reflect.Method.invoke(Native Method)
                                                                                                    	at com.android.internal.os.RuntimeInit$MethodAndArgsCaller.run(RuntimeInit.java:595)
                                                                                                    	at com.android.internal.os.ZygoteInit.main(ZygoteInit.java:871)
                                                                                                    Caused by: android.app.MissingForegroundServiceTypeException: Starting FGS without a type  callerApp=ProcessRecord{c87a7f8 28882:com.akslabs.Suchak/u0a797} targetSDK=34
                                                                                                    	at android.app.MissingForegroundServiceTypeException$1.createFromParcel(MissingForegroundServiceTypeException.java:53)
                                                                                                    	at android.app.MissingForegroundServiceTypeException$1.createFromParcel(MissingForegroundServiceTypeException.java:49)
                                                                                                    	at android.os.Parcel.readParcelableInternal(Parcel.java:5064)
                                                                                                    	at android.os.Parcel.readParcelable(Parcel.java:5046)
                                                                                                    	at android.os.Parcel.createExceptionOrNull(Parcel.java:3226)
                                                                                                    	at android.os.Parcel.createException(Parcel.java:3215)
                                                                                                    	at android.os.Parcel.readException(Parcel.java:3198)
                                                                                                    	at android.os.Parcel.readException(Parcel.java:3140)
                                                                                                    	at android.app.IActivityManager$Stub$Proxy.setServiceForeground(IActivityManager.java:7245)
                                                                                                    	at java.lang.reflect.Method.invoke(Native Method)
                                                                                                    	at leakcanary.ServiceWatcher$install$4$2.invoke(ServiceWatcher.kt:93)
                                                                                                    	at java.lang.reflect.Proxy.invoke(Proxy.java:1006)
                                                                                                    	at $Proxy3.setServiceForeground(Unknown Source)
                                                                                                    	at android.app.Service.startForeground(Service.java:776)
                                                                                                    	at com.akslabs.chitralaya.services.SmsObserverService.onStartCommand(SmsObserverService.kt:37)
                                                                                                    	at android.app.ActivityThread.handleServiceArgs(ActivityThread.java:5228)
                                                                                                    	at android.app.ActivityThread.-$$Nest$mhandleServiceArgs(Unknown Source:0) 
                                                                                                    	at android.app.ActivityThread$H.handleMessage(ActivityThread.java:2546) 
                                                                                                    	at android.os.Handler.dispatchMessage(Handler.java:109) 
                                                                                                    	at android.os.Looper.loopOnce(Looper.java:232) 
                                                                                                    	at android.os.Looper.loop(Looper.java:317) 
                                                                                                    	at android.app.ActivityThread.main(ActivityThread.java:8782) 
                                                                                                    	at java.lang.reflect.Method.invoke(Native Method) 
                                                                                                    	at com.android.internal.os.RuntimeInit$MethodAndArgsCaller.run(RuntimeInit.java:595) 
                                                                                                    	at com.android.internal.os.ZygoteInit.main(ZygoteInit.java:871) 
2025-08-04 07:51:35.267 28993-28993 AndroidRuntime          pid-28993                            E  FATAL EXCEPTION: main
                                                                                                    Process: com.akslabs.Suchak, PID: 28993
                                                                                                    java.lang.RuntimeException: Unable to start service com.akslabs.chitralaya.services.SmsObserverService@d1ac1ad with Intent { cmp=com.akslabs.Suchak/com.akslabs.chitralaya.services.SmsObserverService }: android.app.MissingForegroundServiceTypeException: Starting FGS without a type  callerApp=ProcessRecord{e7a874b 28993:com.akslabs.Suchak/u0a797} targetSDK=34
                                                                                                    	at android.app.ActivityThread.handleServiceArgs(ActivityThread.java:5246)
                                                                                                    	at android.app.ActivityThread.-$$Nest$mhandleServiceArgs(Unknown Source:0)
                                                                                                    	at android.app.ActivityThread$H.handleMessage(ActivityThread.java:2546)
                                                                                                    	at android.os.Handler.dispatchMessage(Handler.java:109)
                                                                                                    	at android.os.Looper.loopOnce(Looper.java:232)
                                                                                                    	at android.os.Looper.loop(Looper.java:317)
                                                                                                    	at android.app.ActivityThread.main(ActivityThread.java:8782)
                                                                                                    	at java.lang.reflect.Method.invoke(Native Method)
                                                                                                    	at com.android.internal.os.RuntimeInit$MethodAndArgsCaller.run(RuntimeInit.java:595)
                                                                                                    	at com.android.internal.os.ZygoteInit.main(ZygoteInit.java:871)
                                                                                                    Caused by: android.app.MissingForegroundServiceTypeException: Starting FGS without a type  callerApp=ProcessRecord{e7a874b 28993:com.akslabs.Suchak/u0a797} targetSDK=34
                                                                                                    	at android.app.MissingForegroundServiceTypeException$1.createFromParcel(MissingForegroundServiceTypeException.java:53)
                                                                                                    	at android.app.MissingForegroundServiceTypeException$1.createFromParcel(MissingForegroundServiceTypeException.java:49)
                                                                                                    	at android.os.Parcel.readParcelableInternal(Parcel.java:5064)
                                                                                                    	at android.os.Parcel.readParcelable(Parcel.java:5046)
                                                                                                    	at android.os.Parcel.createExceptionOrNull(Parcel.java:3226)
                                                                                                    	at android.os.Parcel.createException(Parcel.java:3215)
                                                                                                    	at android.os.Parcel.readException(Parcel.java:3198)
                                                                                                    	at android.os.Parcel.readException(Parcel.java:3140)
                                                                                                    	at android.app.IActivityManager$Stub$Proxy.setServiceForeground(IActivityManager.java:7245)
                                                                                                    	at java.lang.reflect.Method.invoke(Native Method)
                                                                                                    	at leakcanary.ServiceWatcher$install$4$2.invoke(ServiceWatcher.kt:93)
                                                                                                    	at java.lang.reflect.Proxy.invoke(Proxy.java:1006)
                                                                                                    	at $Proxy3.setServiceForeground(Unknown Source)
                                                                                                    	at android.app.Service.startForeground(Service.java:776)
                                                                                                    	at com.akslabs.chitralaya.services.SmsObserverService.onStartCommand(SmsObserverService.kt:37)
                                                                                                    	at android.app.ActivityThread.handleServiceArgs(ActivityThread.java:5228)
                                                                                                    	at android.app.ActivityThread.-$$Nest$mhandleServiceArgs(Unknown Source:0) 
                                                                                                    	at android.app.ActivityThread$H.handleMessage(ActivityThread.java:2546) 
                                                                                                    	at android.os.Handler.dispatchMessage(Handler.java:109) 
                                                                                                    	at android.os.Looper.loopOnce(Looper.java:232) 
                                                                                                    	at android.os.Looper.loop(Looper.java:317) 
                                                                                                    	at android.app.ActivityThread.main(ActivityThread.java:8782) 
                                                                                                    	at java.lang.reflect.Method.invoke(Native Method) 
                                                                                                    	at com.android.internal.os.RuntimeInit$MethodAndArgsCaller.run(RuntimeInit.java:595) 
                                                                                                    	at com.android.internal.os.ZygoteInit.main(ZygoteInit.java:871) 
--------- beginning of system
--------- beginning of main
2025-08-04 13:15:13.317  4246-4246  Choreographer           com.akslabs.Suchak                   I  Skipped 38 frames!  The application may be doing too much work on its main thread.
2025-08-04 13:15:13.418  4246-4256  HWUI                    com.akslabs.Suchak                   I  Davey! duration=730ms; Flags=0, FrameTimelineVsyncId=15311458, IntendedVsync=26788253150497, Vsync=26788886512039, InputEventId=0, HandleInputStart=26788894566862, AnimationStart=26788894569831, PerformTraversalsStart=26788913938998, DrawStart=26788914399206, FrameDeadline=26788903139078, FrameInterval=26788890297956, FrameStartTime=16667409, SyncQueued=26788925223842, SyncStart=26788925482383, IssueDrawCommandsStart=26788925917019, SwapBuffers=26788973339050, FrameCompleted=26788983696342, DequeueBufferDuration=26406, QueueBufferDuration=258907, GpuCompleted=26788983696342, SwapBuffersCompleted=26788976062800, DisplayPresentTime=27990301874544, CommandSubmissionCompleted=26788973339050, 
2025-08-04 13:15:20.024  4246-4246  Choreographer           com.akslabs.Suchak                   I  Skipped 39 frames!  The application may be doing too much work on its main thread.
2025-08-04 13:15:20.051  4246-4257  HWUI                    com.akslabs.Suchak                   I  Davey! duration=729ms; Flags=0, FrameTimelineVsyncId=15317562, IntendedVsync=26794885984380, Vsync=26794919314176, InputEventId=0, HandleInputStart=26794926962537, AnimationStart=26794926964725, PerformTraversalsStart=26794983485610, DrawStart=26794983583787, FrameDeadline=26794935983074, FrameInterval=26794926951808, FrameStartTime=16664898, SyncQueued=26795595095922, SyncStart=26795595188787, IssueDrawCommandsStart=26795595510193, SwapBuffers=26795600660349, FrameCompleted=26795615523683, DequeueBufferDuration=17708, QueueBufferDuration=306979, GpuCompleted=26795615523683, SwapBuffersCompleted=26795601286183, DisplayPresentTime=0, CommandSubmissionCompleted=26795600660349, 
2025-08-04 13:17:18.464  5055-5055  .akslabs.Suchak         com.akslabs.Suchak                   I  Late-enabling -Xcheck:jni
2025-08-04 13:17:18.543  5055-5055  .akslabs.Suchak         com.akslabs.Suchak                   I  Using CollectorTypeCC GC.
2025-08-04 13:17:18.603  5055-5055  nativeloader            com.akslabs.Suchak                   D  Load libframework-connectivity-tiramisu-jni.so using APEX ns com_android_tethering for caller /apex/com.android.tethering/javalib/framework-connectivity-t.jar: ok
2025-08-04 13:17:18.654  5055-5055  re-initialized>         com.akslabs.Suchak                   W  type=1400 audit(0.0:2401): avc:  granted  { execute } for  path="/data/data/com.akslabs.Suchak/code_cache/startup_agents/b13c65d9-agent.so" dev="mmcblk0p61" ino=291916 scontext=u:r:untrusted_app:s0:c29,c259,c512,c768 tcontext=u:object_r:app_data_file:s0:c29,c259,c512,c768 tclass=file app=com.akslabs.Suchak
2025-08-04 13:17:18.665  5055-5055  nativeloader            com.akslabs.Suchak                   D  Load /data/user/0/com.akslabs.Suchak/code_cache/startup_agents/b13c65d9-agent.so using system ns (caller=<unknown>): ok
2025-08-04 13:17:18.686  5055-5055  .akslabs.Suchak         com.akslabs.Suchak                   W  DexFile /data/data/com.akslabs.Suchak/code_cache/.studio/instruments-462f9421.jar is in boot class path but is not in a known location
2025-08-04 13:17:18.871  5055-5055  .akslabs.Suchak         com.akslabs.Suchak                   W  Redefining intrinsic method java.lang.Thread java.lang.Thread.currentThread(). This may cause the unexpected use of the original definition of java.lang.Thread java.lang.Thread.currentThread()in methods that have already been compiled.
2025-08-04 13:17:18.871  5055-5055  .akslabs.Suchak         com.akslabs.Suchak                   W  Redefining intrinsic method boolean java.lang.Thread.interrupted(). This may cause the unexpected use of the original definition of boolean java.lang.Thread.interrupted()in methods that have already been compiled.
2025-08-04 13:17:18.936  5055-5055  ApplicationLoaders      com.akslabs.Suchak                   D  Returning zygote-cached class loader: /system_ext/framework/androidx.window.extensions.jar
2025-08-04 13:17:18.936  5055-5055  ApplicationLoaders      com.akslabs.Suchak                   D  Returning zygote-cached class loader: /system_ext/framework/androidx.window.sidecar.jar
2025-08-04 13:17:18.944  5055-5055  ziparchive              com.akslabs.Suchak                   W  Unable to open '/data/data/com.akslabs.Suchak/code_cache/.overlay/base.apk/classes13.dm': No such file or directory
2025-08-04 13:17:18.948  5055-5055  ziparchive              com.akslabs.Suchak                   W  Unable to open '/data/data/com.akslabs.Suchak/code_cache/.overlay/base.apk/classes14.dm': No such file or directory
2025-08-04 13:17:18.952  5055-5055  ziparchive              com.akslabs.Suchak                   W  Unable to open '/data/data/com.akslabs.Suchak/code_cache/.overlay/base.apk/classes4.dm': No such file or directory
2025-08-04 13:17:18.956  5055-5055  ziparchive              com.akslabs.Suchak                   W  Unable to open '/data/data/com.akslabs.Suchak/code_cache/.overlay/base.apk/classes7.dm': No such file or directory
2025-08-04 13:17:18.959  5055-5055  ziparchive              com.akslabs.Suchak                   W  Unable to open '/data/data/com.akslabs.Suchak/code_cache/.overlay/base.apk/classes9.dm': No such file or directory
2025-08-04 13:17:18.964  5055-5055  ziparchive              com.akslabs.Suchak                   W  Unable to open '/data/app/~~GQAOdaftQIEMP0dxF6S1TA==/com.akslabs.Suchak-KKSxtNoNtXOk5aUef31tRg==/base.dm': No such file or directory
2025-08-04 13:17:18.964  5055-5055  ziparchive              com.akslabs.Suchak                   W  Unable to open '/data/app/~~GQAOdaftQIEMP0dxF6S1TA==/com.akslabs.Suchak-KKSxtNoNtXOk5aUef31tRg==/base.dm': No such file or directory
2025-08-04 13:17:19.612  5055-5055  nativeloader            com.akslabs.Suchak                   D  Configuring clns-7 for other apk /data/app/~~GQAOdaftQIEMP0dxF6S1TA==/com.akslabs.Suchak-KKSxtNoNtXOk5aUef31tRg==/base.apk. target_sdk_version=34, uses_libraries=, library_path=/data/app/~~GQAOdaftQIEMP0dxF6S1TA==/com.akslabs.Suchak-KKSxtNoNtXOk5aUef31tRg==/lib/arm64:/data/app/~~GQAOdaftQIEMP0dxF6S1TA==/com.akslabs.Suchak-KKSxtNoNtXOk5aUef31tRg==/base.apk!/lib/arm64-v8a, permitted_path=/data:/mnt/expand:/data/user/0/com.akslabs.Suchak
2025-08-04 13:17:19.656  5055-5055  GraphicsEnvironment     com.akslabs.Suchak                   V  Currently set values for:
2025-08-04 13:17:19.656  5055-5055  GraphicsEnvironment     com.akslabs.Suchak                   V    angle_gl_driver_selection_pkgs=[com.android.angle, com.linecorp.b612.android, com.campmobile.snow, com.google.android.apps.tachyon]
2025-08-04 13:17:19.656  5055-5055  GraphicsEnvironment     com.akslabs.Suchak                   V    angle_gl_driver_selection_values=[angle, native, native, native]
2025-08-04 13:17:19.656  5055-5055  GraphicsEnvironment     com.akslabs.Suchak                   V  com.akslabs.Suchak is not listed in per-application setting
2025-08-04 13:17:19.656  5055-5055  GraphicsEnvironment     com.akslabs.Suchak                   V  Neither updatable production driver nor prerelease driver is supported.
2025-08-04 13:17:19.703  5055-5055  WM-WrkMgrInitializer    com.akslabs.Suchak                   D  Initializing WorkManager with default configuration.
2025-08-04 13:17:19.775  5055-5055  WM-PackageManagerHelper com.akslabs.Suchak                   D  Skipping component enablement for androidx.work.impl.background.systemjob.SystemJobService
2025-08-04 13:17:19.775  5055-5055  WM-Schedulers           com.akslabs.Suchak                   D  Created SystemJobScheduler and enabled SystemJobService
2025-08-04 13:17:19.857  5055-5055  .akslabs.Suchak         com.akslabs.Suchak                   W  Accessing hidden method Landroid/view/WindowManagerGlobal;->getInstance()Landroid/view/WindowManagerGlobal; (unsupported, reflection, allowed)
2025-08-04 13:17:19.857  5055-5055  .akslabs.Suchak         com.akslabs.Suchak                   W  Accessing hidden field Landroid/view/WindowManagerGlobal;->mViews:Ljava/util/ArrayList; (unsupported, reflection, allowed)
2025-08-04 13:17:19.858  5055-5055  .akslabs.Suchak         com.akslabs.Suchak                   W  Accessing hidden field Landroid/app/ActivityThread;->mH:Landroid/app/ActivityThread$H; (unsupported, reflection, allowed)
2025-08-04 13:17:19.858  5055-5055  .akslabs.Suchak         com.akslabs.Suchak                   W  Accessing hidden method Landroid/app/ActivityThread;->currentActivityThread()Landroid/app/ActivityThread; (unsupported, reflection, allowed)
2025-08-04 13:17:19.858  5055-5055  .akslabs.Suchak         com.akslabs.Suchak                   W  Accessing hidden field Landroid/os/Handler;->mCallback:Landroid/os/Handler$Callback; (unsupported, reflection, allowed)
2025-08-04 13:17:19.859  5055-5055  .akslabs.Suchak         com.akslabs.Suchak                   W  Accessing hidden field Landroid/util/Singleton;->mInstance:Ljava/lang/Object; (unsupported, reflection, allowed)
2025-08-04 13:17:19.859  5055-5055  .akslabs.Suchak         com.akslabs.Suchak                   W  Accessing hidden method Landroid/util/Singleton;->get()Ljava/lang/Object; (unsupported, reflection, allowed)
2025-08-04 13:17:19.860  5055-5055  .akslabs.Suchak         com.akslabs.Suchak                   W  Accessing hidden field Landroid/app/ActivityManager;->IActivityManagerSingleton:Landroid/util/Singleton; (unsupported, reflection, allowed)
2025-08-04 13:17:20.157  5055-5055  EngineFactory           com.akslabs.Suchak                   I  Provider GmsCore_OpenSSL not available
2025-08-04 13:17:20.181  5055-5055  ImageLoaderModule       com.akslabs.Suchak                   I  === INITIALIZING IMAGE LOADERS ===
2025-08-04 13:17:20.313  5055-5055  ImageLoaderModule       com.akslabs.Suchak                   D  Created OkHttpClient with 30s connect, 60s read/write timeouts
2025-08-04 13:17:20.313  5055-5055  ImageLoaderModule       com.akslabs.Suchak                   D  Creating remoteImageLoader...
2025-08-04 13:17:20.317  5055-5055  ImageLoaderModule       com.akslabs.Suchak                   I  remoteImageLoader created successfully
2025-08-04 13:17:20.317  5055-5055  ImageLoaderModule       com.akslabs.Suchak                   D  Creating thumbnailImageLoader...
2025-08-04 13:17:20.317  5055-5055  ImageLoaderModule       com.akslabs.Suchak                   I  thumbnailImageLoader created successfully
2025-08-04 13:17:20.317  5055-5055  ImageLoaderModule       com.akslabs.Suchak                   I  === IMAGE LOADERS INITIALIZATION COMPLETE ===
2025-08-04 13:17:20.317  5055-5055  ImageLoaderModule       com.akslabs.Suchak                   I  Available loaders:
2025-08-04 13:17:20.317  5055-5055  ImageLoaderModule       com.akslabs.Suchak                   I  - defaultImageLoader: ✓
2025-08-04 13:17:20.317  5055-5055  ImageLoaderModule       com.akslabs.Suchak                   I  - remoteImageLoader: ✓
2025-08-04 13:17:20.318  5055-5055  ImageLoaderModule       com.akslabs.Suchak                   I  - thumbnailImageLoader: ✓
2025-08-04 13:17:20.422  5055-5055  Choreographer           com.akslabs.Suchak                   I  Skipped 37 frames!  The application may be doing too much work on its main thread.
2025-08-04 13:17:20.497  5055-5092  LeakCanary              com.akslabs.Suchak                   D  LeakCanary is running and ready to detect memory leaks.
2025-08-04 13:17:20.550  5055-5096  DatabaseDebugHelper     com.akslabs.Suchak                   I  === DATABASE DEBUG REPORT ===
2025-08-04 13:17:20.576  5055-5096  DatabaseDebugHelper     com.akslabs.Suchak                   I  Database version: 5
2025-08-04 13:17:20.590  5055-5096  DatabaseDebugHelper     com.akslabs.Suchak                   I  Record counts:
2025-08-04 13:17:20.590  5055-5096  DatabaseDebugHelper     com.akslabs.Suchak                   I    Total photos: 0
2025-08-04 13:17:20.590  5055-5096  DatabaseDebugHelper     com.akslabs.Suchak                   I    Photos with remoteId: 0
2025-08-04 13:17:20.590  5055-5096  DatabaseDebugHelper     com.akslabs.Suchak                   I    Total remote photos: 0
2025-08-04 13:17:20.590  5055-5096  DatabaseDebugHelper     com.akslabs.Suchak                   I  === END DATABASE DEBUG REPORT ===
2025-08-04 13:17:20.594  5055-5055  DesktopModeFlagsUtil    com.akslabs.Suchak                   D  Toggle override initialized to: OVERRIDE_UNSET
2025-08-04 13:17:20.676  5055-5055  .akslabs.Suchak         com.akslabs.Suchak                   W  Accessing hidden field Lcom/android/internal/policy/DecorView;->mWindow:Lcom/android/internal/policy/PhoneWindow; (unsupported, reflection, allowed)
2025-08-04 13:17:20.953  5055-5077  .akslabs.Suchak         com.akslabs.Suchak                   I  Compiler allocated 4431KB to compile void android.view.ViewRootImpl.performTraversals()
2025-08-04 13:17:21.160  5055-5055  .akslabs.Suchak         com.akslabs.Suchak                   W  Method boolean androidx.compose.runtime.snapshots.SnapshotStateList.conditionalUpdate(boolean, kotlin.jvm.functions.Function1) failed lock verification and will run slower.
                                                                                                    Common causes for lock verification issues are non-optimized dex code
                                                                                                    and incorrect proguard optimizations.
2025-08-04 13:17:21.160  5055-5055  .akslabs.Suchak         com.akslabs.Suchak                   W  Method boolean androidx.compose.runtime.snapshots.SnapshotStateList.conditionalUpdate$default(androidx.compose.runtime.snapshots.SnapshotStateList, boolean, kotlin.jvm.functions.Function1, int, java.lang.Object) failed lock verification and will run slower.
2025-08-04 13:17:21.160  5055-5055  .akslabs.Suchak         com.akslabs.Suchak                   W  Method java.lang.Object androidx.compose.runtime.snapshots.SnapshotStateList.mutate(kotlin.jvm.functions.Function1) failed lock verification and will run slower.
2025-08-04 13:17:21.160  5055-5055  .akslabs.Suchak         com.akslabs.Suchak                   W  Method void androidx.compose.runtime.snapshots.SnapshotStateList.update(boolean, kotlin.jvm.functions.Function1) failed lock verification and will run slower.
2025-08-04 13:17:21.161  5055-5055  .akslabs.Suchak         com.akslabs.Suchak                   W  Method void androidx.compose.runtime.snapshots.SnapshotStateList.update$default(androidx.compose.runtime.snapshots.SnapshotStateList, boolean, kotlin.jvm.functions.Function1, int, java.lang.Object) failed lock verification and will run slower.
2025-08-04 13:17:21.836  5055-5100  AdrenoGLES-0            com.akslabs.Suchak                   I  QUALCOMM build                   : 95db91f, Ifbc588260a
                                                                                                    Build Date                       : 09/24/20
                                                                                                    OpenGL ES Shader Compiler Version: EV031.32.02.01
                                                                                                    Local Branch                     : mybrancheafe5b6d-fb5b-f1b0-b904-5cb90179c3e0
                                                                                                    Remote Branch                    : quic/gfx-adreno.lnx.1.0.r114-rel
                                                                                                    Remote Branch                    : NONE
                                                                                                    Reconstruct Branch               : NOTHING
2025-08-04 13:17:21.837  5055-5100  AdrenoGLES-0            com.akslabs.Suchak                   I  Build Config                     : S P 10.0.7 AArch64
2025-08-04 13:17:21.837  5055-5100  AdrenoGLES-0            com.akslabs.Suchak                   I  Driver Path                      : /vendor/lib64/egl/libGLESv2_adreno.so
2025-08-04 13:17:21.852  5055-5100  AdrenoGLES-0            com.akslabs.Suchak                   I  PFP: 0x016ee190, ME: 0x00000000
2025-08-04 13:17:21.903  5055-5111  Gralloc4                com.akslabs.Suchak                   I  mapper 4.x is not supported
2025-08-04 13:17:21.904  5055-5111  Gralloc3                com.akslabs.Suchak                   W  mapper 3.x is not supported
2025-08-04 13:17:21.914  5055-5111  Gralloc2                com.akslabs.Suchak                   I  Adding additional valid usage bits: 0x8202000
2025-08-04 13:17:22.012  5055-5083  HWUI                    com.akslabs.Suchak                   I  Davey! duration=1289ms; Flags=1, FrameTimelineVsyncId=15417068, IntendedVsync=26916266636149, Vsync=26916266636149, InputEventId=0, HandleInputStart=26916276527856, AnimationStart=26916276530199, PerformTraversalsStart=26916276531293, DrawStart=26917469842438, FrameDeadline=26916287969481, FrameInterval=26916276519053, FrameStartTime=16666569, SyncQueued=26917527626397, SyncStart=26917527785876, IssueDrawCommandsStart=26917528440251, SwapBuffers=26917547534313, FrameCompleted=26917555833168, DequeueBufferDuration=15365, QueueBufferDuration=589428, GpuCompleted=26917555833168, SwapBuffersCompleted=26917549113480, DisplayPresentTime=8929200823284531200, CommandSubmissionCompleted=26917547534313, 
2025-08-04 13:17:22.117  5055-5055  Choreographer           com.akslabs.Suchak                   I  Skipped 80 frames!  The application may be doing too much work on its main thread.
2025-08-04 13:17:22.236  5055-5084  HWUI                    com.akslabs.Suchak                   I  Davey! duration=1435ms; Flags=0, FrameTimelineVsyncId=15417161, IntendedVsync=26916352635701, Vsync=26917685961861, InputEventId=0, HandleInputStart=26917690472751, AnimationStart=26917690475667, PerformTraversalsStart=26917764820772, DrawStart=26917765133220, FrameDeadline=26917583295197, FrameInterval=26917690085147, FrameStartTime=16666577, SyncQueued=26917783804522, SyncStart=26917783887386, IssueDrawCommandsStart=26917784021449, SwapBuffers=26917785265251, FrameCompleted=26917788012386, DequeueBufferDuration=16198, QueueBufferDuration=231146, GpuCompleted=26917788012386, SwapBuffersCompleted=26917786461970, DisplayPresentTime=72904454214516736, CommandSubmissionCompleted=26917785265251, 
2025-08-04 13:17:22.620  5055-5096  ContentValues           com.akslabs.Suchak                   D  doWork: []
2025-08-04 13:17:22.620  5055-5096  Sync MediaStore         com.akslabs.Suchak                   D  doWork: Success
2025-08-04 13:17:22.622  5055-5095  WM-WorkerWrapper        com.akslabs.Suchak                   I  Worker result SUCCESS for Work [ id=735b0b23-03c9-45c8-bdb7-6e503ec5cfb6, tags={ com.akslabs.Suchak.workers.SyncDbMediaStoreWorker } ]
2025-08-04 13:17:23.579  5055-5055  Choreographer           com.akslabs.Suchak                   I  Skipped 46 frames!  The application may be doing too much work on its main thread.
2025-08-04 13:17:23.586  5055-5086  HWUI                    com.akslabs.Suchak                   I  Davey! duration=913ms; Flags=0, FrameTimelineVsyncId=15419103, IntendedVsync=26918235982805, Vsync=26918352650601, InputEventId=0, HandleInputStart=26918365483740, AnimationStart=26918365488063, PerformTraversalsStart=26918432951032, DrawStart=26918433054053, FrameDeadline=26918369322851, FrameInterval=26918365099053, FrameStartTime=16666828, SyncQueued=26919126391240, SyncStart=26919126664782, IssueDrawCommandsStart=26919127386657, SwapBuffers=26919143535354, FrameCompleted=26919149278479, DequeueBufferDuration=29844, QueueBufferDuration=318177, GpuCompleted=26919149278479, SwapBuffersCompleted=26919144370719, DisplayPresentTime=3397062777423003648, CommandSubmissionCompleted=26919143535354, 
2025-08-04 13:17:23.586  5055-5086  HWUI                    com.akslabs.Suchak                   I  Davey! duration=898ms; Flags=0, FrameTimelineVsyncId=15419103, IntendedVsync=26918235982805, Vsync=26918352650601, InputEventId=0, HandleInputStart=26918365483740, AnimationStart=26918365488063, PerformTraversalsStart=26918432951032, DrawStart=26919127605563, FrameDeadline=26918369322851, FrameInterval=26918365099053, FrameStartTime=16666828, SyncQueued=26919129170146, SyncStart=26919144440563, IssueDrawCommandsStart=26919145008479, SwapBuffers=26919146069834, FrameCompleted=26919149877594, DequeueBufferDuration=20625, QueueBufferDuration=389270, GpuCompleted=26919149449156, SwapBuffersCompleted=26919149877594, DisplayPresentTime=529106872368, CommandSubmissionCompleted=26919146069834, 
2025-08-04 13:17:23.641  5055-5055  WindowOnBackDispatcher  com.akslabs.Suchak                   W  sendCancelIfRunning: isInProgress=false callback=androidx.activity.OnBackPressedDispatcher$Api34Impl$createOnBackAnimationCallback$1@7b5b97d
2025-08-04 13:17:23.701  5055-5055  .akslabs.Suchak         com.akslabs.Suchak                   W  Accessing hidden field Landroid/app/ActivityThread;->mServices:Landroid/util/ArrayMap; (unsupported, reflection, allowed)
2025-08-04 13:17:23.702  5055-5055  LeakCanary              com.akslabs.Suchak                   D  Watching instance of androidx.work.impl.background.systemjob.SystemJobService (androidx.work.impl.background.systemjob.SystemJobService received Service#onDestroy() callback) with key 1b4f8737-ffbc-4d53-ab45-54a3b56ecbd0
2025-08-04 13:17:23.717  5055-5083  HWUI                    com.akslabs.Suchak                   I  Davey! duration=909ms; Flags=0, FrameTimelineVsyncId=15419256, IntendedVsync=26918369314458, Vsync=26919135981508, InputEventId=0, HandleInputStart=26919152813688, AnimationStart=26919152816292, PerformTraversalsStart=26919251900927, DrawStart=26919269099990, FrameDeadline=26919169325011, FrameInterval=26919152403479, FrameStartTime=16666675, SyncQueued=26919272016188, SyncStart=26919272234625, IssueDrawCommandsStart=26919272463948, SwapBuffers=26919274149261, FrameCompleted=26919278545302, DequeueBufferDuration=17813, QueueBufferDuration=193541, GpuCompleted=26919278545302, SwapBuffersCompleted=26919274799365, DisplayPresentTime=72904454231491835, CommandSubmissionCompleted=26919274149261, 
2025-08-04 13:17:26.407  5055-5130  ProfileInstaller        com.akslabs.Suchak                   D  Installing profile for com.akslabs.Suchak
2025-08-04 13:17:28.766  5055-5092  .akslabs.Suchak         com.akslabs.Suchak                   W  Cleared Reference was only reachable from finalizer (only reported once)
2025-08-04 13:17:28.771  5055-5092  .akslabs.Suchak         com.akslabs.Suchak                   I  Explicit concurrent copying GC freed 5416KB AllocSpace bytes, 16(512KB) LOS objects, 49% free, 7101KB/13MB, paused 56us,38us total 55.806ms
2025-08-04 13:19:16.710  5055-5055  Choreographer           com.akslabs.Suchak                   I  Skipped 49 frames!  The application may be doing too much work on its main thread.
2025-08-04 13:19:16.723  5055-5096  BackupHelper            com.akslabs.Suchak                   D  Backup status - Has backup: true, Data unchanged: true
2025-08-04 13:19:16.724  5055-5096  BackupHelper            com.akslabs.Suchak                   D  Current: 0 photos, 0 remote | Last backup: 0 photos, 0 remote
2025-08-04 13:19:16.803  5055-5256  HWUI                    com.akslabs.Suchak                   I  Davey! duration=910ms; Flags=0, FrameTimelineVsyncId=15506310, IntendedVsync=27031452651944, Vsync=27032269302555, InputEventId=590082990, HandleInputStart=27032284426770, AnimationStart=27032284429738, PerformTraversalsStart=27032316958853, DrawStart=27032317052447, FrameDeadline=27032102640713, FrameInterval=27032283923280, FrameStartTime=16666351, SyncQueued=27032342346509, SyncStart=27032342724790, IssueDrawCommandsStart=27032343003072, SwapBuffers=27032351269061, FrameCompleted=27032363967447, DequeueBufferDuration=17084, QueueBufferDuration=293646, GpuCompleted=27032363967447, SwapBuffersCompleted=27032351842811, DisplayPresentTime=0, CommandSubmissionCompleted=27032351269061, 
2025-08-04 13:19:21.352  5055-5055  VRI[MainActivity]       com.akslabs.Suchak                   D  visibilityChanged oldVisibility=true newVisibility=false
2025-08-04 13:19:21.895  5055-5055  WindowOnBackDispatcher  com.akslabs.Suchak                   W  sendCancelIfRunning: isInProgress=false callback=androidx.activity.OnBackPressedDispatcher$Api34Impl$createOnBackAnimationCallback$1@672f8b7
2025-08-04 13:19:23.744  5055-5096  BackupHelper            com.akslabs.Suchak                   I  Creating backup: 0 photos, 0 remote photos, 405 SMS, 145 remote SMS
2025-08-04 13:19:25.825  5055-5055  Choreographer           com.akslabs.Suchak                   I  Skipped 31 frames!  The application may be doing too much work on its main thread.
2025-08-04 13:19:27.108  5055-5055  WindowOnBackDispatcher  com.akslabs.Suchak                   W  sendCancelIfRunning: isInProgress=false callback=androidx.activity.OnBackPressedDispatcher$Api34Impl$createOnBackAnimationCallback$1@672f8b7
2025-08-04 13:19:44.782  5594-5594  .akslabs.Suchak         com.akslabs.Suchak                   I  Late-enabling -Xcheck:jni
2025-08-04 13:19:44.838  5594-5594  .akslabs.Suchak         com.akslabs.Suchak                   I  Using CollectorTypeCC GC.
2025-08-04 13:19:44.859  5594-5594  nativeloader            com.akslabs.Suchak                   D  Load libframework-connectivity-tiramisu-jni.so using APEX ns com_android_tethering for caller /apex/com.android.tethering/javalib/framework-connectivity-t.jar: ok
2025-08-04 13:19:44.942  5594-5594  ApplicationLoaders      com.akslabs.Suchak                   D  Returning zygote-cached class loader: /system_ext/framework/androidx.window.extensions.jar
2025-08-04 13:19:44.942  5594-5594  ApplicationLoaders      com.akslabs.Suchak                   D  Returning zygote-cached class loader: /system_ext/framework/androidx.window.sidecar.jar
2025-08-04 13:19:44.943  5594-5594  ziparchive              com.akslabs.Suchak                   W  Unable to open '/data/app/~~GQAOdaftQIEMP0dxF6S1TA==/com.akslabs.Suchak-KKSxtNoNtXOk5aUef31tRg==/base.dm': No such file or directory
2025-08-04 13:19:44.943  5594-5594  ziparchive              com.akslabs.Suchak                   W  Unable to open '/data/app/~~GQAOdaftQIEMP0dxF6S1TA==/com.akslabs.Suchak-KKSxtNoNtXOk5aUef31tRg==/base.dm': No such file or directory
2025-08-04 13:19:45.738  5594-5594  nativeloader            com.akslabs.Suchak                   D  Configuring clns-7 for other apk /data/app/~~GQAOdaftQIEMP0dxF6S1TA==/com.akslabs.Suchak-KKSxtNoNtXOk5aUef31tRg==/base.apk. target_sdk_version=34, uses_libraries=, library_path=/data/app/~~GQAOdaftQIEMP0dxF6S1TA==/com.akslabs.Suchak-KKSxtNoNtXOk5aUef31tRg==/lib/arm64:/data/app/~~GQAOdaftQIEMP0dxF6S1TA==/com.akslabs.Suchak-KKSxtNoNtXOk5aUef31tRg==/base.apk!/lib/arm64-v8a, permitted_path=/data:/mnt/expand:/data/user/0/com.akslabs.Suchak
2025-08-04 13:19:45.762  5594-5594  GraphicsEnvironment     com.akslabs.Suchak                   V  Currently set values for:
2025-08-04 13:19:45.762  5594-5594  GraphicsEnvironment     com.akslabs.Suchak                   V    angle_gl_driver_selection_pkgs=[com.android.angle, com.linecorp.b612.android, com.campmobile.snow, com.google.android.apps.tachyon]
2025-08-04 13:19:45.762  5594-5594  GraphicsEnvironment     com.akslabs.Suchak                   V    angle_gl_driver_selection_values=[angle, native, native, native]
2025-08-04 13:19:45.762  5594-5594  GraphicsEnvironment     com.akslabs.Suchak                   V  com.akslabs.Suchak is not listed in per-application setting
2025-08-04 13:19:45.762  5594-5594  GraphicsEnvironment     com.akslabs.Suchak                   V  Neither updatable production driver nor prerelease driver is supported.
2025-08-04 13:19:45.781  5594-5594  WM-WrkMgrInitializer    com.akslabs.Suchak                   D  Initializing WorkManager with default configuration.
2025-08-04 13:19:45.850  5594-5594  WM-PackageManagerHelper com.akslabs.Suchak                   D  Skipping component enablement for androidx.work.impl.background.systemjob.SystemJobService
2025-08-04 13:19:45.850  5594-5594  WM-Schedulers           com.akslabs.Suchak                   D  Created SystemJobScheduler and enabled SystemJobService
2025-08-04 13:19:46.026  5594-5594  .akslabs.Suchak         com.akslabs.Suchak                   W  Accessing hidden method Landroid/view/WindowManagerGlobal;->getInstance()Landroid/view/WindowManagerGlobal; (unsupported, reflection, allowed)
2025-08-04 13:19:46.027  5594-5594  .akslabs.Suchak         com.akslabs.Suchak                   W  Accessing hidden field Landroid/view/WindowManagerGlobal;->mViews:Ljava/util/ArrayList; (unsupported, reflection, allowed)
2025-08-04 13:19:46.028  5594-5594  .akslabs.Suchak         com.akslabs.Suchak                   W  Accessing hidden field Landroid/app/ActivityThread;->mH:Landroid/app/ActivityThread$H; (unsupported, reflection, allowed)
2025-08-04 13:19:46.028  5594-5594  .akslabs.Suchak         com.akslabs.Suchak                   W  Accessing hidden method Landroid/app/ActivityThread;->currentActivityThread()Landroid/app/ActivityThread; (unsupported, reflection, allowed)
2025-08-04 13:19:46.028  5594-5594  .akslabs.Suchak         com.akslabs.Suchak                   W  Accessing hidden field Landroid/os/Handler;->mCallback:Landroid/os/Handler$Callback; (unsupported, reflection, allowed)
2025-08-04 13:19:46.030  5594-5594  .akslabs.Suchak         com.akslabs.Suchak                   W  Accessing hidden field Landroid/util/Singleton;->mInstance:Ljava/lang/Object; (unsupported, reflection, allowed)
2025-08-04 13:19:46.030  5594-5594  .akslabs.Suchak         com.akslabs.Suchak                   W  Accessing hidden method Landroid/util/Singleton;->get()Ljava/lang/Object; (unsupported, reflection, allowed)
2025-08-04 13:19:46.030  5594-5594  .akslabs.Suchak         com.akslabs.Suchak                   W  Accessing hidden field Landroid/app/ActivityManager;->IActivityManagerSingleton:Landroid/util/Singleton; (unsupported, reflection, allowed)
2025-08-04 13:19:46.293  5594-5594  AndroidKeysetManager    com.akslabs.Suchak                   W  keyset not found, will generate a new one
                                                                                                    java.io.FileNotFoundException: can't read keyset; the pref value __androidx_security_crypto_encrypted_prefs_key_keyset__ does not exist
                                                                                                    	at com.google.crypto.tink.integration.android.SharedPrefKeysetReader.readPref(SharedPrefKeysetReader.java:71)
                                                                                                    	at com.google.crypto.tink.integration.android.SharedPrefKeysetReader.readEncrypted(SharedPrefKeysetReader.java:89)
                                                                                                    	at com.google.crypto.tink.KeysetHandle.read(KeysetHandle.java:105)
                                                                                                    	at com.google.crypto.tink.integration.android.AndroidKeysetManager$Builder.read(AndroidKeysetManager.java:311)
                                                                                                    	at com.google.crypto.tink.integration.android.AndroidKeysetManager$Builder.readOrGenerateNewKeyset(AndroidKeysetManager.java:287)
                                                                                                    	at com.google.crypto.tink.integration.android.AndroidKeysetManager$Builder.build(AndroidKeysetManager.java:238)
                                                                                                    	at androidx.security.crypto.EncryptedSharedPreferences.create(EncryptedSharedPreferences.java:123)
                                                                                                    	at com.akslabs.Suchak.data.localdb.Preferences.init(Preferences.kt:37)
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
2025-08-04 13:19:46.372  5594-5594  AndroidKeysetManager    com.akslabs.Suchak                   W  keyset not found, will generate a new one
                                                                                                    java.io.FileNotFoundException: can't read keyset; the pref value __androidx_security_crypto_encrypted_prefs_value_keyset__ does not exist
                                                                                                    	at com.google.crypto.tink.integration.android.SharedPrefKeysetReader.readPref(SharedPrefKeysetReader.java:71)
                                                                                                    	at com.google.crypto.tink.integration.android.SharedPrefKeysetReader.readEncrypted(SharedPrefKeysetReader.java:89)
                                                                                                    	at com.google.crypto.tink.KeysetHandle.read(KeysetHandle.java:105)
                                                                                                    	at com.google.crypto.tink.integration.android.AndroidKeysetManager$Builder.read(AndroidKeysetManager.java:311)
                                                                                                    	at com.google.crypto.tink.integration.android.AndroidKeysetManager$Builder.readOrGenerateNewKeyset(AndroidKeysetManager.java:287)
                                                                                                    	at com.google.crypto.tink.integration.android.AndroidKeysetManager$Builder.build(AndroidKeysetManager.java:238)
                                                                                                    	at androidx.security.crypto.EncryptedSharedPreferences.create(EncryptedSharedPreferences.java:128)
                                                                                                    	at com.akslabs.Suchak.data.localdb.Preferences.init(Preferences.kt:37)
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
2025-08-04 13:19:46.406  5594-5594  EngineFactory           com.akslabs.Suchak                   I  Provider GmsCore_OpenSSL not available
2025-08-04 13:19:46.418  5594-5594  ImageLoaderModule       com.akslabs.Suchak                   I  === INITIALIZING IMAGE LOADERS ===
2025-08-04 13:19:46.509  5594-5594  ImageLoaderModule       com.akslabs.Suchak                   D  Created OkHttpClient with 30s connect, 60s read/write timeouts
2025-08-04 13:19:46.509  5594-5594  ImageLoaderModule       com.akslabs.Suchak                   D  Creating remoteImageLoader...
2025-08-04 13:19:46.512  5594-5594  ImageLoaderModule       com.akslabs.Suchak                   I  remoteImageLoader created successfully
2025-08-04 13:19:46.512  5594-5594  ImageLoaderModule       com.akslabs.Suchak                   D  Creating thumbnailImageLoader...
2025-08-04 13:19:46.513  5594-5594  ImageLoaderModule       com.akslabs.Suchak                   I  thumbnailImageLoader created successfully
2025-08-04 13:19:46.513  5594-5594  ImageLoaderModule       com.akslabs.Suchak                   I  === IMAGE LOADERS INITIALIZATION COMPLETE ===
2025-08-04 13:19:46.513  5594-5594  ImageLoaderModule       com.akslabs.Suchak                   I  Available loaders:
2025-08-04 13:19:46.513  5594-5594  ImageLoaderModule       com.akslabs.Suchak                   I  - defaultImageLoader: ✓
2025-08-04 13:19:46.513  5594-5594  ImageLoaderModule       com.akslabs.Suchak                   I  - remoteImageLoader: ✓
2025-08-04 13:19:46.513  5594-5594  ImageLoaderModule       com.akslabs.Suchak                   I  - thumbnailImageLoader: ✓
2025-08-04 13:19:46.611  5594-5594  Choreographer           com.akslabs.Suchak                   I  Skipped 44 frames!  The application may be doing too much work on its main thread.
2025-08-04 13:19:46.619  5594-5625  LeakCanary              com.akslabs.Suchak                   D  LeakCanary is running and ready to detect memory leaks.
2025-08-04 13:19:46.686  5594-5640  DatabaseDebugHelper     com.akslabs.Suchak                   I  === DATABASE DEBUG REPORT ===
2025-08-04 13:19:46.722  5594-5640  DatabaseDebugHelper     com.akslabs.Suchak                   I  Database version: 5
2025-08-04 13:19:46.722  5594-5594  DesktopModeFlagsUtil    com.akslabs.Suchak                   D  Toggle override initialized to: OVERRIDE_UNSET
2025-08-04 13:19:46.734  5594-5640  DatabaseDebugHelper     com.akslabs.Suchak                   I  Record counts:
2025-08-04 13:19:46.734  5594-5640  DatabaseDebugHelper     com.akslabs.Suchak                   I    Total photos: 0
2025-08-04 13:19:46.734  5594-5640  DatabaseDebugHelper     com.akslabs.Suchak                   I    Photos with remoteId: 0
2025-08-04 13:19:46.734  5594-5640  DatabaseDebugHelper     com.akslabs.Suchak                   I    Total remote photos: 0
2025-08-04 13:19:46.735  5594-5640  DatabaseDebugHelper     com.akslabs.Suchak                   I  === END DATABASE DEBUG REPORT ===
2025-08-04 13:19:46.810  5594-5594  .akslabs.Suchak         com.akslabs.Suchak                   W  Accessing hidden field Lcom/android/internal/policy/DecorView;->mWindow:Lcom/android/internal/policy/PhoneWindow; (unsupported, reflection, allowed)
2025-08-04 13:19:47.157  5594-5600  .akslabs.Suchak         com.akslabs.Suchak                   I  Compiler allocated 4431KB to compile void android.view.ViewRootImpl.performTraversals()
2025-08-04 13:19:47.298  5594-5594  .akslabs.Suchak         com.akslabs.Suchak                   W  Method boolean androidx.compose.runtime.snapshots.SnapshotStateList.conditionalUpdate(boolean, kotlin.jvm.functions.Function1) failed lock verification and will run slower.
                                                                                                    Common causes for lock verification issues are non-optimized dex code
                                                                                                    and incorrect proguard optimizations.
2025-08-04 13:19:47.298  5594-5594  .akslabs.Suchak         com.akslabs.Suchak                   W  Method boolean androidx.compose.runtime.snapshots.SnapshotStateList.conditionalUpdate$default(androidx.compose.runtime.snapshots.SnapshotStateList, boolean, kotlin.jvm.functions.Function1, int, java.lang.Object) failed lock verification and will run slower.
2025-08-04 13:19:47.298  5594-5594  .akslabs.Suchak         com.akslabs.Suchak                   W  Method java.lang.Object androidx.compose.runtime.snapshots.SnapshotStateList.mutate(kotlin.jvm.functions.Function1) failed lock verification and will run slower.
2025-08-04 13:19:47.299  5594-5594  .akslabs.Suchak         com.akslabs.Suchak                   W  Method void androidx.compose.runtime.snapshots.SnapshotStateList.update(boolean, kotlin.jvm.functions.Function1) failed lock verification and will run slower.
2025-08-04 13:19:47.299  5594-5594  .akslabs.Suchak         com.akslabs.Suchak                   W  Method void androidx.compose.runtime.snapshots.SnapshotStateList.update$default(androidx.compose.runtime.snapshots.SnapshotStateList, boolean, kotlin.jvm.functions.Function1, int, java.lang.Object) failed lock verification and will run slower.
2025-08-04 13:19:47.905  5594-5644  AdrenoGLES-0            com.akslabs.Suchak                   I  QUALCOMM build                   : 95db91f, Ifbc588260a
                                                                                                    Build Date                       : 09/24/20
                                                                                                    OpenGL ES Shader Compiler Version: EV031.32.02.01
                                                                                                    Local Branch                     : mybrancheafe5b6d-fb5b-f1b0-b904-5cb90179c3e0
                                                                                                    Remote Branch                    : quic/gfx-adreno.lnx.1.0.r114-rel
                                                                                                    Remote Branch                    : NONE
                                                                                                    Reconstruct Branch               : NOTHING
2025-08-04 13:19:47.905  5594-5644  AdrenoGLES-0            com.akslabs.Suchak                   I  Build Config                     : S P 10.0.7 AArch64
2025-08-04 13:19:47.905  5594-5644  AdrenoGLES-0            com.akslabs.Suchak                   I  Driver Path                      : /vendor/lib64/egl/libGLESv2_adreno.so
2025-08-04 13:19:47.911  5594-5644  AdrenoGLES-0            com.akslabs.Suchak                   I  PFP: 0x016ee190, ME: 0x00000000
2025-08-04 13:19:47.962  5594-5654  Gralloc4                com.akslabs.Suchak                   I  mapper 4.x is not supported
2025-08-04 13:19:47.962  5594-5654  Gralloc3                com.akslabs.Suchak                   W  mapper 3.x is not supported
2025-08-04 13:19:47.967  5594-5654  Gralloc2                com.akslabs.Suchak                   I  Adding additional valid usage bits: 0x8202000
2025-08-04 13:19:48.129  5594-5605  HWUI                    com.akslabs.Suchak                   I  Davey! duration=1285ms; Flags=1, FrameTimelineVsyncId=15538336, IntendedVsync=27062399981329, Vsync=27062399981329, InputEventId=0, HandleInputStart=27062406588841, AnimationStart=27062406593164, PerformTraversalsStart=27062406595144, DrawStart=27063527199258, FrameDeadline=27062421314661, FrameInterval=27062406574206, FrameStartTime=16666613, SyncQueued=27063554839258, SyncStart=27063555014153, IssueDrawCommandsStart=27063556731289, SwapBuffers=27063677948945, FrameCompleted=27063685723841, DequeueBufferDuration=21719, QueueBufferDuration=377448, GpuCompleted=27063685723841, SwapBuffersCompleted=27063678773164, DisplayPresentTime=18013092839424, CommandSubmissionCompleted=27063677948945, 
2025-08-04 13:19:48.133  5594-5594  Choreographer           com.akslabs.Suchak                   I  Skipped 73 frames!  The application may be doing too much work on its main thread.
2025-08-04 13:19:48.290  5594-5640  SmsSyncWorker           com.akslabs.Suchak                   I  === SMS SYNC WORKER STARTED ===
2025-08-04 13:19:48.301  5594-5649  WM-Processor            com.akslabs.Suchak                   I  Moving WorkSpec (5362d2ee-b2ac-4ccf-9049-a45dbcabb7bb) to the foreground
2025-08-04 13:19:48.312  5594-5609  HWUI                    com.akslabs.Suchak                   I  Davey! duration=1370ms; Flags=1, FrameTimelineVsyncId=15538429, IntendedVsync=27062485984883, Vsync=27063702650260, InputEventId=0, HandleInputStart=27063707746393, AnimationStart=27063707749831, PerformTraversalsStart=27063715332747, DrawStart=27063842172591, FrameDeadline=27062502651549, FrameInterval=27063706952174, FrameStartTime=16666649, SyncQueued=27063850815195, SyncStart=27063851118268, IssueDrawCommandsStart=27063851223580, SwapBuffers=27063853766758, FrameCompleted=27063856905976, DequeueBufferDuration=14271, QueueBufferDuration=305781, GpuCompleted=27063856905976, SwapBuffersCompleted=27063854696080, DisplayPresentTime=0, CommandSubmissionCompleted=27063853766758, 
2025-08-04 13:19:48.312  5594-5609  HWUI                    com.akslabs.Suchak                   I  Davey! duration=1377ms; Flags=1, FrameTimelineVsyncId=15538429, IntendedVsync=27062485984883, Vsync=27063702650260, InputEventId=0, HandleInputStart=27063707746393, AnimationStart=27063707749831, PerformTraversalsStart=27063715332747, DrawStart=27063855531914, FrameDeadline=27063716643809, FrameInterval=27063706952174, FrameStartTime=16666649, SyncQueued=27063859674258, SyncStart=27063860144205, IssueDrawCommandsStart=27063860233060, SwapBuffers=27063861383216, FrameCompleted=27063863957070, DequeueBufferDuration=20312, QueueBufferDuration=273334, GpuCompleted=27063863957070, SwapBuffersCompleted=27063862283528, DisplayPresentTime=72904454214516736, CommandSubmissionCompleted=27063861383216, 
2025-08-04 13:19:48.326  5594-5640  SmsSyncService          com.akslabs.Suchak                   I  === STARTING FULL SMS SYNC ===
2025-08-04 13:19:48.341  5594-5640  SmsSyncService          com.akslabs.Suchak                   W  No channel ID configured for SMS sync
2025-08-04 13:19:48.341  5594-5640  SmsSyncWorker           com.akslabs.Suchak                   E  SMS sync failed: No Telegram channel configured
2025-08-04 13:19:48.341  5594-5640  SmsSyncWorker           com.akslabs.Suchak                   I  === SMS SYNC WORKER FINISHED ===
2025-08-04 13:19:48.343  5594-5647  WM-WorkerWrapper        com.akslabs.Suchak                   I  Worker result FAILURE for Work [ id=5362d2ee-b2ac-4ccf-9049-a45dbcabb7bb, tags={ com.akslabs.chitralaya.workers.SmsSyncWorker } ]
2025-08-04 13:19:48.343  5594-5594  WM-SystemFgDispatcher   com.akslabs.Suchak                   I  Started foreground service Intent { act=ACTION_START_FOREGROUND cmp=com.akslabs.Suchak/androidx.work.impl.foreground.SystemForegroundService (has extras) }
2025-08-04 13:19:48.372  5594-5594  WM-SystemFgDispatcher   com.akslabs.Suchak                   I  Stopping foreground service
2025-08-04 13:19:48.380  5594-5594  .akslabs.Suchak         com.akslabs.Suchak                   W  Accessing hidden field Landroid/app/ActivityThread;->mServices:Landroid/util/ArrayMap; (unsupported, reflection, allowed)
2025-08-04 13:19:48.382  5594-5594  LeakCanary              com.akslabs.Suchak                   D  Watching instance of androidx.work.impl.foreground.SystemForegroundService (androidx.work.impl.foreground.SystemForegroundService received Service#onDestroy() callback) with key 85576613-d07f-4b50-a5eb-646d5a8733d5
2025-08-04 13:19:48.384  5594-5594  LeakCanary              com.akslabs.Suchak                   D  Watching instance of androidx.work.impl.background.systemjob.SystemJobService (androidx.work.impl.background.systemjob.SystemJobService received Service#onDestroy() callback) with key e96b2160-ac6d-4b47-87dd-2cd0f2a023a8
2025-08-04 13:19:51.720  5594-5594  WindowOnBackDispatcher  com.akslabs.Suchak                   W  sendCancelIfRunning: isInProgress=false callback=androidx.activity.OnBackPressedDispatcher$Api34Impl$createOnBackAnimationCallback$1@bc25f70
2025-08-04 13:19:52.501  5594-5678  ProfileInstaller        com.akslabs.Suchak                   D  Installing profile for com.akslabs.Suchak
2025-08-04 13:19:52.825  5594-5594  InsetsController        com.akslabs.Suchak                   D  show(ime(), fromIme=false)
2025-08-04 13:19:52.827  5594-5594  ImeTracker              com.akslabs.Suchak                   I  com.akslabs.Suchak:3c3df090: onRequestShow at ORIGIN_CLIENT reason SHOW_SOFT_INPUT_BY_INSETS_API fromUser false
2025-08-04 13:19:52.828  5594-5594  InputMethodManager      com.akslabs.Suchak                   D  showSoftInput() view=androidx.compose.ui.platform.AndroidComposeView{d6faa14 VFED..... .F....ID 0,0-1080,2263 aid=1073741825} flags=0 reason=SHOW_SOFT_INPUT_BY_INSETS_API
2025-08-04 13:19:52.865  5594-5594  ImeTracker              com.akslabs.Suchak                   I  com.akslabs.Suchak:b077f1b9: onRequestShow at ORIGIN_CLIENT reason SHOW_SOFT_INPUT fromUser false
2025-08-04 13:19:52.865  5594-5594  InputMethodManager      com.akslabs.Suchak                   D  showSoftInput() view=androidx.compose.ui.platform.AndroidComposeView{d6faa14 VFED..... .F...... 0,0-1080,2263 aid=1073741825} flags=0 reason=SHOW_SOFT_INPUT
2025-08-04 13:19:53.172  5594-5594  InsetsController        com.akslabs.Suchak                   D  show(ime(), fromIme=true)
2025-08-04 13:19:53.187  5594-5681  InteractionJankMonitor  com.akslabs.Suchak                   W  Initializing without READ_DEVICE_CONFIG permission. enabled=false, interval=1, missedFrameThreshold=3, frameTimeThreshold=64, package=com.akslabs.Suchak
2025-08-04 13:19:53.422  5594-5594  ImeTracker              com.akslabs.Suchak                   I  com.akslabs.Suchak:b077f1b9: onShown
2025-08-04 13:19:53.480  5594-5625  .akslabs.Suchak         com.akslabs.Suchak                   W  Cleared Reference was only reachable from finalizer (only reported once)
2025-08-04 13:19:53.486  5594-5625  .akslabs.Suchak         com.akslabs.Suchak                   I  Explicit concurrent copying GC freed 5762KB AllocSpace bytes, 20(784KB) LOS objects, 49% free, 6280KB/12MB, paused 248us,40us total 84.598ms
2025-08-04 13:20:00.773  5594-5594  WindowOnBackDispatcher  com.akslabs.Suchak                   W  sendCancelIfRunning: isInProgress=false callback=ImeCallback=ImeOnBackInvokedCallback@197612445 Callback=android.window.IOnBackInvokedCallback$Stub$Proxy@e28fc97
2025-08-04 13:20:00.774  5594-5594  InsetsController        com.akslabs.Suchak                   D  hide(ime(), fromIme=true)
2025-08-04 13:20:01.034  5594-5594  ImeTracker              com.akslabs.Suchak                   I  com.akslabs.Suchak:af30603a: onRequestHide at ORIGIN_CLIENT reason HIDE_SOFT_INPUT_ON_ANIMATION_STATE_CHANGED fromUser false
2025-08-04 13:20:01.036  5594-5594  ImeTracker              com.akslabs.Suchak                   I  helium314.keyboard:f1546bcb: onHidden
2025-08-04 13:20:03.158  5594-5594  WindowOnBackDispatcher  com.akslabs.Suchak                   W  sendCancelIfRunning: isInProgress=false callback=androidx.activity.OnBackPressedDispatcher$Api34Impl$createOnBackAnimationCallback$1@d3c3cae
2025-08-04 13:20:04.585  5594-5594  InsetsController        com.akslabs.Suchak                   D  show(ime(), fromIme=false)
2025-08-04 13:20:04.586  5594-5594  ImeTracker              com.akslabs.Suchak                   I  com.akslabs.Suchak:d5132200: onRequestShow at ORIGIN_CLIENT reason SHOW_SOFT_INPUT_BY_INSETS_API fromUser false
2025-08-04 13:20:04.586  5594-5594  InputMethodManager      com.akslabs.Suchak                   D  showSoftInput() view=androidx.compose.ui.platform.AndroidComposeView{731596f VFED..... .F....ID 0,0-872,511 aid=1073741828} flags=0 reason=SHOW_SOFT_INPUT_BY_INSETS_API
2025-08-04 13:20:04.616  5594-5594  ImeTracker              com.akslabs.Suchak                   I  com.akslabs.Suchak:e5a926dd: onRequestShow at ORIGIN_CLIENT reason SHOW_SOFT_INPUT fromUser false
2025-08-04 13:20:04.616  5594-5594  InputMethodManager      com.akslabs.Suchak                   D  showSoftInput() view=androidx.compose.ui.platform.AndroidComposeView{731596f VFED..... .F...... 0,0-872,511 aid=1073741828} flags=0 reason=SHOW_SOFT_INPUT
2025-08-04 13:20:04.863  5594-5594  InsetsController        com.akslabs.Suchak                   D  show(ime(), fromIme=true)
2025-08-04 13:20:04.882  5594-5594  InsetsController        com.akslabs.Suchak                   D  show(ime(), fromIme=true)
2025-08-04 13:20:04.882  5594-5594  ImeTracker              com.akslabs.Suchak                   I  com.akslabs.Suchak:e5a926dd: onCancelled at PHASE_CLIENT_APPLY_ANIMATION
2025-08-04 13:20:05.099  5594-5594  ImeTracker              com.akslabs.Suchak                   I  com.akslabs.Suchak:d5132200: onShown
2025-08-04 13:20:07.912  5594-5594  UidComponent            com.akslabs.Suchak                   I  Attempting to validate chat ID: -1002651869724
2025-08-04 13:20:07.913  5594-5594  UidComponent            com.akslabs.Suchak                   I  Parsed chat ID: -1002651869724 (group/channel)
2025-08-04 13:20:07.913  5594-5594  UidComponent            com.akslabs.Suchak                   I  Checking bot access to chat ID: -1002651869724
2025-08-04 13:20:07.916  5594-5642  BotApi                  com.akslabs.Suchak                   I  Attempting to get chat info for: Id(id=-1002651869724)
2025-08-04 13:20:08.515  5594-5642  BotApi                  com.akslabs.Suchak                   I  getChat result - isSuccess: true
2025-08-04 13:20:08.516  5594-5594  UidComponent            com.akslabs.Suchak                   I  Bot access result: true
2025-08-04 13:20:08.516  5594-5594  UidComponent            com.akslabs.Suchak                   I  Chat ID verification successful, saving to preferences
2025-08-04 13:20:08.520  5594-5594  UidComponent            com.akslabs.Suchak                   I  Successfully configured chat ID: -1002651869724
2025-08-04 13:20:08.745  5594-5594  WindowOnBackDispatcher  com.akslabs.Suchak                   W  sendCancelIfRunning: isInProgress=false callback=ImeCallback=ImeOnBackInvokedCallback@197612445 Callback=android.window.IOnBackInvokedCallback$Stub$Proxy@7d968a7
2025-08-04 13:20:08.755  5594-5594  WindowOnBackDispatcher  com.akslabs.Suchak                   W  sendCancelIfRunning: isInProgress=false callback=ImeCallback=ImeOnBackInvokedCallback@197612445 Callback=android.window.IOnBackInvokedCallback$Stub$Proxy@7d968a7
2025-08-04 13:20:08.757  5594-5594  InsetsController        com.akslabs.Suchak                   D  hide(ime(), fromIme=true)
2025-08-04 13:20:08.794  5594-5594  ImeTracker              com.akslabs.Suchak                   I  com.akslabs.Suchak:f425cb09: onCancelled at PHASE_CLIENT_ANIMATION_CANCEL
2025-08-04 13:20:08.796  5594-5594  ImeTracker              com.akslabs.Suchak                   I  com.akslabs.Suchak:7504e1fb: onRequestHide at ORIGIN_CLIENT reason HIDE_SOFT_INPUT_ON_ANIMATION_STATE_CHANGED fromUser false
2025-08-04 13:20:08.796  5594-5594  ImeTracker              com.akslabs.Suchak                   I  com.akslabs.Suchak:7504e1fb: onFailed at PHASE_CLIENT_VIEW_SERVED
2025-08-04 13:20:21.052  5594-5594  MainActivity            com.akslabs.Suchak                   I  Triggering initial SMS reading...
2025-08-04 13:20:21.087  5594-5594  MainActivity            com.akslabs.Suchak                   I  Initial SMS reading triggered successfully
2025-08-04 13:20:21.133  5594-5594  WindowOnBackDispatcher  com.akslabs.Suchak                   W  sendCancelIfRunning: isInProgress=false callback=androidx.activity.OnBackPressedDispatcher$Api34Impl$createOnBackAnimationCallback$1@84c7664
2025-08-04 13:20:21.161  5594-5594  SmsObserverService      com.akslabs.Suchak                   I  SMS Observer Service created
2025-08-04 13:20:21.165  5594-5594  SmsContentObserver      com.akslabs.Suchak                   I  SMS content observer started
2025-08-04 13:20:21.165  5594-5594  SmsObserverService      com.akslabs.Suchak                   I  SMS Observer Service started
2025-08-04 13:20:21.233  5594-5642  SmsSyncWorker           com.akslabs.Suchak                   I  === SMS SYNC WORKER STARTED ===
2025-08-04 13:20:21.238  5594-5647  WM-Processor            com.akslabs.Suchak                   I  Moving WorkSpec (d80d1a01-334b-4954-a0f1-a66d71e253d4) to the foreground
2025-08-04 13:20:21.248  5594-5642  SmsSyncService          com.akslabs.Suchak                   I  === STARTING FULL SMS SYNC ===
2025-08-04 13:20:21.255  5594-5642  SmsSyncService          com.akslabs.Suchak                   I  Syncing SMS messages to channel ID: -1002651869724
2025-08-04 13:20:21.260  5594-5642  SmsReaderService        com.akslabs.Suchak                   I  🔄 Starting optimized SMS database sync...
2025-08-04 13:20:21.262  5594-5642  SmsReaderService        com.akslabs.Suchak                   I  🚀 Starting optimized SMS reading...
2025-08-04 13:20:21.362  5594-5642  SmsReaderService        com.akslabs.Suchak                   I  ✅ Successfully read 405 SMS messages
2025-08-04 13:20:21.363  5594-5642  PerformanceMonitor      com.akslabs.Suchak                   D  ⚡ FAST: sms_read_all took 103ms
2025-08-04 13:20:21.366  5594-5642  SmsReaderService        com.akslabs.Suchak                   D  Processing batch 1 with 100 messages
2025-08-04 13:20:21.772  5594-5594  WM-SystemFgDispatcher   com.akslabs.Suchak                   I  Started foreground service Intent { act=ACTION_START_FOREGROUND cmp=com.akslabs.Suchak/androidx.work.impl.foreground.SystemForegroundService (has extras) }
2025-08-04 13:20:21.776  5594-5594  Choreographer           com.akslabs.Suchak                   I  Skipped 31 frames!  The application may be doing too much work on its main thread.
2025-08-04 13:20:21.810  5594-5642  SmsReaderService        com.akslabs.Suchak                   D  Processing batch 2 with 100 messages
2025-08-04 13:20:22.042  5594-5594  WindowOnBackDispatcher  com.akslabs.Suchak                   W  sendCancelIfRunning: isInProgress=false callback=androidx.activity.OnBackPressedDispatcher$Api34Impl$createOnBackAnimationCallback$1@33fd44e
2025-08-04 13:20:22.056  5594-5642  ContentValues           com.akslabs.Suchak                   D  doWork: []
2025-08-04 13:20:22.056  5594-5642  Sync MediaStore         com.akslabs.Suchak                   D  doWork: Success
2025-08-04 13:20:22.058  5594-5647  WM-WorkerWrapper        com.akslabs.Suchak                   I  Worker result SUCCESS for Work [ id=66542070-9c24-4d0a-a41c-3662991ac399, tags={ com.akslabs.Suchak.workers.SyncDbMediaStoreWorker } ]
2025-08-04 13:20:22.164  5594-5594  ImeTracker              com.akslabs.Suchak                   I  com.akslabs.Suchak:318ca2ca: onRequestHide at ORIGIN_CLIENT reason HIDE_SOFT_INPUT fromUser false
2025-08-04 13:20:22.164  5594-5594  ImeTracker              com.akslabs.Suchak                   I  com.akslabs.Suchak:318ca2ca: onFailed at PHASE_CLIENT_VIEW_SERVED
2025-08-04 13:20:22.164  5594-5594  InsetsController        com.akslabs.Suchak                   D  hide(ime(), fromIme=false)
2025-08-04 13:20:22.165  5594-5594  ImeTracker              com.akslabs.Suchak                   I  com.akslabs.Suchak:49ce5416: onRequestHide at ORIGIN_CLIENT reason HIDE_SOFT_INPUT_BY_INSETS_API fromUser false
2025-08-04 13:20:22.166  5594-5594  ImeTracker              com.akslabs.Suchak                   I  com.akslabs.Suchak:49ce5416: onCancelled at PHASE_CLIENT_APPLY_ANIMATION
2025-08-04 13:20:22.168  5594-5594  ImeTracker              com.akslabs.Suchak                   I  com.akslabs.Suchak:745a35f6: onRequestHide at ORIGIN_CLIENT reason HIDE_SOFT_INPUT fromUser false
2025-08-04 13:20:22.168  5594-5594  ImeTracker              com.akslabs.Suchak                   I  com.akslabs.Suchak:745a35f6: onFailed at PHASE_CLIENT_VIEW_SERVED
2025-08-04 13:20:22.310  5594-5642  SmsReaderService        com.akslabs.Suchak                   D  Processing batch 3 with 100 messages
2025-08-04 13:20:22.713  5594-5641  SmsReaderService        com.akslabs.Suchak                   D  Processing batch 4 with 100 messages
2025-08-04 13:20:23.099  5594-5642  SmsReaderService        com.akslabs.Suchak                   D  Processing batch 5 with 5 messages
2025-08-04 13:20:23.117  5594-5642  SmsReaderService        com.akslabs.Suchak                   I  ✅ SMS sync complete: 405 new, 0 updated
2025-08-04 13:20:23.118  5594-5830  PerformanceMonitor      com.akslabs.Suchak                   D  ⚡ FAST: sms_sync_to_db took 1860ms
2025-08-04 13:20:23.118  5594-5830  SmsSyncService          com.akslabs.Suchak                   I  Synced 405 new SMS messages to local database
2025-08-04 13:20:23.133  5594-5830  SmsSyncService          com.akslabs.Suchak                   I  Found 100 unsynced SMS messages
2025-08-04 13:20:23.134  5594-5830  SmsSyncService          com.akslabs.Suchak                   I  📦 Processing batch 1 with 10 messages (oldest first)
2025-08-04 13:20:23.134  5594-5830  SmsSyncService          com.akslabs.Suchak                   D  📅 Batch date range: 1743144060850 to 1743492220609
2025-08-04 13:20:23.153  5594-5830  SmsSyncService          com.akslabs.Suchak                   D  📤 Sending SMS message 1/10: 1 (1743144060850)
2025-08-04 13:20:23.155  5594-5642  BotApi                  com.akslabs.Suchak                   D  📤 Sending SMS message to channel: -1002651869724
2025-08-04 13:20:23.659  5594-5642  TelegramRateLimiter     com.akslabs.Suchak                   D  ✅ Request successful, rate limiter reset
2025-08-04 13:20:23.659  5594-5642  PerformanceMonitor      com.akslabs.Suchak                   D  ⚡ FAST: telegram_send_message took 504ms
2025-08-04 13:20:23.671  5594-5642  SmsSyncService          com.akslabs.Suchak                   I  ✅ Successfully synced SMS message: 1 (1/20 in current burst, total: 1)
2025-08-04 13:20:23.671  5594-5642  SmsSyncService          com.akslabs.Suchak                   D  ⏳ Waiting 1 second before sending next message (2/10)
2025-08-04 13:20:24.835  5594-5642  SmsSyncService          com.akslabs.Suchak                   D  📤 Sending SMS message 2/10: 2 (1743144066407)
2025-08-04 13:20:24.836  5594-5642  BotApi                  com.akslabs.Suchak                   D  📤 Sending SMS message to channel: -1002651869724
2025-08-04 13:20:25.313  5594-5642  TelegramRateLimiter     com.akslabs.Suchak                   D  ✅ Request successful, rate limiter reset
2025-08-04 13:20:25.314  5594-5642  PerformanceMonitor      com.akslabs.Suchak                   D  ⚡ FAST: telegram_send_message took 477ms
2025-08-04 13:20:25.322  5594-5830  SmsSyncService          com.akslabs.Suchak                   I  ✅ Successfully synced SMS message: 2 (2/20 in current burst, total: 2)
2025-08-04 13:20:25.323  5594-5830  SmsSyncService          com.akslabs.Suchak                   D  ⏳ Waiting 1 second before sending next message (3/10)
2025-08-04 13:20:26.477  5594-5830  SmsSyncService          com.akslabs.Suchak                   D  📤 Sending SMS message 3/10: 3 (1743492128833)
2025-08-04 13:20:26.478  5594-5642  BotApi                  com.akslabs.Suchak                   D  📤 Sending SMS message to channel: -1002651869724
2025-08-04 13:20:26.972  5594-5642  TelegramRateLimiter     com.akslabs.Suchak                   D  ✅ Request successful, rate limiter reset
2025-08-04 13:20:26.972  5594-5642  PerformanceMonitor      com.akslabs.Suchak                   D  ⚡ FAST: telegram_send_message took 494ms
2025-08-04 13:20:26.978  5594-5641  SmsSyncService          com.akslabs.Suchak                   I  ✅ Successfully synced SMS message: 3 (3/20 in current burst, total: 3)
2025-08-04 13:20:26.978  5594-5641  SmsSyncService          com.akslabs.Suchak                   D  ⏳ Waiting 1 second before sending next message (4/10)
2025-08-04 13:20:28.140  5594-5641  SmsSyncService          com.akslabs.Suchak                   D  📤 Sending SMS message 4/10: 4 (1743492180323)
2025-08-04 13:20:28.142  5594-5642  BotApi                  com.akslabs.Suchak                   D  📤 Sending SMS message to channel: -1002651869724
2025-08-04 13:20:28.669  5594-5642  TelegramRateLimiter     com.akslabs.Suchak                   D  ✅ Request successful, rate limiter reset
2025-08-04 13:20:28.669  5594-5642  PerformanceMonitor      com.akslabs.Suchak                   D  ⚡ FAST: telegram_send_message took 528ms
2025-08-04 13:20:28.679  5594-5642  SmsSyncService          com.akslabs.Suchak                   I  ✅ Successfully synced SMS message: 4 (4/20 in current burst, total: 4)
2025-08-04 13:20:28.679  5594-5642  SmsSyncService          com.akslabs.Suchak                   D  ⏳ Waiting 1 second before sending next message (5/10)
2025-08-04 13:20:29.768  5594-5830  BackupHelper            com.akslabs.Suchak                   D  Backup status - Has backup: false, Data unchanged: true
2025-08-04 13:20:29.768  5594-5830  BackupHelper            com.akslabs.Suchak                   D  Current: 0 photos, 0 remote | Last backup: 0 photos, 0 remote
2025-08-04 13:20:29.833  5594-5830  SmsSyncService          com.akslabs.Suchak                   D  📤 Sending SMS message 5/10: 5 (1743492188285)
2025-08-04 13:20:29.833  5594-5830  BotApi                  com.akslabs.Suchak                   D  📤 Sending SMS message to channel: -1002651869724
2025-08-04 13:20:30.446  5594-5830  TelegramRateLimiter     com.akslabs.Suchak                   D  ✅ Request successful, rate limiter reset
2025-08-04 13:20:30.446  5594-5830  PerformanceMonitor      com.akslabs.Suchak                   D  ⚡ FAST: telegram_send_message took 613ms
2025-08-04 13:20:30.467  5594-5642  SmsSyncService          com.akslabs.Suchak                   I  ✅ Successfully synced SMS message: 5 (5/20 in current burst, total: 5)
2025-08-04 13:20:30.468  5594-5642  SmsSyncService          com.akslabs.Suchak                   D  ⏳ Waiting 1 second before sending next message (6/10)
2025-08-04 13:20:31.629  5594-5642  SmsSyncService          com.akslabs.Suchak                   D  📤 Sending SMS message 6/10: 6 (1743492188478)
2025-08-04 13:20:31.631  5594-5642  BotApi                  com.akslabs.Suchak                   D  📤 Sending SMS message to channel: -1002651869724
2025-08-04 13:20:32.243  5594-5642  TelegramRateLimiter     com.akslabs.Suchak                   D  ✅ Request successful, rate limiter reset
2025-08-04 13:20:32.243  5594-5642  PerformanceMonitor      com.akslabs.Suchak                   D  ⚡ FAST: telegram_send_message took 612ms
2025-08-04 13:20:32.251  5594-5830  SmsSyncService          com.akslabs.Suchak                   I  ✅ Successfully synced SMS message: 6 (6/20 in current burst, total: 6)
2025-08-04 13:20:32.252  5594-5830  SmsSyncService          com.akslabs.Suchak                   D  ⏳ Waiting 1 second before sending next message (7/10)
2025-08-04 13:20:32.727  5594-5594  VRI[MainActivity]       com.akslabs.Suchak                   D  visibilityChanged oldVisibility=true newVisibility=false
2025-08-04 13:20:32.757  5594-5594  WindowOnBackDispatcher  com.akslabs.Suchak                   W  sendCancelIfRunning: isInProgress=false callback=androidx.activity.OnBackPressedDispatcher$Api34Impl$createOnBackAnimationCallback$1@76ed38c
2025-08-04 13:20:32.838  5594-5594  JobService              com.akslabs.Suchak                   W  onNetworkChanged() not implemented in androidx.work.impl.background.systemjob.SystemJobService. Must override in a subclass.
2025-08-04 13:20:33.414  5594-5830  SmsSyncService          com.akslabs.Suchak                   D  📤 Sending SMS message 7/10: 7 (1743492191789)
2025-08-04 13:20:33.416  5594-5830  BotApi                  com.akslabs.Suchak                   D  📤 Sending SMS message to channel: -1002651869724
2025-08-04 13:20:34.035  5594-5830  TelegramRateLimiter     com.akslabs.Suchak                   D  ✅ Request successful, rate limiter reset
2025-08-04 13:20:34.035  5594-5830  PerformanceMonitor      com.akslabs.Suchak                   D  ⚡ FAST: telegram_send_message took 619ms
2025-08-04 13:20:34.041  5594-5830  SmsSyncService          com.akslabs.Suchak                   I  ✅ Successfully synced SMS message: 7 (7/20 in current burst, total: 7)
2025-08-04 13:20:34.041  5594-5830  SmsSyncService          com.akslabs.Suchak                   D  ⏳ Waiting 1 second before sending next message (8/10)
2025-08-04 13:20:35.204  5594-5640  SmsSyncService          com.akslabs.Suchak                   D  📤 Sending SMS message 8/10: 8 (1743492200730)
2025-08-04 13:20:35.206  5594-5640  BotApi                  com.akslabs.Suchak                   D  📤 Sending SMS message to channel: -1002651869724
2025-08-04 13:20:35.869  5594-5640  TelegramRateLimiter     com.akslabs.Suchak                   D  ✅ Request successful, rate limiter reset
2025-08-04 13:20:35.870  5594-5640  PerformanceMonitor      com.akslabs.Suchak                   D  ⚡ FAST: telegram_send_message took 664ms
2025-08-04 13:20:35.879  5594-5641  SmsSyncService          com.akslabs.Suchak                   I  ✅ Successfully synced SMS message: 8 (8/20 in current burst, total: 8)
2025-08-04 13:20:35.879  5594-5641  SmsSyncService          com.akslabs.Suchak                   D  ⏳ Waiting 1 second before sending next message (9/10)
2025-08-04 13:20:37.049  5594-5641  SmsSyncService          com.akslabs.Suchak                   D  📤 Sending SMS message 9/10: 9 (1743492203825)
2025-08-04 13:20:37.051  5594-5641  BotApi                  com.akslabs.Suchak                   D  📤 Sending SMS message to channel: -1002651869724
2025-08-04 13:20:37.681  5594-5641  TelegramRateLimiter     com.akslabs.Suchak                   D  ✅ Request successful, rate limiter reset
2025-08-04 13:20:37.681  5594-5641  PerformanceMonitor      com.akslabs.Suchak                   D  ⚡ FAST: telegram_send_message took 630ms
2025-08-04 13:20:37.694  5594-5641  SmsSyncService          com.akslabs.Suchak                   I  ✅ Successfully synced SMS message: 9 (9/20 in current burst, total: 9)
2025-08-04 13:20:37.694  5594-5641  SmsSyncService          com.akslabs.Suchak                   D  ⏳ Waiting 1 second before sending next message (10/10)
2025-08-04 13:20:38.859  5594-5641  SmsSyncService          com.akslabs.Suchak                   D  📤 Sending SMS message 10/10: 10 (1743492220609)
2025-08-04 13:20:38.860  5594-5641  BotApi                  com.akslabs.Suchak                   D  📤 Sending SMS message to channel: -1002651869724
2025-08-04 13:20:39.497  5594-5641  TelegramRateLimiter     com.akslabs.Suchak                   D  ✅ Request successful, rate limiter reset
2025-08-04 13:20:39.497  5594-5641  PerformanceMonitor      com.akslabs.Suchak                   D  ⚡ FAST: telegram_send_message took 637ms
2025-08-04 13:20:39.504  5594-5641  SmsSyncService          com.akslabs.Suchak                   I  ✅ Successfully synced SMS message: 10 (10/20 in current burst, total: 10)
2025-08-04 13:20:39.510  5594-5641  SmsSyncService          com.akslabs.Suchak                   D  ⏳ Waiting 2 seconds before next batch
2025-08-04 13:20:41.513  5594-5641  SmsSyncService          com.akslabs.Suchak                   I  📦 Processing batch 2 with 10 messages (oldest first)
2025-08-04 13:20:41.513  5594-5641  SmsSyncService          com.akslabs.Suchak                   D  📅 Batch date range: 1743492246827 to 1744058186848
2025-08-04 13:20:41.527  5594-5641  SmsSyncService          com.akslabs.Suchak                   D  📤 Sending SMS message 1/10: 11 (1743492246827)
2025-08-04 13:20:41.528  5594-5641  BotApi                  com.akslabs.Suchak                   D  📤 Sending SMS message to channel: -1002651869724
2025-08-04 13:20:41.998  5594-5641  TelegramRateLimiter     com.akslabs.Suchak                   D  ✅ Request successful, rate limiter reset
2025-08-04 13:20:41.998  5594-5641  PerformanceMonitor      com.akslabs.Suchak                   D  ⚡ FAST: telegram_send_message took 470ms
2025-08-04 13:20:42.004  5594-5641  SmsSyncService          com.akslabs.Suchak                   I  ✅ Successfully synced SMS message: 11 (11/20 in current burst, total: 11)
2025-08-04 13:20:42.004  5594-5641  SmsSyncService          com.akslabs.Suchak                   D  ⏳ Waiting 1 second before sending next message (2/10)
2025-08-04 13:20:43.167  5594-5641  SmsSyncService          com.akslabs.Suchak                   D  📤 Sending SMS message 2/10: 12 (1743492386996)
2025-08-04 13:20:43.169  5594-5641  BotApi                  com.akslabs.Suchak                   D  📤 Sending SMS message to channel: -1002651869724
2025-08-04 13:20:43.658  5594-5641  TelegramRateLimiter     com.akslabs.Suchak                   D  ✅ Request successful, rate limiter reset
2025-08-04 13:20:43.658  5594-5641  PerformanceMonitor      com.akslabs.Suchak                   D  ⚡ FAST: telegram_send_message took 489ms
2025-08-04 13:20:43.667  5594-5641  SmsSyncService          com.akslabs.Suchak                   I  ✅ Successfully synced SMS message: 12 (12/20 in current burst, total: 12)
2025-08-04 13:20:43.667  5594-5641  SmsSyncService          com.akslabs.Suchak                   D  ⏳ Waiting 1 second before sending next message (3/10)
2025-08-04 13:20:44.831  5594-5641  SmsSyncService          com.akslabs.Suchak                   D  📤 Sending SMS message 3/10: 13 (1743741615305)
2025-08-04 13:20:44.833  5594-5641  BotApi                  com.akslabs.Suchak                   D  📤 Sending SMS message to channel: -1002651869724
2025-08-04 13:20:45.484  5594-5641  TelegramRateLimiter     com.akslabs.Suchak                   D  ✅ Request successful, rate limiter reset
2025-08-04 13:20:45.484  5594-5641  PerformanceMonitor      com.akslabs.Suchak                   D  ⚡ FAST: telegram_send_message took 651ms
2025-08-04 13:20:45.491  5594-5641  SmsSyncService          com.akslabs.Suchak                   I  ✅ Successfully synced SMS message: 13 (13/20 in current burst, total: 13)
2025-08-04 13:20:45.491  5594-5641  SmsSyncService          com.akslabs.Suchak                   D  ⏳ Waiting 1 second before sending next message (4/10)
2025-08-04 13:20:46.652  5594-5641  SmsSyncService          com.akslabs.Suchak                   D  📤 Sending SMS message 4/10: 14 (1743742940643)
2025-08-04 13:20:46.653  5594-5641  BotApi                  com.akslabs.Suchak                   D  📤 Sending SMS message to channel: -1002651869724
2025-08-04 13:20:47.141  5594-5641  TelegramRateLimiter     com.akslabs.Suchak                   D  ✅ Request successful, rate limiter reset
2025-08-04 13:20:47.141  5594-5641  PerformanceMonitor      com.akslabs.Suchak                   D  ⚡ FAST: telegram_send_message took 488ms
2025-08-04 13:20:47.149  5594-5641  SmsSyncService          com.akslabs.Suchak                   I  ✅ Successfully synced SMS message: 14 (14/20 in current burst, total: 14)
2025-08-04 13:20:47.149  5594-5641  SmsSyncService          com.akslabs.Suchak                   D  ⏳ Waiting 1 second before sending next message (5/10)
2025-08-04 13:20:48.302  5594-5641  SmsSyncService          com.akslabs.Suchak                   D  📤 Sending SMS message 5/10: 15 (1743861007120)
2025-08-04 13:20:48.303  5594-5640  BotApi                  com.akslabs.Suchak                   D  📤 Sending SMS message to channel: -1002651869724
2025-08-04 13:20:48.898  5594-5640  TelegramRateLimiter     com.akslabs.Suchak                   D  ✅ Request successful, rate limiter reset
2025-08-04 13:20:48.899  5594-5640  PerformanceMonitor      com.akslabs.Suchak                   D  ⚡ FAST: telegram_send_message took 596ms
2025-08-04 13:20:48.904  5594-5641  SmsSyncService          com.akslabs.Suchak                   I  ✅ Successfully synced SMS message: 15 (15/20 in current burst, total: 15)
2025-08-04 13:20:48.904  5594-5641  SmsSyncService          com.akslabs.Suchak                   D  ⏳ Waiting 1 second before sending next message (6/10)
2025-08-04 13:20:50.064  5594-5641  SmsSyncService          com.akslabs.Suchak                   D  📤 Sending SMS message 6/10: 16 (1743861229143)
2025-08-04 13:20:50.066  5594-5641  BotApi                  com.akslabs.Suchak                   D  📤 Sending SMS message to channel: -1002651869724
2025-08-04 13:20:50.739  5594-5641  TelegramRateLimiter     com.akslabs.Suchak                   D  ✅ Request successful, rate limiter reset
2025-08-04 13:20:50.740  5594-5641  PerformanceMonitor      com.akslabs.Suchak                   D  ⚡ FAST: telegram_send_message took 673ms
2025-08-04 13:20:50.747  5594-5641  SmsSyncService          com.akslabs.Suchak                   I  ✅ Successfully synced SMS message: 16 (16/20 in current burst, total: 16)
2025-08-04 13:20:50.747  5594-5641  SmsSyncService          com.akslabs.Suchak                   D  ⏳ Waiting 1 second before sending next message (7/10)
2025-08-04 13:20:51.905  5594-5641  SmsSyncService          com.akslabs.Suchak                   D  📤 Sending SMS message 7/10: 17 (1743888531672)
2025-08-04 13:20:51.906  5594-5640  BotApi                  com.akslabs.Suchak                   D  📤 Sending SMS message to channel: -1002651869724
2025-08-04 13:20:52.531  5594-5640  TelegramRateLimiter     com.akslabs.Suchak                   D  ✅ Request successful, rate limiter reset
2025-08-04 13:20:52.532  5594-5640  PerformanceMonitor      com.akslabs.Suchak                   D  ⚡ FAST: telegram_send_message took 626ms
2025-08-04 13:20:52.536  5594-5830  SmsSyncService          com.akslabs.Suchak                   I  ✅ Successfully synced SMS message: 17 (17/20 in current burst, total: 17)
2025-08-04 13:20:52.536  5594-5830  SmsSyncService          com.akslabs.Suchak                   D  ⏳ Waiting 1 second before sending next message (8/10)
2025-08-04 13:20:53.689  5594-5830  SmsSyncService          com.akslabs.Suchak                   D  📤 Sending SMS message 8/10: 18 (1743961038155)
2025-08-04 13:20:53.690  5594-5830  BotApi                  com.akslabs.Suchak                   D  📤 Sending SMS message to channel: -1002651869724
2025-08-04 13:20:54.339  5594-5830  TelegramRateLimiter     com.akslabs.Suchak                   D  ✅ Request successful, rate limiter reset
2025-08-04 13:20:54.340  5594-5830  PerformanceMonitor      com.akslabs.Suchak                   D  ⚡ FAST: telegram_send_message took 650ms
2025-08-04 13:20:54.349  5594-5830  SmsSyncService          com.akslabs.Suchak                   I  ✅ Successfully synced SMS message: 18 (18/20 in current burst, total: 18)
2025-08-04 13:20:54.349  5594-5830  SmsSyncService          com.akslabs.Suchak                   D  ⏳ Waiting 1 second before sending next message (9/10)
2025-08-04 13:20:55.504  5594-5830  SmsSyncService          com.akslabs.Suchak                   D  📤 Sending SMS message 9/10: 19 (1744045086889)
2025-08-04 13:20:55.505  5594-5830  BotApi                  com.akslabs.Suchak                   D  📤 Sending SMS message to channel: -1002651869724
2025-08-04 13:20:56.148  5594-5830  TelegramRateLimiter     com.akslabs.Suchak                   D  ✅ Request successful, rate limiter reset
2025-08-04 13:20:56.149  5594-5830  PerformanceMonitor      com.akslabs.Suchak                   D  ⚡ FAST: telegram_send_message took 643ms
2025-08-04 13:20:56.161  5594-5830  SmsSyncService          com.akslabs.Suchak                   I  ✅ Successfully synced SMS message: 19 (19/20 in current burst, total: 19)
2025-08-04 13:20:56.161  5594-5830  SmsSyncService          com.akslabs.Suchak                   D  ⏳ Waiting 1 second before sending next message (10/10)
2025-08-04 13:20:57.314  5594-5830  SmsSyncService          com.akslabs.Suchak                   D  📤 Sending SMS message 10/10: 20 (1744058186848)
2025-08-04 13:20:57.315  5594-5640  BotApi                  com.akslabs.Suchak                   D  📤 Sending SMS message to channel: -1002651869724
2025-08-04 13:20:57.790  5594-5640  TelegramRateLimiter     com.akslabs.Suchak                   D  ✅ Request successful, rate limiter reset
2025-08-04 13:20:57.790  5594-5640  PerformanceMonitor      com.akslabs.Suchak                   D  ⚡ FAST: telegram_send_message took 475ms
2025-08-04 13:20:57.798  5594-5640  SmsSyncService          com.akslabs.Suchak                   I  ✅ Successfully synced SMS message: 20 (0/20 in current burst, total: 20)
2025-08-04 13:20:57.803  5594-5640  SmsSyncService          com.akslabs.Suchak                   D  ⏳ Waiting 2 seconds before next batch
2025-08-04 13:20:59.806  5594-5640  SmsSyncService          com.akslabs.Suchak                   I  📦 Processing batch 3 with 10 messages (oldest first)
2025-08-04 13:20:59.806  5594-5640  SmsSyncService          com.akslabs.Suchak                   D  📅 Batch date range: 1744141736124 to 1744481906426
2025-08-04 13:20:59.806  5594-5640  SmsSyncService          com.akslabs.Suchak                   W  🛑 Sent 20 messages (total: 20), pausing for 25 seconds as per Telegram's requirement
2025-08-04 13:21:02.253  5594-5642  BackupHelper            com.akslabs.Suchak                   D  Backup status - Has backup: false, Data unchanged: true
2025-08-04 13:21:02.254  5594-5642  BackupHelper            com.akslabs.Suchak                   D  Current: 0 photos, 0 remote | Last backup: 0 photos, 0 remote
2025-08-04 13:21:04.891  5594-5594  VRI[MainActivity]       com.akslabs.Suchak                   D  visibilityChanged oldVisibility=true newVisibility=false
2025-08-04 13:21:04.914  5594-5594  WindowOnBackDispatcher  com.akslabs.Suchak                   W  sendCancelIfRunning: isInProgress=false callback=androidx.activity.OnBackPressedDispatcher$Api34Impl$createOnBackAnimationCallback$1@76ed38c
2025-08-04 13:21:19.807  5594-5594  JobService              com.akslabs.Suchak                   W  onNetworkChanged() not implemented in androidx.work.impl.background.systemjob.SystemJobService. Must override in a subclass.
2025-08-04 13:21:21.739  5594-5594  JobService              com.akslabs.Suchak                   W  onNetworkChanged() not implemented in androidx.work.impl.background.systemjob.SystemJobService. Must override in a subclass.
2025-08-04 13:21:24.726  5594-5594  JobService              com.akslabs.Suchak                   W  onNetworkChanged() not implemented in androidx.work.impl.background.systemjob.SystemJobService. Must override in a subclass.
2025-08-04 13:21:24.807  5594-5830  SmsSyncService          com.akslabs.Suchak                   I  ▶️ Resuming message sending after pause
2025-08-04 13:21:24.809  5594-5830  SmsSyncService          com.akslabs.Suchak                   D  📤 Sending SMS message 1/10: 21 (1744141736124)
2025-08-04 13:21:24.810  5594-5830  BotApi                  com.akslabs.Suchak                   D  📤 Sending SMS message to channel: -1002651869724
2025-08-04 13:21:25.351  5594-5830  TelegramRateLimiter     com.akslabs.Suchak                   D  ✅ Request successful, rate limiter reset
2025-08-04 13:21:25.351  5594-5830  PerformanceMonitor      com.akslabs.Suchak                   D  ⚡ FAST: telegram_send_message took 541ms
2025-08-04 13:21:25.360  5594-5830  SmsSyncService          com.akslabs.Suchak                   I  ✅ Successfully synced SMS message: 21 (1/20 in current burst, total: 21)
2025-08-04 13:21:25.361  5594-5830  SmsSyncService          com.akslabs.Suchak                   D  ⏳ Waiting 1 second before sending next message (2/10)
2025-08-04 13:21:26.518  5594-5830  SmsSyncService          com.akslabs.Suchak                   D  📤 Sending SMS message 2/10: 22 (1744228251435)
2025-08-04 13:21:26.520  5594-5830  BotApi                  com.akslabs.Suchak                   D  📤 Sending SMS message to channel: -1002651869724
2025-08-04 13:21:27.008  5594-5830  TelegramRateLimiter     com.akslabs.Suchak                   D  ✅ Request successful, rate limiter reset
2025-08-04 13:21:27.009  5594-5830  PerformanceMonitor      com.akslabs.Suchak                   D  ⚡ FAST: telegram_send_message took 489ms
2025-08-04 13:21:27.017  5594-5830  SmsSyncService          com.akslabs.Suchak                   I  ✅ Successfully synced SMS message: 22 (2/20 in current burst, total: 22)
2025-08-04 13:21:27.017  5594-5830  SmsSyncService          com.akslabs.Suchak                   D  ⏳ Waiting 1 second before sending next message (3/10)
2025-08-04 13:21:28.175  5594-5830  SmsSyncService          com.akslabs.Suchak                   D  📤 Sending SMS message 3/10: 23 (1744299522266)
2025-08-04 13:21:28.176  5594-5830  BotApi                  com.akslabs.Suchak                   D  📤 Sending SMS message to channel: -1002651869724
2025-08-04 13:21:28.660  5594-5830  TelegramRateLimiter     com.akslabs.Suchak                   D  ✅ Request successful, rate limiter reset
2025-08-04 13:21:28.661  5594-5830  PerformanceMonitor      com.akslabs.Suchak                   D  ⚡ FAST: telegram_send_message took 484ms
2025-08-04 13:21:28.670  5594-5640  SmsSyncService          com.akslabs.Suchak                   I  ✅ Successfully synced SMS message: 23 (3/20 in current burst, total: 23)
2025-08-04 13:21:28.670  5594-5640  SmsSyncService          com.akslabs.Suchak                   D  ⏳ Waiting 1 second before sending next message (4/10)
2025-08-04 13:21:29.824  5594-5640  SmsSyncService          com.akslabs.Suchak                   D  📤 Sending SMS message 4/10: 24 (1744301852589)
2025-08-04 13:21:29.825  5594-5640  BotApi                  com.akslabs.Suchak                   D  📤 Sending SMS message to channel: -1002651869724
2025-08-04 13:21:30.446  5594-5640  TelegramRateLimiter     com.akslabs.Suchak                   D  ✅ Request successful, rate limiter reset
2025-08-04 13:21:30.446  5594-5640  PerformanceMonitor      com.akslabs.Suchak                   D  ⚡ FAST: telegram_send_message took 622ms
2025-08-04 13:21:30.452  5594-5640  SmsSyncService          com.akslabs.Suchak                   I  ✅ Successfully synced SMS message: 24 (4/20 in current burst, total: 24)
2025-08-04 13:21:30.452  5594-5640  SmsSyncService          com.akslabs.Suchak                   D  ⏳ Waiting 1 second before sending next message (5/10)
2025-08-04 13:21:30.688  5594-5601  .akslabs.Suchak         com.akslabs.Suchak                   I  Background concurrent copying GC freed 7628KB AllocSpace bytes, 2(104KB) LOS objects, 49% free, 10MB/20MB, paused 120us,145us total 199.312ms
2025-08-04 13:21:31.608  5594-5640  SmsSyncService          com.akslabs.Suchak                   D  📤 Sending SMS message 5/10: 25 (1744377397613)
2025-08-04 13:21:31.609  5594-5640  BotApi                  com.akslabs.Suchak                   D  📤 Sending SMS message to channel: -1002651869724
2025-08-04 13:21:31.733  5594-5594  WindowOnBackDispatcher  com.akslabs.Suchak                   W  sendCancelIfRunning: isInProgress=false callback=androidx.activity.OnBackPressedDispatcher$Api34Impl$createOnBackAnimationCallback$1@76ed38c
2025-08-04 13:21:32.091  5594-5640  TelegramRateLimiter     com.akslabs.Suchak                   D  ✅ Request successful, rate limiter reset
2025-08-04 13:21:32.091  5594-5640  PerformanceMonitor      com.akslabs.Suchak                   D  ⚡ FAST: telegram_send_message took 483ms
2025-08-04 13:21:32.097  5594-5830  SmsSyncService          com.akslabs.Suchak                   I  ✅ Successfully synced SMS message: 25 (5/20 in current burst, total: 25)
2025-08-04 13:21:32.097  5594-5830  SmsSyncService          com.akslabs.Suchak                   D  ⏳ Waiting 1 second before sending next message (6/10)
2025-08-04 13:21:32.843  5594-5594  JobService              com.akslabs.Suchak                   W  onNetworkChanged() not implemented in androidx.work.impl.background.systemjob.SystemJobService. Must override in a subclass.
2025-08-04 13:21:33.152  5594-5594  LeakCanary              com.akslabs.Suchak                   D  Watching instance of androidx.lifecycle.ReportFragment (androidx.lifecycle.ReportFragment received Fragment#onDestroy() callback) with key 61a21af0-5676-4ace-a954-04d8086558c2
2025-08-04 13:21:33.153  5594-5594  LeakCanary              com.akslabs.Suchak                   D  Watching instance of com.akslabs.chitralaya.ui.MainActivity (com.akslabs.chitralaya.ui.MainActivity received Activity#onDestroy() callback) with key a9dfd7b4-1097-4ec3-8267-d3b3b56654c1
2025-08-04 13:21:33.153  5594-5594  WindowOnBackDispatcher  com.akslabs.Suchak                   W  sendCancelIfRunning: isInProgress=false callback=android.app.Activity$$ExternalSyntheticLambda0@80f134c
2025-08-04 13:21:33.253  5594-5830  SmsSyncService          com.akslabs.Suchak                   D  📤 Sending SMS message 6/10: 26 (1744393335989)
2025-08-04 13:21:33.254  5594-5830  BotApi                  com.akslabs.Suchak                   D  📤 Sending SMS message to channel: -1002651869724
2025-08-04 13:21:36.810  5594-5830  TelegramRateLimiter     com.akslabs.Suchak                   D  ✅ Request successful, rate limiter reset
2025-08-04 13:21:36.811  5594-5830  PerformanceMonitor      com.akslabs.Suchak                   I  ⚠️ MODERATE: telegram_send_message took 3556ms
2025-08-04 13:21:36.818  5594-5830  SmsSyncService          com.akslabs.Suchak                   I  ✅ Successfully synced SMS message: 26 (6/20 in current burst, total: 26)
2025-08-04 13:21:36.818  5594-5830  SmsSyncService          com.akslabs.Suchak                   D  ⏳ Waiting 1 second before sending next message (7/10)
2025-08-04 13:21:37.972  5594-5830  SmsSyncService          com.akslabs.Suchak                   D  📤 Sending SMS message 7/10: 27 (1744445607717)
2025-08-04 13:21:37.974  5594-5830  BotApi                  com.akslabs.Suchak                   D  📤 Sending SMS message to channel: -1002651869724
2025-08-04 13:21:38.308  5594-5625  .akslabs.Suchak         com.akslabs.Suchak                   I  Explicit concurrent copying GC freed 1971KB AllocSpace bytes, 0(0B) LOS objects, 49% free, 9671KB/18MB, paused 73us,66us total 150.567ms
2025-08-04 13:21:39.178  5594-5830  TelegramRateLimiter     com.akslabs.Suchak                   D  ✅ Request successful, rate limiter reset
2025-08-04 13:21:39.178  5594-5830  PerformanceMonitor      com.akslabs.Suchak                   D  ⚡ FAST: telegram_send_message took 1204ms
2025-08-04 13:21:39.195  5594-5830  SmsSyncService          com.akslabs.Suchak                   I  ✅ Successfully synced SMS message: 27 (7/20 in current burst, total: 27)
2025-08-04 13:21:39.195  5594-5830  SmsSyncService          com.akslabs.Suchak                   D  ⏳ Waiting 1 second before sending next message (8/10)
2025-08-04 13:21:40.350  5594-5830  SmsSyncService          com.akslabs.Suchak                   D  📤 Sending SMS message 8/10: 28 (1744446752531)
2025-08-04 13:21:40.351  5594-5830  BotApi                  com.akslabs.Suchak                   D  📤 Sending SMS message to channel: -1002651869724
2025-08-04 13:21:40.571  1857-1857  JobServiceContext       system_server                        E  Binding died for com.akslabs.Suchak but no running job on this context
2025-08-04 13:21:40.623  1857-2233  ConnectivityService     system_server                        E  RemoteException caught trying to send a callback msg for NetworkRequest [ TRACK_DEFAULT id=2248, [ Capabilities: INTERNET&NOT_RESTRICTED&TRUSTED&NOT_VCN_MANAGED&NOT_BANDWIDTH_CONSTRAINED Uid: 10797 RequestorUid: 10797 RequestorPkg: com.akslabs.Suchak UnderlyingNetworks: Null] ]
2025-08-04 13:21:40.769  1857-1857  AppOps                  system_server                        E  Operation not started: uid=10797 pkg=com.akslabs.Suchak(null) op=WAKE_LOCK
