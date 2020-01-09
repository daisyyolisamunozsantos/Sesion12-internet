package com.example.aplicacion01;


import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.aplicacion01.adapters.ContactoAdaptador;
import com.example.aplicacion01.helpers.QueueUtils;
import com.example.aplicacion01.models.Contacto;

import java.util.ArrayList;
import java.util.Queue;

public class MainActivity extends AppCompatActivity {
    ListView contactosList;
    ContactoAdaptador contactoAdaptador;

    QueueUtils.QueueObject queue = null;
    ArrayList<Contacto> items;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        contactosList = findViewById(R.id.contactosList);

        queue = QueueUtils.getInstance(this.getApplicationContext());

        items = new ArrayList<>();
        Contacto.injectContactsFromCloud(queue, items, this);

        contactoAdaptador = new ContactoAdaptador(this,items, queue.getImageLoader());
        contactosList.setAdapter(contactoAdaptador);
        Contacto.sendRequestPOST(queue, this);
    }
    public void refreshList(){
        if ( contactoAdaptador!= null ) {
            contactoAdaptador.notifyDataSetChanged();
        }
    }
}
