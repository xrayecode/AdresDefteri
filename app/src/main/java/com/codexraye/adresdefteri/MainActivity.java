package com.codexraye.adresdefteri;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {

    EditText adisoyadi,telefonu,adresi;
    Button ekle,guncelle,getir,blv;
    ListView lvCustomList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        adisoyadi = (EditText) findViewById(R.id.edAdiSoyadi);
        telefonu  = (EditText) findViewById(R.id.edTelefonu);
        adresi    = (EditText) findViewById(R.id.edAdres);
        ekle      = (Button) findViewById(R.id.bEkle);
        guncelle  = (Button) findViewById(R.id.bGuncelle);
        getir     = (Button) findViewById(R.id.bGetir);
        blv       = (Button) findViewById(R.id.bLv);
        lvCustomList = (ListView) findViewById(R.id.lvListe);

        ekle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Rehber r = new Rehber();
                Rehber.defaulContext=MainActivity.this;
                r.setAdisoyadi(adisoyadi.getText().toString());
                r.setTelefon(telefonu.getText().toString());
                r.setAdresi(adresi.getText().toString());
                r.insert(r.insertContentValues());

            }

        });
        guncelle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Rehber r = new Rehber();
                Rehber.defaulContext=MainActivity.this;
                r.setAdisoyadi(adisoyadi.getText().toString());
                r.setTelefon(telefonu.getText().toString());
                r.setId("1");
                r.update(r.updateContentValues());
            }
        });
        getir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor c;
                Rehber r=new Rehber();
                Rehber.defaulContext=MainActivity.this;
                r.setId("1");
                c=r.readOne();
                adisoyadi.setText(c.getString(c.getColumnIndex("ADISOYAD")));
                adresi.setText(c.getString(c.getColumnIndex("ADRES")));
                telefonu.setText(c.getString(c.getColumnIndex("TELEFON")));

            }
        });

        blv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor c;
                Rehber r=new Rehber();
                Rehber.defaulContext=MainActivity.this;
                r.setId("1");
                c=r.readAll();
                showlist(c);
            }
        });
    }

    private void showlist(Cursor c1) {

        ArrayList<Rehber> contactList = new ArrayList<Rehber>();
        contactList.clear();
        if (c1 != null && c1.getCount() != 0) {
            if (c1.moveToFirst()) {
                do {
                    Rehber contactListItems = new Rehber();

                    contactListItems.setId(c1.getString(c1
                            .getColumnIndex("ID")));
                    contactListItems.setAdisoyadi(c1.getString(c1
                            .getColumnIndex("ADISOYAD")));
                    contactListItems.setTelefon(c1.getString(c1
                            .getColumnIndex("TELEFON")));
                    contactList.add(contactListItems);

                } while (c1.moveToNext());
            }
        }
        c1.close();

        ContactListAdapter contactListAdapter;
        contactListAdapter = new ContactListAdapter(MainActivity.this, contactList);
        lvCustomList.setAdapter(contactListAdapter);

    }



}
