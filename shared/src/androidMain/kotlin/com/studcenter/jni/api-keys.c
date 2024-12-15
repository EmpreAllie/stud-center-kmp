#include <jni.h>

jstring getString(JNIEnv *env, jstring string) {
    return (*env)->NewStringUTF(env, string);
}

JNIEXPORT jstring JNICALL
Java_com_studcenter_data_infrastructure_NativeHost_1androidKt_url(JNIEnv *env, jclass clazz) {
    return getString(env, "http://10.0.2.2:4010");
}