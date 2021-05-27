package com.examples.entities.explore_places.remote

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class RemotePlaces(

	@Expose @field:SerializedName("groups")
	val groups: List<GroupsItem?>? = null
)

data class GroupsItems(

	@Expose @field:SerializedName("venue")
	val venue: RemoteVenue? = null
)

data class GroupsItem(
	@Expose @field:SerializedName("items")
	val items: List<GroupsItems?>? = null
)

data class RemoteVenue(

	@Expose @field:SerializedName("name")
	val name: String? = null,

	@Expose @field:SerializedName("location")
	val location: Location? = null,

	@Expose @field:SerializedName("id")
	val id: String? = null,

	@Expose @field:SerializedName("categories")
	val categories: List<CategoriesItem?>? = null
)

data class CategoriesItem(

	@Expose @field:SerializedName("pluralName")
	val pluralName: String? = null,

	@Expose @field:SerializedName("name")
	val name: String? = null,

	@Expose @field:SerializedName("icon")
	val icon: Icon? = null,

	@Expose @field:SerializedName("id")
	val id: String? = null,

	@Expose @field:SerializedName("shortName")
	val shortName: String? = null,

	@Expose @field:SerializedName("primary")
	val primary: Boolean? = null
)

data class Icon(

	@Expose @field:SerializedName("prefix")
	val prefix: String? = null,

	@Expose @field:SerializedName("suffix")
	val suffix: String? = null
)

data class Location(

	@Expose @field:SerializedName("address")
	val address: String? = null,

	@Expose @field:SerializedName("formattedAddress")
	val formattedAddress: List<String?>? = null
)
