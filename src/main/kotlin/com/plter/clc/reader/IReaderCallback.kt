package com.plter.clc.reader

import java.io.File

interface IReaderCallback {

    fun onProgress(currentFile: File, currentLines: Int, linesTotal: Int)
    fun onComplete()
}