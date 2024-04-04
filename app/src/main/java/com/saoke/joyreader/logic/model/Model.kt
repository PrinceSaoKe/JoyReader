package com.saoke.joyreader.logic.model

data class Model<T>(
    val base: BaseModel,
    val data: T,
)