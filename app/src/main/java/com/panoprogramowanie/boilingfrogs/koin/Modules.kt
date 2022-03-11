package com.panoprogramowanie.boilingfrogs.koin

import android.content.Context
import com.google.gson.Gson
import com.panoprogramowanie.boilingfrogs.BuildConfig
import com.panoprogramowanie.boilingfrogs.R
import com.panoprogramowanie.boilingfrogs.api.ScheduleService
import com.panoprogramowanie.boilingfrogs.suppliers.NavigationSupplier
import com.panoprogramowanie.boilingfrogs.suppliers.NotificationSupplier
import com.panoprogramowanie.boilingfrogs.suppliers.ScheduleSupplier
import com.panoprogramowanie.boilingfrogs.suppliers.UpdateSupplier
import com.panoprogramowanie.boilingfrogs.suppliers.implementation.GreenDaoScheduleSupplier
import com.panoprogramowanie.boilingfrogs.suppliers.implementation.NavigationSupplierImpl
import com.panoprogramowanie.boilingfrogs.suppliers.implementation.NotificationSupplierImpl
import com.panoprogramowanie.boilingfrogs.ui.myschedule.MySchedulePresenter
import com.panoprogramowanie.boilingfrogs.ui.schedule.SchedulePresenter
import com.panoprogramowanie.boilingfrogs.ui.speaker.SpeakerPresenter
import com.panoprogramowanie.boilingfrogs.ui.speakers.SpeakersListPresenter
import com.panoprogramowanie.boilingfrogs.ui.speech.SpeechPresenter
import com.panoprogramowanie.boilingfrogs.ui.speechslot.SpeechSlotPresenter
import com.panoprogramowanie.boilingfrogs.ui.splash.SplashPresenter
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object KoinModule {
    lateinit var koinApplication: KoinApplication
        private set

    @JvmStatic
    fun getNavigationSupplier(): NavigationSupplier = get()

    @JvmStatic
    fun getSchedulePresenter(): SchedulePresenter = get()

    @JvmStatic
    fun getSpeakerPresenter(): SpeakerPresenter = get()

    @JvmStatic
    fun getSpeakersListPresenter(): SpeakersListPresenter = get()

    @JvmStatic
    fun getSpeechPresenter(): SpeechPresenter = get()

    @JvmStatic
    fun getSpeechSlotPresenter(): SpeechSlotPresenter = get()

    @JvmStatic
    fun getSplashPresenter(): SplashPresenter = get()

    @JvmStatic
    fun getMySchedulePresenter(): MySchedulePresenter = get()

    @JvmStatic
    inline fun <reified T : Any> get(): T = koinApplication.koin.get()

    @JvmStatic
    fun initKoin(context: Context) {
        koinApplication = startKoin {
            modules(mainModule)
            androidContext(context)
        }
    }

    private val mainModule = module {
        single { UpdateSupplier(get(), get()) }
        single<ScheduleSupplier> { GreenDaoScheduleSupplier(get()) }
        single<NavigationSupplier> { NavigationSupplierImpl() }
        single<NotificationSupplier> { NotificationSupplierImpl() }
        single { provideScheduleService(get()) }

        factory { MySchedulePresenter(get(), get()) }
        factory { SchedulePresenter(get()) }
        factory { SpeakerPresenter(get()) }
        factory { SpeakersListPresenter(get(), get()) }
        factory { SpeechPresenter(get(), get()) }
        factory { SpeechSlotPresenter(get(), get()) }
        factory { SplashPresenter(get(), get()) }

    }

    private fun provideScheduleService(applicationContext: Context): ScheduleService {
        val interceptor = HttpLoggingInterceptor()
        if (BuildConfig.DEBUG) {
            interceptor.level = HttpLoggingInterceptor.Level.HEADERS
        }
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(applicationContext.getString(R.string.schedule_server_url))
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .client(client)
            .build()

        return retrofit.create(ScheduleService::class.java)
    }
}
