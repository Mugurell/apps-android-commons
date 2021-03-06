package fr.free.nrw.commons.quiz

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.RadioButton
import fr.free.nrw.commons.R
import fr.free.nrw.commons.TestCommonsApplication
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.powermock.api.mockito.PowerMockito.mock
import org.powermock.reflect.Whitebox
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.LooperMode


@RunWith(RobolectricTestRunner::class)
@Config(sdk = [21], application = TestCommonsApplication::class)
@LooperMode(LooperMode.Mode.PAUSED)
class QuizActivityUnitTest {

    private lateinit var activity: QuizActivity
    private lateinit var positiveAnswer: RadioButton
    private lateinit var negativeAnswer: RadioButton
    private lateinit var view: View

    private val SAMPLE_ALERT_TITLE_VALUE = "Title"
    private val SAMPLE_ALERT_MESSAGE_VALUE = "Message"

    @Mock
    private lateinit var quizController: QuizController

    @Mock
    private lateinit var context: Context


    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        activity = Robolectric.buildActivity(QuizActivity::class.java).get()

        context = mock(Context::class.java)

        view = LayoutInflater.from(activity)
            .inflate(R.layout.answer_layout, null) as View

        Mockito.`when`(context.getString(Mockito.any(Int::class.java)))
            .thenReturn("")
        quizController = QuizController()

        quizController.initialize(context)

        positiveAnswer = view.findViewById(R.id.quiz_positive_answer)
        negativeAnswer = view.findViewById(R.id.quiz_negative_answer)

        activity.positiveAnswer = positiveAnswer
        activity.negativeAnswer = negativeAnswer
    }

    @Test
    @Throws(Exception::class)
    fun checkActivityNotNull() {
        Assert.assertNotNull(activity)
        Assert.assertNotNull(positiveAnswer)
        Assert.assertNotNull(negativeAnswer)
    }

    @Test
    @Throws(Exception::class)
    fun testSetNextQuestion() {
        activity.setNextQuestion()
    }

    @Test
    @Throws(Exception::class)
    fun testOnBackPressed() {
        activity.onBackPressed()
    }

    @Test
    @Throws(Exception::class)
    fun testEvaluateScore() {

        Whitebox.setInternalState(activity, "quiz", quizController.getQuiz())
        Whitebox.setInternalState(activity, "questionIndex", 0)

        activity.evaluateScore()
    }

    @Test
    @Throws(Exception::class)
    fun testCustomAlert() {
        activity.customAlert(SAMPLE_ALERT_TITLE_VALUE, SAMPLE_ALERT_MESSAGE_VALUE)
    }

}