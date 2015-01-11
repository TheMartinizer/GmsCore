package org.microg.gms.location;

import android.app.PendingIntent;
import android.content.Context;
import android.location.Location;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofencingRequest;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationStatus;
import com.google.android.gms.location.internal.IGeofencerCallbacks;
import com.google.android.gms.location.internal.IGoogleLocationManagerService;
import com.google.android.gms.location.internal.ILocationListener;
import com.google.android.gms.location.internal.LocationRequestInternal;
import com.google.android.gms.location.places.*;
import com.google.android.gms.location.places.internal.IPlacesCallbacks;
import com.google.android.gms.location.places.internal.PlacesParams;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;

import java.util.List;

public class GoogleLocationManagerServiceImpl extends IGoogleLocationManagerService.Stub {
    private static final String TAG = "GmsLMSImpl";

    private final Context context;
    private GoogleLocationManager locationManager;

    public GoogleLocationManagerServiceImpl(Context context) {
        this.context = context;
    }

    private GoogleLocationManager getLocationManager() {
        if (locationManager == null)
            locationManager = new GoogleLocationManager(context);
        return locationManager;
    }

    @Override
    public void addGeofencesList(List<Geofence> geofences, PendingIntent pendingIntent,
            IGeofencerCallbacks callbacks, String packageName) throws RemoteException {
        Log.d(TAG, "addGeofencesList: " + geofences);
    }

    @Override
    public void removeGeofencesByIntent(PendingIntent pendingIntent, IGeofencerCallbacks callbacks,
            String packageName) throws RemoteException {
        Log.d(TAG, "removeGeofencesByIntent: " + pendingIntent);
    }

    @Override
    public void removeGeofencesById(String[] geofenceRequestIds, IGeofencerCallbacks callbacks,
            String packageName) throws RemoteException {
        Log.d(TAG, "removeGeofencesById: " + geofenceRequestIds);
    }

    @Override
    public void iglms4(IGeofencerCallbacks callbacks, String packageName) throws RemoteException {
        Log.d(TAG, "iglms4: " + packageName);
    }

    @Override
    public void requestActivityUpdates(long detectionIntervalMillis, boolean alwaysTrue,
            PendingIntent callbackIntent) throws RemoteException {
        Log.d(TAG, "requestActivityUpdates: " + callbackIntent);
    }

    @Override
    public void removeActivityUpdates(PendingIntent callbackIntent) throws RemoteException {
        Log.d(TAG, "removeActivityUpdates: " + callbackIntent);
    }

    @Override
    public Location getLastLocation() throws RemoteException {
        Log.d(TAG, "getLastLocation");
        return getLocationManager().getLastLocation(null);
    }

    @Override
    public void requestLocationUpdatesWithListener(LocationRequest request,
            final ILocationListener listener) throws RemoteException {
        Log.d(TAG, "requestLocationUpdatesWithListener: " + request);
        getLocationManager().requestLocationUpdates(request, listener, null);
    }

    @Override
    public void requestLocationUpdatesWithIntent(LocationRequest request,
            PendingIntent callbackIntent) throws RemoteException {
        Log.d(TAG, "requestLocationUpdatesWithIntent: " + request);
        getLocationManager().requestLocationUpdates(request, callbackIntent);
    }

    @Override
    public void removeLocationUpdatesWithListener(ILocationListener listener)
            throws RemoteException {
        Log.d(TAG, "removeLocationUpdatesWithListener: " + listener);
        getLocationManager().removeLocationUpdates(listener);
    }

    @Override
    public void removeLocationUpdatesWithIntent(PendingIntent callbackIntent)
            throws RemoteException {
        Log.d(TAG, "removeLocationUpdatesWithIntent: " + callbackIntent);
        getLocationManager().removeLocationUpdates(callbackIntent);
    }

    @Override
    public void setMockMode(boolean mockMode) throws RemoteException {
        Log.d(TAG, "setMockMode: " + mockMode);
        getLocationManager().setMockMode(mockMode);
    }

    @Override
    public void setMockLocation(Location mockLocation) throws RemoteException {
        Log.d(TAG, "setMockLocation: " + mockLocation);
        getLocationManager().setMockLocation(mockLocation);
    }

    @Override
    public void iglms14(LatLngBounds var1, int var2, PlaceFilter var3, PlacesParams var4,
            IPlacesCallbacks var5) throws RemoteException {
        Log.d(TAG, "iglms14: " + var1);
    }

    @Override
    public void iglms15(String var1, PlacesParams var2, IPlacesCallbacks var3)
            throws RemoteException {
        Log.d(TAG, "iglms15: " + var1);
    }

    @Override
    public void iglms16(LatLng var1, PlaceFilter var2, PlacesParams var3, IPlacesCallbacks var4)
            throws RemoteException {
        Log.d(TAG, "iglms16: " + var1);
    }

    @Override
    public void iglms17(PlaceFilter var1, PlacesParams var2, IPlacesCallbacks var3)
            throws RemoteException {
        Log.d(TAG, "iglms17: " + var1);
    }

    @Override
    public void iglms18(PlaceRequest var1, PlacesParams var2, PendingIntent var3)
            throws RemoteException {
        Log.d(TAG, "iglms18: " + var1);
    }

    @Override
    public void iglms19(PlacesParams var1, PendingIntent var2) throws RemoteException {
        Log.d(TAG, "iglms19: " + var1);
    }

    @Override
    public void requestLocationUpdates(LocationRequest request, ILocationListener listener,
            String packageName) throws RemoteException {
        Log.d(TAG, "requestLocationUpdates: " + request);
        getLocationManager().requestLocationUpdates(request, listener, packageName);
    }

    @Override
    public Location getLastLocationWithPackage(String packageName) throws RemoteException {
        Log.d(TAG, "getLastLocationWithPackage: " + packageName);
        return getLocationManager().getLastLocation(packageName);
    }

    @Override
    public void iglms25(PlaceReport var1, PlacesParams var2) throws RemoteException {
        Log.d(TAG, "iglms25: " + var1);
    }

    @Override
    public void iglms26(Location var1, int var2) throws RemoteException {
        Log.d(TAG, "iglms26: " + var1);
    }

    @Override
    public LocationStatus iglms34(String var1) throws RemoteException {
        Log.d(TAG, "iglms34: " + var1);
        return null;
    }

    @Override
    public void iglms42(String var1, PlacesParams var2, IPlacesCallbacks var3)
            throws RemoteException {
        Log.d(TAG, "iglms42: " + var1);
    }

    @Override
    public void iglms46(UserAddedPlace var1, PlacesParams var2, IPlacesCallbacks var3)
            throws RemoteException {
        Log.d(TAG, "iglms46: " + var1);
    }

    @Override
    public void iglms47(LatLngBounds var1, int var2, String var3, PlaceFilter var4,
            PlacesParams var5, IPlacesCallbacks var6) throws RemoteException {
        Log.d(TAG, "iglms47: " + var1);
    }

    @Override
    public void iglms48(NearbyAlertRequest var1, PlacesParams var2, PendingIntent var3)
            throws RemoteException {
        Log.d(TAG, "iglms48: " + var1);
    }

    @Override
    public void iglms49(PlacesParams var1, PendingIntent var2) throws RemoteException {
        Log.d(TAG, "iglms49: " + var1);
    }

    @Override
    public void iglms50(UserDataType var1, LatLngBounds var2, List var3, PlacesParams var4,
            IPlacesCallbacks var5) throws RemoteException {
        Log.d(TAG, "iglms50: " + var1);
    }

    @Override
    public IBinder iglms51() throws RemoteException {
        Log.d(TAG, "iglms51");
        return null;
    }

    @Override
    public void requestLocationUpdatesInternalWithListener(LocationRequestInternal request,
            ILocationListener listener) throws RemoteException {
        Log.d(TAG, "requestLocationUpdatesInternalWithListener: " + request);
    }

    @Override
    public void requestLocationUpdatesInternalWithIntent(LocationRequestInternal request,
            PendingIntent callbackIntent) throws RemoteException {
        Log.d(TAG, "requestLocationUpdatesInternalWithIntent: " + request);
    }

    @Override
    public IBinder iglms54() throws RemoteException {
        Log.d(TAG, "iglms54");
        return null;
    }

    @Override
    public void iglms55(String var1, LatLngBounds var2, AutocompleteFilter var3, PlacesParams var4,
            IPlacesCallbacks var5) throws RemoteException {
        Log.d(TAG, "iglms54: " + var1);
    }

    @Override
    public void addGeofences(GeofencingRequest geofencingRequest, PendingIntent pendingIntent,
            IGeofencerCallbacks callbacks) throws RemoteException {
        Log.d(TAG, "addGeofences: " + geofencingRequest);
    }

    @Override
    public void iglms58(List var1, PlacesParams var2, IPlacesCallbacks var3)
            throws RemoteException {
        Log.d(TAG, "iglms54: " + var1);
    }
}