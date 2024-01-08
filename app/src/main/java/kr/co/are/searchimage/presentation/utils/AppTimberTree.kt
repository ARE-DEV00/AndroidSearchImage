package kr.co.are.searchimage.presentation.utils

import timber.log.Timber

class AppTimberTree : Timber.DebugTree() {
    override fun createStackElementTag(element: StackTraceElement): String {
        return "[ARE][${element.fileName}][${element.lineNumber}]\t${element.methodName}"
    }

}