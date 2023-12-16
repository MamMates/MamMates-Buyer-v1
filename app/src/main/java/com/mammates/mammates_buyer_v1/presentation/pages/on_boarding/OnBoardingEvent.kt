package com.mammates.mammates_buyer_v1.presentation.pages.on_boarding

sealed class OnBoardingEvent {
    data object SetIntroIsDone : OnBoardingEvent()
}