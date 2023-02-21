package com.space.conquestofspace.data.remote.exception

import java.io.IOException

class NoInternetConnectionException : IOException() {
    override val message: String
        get() = "No network connection"
}
