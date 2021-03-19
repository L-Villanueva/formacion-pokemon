package com.example.data.repositories

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.data.commons.BaseRepository
import com.example.data.commons.clean
import com.example.data.local.BankDatabase
import com.example.data.models.PokemonDTO
import com.example.data.remote.ITransactionAPI
import com.example.data.remote.ResultHandler

class TransactionRepository(private val api: ITransactionAPI, private val bankDB: BankDatabase): BaseRepository() {

    val mTransactions: LiveData<List<PokemonDTO>> by lazy {
        bankDB.transactionDao().load()
    }

    //API
    suspend fun getTransactionsAndSave(): ResultHandler<String> {
        //Call to API and save in Room
        when (val result = safeApiCall(call = { api.getList() })) {
            is ResultHandler.Success -> {
                //Sort the list
                result.data.let {

                    val sortedList = result.data.results
                    //Save data in Room
                    sortedList.map {
                        Log.d("aca", it.url.clean())
                        when (val resultPokemonDTO = safeApiCall (call = { api.getPokemon(it.url.clean()) } )) {
                            is ResultHandler.Success -> {
                                Log.d("pokemon", resultPokemonDTO.toString())
                                resultPokemonDTO.data.let { pokemon ->

                                    bankDB.transactionDao().save(pokemon)
                                }
                            }
                            is ResultHandler.GenericError -> return@map resultPokemonDTO
                            is ResultHandler.HttpError -> return@map resultPokemonDTO
                            is ResultHandler.NetworkError ->return@map resultPokemonDTO
                        }
                    }
                }
                //It is not necessary to return nothing, magic is done with liveData in Room
                return ResultHandler.Success("Successful update")
            }
            is ResultHandler.GenericError -> return result
            is ResultHandler.HttpError -> return result
            is ResultHandler.NetworkError -> return result

        }

    }

    suspend fun deleteTransactions(){
        bankDB.transactionDao().deleteAll()
    }
}