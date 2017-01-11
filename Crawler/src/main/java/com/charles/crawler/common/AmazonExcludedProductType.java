package com.charles.crawler.common;

/**
 * Created by ChenCheng on 1/11/2017.
 */
public enum AmazonExcludedProductType {
    AMAZON_PRODUCT_MOVIE_TRAILER {
        @Override
        public String toString() {
            return "body[class=movie_trailer en_US]";
        }
    },
    AMAZON_PRODUCT_GAME_TRAILER {
        @Override
        public String toString() {
            return "body[class=video_game_trailer en_US]";
        }
    },
    AMAZON_PRODUCT_MUSIC_VIDEO {
        @Override
        public String toString() {
            return "body[class=music_video en_US]";
        }
    },
    AMAZON_PRODUCT_HOW_TO_VIDEO {
        @Override
        public String toString() {
            return "body[class=how_to_video en_US]";
        }
    };
}
