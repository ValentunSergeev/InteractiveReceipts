package com.valentun.interactiverecipes.data.pojo

import android.os.Parcelable
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonSubTypes.Type
import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.fasterxml.jackson.annotation.JsonTypeInfo.*
import kotlinx.android.parcel.Parcelize

class RecipeOverview @JsonCreator constructor(
    @JsonProperty("id") val id: Long,
    @JsonProperty("name") val title: String,
    @JsonProperty("description") val description: String
)

class Recipe @JsonCreator constructor(
    @JsonProperty("id") val id: Long,
    @JsonProperty("name") val title: String,
    @JsonProperty("description") val description: String,
    @JsonProperty("image_url") val imageUrl: String?,
    @JsonProperty("total_time") val requiredTime: Int,
    @JsonProperty("steps") val sections: List<Section>
)

@Parcelize
class Section @JsonCreator constructor(
    @JsonProperty("name") val name: String,
    @JsonProperty("blocks") val steps: List<Step>
) : Parcelable

const val TYPE_COUNTDOWN = "timer"
const val TYPE_IMAGE = "image"
const val TYPE_TEXT = "text"

const val KEY_TYPE = "type"

@JsonTypeInfo(
    use = Id.NAME,
    include = As.PROPERTY,
    property = KEY_TYPE,
    defaultImpl = UnsupportedStep::class
)
@JsonSubTypes(
    Type(value = TextStep::class, name = TYPE_TEXT),
    Type(value = ImageStep::class, name = TYPE_IMAGE),
    Type(value = CountdownStep::class, name = TYPE_COUNTDOWN)
)
interface Step : Parcelable

@Parcelize
class TextStep @JsonCreator constructor(
    @JsonProperty("text") val text: String) : Step

@Parcelize
class ImageStep @JsonCreator constructor(
    @JsonProperty("image_url") val imageUrl: String) : Step

@Parcelize
class CountdownStep @JsonCreator constructor(
    @JsonProperty("time") val time: Int) : Step

@Parcelize
class UnsupportedStep : Step