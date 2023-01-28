package com.example.filmoteca;



import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.SupportMapFragment;

public class Videoclubs extends Fragment {

    private static final int MY_PERMISSIONS_REQUEST_LOCATION = 1;
    private OnMapReadyCallback callback = new OnMapReadyCallback() {

        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */
        @Override
        public void onMapReady(GoogleMap googleMap) {


            /*if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {



            }*/
            while (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, MY_PERMISSIONS_REQUEST_LOCATION);

            }
            googleMap.setMyLocationEnabled(true);

            LatLng madrid = new LatLng(40.4168, -3.7038);
            googleMap.addMarker(new MarkerOptions().position(madrid).title("Madrid"));
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(madrid, 10));




            LatLng videoclubStar = new LatLng(40.41151000432687, -3.7312850215109274);
            googleMap.addMarker(new MarkerOptions().position(videoclubStar).title("Video Club Star"));

            LatLng ficcionesDeCine = new LatLng(40.41119443224569, -3.705804666011456);
            googleMap.addMarker(new MarkerOptions().position(ficcionesDeCine).title("Ficciones de Cine"));

            LatLng haaso = new LatLng(40.4219062490694, -3.6831712279623092);
            googleMap.addMarker(new MarkerOptions().position(haaso).title("Haaaso"));

            LatLng bola = new LatLng(40.4248236331364, -3.6738405799485814);
            googleMap.addMarker(new MarkerOptions().position(bola).title("Bola"));

            LatLng dvdStore = new LatLng(40.44796293006687, -3.569327179093743);
            googleMap.addMarker(new MarkerOptions().position(dvdStore).title("Dvd Store"));

            LatLng videoclubAlfil = new LatLng(40.454644259212074, -3.5880425355164256);
            googleMap.addMarker(new MarkerOptions().position(videoclubAlfil).title("Videoclub Alfil"));

            LatLng laVideoteca = new LatLng(40.49076501697467, -3.701744933004369);
            googleMap.addMarker(new MarkerOptions().position(laVideoteca).title("La Videoteca"));

            LatLng videoclubArfe = new LatLng(40.48216761125177, -3.712768075378966);
            googleMap.addMarker(new MarkerOptions().position(videoclubArfe).title("Videoclub Arfe"));

            LatLng videoclub2000 = new LatLng(40.2868145400284, -3.798565302568748);
            googleMap.addMarker(new MarkerOptions().position(videoclub2000).title("Videoclub 2000"));






        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_videoclubs, container, false);



    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.videoclubfragment);
        if (mapFragment != null) {
            mapFragment.getMapAsync(callback);
        }


    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_PERMISSIONS_REQUEST_LOCATION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

            } else {
                //permission denied
            }
        }
    }
}