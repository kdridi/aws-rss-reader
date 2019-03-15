package com.serverless;

import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.function.Function;

public class FeedFetchFunction implements Function<FeedFetchRequest, FeedFetchResponse> {
    @Override
    public FeedFetchResponse apply(FeedFetchRequest feedFetchRequest) {
        FeedFetchResponse response = null;

        try {
            final SyndFeedInput feedInput = new SyndFeedInput();
            final SyndFeed feed = feedInput.build(new XmlReader(new URL(feedFetchRequest.location)));

            response = new FeedFetchResponse(feed);
        } catch (FeedException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return response;
    }
}
