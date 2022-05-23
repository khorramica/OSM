package ir.finto.osm;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Context;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.webkit.PermissionRequest;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import org.osmdroid.api.IMapController;
import org.osmdroid.config.Configuration;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private MapView map;
    private IMapController mapController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Context ctx = getApplicationContext();
        Configuration.getInstance().load(ctx, PreferenceManager.getDefaultSharedPreferences(ctx));

        setContentView(R.layout.activity_main);

        Dexter.withContext(getApplicationContext()).withPermissions(Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport multiplePermissionsReport) {

                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<com.karumi.dexter.listener.PermissionRequest> list, PermissionToken permissionToken) {

                    }


                }).check();

        map = findViewById(R.id.mapView);

        mapController = map.getController();

        mapController.setZoom(7);

        map.setTilesScaledToDpi(true);
        map.setBuiltInZoomControls(true);
        map.setMultiTouchControls(true);
        GeoPoint point = new GeoPoint(35.7448416,51.3775099,17);
        Marker marker = new Marker(map);
        marker.setPosition(point);
        marker.setTitle("برج میلاد");
        marker.setSubDescription("توضیحات");
        map.getOverlays().add(marker);
        mapController.setCenter(point);


    }
}