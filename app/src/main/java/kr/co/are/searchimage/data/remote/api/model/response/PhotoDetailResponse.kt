package kr.co.are.searchimage.data.remote.api.model.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PhotoDetailResponse(
    @Json(name = "id")
    val id: String,
    @Json(name = "slug")
    val slug: String?,
    @Json(name = "created_at")
    val createdAt: String?,
    @Json(name = "updated_at")
    val updatedAt: String?,
    @Json(name = "promoted_at")
    val promotedAt: String?,
    @Json(name = "width")
    val width: Int?,
    @Json(name = "height")
    val height: Int?,
    @Json(name = "color")
    val color: String?,
    @Json(name = "blur_hash")
    val blurHash: String?,
    @Json(name = "description")
    val description: String?,
    @Json(name = "alt_description")
    val altDescription: String?,
    @Json(name = "urls")
    val urls: Urls?,
    @Json(name = "likes")
    val likes: Int?,
    @Json(name = "liked_by_user")
    val likedByUser: Boolean?,
    @Json(name = "current_user_collections")
    val currentUserCollections: List<String>?,
    @Json(name = "user")
    val user: User?
) {
    @JsonClass(generateAdapter = true)
    data class Urls(
        @Json(name = "raw")
        val raw: String?,
        @Json(name = "full")
        val full: String?,
        @Json(name = "regular")
        val regular: String?,
        @Json(name = "small")
        val small: String?,
        @Json(name = "thumb")
        val thumb: String?,
        @Json(name = "small_s3")
        val smallS3: String?
    )

    @JsonClass(generateAdapter = true)
    data class User(
        @Json(name = "id")
        val id: String?,
        @Json(name = "updated_at")
        val updatedAt: String?,
        @Json(name = "username")
        val username: String?,
        @Json(name = "name")
        val name: String?,
        @Json(name = "first_name")
        val firstName: String?,
        @Json(name = "last_name")
        val lastName: String?,
        @Json(name = "twitter_username")
        val twitterUsername: String?,
        @Json(name = "portfolio_url")
        val portfolioUrl: String?,
        @Json(name = "bio")
        val bio: String?,
        @Json(name = "location")
        val location: String?,
        @Json(name = "instagram_username")
        val instagramUsername: String?,
        @Json(name = "total_collections")
        val totalCollections: Int?,
        @Json(name = "total_likes")
        val totalLikes: Int?,
        @Json(name = "total_photos")
        val totalPhotos: Int?,
        @Json(name = "total_promoted_photos")
        val totalPromotedPhotos: Int?,
        @Json(name = "accepted_tos")
        val acceptedTos: Boolean?,
        @Json(name = "for_hire")
        val forHire: Boolean?,
    )
}


/*
{
        "id": "bhKqZNZeAR0",
        "slug": "a-lone-person-standing-in-the-middle-of-a-desert-bhKqZNZeAR0",
        "created_at": "2023-04-28T13:09:43Z",
        "updated_at": "2024-01-02T22:43:37Z",
        "promoted_at": "2023-05-10T13:48:43Z",
        "width": 8640,
        "height": 5760,
        "color": "#402626",
        "blur_hash": "LUExLe59IV%K0g=_t6M|EMWWxZj[",
        "description": "Nature Reserve - NEOM, Saudi Arabia | The NEOM Nature Reserve region is being designed to deliver protection and restoration of biodiversity across 95% of NEOM.",
        "alt_description": "a lone person standing in the middle of a desert",
        "breadcrumbs": [],
        "urls": {
            "raw": "https://images.unsplash.com/photo-1682687221006-b7fd60cf9dd0?ixid=M3w1NDc3NTB8MXwxfGFsbHwxfHx8fHx8Mnx8MTcwNDI5NDgwMnw&ixlib=rb-4.0.3",
            "full": "https://images.unsplash.com/photo-1682687221006-b7fd60cf9dd0?crop=entropy&cs=srgb&fm=jpg&ixid=M3w1NDc3NTB8MXwxfGFsbHwxfHx8fHx8Mnx8MTcwNDI5NDgwMnw&ixlib=rb-4.0.3&q=85",
            "regular": "https://images.unsplash.com/photo-1682687221006-b7fd60cf9dd0?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w1NDc3NTB8MXwxfGFsbHwxfHx8fHx8Mnx8MTcwNDI5NDgwMnw&ixlib=rb-4.0.3&q=80&w=1080",
            "small": "https://images.unsplash.com/photo-1682687221006-b7fd60cf9dd0?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w1NDc3NTB8MXwxfGFsbHwxfHx8fHx8Mnx8MTcwNDI5NDgwMnw&ixlib=rb-4.0.3&q=80&w=400",
            "thumb": "https://images.unsplash.com/photo-1682687221006-b7fd60cf9dd0?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w1NDc3NTB8MXwxfGFsbHwxfHx8fHx8Mnx8MTcwNDI5NDgwMnw&ixlib=rb-4.0.3&q=80&w=200",
            "small_s3": "https://s3.us-west-2.amazonaws.com/images.unsplash.com/small/photo-1682687221006-b7fd60cf9dd0"
        },
        "links": {
            "self": "https://api.unsplash.com/photos/a-lone-person-standing-in-the-middle-of-a-desert-bhKqZNZeAR0",
            "html": "https://unsplash.com/photos/a-lone-person-standing-in-the-middle-of-a-desert-bhKqZNZeAR0",
            "download": "https://unsplash.com/photos/bhKqZNZeAR0/download?ixid=M3w1NDc3NTB8MXwxfGFsbHwxfHx8fHx8Mnx8MTcwNDI5NDgwMnw",
            "download_location": "https://api.unsplash.com/photos/bhKqZNZeAR0/download?ixid=M3w1NDc3NTB8MXwxfGFsbHwxfHx8fHx8Mnx8MTcwNDI5NDgwMnw"
        },
        "likes": 546,
        "liked_by_user": false,
        "current_user_collections": [],
        "sponsorship": {
            "impression_urls": [
                "https://secure.insightexpressai.com/adServer/adServerESI.aspx?script=false&bannerID=11515585&rnd=[timestamp]&redir=https://secure.insightexpressai.com/adserver/1pixel.gif",
                "https://secure.insightexpressai.com/adServer/adServerESI.aspx?script=false&bannerID=11515788&rnd=[timestamp]&redir=https://secure.insightexpressai.com/adserver/1pixel.gif"
            ],
            "tagline": "Made to Change",
            "tagline_url": "https://www.neom.com/en-us?utm_source=unsplash&utm_medium=referral",
            "sponsor": {
                "id": "mYizSrdJkkU",
                "updated_at": "2024-01-03T13:52:16Z",
                "username": "neom",
                "name": "NEOM",
                "first_name": "NEOM",
                "last_name": null,
                "twitter_username": "neom",
                "portfolio_url": "http://www.neom.com",
                "bio": "Located in the northwest of Saudi Arabia, NEOM’s diverse climate offers both sun-soaked beaches and snow-capped mountains. NEOM’s unique location will provide residents with enhanced livability while protecting 95% of the natural landscape.",
                "location": "NEOM, Saudi Arabia",
                "links": {
                    "self": "https://api.unsplash.com/users/neom",
                    "html": "https://unsplash.com/@neom",
                    "photos": "https://api.unsplash.com/users/neom/photos",
                    "likes": "https://api.unsplash.com/users/neom/likes",
                    "portfolio": "https://api.unsplash.com/users/neom/portfolio",
                    "following": "https://api.unsplash.com/users/neom/following",
                    "followers": "https://api.unsplash.com/users/neom/followers"
                },
                "profile_image": {
                    "small": "https://images.unsplash.com/profile-1679489218992-ebe823c797dfimage?ixlib=rb-4.0.3&crop=faces&fit=crop&w=32&h=32",
                    "medium": "https://images.unsplash.com/profile-1679489218992-ebe823c797dfimage?ixlib=rb-4.0.3&crop=faces&fit=crop&w=64&h=64",
                    "large": "https://images.unsplash.com/profile-1679489218992-ebe823c797dfimage?ixlib=rb-4.0.3&crop=faces&fit=crop&w=128&h=128"
                },
                "instagram_username": "discoverneom",
                "total_collections": 7,
                "total_likes": 1,
                "total_photos": 202,
                "total_promoted_photos": 72,
                "accepted_tos": true,
                "for_hire": false,
                "social": {
                    "instagram_username": "discoverneom",
                    "portfolio_url": "http://www.neom.com",
                    "twitter_username": "neom",
                    "paypal_email": null
                }
            }
        },
        "topic_submissions": {},
        "user": {
            "id": "mYizSrdJkkU",
            "updated_at": "2024-01-03T13:52:16Z",
            "username": "neom",
            "name": "NEOM",
            "first_name": "NEOM",
            "last_name": null,
            "twitter_username": "neom",
            "portfolio_url": "http://www.neom.com",
            "bio": "Located in the northwest of Saudi Arabia, NEOM’s diverse climate offers both sun-soaked beaches and snow-capped mountains. NEOM’s unique location will provide residents with enhanced livability while protecting 95% of the natural landscape.",
            "location": "NEOM, Saudi Arabia",
            "links": {
                "self": "https://api.unsplash.com/users/neom",
                "html": "https://unsplash.com/@neom",
                "photos": "https://api.unsplash.com/users/neom/photos",
                "likes": "https://api.unsplash.com/users/neom/likes",
                "portfolio": "https://api.unsplash.com/users/neom/portfolio",
                "following": "https://api.unsplash.com/users/neom/following",
                "followers": "https://api.unsplash.com/users/neom/followers"
            },
            "profile_image": {
                "small": "https://images.unsplash.com/profile-1679489218992-ebe823c797dfimage?ixlib=rb-4.0.3&crop=faces&fit=crop&w=32&h=32",
                "medium": "https://images.unsplash.com/profile-1679489218992-ebe823c797dfimage?ixlib=rb-4.0.3&crop=faces&fit=crop&w=64&h=64",
                "large": "https://images.unsplash.com/profile-1679489218992-ebe823c797dfimage?ixlib=rb-4.0.3&crop=faces&fit=crop&w=128&h=128"
            },
            "instagram_username": "discoverneom",
            "total_collections": 7,
            "total_likes": 1,
            "total_photos": 202,
            "total_promoted_photos": 72,
            "accepted_tos": true,
            "for_hire": false,
            "social": {
                "instagram_username": "discoverneom",
                "portfolio_url": "http://www.neom.com",
                "twitter_username": "neom",
                "paypal_email": null
            }
        }
    }
*/