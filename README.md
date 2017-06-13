# AndroidNougatFrameWork
基于android7.0搭建开发框架

## 数据库

```
 compile 'org.greenrobot:greendao:3.2.0'
```

## 网络请求

rxjava2+retrofit

```
compile 'com.squareup.okhttp3:okhttp:3.4.2'
    compile 'io.reactivex.rxjava2:rxjava:2.0.7'
    compile 'io.reactivex.rxjava2:rxandroid:2.0.1'
    compile 'com.squareup.retrofit2:retrofit:2.3.0'
    compile 'com.squareup.retrofit2:converter-gson:2.2.0'
    compile 'com.squareup.retrofit2:adapter-rxjava2:2.2.0'
    compile 'com.squareup.retrofit2:retrofit-converters:2.1.0'
```

## 生命周期管理

```
compile 'com.trello.rxlifecycle2:rxlifecycle:2.1.0'
    compile 'com.trello.rxlifecycle2:rxlifecycle-android:2.1.0'
    compile 'com.trello.rxlifecycle2:rxlifecycle-components:2.1.0'
```
## 事件总线

```
compile 'org.greenrobot:eventbus:3.0.0'
```
## log

```
compile 'com.orhanobut:logger:1.15'
```
## 调试

```
compile 'com.facebook.stetho:stetho:1.4.2'
    compile 'com.facebook.stetho:stetho-okhttp3:1.4.2'
```
## 视图绑定

```
compile 'com.jakewharton:butterknife:8.4.0'
 annotationProcessor 'com.jakewharton:butterknife-compiler:8.4.0'
```

## lambda

```
retrolambdaConfig 'net.orfjackal.retrolambda:retrolambda:2.3.0'
```

## lombok

```
 compile 'org.projectlombok:lombok:1.16.16'
```

## 动态权限

```
compile project(':premisson')
```

## rxjava，retrofit自定义数据转换 错误处理
ApiException.java
HttpStatus.java
CustomGsonConverterFactory.java
CustomGsonRequestBodyConverter.java
CustomGsonResponseBodyConverter.java
BaseDisposableObserver.java
BaseObserver.java

## rx简化调用
RxObservableUtils.java




# Template
