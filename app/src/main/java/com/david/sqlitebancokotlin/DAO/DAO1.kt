package com.david.sqlitebancokotlin.DAO

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteAbortException
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.os.Build
import androidx.annotation.RequiresApi
import com.david.sqlitebancokotlin.OBJETOS.Usuarios

@RequiresApi(Build.VERSION_CODES.P)
class DAO1 (context: Context) :
    SQLiteOpenHelper(context, "banco1",null, 1) {

    override fun onCreate(db: SQLiteDatabase) {
        val sql_banco1 = "CREATE TABLE banco1(usuarios_id INTEGER PRIMARY KEY AUTOINCREMENT, usuarios_nome TEXT UNIQUE NOT NULL,usuarios_senha TEXT NOT NULL );"

        db.execSQL(sql_banco1)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        val sql_banco1_onUpgrade = "DROP TABLE IF EXISTS banco1;"
        db?.execSQL(sql_banco1_onUpgrade)
      onCreate(db!!)
    }

    fun insereUsuario(usuario : Usuarios) : Boolean{

        val db = writableDatabase

        val dados_usuario = ContentValues()
        dados_usuario.put("usuarios_nome",usuario.usuarios_nome)
        dados_usuario.put("usuarios_senha",usuario.usuarios_senha)

        try {
            db.insertOrThrow("banco1",null,dados_usuario)
        } catch (e: SQLiteAbortException){
           db.close()
            return  false
        }

        db.close()
        return true
    }
@SuppressLint("Range")
fun buscaUsuario(nome : String): Usuarios {
    val db = readableDatabase
    val sql_busca_usuario = "SELECT * FROM banco1 WHERE usuarios_nome = '$nome'"

    val cursor = db.rawQuery(sql_busca_usuario, null)
    val usuario = Usuarios()

    while (cursor.moveToNext()) {
        usuario.usuarios_nome = cursor.getString(cursor.getColumnIndex("usuarios_nome"))
        usuario.usuarios_senha = cursor.getString(cursor.getColumnIndex("usuarios_senha"))
    }
    db.close()
    cursor.close()
    return usuario
}
}