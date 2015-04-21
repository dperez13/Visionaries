package com.example.davidperez.theapp;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class ViewRouteActivity extends ActionBarActivity {

    private TextView nameField;
    private TextView startPointField;
    private TextView endPointField;
    private Button editRoute;
    private Intent data;
    private DummyRoute route;
    private int index;
    private DummyRouteCollection routeArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_route);

    PreferencesActivity.setOrientation(this);

    data = getIntent();
    index = Integer.parseInt(data.getStringExtra("indexString"));
    routeArray = (DummyRouteCollection) data.getSerializableExtra("toView");
    route = routeArray.routes.get(index);
    nameField = (TextView) findViewById(R.id.nameView);
    startPointField = (TextView) findViewById(R.id.startPointView);
    endPointField = (TextView) findViewById(R.id.endPointView);
    editRoute = (Button) findViewById(R.id.button_edit_route);

    nameField.setText(route.getName());
    startPointField.setText(route.getStartPoint());
    endPointField.setText(route.getEndPoint());


    editRoute.setOnClickListener(new View.OnClickListener() {
        public void onClick(View v) {
            Intent editItem = new Intent(ViewRouteActivity.this, EditRouteActivity.class);
            editItem.putExtra("toEdit", routeArray);
            editItem.putExtra("indexString", ""+index);
            startActivityForResult(editItem, 20);
        }
    });
}
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent data) {
        if (requestCode == 20) {
            if (resultCode == RESULT_OK) {
                boolean delete = data.getBooleanExtra("delete", false);
                if (!delete) {
//                    routeArray = (DummyRouteCollection) data.getSerializableExtra("toEdit");
                    routeArray = routeArray.readRoutes(getApplicationContext());
                    route = routeArray.routes.get(index);
                    nameField.setText(route.getName());
                    startPointField.setText(route.getStartPoint());
                    endPointField.setText(route.getEndPoint());
                    data.putExtra("delete", false);
                    data.putExtra("edited", true);
                    setResult(RESULT_OK, data);

                }
                else
                {
                    data.putExtra("edited", true);
                    data.putExtra("delete", true);
                    setResult(RESULT_OK,data);
                    finish();
                }

            }
        }
    }

    protected void onRestart()
    {
        super.onRestart();
        PreferencesActivity.setOrientation(this);
    }
    protected void onPause(Bundle savedInstanceState)
    {
        PreferencesActivity.setOrientation(this);
    }
    protected void onResume(Bundle savedInstanceState)
    {
        PreferencesActivity.setOrientation(this);
    }
    protected void onStart(Bundle savedInstanceState)
    {
        PreferencesActivity.setOrientation(this);
    }

   /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_view_route, menu);
        return true;
    }*/

 /*   @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/


}
