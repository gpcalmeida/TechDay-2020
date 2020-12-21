package com.devcamp.tv.builders

import com.devcamp.domain.TracksUseCase
import com.devcamp.tv.data.TracksManager

object TracksBuilder{
    fun create() : TracksUseCase {
        return TracksUseCase(TracksManager())
    }
}