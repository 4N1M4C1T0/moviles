package com.example.proyecto;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class RecicleView extends AppCompatActivity {
RecyclerView misPedidos;
RecyclerView.LayoutManager miLayoutManager;
RecyclerView.Adapter miAdaptador;
    FirebaseFirestore db;
    List<Pedido> resultado;
    public static final String user="names";
    TextView txtuser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recicle_view);

        txtuser = findViewById(R.id.TvRepartidor);
        String user = getIntent().getStringExtra("names");
        txtuser.setText(user);


        misPedidos = findViewById(R.id.RvPedidos);
        miLayoutManager = new LinearLayoutManager(this);
        misPedidos.setLayoutManager(miLayoutManager);


        resultado = new ArrayList<>();
        db = FirebaseFirestore.getInstance();
        db.collection("Pedido")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            for (QueryDocumentSnapshot documentos: task.getResult()){
                                String ubicacion = documentos.getData().get("ubicacion").toString();
                                String estado = documentos.getData().get("estado").toString();
                                String telefono = documentos.getData().get("telefono").toString();
                                String id = documentos.getData().get("id").toString();

                                resultado.add(new Pedido(ubicacion,estado,telefono,id));
                            }

                            miAdaptador = new MiAdaptador(resultado);
                            misPedidos.setAdapter(miAdaptador);
                        }
                        else{
                            Log.e("proyecto", "Error", task.getException());
                        }
                    }
                });
    }

    public void Return(View view) {
        finish();
    }
}