package com.example.proyecto_comedor.DataMain

import android.util.Log
import com.example.proyecto_comedor.model.Product
import com.example.proyecto_comedor.model.ProductListResponse
import com.example.proyecto_comedor.network.ProductApiService
import retrofit2.Response

interface ProductRepository {
    suspend fun fetchPlatillos(): List<Product>
    suspend fun fetchSandwiches(): List<Product>
    suspend fun fetchPlatAdvice(): List<Product>
    suspend fun fetchPostres(): List<Product>
    suspend fun fetchBebidas(): List<Product>
    suspend fun fetchExtras(): List<Product>
    suspend fun fetchOtros(): List<Product>
}

class NetworkProductRepository(
    private val api: ProductApiService
) : ProductRepository {
    private suspend fun <T: ProductListResponse> safeFetch(call: suspend ()->Response<T>, tag: String): List<Product> {
        val resp = call()
        return if (resp.isSuccessful) {
            resp.body()?.items ?: emptyList()
        } else {
            Log.e("RepoProduct", "$tag failed: code=${resp.code()}")
            emptyList()
        }
    }

    override suspend fun fetchPlatillos()   = safeFetch({ api.getPlatillos() },   "Platillos")
    override suspend fun fetchSandwiches() = safeFetch({ api.getSandwiches() }, "Sandwiches")
    override suspend fun fetchPlatAdvice()  = safeFetch({ api.getSugerenciaChef() }, "SugerenciaChef")
    override suspend fun fetchPostres()     = safeFetch({ api.getPostres() },    "Postres")
    override suspend fun fetchBebidas()     = safeFetch({ api.getBebidas() },    "Bebidas")
    override suspend fun fetchExtras()      = safeFetch({ api.getExtras() },     "Extras")
    override suspend fun fetchOtros()       = safeFetch({ api.getOtros() },      "Otros")
}
