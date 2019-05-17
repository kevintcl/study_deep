# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#保留源文件以及行号 方便查看具体的崩溃信息
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile


##################### 保留类名 begin==================

#一颗星表示只保持该包下的类名，而子包下的类名还是会被混淆
#-keep class com.zeus.proguard.*

#两颗星表示把本包和所含子包下的类名都保持
#-keep class com.zeus.proguard.**

#使用上面两个规则都只是保持类名不变，类中的方法和成员都会被混淆
##################### 保留类名 end==================


##################### 保留类名及类的成员 begin==================
#保留 com.zeus.proguard.parent 包下的类及类的成员
#-keep class com.zeus.proguard.parent.*{*;}

#保留 com.zeus.proguard.parent 包下及其子包下的类及类的成员
#-keep class com.zeus.proguard.parent.**{*;}

#保留具体的某个类及类的成员
#-keep class com.zeus.proguard.parent.ParentTest1{*;}

#如果不希望保留类的所有成员，可以使用：
##<init>(...);     //匹配所有构造器
##<fields>;   //匹配所有域
##<methods>;  //匹配所有方法方法

# 如保留构造方法
#-keep class com.zeus.proguard.parent.ParentTest1{
#<init>(...);
#<fields>;
#<methods>;
#}

# 还可以在<fields>或<methods>前面加上private 、public、native等来进一步指定不被混淆的内容。
#如保留指定构造方法,且会保存类名
#-keep class com.zeus.proguard.parent.ParentTest1{
#    private <init>(int, java.lang.String);
#}
-keep class com.zeus.proguard.parent.ParentTest1{
    public *;
    protected *;
#     public protected *; 不能写成这种，至少在R8中不能
}

##################### 保留类名及类的成员 end==================

##################### 保留指定类的所有子类（implement／extends） begin==================
#-keep public class * extends com.zeus.proguard.parent.ParentTest1
##################### 保留指定类的所有子类（implement／extends） end==================