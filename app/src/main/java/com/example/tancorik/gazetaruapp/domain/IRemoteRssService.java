package com.example.tancorik.gazetaruapp.domain;

import com.example.tancorik.gazetaruapp.presentation.model.NewsModel;

import java.io.IOException;

import io.reactivex.Observable;

/**
 * created by Aleksandr Karpachev
 *
 *      21.08.2018
 *
 *  Интерфейс для предоставления новостей в формате NewsModel.
 *
 */
public interface IRemoteRssService {

    NewsModel getNews(String rssUrl) throws IOException;
    Observable<NewsModel> getNewsObservable();

    /**
     * Интерфейс содержит список ссылок на рубрики новостей
     *
     * Если необходимы изменения по количеству рубрик, то следует изменять прямо тут!
     */
    interface IRssList {
        String[] RSS_ARRAY =
                {"https://www.gazeta.ru/export/rss/first.xml",          // Главное / первая полоса
                "https://www.gazeta.ru/export/rss/lenta.xml",           // Главное / хроника дня
                "https://www.gazeta.ru/export/rss/lastnews.xml",        // Главное / новости часа
                "https://www.gazeta.ru/export/rss/politics.xml",        // Политика / заметки
                "https://www.gazeta.ru/export/rss/politics_news.xml",   // Политика / новости
                "https://www.gazeta.ru/export/rss/business.xml",        // Бизнес / заметки
                "https://www.gazeta.ru/export/rss/busnews.xml",         // Бизнес / деловые новости
                "https://www.gazeta.ru/export/rss/social.xml",          // Общество / заметки
                "https://www.gazeta.ru/export/rss/social_more.xml",     // Общество / новости
                "https://www.gazeta.ru/export/rss/lifestyle.xml",       // Стиль / заметки
                "https://www.gazeta.ru/export/rss/lifestyle_news.xml",  // Стиль / новости
                "https://www.gazeta.ru/export/rss/tech_articles.xml",   // Технологии / заметки
                "https://www.gazeta.ru/export/rss/tech_news.xml",       // Технологии / новости
                "https://www.gazeta.ru/export/rss/army.xml",            // Армия / заметки
                "https://www.gazeta.ru/export/rss/army_news.xml",       // Армия / новости
                "https://www.gazeta.ru/export/rss/comments.xml",        // Мнения
                "https://www.gazeta.ru/export/rss/culture.xml",         // Культура / заметки
                "https://www.gazeta.ru/export/rss/culture_more.xml",    // Культура / новости
                "https://www.gazeta.ru/export/rss/science.xml",         // Наука / заметки
                "https://www.gazeta.ru/export/rss/science_more.xml",    // Наука / новости
                "https://www.gazeta.ru/export/rss/sport.xml",           // Спорт / заметки
                "https://www.gazeta.ru/export/rss/sportnews.xml",       // Спорт / новости
                "https://www.gazeta.ru/export/rss/auto.xml",            // Авто / заметки
                "https://www.gazeta.ru/export/rss/autonews.xml"         // Авто / новости
                };
    }
}
