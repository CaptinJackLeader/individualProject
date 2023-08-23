package com.example.individualproject


import android.content.Context
import android.content.Intent
import android.location.Location
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.individualproject.ui.theme.IndividualProjectTheme
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.delay
import android.app.Activity
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Card
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.core.app.ActivityCompat

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            IndividualProjectTheme() {
                // data store
//                val activity = LocalContext.current as? Activity
//                val sharedPref = remember { activity?.getPreferences(Context.MODE_PRIVATE) }

                // navigation controller instance
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "first") {
                    composable("first") { backStackEntry ->
                        FirstScreen(navController = navController)
                    }
//                    composable("second") {
//                        SecondScreen(navController)
//                    }
                    composable("third/{todayFood}") { backStackEntry ->
                        ThirdScreen(
                            navController = navController,
                            todayFood = backStackEntry.arguments?.getString("todayFood") ?: "",
                        )
                    }
                    composable("second2") {
                        SecondScreen2(navController)
                    }
                }
            }
        }
    }
}

@Composable
fun FirstScreen(navController: NavController) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Image(
            painter = painterResource(id = R.drawable.background),
            contentDescription = "",
            modifier = Modifier.fillMaxSize(1f)
        )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {

//            MyText(height = 50, name = "오늘의 점심", size = 50, color = Color.Black)
//            MyText(height = 210, name = "당신의 선택은", size = 30, color = Color.Black)


//            Button(
//                onClick = {
//                    navController.navigate("second2")
//                },
//                shape = CircleShape,
//                modifier = Modifier.size(100.dp),
//                contentPadding = PaddingValues(all = 0.dp)
//            ) {
//                Text(text = "Click")
//            }

            Spacer(modifier = Modifier.height(60.dp))

            Row() {
                Card(
                    modifier = Modifier.clickable { navController.navigate("second2") }
                )
                {
                    Row(
                        horizontalArrangement = Arrangement.Center,
//                        modifier = Modifier
//                            .fillMaxHeight(0.32f)
//                            .fillMaxWidth(0.8f)
//                            .background(color = Color.White)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.menu),
                            contentDescription = "",
                            modifier = Modifier
                                .height(120.dp)
                                .width(120.dp)
                        )
                    }

                }
//                Spacer(modifier = Modifier.width(35.dp))
            }


            Spacer(modifier = Modifier.height(438.dp))
//            val context = LocalContext.current
//            val db = remember {
//                AppDatabase.getDatabase(context)
//            }
//            val scope = rememberCoroutineScope()
//
//            val userList by db.userDao().getAll().collectAsState(initial = emptyList())
//            val firstUser = userList.firstOrNull() // 첫 번째 사용자 가져오기

            val activity = LocalContext.current as? Activity
            val sharedPref = remember { activity?.getPreferences(Context.MODE_PRIVATE) }
            val imageS = remember() { sharedPref?.getInt("imageS", 0) ?: 0 }
            Image(
                painter = painterResource(id = imageS.takeIf { it != 0 } ?: R.drawable.menu),
                contentDescription = "",
                modifier = Modifier
                    .height(140.dp)
                    .width(390.dp)
            )

            Text(text = "어제 먹었자나 돼지야!!", fontSize = 40.sp)

            Spacer(modifier = Modifier.height(30.dp))

//            if (userList.isEmpty()) {
//                Image(
//                    painter = painterResource(id = R.drawable.menu),
//                    contentDescription = ""
//                )
//            }

            // data store
//            val activity = LocalContext.current as? Activity
//            val sharedPref = remember { activity?.getPreferences(Context.MODE_PRIVATE) }

//            val imageS = backStackEntry.arguments?.getInt("imageS") ?: R.drawable.menu


        }
    }
}


//@Composable
//fun SecondScreen(navController: NavController) {
//    Surface(
//        modifier = Modifier.fillMaxSize(),
//        color = MaterialTheme.colorScheme.background
//    ) {
//        Column(
//            horizontalAlignment = Alignment.CenterHorizontally,
//            verticalArrangement = Arrangement.Top
//        ) {
//            val answers = remember { mutableStateListOf<String>() }
//            var number by remember { mutableStateOf("") }
//            var todayFood by remember { mutableStateOf("") }
//            var click by remember { mutableStateOf(true) }
//
//            Spacer(modifier = Modifier.height(20.dp))
//
//            Divider(thickness = 1.5.dp, color = Color(0xFF5B99D6))
//            QuestionBox("질문 1.", question = "오늘은 자극적인 \n짠 음식을 먹고 싶다", click, onClick = {
//                answers.add(it)
//            })
//            Divider(thickness = 1.5.dp, color = Color(0xFF5B99D6))
//
//
//            QuestionBox("질문 2.", question = "오늘은 집밥이 땡긴다", click, onClick = {
//                answers.add(it)
//            })
//            Divider(thickness = 1.5.dp, color = Color(0xFF5B99D6))
//
//            QuestionBox("질문 3.", question = "오늘은 어패류가 땡긴다", click, onClick = {
//                answers.add(it)
//            })
//            Divider(thickness = 1.5.dp, color = Color(0xFF5B99D6))
//
//            QuestionBox("질문 4.", question = "싱싱한 재료의 맛을 \n느끼고 싶다", click, onClick = {
//                answers.add(it)
//            })
//            Divider(thickness = 1.5.dp, color = Color(0xFF5B99D6))
//
//            QuestionBox("질문 5.", question = "기름지고 느끼한 음식을 먹고 싶다", click, onClick = {
//                answers.add(it)
//            })
//            Divider(thickness = 1.5.dp, color = Color(0xFF5B99D6))
//
//            QuestionBox("질문 6.", question = "향신료와 함께 화려한 \n불맛을 느껴보고 싶다", click, onClick = {
//                answers.add(it)
//            })
//            Divider(thickness = 1.5.dp, color = Color(0xFF5B99D6))
//
//            Button(onClick = {
//                var koreaFood = 0
//                var japanFood = 0
//                var chinaFood = 0
//
//                for (i in answers) {
//                    when (i) {
//                        "한식" -> koreaFood++
//                        "일식" -> japanFood++
//                        "중식" -> chinaFood++
//                    }
//                }
//
//                val recommendMenu = maxOf(koreaFood, japanFood, chinaFood)
//
//                val recoList = mutableListOf<String>()
//                if (recommendMenu == koreaFood) {
//                    recoList.add("한식")
//                }
//                if (recommendMenu == japanFood) {
//                    recoList.add("일식")
//                }
//                if (recommendMenu == chinaFood) {
//                    recoList.add("중식")
//                }
//                todayFood = recoList.random()
//
//                click = false
//            }) {
//                Text(text = "Set")
//            }
//
//            Button(onClick = {
//                click = true
//                for (i in 0..5) {
//                    answers[i] = ""
//                }
//            }) {
//                Text(text = "Reset")
//            }
//
//            MyText(height = 10, name = "당신의 선택은", size = 30, color = Color.Black)
//
//            Spacer(modifier = Modifier.height(15.dp))
//
//            Text(text = answers.toString())
//
//            Text(text = todayFood)
//
//            Button(onClick = {
//                navController.navigate("third")
//
//            }) {
//                Text(text = "사진 및 위치 보기")
//            }
//        }
//    }
//}


@Composable
fun ThirdScreen(navController: NavController, todayFood: String) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            val context = LocalContext.current
            val fusedLocationClient = remember(context) {
                LocationServices.getFusedLocationProviderClient(context)
            }
            var userLocation by remember { mutableStateOf<Location?>(null) }

            val requestPermissionLauncher =
                rememberLauncherForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
                    if (isGranted) {
                        // 위치 권한이 허용된 경우 위치 정보 가져오기 시도
                        fusedLocationClient.lastLocation.addOnSuccessListener { location ->
                            if (location != null) {
                                val latitude = location.latitude
                                val longitude = location.longitude

                                // geo URI 생성
                                val intent = Intent(
                                    Intent.ACTION_VIEW,
                                    Uri.parse("geo:$latitude,$longitude?q=$todayFood")
                                )
                                context.startActivity(intent)
                            } else {
                                // 위치 정보가 없을 경우 처리
                            }
                        }.addOnFailureListener { exception ->
                            // 위치 정보 가져오기 실패 시 처리
                        }
                    } else {
                        // 위치 권한이 거부된 경우 처리
                        // 여기에 권한이 거부되었을 때의 동작을 추가할 수 있습니다.
                    }
                }

            // 두번째 화면에서 넘어온 값에 따라 when문 써서 해야함.
            var bowl by remember {
                mutableStateOf("")
            }
            var image by remember { mutableStateOf(0) }
            var calorie by remember { mutableStateOf("") }
            var price by remember { mutableStateOf("") }

            when (todayFood) {
                "비빔밥" -> {
                    bowl = "bibimbap"
                    image = R.drawable.bibimbap
                    calorie = "약 400~700kcal"
                    price = "약 9,000원"
                }

                "불고기" -> {
                    bowl = "bulgogi"
                    image = R.drawable.bulgogi
                    calorie = "200g당 약 526kcal"
                    price = "100g당 5,990원"
                }

                "청국장" -> {
                    bowl = "cheonggukjang"
                    image = R.drawable.cheonggukjang
                    calorie = "100g당 108kcal"
                    price = "평균 8,000원"
                }

                "갈비찜" -> {
                    bowl = "galbijjim"
                    image = R.drawable.galbijjim
                    calorie = "300g당 581kcal"
                    price = "약 13,500원"
                }

                "김치찌개" -> {
                    bowl = "kimchisoup"
                    image = R.drawable.kimchisoup
                    calorie = "1인분당 121kcal"
                    price = "약 8,000원"
                }

                "삼겹살" -> {
                    bowl = "porkbelly"
                    image = R.drawable.porkbelly
                    calorie = "100g당 331kcal"
                    price = "200g당 약 19,000원"
                }

                "갈비" -> {
                    bowl = "ribmeat"
                    image = R.drawable.ribmeat
                    calorie = "210g당 504kcal"
                    price = "1인분당 12,000원"
                }

                "된장국" -> {
                    bowl = "soybeanpastesoup"
                    image = R.drawable.soybeanpastesoup
                    calorie = "200g당 76kcal"
                    price = "5,000원"
                }

                "떡볶이" -> {
                    bowl = "tteokbokki"
                    image = R.drawable.tteokbokki
                    calorie = "200g당 304kcal"
                    price = "1인분당 3,500원"
                }

                "치라시동" -> {
                    bowl = "chirashidon"
                    image = R.drawable.chirashidon
                    calorie = "200g당 304kcal"
                    price = "30,000원"
                }

                "일본식 카레" -> {
                    bowl = "japanesecurryrice"
                    image = R.drawable.japanesecurryrice
                    calorie = " 약 600kcal"
                    price = "10,000원"
                }

                "오니기리" -> {
                    bowl = "onigiri"
                    image = R.drawable.onigiri
                    calorie = " 약 600kcal"
                    price = "10,000원"
                }

                "라멘" -> {
                    bowl = "ramen"
                    image = R.drawable.ramen
                    calorie = " 약 600kcal"
                    price = "10,000원"
                }

                "스시" -> {
                    bowl = "sushi"
                    image = R.drawable.sushi
                    calorie = "1개 37kcal"
                    price = "시가"
                }

                "타코야끼" -> {
                    bowl = "takoyaki"
                    image = R.drawable.takoyaki
                    price = "6개당 3,000원"
                    calorie = "120g당 칼로리가 171kcal"
                }

                "덴푸라" -> {
                    bowl = "tempura"
                    image = R.drawable.tempura
                    price = "10종 33,000원"
                    calorie = "1조각 48kcal"
                }

                "돈까스" -> {
                    bowl = "tonkatsu"
                    image = R.drawable.tonkatsu
                    price = "약 12,000원"
                    calorie = "약 650kcal"
                }

                "소바" -> {
                    bowl = "teuchisoba"
                    image = R.drawable.teuchisoba
                    price = "약 2,760~4,600원"
                    calorie = "100g당 약 98.9kcal"
                }

                "볶음밥" -> {
                    bowl = "bokkeumbab"
                    image = R.drawable.bokkeumbab
                    price = "약 7,000원"
                    calorie = "100g당 약 193.33Kcal"
                }

                "짜장면" -> {
                    bowl = "jajangmyeon"
                    image = R.drawable.jajangmyeon
                    price = "약 5,000원"
                    calorie = "800kcal"
                }

                "짬뽕" -> {
                    bowl = "jjamppong"
                    image = R.drawable.jjamppong
                    price = "약 6,500원"
                    calorie = "688kcal"
                }

                "깐풍기" -> {
                    bowl = "kkanpunggi"
                    image = R.drawable.kkanpunggi
                    price = "약 22,000원"
                    calorie = "100g당 249.8kcal"
                }

                "꿔바로우" -> {
                    bowl = "kkwobalou"
                    image = R.drawable.kkwobalou
                    price = "1kg당 1,1000원"
                    calorie = "200g당 384kcal"
                }

                "마라탕" -> {
                    bowl = "marathon"
                    image = R.drawable.marathon
                    price = "평균 10,000원"
                    calorie = "1인분 1,840칼로리"
                }

                "팔보채" -> {
                    bowl = "palbochae"
                    image = R.drawable.palbochae
                    price = "약 30,000원"
                    calorie = "200g당 170kcal"
                }

                "탕수육" -> {
                    bowl = "tangsuyug"
                    image = R.drawable.tangsuyug
                    price = "大 22,000원"
                    calorie = "1인분 약 470칼로리"
                }

                "양장피" -> {
                    bowl = "yangjangpi"
                    image = R.drawable.yangjangpi
                    price = "약 30,000원"
                    calorie = "208g 약 266kcal"
                }
            }


            var sizeControl by remember { mutableStateOf(true) }


            if (sizeControl) {
//                Spacer(modifier = Modifier.height(100.dp))

                Card(
                    modifier = Modifier.clickable { sizeControl = !sizeControl }
                )
                {
                    if (sizeControl) {
                        Row(
                            horizontalArrangement = Arrangement.Center,
                            modifier = Modifier
                                .fillMaxHeight(0.32f)
                                .fillMaxWidth(0.8f)
                                .background(color = Color.White)
                        ) {
                            Image(
                                painter = painterResource(id = image),
                                contentDescription = "",
                                Modifier.fillMaxSize()
                            )
                        }
                    }
                }
            } else {
                Card(
                    modifier = Modifier.clickable { sizeControl = !sizeControl }
                ) {
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier.background(color = Color.White)
                    ) {
                        Image(
                            painter = painterResource(id = image),
                            contentDescription = "",
                            Modifier.fillMaxSize()
                        )
                    }

                }

            }


            Spacer(modifier = Modifier.height(15.dp))

            Text(text = "칼로리: $calorie", fontSize = 30.sp)
            Text(text = "가격: $price", fontSize = 30.sp)

//            val context = LocalContext.current

//            val userList by db.userDao().getAll().collectAsState(initial = emptyList())

            Row(horizontalArrangement = Arrangement.Center) {
                Button(onClick = {
                    // 화면 전환
//                val intent = Intent(context, FoodActivity2::class.java)
//                context.startActivity(intent)

//                    val latitude = 37.560398
//                    val longitude = 126.967220
////
//                    val i = Intent(
//                        Intent.ACTION_VIEW,
//                        Uri.parse("geo:$latitude,$longitude?q=$todayFood")
//                    )
//                    context.startActivity(i)

//                     위치 권한 확인
                    val hasLocationPermission =
                        ActivityCompat.checkSelfPermission(
                            context,
                            android.Manifest.permission.ACCESS_FINE_LOCATION
                        ) == PackageManager.PERMISSION_GRANTED &&
                                ActivityCompat.checkSelfPermission(
                                    context,
                                    android.Manifest.permission.ACCESS_COARSE_LOCATION
                                ) == PackageManager.PERMISSION_GRANTED

                    if (hasLocationPermission) {
//                     위치 권한이 이미 허용된 경우 위치 정보 가져오기 시도
                        fusedLocationClient.lastLocation.addOnSuccessListener { location ->
                            if (location != null) {
                                val latitude = location.latitude
                                val longitude = location.longitude


                                // geo URI 생성
                                val intent = Intent(
                                    Intent.ACTION_VIEW,
                                    Uri.parse("geo:$latitude,$longitude?q=$todayFood")
                                )
                                context.startActivity(intent)
                            } else {
                                // 위치 정보가 없을 경우 처리
                            }
                        }.addOnFailureListener { exception ->
                            // 위치 정보 가져오기 실패 시 처리
                        }
                    } else {
                        // 위치 권한이 허용되지 않은 경우 권한 요청
//                        requestPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
                    }

                }) {
                    Text(text = "< 근처 맛집 >")
                }

                Spacer(modifier = Modifier.width(20.dp))

//                val db = remember {
//                    AppDatabase.getDatabase(context)
//                }
//                val scope = rememberCoroutineScope()
//                val userList by db.userDao().getAll().collectAsState(initial = emptyList())
//                val editUser = userList.firstOrNull() // 첫 번째 사용자 가져오기

                // data store
                val activity = LocalContext.current as? Activity
                val sharedPref = remember { activity?.getPreferences(Context.MODE_PRIVATE) }

                var imageS by remember {
                    val imageValue: Int = sharedPref?.getInt("imageS", R.drawable.menu) ?: 0
                    mutableStateOf(imageValue)
                }

                Button(onClick = {
//                    editUser?.let {
//                        // Update the fields
//                        it.image = image
//
//                        scope.launch(Dispatchers.IO) {
//                            db.userDao().update(it)
//                        }
//                    }
                    imageS = image
                    sharedPref?.edit()?.putInt("imageS", imageS)?.apply()
                    navController.navigate("first")
                }) {
                    Text(text = "저장")
                }
                Spacer(modifier = Modifier.height(20.dp)) // 공백 추가
            }
        }
    }
}


@Composable
fun SecondScreen2(navController: NavController) {
    // 애니메이션으로 랜덤하게 생성
    // 한식, 중식, 일식 중 하나 선택
    // 선택한 것에 대해 9가지 음식 랜덤
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Image(
            painter = painterResource(id = R.drawable.linenote), contentDescription = "",
            modifier = Modifier.fillMaxSize(1f),
            contentScale = ContentScale.FillHeight
        )
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            // answers list always have six elements
            val answers = remember { mutableStateListOf("", "", "", "", "", "") }
            var todayC by remember { mutableStateOf("") }
            var click by remember { mutableStateOf(true) }
            var todayFood by remember { mutableStateOf("") }
            var showButton by remember { mutableStateOf(false) }


            Spacer(modifier = Modifier.height(20.dp))

            Text(text = "점심 설문지", fontSize = 50.sp)

            Spacer(modifier = Modifier.height(20.dp))


            Box() {
                Column() {
                    Divider(thickness = 1.5.dp, color = Color(0xFF070707))

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.End
                    ) {
                        var colorY by remember { mutableStateOf(0xFFFFFFFF) }
                        var colorN by remember { mutableStateOf(0xFFFFFFFF) }

                        MyText(height = 70, name = "질문 1.", size = 28, color = Color.Black)

                        Spacer(modifier = Modifier.width(10.dp))

                        Text(text = "오늘은 자극적인 \n짠 음식을 먹고 싶다.", modifier = Modifier.weight(1f))

                        Row(
                            modifier = Modifier
                                .width(120.dp), horizontalArrangement = Arrangement.End
                        ) {

                            Button(
                                onClick = {
                                    // when click, color change
                                    if (colorY == 0XFFFFFFFF && colorN == 0xFFFFFFFF) {
                                        colorY = 0xFFFF0000
                                        answers[0] = "한식"
                                    } else {
                                        colorY = 0xFFFFFFFF
                                    }

                                },
                                contentPadding = PaddingValues(all = 0.dp),
                                enabled = click
                            ) {
                                Text(text = "YES", color = Color(colorY))
                            }

                            Spacer(modifier = Modifier.width(5.dp))

                            Button(
                                onClick = {
                                    // when click, color change
                                    if (colorN == 0xFFFFFFFF && colorY == 0xFFFFFFFF) {
                                        colorN = 0xFFFF0000
                                        answers[0] = "한식x"
                                    } else {
                                        colorN = 0xFFFFFFFF
                                    }
                                },
                                contentPadding = PaddingValues(all = 0.dp),
                                enabled = click
                            ) {
                                Text(text = "NO", color = Color(colorN))
                            }

                        }
                    }
                    Divider(thickness = 1.5.dp, color = Color(0xFF070707))


                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.End
                    ) {
                        var colorY by remember { mutableStateOf(0xFFFFFFFF) }
                        var colorN by remember { mutableStateOf(0xFFFFFFFF) }

                        MyText(height = 70, name = "질문 2.", size = 28, color = Color.Black)

                        Spacer(modifier = Modifier.width(10.dp))

                        Text(text = "오늘은 집밥이 땡긴다.", modifier = Modifier.weight(1f))

                        Row(
                            modifier = Modifier
                                .width(120.dp), horizontalArrangement = Arrangement.End
                        ) {

                            Button(
                                onClick = {
                                    // when click, color change
                                    if (colorY == 0XFFFFFFFF && colorN == 0xFFFFFFFF) {
                                        colorY = 0xFFFF0000
                                        answers[1] = "한식"
                                    } else {
                                        colorY = 0xFFFFFFFF
                                    }
                                },
                                contentPadding = PaddingValues(all = 0.dp),
                                enabled = click,
                            ) {
                                Text(text = "YES", color = Color(colorY))
                            }

                            Spacer(modifier = Modifier.width(5.dp))

                            Button(
                                onClick = {
                                    // when click, color change
                                    if (colorN == 0xFFFFFFFF && colorY == 0xFFFFFFFF) {
                                        colorN = 0xFFFF0000
                                        answers[1] = "한식x"
                                    } else {
                                        colorN = 0xFFFFFFFF
                                    }
                                },
                                contentPadding = PaddingValues(all = 0.dp),
                                enabled = click
                            ) {
                                Text(text = "NO", color = Color(colorN))
                            }

                        }
                    }

                    Divider(thickness = 1.5.dp, color = Color(0xFF070707))

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.End
                    ) {
                        var colorY by remember { mutableStateOf(0xFFFFFFFF) }
                        var colorN by remember { mutableStateOf(0xFFFFFFFF) }

                        MyText(height = 70, name = "질문 3.", size = 28, color = Color.Black)

                        Spacer(modifier = Modifier.width(10.dp))

                        Text(text = "오늘은 어패류가 땡긴다.", modifier = Modifier.weight(1f))

                        Row(
                            modifier = Modifier
                                .width(120.dp), horizontalArrangement = Arrangement.End
                        ) {

                            Button(
                                onClick = {
                                    // when click, color change
                                    if (colorY == 0XFFFFFFFF && colorN == 0xFFFFFFFF) {
                                        colorY = 0xFFFF0000
                                        answers[2] = "일식"
                                    } else {
                                        colorY = 0xFFFFFFFF
                                    }
                                },
                                contentPadding = PaddingValues(all = 0.dp),
                                enabled = click
                            ) {
                                Text(text = "YES", color = Color(colorY))
                            }

                            Spacer(modifier = Modifier.width(5.dp))

                            Button(
                                onClick = {
                                    // when click, color change
                                    if (colorN == 0xFFFFFFFF && colorY == 0xFFFFFFFF) {
                                        colorN = 0xFFFF0000
                                        answers[2] = "일식x"
                                    } else {
                                        colorN = 0xFFFFFFFF
                                    }
                                },
                                contentPadding = PaddingValues(all = 0.dp),
                                enabled = click
                            ) {
                                Text(text = "NO", color = Color(colorN))
                            }

                        }
                    }
                    Divider(thickness = 1.5.dp, color = Color(0xFF070707))

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.End
                    ) {
                        var colorY by remember { mutableStateOf(0xFFFFFFFF) }
                        var colorN by remember { mutableStateOf(0xFFFFFFFF) }

                        MyText(height = 70, name = "질문 4.", size = 28, color = Color.Black)

                        Spacer(modifier = Modifier.width(10.dp))

                        Text(
                            text = "싱싱한 재료의 맛을 \n" +
                                    "느끼고 싶다.", modifier = Modifier.weight(1f)
                        )

                        Row(
                            modifier = Modifier
                                .width(120.dp), horizontalArrangement = Arrangement.End
                        ) {

                            Button(
                                onClick = {
                                    // when click, color change
                                    if (colorY == 0XFFFFFFFF && colorN == 0xFFFFFFFF) {
                                        colorY = 0xFFFF0000
                                        answers[3] = "일식"
                                    } else {
                                        colorY = 0xFFFFFFFF
                                    }
                                },
                                contentPadding = PaddingValues(all = 0.dp),
                                enabled = click
                            ) {
                                Text(text = "YES", color = Color(colorY))
                            }

                            Spacer(modifier = Modifier.width(5.dp))

                            Button(
                                onClick = {
                                    // when click, color change
                                    if (colorN == 0xFFFFFFFF && colorY == 0xFFFFFFFF) {
                                        colorN = 0xFFFF0000
                                        answers[3] = "일식x"
                                    } else {
                                        colorN = 0xFFFFFFFF
                                    }
                                },
                                contentPadding = PaddingValues(all = 0.dp),
                                enabled = click
                            ) {
                                Text(text = "NO", color = Color(colorN))
                            }

                        }
                    }

                    Divider(thickness = 1.5.dp, color = Color(0xFF070707))

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.End
                    ) {
                        var colorY by remember { mutableStateOf(0xFFFFFFFF) }
                        var colorN by remember { mutableStateOf(0xFFFFFFFF) }

                        MyText(height = 70, name = "질문 5.", size = 28, color = Color.Black)

                        Spacer(modifier = Modifier.width(10.dp))

                        Text(text = "기름지고 느끼한 음식을 먹고 싶다.", modifier = Modifier.weight(1f))

                        Row(
                            modifier = Modifier
                                .width(120.dp), horizontalArrangement = Arrangement.End
                        ) {

                            Button(
                                onClick = {
                                    // when click, color change
                                    if (colorY == 0XFFFFFFFF && colorN == 0xFFFFFFFF) {
                                        colorY = 0xFFFF0000
                                        answers[4] = "중식"
                                    } else {
                                        colorY = 0xFFFFFFFF
                                    }
                                },
                                contentPadding = PaddingValues(all = 0.dp),
                                enabled = click
                            ) {
                                Text(text = "YES", color = Color(colorY))
                            }

                            Spacer(modifier = Modifier.width(5.dp))

                            Button(
                                onClick = {
                                    // when click, color change
                                    if (colorN == 0xFFFFFFFF && colorY == 0xFFFFFFFF) {
                                        colorN = 0xFFFF0000
                                        answers[4] = "중식x"
                                    } else {
                                        colorN = 0xFFFFFFFF
                                    }
                                },
                                contentPadding = PaddingValues(all = 0.dp),
                                enabled = click
                            ) {
                                Text(text = "NO", color = Color(colorN))
                            }

                        }
                    }

                    Divider(thickness = 1.5.dp, color = Color(0xFF070707))

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.End
                    ) {
                        var colorY by remember { mutableStateOf(0xFFFFFFFF) }
                        var colorN by remember { mutableStateOf(0xFFFFFFFF) }

                        MyText(height = 70, name = "질문 6.", size = 28, color = Color.Black)

                        Spacer(modifier = Modifier.width(10.dp))

                        Text(
                            text = "향신료와 함께 화려한 \n" +
                                    "불맛을 느껴보고 싶다.", modifier = Modifier.weight(1f)
                        )

                        Row(
                            modifier = Modifier
                                .width(120.dp), horizontalArrangement = Arrangement.End
                        ) {

                            Button(
                                onClick = {
                                    // when click, color change
                                    if (colorY == 0XFFFFFFFF && colorN == 0xFFFFFFFF) {
                                        colorY = 0xFFFF0000
                                        answers[5] = "중식"
                                    } else {
                                        colorY = 0xFFFFFFFF
                                    }
                                },
                                contentPadding = PaddingValues(all = 0.dp),
                                enabled = click,
                            ) {
                                Text(text = "YES", color = Color(colorY))
                            }

                            Spacer(modifier = Modifier.width(5.dp))

                            Button(
                                onClick = {
                                    // when click, color change
                                    if (colorN == 0xFFFFFFFF && colorY == 0xFFFFFFFF) {
                                        colorN = 0xFFFF0000
                                        answers[5] = "중식x"
                                    } else {
                                        colorN = 0xFFFFFFFF
                                    }
                                },
                                contentPadding = PaddingValues(all = 0.dp),
                                enabled = click
                            ) {
                                Text(text = "NO", color = Color(colorN))
                            }

                        }
                    }
                    Divider(thickness = 1.5.dp, color = Color(0xFF070707))
                }
            }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top,
                modifier = Modifier
                    .background(Color.White)
                    .fillMaxSize(1f)
            )
            {
                Spacer(modifier = Modifier.height(30.dp))

                Row(horizontalArrangement = Arrangement.Center) {
                    Button(onClick = {
                        var koreaFood = 0
                        var japanFood = 0
                        var chinaFood = 0

                        for (i in answers) {
                            when (i) {
                                "한식" -> koreaFood++
                                "일식" -> japanFood++
                                "중식" -> chinaFood++
                            }
                        }

                        val recommendMenu = maxOf(koreaFood, japanFood, chinaFood)

                        val recoList = mutableListOf<String>()
                        if (recommendMenu == koreaFood) {
                            recoList.add("한식")
                        }
                        if (recommendMenu == japanFood) {
                            recoList.add("일식")
                        }
                        if (recommendMenu == chinaFood) {
                            recoList.add("중식")
                        }
                        todayC = recoList.random()

                        click = false
                    }) {
                        Text(text = "Set")
                    }
                    Spacer(modifier = Modifier.width(20.dp))
                    Button(onClick = {
                        click = true
                        for (i in 0..5) {
                            answers[i] = ""
                        }
                        todayC = ""
                        todayFood = ""
                    }) {
                        Text(text = "Reset")
                    }
                }

//            Text(text = answers.joinToString(", "))

                Spacer(modifier = Modifier.height(23.dp))

                Row(horizontalArrangement = Arrangement.Center) {
                    Text(
                        text = "오늘은",
                        fontSize = 40.sp,
                        color = Color.Black
                    )

                    Spacer(modifier = Modifier.width(12.dp))

                    Text(
                        text = "$todayC!!",
                        fontSize = 40.sp,
                        color = Color(0xFF26AC2C)
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))


                val foodKlist = remember {
                    mutableStateListOf(
                        "비빔밥",
                        "불고기",
                        "청국장",
                        "갈비찜",
                        "김치찌개",
                        "삼겹살",
                        "갈비",
                        "된장국",
                        "떡볶이"
                    )
                }
                val foodJlist = remember {
                    mutableStateListOf(
                        "치라시동",
                        "일본식 카레",
                        "오니기리",
                        "라멘",
                        "스시",
                        "타코야끼",
                        "덴푸라",
                        "돈까스",
                        "소바"
                    )
                }
                val foodClist = remember {
                    mutableStateListOf(
                        "볶음밥", "짜장면", "짬뽕", "깐풍기",
                        "꿔바로우", "마라탕", "팔보채", "탕수육", "양장피"
                    )
                }

                when (todayC) {
                    "한식" -> todayFood = foodKlist.random()
                    "일식" -> todayFood = foodJlist.random()
                    "중식" -> todayFood = foodClist.random()
                }

                if (todayFood != "") {

                    LaunchedEffect(Unit) {
                        delay(1500) // 1.5초 지연
                        showButton = true
                    }

                    if (showButton) {
                        Button(
                            onClick = {
                                navController.navigate("third/$todayFood")
                            },
                            modifier = Modifier.background(Color.Transparent)
                        ) {
                            Text(
                                text = todayFood,
                                fontStyle = FontStyle.Italic,
                                fontWeight = FontWeight.Bold,
                                fontSize = 25.sp
                            )
                        }
                    }
                }
            }
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun FoodSelectPreview() {
//    IndividualProjectTheme {
//        SecondScreen()
//    }
//}

@Composable
fun MyText(height: Int, name: String, size: Int, color: Color) {
    Spacer(modifier = Modifier.height(height.dp))
    Text(text = name, fontSize = size.sp, color = color, fontStyle = FontStyle.Italic)
}

@Composable
fun QuestionBox(
    qNumber: String,
    question: String,
    click: Boolean,
    onClick: (String) -> Unit
) {
    var number by remember { mutableStateOf("") }
    var colorY by remember { mutableStateOf(0xFFFFFFFF) }
    var colorN by remember { mutableStateOf(0xFFFFFFFF) }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.End
    ) {

        MyText(height = 70, name = qNumber, size = 28, color = Color.Black)

        Spacer(modifier = Modifier.width(10.dp))

        Text(text = question, modifier = Modifier.weight(1f))

        Row(
            modifier = Modifier
                .width(120.dp), horizontalArrangement = Arrangement.End
        ) {

            Button(
                onClick = {
                    // when click, color change
                    colorY = if (colorY == 0xFFFFFFFF) {
                        0xFFFF0000
                    } else {
                        0xFFFFFFFF
                    }

                    number = when (qNumber) {
                        "질문 1." -> "한식"
                        "질문 2." -> "한식"
                        "질문 3." -> "일식"
                        "질문 4." -> "일식"
                        "질문 5." -> "중식"
                        "질문 6." -> "중식"
                        else -> {
                            ""
                        }
                    }
                    onClick(number)

                },
                contentPadding = PaddingValues(all = 0.dp),
                enabled = click
            ) {
                Text(text = "YES", color = Color(colorY))
            }

            Spacer(modifier = Modifier.width(5.dp))

            var no by remember {
                mutableStateOf(false)
            }
            ToggleButton(
                text = "NO",
                selected = no,
                onClick = {
                    number = when (qNumber) {
                        "질문 1." -> "한식x"
                        "질문 2." -> "한식x"
                        "질문 3." -> "일식x"
                        "질문 4." -> "일식x"
                        "질문 5." -> "중식x"
                        "질문 6." -> "중식x"
                        else -> {
                            ""
                        }
                    }
                    no = !no
                    onClick(number)

                },
                click
            )

        }
    }
//    return number

}

@Composable
fun ToggleButton(text: String, selected: Boolean, onClick: () -> Unit, click: Boolean) {
    Button(
        onClick = {
            onClick()
        },
        contentPadding = PaddingValues(all = 0.dp),
        enabled = click
    ) {
        Text(
            text = text, color = when {
                selected -> Color(0xFFFF0000)
                else -> Color(0xFFFFFFFF)
            }
        )
    }
}

fun launchMapApp(context: Context, location: Location?, todayFood: String) {
    location?.let {
        val latitude = it.latitude
        val longitude = it.longitude
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse("geo:$latitude,$longitude?q=$todayFood")
        context.startActivity(intent)
    }
}