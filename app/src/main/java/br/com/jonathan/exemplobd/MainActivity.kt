package br.com.jonathan.exemplobd

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_main.*
import java.security.KeyStore

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        Realm.init(this)


        realmWrite()
        realRead()
        realmWhereAvancado()



    }

    private fun realmWrite() {

        val iniciar = Usuario()


        iniciar.id = 1
        iniciar.nome = "Jonathan"
        iniciar.email = null


        val miguel= Usuario()

        miguel.id = 2
        miguel.nome = null
        miguel.email = "miguel@miguel.com"


        val augusto= Usuario()

        augusto.id = 3
        augusto.nome = "AugustoCeta"
        augusto.email = "augutos@aug.com"


        val realm = Realm.getDefaultInstance()


        realm.use {
            it.beginTransaction()
            it.copyToRealm(iniciar)
            it.copyToRealm(miguel)
            it.copyToRealm(augusto)
            it.commitTransaction()

        }

        /*
        try {

            realm.beginTransaction()
            realm.copyToRealm(iniciar)
            realm.commitTransaction()
        }catch (e : Exception){
            realm.cancelTransaction()
        }finally {
            realm.close()
        }
        */




    }


    private fun realRead(){

        val realm = Realm.getDefaultInstance()

        val todosUsuarios = realm.where(Usuario::class.java).findAll()


        todosUsuarios.forEach{usuario ->

            //Com muitos usuarios
           /* usuario?.let {

                usuarios.text = it.nome ?: ""

            }
            */

            usuarios.text = usuario?.nome ?: ""

        }


    }


    private fun realmWhereAvancado(){

        val realm = Realm.getDefaultInstance()

        val results = realm.where(Usuario::class.java).isNull("nome").findAll()


        Log.d("tag", "${results.size}")


    }

}
