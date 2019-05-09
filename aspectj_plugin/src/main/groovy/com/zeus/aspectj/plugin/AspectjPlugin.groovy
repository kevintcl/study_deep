package com.zeus.aspectj.plugin

import com.android.build.gradle.AppPlugin
import com.android.build.gradle.LibraryPlugin
import org.aspectj.bridge.IMessage
import org.aspectj.bridge.MessageHandler
import org.aspectj.tools.ajc.Main
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.TaskProvider
import org.gradle.api.tasks.compile.JavaCompile

public class AspectjPlugin implements Plugin<Project> {

    @Override
    void apply(Project project) {
//        project.task('testPlugin') << {
//            println "Hello gradle plugin in src"
//        }

        project.task('testPlugin') {
            println 'hello, world!'
        }


        def hasApp = project.plugins.withType(AppPlugin)
        def hasLib = project.plugins.withType(LibraryPlugin)
        if (!hasApp && !hasLib) {
            throw new IllegalStateException("'android' or 'android-library' plugin required.")
        }

        final def log = project.logger
        final def variants
        if (hasApp) {
            variants = project.android.applicationVariants
        } else {
            variants = project.android.libraryVariants
        }

        project.dependencies {
            // TODO this should come transitively
            debugImplementation 'org.aspectj:aspectjrt:1.9.3'    //app 运行时需要aspectjrt
            implementation 'org.aspectj:aspectjrt:1.9.3'
        }

        variants.all { variant ->
            //--------------------------------问题位置-------------------------
//            JavaCompile javaCompile = variant.javaCompile //******问题代码
            //--------------------------------修复后的代码-------------------
            JavaCompile javaCompile = null
            if (variant.hasProperty('javaCompileProvider')) {
                //gradle 4.10.1 +
                TaskProvider<JavaCompile> provider =  variant.javaCompileProvider
                javaCompile = provider.get()
            } else {
                javaCompile = variant.hasProperty('javaCompiler') ? variant.javaCompiler : variant.javaCompile
            }
            //--------------------------------修复后的代码-----------------------

            //ajc args:
            // [-showWeaveInfo,
            // -1.8,
            // -inpath,
            //           /Users/tangchunlin/study/projects/study_deep/aspectj_test/build/intermediates/javac/release/compileReleaseJavaWithJavac/classes,
            // -aspectpath,
            //          /Users/tangchunlin/.gradle/caches/transforms-2/files-2.1/76fa4322f7c98fc5303c4b66cbec37cc/jars/classes.jar
            //          :/Users/tangchunlin/.gradle/caches/transforms-2/files-2.1/5c437a9a40526b2488276be0f5ab1b14/jars/classes.jar
            //          .....
            //          :/Users/tangchunlin/.gradle/caches/transforms-2/files-2.1/c719eaa59ad928b5d30a4c9ef68fd56b/jars/classes.jar:/Users/tangchunlin/.gradle/caches/transforms-2/files-2.1/4802bea8189c34aadd8d420b1d48853a/jars/classes.jar:/Users/tangchunlin/.gradle/caches/transforms-2/files-2.1/769f402933ce791ed4b3c51c26f45d0d/jars/classes.jar:/Users/tangchunlin/.gradle/caches/modules-2/files-2.1/androidx.lifecycle/lifecycle-common/2.0.0/e070ffae07452331bc5684734fce6831d531785c/lifecycle-common-2.0.0.jar:/Users/tangchunlin/.gradle/caches/transforms-2/files-2.1/dbc289b1b6a0bccc4b426cac921f1bfe/jars/classes.jar:/Users/tangchunlin/.gradle/caches/modules-2/files-2.1/androidx.arch.core/core-common/2.0.0/bb21b9a11761451b51624ac428d1f1bb5deeac38/core-common-2.0.0.jar:/Users/tangchunlin/.gradle/caches/transforms-2/files-2.1/1a7379647f8b12b050c0d3f7c8fb7647/jars/classes.jar:/Users/tangchunlin/.gradle/caches/modules-2/files-2.1/androidx.annotation/annotation/1.0.0/45599f2cd5965ac05a1488fa2a5c0cdd7c499ead/annotation-1.0.0.jar:/Users/tangchunlin/.gradle/caches/modules-2/files-2.1/androidx.constraintlayout/constraintlayout-solver/1.1.3/54abe9ffb22cc9019b0b6fcc10f185cc4e67b34e/constraintlayout-solver-1.1.3.jar,
            // -d,
            //          /Users/tangchunlin/study/projects/study_deep/aspectj_test/build/intermediates/javac/release/compileReleaseJavaWithJavac/classes,
            // -classpath,
            //          /Users/tangchunlin/.gradle/caches/transforms-2/files-2.1/76fa4322f7c98fc5303c4b66cbec37cc/jars/classes.jar
            //          :/Users/tangchunlin/.gradle/caches/transforms-2/files-2.1/5c437a9a40526b2488276be0f5ab1b14/jars/classes.jar
            //          ....
            //           :/Users/tangchunlin/.gradle/caches/modules-2/files-2.1/org.aspectj/aspectjrt/1.9.3/c98f37d3c7979f7cf813cf11df53b2dc05805f82/aspectjrt-1.9.3.jar:/Users/tangchunlin/.gradle/caches/transforms-2/files-2.1/c873e117e664ee74248834e9a9471b47/jars/classes.jar:/Users/tangchunlin/.gradle/caches/transforms-2/files-2.1/74e30af680ef213e8aadec9ab41a8f74/jars/classes.jar:/Users/tangchunlin/.gradle/caches/transforms-2/files-2.1/8ca7c5a0c4f005cfa0d040785c3bf65b/jars/classes.jar:/Users/tangchunlin/.gradle/caches/transforms-2/files-2.1/81a8cf8a99e796645d33dfe4815fea80/jars/classes.jar:/Users/tangchunlin/.gradle/caches/transforms-2/files-2.1/6a06adf264ea5627833acfdd58f369a2/jars/classes.jar:/Users/tangchunlin/.gradle/caches/transforms-2/files-2.1/116437a538b430452f8a691d8043055c/jars/classes.jar:/Users/tangchunlin/.gradle/caches/transforms-2/files-2.1/c8b781182c61d9d0c5959071222098b1/jars/classes.jar:/Users/tangchunlin/.gradle/caches/transforms-2/files-2.1/c9a2aa8558f4e75c8e620da3ce993b76/jars/classes.jar:/Users/tangchunlin/.gradle/caches/transforms-2/files-2.1/687cc1d370a970d3e94ddbfcd2ac9a93/jars/classes.jar:/Users/tangchunlin/.gradle/caches/transforms-2/files-2.1/fa3583604b06d05e8ccb619696ede7f1/jars/classes.jar:/Users/tangchunlin/.gradle/caches/transforms-2/files-2.1/0bdd9388928112d33910bbee3cebc9b4/jars/classes.jar:/Users/tangchunlin/.gradle/caches/transforms-2/files-2.1/9a52c770da66c45f13737e7a4c7de61b/jars/classes.jar:/Users/tangchunlin/.gradle/caches/transforms-2/files-2.1/2515a700e9803f34a4c86bbec999cf0e/jars/classes.jar:/Users/tangchunlin/.gradle/caches/transforms-2/files-2.1/aafa161ab831ad9fcb312a12337c7706/jars/classes.jar:/Users/tangchunlin/.gradle/caches/transforms-2/files-2.1/cb8007652ac61e49c9b0f1ec4b35d707/jars/classes.jar:/Users/tangchunlin/.gradle/caches/modules-2/files-2.1/androidx.collection/collection/1.0.0/42858b26cafdaa69b6149f45dfc2894007bc2c7a/collection-1.0.0.jar:/Users/tangchunlin/.gradle/caches/transforms-2/files-2.1/34e25d6ac3404c4c962f63b0491cb92f/jars/classes.jar:/Users/tangchunlin/.gradle/caches/transforms-2/files-2.1/f6467451963d7da4cc81c4bdd277ba33/jars/classes.jar:/Users/tangchunlin/.gradle/caches/transforms-2/files-2.1/e46bb68db4af31cee80a58c9e618062a/jars/classes.jar:/Users/tangchunlin/.gradle/caches/transforms-2/files-2.1/0dc1ab10f58b7d355fe77e4ed0e162e7/jars/classes.jar:/Users/tangchunlin/.gradle/caches/transforms-2/files-2.1/b749ce6754c69c261d50949cb1c7aee9/jars/classes.jar:/Users/tangchunlin/.gradle/caches/transforms-2/files-2.1/c719eaa59ad928b5d30a4c9ef68fd56b/jars/classes.jar:/Users/tangchunlin/.gradle/caches/transforms-2/files-2.1/4802bea8189c34aadd8d420b1d48853a/jars/classes.jar:/Users/tangchunlin/.gradle/caches/transforms-2/files-2.1/769f402933ce791ed4b3c51c26f45d0d/jars/classes.jar:/Users/tangchunlin/.gradle/caches/modules-2/files-2.1/androidx.lifecycle/lifecycle-common/2.0.0/e070ffae07452331bc5684734fce6831d531785c/lifecycle-common-2.0.0.jar
            //-bootclasspath,
            //          /Users/tangchunlin/Library/Android/sdk/platforms/android-28/android.jar
            //          :/Users/tangchunlin/Library/Android/sdk/build-tools/28.0.3/core-lambda-stubs.jar]

            javaCompile.doLast {
                String[] args = [
                        "-showWeaveInfo",
                        "-1.9",
                        "-inpath", javaCompile.destinationDir.toString(),
                        "-aspectpath", javaCompile.classpath.asPath,
                        "-d", javaCompile.destinationDir.toString(),
                        "-classpath", javaCompile.classpath.asPath,
                        "-bootclasspath", project.android.bootClasspath.join(File.pathSeparator)
                ]
                log.lifecycle "ajc args: " + Arrays.toString(args)

                MessageHandler handler = new MessageHandler(true);
                new Main().run(args, handler);
                for (IMessage message : handler.getMessages(null, true)) {
                    switch (message.getKind()) {
                        case IMessage.ABORT:
                        case IMessage.ERROR:
                        case IMessage.FAIL:
                            log.error message.message, message.thrown
                            break;
                        case IMessage.WARNING:
                            log.warn message.message, message.thrown
                            break;
                        case IMessage.INFO:
                            log.info message.message, message.thrown
                            break;
                        case IMessage.DEBUG:
                            log.debug message.message, message.thrown
                            break;
                    }
                }
            }
        }
    }
}

