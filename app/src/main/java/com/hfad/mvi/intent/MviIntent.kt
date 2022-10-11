package com.hfad.mvi.intent

sealed class MainIntent{
    object LoadUser:MainIntent()
}

sealed class UserIntent {
    object LoadUser: UserIntent()
}