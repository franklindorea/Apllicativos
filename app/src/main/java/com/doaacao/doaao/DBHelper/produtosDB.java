package com.doaacao.doaao.DBHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.doaacao.doaao.model.produtos;

import java.util.ArrayList;

public class produtosDB extends SQLiteOpenHelper {

    private static final String DATABASE ="dbprodutos";
    private static final int VERSION = 1;

    public produtosDB (Context context){
        super(context, DATABASE, null, VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String produto = "CREATE TABLE produtos (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, nomeproduto TEXT NOT NULL, funcionalidade TEXT NOT NULL, defeito TEXT NOT NULL, disponibilidade TEXT NOT NULL);";
        db.execSQL(produto);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String produto = "DROP TABLE IF EXISTS produtos";
        db.execSQL(produto);
    }
    // aqui salva
    public void salvarproduto (produtos produto){
        ContentValues values = new ContentValues();

        values.put("nomeproduto",produto.getNomeproduto());
        values.put("funcionalidade",produto.getFuncionalidade());
        values.put("defeito",produto.getDefeito());
        values.put("disponibilidade",produto.getDisponibilidade());

        getWritableDatabase().insert("produtos", null, values);

    }
    //lista - mostrar
    public ArrayList<produtos> getLista(){
        String [] columns ={"id","nomeproduto","funcionalidade","defeito","disponibilidade"};
        Cursor cursor = getWritableDatabase().query("produtos",columns,null,null,null,null,null,null);
        ArrayList<produtos> produtos = new ArrayList<produtos>();

        while (cursor.moveToLast()){
            produtos produto = new produtos();
            produto.setId(cursor.getLong(0));
            produto.setNomeproduto(cursor.getString(1));
            produto.setFuncionalidade(cursor.getString(2));
            produto.setDefeito(cursor.getString(3));
            produto.setDisponibilidade(cursor.getString(4));

            produtos.add(produto);

        }
    return produtos;
    }


}
