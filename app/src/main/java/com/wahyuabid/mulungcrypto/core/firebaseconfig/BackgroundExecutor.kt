package com.wahyuabid.mulungcrypto.core.firebaseconfig

import java.util.concurrent.*

/**
 * Created by Wahyu Abid A on 29/12/21
 * Email : wahyu.abied@gmail.com
 * Github: https://github.com/wahyuabied/
 */
class BackgroundExecutor: Executor {
    private val threadPoolExecutor = ThreadPoolExecutor(
        2, 4, 10, TimeUnit.SECONDS,
        LinkedBlockingQueue<Runnable>(), JobThreadFactory()
    )

    override fun execute(runnable: Runnable) {
        this.threadPoolExecutor.execute(runnable)
    }

    private class JobThreadFactory: ThreadFactory {
        var counter = 0

        override fun newThread(runnable: Runnable): Thread {
            return Thread(runnable, "android_config_" + counter++)
        }
    }
}