object Versions {
    val sharedPreference = "1.0.6"
    val work_version = "2.3.4"
    val core_ktx_version = "1.3.2"
    val kotlin_version = "1.4.10"
    val picasso_version = "2.5.2"
    val nav_version = "2.3.0"
    val appcompat_version = "1.2.0"
    val fragment_version = "1.3.0-beta01"
    val cardview_version = "1.0.0"
    val recyclerview_version = "1.2.0-alpha02"
    val multidex_version = "2.0.1"
    val lifecycle_version = "2.2.0"
    val legacy_version = "1.0.0"
    val room_version = "2.2.5"
    val constraintlayout_version = "2.0.2"
    val material_version = "1.3.0-alpha04"
    val retrofit2_version = "2.6.1"
    val okhttp3_version = "4.9.0"
    val converter_gson_version = "2.4.0"
    val logging_interceptor_version = "4.4.0"
    val gson_version = "2.8.2"
    val coroutines_version = "1.3.9"
    val groupie_version = "2.1.0"
    val hilt_android = "2.28-alpha"
    val hilt_view_model = "1.0.0-alpha01"
    val hilt_kapt = "2.28-alpha"
    val hilt_kapt_compiler = "1.0.0-alpha01"

    val activityKtxVersion = "1.2.0-beta01"
    val fragmentKtxVersion = "1.3.0-alpha05"
    val glideVersion = "4.11.0"
    val glideProcessorVersion = "4.9.0"
    val sdpVersion = "1.0.6"
    val sspVersion = "1.0.6"
    val eventBusVersion = "3.1.1"
    val seeMoreViewVersion = "1.0.2"
    val localizationActivityVersion = "1.2.2"
    val koin_version = "2.0.1"
    val roundedImageView = "2.3.0"
    val roundedLayout = "1.1.3"
    val cardSliderViewPager = "1.0.1"
    val otp_view_version = "2.1.2"
    val bubble_bottom_nav_version = "1.2.5-dotBadge"
    val tooltipMenu_version = "2.4.3"

    val androidx_test_version = "1.1.0"
    val androidx_test_ktx_version = "1.2.0"
    val mockito_version = "2.19.0"
    val kotlinTest = "1.3.61"
    val androidx_espresso_idling_resource = "3.2.0"
    val mockk_version = "1.9.2"
    val androidx_test_ext = "1.1.1"

    val test_junit = "4.12"
    val test_runner = "1.1.1"
    val espresso = "3.1.1"
    val mockitoInline = "2.11.0"
    val test_arch_core = "2.0.1"
    val mockitoKotlinVersion = "2.2.0"
    val junit_jupiter_version = "5.3.2"
    val circle_image_view = "3.1.0"

    val lottie_version = "3.5.0"
    val switch_buttons_version = "1.1.8"
    val expandableRecyclerView = "1.4"
    val security_crypto_version = "1.0.0-rc03"

    val okio_version = "2.3.0"

    val hawk_version = "2.0.1"
    val image_picker_version = "2.4.1"
    val image_picker_pro_version = "0.5.3-3"
    val file_picker_version = "2.2.5"
    val viewpager2_version = "1.0.0"
    val flowLayout_version = "1.3.3"

    val recyclerAnimation_version = "4.0.2"

    val appCenterSdkVersion = "4.1.0"

    val hijriDatePickerVersion = "3.0.0"

    val locus_location_version="3.0.1"

    val gsm_location_version="17.0.0"
}


object Android {
    val applicationId = "com.example.location_finder"
    val compileSdkVersion = 29
    val buildToolsVersion = "29.0.2"
    val minSdkVersion = 23
    val targetSdkVersion = 29
    val versionCode = 1
    val versionName = "1.0"

    val kotlin_version = "1.3.72"
    val mockitoKotlinVersion = "1.5.0"
    val nav_version = "2.1.0"
    val arch_version = "1.1.1"
}

object Kotlin {
    val kotlin_stdlib_jdk = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin_version}"
    val kotlin_stdlib = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin_version}"
    val core_ktx = "androidx.core:core-ktx:${Versions.core_ktx_version}"
    val kotlin_reflect = "org.jetbrains.kotlin:kotlin-reflect:${Versions.kotlin_version}"
}

object Androidx {
    val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat_version}"
    val fragment = "androidx.fragment:fragment-ktx:${Versions.fragment_version}"
    val cardview = "androidx.cardview:cardview:${Versions.cardview_version}"
    val recyclerview = "androidx.recyclerview:recyclerview:${Versions.recyclerview_version}"
    val multidex = "androidx.multidex:multidex:${Versions.multidex_version}"
    val lifecycle = "androidx.lifecycle:lifecycle-extensions:${Versions.lifecycle_version}"
    val legacy = "androidx.legacy:legacy-support-v4:${Versions.legacy_version}"
    val lifecycle_compiler_kapt =
        "androidx.lifecycle:lifecycle-compiler:${Versions.lifecycle_version}"
    val ifecycle_reactivestreams =
        "androidx.lifecycle:lifecycle-reactivestreams:${Versions.lifecycle_version}"
    val lifecycle_common =
        "androidx.lifecycle:lifecycle-common-java8:${Versions.lifecycle_version}"
    val lifecycle_runtime =
        "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle_version}"
    val lifecycle_livedata =
        "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle_version}"
    val constraintlayout =
        "androidx.constraintlayout:constraintlayout:${Versions.constraintlayout_version}"
    val navigation_fragment =
        "androidx.navigation:navigation-fragment-ktx:${Versions.nav_version}"
    val navigation_ui = "androidx.navigation:navigation-ui-ktx:${Versions.nav_version}"
    val room_runtime = "androidx.room:room-runtime:${Versions.room_version}"
    val room_ktx = "androidx.room:room-ktx:${Versions.room_version}"
    val room_compiler_kapt = "androidx.room:room-compiler:${Versions.room_version}"
    val lifecycle_viewmodel =
        "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle_version}"

    val activityKtx = "androidx.activity:activity-ktx:${Versions.activityKtxVersion}"
    val fragmentKtx = "androidx.fragment:fragment-ktx:${Versions.fragmentKtxVersion}"
    val viewPager2 = "androidx.viewpager2:viewpager2:${Versions.viewpager2_version}"
    val flowLayout = "com.nex3z:flow-layout:${Versions.flowLayout_version}"
}

object Material {
    val material = "com.google.android.material:material:${Versions.material_version}"
}

object Design {
    val sdp = "com.intuit.sdp:sdp-android:${Versions.sdpVersion}"
    val ssp = "com.intuit.ssp:ssp-android:${Versions.sspVersion}"
    val seeMoreView = "com.github.mahimrocky:ShowMoreText:${Versions.seeMoreViewVersion}"
    val otpView = "com.github.mukeshsolanki:android-otpview-pinview:${Versions.otp_view_version}"
    val cardSliderViewPager = "com.github.IslamKhSh:CardSlider:${Versions.cardSliderViewPager}"
    val bubbleBottomNav =
        "com.github.NaderNabil216:BubbleTabBar:${Versions.bubble_bottom_nav_version}"
    val lottie = "com.airbnb.android:lottie:${Versions.lottie_version}"
    val switch_buttons = "lib.kingja.switchbutton:switchbutton:${Versions.switch_buttons_version}"

    val tooltipMenu = "me.piruin:quickaction:${Versions.tooltipMenu_version}"
    val hijriDatePicker = "net.alhazmy13.hijridatepicker:library:${Versions.hijriDatePickerVersion}"
}

object Picasso {
    val picasso = "com.squareup.picasso:picasso:${Versions.picasso_version}"
}

object Retrofit {
    val retrofit2 = "com.squareup.retrofit2:retrofit:${Versions.retrofit2_version}"
    val okhttp3 = "com.squareup.okhttp3:okhttp:${Versions.okhttp3_version}"
    val converter_gson =
        "com.squareup.retrofit2:converter-gson:${Versions.converter_gson_version}"
    val logging_interceptor =
        "com.squareup.okhttp3:logging-interceptor:${Versions.logging_interceptor_version}"
}

object Gson {
    val gson = "com.google.code.gson:gson:${Versions.gson_version}"
}

object AppCenter {
    val inAppUpdate = "com.microsoft.appcenter:appcenter-distribute:${Versions.appCenterSdkVersion}"
    val analytics = "com.microsoft.appcenter:appcenter-analytics:${Versions.appCenterSdkVersion}"
    val crashes = "com.microsoft.appcenter:appcenter-crashes:${Versions.appCenterSdkVersion}"
}

object Coroutines {
    val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines_version}"
    val android =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines_version}"
}

object Groupie {
    val groupie = "com.xwray:groupie:${Versions.groupie_version}"
    val groupie_extension =
        "com.xwray:groupie-kotlin-android-extensions:${Versions.groupie_version}"
}

object Hilt {
    val hilt_android = "com.google.dagger:hilt-android:${Versions.hilt_android}"
    val viewmodel = "androidx.hilt:hilt-lifecycle-viewmodel:${Versions.hilt_view_model}"
    val kapt_hilt_android = "com.google.dagger:hilt-android-compiler:${Versions.hilt_kapt}"
    val kapt_hilt_compiler = "androidx.hilt:hilt-compiler:${Versions.hilt_kapt_compiler}"
}

object Koin {
    val scope = "org.koin:koin-androidx-scope:${Versions.koin_version}"
    val viewmodel = "org.koin:koin-androidx-viewmodel:${Versions.koin_version}"
    val expremental = "org.koin:koin-androidx-ext:${Versions.koin_version}"
}

object TestLib {

    val junit = "junit:junit:${Versions.test_junit}"

    // AndroidX test
    val androidxTestCore = "androidx.test:core:${Versions.androidx_test_version}"
    val androidxTestCoreKtx = "androidx.test:core-ktx:${Versions.androidx_test_ktx_version}"
    val runner = "androidx.test:runner:${Versions.test_runner}"
    val testRules = "androidx.test:rules:${Versions.test_runner}"
    val testExt = "androidx.test.ext:junit-ktx:${Versions.androidx_test_ext}"

    // Architecture components testing
    val core_testing = "androidx.arch.core:core-testing:${Versions.test_arch_core}"
    val fragmentTesting = "androidx.fragment:fragment-testing:${Versions.fragment_version}"
    val navigationTesting = "androidx.navigation:navigation-testing:${Versions.nav_version}"

    // JUnit Jupiter
    val junit_jupiter_api = "org.junit.jupiter:junit-jupiter-api:${Versions.junit_jupiter_version}"
    val junit_jupiter_params =
        "org.junit.jupiter:junit-jupiter-api:${Versions.junit_jupiter_version}"
    val junit_jupiter_engine =
        "org.junit.jupiter:junit-jupiter-api:${Versions.junit_jupiter_version}"

    val mockito_core = "org.mockito:mockito-core:${Versions.mockito_version}"
    val mockito = "org.mockito:mockito-inline:${Versions.mockitoInline}"
    val mockito_kotlin =
        "com.nhaarman.mockitokotlin2:mockito-kotlin:${Versions.mockitoKotlinVersion}"

    // Kotlin test
    val kotlinTest = "org.jetbrains.kotlin:kotlin-test-junit:${Versions.kotlinTest}"

    val espresso_core = "androidx.test.espresso:espresso-core:${Versions.espresso}"
    val espresso_contrib = "androidx.test.espresso:espresso-contrib:${Versions.espresso}"

    val androidx_espresso_idling_resource =
        "androidx.test.espresso:espresso-idling-resource:${Versions.androidx_espresso_idling_resource}"

    val mockk = "io.mockk:mockk-android:${Versions.mockk_version}"
    val coroutinesTest =
        "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutines_version}"
}


object WorkManager {
    val workManager = "androidx.work:work-runtime-ktx:${Versions.work_version}"
}

object SharedPreference {
    val sharedPreference = "com.github.yehiahd:FastSave-Android:${Versions.sharedPreference}"
    val hawk = "com.orhanobut:hawk:${Versions.hawk_version}"
}

object Glide {
    val glide = "com.github.bumptech.glide:glide:${Versions.glideVersion}"
    val glideProcess = "com.github.bumptech.glide:compiler:${Versions.glideProcessorVersion}"
}

object EventBus {
    val eventBus = "org.greenrobot:eventbus:${Versions.eventBusVersion}"
}

object Localization {
    val localizationactivity =
        "com.akexorcist:localizationactivity:${Versions.localizationActivityVersion}"
}

object Lib {
    val roundedImageView = "com.makeramen:roundedimageview:${Versions.roundedImageView}"
    val roundedLayout = "com.github.zladnrms:RoundableLayout:${Versions.roundedLayout}"
    val circleImageView = "de.hdodenhof:circleimageview:${Versions.circle_image_view}"
    val expandableRecyclerView =
        "com.thoughtbot:expandablecheckrecyclerview:${Versions.expandableRecyclerView}"
    val imagePicker =
        "com.github.esafirm.android-image-picker:imagepicker:${Versions.image_picker_version}"

    val imagePickerPro =
        "com.github.lwj1994:Matisse:${Versions.image_picker_pro_version}"
    val filePicker = "com.droidninja:filepicker:${Versions.file_picker_version}"

    val recyclerAnimation = "jp.wasabeef:recyclerview-animators:${Versions.recyclerAnimation_version}"
}

object Security {
    val crypto = "androidx.security:security-crypto:${Versions.security_crypto_version}"
    val okio = "com.squareup.okio:okio:${Versions.okio_version}"
}

object Location{
    val locus = "com.github.BirjuVachhani:locus-android:${Versions.locus_location_version}"
    val gsm_location="com.google.android.gms:play-services-location:${Versions.gsm_location_version}"
}

object plugin{
    val google_services_plugin="com.google.gms.google-services"
}