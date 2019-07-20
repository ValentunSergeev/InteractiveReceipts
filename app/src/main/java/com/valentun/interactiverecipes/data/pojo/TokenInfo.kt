package com.valentun.interactiverecipes.data.pojo

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty

class TokenInfo @JsonCreator constructor(
        @param:JsonProperty("token") val token: String)