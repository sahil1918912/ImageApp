package com.sahil.imageapp.domain.repository

interface Downloader {
    fun downloadFile(url: String, fileName: String?)
}