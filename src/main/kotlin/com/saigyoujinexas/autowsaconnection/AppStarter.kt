package com.saigyoujinexas.autowsaconnection

import com.intellij.openapi.module.ModuleManager
import com.intellij.openapi.project.Project
import com.intellij.openapi.startup.StartupActivity
import org.codehaus.groovy.reflection.android.AndroidSupport

class AppStarter : StartupActivity {
    override fun runActivity(project: Project) {
        if (haveAdbTool()) {
            Runtime.getRuntime().exec("adb connect 127.0.0.1:58526\n")
        }
    }

    private fun isAndroidProject(project: Project): Boolean {
        //determine current project is android project or not
        return AndroidSupport.isRunningAndroid()
    }

    private fun haveAdbTool(): Boolean {
        //determine current project is android project or not
        // whether relative path file app/src/main/AndroidManifest.xml exists
        // please write code.
        val command = "adb version"
        val process = Runtime.getRuntime().exec(command);
        val input = process.inputStream.bufferedReader()
        var line: String?;
        while ((input.readLine().also { line = it }) != null) {
            if (line!!.contains("Android Debug Bridge version")) {
                return true;
            }
        }
        return false;
    }

}