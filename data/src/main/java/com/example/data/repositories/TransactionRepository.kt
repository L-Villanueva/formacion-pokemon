package com.example.data.repositories

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.data.commons.BaseRepository
import com.example.data.commons.clean
import com.example.data.local.BankDatabase
import com.example.data.models.PokemonDTO
import com.example.data.models.Result
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

                result.data.results?.let { sortedList ->
                    sortedList.map {
                        Log.d("aca", it.url.clean())
                        when (val resultPokemon = getPokemonAndSave(it)){
                            is ResultHandler.Success -> return@map
                            else -> return resultPokemon
                        }
                    }
                }
                //It is not necessary to return nothing, magic is done with liveData in Room
                return ResultHandler.Success("Successful update")
            }
            is ResultHandler.GenericError -> return result
            is ResultHandler.HttpError ->  return result
            is ResultHandler.NetworkError ->  return result
        }
    }
    private suspend fun getPokemonAndSave(pokemon: Result): ResultHandler<String>{

        return when (val resultPokemonDTO = safeApiCall (call = { api.getPokemon(pokemon.url.clean()) } )) {
            is ResultHandler.Success -> {
                resultPokemonDTO.data.let { pokemonResult ->
                    bankDB.transactionDao().save(pokemonResult)
                }
                ResultHandler.Success("Successful update")
            }
            is ResultHandler.GenericError -> resultPokemonDTO
            is ResultHandler.HttpError -> resultPokemonDTO
            is ResultHandler.NetworkError -> resultPokemonDTO
        }
    }

    suspend fun deleteTransactions(){
        bankDB.transactionDao().deleteAll()
    }
}