package com.example.util.emotions

import com.example.ui.R

private const val HAPPY = "Alegre"
private const val SURPRISED = "Surpreso(a)"
private const val SAD = "Triste"
private const val IRRITATED = "Irritado(a)"

data class Emotion(
    val emoji: Int,
    val emotionName: Int
) {

    companion object {

        fun listOfEmotions() = arrayListOf(
            Emotion(
                emoji = R.drawable.ic_inoty_laughing_emoji,
                emotionName = R.string.inoty_emotion_happy
            ),
            Emotion(
                emoji = R.drawable.ic_inoty_emoji_surprised,
                emotionName = R.string.inoty_emotion_surprised
            ),
            Emotion(
                emoji = R.drawable.ic_inoty_emoji_sad,
                emotionName = R.string.inoty_emotion_sad
            ),
            Emotion(
                emoji = R.drawable.ic_inoty_emoji_irritated,
                emotionName = R.string.inoty_emotion_irritated
            )
        )
    }
}


fun Int.getEmojiDescription(): Int {
    return when (this) {
        R.drawable.ic_inoty_laughing_emoji -> R.string.inoty_emotion_happy
        R.drawable.ic_inoty_emoji_surprised -> R.string.inoty_emotion_surprised
        R.drawable.ic_inoty_emoji_sad -> R.string.inoty_emotion_sad
        else -> R.string.inoty_emotion_irritated
    }
}

fun Int.getEmotionColor(): Int {
    return when (this) {
        R.drawable.ic_inoty_laughing_emoji -> R.color.color_inoty_happy
        R.drawable.ic_inoty_emoji_surprised -> R.color.color_inoty_surprised
        R.drawable.ic_inoty_emoji_sad -> R.color.color_inoty_sad
        else -> R.color.color_inoty_irritated
    }
}