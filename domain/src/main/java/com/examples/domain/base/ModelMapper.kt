package com.youxel.domain.base

/**
 * Created by Nader Nabil on 29/10/2020.
 */
interface ModelMapper<From,To> {
    fun convert(from:From?):To
}