package com.example.modelresp

import com.google.gson.annotations.SerializedName

data class UserResp(
    @SerializedName("data")
    val userDataList: List<UserData>
)

data class UserData(
    @SerializedName("pk")
    val userId: String,
    @SerializedName("username")
    val username: String,
    @SerializedName("full_name")
    val fullName: String,
    @SerializedName("is_private")
    val isPrivate: Boolean,
    @SerializedName("profile_pic_url")
    val profilePicUrl: String,
    @SerializedName("profile_pic_url_hd")
    val profilePicUrlHd: String,
    @SerializedName("is_verified")
    val isVerified: Boolean,
    @SerializedName("follower_count")
    val followerCount: Int,
    @SerializedName("following_count")
    val followingCount: Int,
    @SerializedName("biography")
    val biography: String,
    @SerializedName("mutual_followers_count")
    val mutualFollowersCount: Int,
    @SerializedName("has_guides")
    val hasGuides: Boolean,
    @SerializedName("business_contact_method")
    val businessContactMethod: String,
    @SerializedName("should_show_public_contacts")
    val shouldShowPublicContacts: Boolean,
    @SerializedName("should_show_category")
    val shouldShowCategory: Boolean,
    @SerializedName("is_business")
    val isBusiness: Boolean,
    @SerializedName("is_professional")
    val isProfessional: Boolean,
    @SerializedName("media_count")
    val mediaCount: Int,
    @SerializedName("pronouns")
    val pronouns: List<String>,
    @SerializedName("is_new_to_instagram")
    val isNewToInstagram: Boolean,
    @SerializedName("fbid")
    val fbid: String,
    @SerializedName("hd_profile_pic_url_info")
    val hdProfilePicUrlInfo: HdProfilePicUrlInfo,
    @SerializedName("_type")
    val type: Int,
    @SerializedName("_r")
    val r: Int
)

data class HdProfilePicUrlInfo(
    @SerializedName("url")
    val url: String,
    @SerializedName("width")
    val width: Int,
    @SerializedName("height")
    val height: Int
)
