package com.examples.entities.base

sealed class RelativeDateTime {
    override fun equals(other: Any?): Boolean {
        return if (other is FewSeconds && this is FewSeconds) true
        else if (other is Today && this is Today && other.date == this.date) true
        else if (other is RangeWeek && this is RangeWeek && other.numOfDays == this.numOfDays) true
        else other is MoreThanWeek && this is MoreThanWeek && other.date == this.date
    }

    override fun hashCode(): Int {
        return javaClass.hashCode()
    }
}

object FewSeconds : RelativeDateTime()

class Today(val date: String) : RelativeDateTime()

class RangeWeek(val numOfDays: Int) : RelativeDateTime()

class MoreThanWeek(val date: String) : RelativeDateTime()