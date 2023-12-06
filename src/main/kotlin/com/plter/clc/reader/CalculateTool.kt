package com.plter.clc.reader

import javafx.application.Platform
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.io.BufferedReader
import java.io.File
import java.io.FileInputStream
import java.io.InputStreamReader

object CalculateTool {
    var running = false
    var extsList: List<String>? = null
    var callback: IReaderCallback? = null
    var totalLines = 0

    fun startCalculate(rootDir: File, exts: String, callback: IReaderCallback) {

        this.callback = callback

        if (running) {
            return
        }

        running = true

        extsList = exts.lowercase().split(";")
        GlobalScope.launch {
            totalLines = 0
            readDir(rootDir)
            Platform.runLater {
                this@CalculateTool.callback?.onComplete()
            }
            running = false
        }
    }

    fun readDir(dir: File) {
        val children = dir.listFiles()
        children.forEach { file ->
            if (file.isDirectory) {
                readDir(file)
            } else if (file.isFile) {
                extsList?.forEach extNameLoop@{ ext ->
                    if (file.name.lowercase().endsWith(ext)) {
                        val currentLines = readFileLines(file)
                        totalLines += currentLines
                        Platform.runLater {
                            this.callback?.onProgress(file, currentLines, totalLines)
                        }
                        return@extNameLoop
                    }
                }
            }
        }
    }

    fun readFileLines(file: File): Int {
        val reader = BufferedReader(InputStreamReader(FileInputStream(file), "UTF-8"))
        return reader.readLines().size
    }

    fun isRunning(): Boolean {
        return running
    }
}