#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <jni.h>
#include <errno.h>

JavaVM *jvm;
JNIEnv *env;

void JVM_Init()
{
    JavaVMInitArgs vm_args;
    JavaVMOption options[1];

    vm_args.version = JNI_VERSION_1_6;
    vm_args.ignoreUnrecognized = false;
    vm_args.nOptions = 0;

    char classpath[1024] = "-Djava.class.path=";
    size_t len;
    char *env_classpath;
    errno_t err = _dupenv_s(&env_classpath,&len,"CLASSPATH");

    if (err)
    {
        printf("Cannot found classpath in EnvPath!\n");
    }
    strcat_s(classpath, env_classpath);
    options[0].optionString = classpath;
    // options[0].optionString = "-Djava.class.path=C:/Program Files/Java/jdk1.8.0_20";
    vm_args.nOptions++;

    if (vm_args.nOptions > 0) {
        vm_args.options = options;
    }

    // create jvm
    jint res = JNI_CreateJavaVM(&jvm, (void **)&env, &vm_args);
    // delete options;
    if (res < 0) {
        printf("Create Java VM error, code = %d\n", res);
        exit(-1);
    }
    printf("Create Java VM successful!, code = %d\n", res);
}

void JVM_Destroy()
{
    jvm->DestroyJavaVM();
    env = NULL;
    jvm = NULL;
}

int main(){
	JVM_Init();
}