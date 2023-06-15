package kr.ac.mokwon.parkingnetwork;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.naver.maps.geometry.LatLng;
import com.naver.maps.map.CameraAnimation;
import com.naver.maps.map.CameraUpdate;
import com.naver.maps.map.MapFragment;
import com.naver.maps.map.NaverMap;
import com.naver.maps.map.OnMapReadyCallback;
import com.naver.maps.map.overlay.InfoWindow;
import com.naver.maps.map.overlay.Marker;
import com.naver.maps.map.overlay.Overlay;
import com.naver.maps.map.overlay.OverlayImage;

public class MainMapFragment extends Fragment implements OnMapReadyCallback
{

    MainActivity main;

    // 네이버 맵 관련
    private NaverMap naverMap;

    public MainMapFragment()
    {
        super();
    }

    @Override
    public void onAttach(@NonNull Context context)
    {
        super.onAttach(context);
        main = (MainActivity)context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.activity_map_fragment, container, false);

        TextView tv_search = view.findViewById(R.id.tv_search);


        tv_search.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent (getContext(), ParkingInformationActivity.class);
                startActivity(intent);
            }
        });


        // 지도 생성
        FragmentManager fm = getChildFragmentManager();
        MapFragment mapFragment = (MapFragment)fm.findFragmentById(R.id.map);
        if (mapFragment == null)
        {
            fm.beginTransaction().add(R.id.map, mapFragment).commit();
        }
        mapFragment.getMapAsync(this);

        return view;
    }

    private void setMarker(Marker marker, double lat, double lng, int resID, int zIndex)
    {
        marker.setIconPerspectiveEnabled(true);
        marker.setIcon(OverlayImage.fromResource(resID));
        marker.setAlpha(0.8f);
        marker.setPosition(new LatLng(lat, lng));
        marker.setZIndex(zIndex);
        marker.setMap(naverMap);
    }

    @Override
    public void onDetach()
    {
        super.onDetach();
        main = null;
    }

    @Override
    public void onMapReady(@NonNull NaverMap naverMap)
    {
        this.naverMap = naverMap;

        CameraUpdate();
        MarkerUpdate();
    }

    void CameraUpdate()
    {
        CameraUpdate cameraUpdate;

        cameraUpdate = CameraUpdate.scrollAndZoomTo(new LatLng(36.32736606792058, 127.33835718548377), 15).animate(CameraAnimation.Fly, 1000);

        naverMap.moveCamera(cameraUpdate);
    }


    // 마커를 설정하기 위한 노력들
    int max = 100;
    Marker[] marker;
    InfoWindow[] infoWindow;
    void MarkerUpdate()
    {
        marker = new Marker[max];
        infoWindow = new InfoWindow[max];

        setMarker();
    }

    // 마커와 정보창 설정
     void setMarker()
     {
         for(int i=0; i<max; i++)
         {
             marker[i] = new Marker();
             infoWindow[i] = new InfoWindow();
         }

         // 목원대학교 정문
         marker[0].setPosition(new LatLng(36.32883615298836, 127.33874220982563));
         marker[0].setIcon(OverlayImage.fromResource(R.drawable.marker_green));
         marker[0].setMap(naverMap);

         // 목원대학교 학생회관
         marker[1].setPosition(new LatLng(36.32807049116183, 127.33862195511007));
         marker[1].setIcon(OverlayImage.fromResource(R.drawable.marker_orange));
         marker[1].setMap(naverMap);

         // 목원대학교 도서관
         marker[2].setPosition(new LatLng(36.32507427860029, 127.338547739366));
         marker[2].setIcon(OverlayImage.fromResource(R.drawable.marker_red));
         infoWindow[2].setAdapter(new InfoWindow.DefaultTextAdapter(getActivity())
         {
             @NonNull
             @Override
             public CharSequence getText(@NonNull InfoWindow infoWindow)
             {
                 return "목원대학교 도서관";
             }
         });
         marker[2].setMap(naverMap);

         // 계룡황제 전용 주차장
         marker[99].setPosition(new LatLng( 36.27455401852988, 127.2485525622957));
         marker[99].setIcon(OverlayImage.fromResource(R.drawable.marker_red));
         infoWindow[99].setAdapter(new InfoWindow.DefaultTextAdapter(getActivity())
         {
             @NonNull
             @Override
             public CharSequence getText(@NonNull InfoWindow infoWindow)
             {
                 return "계룡황제 전용 주차장";
             }
         });
         marker[99].setMap(naverMap);

         /*

         정보 처리하는 곳

          */

         ViewGroup root = (ViewGroup) getView().findViewById(R.id.fragment);

         // 지도를 클릭하면 정보 창을 닫음
         naverMap.setOnMapClickListener((coord, point) -> {
             for (int i=0; i<max; i++)
             {
                 infoWindow[i].close();
             }
         });

         marker[0].setOnClickListener(overlay -> {
             for (int i=0; i<max; i++)
             {
                 infoWindow[i].close();
             }

             PointInformationAdapter adapter = new PointInformationAdapter(getActivity().getApplicationContext(), root, "목원대학교 정문", "대전 서구 도안북로 88", 95);
             infoWindow[0].setAdapter(adapter);

             // 마커를 클릭할 때 정보창을 엶
             infoWindow[0].open(marker[0]);
             return true;
         });

         marker[1].setOnClickListener(overlay -> {
             for (int i=0; i<max; i++)
             {
                 infoWindow[i].close();
             }
             // 마커를 클릭할 때 정보창을 엶
             PointInformationAdapter adapter = new PointInformationAdapter(getActivity().getApplicationContext(), root, "목원대학교 학생회관", "대전 서구 도안북로 88", 48);
             infoWindow[1].setAdapter(adapter);

             infoWindow[1].open(marker[1]);

             return true;
         });

         marker[2].setOnClickListener(overlay -> {
             for (int i=0; i<max; i++)
             {
                 infoWindow[i].close();
             }
             // 마커를 클릭할 때 정보창을 엶
             infoWindow[2].open(marker[2]);
             PointInformationAdapter adapter = new PointInformationAdapter(getActivity().getApplicationContext(), root, "목원대학교 도서관", "대전 서구 도안북로 88", 3);
             infoWindow[2].setAdapter(adapter);

             infoWindow[2].open(marker[2]);

             return true;
         });

         marker[99].setOnClickListener(overlay -> {
             for (int i=0; i<max; i++)
             {
                 infoWindow[i].close();
             }
             // 마커를 클릭할 때 정보창을 엶
             PointInformationAdapter adapter = new PointInformationAdapter(getActivity().getApplicationContext(), root, "계룡시청", "충남 계룡시 장안로 46 계룡시청", 1);
             infoWindow[99].setAdapter(adapter);

             infoWindow[99].open(marker[99]);

             return true;
         });
     }

}