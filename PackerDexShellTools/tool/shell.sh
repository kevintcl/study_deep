#!/usr/bin/env bash
cd /Users/tangchunlin/study/projects/study_deep/PackerDexShellTools/tool

rm force/demo.apk
echo 'rm demo.apk suc'
rm force/classes.dex
echo 'rm classes.dex suc'
rm force/shell.dex
echo 'rm shell.dex suc'



cp /Users/tangchunlin/study/projects/study_deep/packer_source_apk/build/outputs/apk/debug/packer_source_apk-debug.apk force/demo.apk
echo 'cp demo.apk suc'
cp /Users/tangchunlin/study/projects/study_deep/packer_parse_apk/build/intermediates/dex/debug/mergeDexDebug/out/classes.dex force/shell.dex
echo 'cp shell.dex suc'


java -cp  ../build/libs/PackerDexShellTools.jar  com.zeus.packerdexshelltools.ShellTool

echo '生成 加壳 apk 成功'
cd sign
rm demo*.apk
echo 'rm demo.apk suc'
rm demo*-release.apk
echo 'rm demo-release.apk suc'
time=$(date "+%Y-%m-%d_%H%M%S")
echo ${time}
cp ../force/demo.apk ./demo${time}.apk

#jarsigner -verbose -keystore secure.jks -storepass androidsecure -keypass androidsecure -sigfile CERT -digestalg SHA1 -sigalg MD5withRSA -signedjar demo${time}-release.apk demo${time}.apk secure
jarsigner -verbose -keystore secure.jks -storepass androidsecure -keypass androidsecure -sigfile CERT -digestalg SHA1 -sigalg MD5withRSA -signedjar demo${time}-release.apk demo${time}.apk secure


echo '签名 加壳 apk 成功'

#查看签名
keytool -list -printcert -jarfile demo${time}-release.apk
#apksigner verify -v demo${time}-release.apk
echo $(pwd)