package com.david.sqlitebancokotlin.DAO

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteAbortException
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.david.sqlitebancokotlin.OBJETOS.Cliente
import com.david.sqlitebancokotlin.OBJETOS.Usuarios

class DAO2 (context: Context) :
    SQLiteOpenHelper(context, "banco2",null, 1) {

    override fun onCreate(db: SQLiteDatabase) {
        val sql_banco2 = "CREATE TABLE banco2(clientes_id INTEGER PRIMARY KEY AUTOINCREMENT, clientes_nome TEXT UNIQUE NOT NULL,clientes_telefone TEXT NOT NULL );"

        db.execSQL(sql_banco2)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        val sql_banco2_onUpgrade = "DROP TABLE IF EXISTS banco2;"
        db?.execSQL(sql_banco2_onUpgrade)
        onCreate(db!!)
    }

    fun insereCliente(cliente : Cliente) : Boolean{

        val db = writableDatabase

        val dados_cliente = ContentValues()
        dados_cliente.put("clientes_nome",cliente.nome)
        dados_cliente.put("clientes_telefone",cliente.telefone)

        try {
            db.insertOrThrow("banco2",null,dados_cliente)
        } catch (e: SQLiteAbortException){
          db.close()
            return  false
        }

        db.close()
        return true
    }


    @SuppressLint("Range")
    fun buscaCliente(nome : String): Cliente{
        val db = readableDatabase
        val sql_busca_cliente = "SELECT * FROM banco2 WHERE clientes_nome = '$nome'"

        val cursor = db.rawQuery(sql_busca_cliente, null)
        val cliente = Cliente()

        while (cursor.moveToNext()) {
            cliente.nome = cursor.getString(cursor.getColumnIndex("clientes_nome"))
            cliente.telefone = cursor.getString(cursor.getColumnIndex("clientes_telefone"))
        }
        db.close()
        cursor.close()
        return cliente
    }

}