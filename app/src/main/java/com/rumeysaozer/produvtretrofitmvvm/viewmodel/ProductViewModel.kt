package com.rumeysaozer.produvtretrofitmvvm.viewmodel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rumeysaozer.produvtretrofitmvvm.mod.ProductItem
import com.rumeysaozer.produvtretrofitmvvm.service.ProductAPIService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class ProductViewModel : ViewModel() {
    private val productAPIService = ProductAPIService()
    private val disposable = CompositeDisposable()
    val products = MutableLiveData<List<ProductItem>>()
    val error = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()
    fun refreshData(){
   getDataFromAPI()
    }
    fun getDataFromAPI(){
        loading.value = true
        disposable.add(
            productAPIService.getData()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object: DisposableSingleObserver<List<ProductItem>>(){
                    override fun onSuccess(t: List<ProductItem>) {
                   products.value = t
                    loading.value = false
                        error.value = false
                    }

                    override fun onError(e: Throwable) {
                       e.printStackTrace()
                        loading.value = false
                        error.value = true
                    }

                })
        )

    }
}