--------- beginning of system
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
2025-08-06 14:15:03.477  1882-2022  VerityUtils             system_server                        E  Failed to check whether fs-verity is enabled, errno 38: /data/app/~~4U105JfQdV8pDm-5yS8OUg==/com.akslabs.Suchak-zsG5HIZgkU_CmWjuaoJ8MA==/base.apk
2025-08-06 14:15:04.246  1882-2022  VerityUtils             system_server                        E  Failed to check whether fs-verity is enabled, errno 38: /data/app/~~4U105JfQdV8pDm-5yS8OUg==/com.akslabs.Suchak-zsG5HIZgkU_CmWjuaoJ8MA==/base.apk
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
2025-08-06 14:23:03.327  1882-2022  VerityUtils             system_server                        E  Failed to check whether fs-verity is enabled, errno 38: /data/app/~~CCFyotMe_1eZJ18OOh9Vvw==/com.akslabs.Suchak-V_2JdDG-QzKRQ9s3hdtp8A==/base.apk
2025-08-06 14:23:04.295  1882-2022  VerityUtils             system_server                        E  Failed to check whether fs-verity is enabled, errno 38: /data/app/~~CCFyotMe_1eZJ18OOh9Vvw==/com.akslabs.Suchak-V_2JdDG-QzKRQ9s3hdtp8A==/base.apk
--------- beginning of main
2025-08-06 14:28:09.087   319-319   InsetsController        com.akslabs.Suchak                   D  show(ime(), fromIme=false)
2025-08-06 14:28:09.088   319-319   ImeTracker              com.akslabs.Suchak                   I  com.akslabs.Suchak:538d85b4: onRequestShow at ORIGIN_CLIENT reason SHOW_SOFT_INPUT_BY_INSETS_API fromUser false
2025-08-06 14:28:09.088   319-319   InputMethodManager      com.akslabs.Suchak                   D  showSoftInput() view=androidx.compose.ui.platform.AndroidComposeView{582eacd VFED..... .F....ID 0,0-1080,2263 aid=1073741825} flags=0 reason=SHOW_SOFT_INPUT_BY_INSETS_API
2025-08-06 14:28:09.121   319-319   ImeTracker              com.akslabs.Suchak                   I  com.akslabs.Suchak:916318c7: onRequestShow at ORIGIN_CLIENT reason SHOW_SOFT_INPUT fromUser false
2025-08-06 14:28:09.121   319-319   InputMethodManager      com.akslabs.Suchak                   D  showSoftInput() view=androidx.compose.ui.platform.AndroidComposeView{582eacd VFED..... .F...... 0,0-1080,2263 aid=1073741825} flags=0 reason=SHOW_SOFT_INPUT
2025-08-06 14:28:09.435   319-319   InsetsController        com.akslabs.Suchak                   D  show(ime(), fromIme=true)
2025-08-06 14:28:09.441   319-319   InsetsController        com.akslabs.Suchak                   D  show(ime(), fromIme=true)
2025-08-06 14:28:09.442   319-319   ImeTracker              com.akslabs.Suchak                   I  com.akslabs.Suchak:916318c7: onCancelled at PHASE_CLIENT_APPLY_ANIMATION
2025-08-06 14:28:09.656   319-319   ImeTracker              com.akslabs.Suchak                   I  com.akslabs.Suchak:538d85b4: onShown
2025-08-06 14:28:12.849   319-319   WindowOnBackDispatcher  com.akslabs.Suchak                   W  sendCancelIfRunning: isInProgress=false callback=ImeCallback=ImeOnBackInvokedCallback@180766098 Callback=android.window.IOnBackInvokedCallback$Stub$Proxy@83042bb
2025-08-06 14:28:12.850   319-319   InsetsController        com.akslabs.Suchak                   D  hide(ime(), fromIme=true)
2025-08-06 14:28:13.095   319-319   ImeTracker              com.akslabs.Suchak                   I  com.akslabs.Suchak:330191bb: onRequestHide at ORIGIN_CLIENT reason HIDE_SOFT_INPUT_ON_ANIMATION_STATE_CHANGED fromUser false
2025-08-06 14:28:13.098   319-319   ImeTracker              com.akslabs.Suchak                   I  helium314.keyboard:48484976: onHidden
2025-08-06 14:28:15.350   319-319   WindowOnBackDispatcher  com.akslabs.Suchak                   W  sendCancelIfRunning: isInProgress=false callback=androidx.activity.OnBackPressedDispatcher$Api34Impl$createOnBackAnimationCallback$1@eacda57
2025-08-06 14:28:16.494   319-319   InsetsController        com.akslabs.Suchak                   D  show(ime(), fromIme=false)
2025-08-06 14:28:16.495   319-319   ImeTracker              com.akslabs.Suchak                   I  com.akslabs.Suchak:2e8e1d49: onRequestShow at ORIGIN_CLIENT reason SHOW_SOFT_INPUT_BY_INSETS_API fromUser false
2025-08-06 14:28:16.496   319-319   InputMethodManager      com.akslabs.Suchak                   D  showSoftInput() view=androidx.compose.ui.platform.AndroidComposeView{ec94902 VFED..... .F....ID 0,0-872,511 aid=1073741828} flags=0 reason=SHOW_SOFT_INPUT_BY_INSETS_API
2025-08-06 14:28:16.544   319-319   ImeTracker              com.akslabs.Suchak                   I  com.akslabs.Suchak:7072a0b9: onRequestShow at ORIGIN_CLIENT reason SHOW_SOFT_INPUT fromUser false
2025-08-06 14:28:16.545   319-319   InputMethodManager      com.akslabs.Suchak                   D  showSoftInput() view=androidx.compose.ui.platform.AndroidComposeView{ec94902 VFED..... .F...... 0,0-872,511 aid=1073741828} flags=0 reason=SHOW_SOFT_INPUT
2025-08-06 14:28:16.822   319-319   InsetsController        com.akslabs.Suchak                   D  show(ime(), fromIme=true)
2025-08-06 14:28:16.839   319-319   InsetsController        com.akslabs.Suchak                   D  show(ime(), fromIme=true)
2025-08-06 14:28:16.840   319-319   ImeTracker              com.akslabs.Suchak                   I  com.akslabs.Suchak:7072a0b9: onCancelled at PHASE_CLIENT_APPLY_ANIMATION
2025-08-06 14:28:17.057   319-319   ImeTracker              com.akslabs.Suchak                   I  com.akslabs.Suchak:2e8e1d49: onShown
2025-08-06 14:28:19.203   319-319   UidComponent            com.akslabs.Suchak                   I  Attempting to validate chat ID: -1002651869724
2025-08-06 14:28:19.208   319-319   UidComponent            com.akslabs.Suchak                   I  Parsed chat ID: -1002651869724 (group/channel)
2025-08-06 14:28:19.208   319-319   UidComponent            com.akslabs.Suchak                   I  Checking bot access to chat ID: -1002651869724
2025-08-06 14:28:19.212   319-438   BotApi                  com.akslabs.Suchak                   I  Attempting to get chat info for: Id(id=-1002651869724)
2025-08-06 14:28:20.252   319-438   BotApi                  com.akslabs.Suchak                   I  getChat result - isSuccess: false
2025-08-06 14:28:20.260   319-438   BotApi                  com.akslabs.Suchak                   W  getChat failed - result: Unknown(exception=com.google.gson.JsonSyntaxException: java.lang.IllegalStateException: Expected a string but was BEGIN_OBJECT at line 1 column 944 path $.result.pinned_message)
2025-08-06 14:28:20.261   319-319   UidComponent            com.akslabs.Suchak                   I  Bot access result: false
2025-08-06 14:28:20.261   319-319   UidComponent            com.akslabs.Suchak                   W  Bot cannot access chat ID: -1002651869724
2025-08-06 14:28:30.672   319-319   UidComponent            com.akslabs.Suchak                   I  Attempting to validate chat ID: -1002651869724
2025-08-06 14:28:30.673   319-319   UidComponent            com.akslabs.Suchak                   I  Parsed chat ID: -1002651869724 (group/channel)
2025-08-06 14:28:30.673   319-438   PerformanceMonitor      com.akslabs.Suchak                   I  📊 === PERFORMANCE SUMMARY ===
2025-08-06 14:28:30.673   319-319   UidComponent            com.akslabs.Suchak                   I  Checking bot access to chat ID: -1002651869724
2025-08-06 14:28:30.673   319-438   PerformanceMonitor      com.akslabs.Suchak                   I  📊 === END PERFORMANCE SUMMARY ===
2025-08-06 14:28:30.681   319-438   BotApi                  com.akslabs.Suchak                   I  Attempting to get chat info for: Id(id=-1002651869724)
2025-08-06 14:28:30.885   319-438   BotApi                  com.akslabs.Suchak                   I  getChat result - isSuccess: false
2025-08-06 14:28:30.886   319-438   BotApi                  com.akslabs.Suchak                   W  getChat failed - result: Unknown(exception=com.google.gson.JsonSyntaxException: java.lang.IllegalStateException: Expected a string but was BEGIN_OBJECT at line 1 column 944 path $.result.pinned_message)
2025-08-06 14:28:30.886   319-319   UidComponent            com.akslabs.Suchak                   I  Bot access result: false
2025-08-06 14:28:30.886   319-319   UidComponent            com.akslabs.Suchak                   W  Bot cannot access chat ID: -1002651869724
2025-08-06 14:28:32.033   319-319   InsetsController        com.akslabs.Suchak                   D  show(ime(), fromIme=false)
2025-08-06 14:28:32.034   319-319   ImeTracker              com.akslabs.Suchak                   I  com.akslabs.Suchak:920dbe66: onRequestShow at ORIGIN_CLIENT reason SHOW_SOFT_INPUT_BY_INSETS_API fromUser false
2025-08-06 14:28:32.034   319-319   ImeTracker              com.akslabs.Suchak                   I  com.akslabs.Suchak:920dbe66: onCancelled at PHASE_CLIENT_APPLY_ANIMATION
2025-08-06 14:28:32.059   319-319   ImeTracker              com.akslabs.Suchak                   I  com.akslabs.Suchak:314fb64f: onRequestShow at ORIGIN_CLIENT reason SHOW_SOFT_INPUT fromUser false
2025-08-06 14:28:32.059   319-319   InputMethodManager      com.akslabs.Suchak                   D  showSoftInput() view=androidx.compose.ui.platform.AndroidComposeView{ec94902 VFED..... .F....ID 0,0-872,469 aid=1073741828} flags=0 reason=SHOW_SOFT_INPUT
2025-08-06 14:28:32.217   319-319   InsetsController        com.akslabs.Suchak                   D  show(ime(), fromIme=true)
2025-08-06 14:28:32.218   319-319   ImeTracker              com.akslabs.Suchak                   I  com.akslabs.Suchak:314fb64f: onCancelled at PHASE_CLIENT_APPLY_ANIMATION
2025-08-06 14:28:32.940   319-319   InsetsController        com.akslabs.Suchak                   D  show(ime(), fromIme=false)
2025-08-06 14:28:32.941   319-319   ImeTracker              com.akslabs.Suchak                   I  com.akslabs.Suchak:8fab86d2: onRequestShow at ORIGIN_CLIENT reason SHOW_SOFT_INPUT_BY_INSETS_API fromUser false
2025-08-06 14:28:32.941   319-319   ImeTracker              com.akslabs.Suchak                   I  com.akslabs.Suchak:8fab86d2: onCancelled at PHASE_CLIENT_APPLY_ANIMATION
2025-08-06 14:28:32.963   319-319   ImeTracker              com.akslabs.Suchak                   I  com.akslabs.Suchak:741620e5: onRequestShow at ORIGIN_CLIENT reason SHOW_SOFT_INPUT fromUser false
2025-08-06 14:28:32.963   319-319   InputMethodManager      com.akslabs.Suchak                   D  showSoftInput() view=androidx.compose.ui.platform.AndroidComposeView{ec94902 VFED..... .F...... 0,0-872,469 aid=1073741828} flags=0 reason=SHOW_SOFT_INPUT
2025-08-06 14:28:32.969   319-319   InsetsController        com.akslabs.Suchak                   D  show(ime(), fromIme=true)
2025-08-06 14:28:32.969   319-319   ImeTracker              com.akslabs.Suchak                   I  com.akslabs.Suchak:741620e5: onCancelled at PHASE_CLIENT_APPLY_ANIMATION
2025-08-06 14:28:34.437   319-319   WindowOnBackDispatcher  com.akslabs.Suchak                   W  sendCancelIfRunning: isInProgress=false callback=androidx.compose.ui.window.Api33Impl$$ExternalSyntheticLambda0@f273c66
2025-08-06 14:28:35.554   319-319   UidComponent            com.akslabs.Suchak                   I  Attempting to validate chat ID: 1002651869724
2025-08-06 14:28:35.554   319-319   UidComponent            com.akslabs.Suchak                   I  Parsed chat ID: 1002651869724 (bot/user)
2025-08-06 14:28:35.554   319-319   UidComponent            com.akslabs.Suchak                   I  Checking bot access to chat ID: 1002651869724
2025-08-06 14:28:35.556   319-438   BotApi                  com.akslabs.Suchak                   I  Attempting to get chat info for: Id(id=1002651869724)
2025-08-06 14:28:36.132   319-438   BotApi                  com.akslabs.Suchak                   I  getChat result - isSuccess: false
2025-08-06 14:28:36.132   319-438   BotApi                  com.akslabs.Suchak                   W  getChat failed - result: HttpError(httpCode=400, description={"ok":false,"error_code":400,"description":"Bad Request: chat not found"})
2025-08-06 14:28:36.133   319-319   UidComponent            com.akslabs.Suchak                   I  Bot access result: false
2025-08-06 14:28:36.133   319-319   UidComponent            com.akslabs.Suchak                   W  Bot cannot access chat ID: 1002651869724
2025-08-06 14:28:37.862   319-319   UidComponent            com.akslabs.Suchak                   I  Attempting to validate chat ID: -1002651869724
2025-08-06 14:28:37.862   319-319   UidComponent            com.akslabs.Suchak                   I  Parsed chat ID: -1002651869724 (group/channel)
2025-08-06 14:28:37.862   319-319   UidComponent            com.akslabs.Suchak                   I  Checking bot access to chat ID: -1002651869724
2025-08-06 14:28:37.864   319-438   BotApi                  com.akslabs.Suchak                   I  Attempting to get chat info for: Id(id=-1002651869724)
2025-08-06 14:28:38.097   319-438   BotApi                  com.akslabs.Suchak                   I  getChat result - isSuccess: false
2025-08-06 14:28:38.097   319-438   BotApi                  com.akslabs.Suchak                   W  getChat failed - result: Unknown(exception=com.google.gson.JsonSyntaxException: java.lang.IllegalStateException: Expected a string but was BEGIN_OBJECT at line 1 column 944 path $.result.pinned_message)
2025-08-06 14:28:38.098   319-319   UidComponent            com.akslabs.Suchak                   I  Bot access result: false
2025-08-06 14:28:38.098   319-319   UidComponent            com.akslabs.Suchak                   W  Bot cannot access chat ID: -1002651869724
2025-08-06 14:28:40.053   319-319   WindowOnBackDispatcher  com.akslabs.Suchak                   W  sendCancelIfRunning: isInProgress=false callback=ImeCallback=ImeOnBackInvokedCallback@180766098 Callback=android.window.IOnBackInvokedCallback$Stub$Proxy@f255914
2025-08-06 14:28:40.082   319-319   InsetsController        com.akslabs.Suchak                   D  hide(ime(), fromIme=true)
2025-08-06 14:28:40.089   319-319   VRI[MainActivity]       com.akslabs.Suchak                   D  visibilityChanged oldVisibility=true newVisibility=false
2025-08-06 14:28:40.114   319-319   ImeTracker              com.akslabs.Suchak                   I  com.android.launcher3:5055dcd1: onCancelled at PHASE_CLIENT_ANIMATION_CANCEL
2025-08-06 14:28:40.121   319-319   ImeTracker              com.akslabs.Suchak                   I  com.akslabs.Suchak:67571295: onRequestHide at ORIGIN_CLIENT reason HIDE_SOFT_INPUT_ON_ANIMATION_STATE_CHANGED fromUser false
2025-08-06 14:28:40.122   319-319   ImeTracker              com.akslabs.Suchak                   I  com.akslabs.Suchak:67571295: onFailed at PHASE_CLIENT_VIEW_SERVED
2025-08-06 14:28:49.316   319-319   VRI[MainActivity]       com.akslabs.Suchak                   D  visibilityChanged oldVisibility=false newVisibility=true
2025-08-06 14:28:49.413   319-319   ImeBackDispatcher       com.akslabs.Suchak                   E  Ime callback not found. Ignoring unregisterReceivedCallback. callbackId: 180766098
2025-08-06 14:28:49.562   319-319   InsetsController        com.akslabs.Suchak                   D  show(ime(), fromIme=true)
2025-08-06 14:28:49.579   319-319   ImeTracker              com.akslabs.Suchak                   I  com.akslabs.Suchak:f0382da3: onShown
2025-08-06 14:28:51.612   319-319   UidComponent            com.akslabs.Suchak                   I  Attempting to validate chat ID: -1002651869724
2025-08-06 14:28:51.613   319-319   UidComponent            com.akslabs.Suchak                   I  Parsed chat ID: -1002651869724 (group/channel)
2025-08-06 14:28:51.613   319-319   UidComponent            com.akslabs.Suchak                   I  Checking bot access to chat ID: -1002651869724
2025-08-06 14:28:51.614   319-440   BotApi                  com.akslabs.Suchak                   I  Attempting to get chat info for: Id(id=-1002651869724)
2025-08-06 14:28:51.826   319-440   BotApi                  com.akslabs.Suchak                   I  getChat result - isSuccess: false
2025-08-06 14:28:51.826   319-440   BotApi                  com.akslabs.Suchak                   W  getChat failed - result: Unknown(exception=com.google.gson.JsonSyntaxException: java.lang.IllegalStateException: Expected a string but was BEGIN_OBJECT at line 1 column 944 path $.result.pinned_message)
2025-08-06 14:28:51.827   319-319   UidComponent            com.akslabs.Suchak                   I  Bot access result: false
2025-08-06 14:28:51.827   319-319   UidComponent            com.akslabs.Suchak                   W  Bot cannot access chat ID: -1002651869724
2025-08-06 14:28:53.328   319-319   InsetsController        com.akslabs.Suchak                   D  show(ime(), fromIme=false)
2025-08-06 14:28:53.329   319-319   ImeTracker              com.akslabs.Suchak                   I  com.akslabs.Suchak:f6c9f0a5: onRequestShow at ORIGIN_CLIENT reason SHOW_SOFT_INPUT_BY_INSETS_API fromUser false
2025-08-06 14:28:53.329   319-319   ImeTracker              com.akslabs.Suchak                   I  com.akslabs.Suchak:f6c9f0a5: onCancelled at PHASE_CLIENT_APPLY_ANIMATION
2025-08-06 14:28:53.390   319-319   ImeTracker              com.akslabs.Suchak                   I  com.akslabs.Suchak:25989a1: onRequestShow at ORIGIN_CLIENT reason SHOW_SOFT_INPUT fromUser false
2025-08-06 14:28:53.390   319-319   InputMethodManager      com.akslabs.Suchak                   D  showSoftInput() view=androidx.compose.ui.platform.AndroidComposeView{ec94902 VFED..... .F...... 0,0-872,469 aid=1073741828} flags=0 reason=SHOW_SOFT_INPUT
2025-08-06 14:28:53.399   319-319   InsetsController        com.akslabs.Suchak                   D  show(ime(), fromIme=true)
2025-08-06 14:28:53.399   319-319   ImeTracker              com.akslabs.Suchak                   I  com.akslabs.Suchak:25989a1: onCancelled at PHASE_CLIENT_APPLY_ANIMATION
2025-08-06 14:28:57.021   319-319   WindowOnBackDispatcher  com.akslabs.Suchak                   W  sendCancelIfRunning: isInProgress=false callback=androidx.compose.ui.window.Api33Impl$$ExternalSyntheticLambda0@1bf01e6
2025-08-06 14:28:59.667   319-319   WindowOnBackDispatcher  com.akslabs.Suchak                   W  sendCancelIfRunning: isInProgress=false callback=android.widget.PopupWindow$PopupDecorView$$ExternalSyntheticLambda1@c3415aa
2025-08-06 14:29:00.761   319-319   UidComponent            com.akslabs.Suchak                   I  Attempting to validate chat ID: -1002651869724
2025-08-06 14:29:00.761   319-319   UidComponent            com.akslabs.Suchak                   I  Parsed chat ID: -1002651869724 (group/channel)
2025-08-06 14:29:00.762   319-319   UidComponent            com.akslabs.Suchak                   I  Checking bot access to chat ID: -1002651869724
2025-08-06 14:29:00.762   319-440   BotApi                  com.akslabs.Suchak                   I  Attempting to get chat info for: Id(id=-1002651869724)
2025-08-06 14:29:00.947   319-440   BotApi                  com.akslabs.Suchak                   I  getChat result - isSuccess: false
2025-08-06 14:29:00.948   319-440   BotApi                  com.akslabs.Suchak                   W  getChat failed - result: Unknown(exception=com.google.gson.JsonSyntaxException: java.lang.IllegalStateException: Expected a string but was BEGIN_OBJECT at line 1 column 944 path $.result.pinned_message)
2025-08-06 14:29:00.948   319-319   UidComponent            com.akslabs.Suchak                   I  Bot access result: false
2025-08-06 14:29:00.948   319-319   UidComponent            com.akslabs.Suchak                   W  Bot cannot access chat ID: -1002651869724
2025-08-06 14:29:01.554   319-319   WindowOnBackDispatcher  com.akslabs.Suchak                   W  sendCancelIfRunning: isInProgress=false callback=androidx.compose.ui.window.Api33Impl$$ExternalSyntheticLambda0@a9436c8
2025-08-06 14:29:01.569   319-319   WindowOnBackDispatcher  com.akslabs.Suchak                   W  sendCancelIfRunning: isInProgress=false callback=androidx.compose.ui.window.Api33Impl$$ExternalSyntheticLambda0@d467b50
2025-08-06 14:29:01.580   319-319   InsetsController        com.akslabs.Suchak                   D  show(ime(), fromIme=false)
2025-08-06 14:29:01.581   319-319   ImeTracker              com.akslabs.Suchak                   I  com.akslabs.Suchak:662a667a: onRequestShow at ORIGIN_CLIENT reason SHOW_SOFT_INPUT_BY_INSETS_API fromUser false
2025-08-06 14:29:01.582   319-319   ImeTracker              com.akslabs.Suchak                   I  com.akslabs.Suchak:662a667a: onCancelled at PHASE_CLIENT_APPLY_ANIMATION
2025-08-06 14:29:01.671   319-319   ImeTracker              com.akslabs.Suchak                   I  com.akslabs.Suchak:9fb28620: onRequestShow at ORIGIN_CLIENT reason SHOW_SOFT_INPUT fromUser false
2025-08-06 14:29:01.671   319-319   InputMethodManager      com.akslabs.Suchak                   D  showSoftInput() view=androidx.compose.ui.platform.AndroidComposeView{ec94902 VFED..... .F...... 0,0-872,469 aid=1073741828} flags=0 reason=SHOW_SOFT_INPUT
2025-08-06 14:29:01.692   319-319   InsetsController        com.akslabs.Suchak                   D  show(ime(), fromIme=true)
2025-08-06 14:29:01.692   319-319   ImeTracker              com.akslabs.Suchak                   I  com.akslabs.Suchak:9fb28620: onCancelled at PHASE_CLIENT_APPLY_ANIMATION
2025-08-06 14:29:01.774   319-319   WindowOnBackDispatcher  com.akslabs.Suchak                   W  sendCancelIfRunning: isInProgress=false callback=android.widget.PopupWindow$PopupDecorView$$ExternalSyntheticLambda1@c30a3a0
2025-08-06 14:29:03.614   319-319   WindowOnBackDispatcher  com.akslabs.Suchak                   W  sendCancelIfRunning: isInProgress=false callback=androidx.compose.ui.window.Api33Impl$$ExternalSyntheticLambda0@841e73d
2025-08-06 14:29:04.597   319-319   VRI[MainActivity]       com.akslabs.Suchak                   D  visibilityChanged oldVisibility=true newVisibility=false
2025-08-06 14:29:04.597   319-319   VRI[MainActivity]       com.akslabs.Suchak                   D  visibilityChanged oldVisibility=true newVisibility=false
2025-08-06 14:29:04.717   319-319   WindowOnBackDispatcher  com.akslabs.Suchak                   W  sendCancelIfRunning: isInProgress=false callback=ImeCallback=ImeOnBackInvokedCallback@180766098 Callback=android.window.IOnBackInvokedCallback$Stub$Proxy@8fc0a8e
2025-08-06 14:29:04.721   319-319   InsetsController        com.akslabs.Suchak                   D  hide(ime(), fromIme=true)
2025-08-06 14:29:04.748   319-319   ImeTracker              com.akslabs.Suchak                   I  tw.nekomimi.nekogram:37636ca: onCancelled at PHASE_CLIENT_ANIMATION_CANCEL
2025-08-06 14:29:04.752   319-319   ImeTracker              com.akslabs.Suchak                   I  com.akslabs.Suchak:b643096: onRequestHide at ORIGIN_CLIENT reason HIDE_SOFT_INPUT_ON_ANIMATION_STATE_CHANGED fromUser false
2025-08-06 14:29:04.752   319-319   ImeTracker              com.akslabs.Suchak                   I  com.akslabs.Suchak:b643096: onFailed at PHASE_CLIENT_VIEW_SERVED
2025-08-06 14:29:22.744   319-326   .akslabs.Suchak         com.akslabs.Suchak                   I  Background young concurrent copying GC freed 3632KB AllocSpace bytes, 0(0B) LOS objects, 28% free, 8901KB/12MB, paused 94us,190us total 125.032ms
2025-08-06 14:29:23.805   319-326   .akslabs.Suchak         com.akslabs.Suchak                   I  Background concurrent copying GC freed 5327KB AllocSpace bytes, 0(0B) LOS objects, 49% free, 7238KB/14MB, paused 405us,72us total 142.213ms
2025-08-06 14:29:30.502   319-326   .akslabs.Suchak         com.akslabs.Suchak                   I  Background young concurrent copying GC freed 7095KB AllocSpace bytes, 0(0B) LOS objects, 48% free, 7469KB/14MB, paused 128us,67us total 109.012ms
2025-08-06 14:29:32.516   319-326   .akslabs.Suchak         com.akslabs.Suchak                   I  Background young concurrent copying GC freed 6968KB AllocSpace bytes, 0(0B) LOS objects, 47% free, 7540KB/14MB, paused 106us,179us total 116.651ms
2025-08-06 14:29:36.283   319-326   .akslabs.Suchak         com.akslabs.Suchak                   I  Background young concurrent copying GC freed 6967KB AllocSpace bytes, 0(0B) LOS objects, 47% free, 7633KB/14MB, paused 116us,138us total 120.015ms
2025-08-06 14:29:38.293   319-326   .akslabs.Suchak         com.akslabs.Suchak                   I  Background young concurrent copying GC freed 6794KB AllocSpace bytes, 0(0B) LOS objects, 46% free, 7767KB/14MB, paused 160us,178us total 120.473ms
2025-08-06 14:29:40.209   319-326   .akslabs.Suchak         com.akslabs.Suchak                   I  Background young concurrent copying GC freed 6665KB AllocSpace bytes, 0(0B) LOS objects, 46% free, 7790KB/14MB, paused 267us,183us total 129.969ms
2025-08-06 14:29:54.921   319-319   VRI[MainActivity]       com.akslabs.Suchak                   D  visibilityChanged oldVisibility=false newVisibility=true
2025-08-06 14:29:55.239   319-326   .akslabs.Suchak         com.akslabs.Suchak                   I  Background young concurrent copying GC freed 6445KB AllocSpace bytes, 0(0B) LOS objects, 41% free, 8430KB/14MB, paused 4.998ms,1.587ms total 156.125ms
2025-08-06 14:29:55.421   319-319   ImeBackDispatcher       com.akslabs.Suchak                   E  Ime callback not found. Ignoring unregisterReceivedCallback. callbackId: 180766098
2025-08-06 14:29:55.854   319-319   InsetsController        com.akslabs.Suchak                   D  show(ime(), fromIme=true)
2025-08-06 14:29:55.863   319-319   ImeTracker              com.akslabs.Suchak                   I  com.akslabs.Suchak:523ee204: onShown
2025-08-06 14:29:59.953   319-319   WindowOnBackDispatcher  com.akslabs.Suchak                   W  sendCancelIfRunning: isInProgress=false callback=androidx.compose.ui.window.Api33Impl$$ExternalSyntheticLambda0@7b159dd
2025-08-06 14:29:59.966   319-319   WindowOnBackDispatcher  com.akslabs.Suchak                   W  sendCancelIfRunning: isInProgress=false callback=androidx.compose.ui.window.Api33Impl$$ExternalSyntheticLambda0@ce32a2b
2025-08-06 14:29:59.977   319-319   WindowOnBackDispatcher  com.akslabs.Suchak                   W  sendCancelIfRunning: isInProgress=false callback=androidx.compose.ui.window.Api33Impl$$ExternalSyntheticLambda0@7616b10
2025-08-06 14:30:02.311   319-319   UidComponent            com.akslabs.Suchak                   I  Attempting to validate chat ID: -1002651869724
2025-08-06 14:30:02.311   319-319   UidComponent            com.akslabs.Suchak                   I  Parsed chat ID: -1002651869724 (group/channel)
2025-08-06 14:30:02.312   319-319   UidComponent            com.akslabs.Suchak                   I  Checking bot access to chat ID: -1002651869724
2025-08-06 14:30:02.329   319-440   BotApi                  com.akslabs.Suchak                   I  Attempting to get chat info for: Id(id=-1002651869724)
2025-08-06 14:30:03.071   319-440   BotApi                  com.akslabs.Suchak                   I  getChat result - isSuccess: false
2025-08-06 14:30:03.071   319-440   BotApi                  com.akslabs.Suchak                   W  getChat failed - result: Unknown(exception=com.google.gson.JsonSyntaxException: java.lang.IllegalStateException: Expected a string but was BEGIN_OBJECT at line 1 column 944 path $.result.pinned_message)
2025-08-06 14:30:03.071   319-319   UidComponent            com.akslabs.Suchak                   I  Bot access result: false
2025-08-06 14:30:03.071   319-319   UidComponent            com.akslabs.Suchak                   W  Bot cannot access chat ID: -1002651869724
2025-08-06 14:30:03.661   319-319   UidComponent            com.akslabs.Suchak                   I  Attempting to validate chat ID: -1002651869724
2025-08-06 14:30:03.661   319-319   UidComponent            com.akslabs.Suchak                   I  Parsed chat ID: -1002651869724 (group/channel)
2025-08-06 14:30:03.661   319-319   UidComponent            com.akslabs.Suchak                   I  Checking bot access to chat ID: -1002651869724
2025-08-06 14:30:03.663   319-440   BotApi                  com.akslabs.Suchak                   I  Attempting to get chat info for: Id(id=-1002651869724)
2025-08-06 14:30:03.874   319-440   BotApi                  com.akslabs.Suchak                   I  getChat result - isSuccess: false
2025-08-06 14:30:03.874   319-440   BotApi                  com.akslabs.Suchak                   W  getChat failed - result: Unknown(exception=com.google.gson.JsonSyntaxException: java.lang.IllegalStateException: Expected a string but was BEGIN_OBJECT at line 1 column 944 path $.result.pinned_message)
2025-08-06 14:30:03.875   319-319   UidComponent            com.akslabs.Suchak                   I  Bot access result: false
2025-08-06 14:30:03.875   319-319   UidComponent            com.akslabs.Suchak                   W  Bot cannot access chat ID: -1002651869724
2025-08-06 14:30:08.028   319-319   UidComponent            com.akslabs.Suchak                   I  Attempting to validate chat ID: -1002651869724
2025-08-06 14:30:08.028   319-319   UidComponent            com.akslabs.Suchak                   I  Parsed chat ID: -1002651869724 (group/channel)
2025-08-06 14:30:08.029   319-319   UidComponent            com.akslabs.Suchak                   I  Checking bot access to chat ID: -1002651869724
2025-08-06 14:30:08.030   319-440   BotApi                  com.akslabs.Suchak                   I  Attempting to get chat info for: Id(id=-1002651869724)
2025-08-06 14:30:08.466   319-440   BotApi                  com.akslabs.Suchak                   I  getChat result - isSuccess: false
2025-08-06 14:30:08.467   319-440   BotApi                  com.akslabs.Suchak                   W  getChat failed - result: Unknown(exception=com.google.gson.JsonSyntaxException: java.lang.IllegalStateException: Expected a string but was BEGIN_OBJECT at line 1 column 944 path $.result.pinned_message)
2025-08-06 14:30:08.468   319-319   UidComponent            com.akslabs.Suchak                   I  Bot access result: false
2025-08-06 14:30:08.468   319-319   UidComponent            com.akslabs.Suchak                   W  Bot cannot access chat ID: -1002651869724
2025-08-06 14:30:18.963   319-319   WindowOnBackDispatcher  com.akslabs.Suchak                   W  sendCancelIfRunning: isInProgress=false callback=ImeCallback=ImeOnBackInvokedCallback@180766098 Callback=android.window.IOnBackInvokedCallback$Stub$Proxy@da61c9a
2025-08-06 14:30:18.965   319-319   InsetsController        com.akslabs.Suchak                   D  hide(ime(), fromIme=true)
2025-08-06 14:30:19.191   319-319   ImeTracker              com.akslabs.Suchak                   I  com.akslabs.Suchak:b8959ed0: onRequestHide at ORIGIN_CLIENT reason HIDE_SOFT_INPUT_ON_ANIMATION_STATE_CHANGED fromUser false
2025-08-06 14:30:19.192   319-319   ImeTracker              com.akslabs.Suchak                   I  helium314.keyboard:a59a4606: onHidden
2025-08-06 14:30:26.622   319-319   VRI[MainActivity]       com.akslabs.Suchak                   D  visibilityChanged oldVisibility=true newVisibility=false
2025-08-06 14:30:32.203   319-326   .akslabs.Suchak         com.akslabs.Suchak                   I  Background concurrent copying GC freed 7061KB AllocSpace bytes, 4(80KB) LOS objects, 49% free, 7437KB/14MB, paused 676us,122us total 156.392ms
2025-08-06 14:30:35.303   319-326   .akslabs.Suchak         com.akslabs.Suchak                   I  Background young concurrent copying GC freed 7332KB AllocSpace bytes, 0(0B) LOS objects, 48% free, 7645KB/14MB, paused 103us,187us total 122.430ms
---------------------------- PROCESS STARTED (3445) for package com.akslabs.Suchak ----------------------------
2025-08-06 14:30:57.259  3445-3445  ApplicationLoaders      com.akslabs.Suchak                   D  Returning zygote-cached class loader: /system_ext/framework/androidx.window.extensions.jar
2025-08-06 14:30:57.259  3445-3445  ApplicationLoaders      com.akslabs.Suchak                   D  Returning zygote-cached class loader: /system_ext/framework/androidx.window.sidecar.jar
2025-08-06 14:30:57.261  3445-3445  ziparchive              com.akslabs.Suchak                   W  Unable to open '/data/app/~~N4VSUf1rDAH8AjWvhTH7ow==/com.akslabs.Suchak-JHE6wqPY1ErYFHuf4uIzRA==/base.dm': No such file or directory
2025-08-06 14:30:57.261  3445-3445  ziparchive              com.akslabs.Suchak                   W  Unable to open '/data/app/~~N4VSUf1rDAH8AjWvhTH7ow==/com.akslabs.Suchak-JHE6wqPY1ErYFHuf4uIzRA==/base.dm': No such file or directory
2025-08-06 14:30:57.753  3445-3445  nativeloader            com.akslabs.Suchak                   D  Configuring clns-7 for other apk /data/app/~~N4VSUf1rDAH8AjWvhTH7ow==/com.akslabs.Suchak-JHE6wqPY1ErYFHuf4uIzRA==/base.apk. target_sdk_version=34, uses_libraries=, library_path=/data/app/~~N4VSUf1rDAH8AjWvhTH7ow==/com.akslabs.Suchak-JHE6wqPY1ErYFHuf4uIzRA==/lib/arm64:/data/app/~~N4VSUf1rDAH8AjWvhTH7ow==/com.akslabs.Suchak-JHE6wqPY1ErYFHuf4uIzRA==/base.apk!/lib/arm64-v8a, permitted_path=/data:/mnt/expand:/data/user/0/com.akslabs.Suchak
2025-08-06 14:30:57.774  3445-3445  GraphicsEnvironment     com.akslabs.Suchak                   V  Currently set values for:
2025-08-06 14:30:57.774  3445-3445  GraphicsEnvironment     com.akslabs.Suchak                   V    angle_gl_driver_selection_pkgs=[com.android.angle, com.linecorp.b612.android, com.campmobile.snow, com.google.android.apps.tachyon]
2025-08-06 14:30:57.774  3445-3445  GraphicsEnvironment     com.akslabs.Suchak                   V    angle_gl_driver_selection_values=[angle, native, native, native]
2025-08-06 14:30:57.774  3445-3445  GraphicsEnvironment     com.akslabs.Suchak                   V  com.akslabs.Suchak is not listed in per-application setting
2025-08-06 14:30:57.774  3445-3445  GraphicsEnvironment     com.akslabs.Suchak                   V  Neither updatable production driver nor prerelease driver is supported.
2025-08-06 14:30:57.850  3445-3445  WM-WrkMgrInitializer    com.akslabs.Suchak                   D  Initializing WorkManager with default configuration.
2025-08-06 14:30:57.941  3445-3445  WM-PackageManagerHelper com.akslabs.Suchak                   D  Skipping component enablement for androidx.work.impl.background.systemjob.SystemJobService
2025-08-06 14:30:57.942  3445-3445  WM-Schedulers           com.akslabs.Suchak                   D  Created SystemJobScheduler and enabled SystemJobService
2025-08-06 14:30:58.404  3445-3445  EngineFactory           com.akslabs.Suchak                   I  Provider GmsCore_OpenSSL not available
2025-08-06 14:30:58.796  3445-3445  Choreographer           com.akslabs.Suchak                   I  Skipped 49 frames!  The application may be doing too much work on its main thread.
2025-08-06 14:30:58.975  3445-3574  DatabaseDebugHelper     com.akslabs.Suchak                   I  === DATABASE DEBUG REPORT ===
2025-08-06 14:30:59.068  3445-3445  DesktopModeFlagsUtil    com.akslabs.Suchak                   D  Toggle override initialized to: OVERRIDE_UNSET
2025-08-06 14:30:59.395  3445-3574  DatabaseDebugHelper     com.akslabs.Suchak                   I  Database version: 6
2025-08-06 14:30:59.414  3445-3574  DatabaseDebugHelper     com.akslabs.Suchak                   I  Record counts:
2025-08-06 14:30:59.414  3445-3574  DatabaseDebugHelper     com.akslabs.Suchak                   I    Total SMS messages: 0
2025-08-06 14:30:59.414  3445-3574  DatabaseDebugHelper     com.akslabs.Suchak                   I    Synced SMS messages: 0
2025-08-06 14:30:59.414  3445-3574  DatabaseDebugHelper     com.akslabs.Suchak                   I    Total remote SMS messages: 0
2025-08-06 14:30:59.414  3445-3574  DatabaseDebugHelper     com.akslabs.Suchak                   I  === END DATABASE DEBUG REPORT ===
2025-08-06 14:30:59.835  3445-3464  .akslabs.Suchak         com.akslabs.Suchak                   I  Compiler allocated 4431KB to compile void android.view.ViewRootImpl.performTraversals()
2025-08-06 14:31:00.034  3445-3445  .akslabs.Suchak         com.akslabs.Suchak                   W  Method boolean androidx.compose.runtime.snapshots.SnapshotStateList.conditionalUpdate(boolean, kotlin.jvm.functions.Function1) failed lock verification and will run slower.
                                                                                                    Common causes for lock verification issues are non-optimized dex code
                                                                                                    and incorrect proguard optimizations.
2025-08-06 14:31:00.034  3445-3445  .akslabs.Suchak         com.akslabs.Suchak                   W  Method boolean androidx.compose.runtime.snapshots.SnapshotStateList.conditionalUpdate$default(androidx.compose.runtime.snapshots.SnapshotStateList, boolean, kotlin.jvm.functions.Function1, int, java.lang.Object) failed lock verification and will run slower.
2025-08-06 14:31:00.034  3445-3445  .akslabs.Suchak         com.akslabs.Suchak                   W  Method java.lang.Object androidx.compose.runtime.snapshots.SnapshotStateList.mutate(kotlin.jvm.functions.Function1) failed lock verification and will run slower.
2025-08-06 14:31:00.034  3445-3445  .akslabs.Suchak         com.akslabs.Suchak                   W  Method void androidx.compose.runtime.snapshots.SnapshotStateList.update(boolean, kotlin.jvm.functions.Function1) failed lock verification and will run slower.
2025-08-06 14:31:00.035  3445-3445  .akslabs.Suchak         com.akslabs.Suchak                   W  Method void androidx.compose.runtime.snapshots.SnapshotStateList.update$default(androidx.compose.runtime.snapshots.SnapshotStateList, boolean, kotlin.jvm.functions.Function1, int, java.lang.Object) failed lock verification and will run slower.
2025-08-06 14:31:00.865  3445-3579  AdrenoGLES-0            com.akslabs.Suchak                   I  QUALCOMM build                   : 95db91f, Ifbc588260a
                                                                                                    Build Date                       : 09/24/20
                                                                                                    OpenGL ES Shader Compiler Version: EV031.32.02.01
                                                                                                    Local Branch                     : mybrancheafe5b6d-fb5b-f1b0-b904-5cb90179c3e0
                                                                                                    Remote Branch                    : quic/gfx-adreno.lnx.1.0.r114-rel
                                                                                                    Remote Branch                    : NONE
                                                                                                    Reconstruct Branch               : NOTHING
2025-08-06 14:31:00.866  3445-3579  AdrenoGLES-0            com.akslabs.Suchak                   I  Build Config                     : S P 10.0.7 AArch64
2025-08-06 14:31:00.866  3445-3579  AdrenoGLES-0            com.akslabs.Suchak                   I  Driver Path                      : /vendor/lib64/egl/libGLESv2_adreno.so
2025-08-06 14:31:00.890  3445-3579  AdrenoGLES-0            com.akslabs.Suchak                   I  PFP: 0x016ee190, ME: 0x00000000
2025-08-06 14:31:01.118  3445-3716  Gralloc4                com.akslabs.Suchak                   I  mapper 4.x is not supported
2025-08-06 14:31:01.119  3445-3716  Gralloc3                com.akslabs.Suchak                   W  mapper 3.x is not supported
2025-08-06 14:31:01.125  3445-3716  Gralloc2                com.akslabs.Suchak                   I  Adding additional valid usage bits: 0x8202000
2025-08-06 14:31:01.190  3445-3476  HWUI                    com.akslabs.Suchak                   I  Davey! duration=1852ms; Flags=1, FrameTimelineVsyncId=38145160, IntendedVsync=65158840908490, Vsync=65158874242198, InputEventId=0, HandleInputStart=65158880172798, AnimationStart=65158880175246, PerformTraversalsStart=65158880176235, DrawStart=65160606127016, FrameDeadline=65158862241822, FrameInterval=65158880154100, FrameStartTime=16666854, SyncQueued=65160648093526, SyncStart=65160648819516, IssueDrawCommandsStart=65160650951912, SwapBuffers=65160692388787, FrameCompleted=65160694339568, DequeueBufferDuration=2356146, QueueBufferDuration=449323, GpuCompleted=65160694339568, SwapBuffersCompleted=65160693780089, DisplayPresentTime=0, CommandSubmissionCompleted=65160692388787, 
2025-08-06 14:31:01.217  3445-3445  Choreographer           com.akslabs.Suchak                   I  Skipped 103 frames!  The application may be doing too much work on its main thread.
2025-08-06 14:31:01.444  3445-3762  HWUI                    com.akslabs.Suchak                   I  Davey! duration=1905ms; Flags=1, FrameTimelineVsyncId=38145218, IntendedVsync=65159007570653, Vsync=65160724231071, InputEventId=0, HandleInputStart=65160737408682, AnimationStart=65160737411807, PerformTraversalsStart=65160745121495, DrawStart=65160892856911, FrameDeadline=65159028903985, FrameInterval=65160736920714, FrameStartTime=16666606, SyncQueued=65160904097797, SyncStart=65160904328891, IssueDrawCommandsStart=65160904646339, SwapBuffers=65160911008734, FrameCompleted=65160913723266, DequeueBufferDuration=24896, QueueBufferDuration=319531, GpuCompleted=65160913723266, SwapBuffersCompleted=65160912875766, DisplayPresentTime=0, CommandSubmissionCompleted=65160911008734, 
2025-08-06 14:31:01.444  3445-3762  HWUI                    com.akslabs.Suchak                   I  Davey! duration=1916ms; Flags=1, FrameTimelineVsyncId=38145218, IntendedVsync=65159007570653, Vsync=65160724231071, InputEventId=0, HandleInputStart=65160737408682, AnimationStart=65160737411807, PerformTraversalsStart=65160745121495, DrawStart=65160913992953, FrameDeadline=65160728929470, FrameInterval=65160736920714, FrameStartTime=16666606, SyncQueued=65160918562953, SyncStart=65160918767380, IssueDrawCommandsStart=65160918960297, SwapBuffers=65160921866807, FrameCompleted=65160923849255, DequeueBufferDuration=26354, QueueBufferDuration=305156, GpuCompleted=65160923849255, SwapBuffersCompleted=65160922982432, DisplayPresentTime=72904454214516736, CommandSubmissionCompleted=65160921866807, 
2025-08-06 14:31:01.480  3445-3574  SmsSyncWorker           com.akslabs.Suchak                   I  === SMS SYNC WORKER STARTED ===
2025-08-06 14:31:01.527  3445-3654  WM-Processor            com.akslabs.Suchak                   I  Moving WorkSpec (ccd08bec-88c3-401a-a9d1-7a2508128e69) to the foreground
2025-08-06 14:31:01.549  3445-3445  WM-SystemFgDispatcher   com.akslabs.Suchak                   I  Started foreground service Intent { act=ACTION_START_FOREGROUND cmp=com.akslabs.Suchak/androidx.work.impl.foreground.SystemForegroundService (has extras) }
2025-08-06 14:31:01.571  3445-3574  SmsSyncService          com.akslabs.Suchak                   I  === STARTING FULL SMS SYNC ===
2025-08-06 14:31:01.588  3445-3574  SmsSyncService          com.akslabs.Suchak                   W  No channel ID configured for SMS sync
2025-08-06 14:31:01.589  3445-3574  SmsSyncWorker           com.akslabs.Suchak                   E  SMS sync failed: No Telegram channel configured
2025-08-06 14:31:01.589  3445-3574  SmsSyncWorker           com.akslabs.Suchak                   I  === SMS SYNC WORKER FINISHED ===
2025-08-06 14:31:01.687  3445-3534  WM-WorkerWrapper        com.akslabs.Suchak                   I  Worker result FAILURE for Work [ id=ccd08bec-88c3-401a-a9d1-7a2508128e69, tags={ com.akslabs.chitralaya.workers.SmsSyncWorker } ]
2025-08-06 14:31:01.703  3445-3445  WM-SystemFgDispatcher   com.akslabs.Suchak                   I  Stopping foreground service
2025-08-06 14:31:01.756  1882-2022  VerityUtils             system_server                        E  Failed to check whether fs-verity is enabled, errno 38: /data/app/~~N4VSUf1rDAH8AjWvhTH7ow==/com.akslabs.Suchak-JHE6wqPY1ErYFHuf4uIzRA==/base.apk
2025-08-06 14:31:02.222  1882-2022  VerityUtils             system_server                        E  Failed to check whether fs-verity is enabled, errno 38: /data/app/~~N4VSUf1rDAH8AjWvhTH7ow==/com.akslabs.Suchak-JHE6wqPY1ErYFHuf4uIzRA==/base.apk
2025-08-06 14:31:04.251  3445-3900  ProfileInstaller        com.akslabs.Suchak                   D  Installing profile for com.akslabs.Suchak
2025-08-06 14:31:05.203  3445-3445  WindowOnBackDispatcher  com.akslabs.Suchak                   W  sendCancelIfRunning: isInProgress=false callback=androidx.activity.OnBackPressedDispatcher$Api34Impl$createOnBackAnimationCallback$1@a2eba8
2025-08-06 14:31:06.046  3445-3445  InsetsController        com.akslabs.Suchak                   D  show(ime(), fromIme=false)
2025-08-06 14:31:06.049  3445-3445  ImeTracker              com.akslabs.Suchak                   I  com.akslabs.Suchak:2003a43e: onRequestShow at ORIGIN_CLIENT reason SHOW_SOFT_INPUT_BY_INSETS_API fromUser false
2025-08-06 14:31:06.052  3445-3445  InputMethodManager      com.akslabs.Suchak                   D  showSoftInput() view=androidx.compose.ui.platform.AndroidComposeView{7a00ccc VFED..... .F....ID 0,0-1080,2263 aid=1073741825} flags=0 reason=SHOW_SOFT_INPUT_BY_INSETS_API
2025-08-06 14:31:06.150  3445-3445  ImeTracker              com.akslabs.Suchak                   I  com.akslabs.Suchak:4af756cb: onRequestShow at ORIGIN_CLIENT reason SHOW_SOFT_INPUT fromUser false
2025-08-06 14:31:06.150  3445-3445  InputMethodManager      com.akslabs.Suchak                   D  showSoftInput() view=androidx.compose.ui.platform.AndroidComposeView{7a00ccc VFED..... .F...... 0,0-1080,2263 aid=1073741825} flags=0 reason=SHOW_SOFT_INPUT
2025-08-06 14:31:06.378  3445-3445  InsetsController        com.akslabs.Suchak                   D  show(ime(), fromIme=true)
2025-08-06 14:31:06.398  3445-3914  InteractionJankMonitor  com.akslabs.Suchak                   W  Initializing without READ_DEVICE_CONFIG permission. enabled=false, interval=1, missedFrameThreshold=3, frameTimeThreshold=64, package=com.akslabs.Suchak
2025-08-06 14:31:06.624  3445-3445  ImeTracker              com.akslabs.Suchak                   I  com.akslabs.Suchak:4af756cb: onShown
2025-08-06 14:31:08.922  3445-3465  .akslabs.Suchak         com.akslabs.Suchak                   W  Cleared Reference was only reachable from finalizer (only reported once)
2025-08-06 14:31:08.936  3445-3465  .akslabs.Suchak         com.akslabs.Suchak                   I  Background concurrent copying GC freed 5275KB AllocSpace bytes, 14(472KB) LOS objects, 49% free, 5927KB/11MB, paused 128us,80us total 108.409ms
2025-08-06 14:31:09.666  3445-3445  WindowOnBackDispatcher  com.akslabs.Suchak                   W  sendCancelIfRunning: isInProgress=false callback=ImeCallback=ImeOnBackInvokedCallback@180766098 Callback=android.window.IOnBackInvokedCallback$Stub$Proxy@ed57a10
2025-08-06 14:31:09.667  3445-3445  InsetsController        com.akslabs.Suchak                   D  hide(ime(), fromIme=true)
2025-08-06 14:31:09.895  3445-3445  ImeTracker              com.akslabs.Suchak                   I  com.akslabs.Suchak:695e499e: onRequestHide at ORIGIN_CLIENT reason HIDE_SOFT_INPUT_ON_ANIMATION_STATE_CHANGED fromUser false
2025-08-06 14:31:09.897  3445-3445  ImeTracker              com.akslabs.Suchak                   I  helium314.keyboard:859a3d8d: onHidden
2025-08-06 14:31:11.999  3445-3445  WindowOnBackDispatcher  com.akslabs.Suchak                   W  sendCancelIfRunning: isInProgress=false callback=androidx.activity.OnBackPressedDispatcher$Api34Impl$createOnBackAnimationCallback$1@979a700
2025-08-06 14:31:12.783  3445-3445  InsetsController        com.akslabs.Suchak                   D  show(ime(), fromIme=false)
2025-08-06 14:31:12.783  3445-3445  ImeTracker              com.akslabs.Suchak                   I  com.akslabs.Suchak:138355b8: onRequestShow at ORIGIN_CLIENT reason SHOW_SOFT_INPUT_BY_INSETS_API fromUser false
2025-08-06 14:31:12.784  3445-3445  InputMethodManager      com.akslabs.Suchak                   D  showSoftInput() view=androidx.compose.ui.platform.AndroidComposeView{eb0305d VFED..... .F....ID 0,0-872,511 aid=1073741828} flags=0 reason=SHOW_SOFT_INPUT_BY_INSETS_API
2025-08-06 14:31:12.814  3445-3445  ImeTracker              com.akslabs.Suchak                   I  com.akslabs.Suchak:b4099502: onRequestShow at ORIGIN_CLIENT reason SHOW_SOFT_INPUT fromUser false
2025-08-06 14:31:12.814  3445-3445  InputMethodManager      com.akslabs.Suchak                   D  showSoftInput() view=androidx.compose.ui.platform.AndroidComposeView{eb0305d VFED..... .F...... 0,0-872,511 aid=1073741828} flags=0 reason=SHOW_SOFT_INPUT
2025-08-06 14:31:13.056  3445-3445  InsetsController        com.akslabs.Suchak                   D  show(ime(), fromIme=true)
2025-08-06 14:31:13.102  3445-3445  InsetsController        com.akslabs.Suchak                   D  show(ime(), fromIme=true)
2025-08-06 14:31:13.103  3445-3445  ImeTracker              com.akslabs.Suchak                   I  com.akslabs.Suchak:b4099502: onCancelled at PHASE_CLIENT_APPLY_ANIMATION
2025-08-06 14:31:13.290  3445-3445  ImeTracker              com.akslabs.Suchak                   I  com.akslabs.Suchak:138355b8: onShown
2025-08-06 14:31:16.982  3445-3445  UidComponent            com.akslabs.Suchak                   I  Attempting to validate chat ID: -1002651869724
2025-08-06 14:31:16.985  3445-3445  UidComponent            com.akslabs.Suchak                   I  Parsed chat ID: -1002651869724 (group/channel)
2025-08-06 14:31:16.986  3445-3445  UidComponent            com.akslabs.Suchak                   I  Checking bot access to chat ID: -1002651869724
2025-08-06 14:31:16.988  3445-3577  BotApi                  com.akslabs.Suchak                   I  Attempting to get chat info for: Id(id=-1002651869724)
2025-08-06 14:31:17.701  3445-3577  BotApi                  com.akslabs.Suchak                   I  getChat result - isSuccess: false
2025-08-06 14:31:17.702  3445-3577  BotApi                  com.akslabs.Suchak                   W  getChat failed - result: Unknown(exception=com.google.gson.JsonSyntaxException: java.lang.IllegalStateException: Expected a string but was BEGIN_OBJECT at line 1 column 944 path $.result.pinned_message)
2025-08-06 14:31:17.703  3445-3445  UidComponent            com.akslabs.Suchak                   I  Bot access result: false
2025-08-06 14:31:17.703  3445-3445  UidComponent            com.akslabs.Suchak                   W  Bot cannot access chat ID: -1002651869724
