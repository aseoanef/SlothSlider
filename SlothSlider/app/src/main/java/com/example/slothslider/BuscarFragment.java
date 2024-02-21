package com.example.slothslider;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;

public class BuscarFragment extends Fragment implements SearchView.OnQueryTextListener {
    private static final String TEXT_ID = "text_id";
    private Intent intent;

    public static BuscarFragment newInstance(@StringRes int textId) {
        BuscarFragment frag = new BuscarFragment();

        Bundle args = new Bundle();
        args.putInt(TEXT_ID, textId);
        frag.setArguments(args);

        return frag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable
    Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.addmovie_review_fragment, container, false);
        return layout;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }
}
//    public class ListwithProducts extends AppCompatActivity implements SearchView.OnQueryTextListener {
//
//        private android.widget.SearchView buscador;
//        private CharSequence busqueda;
//        private Integer list_id;
//        private RequestQueue queue;
//        static String host ="http://10.0.2.2:8000";
//        private Context context;
//        private String nombreLista;
//        private ScrollView scrollView;
//        private ListViewAdapter adapter;
//        private TextView textView;
//        private String nombre_lista;
//        private ListView list;
//        private ArrayList<Products> productsList;
//        private ArrayList<Products> arrayList;
//
//        @Override
//        protected void onCreate(@Nullable Bundle savedInstanceState) {
//            super.onCreate(savedInstanceState);
//            setContentView(R.layout.list_with_products);
//            context= this;
//            this.queue = Volley.newRequestQueue(this);
//            arrayList = new ArrayList<>();
//            adapter = new ListViewAdapter(this, arrayList);
//            list = (ListView) findViewById(R.id.listview);
//
//            Intent intent = getIntent();
//            Bundle bundle = intent.getExtras();
//
//
//
//            if (bundle!=null){
//                list_id = (Integer) bundle.getInt("ID-LIST");
//            }
//            getSpecificList(list_id);
//
//
//
//            getAllProducts();
//            buscador = (SearchView) findViewById(R.id.searchView);
//            buscador.setOnQueryTextListener(this);
//
//        }
//        public void getSpecificList(int list_id) throws NullPointerException {
//            SharedPreferences preferences = context.getSharedPreferences("SESSIONS_APP_PREFS", MODE_PRIVATE);
//            String username = preferences.getString("VALID_USERNAME", null);
//
//            JsonObjectRequestAuthenticated request = new JsonObjectRequestAuthenticated(
//                    Request.Method.GET,
//                    host + "/list/"+list_id,
//                    null,
//                    new Response.Listener<JSONObject>() {
//                        @Override
//                        public void onResponse(JSONObject response) {
//                            Toast.makeText(context, "Lista recibida", Toast.LENGTH_LONG).show();
//                            try {
//                                nombre_lista = response.getString("name");
//                            }catch (JSONException e){}
//                            textView=findViewById(R.id.nombreLista);
//                            textView.setText(nombre_lista);
//
//
//                        }
//                    }, new Response.ErrorListener() {
//                @Override
//                public void onErrorResponse(VolleyError error) {
//                    Toast.makeText(context, "Problema recibiendo la lista", Toast.LENGTH_LONG).show();
//                }
//            }, context
//            );
//
//            queue.add(request);
//        }
//
//
//        public void getAllProducts() {
//            SharedPreferences preferences = context.getSharedPreferences("SESSIONS_APP_PREFS", MODE_PRIVATE);
//            String username = preferences.getString("VALID_USERNAME", null);
//
//
//            JsonArrayRequestAuthenticated request = new JsonArrayRequestAuthenticated(
//                    Request.Method.GET,
//                    host + "/product",
//                    null,
//                    new Response.Listener<JSONArray>() {
//                        @Override
//                        public void onResponse(JSONArray response) {
//                            Toast.makeText(context, "Productos recibidos", Toast.LENGTH_LONG).show();
//                            for (int i = 0; i < response.length(); i++) {
//                                try {
//                                    JSONObject productJson = response.getJSONObject(i);
//                                    Products product = new Products(
//                                            productJson.getInt("id"),
//                                            productJson.getString("name"),
//                                            productJson.getInt("price")
//                                    );
//                                    System.out.println("producto:"+ productJson.getString("name"));
//                                    arrayList.add(product);
//
//
//                                } catch (JSONException e) {
//                                    throw new RuntimeException();
//                                }
//
//
//                                adapter = new ListViewAdapter(context, arrayList);
//                                // Binds the Adapter to the ListView
//                                list.setAdapter(adapter);
//
//                            }
//                            System.out.println("lista-->"+arrayList);
//                        }
//                    }, new Response.ErrorListener() {
//                @Override
//                public void onErrorResponse(VolleyError error) {
//                    Toast.makeText(context, "Problema recibiendo los productos", Toast.LENGTH_LONG).show();
//                }
//            }, context
//            );
//
//            queue.add(request);
//
//        }
//
//
//
//        @Override
//        public boolean onQueryTextSubmit(String query) {
//            return false;
//        }
//
//
//        @Override
//        public boolean onQueryTextChange(String newText) {
//            String text = newText;
//            adapter.filter(text);
//            return false;
//        }
//
//        public List<JSONObject> JSONArraytoArray(JSONArray array) {
//            List<JSONObject> list = new ArrayList<>();
//            for (int i = 0; i < array.length(); i++) {
//                try {
//                    list.add(array.getJSONObject(i));
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//            return list;
//        }
//
//
//    }
//
//}
