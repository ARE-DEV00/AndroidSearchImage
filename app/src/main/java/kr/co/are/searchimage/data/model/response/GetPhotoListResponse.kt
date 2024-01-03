package kr.co.are.searchimage.data.model.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GetPhotoListResponse(
    @Json(name = "id")
    val id: String
) {


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