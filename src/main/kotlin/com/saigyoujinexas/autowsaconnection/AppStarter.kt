package com.saigyoujinexas.autowsaconnection

import com.intellij.openapi.project.Project
import com.intellij.openapi.startup.StartupActivity

class AppStarter : StartupActivity {
    override fun runActivity(project: Project) {
        if (haveAdbTool()) {
            Runtime.getRuntime().exec("adb connect 127.0.0.1:58526\n")
        }
    }
    private fun haveAdbTool(): Boolean {
        val command = "adb version"
        return runCatching {
            val process = Runtime.getRuntime().exec(command)
            val input = process.inputStream.bufferedReader()
            var line: String?
            while ((input.readLine().also { line = it }) != null) {
                if (line?.contains("Android Debug Bridge version") == true) return@runCatching  true
            }

            false
        }.getOrElse {
            false
        }
    }
}