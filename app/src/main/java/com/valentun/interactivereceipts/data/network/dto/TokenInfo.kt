package com.valentun.interactivereceipts.data.network.dto

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty

class TokenInfo @JsonCreator constructor(
        @param:JsonProperty("token") val token: String)