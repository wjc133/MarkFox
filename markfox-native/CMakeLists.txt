cmake_minimum_required(VERSION 2.8)
project(MarkFox)
set(BUILD_USE_64BITS on)
FIND_PACKAGE(JNI REQUIRED)
include_directories(${JNI_INCLUDE_DIRS})

message(JVM LIB >>> ${JAVA_JVM_LIBRARY})
ADD_EXECUTABLE(MarkFox main.cpp)
target_link_libraries(MarkFox ${JAVA_JVM_LIBRARY})