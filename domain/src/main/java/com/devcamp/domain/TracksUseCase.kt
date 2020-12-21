package com.devcamp.domain


class TracksUseCase(private val tracksContract: TracksContract) : UseCase<Unit>(),TracksCallback {
    private lateinit var callback : TracksCallback
    private lateinit var contract : TracksContract

    fun with(callback: TracksCallback): TracksUseCase {
        this.callback = callback
        return this
    }

    override fun onSuccess(tracks: List<Tracks>) {
        callback.onSuccess(tracks)
    }

    override fun onFailure(throwable: Throwable) {
        callback.onFailure(throwable)
    }

    override fun execute() {
        this.contract = tracksContract
        contract.getTracks(callback)
    }
}