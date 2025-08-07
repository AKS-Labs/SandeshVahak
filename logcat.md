--------- beginning of crash
2025-08-06 14:15:01.639 31361-31361 AndroidRuntime          pid-31361                            E  FATAL EXCEPTION: main
                                                                                                    Process: com.akslabs.Suchak, PID: 31361
                                                                                                    java.lang.IllegalStateException: Room cannot verify the data integrity. Looks like you've changed schema but forgot to update the version number. You can simply fix this by increasing the version number. Expected identity hash: c2649516d87989186e76f4a81f3d6c30, found: cf907bd6af687bb23799ab020b61b9fb
                                                                                                    	at androidx.room.RoomOpenHelper.checkIdentity(RoomOpenHelper.kt:146)
                                                                                                    	at androidx.room.RoomOpenHelper.onOpen(RoomOpenHelper.kt:127)
                                                                                                    	at androidx.sqlite.db.framework.FrameworkSQLiteOpenHelper$OpenHelper.onOpen(FrameworkSQLiteOpenHelper.kt:287)
                                                                                                    	at android.database.sqlite.SQLiteOpenHelper.getDatabaseLocked(SQLiteOpenHelper.java:426)
                                                                                                    	at android.database.sqlite.SQLiteOpenHelper.getWritableDatabase(SQLiteOpenHelper.java:316)
                                                                                                    	at androidx.sqlite.db.framework.FrameworkSQLiteOpenHelper$OpenHelper.getWritableOrReadableDatabase(FrameworkSQLiteOpenHelper.kt:232)
                                                                                                    	at androidx.sqlite.db.framework.FrameworkSQLiteOpenHelper$OpenHelper.innerGetDatabase(FrameworkSQLiteOpenHelper.kt:190)
                                                                                                    	at androidx.sqlite.db.framework.FrameworkSQLiteOpenHelper$OpenHelper.getSupportDatabase(FrameworkSQLiteOpenHelper.kt:151)
                                                                                                    	at androidx.sqlite.db.framework.FrameworkSQLiteOpenHelper.getWritableDatabase(FrameworkSQLiteOpenHelper.kt:104)
                                                                                                    	at androidx.room.RoomDatabase.inTransaction(RoomDatabase.kt:632)
                                                                                                    	at androidx.room.RoomDatabase.assertNotSuspendingTransaction(RoomDatabase.kt:451)
                                                                                                    	at androidx.room.RoomDatabase.query(RoomDatabase.kt:480)
                                                                                                    	at androidx.room.util.DBUtil.query(DBUtil.kt:75)
                                                                                                    	at com.akslabs.chitralaya.data.localdb.dao.RemoteSmsMessageDao_Impl$19.call(RemoteSmsMessageDao_Impl.java:612)
                                                                                                    	at com.akslabs.chitralaya.data.localdb.dao.RemoteSmsMessageDao_Impl$19.call(RemoteSmsMessageDao_Impl.java:608)
                                                                                                    	at androidx.room.CoroutinesRoom$Companion$createFlow$1$1$1.invokeSuspend(CoroutinesRoom.kt:129)
                                                                                                    	at kotlin.coroutines.jvm.internal.BaseContinuationImpl.resumeWith(ContinuationImpl.kt:33)
                                                                                                    	at kotlinx.coroutines.DispatchedTask.run(DispatchedTask.kt:104)
                                                                                                    	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1145)
                                                                                                    	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:644)
                                                                                                    	at java.lang.Thread.run(Thread.java:1117)
                                                                                                    	Suppressed: kotlinx.coroutines.internal.DiagnosticCoroutineContextException: [androidx.compose.ui.platform.MotionDurationScaleImpl@9e583ae, androidx.compose.runtime.BroadcastFrameClock@b6c454f, StandaloneCoroutine{Cancelling}@451c4dc, AndroidUiDispatcher@ef409e5]
2025-08-06 14:15:15.858 31556-31556 AndroidRuntime          pid-31556                            E  FATAL EXCEPTION: main
                                                                                                    Process: com.akslabs.Suchak, PID: 31556
                                                                                                    java.lang.IllegalStateException: Room cannot verify the data integrity. Looks like you've changed schema but forgot to update the version number. You can simply fix this by increasing the version number. Expected identity hash: c2649516d87989186e76f4a81f3d6c30, found: cf907bd6af687bb23799ab020b61b9fb
                                                                                                    	at androidx.room.RoomOpenHelper.checkIdentity(RoomOpenHelper.kt:146)
                                                                                                    	at androidx.room.RoomOpenHelper.onOpen(RoomOpenHelper.kt:127)
                                                                                                    	at androidx.sqlite.db.framework.FrameworkSQLiteOpenHelper$OpenHelper.onOpen(FrameworkSQLiteOpenHelper.kt:287)
                                                                                                    	at android.database.sqlite.SQLiteOpenHelper.getDatabaseLocked(SQLiteOpenHelper.java:426)
                                                                                                    	at android.database.sqlite.SQLiteOpenHelper.getWritableDatabase(SQLiteOpenHelper.java:316)
                                                                                                    	at androidx.sqlite.db.framework.FrameworkSQLiteOpenHelper$OpenHelper.getWritableOrReadableDatabase(FrameworkSQLiteOpenHelper.kt:232)
                                                                                                    	at androidx.sqlite.db.framework.FrameworkSQLiteOpenHelper$OpenHelper.innerGetDatabase(FrameworkSQLiteOpenHelper.kt:190)
                                                                                                    	at androidx.sqlite.db.framework.FrameworkSQLiteOpenHelper$OpenHelper.getSupportDatabase(FrameworkSQLiteOpenHelper.kt:151)
                                                                                                    	at androidx.sqlite.db.framework.FrameworkSQLiteOpenHelper.getWritableDatabase(FrameworkSQLiteOpenHelper.kt:104)
                                                                                                    	at androidx.room.RoomDatabase.inTransaction(RoomDatabase.kt:632)
                                                                                                    	at androidx.room.RoomDatabase.assertNotSuspendingTransaction(RoomDatabase.kt:451)
                                                                                                    	at androidx.room.RoomDatabase.query(RoomDatabase.kt:480)
                                                                                                    	at androidx.room.util.DBUtil.query(DBUtil.kt:75)
                                                                                                    	at com.akslabs.chitralaya.data.localdb.dao.RemoteSmsMessageDao_Impl$19.call(RemoteSmsMessageDao_Impl.java:612)
                                                                                                    	at com.akslabs.chitralaya.data.localdb.dao.RemoteSmsMessageDao_Impl$19.call(RemoteSmsMessageDao_Impl.java:608)
                                                                                                    	at androidx.room.CoroutinesRoom$Companion$createFlow$1$1$1.invokeSuspend(CoroutinesRoom.kt:129)
                                                                                                    	at kotlin.coroutines.jvm.internal.BaseContinuationImpl.resumeWith(ContinuationImpl.kt:33)
                                                                                                    	at kotlinx.coroutines.DispatchedTask.run(DispatchedTask.kt:104)
                                                                                                    	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1145)
                                                                                                    	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:644)
                                                                                                    	at java.lang.Thread.run(Thread.java:1117)
                                                                                                    	Suppressed: kotlinx.coroutines.internal.DiagnosticCoroutineContextException: [androidx.compose.ui.platform.MotionDurationScaleImpl@aca7db0, androidx.compose.runtime.BroadcastFrameClock@a35429, StandaloneCoroutine{Cancelling}@9e583ae, AndroidUiDispatcher@b6c454f]
2025-08-06 14:15:32.582 31700-31700 AndroidRuntime          pid-31700                            E  FATAL EXCEPTION: main
                                                                                                    Process: com.akslabs.Suchak, PID: 31700
                                                                                                    java.lang.IllegalStateException: Room cannot verify the data integrity. Looks like you've changed schema but forgot to update the version number. You can simply fix this by increasing the version number. Expected identity hash: c2649516d87989186e76f4a81f3d6c30, found: cf907bd6af687bb23799ab020b61b9fb
                                                                                                    	at androidx.room.RoomOpenHelper.checkIdentity(RoomOpenHelper.kt:146)
                                                                                                    	at androidx.room.RoomOpenHelper.onOpen(RoomOpenHelper.kt:127)
                                                                                                    	at androidx.sqlite.db.framework.FrameworkSQLiteOpenHelper$OpenHelper.onOpen(FrameworkSQLiteOpenHelper.kt:287)
                                                                                                    	at android.database.sqlite.SQLiteOpenHelper.getDatabaseLocked(SQLiteOpenHelper.java:426)
                                                                                                    	at android.database.sqlite.SQLiteOpenHelper.getWritableDatabase(SQLiteOpenHelper.java:316)
                                                                                                    	at androidx.sqlite.db.framework.FrameworkSQLiteOpenHelper$OpenHelper.getWritableOrReadableDatabase(FrameworkSQLiteOpenHelper.kt:232)
                                                                                                    	at androidx.sqlite.db.framework.FrameworkSQLiteOpenHelper$OpenHelper.innerGetDatabase(FrameworkSQLiteOpenHelper.kt:190)
                                                                                                    	at androidx.sqlite.db.framework.FrameworkSQLiteOpenHelper$OpenHelper.getSupportDatabase(FrameworkSQLiteOpenHelper.kt:151)
                                                                                                    	at androidx.sqlite.db.framework.FrameworkSQLiteOpenHelper.getWritableDatabase(FrameworkSQLiteOpenHelper.kt:104)
                                                                                                    	at androidx.room.RoomDatabase.inTransaction(RoomDatabase.kt:632)
                                                                                                    	at androidx.room.RoomDatabase.assertNotSuspendingTransaction(RoomDatabase.kt:451)
                                                                                                    	at androidx.room.RoomDatabase.query(RoomDatabase.kt:480)
                                                                                                    	at androidx.room.util.DBUtil.query(DBUtil.kt:75)
                                                                                                    	at com.akslabs.chitralaya.data.localdb.dao.RemoteSmsMessageDao_Impl$19.call(RemoteSmsMessageDao_Impl.java:612)
                                                                                                    	at com.akslabs.chitralaya.data.localdb.dao.RemoteSmsMessageDao_Impl$19.call(RemoteSmsMessageDao_Impl.java:608)
                                                                                                    	at androidx.room.CoroutinesRoom$Companion$createFlow$1$1$1.invokeSuspend(CoroutinesRoom.kt:129)
                                                                                                    	at kotlin.coroutines.jvm.internal.BaseContinuationImpl.resumeWith(ContinuationImpl.kt:33)
                                                                                                    	at kotlinx.coroutines.DispatchedTask.run(DispatchedTask.kt:104)
                                                                                                    	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1145)
                                                                                                    	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:644)
                                                                                                    	at java.lang.Thread.run(Thread.java:1117)
                                                                                                    	Suppressed: kotlinx.coroutines.internal.DiagnosticCoroutineContextException: [androidx.compose.ui.platform.MotionDurationScaleImpl@aca7db0, androidx.compose.runtime.BroadcastFrameClock@a35429, StandaloneCoroutine{Cancelling}@9e583ae, AndroidUiDispatcher@b6c454f]
2025-08-06 14:15:38.136 31751-31751 AndroidRuntime          pid-31751                            E  FATAL EXCEPTION: main
                                                                                                    Process: com.akslabs.Suchak, PID: 31751
                                                                                                    java.lang.IllegalStateException: Room cannot verify the data integrity. Looks like you've changed schema but forgot to update the version number. You can simply fix this by increasing the version number. Expected identity hash: c2649516d87989186e76f4a81f3d6c30, found: cf907bd6af687bb23799ab020b61b9fb
                                                                                                    	at androidx.room.RoomOpenHelper.checkIdentity(RoomOpenHelper.kt:146)
                                                                                                    	at androidx.room.RoomOpenHelper.onOpen(RoomOpenHelper.kt:127)
                                                                                                    	at androidx.sqlite.db.framework.FrameworkSQLiteOpenHelper$OpenHelper.onOpen(FrameworkSQLiteOpenHelper.kt:287)
                                                                                                    	at android.database.sqlite.SQLiteOpenHelper.getDatabaseLocked(SQLiteOpenHelper.java:426)
                                                                                                    	at android.database.sqlite.SQLiteOpenHelper.getWritableDatabase(SQLiteOpenHelper.java:316)
                                                                                                    	at androidx.sqlite.db.framework.FrameworkSQLiteOpenHelper$OpenHelper.getWritableOrReadableDatabase(FrameworkSQLiteOpenHelper.kt:232)
                                                                                                    	at androidx.sqlite.db.framework.FrameworkSQLiteOpenHelper$OpenHelper.innerGetDatabase(FrameworkSQLiteOpenHelper.kt:190)
                                                                                                    	at androidx.sqlite.db.framework.FrameworkSQLiteOpenHelper$OpenHelper.getSupportDatabase(FrameworkSQLiteOpenHelper.kt:151)
                                                                                                    	at androidx.sqlite.db.framework.FrameworkSQLiteOpenHelper.getWritableDatabase(FrameworkSQLiteOpenHelper.kt:104)
                                                                                                    	at androidx.room.RoomDatabase.inTransaction(RoomDatabase.kt:632)
                                                                                                    	at androidx.room.RoomDatabase.assertNotSuspendingTransaction(RoomDatabase.kt:451)
                                                                                                    	at androidx.room.RoomDatabase.query(RoomDatabase.kt:480)
                                                                                                    	at androidx.room.util.DBUtil.query(DBUtil.kt:75)
                                                                                                    	at com.akslabs.chitralaya.data.localdb.dao.RemoteSmsMessageDao_Impl$19.call(RemoteSmsMessageDao_Impl.java:612)
                                                                                                    	at com.akslabs.chitralaya.data.localdb.dao.RemoteSmsMessageDao_Impl$19.call(RemoteSmsMessageDao_Impl.java:608)
                                                                                                    	at androidx.room.CoroutinesRoom$Companion$createFlow$1$1$1.invokeSuspend(CoroutinesRoom.kt:129)
                                                                                                    	at kotlin.coroutines.jvm.internal.BaseContinuationImpl.resumeWith(ContinuationImpl.kt:33)
                                                                                                    	at kotlinx.coroutines.DispatchedTask.run(DispatchedTask.kt:104)
                                                                                                    	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1145)
                                                                                                    	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:644)
                                                                                                    	at java.lang.Thread.run(Thread.java:1117)
                                                                                                    	Suppressed: kotlinx.coroutines.internal.DiagnosticCoroutineContextException: [androidx.compose.ui.platform.MotionDurationScaleImpl@aca7db0, androidx.compose.runtime.BroadcastFrameClock@a35429, StandaloneCoroutine{Cancelling}@9e583ae, AndroidUiDispatcher@b6c454f]
2025-08-06 14:15:43.358 31800-31800 AndroidRuntime          pid-31800                            E  FATAL EXCEPTION: main
                                                                                                    Process: com.akslabs.Suchak, PID: 31800
                                                                                                    java.lang.IllegalStateException: Room cannot verify the data integrity. Looks like you've changed schema but forgot to update the version number. You can simply fix this by increasing the version number. Expected identity hash: c2649516d87989186e76f4a81f3d6c30, found: cf907bd6af687bb23799ab020b61b9fb
                                                                                                    	at androidx.room.RoomOpenHelper.checkIdentity(RoomOpenHelper.kt:146)
                                                                                                    	at androidx.room.RoomOpenHelper.onOpen(RoomOpenHelper.kt:127)
                                                                                                    	at androidx.sqlite.db.framework.FrameworkSQLiteOpenHelper$OpenHelper.onOpen(FrameworkSQLiteOpenHelper.kt:287)
                                                                                                    	at android.database.sqlite.SQLiteOpenHelper.getDatabaseLocked(SQLiteOpenHelper.java:426)
                                                                                                    	at android.database.sqlite.SQLiteOpenHelper.getWritableDatabase(SQLiteOpenHelper.java:316)
                                                                                                    	at androidx.sqlite.db.framework.FrameworkSQLiteOpenHelper$OpenHelper.getWritableOrReadableDatabase(FrameworkSQLiteOpenHelper.kt:232)
                                                                                                    	at androidx.sqlite.db.framework.FrameworkSQLiteOpenHelper$OpenHelper.innerGetDatabase(FrameworkSQLiteOpenHelper.kt:190)
                                                                                                    	at androidx.sqlite.db.framework.FrameworkSQLiteOpenHelper$OpenHelper.getSupportDatabase(FrameworkSQLiteOpenHelper.kt:151)
                                                                                                    	at androidx.sqlite.db.framework.FrameworkSQLiteOpenHelper.getWritableDatabase(FrameworkSQLiteOpenHelper.kt:104)
                                                                                                    	at androidx.room.RoomDatabase.inTransaction(RoomDatabase.kt:632)
                                                                                                    	at androidx.room.RoomDatabase.assertNotSuspendingTransaction(RoomDatabase.kt:451)
                                                                                                    	at androidx.room.RoomDatabase.query(RoomDatabase.kt:480)
                                                                                                    	at androidx.room.util.DBUtil.query(DBUtil.kt:75)
                                                                                                    	at com.akslabs.chitralaya.data.localdb.dao.RemoteSmsMessageDao_Impl$19.call(RemoteSmsMessageDao_Impl.java:612)
                                                                                                    	at com.akslabs.chitralaya.data.localdb.dao.RemoteSmsMessageDao_Impl$19.call(RemoteSmsMessageDao_Impl.java:608)
                                                                                                    	at androidx.room.CoroutinesRoom$Companion$createFlow$1$1$1.invokeSuspend(CoroutinesRoom.kt:129)
                                                                                                    	at kotlin.coroutines.jvm.internal.BaseContinuationImpl.resumeWith(ContinuationImpl.kt:33)
                                                                                                    	at kotlinx.coroutines.DispatchedTask.run(DispatchedTask.kt:104)
                                                                                                    	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1145)
                                                                                                    	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:644)
                                                                                                    	at java.lang.Thread.run(Thread.java:1117)
                                                                                                    	Suppressed: kotlinx.coroutines.internal.DiagnosticCoroutineContextException: [androidx.compose.ui.platform.MotionDurationScaleImpl@aca7db0, androidx.compose.runtime.BroadcastFrameClock@a35429, StandaloneCoroutine{Cancelling}@9e583ae, AndroidUiDispatcher@b6c454f]
--------- beginning of system
2025-08-06 14:15:53.957 31914-31914 AndroidRuntime          pid-31914                            E  FATAL EXCEPTION: main
                                                                                                    Process: com.akslabs.Suchak, PID: 31914
                                                                                                    java.lang.IllegalStateException: Room cannot verify the data integrity. Looks like you've changed schema but forgot to update the version number. You can simply fix this by increasing the version number. Expected identity hash: c2649516d87989186e76f4a81f3d6c30, found: cf907bd6af687bb23799ab020b61b9fb
                                                                                                    	at androidx.room.RoomOpenHelper.checkIdentity(RoomOpenHelper.kt:146)
                                                                                                    	at androidx.room.RoomOpenHelper.onOpen(RoomOpenHelper.kt:127)
                                                                                                    	at androidx.sqlite.db.framework.FrameworkSQLiteOpenHelper$OpenHelper.onOpen(FrameworkSQLiteOpenHelper.kt:287)
                                                                                                    	at android.database.sqlite.SQLiteOpenHelper.getDatabaseLocked(SQLiteOpenHelper.java:426)
                                                                                                    	at android.database.sqlite.SQLiteOpenHelper.getWritableDatabase(SQLiteOpenHelper.java:316)
                                                                                                    	at androidx.sqlite.db.framework.FrameworkSQLiteOpenHelper$OpenHelper.getWritableOrReadableDatabase(FrameworkSQLiteOpenHelper.kt:232)
                                                                                                    	at androidx.sqlite.db.framework.FrameworkSQLiteOpenHelper$OpenHelper.innerGetDatabase(FrameworkSQLiteOpenHelper.kt:190)
                                                                                                    	at androidx.sqlite.db.framework.FrameworkSQLiteOpenHelper$OpenHelper.getSupportDatabase(FrameworkSQLiteOpenHelper.kt:151)
                                                                                                    	at androidx.sqlite.db.framework.FrameworkSQLiteOpenHelper.getWritableDatabase(FrameworkSQLiteOpenHelper.kt:104)
                                                                                                    	at androidx.room.RoomDatabase.inTransaction(RoomDatabase.kt:632)
                                                                                                    	at androidx.room.RoomDatabase.assertNotSuspendingTransaction(RoomDatabase.kt:451)
                                                                                                    	at androidx.room.RoomDatabase.query(RoomDatabase.kt:480)
                                                                                                    	at androidx.room.util.DBUtil.query(DBUtil.kt:75)
                                                                                                    	at com.akslabs.chitralaya.data.localdb.dao.RemoteSmsMessageDao_Impl$19.call(RemoteSmsMessageDao_Impl.java:612)
                                                                                                    	at com.akslabs.chitralaya.data.localdb.dao.RemoteSmsMessageDao_Impl$19.call(RemoteSmsMessageDao_Impl.java:608)
                                                                                                    	at androidx.room.CoroutinesRoom$Companion$createFlow$1$1$1.invokeSuspend(CoroutinesRoom.kt:129)
                                                                                                    	at kotlin.coroutines.jvm.internal.BaseContinuationImpl.resumeWith(ContinuationImpl.kt:33)
                                                                                                    	at kotlinx.coroutines.DispatchedTask.run(DispatchedTask.kt:104)
                                                                                                    	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1145)
                                                                                                    	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:644)
                                                                                                    	at java.lang.Thread.run(Thread.java:1117)
                                                                                                    	Suppressed: kotlinx.coroutines.internal.DiagnosticCoroutineContextException: [androidx.compose.ui.platform.MotionDurationScaleImpl@aca7db0, androidx.compose.runtime.BroadcastFrameClock@a35429, StandaloneCoroutine{Cancelling}@9e583ae, AndroidUiDispatcher@b6c454f]
--------- beginning of main
2025-08-06 21:11:51.865  4856-4856  .akslabs.Suchak         com.akslabs.Suchak                   I  Late-enabling -Xcheck:jni
2025-08-06 21:11:51.923  4856-4856  .akslabs.Suchak         com.akslabs.Suchak                   I  Using CollectorTypeCC GC.
2025-08-06 21:11:51.971  4856-4856  nativeloader            com.akslabs.Suchak                   D  Load libframework-connectivity-tiramisu-jni.so using APEX ns com_android_tethering for caller /apex/com.android.tethering/javalib/framework-connectivity-t.jar: ok
2025-08-06 21:11:52.077  4856-4856  ApplicationLoaders      com.akslabs.Suchak                   D  Returning zygote-cached class loader: /system_ext/framework/androidx.window.extensions.jar
2025-08-06 21:11:52.077  4856-4856  ApplicationLoaders      com.akslabs.Suchak                   D  Returning zygote-cached class loader: /system_ext/framework/androidx.window.sidecar.jar
2025-08-06 21:11:52.080  4856-4856  ziparchive              com.akslabs.Suchak                   W  Unable to open '/data/app/~~qwOH1xz-fg7ph-SYpWoDSw==/com.akslabs.Suchak-Qw9yBPNcbDu78fST_fPpxQ==/base.dm': No such file or directory
2025-08-06 21:11:52.080  4856-4856  ziparchive              com.akslabs.Suchak                   W  Unable to open '/data/app/~~qwOH1xz-fg7ph-SYpWoDSw==/com.akslabs.Suchak-Qw9yBPNcbDu78fST_fPpxQ==/base.dm': No such file or directory
2025-08-06 21:11:52.581  4856-4856  nativeloader            com.akslabs.Suchak                   D  Configuring clns-7 for other apk /data/app/~~qwOH1xz-fg7ph-SYpWoDSw==/com.akslabs.Suchak-Qw9yBPNcbDu78fST_fPpxQ==/base.apk. target_sdk_version=34, uses_libraries=, library_path=/data/app/~~qwOH1xz-fg7ph-SYpWoDSw==/com.akslabs.Suchak-Qw9yBPNcbDu78fST_fPpxQ==/lib/arm64:/data/app/~~qwOH1xz-fg7ph-SYpWoDSw==/com.akslabs.Suchak-Qw9yBPNcbDu78fST_fPpxQ==/base.apk!/lib/arm64-v8a, permitted_path=/data:/mnt/expand:/data/user/0/com.akslabs.Suchak
2025-08-06 21:11:52.604  4856-4856  GraphicsEnvironment     com.akslabs.Suchak                   V  Currently set values for:
2025-08-06 21:11:52.604  4856-4856  GraphicsEnvironment     com.akslabs.Suchak                   V    angle_gl_driver_selection_pkgs=[com.android.angle, com.linecorp.b612.android, com.campmobile.snow, com.google.android.apps.tachyon]
2025-08-06 21:11:52.604  4856-4856  GraphicsEnvironment     com.akslabs.Suchak                   V    angle_gl_driver_selection_values=[angle, native, native, native]
2025-08-06 21:11:52.604  4856-4856  GraphicsEnvironment     com.akslabs.Suchak                   V  com.akslabs.Suchak is not listed in per-application setting
2025-08-06 21:11:52.605  4856-4856  GraphicsEnvironment     com.akslabs.Suchak                   V  Neither updatable production driver nor prerelease driver is supported.
2025-08-06 21:11:52.704  4856-4856  WM-WrkMgrInitializer    com.akslabs.Suchak                   D  Initializing WorkManager with default configuration.
2025-08-06 21:11:52.815  4856-4856  WM-PackageManagerHelper com.akslabs.Suchak                   D  Skipping component enablement for androidx.work.impl.background.systemjob.SystemJobService
2025-08-06 21:11:52.816  4856-4856  WM-Schedulers           com.akslabs.Suchak                   D  Created SystemJobScheduler and enabled SystemJobService
2025-08-06 21:11:53.200  4856-4856  EngineFactory           com.akslabs.Suchak                   I  Provider GmsCore_OpenSSL not available
2025-08-06 21:11:53.588  4856-4938  DatabaseDebugHelper     com.akslabs.Suchak                   I  === DATABASE DEBUG REPORT ===
2025-08-06 21:11:53.598  1882-2022  VerityUtils             system_server                        E  Failed to check whether fs-verity is enabled, errno 38: /data/app/~~qwOH1xz-fg7ph-SYpWoDSw==/com.akslabs.Suchak-Qw9yBPNcbDu78fST_fPpxQ==/base.apk
2025-08-06 21:11:53.632  4856-4938  DatabaseDebugHelper     com.akslabs.Suchak                   I  Database version: 6
2025-08-06 21:11:53.658  4856-4920  WM-WorkerWrapper        com.akslabs.Suchak                   I  Work [ id=3d9d2045-4ac8-4e5c-8421-a42972a1e890, tags={ com.akslabs.chitralaya.workers.SmsSyncWorker } ] was cancelled
                                                                                                    java.util.concurrent.CancellationException: Task was cancelled.
                                                                                                    	at androidx.work.impl.utils.futures.AbstractFuture.cancellationExceptionWithCause(AbstractFuture.java:1183)
                                                                                                    	at androidx.work.impl.utils.futures.AbstractFuture.getDoneValue(AbstractFuture.java:513)
                                                                                                    	at androidx.work.impl.utils.futures.AbstractFuture.get(AbstractFuture.java:474)
                                                                                                    	at androidx.work.impl.WorkerWrapper$2.run(WorkerWrapper.java:316)
                                                                                                    	at androidx.work.impl.utils.SerialExecutorImpl$Task.run(SerialExecutorImpl.java:96)
                                                                                                    	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1145)
                                                                                                    	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:644)
                                                                                                    	at java.lang.Thread.run(Thread.java:1117)
2025-08-06 21:11:53.663  4856-4856  DesktopModeFlagsUtil    com.akslabs.Suchak                   D  Toggle override initialized to: OVERRIDE_UNSET
2025-08-06 21:11:53.710  4856-4938  DatabaseDebugHelper     com.akslabs.Suchak                   I  Record counts:
2025-08-06 21:11:53.710  4856-4938  DatabaseDebugHelper     com.akslabs.Suchak                   I    Total SMS messages: 420
2025-08-06 21:11:53.710  4856-4938  DatabaseDebugHelper     com.akslabs.Suchak                   I    Synced SMS messages: 9
2025-08-06 21:11:53.710  4856-4938  DatabaseDebugHelper     com.akslabs.Suchak                   I    Total remote SMS messages: 165
2025-08-06 21:11:53.710  4856-4938  DatabaseDebugHelper     com.akslabs.Suchak                   I  Sample synced SMS messages:
2025-08-06 21:11:53.710  4856-4938  DatabaseDebugHelper     com.akslabs.Suchak                   I    SMS: id=20, remoteId=1800, address=BZ-INBSNL, syncedAt=1754471756893
2025-08-06 21:11:53.710  4856-4938  DatabaseDebugHelper     com.akslabs.Suchak                   I    SMS: id=19, remoteId=1799, address=AX-CENTBK, syncedAt=1754471754907
2025-08-06 21:11:53.710  4856-4938  DatabaseDebugHelper     com.akslabs.Suchak                   I    SMS: id=18, remoteId=1798, address=AX-CENTBK, syncedAt=1754471753094
2025-08-06 21:11:53.710  4856-4938  DatabaseDebugHelper     com.akslabs.Suchak                   I  Sample remote SMS messages:
2025-08-06 21:11:53.710  4856-4938  DatabaseDebugHelper     com.akslabs.Suchak                   I    RemoteSMS: remoteId=1800, originalId=20, address=BZ-INBSNL
2025-08-06 21:11:53.710  4856-4938  DatabaseDebugHelper     com.akslabs.Suchak                   I    RemoteSMS: remoteId=1799, originalId=19, address=AX-CENTBK
2025-08-06 21:11:53.710  4856-4938  DatabaseDebugHelper     com.akslabs.Suchak                   I    RemoteSMS: remoteId=1798, originalId=18, address=AX-CENTBK
2025-08-06 21:11:53.710  4856-4938  DatabaseDebugHelper     com.akslabs.Suchak                   W  SMS DATA INCONSISTENCY: Synced SMS count (9) != Remote SMS count (165)
2025-08-06 21:11:53.710  4856-4938  DatabaseDebugHelper     com.akslabs.Suchak                   I  === END DATABASE DEBUG REPORT ===
2025-08-06 21:11:53.830  4856-4856  Choreographer           com.akslabs.Suchak                   I  Skipped 59 frames!  The application may be doing too much work on its main thread.
2025-08-06 21:11:54.095  4856-4883  .akslabs.Suchak         com.akslabs.Suchak                   I  Compiler allocated 4431KB to compile void android.view.ViewRootImpl.performTraversals()
2025-08-06 21:11:54.157  1882-2022  VerityUtils             system_server                        E  Failed to check whether fs-verity is enabled, errno 38: /data/app/~~qwOH1xz-fg7ph-SYpWoDSw==/com.akslabs.Suchak-Qw9yBPNcbDu78fST_fPpxQ==/base.apk
2025-08-06 21:11:54.401  4856-4856  .akslabs.Suchak         com.akslabs.Suchak                   W  Method boolean androidx.compose.runtime.snapshots.SnapshotStateList.conditionalUpdate(boolean, kotlin.jvm.functions.Function1) failed lock verification and will run slower.
                                                                                                    Common causes for lock verification issues are non-optimized dex code
                                                                                                    and incorrect proguard optimizations.
2025-08-06 21:11:54.401  4856-4856  .akslabs.Suchak         com.akslabs.Suchak                   W  Method boolean androidx.compose.runtime.snapshots.SnapshotStateList.conditionalUpdate$default(androidx.compose.runtime.snapshots.SnapshotStateList, boolean, kotlin.jvm.functions.Function1, int, java.lang.Object) failed lock verification and will run slower.
2025-08-06 21:11:54.401  4856-4856  .akslabs.Suchak         com.akslabs.Suchak                   W  Method java.lang.Object androidx.compose.runtime.snapshots.SnapshotStateList.mutate(kotlin.jvm.functions.Function1) failed lock verification and will run slower.
2025-08-06 21:11:54.402  4856-4856  .akslabs.Suchak         com.akslabs.Suchak                   W  Method void androidx.compose.runtime.snapshots.SnapshotStateList.update(boolean, kotlin.jvm.functions.Function1) failed lock verification and will run slower.
2025-08-06 21:11:54.402  4856-4856  .akslabs.Suchak         com.akslabs.Suchak                   W  Method void androidx.compose.runtime.snapshots.SnapshotStateList.update$default(androidx.compose.runtime.snapshots.SnapshotStateList, boolean, kotlin.jvm.functions.Function1, int, java.lang.Object) failed lock verification and will run slower.
2025-08-06 21:11:54.976  4856-4942  AdrenoGLES-0            com.akslabs.Suchak                   I  QUALCOMM build                   : 95db91f, Ifbc588260a
                                                                                                    Build Date                       : 09/24/20
                                                                                                    OpenGL ES Shader Compiler Version: EV031.32.02.01
                                                                                                    Local Branch                     : mybrancheafe5b6d-fb5b-f1b0-b904-5cb90179c3e0
                                                                                                    Remote Branch                    : quic/gfx-adreno.lnx.1.0.r114-rel
                                                                                                    Remote Branch                    : NONE
                                                                                                    Reconstruct Branch               : NOTHING
2025-08-06 21:11:54.977  4856-4942  AdrenoGLES-0            com.akslabs.Suchak                   I  Build Config                     : S P 10.0.7 AArch64
2025-08-06 21:11:54.977  4856-4942  AdrenoGLES-0            com.akslabs.Suchak                   I  Driver Path                      : /vendor/lib64/egl/libGLESv2_adreno.so
2025-08-06 21:11:55.028  4856-4942  AdrenoGLES-0            com.akslabs.Suchak                   I  PFP: 0x016ee190, ME: 0x00000000
2025-08-06 21:11:55.281  4856-4978  Gralloc4                com.akslabs.Suchak                   I  mapper 4.x is not supported
2025-08-06 21:11:55.281  4856-4978  Gralloc3                com.akslabs.Suchak                   W  mapper 3.x is not supported
2025-08-06 21:11:55.308  4856-4978  Gralloc2                com.akslabs.Suchak                   I  Adding additional valid usage bits: 0x8202000
2025-08-06 21:11:55.394  4856-4894  HWUI                    com.akslabs.Suchak                   I  Davey! duration=2534ms; Flags=1, FrameTimelineVsyncId=52798737, IntendedVsync=89212362102603, Vsync=89213345438906, InputEventId=0, HandleInputStart=89213350936642, AnimationStart=89213350962215, PerformTraversalsStart=89213351409559, DrawStart=89214746278673, FrameDeadline=89212383435935, FrameInterval=89213350001017, FrameStartTime=16666717, SyncQueued=89214766568309, SyncStart=89214766839923, IssueDrawCommandsStart=89214767408934, SwapBuffers=89214887469402, FrameCompleted=89214896808673, DequeueBufferDuration=68535625, QueueBufferDuration=376823, GpuCompleted=89214896808673, SwapBuffersCompleted=89214888306694, DisplayPresentTime=8929200823284531200, CommandSubmissionCompleted=89214887469402, 
2025-08-06 21:11:55.460  4856-4856  Choreographer           com.akslabs.Suchak                   I  Skipped 92 frames!  The application may be doing too much work on its main thread.
2025-08-06 21:11:55.591  4856-4938  SmsSyncWorker           com.akslabs.Suchak                   I  === SMS SYNC WORKER STARTED ===
2025-08-06 21:11:55.592  4856-4940  DailyDatabaseBackup     com.akslabs.Suchak                   D  Daily database backup worker started
2025-08-06 21:11:55.592  4856-4939  SmsSyncWorker           com.akslabs.Suchak                   I  === SMS SYNC WORKER STARTED ===
2025-08-06 21:11:55.604  4856-4894  HWUI                    com.akslabs.Suchak                   I  Davey! duration=1677ms; Flags=0, FrameTimelineVsyncId=52799227, IntendedVsync=89213431415891, Vsync=89214964729567, InputEventId=0, HandleInputStart=89214980553673, AnimationStart=89214980559194, PerformTraversalsStart=89215041408777, DrawStart=89215081228256, FrameDeadline=89214928776970, FrameInterval=89214979737163, FrameStartTime=16666453, SyncQueued=89215101809767, SyncStart=89215102131069, IssueDrawCommandsStart=89215102254611, SwapBuffers=89215106918048, FrameCompleted=89215109603569, DequeueBufferDuration=17448, QueueBufferDuration=365104, GpuCompleted=89215109603569, SwapBuffersCompleted=89215108055965, DisplayPresentTime=72904454214516736, CommandSubmissionCompleted=89215106918048, 
2025-08-06 21:11:55.620  4856-4920  WM-Processor            com.akslabs.Suchak                   I  Moving WorkSpec (f8fd0131-4828-4246-ba66-5621ea548400) to the foreground
2025-08-06 21:11:55.643  4856-4920  WM-Processor            com.akslabs.Suchak                   I  Moving WorkSpec (1d1785e3-eb15-432c-adc4-d40b748b0346) to the foreground
2025-08-06 21:11:55.652  4856-4939  SmsSyncService          com.akslabs.Suchak                   I  === STARTING FULL SMS SYNC ===
2025-08-06 21:11:55.659  4856-4939  SmsSyncService          com.akslabs.Suchak                   I  === STARTING FULL SMS SYNC ===
2025-08-06 21:11:55.667  4856-4938  BackupHelper            com.akslabs.Suchak                   D  Backup status - Has backup: false, Data unchanged: false
2025-08-06 21:11:55.667  4856-4938  BackupHelper            com.akslabs.Suchak                   D  Current: 420 SMS, 165 remote | Last backup: 0 SMS, 0 remote
2025-08-06 21:11:55.670  4856-4940  BackupHelper            com.akslabs.Suchak                   I  === UPLOADING DATABASE TO TELEGRAM ===
2025-08-06 21:11:55.675  4856-4939  SmsSyncService          com.akslabs.Suchak                   I  Syncing SMS messages to channel ID: -1002651869724
2025-08-06 21:11:55.676  4856-4940  SmsSyncService          com.akslabs.Suchak                   I  Syncing SMS messages to channel ID: -1002651869724
2025-08-06 21:11:55.678  4856-4939  SmsReaderService        com.akslabs.Suchak                   I  🔄 Starting optimized SMS database sync...
2025-08-06 21:11:55.679  4856-4939  SmsReaderService        com.akslabs.Suchak                   I  🚀 Starting optimized SMS reading...
2025-08-06 21:11:55.681  4856-4940  SmsReaderService        com.akslabs.Suchak                   I  🔄 Starting optimized SMS database sync...
2025-08-06 21:11:55.682  4856-4940  SmsReaderService        com.akslabs.Suchak                   I  🚀 Starting optimized SMS reading...
2025-08-06 21:11:55.735  4856-4938  BackupHelper            com.akslabs.Suchak                   I  Database backup created: 420 SMS, 165 remote SMS
2025-08-06 21:11:55.738  4856-4856  WM-SystemFgDispatcher   com.akslabs.Suchak                   I  Started foreground service Intent { act=ACTION_START_FOREGROUND cmp=com.akslabs.Suchak/androidx.work.impl.foreground.SystemForegroundService (has extras) }
2025-08-06 21:11:55.743  4856-4856  WM-SystemFgDispatcher   com.akslabs.Suchak                   I  Started foreground service Intent { act=ACTION_START_FOREGROUND cmp=com.akslabs.Suchak/androidx.work.impl.foreground.SystemForegroundService (has extras) }
2025-08-06 21:11:56.017  4856-4939  SmsReaderService        com.akslabs.Suchak                   I  ✅ Successfully read 421 SMS messages
2025-08-06 21:11:56.018  4856-4939  PerformanceMonitor      com.akslabs.Suchak                   D  ⚡ FAST: sms_read_all took 338ms
2025-08-06 21:11:56.020  4856-4939  SmsReaderService        com.akslabs.Suchak                   D  Processing batch 1 with 100 messages
2025-08-06 21:11:56.035  4856-4940  SmsReaderService        com.akslabs.Suchak                   I  ✅ Successfully read 421 SMS messages
2025-08-06 21:11:56.035  4856-4940  PerformanceMonitor      com.akslabs.Suchak                   D  ⚡ FAST: sms_read_all took 354ms
2025-08-06 21:11:56.036  4856-4940  SmsReaderService        com.akslabs.Suchak                   D  Processing batch 1 with 100 messages
2025-08-06 21:11:56.337  4856-4938  BackupHelper            com.akslabs.Suchak                   I  Created backup file: database_2025-08-06_21-11-56.json (340681 bytes)
2025-08-06 21:11:56.337  4856-4938  BackupHelper            com.akslabs.Suchak                   I  Uploading to Telegram channel: -1002651869724
2025-08-06 21:11:56.733  4856-4856  Choreographer           com.akslabs.Suchak                   I  Skipped 55 frames!  The application may be doing too much work on its main thread.
2025-08-06 21:11:56.767  4856-4988  HWUI                    com.akslabs.Suchak                   I  Davey! duration=987ms; Flags=0, FrameTimelineVsyncId=52800274, IntendedVsync=89215281426889, Vsync=89215314759833, InputEventId=0, HandleInputStart=89215318071590, AnimationStart=89215318073777, PerformTraversalsStart=89215412027579, DrawStart=89215412144402, FrameDeadline=89215314760885, FrameInterval=89215318056850, FrameStartTime=16666472, SyncQueued=89216251094818, SyncStart=89216251187423, IssueDrawCommandsStart=89216251454089, SwapBuffers=89216260539558, FrameCompleted=89216269173829, DequeueBufferDuration=22604, QueueBufferDuration=232292, GpuCompleted=89216269173829, SwapBuffersCompleted=89216261103048, DisplayPresentTime=3397062777423003648, CommandSubmissionCompleted=89216260539558, 
2025-08-06 21:11:57.057  4856-4995  SmsReaderService        com.akslabs.Suchak                   D  Processing batch 2 with 100 messages
2025-08-06 21:11:57.065  4856-4995  SmsReaderService        com.akslabs.Suchak                   D  Processing batch 2 with 100 messages
2025-08-06 21:11:57.541  4856-4995  SmsReaderService        com.akslabs.Suchak                   D  Processing batch 3 with 100 messages
2025-08-06 21:11:57.552  4856-4939  SmsReaderService        com.akslabs.Suchak                   D  Processing batch 3 with 100 messages
2025-08-06 21:11:57.948  4856-4995  SmsReaderService        com.akslabs.Suchak                   D  Processing batch 4 with 100 messages
2025-08-06 21:11:57.965  4856-4995  SmsReaderService        com.akslabs.Suchak                   D  Processing batch 4 with 100 messages
2025-08-06 21:11:58.315  4856-4940  SmsReaderService        com.akslabs.Suchak                   D  Processing batch 5 with 21 messages
2025-08-06 21:11:58.326  4856-4994  SmsReaderService        com.akslabs.Suchak                   D  Processing batch 5 with 21 messages
2025-08-06 21:11:58.367  4856-4994  SmsReaderService        com.akslabs.Suchak                   I  ✅ SMS sync complete: 1 new, 411 updated
2025-08-06 21:11:58.368  4856-4994  PerformanceMonitor      com.akslabs.Suchak                   I  ⚠️ MODERATE: sms_sync_to_db took 2690ms
2025-08-06 21:11:58.368  4856-4994  SmsSyncService          com.akslabs.Suchak                   I  Synced 1 new SMS messages to local database
2025-08-06 21:11:58.374  4856-4939  SmsReaderService        com.akslabs.Suchak                   I  ✅ SMS sync complete: 0 new, 412 updated
2025-08-06 21:11:58.375  4856-4939  PerformanceMonitor      com.akslabs.Suchak                   I  ⚠️ MODERATE: sms_sync_to_db took 2697ms
2025-08-06 21:11:58.375  4856-4939  SmsSyncService          com.akslabs.Suchak                   I  Synced 0 new SMS messages to local database
2025-08-06 21:11:58.379  4856-4939  SmsSyncService          com.akslabs.Suchak                   I  Found 100 unsynced SMS messages
2025-08-06 21:11:58.380  4856-4939  SmsSyncService          com.akslabs.Suchak                   I  📦 Processing batch 1 with 10 messages (oldest first)
2025-08-06 21:11:58.380  4856-4939  SmsSyncService          com.akslabs.Suchak                   D  📅 Batch date range: 1743144060850 to 1743492220609
2025-08-06 21:11:58.380  4856-4994  SmsSyncService          com.akslabs.Suchak                   I  Found 100 unsynced SMS messages
2025-08-06 21:11:58.380  4856-4994  SmsSyncService          com.akslabs.Suchak                   I  📦 Processing batch 1 with 10 messages (oldest first)
2025-08-06 21:11:58.380  4856-4994  SmsSyncService          com.akslabs.Suchak                   D  📅 Batch date range: 1743144060850 to 1743492220609
2025-08-06 21:11:58.411  4856-4994  SmsSyncService          com.akslabs.Suchak                   D  📤 Sending SMS message 1/10: 1 (1743144060850)
2025-08-06 21:11:58.411  4856-4939  SmsSyncService          com.akslabs.Suchak                   D  📤 Sending SMS message 1/10: 1 (1743144060850)
2025-08-06 21:11:58.413  4856-4939  TelegramRateLimiter     com.akslabs.Suchak                   D  ⏱️ Throttling request, waiting 100ms
2025-08-06 21:11:58.413  4856-5007  BotApi                  com.akslabs.Suchak                   D  📤 Sending SMS message to channel: -1002651869724
2025-08-06 21:11:58.515  4856-4939  BotApi                  com.akslabs.Suchak                   D  📤 Sending SMS message to channel: -1002651869724
2025-08-06 21:11:59.235  4856-5015  ProfileInstaller        com.akslabs.Suchak                   D  Installing profile for com.akslabs.Suchak
2025-08-06 21:12:01.082  4856-4939  TelegramRateLimiter     com.akslabs.Suchak                   D  ✅ Request successful, rate limiter reset
2025-08-06 21:12:01.082  4856-4939  PerformanceMonitor      com.akslabs.Suchak                   I  ⚠️ MODERATE: telegram_send_message took 2669ms
2025-08-06 21:12:01.089  4856-4939  SmsSyncService          com.akslabs.Suchak                   I  ✅ Successfully synced SMS message: 1 (1/20 in current burst, total: 1)
2025-08-06 21:12:01.089  4856-4939  SmsSyncService          com.akslabs.Suchak                   D  ⏳ Waiting 1 second before sending next message (2/10)
2025-08-06 21:12:01.101  4856-5007  TelegramRateLimiter     com.akslabs.Suchak                   D  ✅ Request successful, rate limiter reset
2025-08-06 21:12:01.101  4856-5007  PerformanceMonitor      com.akslabs.Suchak                   I  ⚠️ MODERATE: telegram_send_message took 2688ms
2025-08-06 21:12:01.104  4856-4939  SmsSyncService          com.akslabs.Suchak                   I  ✅ Successfully synced SMS message: 1 (1/20 in current burst, total: 1)
2025-08-06 21:12:01.104  4856-4939  SmsSyncService          com.akslabs.Suchak                   D  ⏳ Waiting 1 second before sending next message (2/10)
2025-08-06 21:12:01.440  4856-4938  BackupHelper            com.akslabs.Suchak                   I  ✅ Database backup uploaded successfully: BQACAgUAAyEGAASeEFIcAAIHDGiTd8gjgbh41YLWojz1WTML7uyDAAKBFQACCJ6gVJ-VFjFgFvKlNgQ
2025-08-06 21:12:01.442  4856-5007  DailyDatabaseBackup     com.akslabs.Suchak                   I  Daily database backup completed: Database uploaded to Telegram: database_2025-08-06_21-11-56.json
2025-08-06 21:12:01.449  4856-4934  WM-WorkerWrapper        com.akslabs.Suchak                   I  Worker result SUCCESS for Work [ id=873336f5-4654-4a99-8c27-0d1e76684fdf, tags={ com.akslabs.Suchak.workers.DailyDatabaseBackupWorker } ]
2025-08-06 21:12:02.258  4856-5007  SmsSyncService          com.akslabs.Suchak                   D  📤 Sending SMS message 2/10: 2 (1743144066407)
2025-08-06 21:12:02.259  4856-4939  BotApi                  com.akslabs.Suchak                   D  📤 Sending SMS message to channel: -1002651869724
2025-08-06 21:12:02.265  4856-4938  SmsSyncService          com.akslabs.Suchak                   D  📤 Sending SMS message 2/10: 2 (1743144066407)
2025-08-06 21:12:02.266  4856-4994  TelegramRateLimiter     com.akslabs.Suchak                   D  ⏱️ Throttling request, waiting 93ms
2025-08-06 21:12:02.361  4856-4994  BotApi                  com.akslabs.Suchak                   D  📤 Sending SMS message to channel: -1002651869724
2025-08-06 21:12:02.426  4856-4884  .akslabs.Suchak         com.akslabs.Suchak                   I  Background concurrent copying GC freed 11MB AllocSpace bytes, 28(1288KB) LOS objects, 49% free, 10MB/20MB, paused 143us,42us total 181.874ms
2025-08-06 21:12:02.687  4856-4939  TelegramRateLimiter     com.akslabs.Suchak                   D  ✅ Request successful, rate limiter reset
2025-08-06 21:12:02.687  4856-4939  PerformanceMonitor      com.akslabs.Suchak                   D  ⚡ FAST: telegram_send_message took 428ms
2025-08-06 21:12:02.691  4856-4939  SmsSyncService          com.akslabs.Suchak                   I  ✅ Successfully synced SMS message: 2 (2/20 in current burst, total: 2)
2025-08-06 21:12:02.692  4856-4939  SmsSyncService          com.akslabs.Suchak                   D  ⏳ Waiting 1 second before sending next message (3/10)
2025-08-06 21:12:02.871  4856-4994  TelegramRateLimiter     com.akslabs.Suchak                   D  ✅ Request successful, rate limiter reset
2025-08-06 21:12:02.871  4856-4994  PerformanceMonitor      com.akslabs.Suchak                   D  ⚡ FAST: telegram_send_message took 605ms
2025-08-06 21:12:02.874  4856-4994  SmsSyncService          com.akslabs.Suchak                   I  ✅ Successfully synced SMS message: 2 (2/20 in current burst, total: 2)
2025-08-06 21:12:02.874  4856-4994  SmsSyncService          com.akslabs.Suchak                   D  ⏳ Waiting 1 second before sending next message (3/10)
2025-08-06 21:12:03.859  4856-4994  SmsSyncService          com.akslabs.Suchak                   D  📤 Sending SMS message 3/10: 3 (1743492128833)
2025-08-06 21:12:03.860  4856-4994  BotApi                  com.akslabs.Suchak                   D  📤 Sending SMS message to channel: -1002651869724
2025-08-06 21:12:04.038  4856-5007  SmsSyncService          com.akslabs.Suchak                   D  📤 Sending SMS message 3/10: 3 (1743492128833)
2025-08-06 21:12:04.040  4856-5007  BotApi                  com.akslabs.Suchak                   D  📤 Sending SMS message to channel: -1002651869724
2025-08-06 21:12:06.430  4856-4856  JobService              com.akslabs.Suchak                   W  onNetworkChanged() not implemented in androidx.work.impl.background.systemjob.SystemJobService. Must override in a subclass.
2025-08-06 21:12:07.495  4856-4994  TelegramRateLimiter     com.akslabs.Suchak                   D  ✅ Request successful, rate limiter reset
2025-08-06 21:12:07.495  4856-4994  PerformanceMonitor      com.akslabs.Suchak                   I  ⚠️ MODERATE: telegram_send_message took 3635ms
2025-08-06 21:12:07.502  4856-4994  SmsSyncService          com.akslabs.Suchak                   I  ✅ Successfully synced SMS message: 3 (3/20 in current burst, total: 3)
2025-08-06 21:12:07.502  4856-4994  SmsSyncService          com.akslabs.Suchak                   D  ⏳ Waiting 1 second before sending next message (4/10)
2025-08-06 21:12:07.963  4856-5007  TelegramRateLimiter     com.akslabs.Suchak                   D  ✅ Request successful, rate limiter reset
2025-08-06 21:12:07.963  4856-5007  PerformanceMonitor      com.akslabs.Suchak                   I  ⚠️ MODERATE: telegram_send_message took 3923ms
2025-08-06 21:12:07.968  4856-4994  SmsSyncService          com.akslabs.Suchak                   I  ✅ Successfully synced SMS message: 3 (3/20 in current burst, total: 3)
2025-08-06 21:12:07.968  4856-4994  SmsSyncService          com.akslabs.Suchak                   D  ⏳ Waiting 1 second before sending next message (4/10)
2025-08-06 21:12:08.658  4856-4856  JobService              com.akslabs.Suchak                   W  onNetworkChanged() not implemented in androidx.work.impl.background.systemjob.SystemJobService. Must override in a subclass.
2025-08-06 21:12:08.664  4856-4994  SmsSyncService          com.akslabs.Suchak                   D  📤 Sending SMS message 4/10: 4 (1743492180323)
2025-08-06 21:12:08.665  4856-4994  BotApi                  com.akslabs.Suchak                   D  📤 Sending SMS message to channel: -1002651869724
2025-08-06 21:12:09.096  4856-4994  TelegramRateLimiter     com.akslabs.Suchak                   D  ✅ Request successful, rate limiter reset
2025-08-06 21:12:09.097  4856-4994  PerformanceMonitor      com.akslabs.Suchak                   D  ⚡ FAST: telegram_send_message took 431ms
2025-08-06 21:12:09.102  4856-4994  SmsSyncService          com.akslabs.Suchak                   I  ✅ Successfully synced SMS message: 4 (4/20 in current burst, total: 4)
2025-08-06 21:12:09.102  4856-4994  SmsSyncService          com.akslabs.Suchak                   D  ⏳ Waiting 1 second before sending next message (5/10)
2025-08-06 21:12:09.127  4856-4994  SmsSyncService          com.akslabs.Suchak                   D  📤 Sending SMS message 4/10: 4 (1743492180323)
2025-08-06 21:12:09.128  4856-4994  BotApi                  com.akslabs.Suchak                   D  📤 Sending SMS message to channel: -1002651869724
2025-08-06 21:12:09.809  4856-4994  TelegramRateLimiter     com.akslabs.Suchak                   D  ✅ Request successful, rate limiter reset
2025-08-06 21:12:09.810  4856-4994  PerformanceMonitor      com.akslabs.Suchak                   D  ⚡ FAST: telegram_send_message took 682ms
2025-08-06 21:12:09.818  4856-4994  SmsSyncService          com.akslabs.Suchak                   I  ✅ Successfully synced SMS message: 4 (4/20 in current burst, total: 4)
2025-08-06 21:12:09.818  4856-4994  SmsSyncService          com.akslabs.Suchak                   D  ⏳ Waiting 1 second before sending next message (5/10)
2025-08-06 21:12:10.166  4856-4856  Choreographer           com.akslabs.Suchak                   I  Skipped 53 frames!  The application may be doing too much work on its main thread.
2025-08-06 21:12:10.182  4856-4894  HWUI                    com.akslabs.Suchak                   I  Davey! duration=993ms; Flags=0, FrameTimelineVsyncId=52806389, IntendedVsync=89228698561110, Vsync=89228781884330, InputEventId=0, HandleInputStart=89228794094449, AnimationStart=89228794099501, PerformTraversalsStart=89228828253095, DrawStart=89228828387001, FrameDeadline=89228798561099, FrameInterval=89228793704762, FrameStartTime=16664644, SyncQueued=89229679312261, SyncStart=89229679426949, IssueDrawCommandsStart=89229680188042, SwapBuffers=89229687423199, FrameCompleted=89229692034657, DequeueBufferDuration=82969, QueueBufferDuration=360417, GpuCompleted=89229692034657, SwapBuffersCompleted=89229688708199, DisplayPresentTime=72904454231491286, CommandSubmissionCompleted=89229687423199, 
2025-08-06 21:12:10.248  4856-4894  HWUI                    com.akslabs.Suchak                   I  Davey! duration=961ms; Flags=0, FrameTimelineVsyncId=52806482, IntendedVsync=89228798618218, Vsync=89229681999428, InputEventId=0, HandleInputStart=89229687372626, AnimationStart=89229687377886, PerformTraversalsStart=89229737529865, DrawStart=89229737678980, FrameDeadline=89229715106416, FrameInterval=89229686575178, FrameStartTime=16667570, SyncQueued=89229748068876, SyncStart=89229748599657, IssueDrawCommandsStart=89229749464345, SwapBuffers=89229756607574, FrameCompleted=89229761008928, DequeueBufferDuration=30573, QueueBufferDuration=473385, GpuCompleted=89229761008928, SwapBuffersCompleted=89229757763459, DisplayPresentTime=72057628414509841, CommandSubmissionCompleted=89229756607574, 
2025-08-06 21:12:10.261  4856-4994  SmsSyncService          com.akslabs.Suchak                   D  📤 Sending SMS message 5/10: 5 (1743492188285)
2025-08-06 21:12:10.262  4856-4994  BotApi                  com.akslabs.Suchak                   D  📤 Sending SMS message to channel: -1002651869724
2025-08-06 21:12:10.282  4856-4856  WindowOnBackDispatcher  com.akslabs.Suchak                   W  sendCancelIfRunning: isInProgress=false callback=androidx.activity.OnBackPressedDispatcher$Api34Impl$createOnBackAnimationCallback$1@cad7478
2025-08-06 21:12:10.469  4856-4856  WindowOnBackDispatcher  com.akslabs.Suchak                   W  sendCancelIfRunning: isInProgress=false callback=android.app.Activity$$ExternalSyntheticLambda0@846d021
2025-08-06 21:12:10.711  4856-4994  TelegramRateLimiter     com.akslabs.Suchak                   D  ✅ Request successful, rate limiter reset
2025-08-06 21:12:10.711  4856-4994  PerformanceMonitor      com.akslabs.Suchak                   D  ⚡ FAST: telegram_send_message took 449ms
2025-08-06 21:12:10.718  4856-4994  SmsSyncService          com.akslabs.Suchak                   I  ✅ Successfully synced SMS message: 5 (5/20 in current burst, total: 5)
2025-08-06 21:12:10.718  4856-4994  SmsSyncService          com.akslabs.Suchak                   D  ⏳ Waiting 1 second before sending next message (6/10)
2025-08-06 21:12:10.977  4856-4994  SmsSyncService          com.akslabs.Suchak                   D  📤 Sending SMS message 5/10: 5 (1743492188285)
2025-08-06 21:12:10.978  4856-4994  BotApi                  com.akslabs.Suchak                   D  📤 Sending SMS message to channel: -1002651869724
2025-08-06 21:12:11.877  4856-4939  SmsSyncService          com.akslabs.Suchak                   D  📤 Sending SMS message 6/10: 6 (1743492188478)
2025-08-06 21:12:11.878  4856-4939  BotApi                  com.akslabs.Suchak                   D  📤 Sending SMS message to channel: -1002651869724
2025-08-06 21:12:14.611  4856-4994  TelegramRateLimiter     com.akslabs.Suchak                   D  ✅ Request successful, rate limiter reset
2025-08-06 21:12:14.611  4856-4994  PerformanceMonitor      com.akslabs.Suchak                   I  ⚠️ MODERATE: telegram_send_message took 3633ms
2025-08-06 21:12:14.616  4856-4938  SmsSyncService          com.akslabs.Suchak                   I  ✅ Successfully synced SMS message: 5 (5/20 in current burst, total: 5)
2025-08-06 21:12:14.616  4856-4938  SmsSyncService          com.akslabs.Suchak                   D  ⏳ Waiting 1 second before sending next message (6/10)
2025-08-06 21:12:15.173  4856-4939  TelegramRateLimiter     com.akslabs.Suchak                   D  ✅ Request successful, rate limiter reset
2025-08-06 21:12:15.174  4856-4939  PerformanceMonitor      com.akslabs.Suchak                   I  ⚠️ MODERATE: telegram_send_message took 3296ms
2025-08-06 21:12:15.179  4856-4938  SmsSyncService          com.akslabs.Suchak                   I  ✅ Successfully synced SMS message: 6 (6/20 in current burst, total: 6)
2025-08-06 21:12:15.179  4856-4938  SmsSyncService          com.akslabs.Suchak                   D  ⏳ Waiting 1 second before sending next message (7/10)
2025-08-06 21:12:15.782  4856-4938  SmsSyncService          com.akslabs.Suchak                   D  📤 Sending SMS message 6/10: 6 (1743492188478)
2025-08-06 21:12:15.784  4856-4938  BotApi                  com.akslabs.Suchak                   D  📤 Sending SMS message to channel: -1002651869724
2025-08-06 21:12:16.237  4856-4938  TelegramRateLimiter     com.akslabs.Suchak                   D  ✅ Request successful, rate limiter reset
2025-08-06 21:12:16.237  4856-4938  PerformanceMonitor      com.akslabs.Suchak                   D  ⚡ FAST: telegram_send_message took 453ms
2025-08-06 21:12:16.247  4856-4994  SmsSyncService          com.akslabs.Suchak                   I  ✅ Successfully synced SMS message: 6 (6/20 in current burst, total: 6)
2025-08-06 21:12:16.248  4856-4994  SmsSyncService          com.akslabs.Suchak                   D  ⏳ Waiting 1 second before sending next message (7/10)
2025-08-06 21:12:16.339  4856-4994  SmsSyncService          com.akslabs.Suchak                   D  📤 Sending SMS message 7/10: 7 (1743492191789)
2025-08-06 21:12:16.340  4856-4994  BotApi                  com.akslabs.Suchak                   D  📤 Sending SMS message to channel: -1002651869724
2025-08-06 21:12:16.962  4856-4994  TelegramRateLimiter     com.akslabs.Suchak                   D  ✅ Request successful, rate limiter reset
2025-08-06 21:12:16.962  4856-4994  PerformanceMonitor      com.akslabs.Suchak                   D  ⚡ FAST: telegram_send_message took 622ms
2025-08-06 21:12:16.969  4856-4938  SmsSyncService          com.akslabs.Suchak                   I  ✅ Successfully synced SMS message: 7 (7/20 in current burst, total: 7)
2025-08-06 21:12:16.969  4856-4938  SmsSyncService          com.akslabs.Suchak                   D  ⏳ Waiting 1 second before sending next message (8/10)
2025-08-06 21:12:17.408  4856-4938  SmsSyncService          com.akslabs.Suchak                   D  📤 Sending SMS message 7/10: 7 (1743492191789)
2025-08-06 21:12:17.409  4856-4938  BotApi                  com.akslabs.Suchak                   D  📤 Sending SMS message to channel: -1002651869724
2025-08-06 21:12:17.897  4856-4938  TelegramRateLimiter     com.akslabs.Suchak                   D  ✅ Request successful, rate limiter reset
2025-08-06 21:12:17.898  4856-4938  PerformanceMonitor      com.akslabs.Suchak                   D  ⚡ FAST: telegram_send_message took 489ms
2025-08-06 21:12:17.904  4856-4939  SmsSyncService          com.akslabs.Suchak                   I  ✅ Successfully synced SMS message: 7 (7/20 in current burst, total: 7)
2025-08-06 21:12:17.904  4856-4939  SmsSyncService          com.akslabs.Suchak                   D  ⏳ Waiting 1 second before sending next message (8/10)
2025-08-06 21:12:18.126  4856-4939  SmsSyncService          com.akslabs.Suchak                   D  📤 Sending SMS message 8/10: 8 (1743492200730)
2025-08-06 21:12:18.128  4856-4939  BotApi                  com.akslabs.Suchak                   D  📤 Sending SMS message to channel: -1002651869724
2025-08-06 21:12:19.062  4856-4938  SmsSyncService          com.akslabs.Suchak                   D  📤 Sending SMS message 8/10: 8 (1743492200730)
2025-08-06 21:12:19.063  4856-4938  BotApi                  com.akslabs.Suchak                   D  📤 Sending SMS message to channel: -1002651869724
2025-08-06 21:12:21.766  4856-4939  TelegramRateLimiter     com.akslabs.Suchak                   D  ✅ Request successful, rate limiter reset
2025-08-06 21:12:21.766  4856-4939  PerformanceMonitor      com.akslabs.Suchak                   I  ⚠️ MODERATE: telegram_send_message took 3638ms
2025-08-06 21:12:21.770  4856-5007  SmsSyncService          com.akslabs.Suchak                   I  ✅ Successfully synced SMS message: 8 (8/20 in current burst, total: 8)
2025-08-06 21:12:21.770  4856-5007  SmsSyncService          com.akslabs.Suchak                   D  ⏳ Waiting 1 second before sending next message (9/10)
2025-08-06 21:12:21.990  4856-4938  TelegramRateLimiter     com.akslabs.Suchak                   D  ✅ Request successful, rate limiter reset
2025-08-06 21:12:21.991  4856-4938  PerformanceMonitor      com.akslabs.Suchak                   I  ⚠️ MODERATE: telegram_send_message took 2927ms
2025-08-06 21:12:22.002  4856-5007  SmsSyncService          com.akslabs.Suchak                   I  ✅ Successfully synced SMS message: 8 (8/20 in current burst, total: 8)
2025-08-06 21:12:22.003  4856-5007  SmsSyncService          com.akslabs.Suchak                   D  ⏳ Waiting 1 second before sending next message (9/10)
2025-08-06 21:12:22.927  4856-5007  SmsSyncService          com.akslabs.Suchak                   D  📤 Sending SMS message 9/10: 9 (1743492203825)
2025-08-06 21:12:22.928  4856-5007  BotApi                  com.akslabs.Suchak                   D  📤 Sending SMS message to channel: -1002651869724
2025-08-06 21:12:23.159  4856-4939  SmsSyncService          com.akslabs.Suchak                   D  📤 Sending SMS message 9/10: 9 (1743492203825)
2025-08-06 21:12:23.160  4856-4939  BotApi                  com.akslabs.Suchak                   D  📤 Sending SMS message to channel: -1002651869724
2025-08-06 21:12:23.406  4856-5007  TelegramRateLimiter     com.akslabs.Suchak                   D  ✅ Request successful, rate limiter reset
2025-08-06 21:12:23.406  4856-5007  PerformanceMonitor      com.akslabs.Suchak                   D  ⚡ FAST: telegram_send_message took 478ms
2025-08-06 21:12:23.427  4856-4994  SmsSyncService          com.akslabs.Suchak                   I  ✅ Successfully synced SMS message: 9 (9/20 in current burst, total: 9)
2025-08-06 21:12:23.427  4856-4994  SmsSyncService          com.akslabs.Suchak                   D  ⏳ Waiting 1 second before sending next message (10/10)
2025-08-06 21:12:23.595  4856-4939  TelegramRateLimiter     com.akslabs.Suchak                   D  ✅ Request successful, rate limiter reset
2025-08-06 21:12:23.595  4856-4939  PerformanceMonitor      com.akslabs.Suchak                   D  ⚡ FAST: telegram_send_message took 435ms
2025-08-06 21:12:23.605  4856-4994  SmsSyncService          com.akslabs.Suchak                   I  ✅ Successfully synced SMS message: 9 (9/20 in current burst, total: 9)
2025-08-06 21:12:23.606  4856-4994  SmsSyncService          com.akslabs.Suchak                   D  ⏳ Waiting 1 second before sending next message (10/10)
2025-08-06 21:12:24.204  1882-1882  JobServiceContext       system_server                        E  Binding died for com.akslabs.Suchak but no running job on this context
2025-08-06 21:12:24.208  1882-1882  JobScheduler.JobStatus  system_server                        E  Hasn't been prepared: JobStatus{877561c #u0a797/0 com.akslabs.Suchak/androidx.work.impl.background.systemjob.SystemJobService u=0 s=10797 NET BATNOTLOW READY}
2025-08-06 21:12:24.208  1882-1882  JobServiceContext       system_server                        E  Binding died for com.akslabs.Suchak but no running job on this context
2025-08-06 21:12:24.280  1882-1882  AppOps                  system_server                        E  Operation not started: uid=10797 pkg=com.akslabs.Suchak(null) op=WAKE_LOCK
2025-08-06 21:12:31.046  5230-5230  .akslabs.Suchak         com.akslabs.Suchak                   I  Late-enabling -Xcheck:jni
2025-08-06 21:12:31.148  5230-5230  .akslabs.Suchak         com.akslabs.Suchak                   I  Using CollectorTypeCC GC.
2025-08-06 21:12:31.257  5230-5230  nativeloader            com.akslabs.Suchak                   D  Load libframework-connectivity-tiramisu-jni.so using APEX ns com_android_tethering for caller /apex/com.android.tethering/javalib/framework-connectivity-t.jar: ok
2025-08-06 21:12:31.378  5230-5230  ApplicationLoaders      com.akslabs.Suchak                   D  Returning zygote-cached class loader: /system_ext/framework/androidx.window.extensions.jar
2025-08-06 21:12:31.379  5230-5230  ApplicationLoaders      com.akslabs.Suchak                   D  Returning zygote-cached class loader: /system_ext/framework/androidx.window.sidecar.jar
2025-08-06 21:12:31.381  5230-5230  ziparchive              com.akslabs.Suchak                   W  Unable to open '/data/app/~~qwOH1xz-fg7ph-SYpWoDSw==/com.akslabs.Suchak-Qw9yBPNcbDu78fST_fPpxQ==/base.dm': No such file or directory
2025-08-06 21:12:31.382  5230-5230  ziparchive              com.akslabs.Suchak                   W  Unable to open '/data/app/~~qwOH1xz-fg7ph-SYpWoDSw==/com.akslabs.Suchak-Qw9yBPNcbDu78fST_fPpxQ==/base.dm': No such file or directory
2025-08-06 21:12:32.205  5230-5230  nativeloader            com.akslabs.Suchak                   D  Configuring clns-7 for other apk /data/app/~~qwOH1xz-fg7ph-SYpWoDSw==/com.akslabs.Suchak-Qw9yBPNcbDu78fST_fPpxQ==/base.apk. target_sdk_version=34, uses_libraries=, library_path=/data/app/~~qwOH1xz-fg7ph-SYpWoDSw==/com.akslabs.Suchak-Qw9yBPNcbDu78fST_fPpxQ==/lib/arm64:/data/app/~~qwOH1xz-fg7ph-SYpWoDSw==/com.akslabs.Suchak-Qw9yBPNcbDu78fST_fPpxQ==/base.apk!/lib/arm64-v8a, permitted_path=/data:/mnt/expand:/data/user/0/com.akslabs.Suchak
2025-08-06 21:12:32.243  5230-5230  GraphicsEnvironment     com.akslabs.Suchak                   V  Currently set values for:
2025-08-06 21:12:32.243  5230-5230  GraphicsEnvironment     com.akslabs.Suchak                   V    angle_gl_driver_selection_pkgs=[com.android.angle, com.linecorp.b612.android, com.campmobile.snow, com.google.android.apps.tachyon]
2025-08-06 21:12:32.243  5230-5230  GraphicsEnvironment     com.akslabs.Suchak                   V    angle_gl_driver_selection_values=[angle, native, native, native]
2025-08-06 21:12:32.243  5230-5230  GraphicsEnvironment     com.akslabs.Suchak                   V  com.akslabs.Suchak is not listed in per-application setting
2025-08-06 21:12:32.244  5230-5230  GraphicsEnvironment     com.akslabs.Suchak                   V  Neither updatable production driver nor prerelease driver is supported.
2025-08-06 21:12:32.305  5230-5230  WM-WrkMgrInitializer    com.akslabs.Suchak                   D  Initializing WorkManager with default configuration.
2025-08-06 21:12:32.379  5230-5230  WM-PackageManagerHelper com.akslabs.Suchak                   D  Skipping component enablement for androidx.work.impl.background.systemjob.SystemJobService
2025-08-06 21:12:32.379  5230-5230  WM-Schedulers           com.akslabs.Suchak                   D  Created SystemJobScheduler and enabled SystemJobService
2025-08-06 21:12:32.670  5230-5230  AndroidKeysetManager    com.akslabs.Suchak                   W  keyset not found, will generate a new one
                                                                                                    java.io.FileNotFoundException: can't read keyset; the pref value __androidx_security_crypto_encrypted_prefs_key_keyset__ does not exist
                                                                                                    	at com.google.crypto.tink.integration.android.SharedPrefKeysetReader.readPref(SharedPrefKeysetReader.java:71)
                                                                                                    	at com.google.crypto.tink.integration.android.SharedPrefKeysetReader.readEncrypted(SharedPrefKeysetReader.java:89)
                                                                                                    	at com.google.crypto.tink.KeysetHandle.read(KeysetHandle.java:105)
                                                                                                    	at com.google.crypto.tink.integration.android.AndroidKeysetManager$Builder.read(AndroidKeysetManager.java:311)
                                                                                                    	at com.google.crypto.tink.integration.android.AndroidKeysetManager$Builder.readOrGenerateNewKeyset(AndroidKeysetManager.java:287)
                                                                                                    	at com.google.crypto.tink.integration.android.AndroidKeysetManager$Builder.build(AndroidKeysetManager.java:238)
                                                                                                    	at androidx.security.crypto.EncryptedSharedPreferences.create(EncryptedSharedPreferences.java:123)
                                                                                                    	at com.akslabs.Suchak.data.localdb.Preferences.init(Preferences.kt:36)
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
2025-08-06 21:12:32.746  5230-5230  AndroidKeysetManager    com.akslabs.Suchak                   W  keyset not found, will generate a new one
                                                                                                    java.io.FileNotFoundException: can't read keyset; the pref value __androidx_security_crypto_encrypted_prefs_value_keyset__ does not exist
                                                                                                    	at com.google.crypto.tink.integration.android.SharedPrefKeysetReader.readPref(SharedPrefKeysetReader.java:71)
                                                                                                    	at com.google.crypto.tink.integration.android.SharedPrefKeysetReader.readEncrypted(SharedPrefKeysetReader.java:89)
                                                                                                    	at com.google.crypto.tink.KeysetHandle.read(KeysetHandle.java:105)
                                                                                                    	at com.google.crypto.tink.integration.android.AndroidKeysetManager$Builder.read(AndroidKeysetManager.java:311)
                                                                                                    	at com.google.crypto.tink.integration.android.AndroidKeysetManager$Builder.readOrGenerateNewKeyset(AndroidKeysetManager.java:287)
                                                                                                    	at com.google.crypto.tink.integration.android.AndroidKeysetManager$Builder.build(AndroidKeysetManager.java:238)
                                                                                                    	at androidx.security.crypto.EncryptedSharedPreferences.create(EncryptedSharedPreferences.java:128)
                                                                                                    	at com.akslabs.Suchak.data.localdb.Preferences.init(Preferences.kt:36)
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
2025-08-06 21:12:32.781  5230-5230  EngineFactory           com.akslabs.Suchak                   I  Provider GmsCore_OpenSSL not available
2025-08-06 21:12:32.985  5230-5230  Choreographer           com.akslabs.Suchak                   I  Skipped 34 frames!  The application may be doing too much work on its main thread.
2025-08-06 21:12:33.080  5230-5252  DatabaseDebugHelper     com.akslabs.Suchak                   I  === DATABASE DEBUG REPORT ===
2025-08-06 21:12:33.135  5230-5230  DesktopModeFlagsUtil    com.akslabs.Suchak                   D  Toggle override initialized to: OVERRIDE_UNSET
2025-08-06 21:12:33.159  5230-5252  DatabaseDebugHelper     com.akslabs.Suchak                   I  Database version: 6
2025-08-06 21:12:33.175  5230-5252  DatabaseDebugHelper     com.akslabs.Suchak                   I  Record counts:
2025-08-06 21:12:33.175  5230-5252  DatabaseDebugHelper     com.akslabs.Suchak                   I    Total SMS messages: 0
2025-08-06 21:12:33.175  5230-5252  DatabaseDebugHelper     com.akslabs.Suchak                   I    Synced SMS messages: 0
2025-08-06 21:12:33.175  5230-5252  DatabaseDebugHelper     com.akslabs.Suchak                   I    Total remote SMS messages: 0
2025-08-06 21:12:33.175  5230-5252  DatabaseDebugHelper     com.akslabs.Suchak                   I  === END DATABASE DEBUG REPORT ===
2025-08-06 21:12:33.599  5230-5236  .akslabs.Suchak         com.akslabs.Suchak                   I  Compiler allocated 4431KB to compile void android.view.ViewRootImpl.performTraversals()
2025-08-06 21:12:33.717  5230-5230  .akslabs.Suchak         com.akslabs.Suchak                   W  Method boolean androidx.compose.runtime.snapshots.SnapshotStateList.conditionalUpdate(boolean, kotlin.jvm.functions.Function1) failed lock verification and will run slower.
                                                                                                    Common causes for lock verification issues are non-optimized dex code
                                                                                                    and incorrect proguard optimizations.
2025-08-06 21:12:33.717  5230-5230  .akslabs.Suchak         com.akslabs.Suchak                   W  Method boolean androidx.compose.runtime.snapshots.SnapshotStateList.conditionalUpdate$default(androidx.compose.runtime.snapshots.SnapshotStateList, boolean, kotlin.jvm.functions.Function1, int, java.lang.Object) failed lock verification and will run slower.
2025-08-06 21:12:33.717  5230-5230  .akslabs.Suchak         com.akslabs.Suchak                   W  Method java.lang.Object androidx.compose.runtime.snapshots.SnapshotStateList.mutate(kotlin.jvm.functions.Function1) failed lock verification and will run slower.
2025-08-06 21:12:33.717  5230-5230  .akslabs.Suchak         com.akslabs.Suchak                   W  Method void androidx.compose.runtime.snapshots.SnapshotStateList.update(boolean, kotlin.jvm.functions.Function1) failed lock verification and will run slower.
2025-08-06 21:12:33.717  5230-5230  .akslabs.Suchak         com.akslabs.Suchak                   W  Method void androidx.compose.runtime.snapshots.SnapshotStateList.update$default(androidx.compose.runtime.snapshots.SnapshotStateList, boolean, kotlin.jvm.functions.Function1, int, java.lang.Object) failed lock verification and will run slower.
2025-08-06 21:12:34.042  5230-5256  AdrenoGLES-0            com.akslabs.Suchak                   I  QUALCOMM build                   : 95db91f, Ifbc588260a
                                                                                                    Build Date                       : 09/24/20
                                                                                                    OpenGL ES Shader Compiler Version: EV031.32.02.01
                                                                                                    Local Branch                     : mybrancheafe5b6d-fb5b-f1b0-b904-5cb90179c3e0
                                                                                                    Remote Branch                    : quic/gfx-adreno.lnx.1.0.r114-rel
                                                                                                    Remote Branch                    : NONE
                                                                                                    Reconstruct Branch               : NOTHING
2025-08-06 21:12:34.042  5230-5256  AdrenoGLES-0            com.akslabs.Suchak                   I  Build Config                     : S P 10.0.7 AArch64
2025-08-06 21:12:34.042  5230-5256  AdrenoGLES-0            com.akslabs.Suchak                   I  Driver Path                      : /vendor/lib64/egl/libGLESv2_adreno.so
2025-08-06 21:12:34.068  5230-5256  AdrenoGLES-0            com.akslabs.Suchak                   I  PFP: 0x016ee190, ME: 0x00000000
2025-08-06 21:12:34.212  5230-5266  Gralloc4                com.akslabs.Suchak                   I  mapper 4.x is not supported
2025-08-06 21:12:34.213  5230-5266  Gralloc3                com.akslabs.Suchak                   W  mapper 3.x is not supported
2025-08-06 21:12:34.221  5230-5266  Gralloc2                com.akslabs.Suchak                   I  Adding additional valid usage bits: 0x8202000
2025-08-06 21:12:34.394  5230-5242  HWUI                    com.akslabs.Suchak                   I  Davey! duration=1133ms; Flags=1, FrameTimelineVsyncId=52822607, IntendedVsync=89252762094348, Vsync=89252762094348, InputEventId=0, HandleInputStart=89252772307409, AnimationStart=89252772311315, PerformTraversalsStart=89252772313502, DrawStart=89253721164440, FrameDeadline=89252783427680, FrameInterval=89252772294544, FrameStartTime=16666665, SyncQueued=89253737906262, SyncStart=89253738047044, IssueDrawCommandsStart=89253738376367, SwapBuffers=89253893296262, FrameCompleted=89253895797877, DequeueBufferDuration=10069636, QueueBufferDuration=384948, GpuCompleted=89253895797877, SwapBuffersCompleted=89253894499752, DisplayPresentTime=0, CommandSubmissionCompleted=89253893296262, 
2025-08-06 21:12:34.404  5230-5230  Choreographer           com.akslabs.Suchak                   I  Skipped 65 frames!  The application may be doing too much work on its main thread.
2025-08-06 21:12:34.409  5230-5252  SmsSyncWorker           com.akslabs.Suchak                   I  === SMS SYNC WORKER STARTED ===
2025-08-06 21:12:34.432  5230-5245  WM-Processor            com.akslabs.Suchak                   I  Moving WorkSpec (d76fc8de-b44a-4a1b-b7ae-e9b6c9e91d89) to the foreground
2025-08-06 21:12:34.452  5230-5252  SmsSyncService          com.akslabs.Suchak                   I  === STARTING FULL SMS SYNC ===
2025-08-06 21:12:34.466  5230-5252  SmsSyncService          com.akslabs.Suchak                   W  No channel ID configured for SMS sync
2025-08-06 21:12:34.466  5230-5252  SmsSyncWorker           com.akslabs.Suchak                   E  SMS sync failed: No Telegram channel configured
2025-08-06 21:12:34.466  5230-5252  SmsSyncWorker           com.akslabs.Suchak                   I  === SMS SYNC WORKER FINISHED ===
2025-08-06 21:12:34.468  5230-5250  WM-WorkerWrapper        com.akslabs.Suchak                   I  Worker result FAILURE for Work [ id=d76fc8de-b44a-4a1b-b7ae-e9b6c9e91d89, tags={ com.akslabs.chitralaya.workers.SmsSyncWorker } ]
2025-08-06 21:12:34.896  5230-5230  WM-SystemFgDispatcher   com.akslabs.Suchak                   I  Started foreground service Intent { act=ACTION_START_FOREGROUND cmp=com.akslabs.Suchak/androidx.work.impl.foreground.SystemForegroundService (has extras) }
2025-08-06 21:12:34.957  5230-5230  WM-SystemFgDispatcher   com.akslabs.Suchak                   I  Stopping foreground service
2025-08-06 21:12:38.557  5230-5292  ProfileInstaller        com.akslabs.Suchak                   D  Installing profile for com.akslabs.Suchak
2025-08-06 21:12:41.257  5230-5237  .akslabs.Suchak         com.akslabs.Suchak                   I  Background concurrent copying GC freed 5648KB AllocSpace bytes, 20(784KB) LOS objects, 49% free, 5899KB/11MB, paused 74us,39us total 100.839ms
2025-08-06 21:15:12.255  5230-5243  HWUI                    com.akslabs.Suchak                   I  Davey! duration=710ms; Flags=0, FrameTimelineVsyncId=52898481, IntendedVsync=89411045468930, Vsync=89411145472034, InputEventId=599244216, HandleInputStart=89411146411671, AnimationStart=89411146436827, PerformTraversalsStart=89411213530317, DrawStart=89411706054536, FrameDeadline=89411066802262, FrameInterval=89411146105057, FrameStartTime=16667184, SyncQueued=89411746445994, SyncStart=89411746670838, IssueDrawCommandsStart=89411746894119, SwapBuffers=89411747813806, FrameCompleted=89411756351827, DequeueBufferDuration=17605, QueueBufferDuration=278125, GpuCompleted=89411756351827, SwapBuffersCompleted=89411748389692, DisplayPresentTime=89397147103031, CommandSubmissionCompleted=89411747813806, 
2025-08-06 21:15:12.294  5230-5230  Choreographer           com.akslabs.Suchak                   I  Skipped 36 frames!  The application may be doing too much work on its main thread.
2025-08-06 21:17:32.997  5230-5253  PerformanceMonitor      com.akslabs.Suchak                   I  📊 === PERFORMANCE SUMMARY ===
2025-08-06 21:17:32.998  5230-5253  PerformanceMonitor      com.akslabs.Suchak                   I  📊 === END PERFORMANCE SUMMARY ===
2025-08-06 21:18:56.469  5230-5230  InsetsController        com.akslabs.Suchak                   D  show(ime(), fromIme=false)
2025-08-06 21:18:56.471  5230-5230  ImeTracker              com.akslabs.Suchak                   I  com.akslabs.Suchak:ee47475a: onRequestShow at ORIGIN_CLIENT reason SHOW_SOFT_INPUT_BY_INSETS_API fromUser false
2025-08-06 21:18:56.474  5230-5230  InputMethodManager      com.akslabs.Suchak                   D  showSoftInput() view=androidx.compose.ui.platform.AndroidComposeView{474e2f6 VFED..... .F....ID 0,0-1080,2263 aid=1073741824} flags=0 reason=SHOW_SOFT_INPUT_BY_INSETS_API
2025-08-06 21:18:56.512  5230-5230  ImeTracker              com.akslabs.Suchak                   I  com.akslabs.Suchak:52ac9a7b: onRequestShow at ORIGIN_CLIENT reason SHOW_SOFT_INPUT fromUser false
2025-08-06 21:18:56.512  5230-5230  InputMethodManager      com.akslabs.Suchak                   D  showSoftInput() view=androidx.compose.ui.platform.AndroidComposeView{474e2f6 VFED..... .F...... 0,0-1080,2263 aid=1073741824} flags=0 reason=SHOW_SOFT_INPUT
2025-08-06 21:18:56.867  5230-5230  InsetsController        com.akslabs.Suchak                   D  show(ime(), fromIme=true)
2025-08-06 21:18:56.871  5230-5230  InsetsController        com.akslabs.Suchak                   D  show(ime(), fromIme=true)
2025-08-06 21:18:56.872  5230-5230  ImeTracker              com.akslabs.Suchak                   I  com.akslabs.Suchak:52ac9a7b: onCancelled at PHASE_CLIENT_APPLY_ANIMATION
2025-08-06 21:18:56.879  5230-5869  InteractionJankMonitor  com.akslabs.Suchak                   W  Initializing without READ_DEVICE_CONFIG permission. enabled=false, interval=1, missedFrameThreshold=3, frameTimeThreshold=64, package=com.akslabs.Suchak
2025-08-06 21:18:57.085  5230-5230  ImeTracker              com.akslabs.Suchak                   I  com.akslabs.Suchak:ee47475a: onShown
2025-08-06 21:19:21.636  5230-5230  InsetsController        com.akslabs.Suchak                   D  show(ime(), fromIme=false)
2025-08-06 21:19:21.637  5230-5230  ImeTracker              com.akslabs.Suchak                   I  com.akslabs.Suchak:229841be: onRequestShow at ORIGIN_CLIENT reason SHOW_SOFT_INPUT_BY_INSETS_API fromUser false
2025-08-06 21:19:21.638  5230-5230  ImeTracker              com.akslabs.Suchak                   I  com.akslabs.Suchak:229841be: onCancelled at PHASE_CLIENT_APPLY_ANIMATION
2025-08-06 21:19:21.672  5230-5230  ImeTracker              com.akslabs.Suchak                   I  com.akslabs.Suchak:f54503cd: onRequestShow at ORIGIN_CLIENT reason SHOW_SOFT_INPUT fromUser false
2025-08-06 21:19:21.673  5230-5230  InputMethodManager      com.akslabs.Suchak                   D  showSoftInput() view=androidx.compose.ui.platform.AndroidComposeView{474e2f6 VFED..... .F...... 0,0-1080,2263 aid=1073741824} flags=0 reason=SHOW_SOFT_INPUT
2025-08-06 21:19:21.901  5230-5230  InsetsController        com.akslabs.Suchak                   D  show(ime(), fromIme=true)
2025-08-06 21:19:21.901  5230-5230  ImeTracker              com.akslabs.Suchak                   I  com.akslabs.Suchak:f54503cd: onCancelled at PHASE_CLIENT_APPLY_ANIMATION
2025-08-06 21:19:26.689  5230-5230  WindowOnBackDispatcher  com.akslabs.Suchak                   W  sendCancelIfRunning: isInProgress=false callback=ImeCallback=ImeOnBackInvokedCallback@180766098 Callback=android.window.IOnBackInvokedCallback$Stub$Proxy@bba5e22
2025-08-06 21:19:26.692  5230-5230  InsetsController        com.akslabs.Suchak                   D  hide(ime(), fromIme=true)
2025-08-06 21:19:26.917  5230-5230  ImeTracker              com.akslabs.Suchak                   I  com.akslabs.Suchak:ec5ddde6: onRequestHide at ORIGIN_CLIENT reason HIDE_SOFT_INPUT_ON_ANIMATION_STATE_CHANGED fromUser false
2025-08-06 21:19:26.919  5230-5230  ImeTracker              com.akslabs.Suchak                   I  helium314.keyboard:155d95a8: onHidden
2025-08-06 21:19:28.453  5230-5230  GettingStartedScreen    com.akslabs.Suchak                   I  Validating chat ID: -1002651869724
2025-08-06 21:19:28.454  5230-5254  BotApi                  com.akslabs.Suchak                   I  Attempting to get chat info for: Id(id=-1002651869724)
2025-08-06 21:19:36.314  5230-5254  BotApi                  com.akslabs.Suchak                   I  getChat result - isSuccess: false
2025-08-06 21:19:36.314  5230-5254  BotApi                  com.akslabs.Suchak                   W  getChat failed - result: HttpError(httpCode=401, description={"ok":false,"error_code":401,"description":"Unauthorized"})
2025-08-06 21:20:13.094  5230-5230  GettingStartedScreen    com.akslabs.Suchak                   I  Validating chat ID: -1002651869724
2025-08-06 21:20:13.096  5230-5253  BotApi                  com.akslabs.Suchak                   I  Attempting to get chat info for: Id(id=-1002651869724)
2025-08-06 21:20:13.599  5230-5253  BotApi                  com.akslabs.Suchak                   I  getChat result - isSuccess: false
2025-08-06 21:20:13.599  5230-5253  BotApi                  com.akslabs.Suchak                   W  getChat failed - result: HttpError(httpCode=401, description={"ok":false,"error_code":401,"description":"Unauthorized"})
