package br.com.siecola.androidproject02.network

import com.squareup.moshi.Json

data class OauthTokenResponse (
    @Json(name = "access_token")
    val accessToken: String,

    @Json(name = "expires_in")
    val expiresIn: Int
)