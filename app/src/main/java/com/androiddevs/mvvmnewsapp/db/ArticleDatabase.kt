package com.androiddevs.mvvmnewsapp.db

import android.content.Context
import androidx.room.*
import com.androiddevs.mvvmnewsapp.models.Article


///////////////////////////////////////////////////////////////////////////
// klase za room moraju uvek da budu abstraktne
///////////////////////////////////////////////////////////////////////////

@Database(
    entities = [Article::class], //u ovom slucaju samo 1 Articles mora preko arraya
    version = 1 // verzija databaze ako zelimo da promenimo
)
@TypeConverters(Convertors::class)
abstract class ArticleDatabase : RoomDatabase() {

    abstract fun getArticleDao() : ArticleDao
    //Abstrakna funkcija ne moramo da je implementujemo
    //implementacija toga ce se desiti beghind the scenes Room ce to da uradi

    companion object{
        @Volatile //druge threads mogu da vide cim ova thread promeni instance
        private var instance : ArticleDatabase? = null
        private val LOCK = Any() // ovo koristimo da sinhronizujemo instance znaci da bude samo
        //instance ovog db


        //ova funkcija ce uvek biti pozvana kada napravimo instance db-a
        operator fun invoke(context: Context) = instance ?: synchronized(LOCK){
            //sve sto se desi u ovom bloku ne moze da bude pristupljen drugim threadovima u isto vreme

            instance ?: createDatabase(context).also { instance = it }

        }


        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                ArticleDatabase::class.java,
                "article_db.db"
            ).fallbackToDestructiveMigration().build()

    }
}