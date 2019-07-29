package com.valentun.interactiverecipes.ui.activity.cooking

import android.Manifest
import android.content.Intent
import android.os.Bundle
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.valentun.interactiverecipes.R
import com.valentun.interactiverecipes.data.pojo.Recipe
import com.valentun.interactiverecipes.presentation.presenter.cooking.CookingPresenter
import com.valentun.interactiverecipes.presentation.view.cooking.CookingView
import com.valentun.interactiverecipes.ui.activity.BaseActivity
import com.valentun.interactiverecipes.ui.navigation.navigator.LeafNavigator
import com.valentun.interactiverecipes.utils.voice.VoiceRecognitionCallback
import kotlinx.android.synthetic.main.activity_cooking.*


const val EXTRA_ID = "EXTRA_ID"

class CookingActivity : BaseActivity(), CookingView, VoiceRecognitionCallback {
    @InjectPresenter
    lateinit var presenter: CookingPresenter

    private var speechRecognizer: SpeechRecognizer? = null

    @ProvidePresenter
    fun provideRecipePresenter() = CookingPresenter(getExtraId())

    override fun provideNavigator() = LeafNavigator(this)

    override fun nextPage(newSectionPosition: Int) {
        cookingPager.currentItem = newSectionPosition
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_cooking)

        cookingPager.isUserInputEnabled = false

        requiresPermission(Manifest.permission.RECORD_AUDIO) {
            setupVoiceControl()
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        speechRecognizer?.stopListening()
        speechRecognizer?.destroy()
    }

    override fun showProgress() {
        cookingContainer.showProgress()
    }

    override fun hideProgress() {
        cookingContainer.hideProgress()
    }

    override fun showRecipe(recipe: Recipe) {
        cookingPager.adapter = SectionAdapter(this, recipe.sections)
    }

    private fun setupVoiceControl() {
        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(this)

        speechRecognizer!!.setRecognitionListener(this)

        startListening()
    }

    private fun startListening() {
        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)

        speechRecognizer!!.startListening(intent)
    }

    override fun keywordsDetected(results: List<String>) {
        val nextKeyword = getString(R.string.recognition_next)

        results.forEach {
            when (it) {
                nextKeyword -> presenter.nextRecognized()
            }
        }

        startListening() // do it again until forever
    }

    private fun getExtraId() = intent.getLongExtra(EXTRA_ID, 0)
}
