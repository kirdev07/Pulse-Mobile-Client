plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.kirdev.pulsepc"
    compileSdk {
        version = release(37)
    }

    defaultConfig {
        applicationId = "com.kirdev.pulsepc"
        minSdk = 30
        targetSdk = 37
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    signingConfigs {
        create("release") {
            // ключ и пароль берутся из переменных окружения, в репозиторий они не попадают
            val ks = System.getenv("PULSE_KS_FILE")
            if (ks != null) {
                storeFile = file(ks)
                storePassword = System.getenv("PULSE_KS_PASS")
                keyAlias = System.getenv("PULSE_KS_ALIAS")
                keyPassword = System.getenv("PULSE_KS_PASS")
            }
        }
    }

    buildTypes {
        release {
            if (System.getenv("PULSE_KS_FILE") != null) signingConfig = signingConfigs.getByName("release")
            optimization {
                enable = false
            }
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.core.ktx)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.junit)
}