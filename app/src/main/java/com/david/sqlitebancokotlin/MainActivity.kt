package com.david.sqlitebancokotlin

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import com.david.sqlitebancokotlin.DAO.DAO1
import com.david.sqlitebancokotlin.DAO.DAO2
import com.david.sqlitebancokotlin.OBJETOS.Cliente
import com.david.sqlitebancokotlin.OBJETOS.Usuarios
import com.david.sqlitebancokotlin.databinding.ActivityMainBinding
import java.sql.SQLException

class MainActivity : AppCompatActivity() {
    var binding: ActivityMainBinding? = null
    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding!!.root)


       /* val usuario = Usuarios()

        usuario.usuarios_nome = "sofia"
        usuario.usuarios_senha = "12345"

        val dao1 = DAO1(this)
       val criaUsuario : String = dao1.insereUsuario(usuario).toString()

        Log.d("Criação de usuário: ",criaUsuario)

*/


        /*
        val cliente = Cliente()

        cliente.nome= "david"
         cliente.telefone= "51 998855444"

        val dao2 = DAO2(this)
        val criaCliente : String = dao2.insereCliente(cliente).toString()

        Log.d("Criação de cliente: ",criaCliente)
*/

            val dao1 = DAO1(this)
        var usuario : Usuarios = dao1.buscaUsuario("sofia")

        usuario.usuarios_nome?.let { Log.d("usuario encontrado", it) }


        val dao2 = DAO2(this)
        var cliente : Cliente = dao2.buscaCliente("david")

        cliente.nome?.let { Log.d("cliente encontrado", it) }
    }
}