package com.example.tancorik.gazetaruapp.presentation.model;

import android.support.annotation.Nullable;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

/**
 * created by Aleksandr Karpachev
 *      20.08.2018
 *
 *      модель содержит всю информацию о новостях с одного RSS
 */
@Root(name = "rss")
public class News {

    @Element(name = "channel")
    private Channel mChannel;

    public Channel getChannel() {
        return mChannel;
    }

    public void setChannel(Channel channel) {
        mChannel = channel;
    }

    /**
     *  корневой элемент содержит информацию о сайте и данном RSS
     */
    public static class Channel {

        @Element(name = "title")
        private String mTitle;

        @Element(name = "link")
        private String mLink;

        @Element(name = "description")
        private String mDescription;

        @Element(name = "pubDate")
        private String mPubDate;

        @ElementList(inline = true)
        private List<Item> mItemList;

        public String getTitle() {
            return mTitle;
        }

        public void setTitle(String title) {
            mTitle = title;
        }

        public String getLink() {
            return mLink;
        }

        public void setLink(String link) {
            mLink = link;
        }

        public String getDescription() {
            return mDescription;
        }

        public void setDescription(String description) {
            mDescription = description;
        }

        public String getPubDate() {
            return mPubDate;
        }

        public void setPubDate(String pubDate) {
            mPubDate = pubDate;
        }

        public List<Item> getItemList() {
            return mItemList;
        }

        public void setItemList(List<Item> itemList) {
            mItemList = itemList;
        }
    }

    /**
     * содержит информацию о каждой новости в пределах одной RSS
     */
    @Root(name = "item")
    public static class Item {

        @Element(name = "title")
        private String mTitle;

        @Element(name = "link")
        private String mLink;

        @Element(name = "category", required = false)
        private String mCategory;

        @Element(name = "pubDate")
        private String mPubDate;

        @Element(name = "description")
        private String mDescription;

        @Element(name = "enclosure", required = false)
        private NewsImage mNewsImage;

        public String getTitle() {
            return mTitle;
        }

        public void setTitle(String title) {
            mTitle = title;
        }

        public String getLink() {
            return mLink;
        }

        public void setLink(String link) {
            mLink = link;
        }

        public @Nullable String getCategory() {
            return mCategory;
        }

        public void setCategory(@Nullable String category) {
            mCategory = category;
        }

        public String getPubDate() {
            return mPubDate;
        }

        public void setPubDate(String pubDate) {
            mPubDate = pubDate;
        }

        public String getDescription() {
            return mDescription;
        }

        public void setDescription(String description) {
            mDescription = description;
        }

        public NewsImage getNewsImage() {
            return mNewsImage;
        }

        public void setNewsImage(NewsImage newsImage) {
            mNewsImage = newsImage;
        }
    }

    /**
     * содержит информацию от картинке к каждой новости
     */
    public static class NewsImage {

        @Attribute(name = "url")
        private String mImageUrl;

        @Attribute(name = "type")
        private String mImageType;

        public String getImageUrl() {
             return mImageUrl;
        }

        public void setImageUrl(String imageUrl) {
            mImageUrl = imageUrl;
        }

        public String getImageType() {
            return mImageType;
        }

        public void setImageType(String imageType) {
            mImageType = imageType;
        }
}

}
