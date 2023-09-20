package com.example.common.data.mapper

interface Mapper<S, R> {

    fun map(source: S): R
}