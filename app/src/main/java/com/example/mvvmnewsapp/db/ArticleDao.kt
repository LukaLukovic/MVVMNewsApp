package com.example.mvvmnewsapp.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.mvvmnewsapp.models.Article

///////////////////////////////////////////////////////////////////////////
// Data access object nacini preko kojeg pristupamo databasu preko rooma
// onConflict - ako objekat koji je vec u db zelimo da ubacimo ovo odgovara sta treba raditi .replace
// ce ih samo zameniti, u ovom slucaju mislim da moze i .ignore ili abort (ne znam jos )
///////////////////////////////////////////////////////////////////////////
@Dao
interface ArticleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(article: Article): Long
    //upsert= updates inserts
    // :Long id koji je insertovan

    @Query("SELECT * FROM articles")
    fun getAllArticles(): LiveData<List<Article>>
    //returnovace liveData object sto ne moze sa suspend funkcijama LiveData
    // (fragmenti mogu da se "subscribeuju" na livedata objekte koja salje update svim svojim gledaocima
    // recyclerView u ovom slucaju) ovo je jako korisno u aplikacijama sa promenom orijentacije
    // jer activity se unisti pa ponovo napravi kada se rotira device

    @Delete
    suspend fun deleteArticles(article: Article)

}