package ru.lionzxy.printbox.repository.print

import android.content.SharedPreferences
import com.google.gson.Gson
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import ru.lionzxy.printbox.data.api.PrintApi
import ru.lionzxy.printbox.data.model.PrintCartModel
import ru.lionzxy.printbox.data.model.PrintPlace
import ru.lionzxy.printbox.data.stores.IPrintCartStore

const val PREF_LASTPRINT = "lastprint"

class PrintRepository(retrofit: Retrofit,
                      private var gson: Gson,
                      private var preferences: SharedPreferences,
                      private val cartStore: IPrintCartStore) : IPrintRepository {
    private val printApi = retrofit.create(PrintApi::class.java)

    override fun getPrinters(): Observable<List<PrintPlace>> {
        return printApi.getPrinters().subscribeOn(Schedulers.io())
    }

    override fun getLastPrinter(): Single<PrintPlace> {
        return Single.fromCallable {
            val json = preferences.getString(PREF_LASTPRINT, "{}")
            return@fromCallable gson.fromJson(json, PrintPlace::class.java)
        }
    }

    override fun savePrinter(printPlace: PrintPlace): Completable {
        return Completable.fromCallable {
            val json = gson.toJson(printPlace)
            preferences.edit().putString(PREF_LASTPRINT, json).apply()
        }
    }

    override fun getObservableCart(): Observable<PrintCartModel> {
        return cartStore.getObservableCart()
    }

    override fun setCart(printCartModel: PrintCartModel) {
        cartStore.setCart(printCartModel)
    }
}