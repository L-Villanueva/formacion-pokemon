package com.example.data.repositories

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.data.commons.BaseRepository
import com.example.data.commons.clean
import com.example.data.commons.clean2
import com.example.data.local.BankDatabase
import com.example.data.models.PokemonDTO
import com.example.data.models.Result
import com.example.data.remote.PokemonAPI
import com.example.data.remote.ResultHandler

class PokemonRepository(private val api: PokemonAPI, private val bankDB: BankDatabase, private val dataStoreRepository: DataStoreRepository): BaseRepository() {

    val mPokemon: LiveData<List<PokemonDTO>> by lazy {
        bankDB.transactionDao().load()
    }
    val mPokemonFavorite: LiveData<List<PokemonDTO>> by lazy {
        bankDB.transactionDao().loadFavorites()
    }

    //API
    suspend fun getPokemonList(): ResultHandler<String> {
        //Call to API and save in Room
        when (val result = safeApiCall(call = { api.getList() })) {
            is ResultHandler.Success -> {
                result.data.next?.let { dataStoreRepository.saveNextDataStore(it) }
                result.data.previous?.let { dataStoreRepository.savePreviousDataStore(it) }
                result.data.results?.let { sortedList ->
                    sortedList.forEach {
                        Log.d("urlPokemon", it.url.clean())
                        when (val resultPokemon = getPokemonAndSave(it)){
                            is ResultHandler.Success -> return@forEach
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
    suspend fun getPokemonListNext(url: String): ResultHandler<String> {
        //Call to API and save in Room
        when (val result = safeApiCall(call = { api.getListNext(url.clean2()) })) {
            is ResultHandler.Success -> {
                result.data.next?.let { dataStoreRepository.saveNextDataStore(it) }
                result.data.previous?.let { dataStoreRepository.savePreviousDataStore(it) }
                result.data.results?.let { sortedList ->
                    sortedList.forEach {
                        Log.d("urlPokemon", it.url.clean())
                        when (val resultPokemon = getPokemonAndSave(it)){
                            is ResultHandler.Success -> return@forEach
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
                    Log.d("Pokemon", pokemonResult.toString())
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