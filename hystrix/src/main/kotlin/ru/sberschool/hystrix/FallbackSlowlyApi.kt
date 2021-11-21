package ru.sberschool.hystrix

class FallbackSlowlyApi : SlowlyApi {
    override fun getLocation(): Location {
        return Location("fallback")
    }
}


