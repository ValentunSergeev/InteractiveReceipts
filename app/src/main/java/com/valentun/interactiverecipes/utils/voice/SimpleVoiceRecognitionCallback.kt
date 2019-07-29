package com.valentun.interactiverecipes.utils.voice

import android.os.Bundle
import android.speech.RecognitionListener
import android.speech.SpeechRecognizer

interface VoiceRecognitionCallback : RecognitionListener {
    override fun onReadyForSpeech(params: Bundle?) {
    }

    override fun onRmsChanged(rmsdB: Float) {
    }

    override fun onBufferReceived(buffer: ByteArray?) {
    }

    override fun onPartialResults(partialResults: Bundle?){
    }

    override fun onEvent(eventType: Int, params: Bundle?) {
    }

    override fun onBeginningOfSpeech() {
    }

    override fun onEndOfSpeech() {
    }

    override fun onError(error: Int) {
    }

    @Suppress("UNCHECKED_CAST")
    override fun onResults(results: Bundle) {
        keywordsDetected(results[SpeechRecognizer.RESULTS_RECOGNITION] as List<String>)
    }

    fun keywordsDetected(results: List<String>)
}