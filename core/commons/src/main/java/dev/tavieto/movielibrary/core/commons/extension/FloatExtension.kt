package dev.tavieto.movielibrary.core.commons.extension

private const val ONE_HUNDRED = 100

fun Float.getPercent(percent: Float) = this * (percent / ONE_HUNDRED)

fun Float.setProportion(proportion: Float) = this * proportion
