package com.example.individualproject

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class FoodActivity2 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question)

        // 원하는 위도와 경도 값을 설정
        val latitude = 37.5665
        val longitude = 126.9780
        val i = Intent(Intent.ACTION_VIEW)
        i.data = Uri.parse("geo:$latitude,$longitude")
        startActivity(i)
        // Google 지도 앱 실행을 시도
        val googleMapIntent = Intent(Intent.ACTION_VIEW, Uri.parse("geo:$latitude,$longitude"))
//        googleMapIntent.setPackage("com.google.android.apps.maps") // Google 지도 앱

//        if (googleMapIntent.resolveActivity(packageManager) != null) {
//            startActivity(googleMapIntent)
//        } else {
//            // Google 지도 앱이 설치되지 않았을 경우, 다음 지도 앱 실행을 시도
//            val daumMapIntent = Intent(Intent.ACTION_VIEW, Uri.parse("geo:$latitude,$longitude"))
//            daumMapIntent.setPackage("net.daum.android.map") // 다음 지도 앱
//
//            if (daumMapIntent.resolveActivity(packageManager) != null) {
//                startActivity(daumMapIntent)
//            } else {
//                // 다음 지도 앱도 설치되지 않았을 경우
//                Toast.makeText(this, "Please install a map application.", Toast.LENGTH_SHORT).show()
//            }
//        }
    }
}

